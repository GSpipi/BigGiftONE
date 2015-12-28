package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by gaoshuai on 2015/12/21.
 */
public class SecondUtils implements Serializable {
    private String name;
    private String icon_url;
    private int id;
    private int parent_id;



    public SecondUtils() {
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_url() {
        return icon_url;
    }

    @JSONField(name = "icon_url")
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    @JSONField(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @JSONField(name = "getParent_id")
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
