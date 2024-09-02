package com.fx.demo.order.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {


    private Integer total;

    private List<T> data;

    public PageData(Integer total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}
