package com.twogroup.biggift.main.tianye;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.twogroup.biggift.main.R;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotItemImageFragment extends Fragment {

    View view;
    private ListView listView;
    JsonData data = new JsonData();
    List<String> list= new ArrayList<String>();
    Context context = getContext();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_item_image, container, false);
        getdata();
        initdata();
        return view;
    }

    private void getdata() {
        data = HotItemActivity.data;
        list = data.getList();
    }

    private void initdata() {
        listView = ((ListView) view.findViewById(R.id.image_listview));
        listView.setAdapter(new MyImageAdapter());
    }

    private class MyImageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
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
            ImageView image = null;
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.images,null);
                image = ((ImageView) convertView.findViewById(R.id.imageView_image));
                convertView.setTag(image);
            }
            image = (ImageView) convertView.getTag();
            x.image().bind(image,list.get(position));
            return convertView;
        }
    }

}
