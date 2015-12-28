package com.twogroup.biggift.main.gaoshuai;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.v
 */
public class StrategyFragment extends Fragment {


    private View view;
    private ImageView Image1;
    private ImageView Image2;
    private ImageView Image3;
    private ImageView Image4;
    private ImageView Image5;
    private ImageView Image6;
    private ImageView Image7;
    private ImageView Image8;
    private ImageView Image9;
    private ImageView Image10;
    private TextView lookmore;
    private TextView one_title;
    private GridView one_gridview;
    private TextView two_title;
    private GridView two_gridview;
    private TextView three_title;
    private GridView three_gridview;
    private TextView four_title;
    private GridView four_gridview;

    public StrategyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_strategy, container, false);
        initView();
        initData("http://api.liwushuo.com/v1/collections?offset=0&limit=20");
        initGridViewData();
        return view;
    }

    private void initGridViewData() {
        StrategyGridViewUtils utils = new StrategyGridViewUtils(one_title, one_gridview, 0, getContext());
        utils.initData();
        StrategyGridViewUtils utils2 = new StrategyGridViewUtils(two_title, two_gridview, 1, getContext());
        utils2.initData();
        StrategyGridViewUtils utils3 = new StrategyGridViewUtils(three_title, three_gridview, 2, getContext());
        utils3.initData();
        StrategyGridViewUtils utils4 = new StrategyGridViewUtils(four_title, four_gridview, 3, getContext());
        utils4.initData();

    }

    private void initData(String path) {
        RequestParams params = new RequestParams(path);

        x.http().get(params, new Callback.CommonCallback<String>() {

            private ArrayList<String> string;
            @Override
            public void onSuccess(String result) {
                Log.e("bbbbbbbbbbb", "onSuccess");
                JSONObject data = JSON.parseObject(result).getJSONObject("data");
                JSONArray collections = data.getJSONArray("collections");
                final List<SpecialUtils> list = JSON.parseArray(collections.toJSONString(), SpecialUtils.class);
                ImageOptions options = new ImageOptions.Builder()
                        //.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                        .setRadius(DensityUtil.dip2px(10))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                        .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                        .build();
                //Image1.setBackground();
                x.image().bind(Image1, list.get(0).getBannerurl(), options);
                x.image().bind(Image2, list.get(1).getBannerurl(), options);
                x.image().bind(Image3, list.get(2).getBannerurl(), options);
                x.image().bind(Image4, list.get(3).getBannerurl(), options);
                x.image().bind(Image5, list.get(4).getBannerurl(), options);
                x.image().bind(Image6, list.get(5).getBannerurl(), options);
                x.image().bind(Image7, list.get(6).getBannerurl(), options);
                x.image().bind(Image8, list.get(7).getBannerurl(), options);
                x.image().bind(Image9, list.get(8).getBannerurl(), options);
                x.image().bind(Image10, list.get(9).getBannerurl(), options);


                lookmore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AllSpecial.class);
                        intent.putExtra("list", (Serializable) list);
                        startActivity(intent);
                    }
                });


                setClick(Image1, list.get(0).getId());
                setClick(Image2, list.get(1).getId());
                setClick(Image3, list.get(2).getId());
                setClick(Image4, list.get(3).getId());
                setClick(Image5, list.get(4).getId());
                setClick(Image6, list.get(5).getId());
                setClick(Image7, list.get(6).getId());
                setClick(Image8, list.get(7).getId());
                setClick(Image9, list.get(8).getId());
                setClick(Image10, list.get(9).getId());

            }

            private void setClick(View view, final int s) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Particular.class);
                        intent.putExtra("path", "http://api.liwushuo.com/v2/collections/" + s + "/posts?limit=20&offset=0");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                ex.printStackTrace();
                Log.e("bbbbbbbbbbb", "onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("bbbbbbbbbbb", "onCancelled");
            }

            @Override
            public void onFinished() {
                Log.e("bbbbbbbbbbb", "onFinished");
            }


        });
        Log.e("22222aaaaaaaaa", "2222");
    }

    private void initView() {
        Image1 = ((ImageView) view.findViewById(R.id.img1));
        Image2 = ((ImageView) view.findViewById(R.id.img2));
        Image3 = ((ImageView) view.findViewById(R.id.img3));
        Image4 = ((ImageView) view.findViewById(R.id.img4));
        Image5 = ((ImageView) view.findViewById(R.id.img5));
        Image6 = ((ImageView) view.findViewById(R.id.img6));
        Image7 = ((ImageView) view.findViewById(R.id.img7));
        Image8 = ((ImageView) view.findViewById(R.id.img8));
        Image9 = ((ImageView) view.findViewById(R.id.img9));
        Image10 = ((ImageView) view.findViewById(R.id.img10));
        lookmore = ((TextView) view.findViewById(R.id.look_more));
        one_title = ((TextView) view.findViewById(R.id.classify_strategy_title));
        one_gridview = ((GridView) view.findViewById(R.id.classify_strategy_gridview));
        two_title = ((TextView) view.findViewById(R.id.classify_strategy_title2));
        two_gridview = ((GridView) view.findViewById(R.id.classify_strategy_gridview2));
        three_title = ((TextView) view.findViewById(R.id.classify_strategy_title3));
        three_gridview = ((GridView) view.findViewById(R.id.classify_strategy_gridview3));
        four_title = ((TextView) view.findViewById(R.id.classify_strategy_title4));
        four_gridview = ((GridView) view.findViewById(R.id.classify_strategy_gridview4));

    }

}
