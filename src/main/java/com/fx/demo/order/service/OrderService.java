package com.fx.demo.order.service;


import com.fx.demo.order.controller.param.AllocationParam;
import com.fx.demo.order.controller.param.OrderListParam;
import com.fx.demo.order.controller.param.SaveParam;
import com.fx.demo.order.controller.param.UpdateParam;
import com.fx.demo.order.entity.Order;
import com.fx.demo.order.service.dto.DeptOverdue;
import com.fx.demo.order.utils.PageData;

import java.util.Map;

public interface OrderService {


    PageData<Order> search(OrderListParam param);


    Order detail(Integer id);

    void deleteById(Integer id);

    void save(SaveParam param);

    void update(UpdateParam param);

    void allocation(AllocationParam param);

    Map<String, DeptOverdue> deptOverdue();

    Map<Integer, DeptOverdue> orderTypeOverdue();
}
