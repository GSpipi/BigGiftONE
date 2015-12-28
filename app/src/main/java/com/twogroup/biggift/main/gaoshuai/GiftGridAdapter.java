package com.twogroup.biggift.main.gaoshuai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersSimpleAdapter;
import com.twogroup.biggift.main.R;

import org.xutils.x;

import java.util.List;

/**
 * Created by gaoshuai on 2015/12/22.
 */
public class GiftGridAdapter extends BaseAdapter implements StickyGridHeadersSimpleAdapter
        , AdapterView.OnItemClickListener{
    private Context context;
    private List<GiftFirstUtils> firstList;
    private List<GiftSecondUtils> list;
    private StickyGridHeadersGridView stickyGridHeaders;
    private RadioGroup giftradiogroup;


    public GiftGridAdapter(Context context, List<GiftFirstUtils> firstList
            , List<GiftSecondUtils> list
            , StickyGridHeadersGridView stickyGridHeaders
            , RadioGroup giftradiogroup) {
        this.context = context;
        this.firstList = firstList;
        this.list = list;
        this.stickyGridHeaders = stickyGridHeaders;
        this.giftradiogroup = giftradiogroup;
        stickyGridHeaders.setOnItemClickListener(this);

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
        VHolder vHolder;
        if (convertView == null) {
            vHolder = new VHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_gift_list_grid_item, null);
            vHolder.imageView = ((ImageView) convertView.findViewById(R.id.gift_grid_item_img));
            vHolder.textView = ((TextView) convertView.findViewById(R.id.gift_grid_item_name));
            convertView.setTag(vHolder);
        }
        vHolder = ((VHolder) convertView.getTag());
        vHolder.textView.setText(list.get(position).getName());
        x.image().bind(vHolder.imageView, list.get(position).getIcon_url());
        return convertView;
    }


    /**
     * 设置标题
     *
     * @param position
     * @return
     */

    @Override
    public long getHeaderId(int position) {
        return list.get(position).getHeadId();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderVHolder headerVHolder;
        if (convertView == null) {
            headerVHolder = new HeaderVHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.classify_gift_list_item, null);
            headerVHolder.headTView = ((TextView) convertView.findViewById(R.id.gift_gridview_title));
            convertView.setTag(headerVHolder);
        }
        headerVHolder = ((HeaderVHolder) convertView.getTag());
        headerVHolder.headTView.setText(firstList.get((list.get(position).getHeadId() - 1)).getName());

        return convertView;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
    }



    class VHolder {
        private ImageView imageView;
        private TextView textView;
    }

    class HeaderVHolder {
        private TextView headTView;
    }
}
