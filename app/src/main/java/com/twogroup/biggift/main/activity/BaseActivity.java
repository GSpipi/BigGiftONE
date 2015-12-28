package com.twogroup.biggift.main.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.twogroup.biggift.main.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class BaseActivity extends AppCompatActivity {
    public ActionBar ab;
    public TextView main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initActionBar();
    }

    private void initActionBar() {
        ab = getSupportActionBar();
    ab.hide();

    }
    public void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我在玩BigGift，你也赶紧来吧，不要说我骗你哦，让你看看图");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/bigGift/tuxiang.jpg");//确保SDcard下面存在此张图片
        //网络图片的url：所有平台
        oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");//网络图片rul
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
    }
//

//        ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);// 给左上角图标的左边加上一个返回的图标
////		ab.setDisplayShowHomeEnabled(true);//使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标
//
////        ab.setLogo(R.mipmap.ic_launcher);//可换
//        ab.setDisplayShowHomeEnabled(true);
//        ab.setDisplayUseLogoEnabled(true);
//        ab.hide();//默认隐藏
//
//        ab.setDisplayShowCustomEnabled(true);//自定义显示
//        //若需自定义则自己定
//
//                ab.setCustomView(R.layout.actionbar_item);
//                View title_layout=ab.getCustomView();
//                main_title = (TextView) title_layout.findViewById(R.id.main_actionbar);
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle presses on the action bar items
//        switch (item.getItemId()) {
//
//            case android.R.id.home:
//                finish();
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    public void setTitleText(String string) {
//        main_title.setText(string);
//    }
//
//    //隐藏actionbar
//    public void showActionBar() {
//        ab.show();
//    }
//
//    public void showreturn() {
//        ab.setDisplayHomeAsUpEnabled(true);
//
//    }
}

