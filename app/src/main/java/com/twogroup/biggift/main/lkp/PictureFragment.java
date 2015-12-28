package com.twogroup.biggift.main.lkp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.entity.Product;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment {


    private View view;
    private RecyclerView recycler;
    private String path;

    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void setArguments(Bundle args) {
        if (args != null) {
            path = args.getString("path");
            Log.i("----2--", path);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_picture, container, false);
        initView();
        return view;
    }

    private void initView() {
        recycler = ((RecyclerView) view.findViewById(R.id.recyclerView));
        //设置layoutManager  参数含义显而易见，2就是2列，第二个参数是垂直方向
        recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        initData();

        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration((int) ((Math.random() * 80))+30);
        recycler.addItemDecoration(decoration);

    }


    private void initData() {
        RequestParams params = new RequestParams(path);


        Log.i("----2--path", path);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject res = JSON.parseObject(result).getJSONObject("res");
                    Log.i("----2--res", res.toString());
                    JSONArray wallpaper = res.getJSONArray("wallpaper");
                    Log.i("----2--wallpager", wallpaper.toString());
                    List<Product> products = JSON.parseArray(wallpaper.toJSONString(), Product.class);
                    Log.i("----2--products", products.get(0).getPreview() + products.get(1).getImg());
                    MyAdapter myAdapter = new MyAdapter(products);
                    recycler.setAdapter(myAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                cex.printStackTrace();
            }

            @Override
            public void onFinished() {
                Log.w("----2--", "下载结束");
            }
        });
    }
}


