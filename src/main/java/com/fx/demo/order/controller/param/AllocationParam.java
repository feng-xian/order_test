package com.fx.demo.order.controller.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AllocationParam {
    @NotBlank(message = "工单编号不能为空")
    private String orderNo;

    @NotNull(message = "部门id不能为空")
    private String deptId;

    @NotBlank(message = "部门名称不能为空")
    private String deptName;
}
