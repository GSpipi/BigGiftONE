package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by gaoshuai on 2015/12/22.
 */
public class GiftSecondUtils {
    private String icon_url;
    private int id;
    private int items_count;
    private String name;
    private int parent_id;
    private int headId;



    public GiftSecondUtils() {
    }

    public String getIcon_url() {
        return icon_url;
    }

    @JSONField(name = "icon_url")
    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public int getItems_count() {
        return items_count;
    }

    @JSONField(name = "items_count")
    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }

    public String getName() {
        return name;
    }

    @JSONField(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    @JSONField(name = "parent_id")
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }
}
