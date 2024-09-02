package com.fx.demo.order.service;

import com.fx.demo.order.entity.Dept;

import java.util.List;

public interface DeptService {

    void checkDept(String deptId);

    List<Dept> getAllDept();
}
