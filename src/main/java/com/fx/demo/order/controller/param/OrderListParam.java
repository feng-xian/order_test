package com.fx.demo.order.controller.param;

import lombok.Data;

@Data
public class OrderListParam {

    /**
     * 工单编号
     */
    private String orderNo;

    /**
     * 类型
     * 0交办 1直接答复 3无效工单
     */
    private String orderType;

    /**
     * 标题
     */
    private String title;

    private Integer pageNum;

    private Integer pageSize;

}
