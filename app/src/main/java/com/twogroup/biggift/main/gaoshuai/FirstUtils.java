package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by gaoshuai on 2015/12/21.
 */
public class FirstUtils implements Serializable {
    private String name;
    private int id;
    private int order;

    public FirstUtils() {
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    @JSONField(name = "order")
    public void setOrder(int order) {
        this.order = order;
    }
}
