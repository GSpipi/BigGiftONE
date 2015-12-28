package com.twogroup.biggift.main.lkp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.entity.Product;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKP on 2015/12/25.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Product> products=new ArrayList<>();


    public MyAdapter() {
    }

    public MyAdapter(List<Product> products) {

        this.products = products;
        Log.i("----2--size",products.size()+"");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem, null);
        return new ViewHolder(v);

    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageOptions options = new ImageOptions.Builder().setFailureDrawableId(R.mipmap.black1).setLoadingDrawableId(R.mipmap.ic_launch).build();
//        x.image().bind(holder.imageView, products.get(position).getPreview(),options);
        x.image().bind(holder.imageView, products.get(position).getImg(), options);


//        holder.textView.setText(products.get(position).getTitle());


    }


    @Override
    public int getItemCount() {
        Log.i("----2--", products.size() + "");
        return products.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //        TextView textView;
        ImageView imageView;

        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
//            textView = (TextView) itemView.findViewById(R.id.masonry_item_title);
            imageView = (ImageView) itemView.findViewById(R.id.masonry_item_img);
        }
    }
}
