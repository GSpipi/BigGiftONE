package com.twogroup.biggift.main.tianye;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.BaseActivity;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class HotItemActivity extends BaseActivity implements View.OnClickListener {

    List<Fragment> list = new ArrayList<Fragment>();

    public static JsonData data = new JsonData();
    private TextView title;
    private TextView price;
    private TextView describe;
    private ImageView image;
    private ViewPager viewpager;
    private TextView imagetext;
    private TextView commenttext;
    private RelativeLayout layoutlike;
    private ImageView heartnormal;
    private ImageView back;
    private TextView taobao;
    private ImageView fenxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_item);
        initView();
        getData();
        initData();
        viewpager();

    }

    private void viewpager() {
        viewpager.setOffscreenPageLimit(1);
        viewpager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    imagetext.setBackgroundColor(getResources().getColor(R.color.colortextbackground));
                    commenttext.setBackgroundColor(Color.WHITE);
                }else if(position ==1){
                    imagetext.setBackgroundColor(Color.WHITE);
                    commenttext.setBackgroundColor(getResources().getColor(R.color.colortextbackground));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        Fragment imageFragment = new HotItemImageFragment();
        list.add(imageFragment);
        Fragment commentFragment = new HotItemCommentFragment();
        list.add(commentFragment);
        x.image().bind(image,data.getIv());
        title.setText(data.getName());
        price.setText(data.getPrice());
        describe.setText(data.getDescribe());
    }

    private void initView() {
        image = ((ImageView) findViewById(R.id.item_imageView));
        title = ((TextView) findViewById(R.id.item_title));
        price = ((TextView) findViewById(R.id.item_price));
        describe = ((TextView) findViewById(R.id.item_describe));
        imagetext = ((TextView) findViewById(R.id.hot_item_imagetextview));
        fenxiang = ((ImageView) findViewById(R.id.fenxiang));
        fenxiang.setOnClickListener(this);
        imagetext.setOnClickListener(this);
        commenttext = ((TextView) findViewById(R.id.hot_item_commenttextview));
        commenttext.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.item_viewPager);
        layoutlike = ((RelativeLayout) findViewById(R.id.layout_like));
        layoutlike.setOnClickListener(this);
        back = ((ImageView) findViewById(R.id.back));
        back.setOnClickListener(this);
        taobao = ((TextView) findViewById(R.id.taobao));
        taobao.setOnClickListener(this);

    }

    private void getData() {
        int i= getIntent().getIntExtra("1",1);
        List<JsonData> list = GridC.list;
        data = list.get(i);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.hot_item_imagetextview:
                imagetext.setBackgroundColor(getResources().getColor(R.color.colortextbackground));
                commenttext.setBackgroundColor(Color.WHITE);
                viewpager.setCurrentItem(0);
                break;
            case R.id.hot_item_commenttextview:
                imagetext.setBackgroundColor(Color.WHITE);
                commenttext.setBackgroundColor(getResources().getColor(R.color.colortextbackground));
                viewpager.setCurrentItem(1);
                break;
            case R.id.back:
                finish();
//                onBackPressed();相当于点击返回键
                break;
            case R.id.taobao:
                Intent intent = new Intent(HotItemActivity.this,TaobaoActivity.class);
                Log.e("onClick: ","=======>一路过"+data.getTaobao() );
                intent.putExtra("1",data.getTaobao());
                startActivity(intent);
                break;
            case  R.id.fenxiang:

                ShareSDK.initSDK(this);
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
                oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(data.getTaobao());
                // text是分享文本，所有平台都需要这个字段
                oks.setText(data.getName());
                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//                oks.setImagePath("/sdcard/bigGift/tuxiang.jpg");//确保SDcard下面存在此张图片
                //网络图片的url：所有平台
                oks.setImageUrl(data.getIv());//网络图片rul
                // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
                // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
                oks.show(this);

                break;
        }

    }

//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}
