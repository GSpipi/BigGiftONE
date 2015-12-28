package com.twogroup.biggift.main.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by LKP on 2015/12/25.
 */
public class Product {
    private String img;
    private String preview;

    public Product() {
    }

    public Product(String img, String preview) {
        this.img = img;
        this.preview = preview;
    }



    @JSONField(name = "img")
    public void setImg(String img) {
        this.img = img;
    }

    @JSONField(name = "preview")
    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getImg() {
        return img;
    }

    public String getPreview() {
        return preview;
    }
}


