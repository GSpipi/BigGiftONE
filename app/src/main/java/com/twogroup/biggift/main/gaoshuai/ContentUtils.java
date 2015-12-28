package com.twogroup.biggift.main.gaoshuai;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.BaseActivity;

public class ContentUtils extends BaseActivity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_utils);
        String path = getIntent().getStringExtra("path");
        web = ((WebView) findViewById(R.id.content_web));
        Log.e("mmmmmmmm","---"+path);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(path);
    }
}
