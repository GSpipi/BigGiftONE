package com.twogroup.biggift.main.weishiyuan.Fragmentools.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/19.
 */
public class BaseBean implements Serializable{
    private int id;
    private String title;
    @JSONField(name = "cover_image_url")
    private String pic;
    @JSONField(name="likes_count")
    private  int count;

    @JSONField(name = "content_url")
    private String strategy_for_details;

    public void setStrategy_for_details(String strategy_for_details) {
        this.strategy_for_details = strategy_for_details;
    }

    public String getStrategy_for_details() {

        return strategy_for_details;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
