package com.fx.demo.order.service.impl;

import com.fx.demo.order.entity.Dept;
import com.fx.demo.order.mapper.DeptMapper;
import com.fx.demo.order.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service("deptServiceImpl")
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public void checkDept(String deptId) {
        int i = deptMapper.countDetpById(deptId);
        if (i != 1) {
            throw new RuntimeException("部门不存在，或存在异常，请联系管理员！");
        }
    }

    @Override
    public List<Dept> getAllDept() {
        List<Dept> allDept = deptMapper.getAllDept();
        if (Objects.isNull(allDept)) {
            throw new RuntimeException("获取部门信息异常");
        }
        return allDept;
    }
}
