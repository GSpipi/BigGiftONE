package com.twogroup.biggift.main.weishiyuan.Fragmentools;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.HomeItemActivity;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.bean.BaseBean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


@SuppressLint("ValidFragment")
public class HomeCommonFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {


    private List<BaseBean> list;
    private ListView lv;
    private String path;
    private SwipeRefreshLayout swipe;
    private ProgressBar progress;
    private ImageView loadinga;


    @SuppressLint("ValidFragment")
    public HomeCommonFragment(String path) {
        this.path = path;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        loadinga = ((ImageView) view.findViewById(R.id.id_loading));
        AnimationDrawable loading = (AnimationDrawable) loadinga.getBackground();
        loading.start();
        initlistview(view);

        return view;
    }

    //找到view组件
    private void initlistview(View view) {
        swipe = ((SwipeRefreshLayout) view.findViewById(R.id.swipe));

        lv = (ListView) view.findViewById(R.id.BF_listview);
        initData(path);
        lv.setOnItemClickListener(this);
        swipe.setOnRefreshListener(this);

    }

    //用xutils通过地址进行解析数据源
    protected void initData(String path) {
        RequestParams params = new RequestParams(path);
        x.http().get(params, new Callback.CommonCallback<String>() {


            private MyAdapter myAdapter;


            @Override//得到数据源进行解析
            public void onSuccess(String result) {

                JSONObject jsonObject = JSON.parseObject(result).getJSONObject("data");
                JSONArray jsonArray = jsonObject.getJSONArray("items");
                list = JSON.parseArray(jsonArray.toJSONString(), BaseBean.class);
                if ( list != null&&list.size() > 0){
                    loadinga.setVisibility(View.GONE);
                }
                myAdapter = new MyAdapter(list, getActivity());
                //listview设置适配
                lv.setAdapter(myAdapter);


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

    }

    @Override
    //下拉刷新
    public void onRefresh() {
        initData(path);
    }


    @Override//条目点击事件跳转
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), HomeItemActivity.class);
//        Toast.makeText(getContext(),"点击了条目",Toast.LENGTH_SHORT).show();
        BaseBean beans = (BaseBean) parent.getAdapter().getItem(position);
        intent.putExtra("item", beans);
        startActivity(intent);
    }


    //创建适配器
//    class MyAdapter extends BaseAdapter {
//        ImageOptions options;
//
//        //构造函数
//        public MyAdapter() {
//
////图片参数设置
//            options = new ImageOptions.Builder()
//                    .setLoadingDrawableId(R.mipmap.ic_launch)
//                    .setRadius(20)
//                    .setFailureDrawableId(R.mipmap.ic_launcher)
//                    .setConfig(Bitmap.Config.RGB_565)
//                    .build();
//        }
//
//        @Override
//        public int getCount() {
//            return list != null ? list.size() : 0;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return list.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            final ViewHolder holder;
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.hp_listview_item, null);
//                holder = new ViewHolder();
//                holder.img = ((ImageView) convertView.findViewById(R.id.hp_lv_imageview1));
//                holder.title = ((TextView) convertView.findViewById(R.id.title));
//                holder.count = ((TextView) convertView.findViewById(R.id.item_count));
//                holder.redHeartImg= ((ImageView) convertView.findViewById(R.id.hp_lv_imageview3));
//                holder.relativeLayout= ((RelativeLayout) convertView.findViewById(R.id.hp_listview_rlayout));
//                convertView.setTag(holder);
//            } else {
//                holder = ((ViewHolder) convertView.getTag());
//
//            }
//    //新加 图片
////            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    if (v.getId()==R.id.hp_listview_rlayout ){
////                   //   int whiteheart=  R.mipmap.hp_lv_imagewheart
////
////                        holder.redHeartImg.setImageResource(R.mipmap.hp_lv_imagewheart);
////                    }else { holder.redHeartImg.setImageResource(R.mipmap.hp_list_itemtwo);}
////                }
////            });
//            holder.title.setText(list.get(position).getTitle());
//            int countss = list.get(position).getCount();
//            double dc = countss / 1000;
//
//            holder.count.setText(dc + "k");
//            String imgUri = list.get(position).getPic();
//            x.image().bind(holder.img, imgUri, options);
//
//
//            return convertView;
//        }
//
//        class ViewHolder {
//            ImageView img,redHeartImg;
//            TextView title;
//            TextView count;
//            RelativeLayout relativeLayout;
//        }
//
//    }
//得到数据源之后进行解析

}
