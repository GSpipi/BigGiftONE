package com.twogroup.biggift.main.gaoshuai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.BaseActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class Particular extends BaseActivity {

    private String path;
    private TextView title;
    private ListView par_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular);
        path = getIntent().getStringExtra("path");

        initView();
        initData();
    }

    private void initView() {
        title = ((TextView) findViewById(R.id.partic_title));
        par_listview = ((ListView) findViewById(R.id.partic_listview));
    }

    private void initData() {
        try {
            final RequestParams params = new RequestParams(path);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    JSONObject data = JSON.parseObject(result).getJSONObject("data");
                    JSONArray posts = data.getJSONArray("posts");
                    String tt = data.getString("title");
                    final List<ParticyalUtils> parList = JSON.parseArray(posts.toJSONString(), ParticyalUtils.class);
                    title.setText(tt);
                    par_listview.setAdapter(new ParticularListAdapter(getApplicationContext(),parList));
                    par_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(),ContentUtils.class);
                            intent.putExtra("path",parList.get(position).getContent_url());
                            startActivity(intent);
                        }
                    });
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
