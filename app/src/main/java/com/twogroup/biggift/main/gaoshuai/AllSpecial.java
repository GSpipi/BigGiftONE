package com.twogroup.biggift.main.gaoshuai;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.BaseActivity;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

public class AllSpecial extends BaseActivity {
    private List<SpecialUtils> list;
    private ListView alls_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_special);


        list = (List<SpecialUtils>) getIntent().getSerializableExtra("list");
        Log.e("ccccccc", "--" + list.size());
        Log.e("cccccccc", "--" + list.get(0).getBannerurl());
        initView();

    }

    private void initView() {
        alls_list = ((ListView) findViewById(R.id.alls_listview));
        alls_list.setAdapter(new BaseAdapter() {
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
                ViewHolder viewHolder;
                if (convertView == null) {
                    viewHolder = new ViewHolder();
                    convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.all_special_list_item, null);
                    viewHolder.imageView = ((ImageView) convertView.findViewById(R.id.all_image));
                    viewHolder.subtitle = ((TextView) convertView.findViewById(R.id.alls_item_title));
                    viewHolder.title = ((TextView) convertView.findViewById(R.id.alls_item_content));
                    convertView.setTag(viewHolder);
                }
                viewHolder = (ViewHolder) convertView.getTag();
                ImageOptions options = new ImageOptions.Builder()
                        //.setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
                        .setRadius(DensityUtil.dip2px(10))
                        // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                        .setCrop(true)
                        // 加载中或错误图片的ScaleType
                        //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)

                        .build();

                x.image().bind(viewHolder.imageView, list.get(position).getCoverurl(), options);
                viewHolder.subtitle.setText(list.get(position).getSubtitle());
                viewHolder.title.setText(list.get(position).getTitle());

                return convertView;
            }

            class ViewHolder {
                private ImageView imageView;
                private TextView subtitle;
                private TextView title;

            }
        });

    }
}
