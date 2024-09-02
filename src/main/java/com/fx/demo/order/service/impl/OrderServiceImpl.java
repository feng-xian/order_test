package com.fx.demo.order.service.impl;

import com.fx.demo.order.controller.param.AllocationParam;
import com.fx.demo.order.controller.param.OrderListParam;
import com.fx.demo.order.controller.param.SaveParam;
import com.fx.demo.order.controller.param.UpdateParam;
import com.fx.demo.order.entity.Dept;
import com.fx.demo.order.entity.Order;
import com.fx.demo.order.mapper.OrderMapper;
import com.fx.demo.order.service.DeptService;
import com.fx.demo.order.service.OrderService;
import com.fx.demo.order.service.dto.DeptOverdue;
import com.fx.demo.order.utils.JacksonUtils;
import com.fx.demo.order.utils.PageData;
import com.google.common.collect.Maps;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;


    @Override
    public PageData<Order> search(OrderListParam param) {

        List<Order> orders = orderMapper.searchList(param);
        Integer count = orderMapper.countList(param);

        return new PageData<>(count, orders);
    }

    @Override
    public Order detail(Integer id) {
        return orderMapper.queryById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SaveParam param) {

        Order order = JacksonUtils.obj2pojo(param, Order.class);
        order.setCreateTime(LocalDateTime.now());
        order.setIsOverdue(0);

        doSave(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UpdateParam param) {
        Order order = JacksonUtils.obj2pojo(param, Order.class);

        doUpdate(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocation(AllocationParam param) {
        validated(param);
        Order order = orderMapper.queryByOrderNo(param.getOrderNo());

        order.setHandleDeptId(param.getDeptId());
        order.setFenpaiTime(LocalDateTime.now());

        doUpdate(order);

    }

    @Override
    public Map<String, DeptOverdue> deptOverdue() {
        List<Dept> allDept = deptService.getAllDept();

        List<String> deptIdList = allDept.stream().map(Dept::getDeptId).collect(Collectors.toList());
        List<Order> search = orderMapper.searchByDeptId("7", deptIdList);

        if (CollectionUtils.isEmpty(search)) {
            throw new RuntimeException("未查询到数据！");
        }


        Map<String, String> idMapName = allDept.stream().collect(Collectors.toMap(Dept::getDeptId, Dept::getDeptName));

        Map<String, List<Order>> groupByDeptId = search.stream().collect(Collectors.groupingBy(Order::getHandleDeptId));


        HashMap<@Nullable String, @Nullable DeptOverdue> map = Maps.newHashMap();

        for (Map.Entry<String, List<Order>> entry : groupByDeptId.entrySet()) {
            String deptId = entry.getKey();
            List<Order> orderList = entry.getValue();

            int overdueNum = (int) orderList.stream().filter(o -> o.getIsOverdue() == 0).count();

            DeptOverdue overdue = new DeptOverdue();
            overdue.setDeptId(deptId);
            overdue.setDeptName(idMapName.get(deptId));
            overdue.setOrderTotal(orderList.size());
            // 取一位小数
            overdue.setOverdue(getOverdue(overdueNum, orderList.size()));
            overdue.setOverdueNum(overdueNum);

            map.put(deptId, overdue);

        }

        return map;

    }

    @Override
    public Map<Integer, DeptOverdue> orderTypeOverdue() {

        List<Order> orders = orderMapper.searchByMonth("7");

        Map<Integer, List<Order>> groupByType = orders.stream().collect(Collectors.groupingBy(Order::getOrderType));

        HashMap<Integer, DeptOverdue> map = Maps.newHashMap();
        Set<Map.Entry<Integer, List<Order>>> entries = groupByType.entrySet();

        for (Map.Entry<Integer, List<Order>> entry : entries) {

            Integer key = entry.getKey();
            List<Order> orderList = entry.getValue();

            int overdueNum = (int) orderList.stream().filter(o -> o.getIsOverdue() == 0).count();

            DeptOverdue overdue = new DeptOverdue();
            overdue.setDeptId("");
            overdue.setDeptName("");
            overdue.setOrderTotal(orderList.size());
            // 取一位小数
            overdue.setOverdue(getOverdue(overdueNum, orderList.size()));
            overdue.setOverdueNum(overdueNum);

            map.put(key, overdue);

        }

        return map;
    }

    private String getOverdue(int total, int overdueNum) {
        double result = (double) overdueNum / total;
        return String.format("%.1f", result);
    }

    public void validated(AllocationParam param) {
        int count = orderMapper.countByOrderNo(param.getOrderNo());

        if (count != 1) {
            throw new RuntimeException("工单编号异常，请联系管理员！");
        }

        deptService.checkDept(param.getDeptId());

    }

    public synchronized void doSave(Order order) {
        int count = orderMapper.countByOrderNo(order.getOrderNo());
        if (count > 0) {
            throw new RuntimeException("编号不能重复!");
        }
        orderMapper.save(order);
    }

    public synchronized void doUpdate(Order order) {
        Order oldOrder = orderMapper.queryById(order.getId());
        int count = orderMapper.countByOrderNo(order.getOrderNo());


        if (count > 1 && !Objects.equals(oldOrder.getOrderNo(), order.getOrderNo())) {
            throw new RuntimeException("编号不能重复!");
        }

        orderMapper.update(order);
    }


}
