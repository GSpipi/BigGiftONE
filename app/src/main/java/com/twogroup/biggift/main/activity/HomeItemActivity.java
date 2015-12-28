package com.twogroup.biggift.main.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.bean.BaseBean;

import org.xutils.x;

public class HomeItemActivity extends BaseActivity {
  private String url;
    private String pic;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_item);
        BaseBean tiem = (BaseBean) this.getIntent().getExtras().get("item");
                   url=tiem.getStrategy_for_details();
                 pic = tiem.getPic();
              title = tiem.getTitle();
        initview();
    }

    private void initview() {
        ImageView imageView= ((ImageView) findViewById(R.id.home_item_image));
        TextView textView=((TextView) findViewById(R.id.home_item_text));
        x.image().bind(imageView,pic);
                   textView.setText(title);
        WebView web = (WebView) findViewById(R.id.home_webview);
         // 不让其跳转到浏览器
            web.setWebViewClient(new WebViewClient());
            web.loadUrl(url);


    }
}
