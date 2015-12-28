package com.twogroup.biggift.main.tianye;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;

import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M on 2015/12/17.
 */
public class HDFAdapter extends BaseAdapter {

    List<JsonData> list = new ArrayList<JsonData>();
    Context context;
    //图片参数设置
    ImageOptions options = new ImageOptions.Builder()
            .setLoadingDrawableId(R.mipmap.ic_launch)
            .setRadius(20)
            .setFailureDrawableId(R.mipmap.ic_launcher)
            .setConfig(Bitmap.Config.RGB_565)
            .build();

    public HDFAdapter(List<JsonData> list, Context context){
        this.list = list;
        this.context = context;

    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(convertView == null){
            GridHold hold = new GridHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.hotdoor_item,null);

            hold.iv = (ImageView) convertView.findViewById(R.id.imageView);
            hold.price = (TextView) convertView.findViewById(R.id.tvPrice);
            hold.name = (TextView) convertView.findViewById(R.id.tvTitle);
            hold.count = (TextView) convertView.findViewById(R.id.tvAttention);
            convertView.setTag(hold);
        }
        GridHold hold = (GridHold) convertView.getTag();
        JsonData data = list.get(position);

        hold.name.setText(data.getName());
        hold.price.setText(data.getPrice());
        hold.count.setText(data.getCount());
        String s = data.getIv();
        x.image().bind(hold.iv,s,options);

        return convertView;
    }
}
