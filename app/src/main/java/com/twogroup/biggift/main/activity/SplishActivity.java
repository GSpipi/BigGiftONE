package com.twogroup.biggift.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.twogroup.biggift.main.R;

public class SplishActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splish);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent mainIntent = new Intent(SplishActivity.this, MainActivity.class);
                SplishActivity.this.startActivity(mainIntent);
                SplishActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

}


