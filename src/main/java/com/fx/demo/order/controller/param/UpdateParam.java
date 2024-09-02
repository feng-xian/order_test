package com.fx.demo.order.controller.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateParam {

    /**
     *
     */
    @NotNull(message = "id不能为空！")
    private Integer id;
    /**
     *工单编号
     */
    @NotBlank(message = "工单编号不能为空")
    private String orderNo;
    /**
     *工单类型,0交办 1直接答复 3无效工单
     */
    @NotNull(message = "工单类型不能为空")
    private Integer orderType;
    /**
     *标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;
    /**
     *内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;
    /**
     *处理部门
     */
    private String handleDeptId;


    /**
     * 是否超期,0否 1是
     */
    private Integer isOverdue;

}
