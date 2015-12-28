package com.twogroup.biggift.main.weishiyuan.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.HomeItemActivity;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.MyAdapter;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.bean.BaseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
public class Fragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener {

    private String imgurl = "http://img02.liwushuo.com/image/151220/jekrns8t3.jpg-w720";
    private String imgur2 = "http://img03.liwushuo.com/image/151220/6vbukrez0.jpg-w720";
    private String imgur3 = "http://img03.liwushuo.com/image/151220/uhqhu2clx.jpg-w720";
    private String path = "http://img03.liwushuo.com/image/151219/abkmxf0dx.jpg-w720";
    private String path1 = "http://img03.liwushuo.com/image/151219/stluk8sm0.jpg-w720";
    private String path6 = "http://img02.liwushuo.com/image/151218/dnwvs90y0.jpg-w720";
    private String path2 = "http://img02.liwushuo.com/image/151219/56jv6itw9.jpg-w720";
    private String path3 = "http://img01.liwushuo.com/image/151216/4n0xpyldi.jpg-w720";
    private String path4 = "http://img03.liwushuo.com/image/151210/enwhfssng.jpg-w720";
    private String path5 = "http://img01.liwushuo.com/image/151216/8erc80eje.jpg-w720";
    private String lvpath = "http://api.liwushuo.com/v2/channels/100/items?limit=20&ad=2&gender=1&offset=0&generation=1";
    private List<ImageView> ImageList, ImageList2;
    private List<String> pathString;
    private ImageView iv[];
    private List<BaseBean> list;
    private ListView lv;
    private MyAdapter myAdapter;
    private View viewfram1list;
    private ViewPager vp;
    private View view;
    private ListView listview;
    private SwipeRefreshLayout swipe;
    private int oldpoistion = 0;
    private int currentItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewfram1list = inflater.inflate(R.layout.fram1list, container, false);

        initHeader();
        initList();

        return viewfram1list;
    }

    //添加到lisiview头部的组件
    private void initHeader() {
        view = LayoutInflater.from(viewfram1list.getContext()).inflate(R.layout.fragment_fragment1, null);
        initImage();
        initImage2();
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.HP_f1Ll);
        vp = (ViewPager) view.findViewById(R.id.HP_f1VP);
        iv = new ImageView[ImageList.size()];
        for (int i = 0; i < ll.getChildCount(); i++) {
            iv[i] = (ImageView) ll.getChildAt(i);
        }
        final ImageAdapter Iadapter = new ImageAdapter();
        vp.setAdapter(Iadapter);
        //滑动监听器
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                iv[oldpoistion].setImageResource(R.mipmap.btn_check_disabled);
                iv[position].setImageResource(R.mipmap.bg_count_red);
                oldpoistion = position;
                currentItem = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //初始化listview
    public void initList() {

        swipe = (SwipeRefreshLayout) viewfram1list.findViewById(R.id.swipelist);
        listview = (ListView) viewfram1list.findViewById(R.id.fram1list);
        listview.addHeaderView(view);
        addlist(lvpath);
        myAdapter = new MyAdapter(new ArrayList<BaseBean>(), getActivity());
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(this);
        swipe.setOnRefreshListener(this);

    }


    //获得listView的数据
    private List<BaseBean> addlist(String lvpath) {
        Log.e("onSuccess: ","lvpath"+lvpath );
        RequestParams params = new RequestParams(lvpath);
        //得到数据源之后进行解析
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSON.parseObject(result).getJSONObject("data");
                JSONArray jsonArray = jsonObject.getJSONArray("items");
//                list = JSON.parseArray(jsonArray.toJSONString(), BaseBean.class);
                int index = 4;
                BaseBean bean;

                list = new ArrayList<BaseBean>();
                for (int i = 3; i < jsonArray.size(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    BaseBean baseBean = JSON.parseObject(object.toString(), BaseBean.class);
                    list.add(baseBean);
                }

                Log.e("onSuccess: ",""+list.size() );
                myAdapter.clear();
                myAdapter.add(list);
                listview.setAdapter(myAdapter);


//                     myAdapter=  new MyAdapter(list,getActivity());
//                listview.setAdapter(myAdapter);
//                myAdapter.notifyDataSetChanged();

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
                swipe.setRefreshing(false);
            }
        });
        return null;
    }

    //小图片的添加
    private void initImage2() {
        ImageList2 = new ArrayList<>();
        ImageView iv1 = (ImageView) view.findViewById(R.id.hp_F1_image1);
        ImageView iv2 = (ImageView) view.findViewById(R.id.hp_F1_image2);
        ImageView iv3 = (ImageView) view.findViewById(R.id.hp_F1_image3);
        ImageView iv4 = (ImageView) view.findViewById(R.id.hp_F1_image4);
        ImageView iv5 = (ImageView) view.findViewById(R.id.hp_F1_image5);
        ImageView iv6 = (ImageView) view.findViewById(R.id.hp_F1_image6);
        ImageView iv7 = (ImageView) view.findViewById(R.id.hp_F1_image7);
        ImageList2.add(iv1);
        ImageList2.add(iv2);
        ImageList2.add(iv3);
        ImageList2.add(iv4);
        ImageList2.add(iv5);
        ImageList2.add(iv6);
        ImageList2.add(iv7);
        pathString = new ArrayList<>();
        pathString.add(path);
        pathString.add(path1);
        pathString.add(path2);
        pathString.add(path3);
        pathString.add(path4);
        pathString.add(path5);
        pathString.add(path6);

        for (int i = 0; i < ImageList2.size(); i++) {
            try{
                x.image().bind(ImageList2.get(i), pathString.get(i));
            }catch (Exception e){e.printStackTrace();}


        }
    }

    //添加viewpage图片位置
    private void initImage() {
        ImageList = new ArrayList<ImageView>();

        ImageView iv = new ImageView(getContext());

        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView iv1 = new ImageView(getContext());

        iv1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView iv2 = new ImageView(getContext());

        iv2.setScaleType(ImageView.ScaleType.FIT_XY);

        ImageList.add(iv);
        ImageList.add(iv1);
        ImageList.add(iv2);
        try{
            x.image().bind(ImageList.get(0), imgurl);
            x.image().bind(ImageList.get(1), imgur2);
            x.image().bind(ImageList.get(2), imgur3);
        }catch (Exception e){e.printStackTrace();}

    }

    @Override//下拉刷新
    public void onRefresh() {
        addlist(lvpath);
        //myAdapter.add(list);
    }

    @Override//条目点击事件
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), HomeItemActivity.class);
        BaseBean beans = (BaseBean) parent.getAdapter().getItem(position);
        intent.putExtra("item", beans);
        startActivity(intent);


    }

    //图片适配器
    class ImageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return ImageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageV = ImageList.get(position);

            container.addView(imageV);
            return imageV;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(ImageList.get(position));
        }
    }

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("handleMessage: ", "(scheduledExecutorService == null ) = "+(scheduledExecutorService == null ));
        if (scheduledExecutorService == null ) {
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 2, TimeUnit.SECONDS);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("handleMessage: ", "onStart() = ");

    }

    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % iv.length;
            handler.obtainMessage().sendToTarget();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("handleMessage: ", "currentItem = "+currentItem);
            vp.setCurrentItem(currentItem);
        }
    };

    @Override
    public void onStop() {
        super.onStop();
    }
}
