package com.twogroup.biggift.main.weishiyuan.Fragmentools;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.bean.BaseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2015/12/20.
 */
public class XutilsJson {
   private List<BaseBean> list;
    JSONArray jsonArray;
    private ListView lv;
    private String path;
    private Activity activity;
    public XutilsJson(List<BaseBean> list , ListView lv,String path, Activity activity) {
        this.list=list;
        this.activity=activity;
        this.lv=lv;
        this.path=path;
    }


    protected void initData() {
        RequestParams params = new RequestParams(path);
        Log.i("----------path",path);
        x.http().get(params, new Callback.CommonCallback<String>() {





            @Override//得到数据源之后进行解析
            public void onSuccess(String result) {
                System.out.println("----HomeCommonFragment--initData-" + result);
                JSONObject jsonObject = JSON.parseObject(result).getJSONObject("data");
                jsonArray = jsonObject.getJSONArray("items");
                list = JSON.parseArray(jsonArray.toJSONString(), BaseBean.class);

                //listview设置适配
                lv.setAdapter(new MyAdapter(list,activity));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        Log.i("-----------------json",jsonArray.toJSONString());
}}
