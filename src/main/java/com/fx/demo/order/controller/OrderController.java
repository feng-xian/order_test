/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fx.demo.order.controller;

import com.fx.demo.order.controller.param.AllocationParam;
import com.fx.demo.order.controller.param.OrderListParam;
import com.fx.demo.order.controller.param.SaveParam;
import com.fx.demo.order.controller.param.UpdateParam;
import com.fx.demo.order.entity.Order;
import com.fx.demo.order.service.OrderService;
import com.fx.demo.order.service.dto.DeptOverdue;
import com.fx.demo.order.utils.PageData;
import com.fx.demo.order.utils.ResponseResult;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource(name = "orderServiceImpl")
    private OrderService orderService;


    /**
     * 查询列表
     *
     * @param param
     * @return
     */
    @RequestMapping("/search")
    public ResponseResult<PageData<Order>> searchList(@RequestBody OrderListParam param) {

        PageData<Order> search = orderService.search(param);

        return new ResponseResult<>(search);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseResult<Order> detail(@PathVariable("id") Integer id) {
        Order order = orderService.detail(id);
        return new ResponseResult<>(order);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseResult<?> delete(@PathVariable("id") Integer id) {
        orderService.deleteById(id);
        return new ResponseResult<>();
    }

    /**
     * 添加
     * @param param
     * @return
     */
    @PostMapping("/save")
    public ResponseResult<?> save(@RequestBody @Validated SaveParam param){
        orderService.save(param);
        return new ResponseResult<>();
    }

    /**
     * 修改
     * @param param
     * @return
     */
    @PostMapping("/update")
    public ResponseResult<?> update(@RequestBody @Validated UpdateParam param){
        orderService.update(param);
        return new ResponseResult<>();
    }

    /**
     * 工单分派
     * @param param
     * @return
     */
    @PostMapping("/allocation")
    public ResponseResult<?> allocation(@RequestBody @Validated AllocationParam param) {
        orderService.allocation(param);
        return new ResponseResult<>();
    }

    /**
     * 2、查询7月每个部门的工单总量、超期率
     * @return
     */
    @PostMapping("/deptOverdue")
    public ResponseResult<Map<String, DeptOverdue>> deptOverdue() {
        Map<String, DeptOverdue> deptOverdue = orderService.deptOverdue();
        return new ResponseResult<>(deptOverdue);
    }

    /**
     * 3、查询7月每个工单类型的工单总量、超期率
     * @return
     */
    @PostMapping("/orderTypeOverdue")
    public ResponseResult<Map<Integer, DeptOverdue>> orderTypeOverdue() {
        Map<Integer, DeptOverdue> deptOverdue = orderService.orderTypeOverdue();
        return new ResponseResult<>(deptOverdue);
    }

}
