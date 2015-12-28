package com.twogroup.biggift.main.weishiyuan.Fragmentools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.bean.BaseBean;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2015/12/20.
 */
public class MyAdapter extends BaseAdapter{
    ImageOptions options;
    //构造函数
    private List<BaseBean> list;
    Activity activity;
    public MyAdapter(List<BaseBean> list, Activity activity) {
    this.list=list;
    this.activity=activity;
//图片参数设置
        options = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launch)
                .setRadius(20)
                .setFailureDrawableId(R.mipmap.ic_launcher)
                .setConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.hp_listview_item, null);
            holder = new ViewHolder();
            holder.img = ((ImageView) convertView.findViewById(R.id.hp_lv_imageview1));
            holder.title = ((TextView) convertView.findViewById(R.id.title));
            holder.count = ((TextView) convertView.findViewById(R.id.item_count));
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.title.setText(list.get(position).getTitle());
        int countss = list.get(position).getCount();
        double dc = countss / 1000;



        holder.count.setText(dc + "k");
        String imgUri = list.get(position).getPic();
        x.image().bind(holder.img, imgUri, options);
        return convertView;
    }

   public void clear() {
       list.clear();
       notifyDataSetChanged();
   }
    public void add(Collection<BaseBean> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }
    class ViewHolder {
        ImageView img;
        TextView title;
        TextView count;
    }

}


