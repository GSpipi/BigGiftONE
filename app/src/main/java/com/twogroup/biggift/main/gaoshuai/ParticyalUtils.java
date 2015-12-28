package com.twogroup.biggift.main.gaoshuai;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by gaoshuai on 2015/12/28.
 */
public class ParticyalUtils implements Serializable {
    private String content_url;
    private String cover_image_url;
    private int id;
    private String short_title;
    private String title;
    private String url;

    public ParticyalUtils() {
    }

    public String getContent_url() {
        return content_url;
    }

    @JSONField(name = "content_url")
    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getCover_image_url() {
        return cover_image_url;
    }

    @JSONField(name = "cover_image_url")
    public void setCover_image_url(String cover_image_url) {
        this.cover_image_url = cover_image_url;
    }

    public int getId() {
        return id;
    }

    @JSONField(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getShort_title() {
        return short_title;
    }

    @JSONField(name = "short_title")
    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getTitle() {
        return title;
    }

    @JSONField(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    @JSONField(name = "url")
    public void setUrl(String url) {
        this.url = url;
    }
}
