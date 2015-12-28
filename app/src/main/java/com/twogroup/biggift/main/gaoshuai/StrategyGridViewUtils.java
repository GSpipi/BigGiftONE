package com.twogroup.biggift.main.gaoshuai;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twogroup.biggift.main.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoshuai on 2015/12/21.
 */
public class StrategyGridViewUtils {
    private TextView title;
    private GridView gridview;
    private int itemID;
    private Context context;

    public StrategyGridViewUtils(TextView title, GridView gridview, int itemID, Context context) {
        this.title = title;
        this.gridview = gridview;
        this.itemID = itemID;
        this.context = context;
    }

    public void initData() {
        RequestParams params = new RequestParams("http://api.liwushuo.com/v2/channel_groups/all");
        Log.e("aaaaaaaa", "1111111111111");
        x.http().get(params, new Callback.CommonCallback<String>() {
            public ArrayList<Pictures> pictures;
            public JSONArray channel_groups;
            public List<FirstUtils> firsts;

            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject data = JSON.parseObject(result).getJSONObject("data");
                    Log.e("aaaaaaaa", "222222222222");
                    channel_groups = data.getJSONArray("channel_groups");
                    Log.e("aaaaaaaa", "3333333333333---" + channel_groups.size());
                    firsts = JSON.parseArray(channel_groups.toJSONString(), FirstUtils.class);
                    Log.e("aaaaaaaa", "444444--" + firsts.size());

                    JSONObject jsonObject = (JSONObject) channel_groups.get(itemID);
                    JSONArray channels = jsonObject.getJSONArray("channels");
                    Log.e("aaaaaaaa", "5555555555--");
                    List<SecondUtils> seconds = JSON.parseArray(channels.toJSONString(), SecondUtils.class);
                    Log.e("aaaaaaaa", "66666666--" + seconds.size());

                    pictures = new ArrayList<Pictures>();
                    for (int i = 0; i < seconds.size(); i++) {
                        pictures.add(new Pictures(seconds.get(i).getName(), seconds.get(i).getIcon_url()));
                    }

                    gridview.setAdapter(new GridViewAdapter(pictures, context));
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(context,pictures.get(position).getName(),Toast.LENGTH_SHORT).show();
                        }
                    });


                } catch (Exception e) {
                    Log.e("aaaaaaaa", "eeeeeeeeee--");
                    e.printStackTrace();
                }


                title.setText(firsts.get(itemID).getName());

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
    }


    class GridViewAdapter extends BaseAdapter {

        private List<Pictures> picturesList;
        private Context context;

        public GridViewAdapter(List<Pictures> picturesList, Context context) {
            this.picturesList = picturesList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return picturesList.size();
        }

        @Override
        public Object getItem(int position) {
            return picturesList.get(position);
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
                convertView = LayoutInflater.from(context).inflate(R.layout.classify_list_item, null);
                viewHolder.textView = ((TextView) convertView.findViewById(R.id.classify_item_text));
                viewHolder.imageView = ((ImageView) convertView.findViewById(R.id.classify_item_img));
                convertView.setTag(viewHolder);
            }
            viewHolder = ((ViewHolder) convertView.getTag());

            viewHolder.textView.setText(picturesList.get(position).getName());
            x.image().bind(viewHolder.imageView, picturesList.get(position).getImgurl());

            return convertView;
        }
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }

    class Pictures {
        private String name;
        private String imgurl;

        public Pictures() {
        }

        public Pictures(String name, String imgurl) {
            this.name = name;
            this.imgurl = imgurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }
    }

}
