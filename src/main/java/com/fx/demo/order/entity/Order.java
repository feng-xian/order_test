package com.fx.demo.order.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    /**
     *
     */
    private Integer id;
    /**
     *工单编号
     */
    private String orderNo;
    /**
     *工单类型,0交办 1直接答复 3无效工单
     */
    private Integer orderType;
    /**
     *标题
     */
    private String title;
    /**
     *内容
     */
    private String content;
    /**
     *处理部门
     */
    private String handleDeptId;

    private LocalDateTime createTime;

    /**
     * 分派时间
     */
    private LocalDateTime fenpaiTime;

    /**
     * 是否超期,0否 1是
     */
    private Integer isOverdue;

}
