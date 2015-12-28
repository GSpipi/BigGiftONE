package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by gaoshuai on 2015/12/22.
 */
public class GiftFirstUtils {

    private String icon_url;
    private String name;
    private int id;
    private int headId;



    public GiftFirstUtils() {
    }

    public String getIcon_url() {
        return icon_url;
    }

    @JSONField(name = "icon_url")
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
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

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }
}
