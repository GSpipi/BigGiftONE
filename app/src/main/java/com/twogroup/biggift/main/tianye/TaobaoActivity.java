package com.twogroup.biggift.main.tianye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.BaseActivity;

public class TaobaoActivity extends BaseActivity implements View.OnClickListener{

    WebView taobao;
    public static JsonData jsondata = new JsonData();
    private ImageView back;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taobao);

        path = getIntent().getStringExtra("1");
        Log.e("onClick: ","=======>一路过"+ path);
        if(path == null){
            Toast.makeText(getBaseContext(),"没有网络！",Toast.LENGTH_SHORT);
        }
        taobao = (WebView)findViewById(R.id.id_taobao);
        taobao.setWebViewClient(new WebViewClient());
        taobao.loadUrl(path);

        back = ((ImageView) findViewById(R.id.back));
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.back:
                onBackPressed();
                break;
        }
    }
}
