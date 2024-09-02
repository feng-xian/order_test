package com.fx.demo.order.mapper;

import com.fx.demo.order.controller.param.OrderListParam;
import com.fx.demo.order.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    Order queryById(@Param("id")Integer id);
    Order queryByOrderNo(@Param("orderNo")String orderNo);

    List<Order> searchList(@Param("param")OrderListParam param);
    Integer countList(@Param("param")OrderListParam param);

    void deleteById(Integer id);

    int save(@Param("order") Order order);

    int countByOrderNo(@Param("orderNo")String orderNo);


    int update(@Param("order")Order order);

    List<Order> searchByDeptId(@Param("month")String month, @Param("deptIdList")List<String> deptIdList);

    List<Order> searchByMonth(@Param("month")String month);



}
