package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by gaoshuai on 2015/12/20.
 */
public class SpecialUtils implements Serializable{
    private String bannerurl;
    private String coverurl;
    private String creattime;
    private int id;
    private int count;
    private String subtitle;
    private String title;
    private String updatatime;

    public SpecialUtils() {
    }

    /**
     * 使用的是 注解  name 必须和 json数据的 名字 一样
     *
     * @return
     */
    public String getBannerurl() {
        return bannerurl;
    }

    @JSONField(name = "banner_image_url")
    public void setBannerurl(String bannerurl) {
        this.bannerurl = bannerurl;
    }

    public String getCoverurl() {
        return coverurl;
    }

    @JSONField(name = "cover_image_url")
    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getCreattime() {
        return creattime;
    }

    @JSONField(name = "created_at")
    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    @JSONField(name = "posts_count")
    public void setCount(int count) {
        this.count = count;
    }

    public String getSubtitle() {
        return subtitle;
    }

    @JSONField(name = "subtitle")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    @JSONField(name = "title")
    public void setTielt(String title) {
        this.title = title;
    }

    public String getUpdatatime() {
        return updatatime;
    }

    @JSONField(name = "updated_at")
    public void setUpdatatime(String updatatime) {
        this.updatatime = updatatime;
    }
}
