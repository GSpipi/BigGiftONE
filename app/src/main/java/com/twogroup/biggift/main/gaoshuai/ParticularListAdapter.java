package com.twogroup.biggift.main.gaoshuai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;

import org.xutils.x;

import java.util.List;

/**
 * Created by gaoshuai on 2015/12/28.
 */
public class ParticularListAdapter extends BaseAdapter {

    private Context context;
    private List<ParticyalUtils> list;

    public ParticularListAdapter(Context context, List<ParticyalUtils> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.particular_list_item, null);
            holder.imageView = ((ImageView) convertView.findViewById(R.id.parent_item_img));
            holder.textView = ((TextView) convertView.findViewById(R.id.parent_item_shortname));
            convertView.setTag(holder);
        }
        holder = ((Holder) convertView.getTag());
        holder.textView.setText(list.get(position).getTitle());
        x.image().bind(holder.imageView,list.get(position).getCover_image_url());
        return convertView;
    }

    class Holder {
        private ImageView imageView;
        private TextView textView;
    }
}
