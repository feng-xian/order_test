package com.fx.demo.order.service.dto;

import lombok.Data;

/**
 * 部门超期率
 */
@Data
public class DeptOverdue {

    private String deptId;

    private String deptName;

    /**
     * 工单总量
     */
    private int orderTotal;

    /**
     * 超期率
     */
    private String overdue;

    /**
     * 超期工单总量
     */
    private int overdueNum;

}
