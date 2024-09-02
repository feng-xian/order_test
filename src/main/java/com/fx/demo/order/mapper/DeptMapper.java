package com.fx.demo.order.mapper;

import com.fx.demo.order.controller.param.SaveParam;
import com.fx.demo.order.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

    int countDetpById(@Param("deptId")String deptId);

    List<Dept> getAllDept();

}
