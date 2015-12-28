package com.twogroup.biggift.main.activity;


import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.twogroup.biggift.main.MyBroadcast.MyBroadcast;
import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.gaoshuai.ClassifyFragment;
import com.twogroup.biggift.main.lkp.MeFragment;
import com.twogroup.biggift.main.tianye.HotFragment;
import com.twogroup.biggift.main.weishiyuan.Home_PageFragment;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private long exitTime = 0;
    private RadioGroup radioGroup;
    private RadioButton ra1, ra2, ra3, ra4;
    private Home_PageFragment home_pageFragment;
    private HotFragment hotFragment;
    private ClassifyFragment classifyFragment;
    private MeFragment meFragment;
    private FragmentTransaction tran;
    private FragmentManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new MyBroadcast() , intentFilter);
        initData();
        initView();
    }

    private void initData() {
        //四个fragment
        //首页
        home_pageFragment = new Home_PageFragment();
        //热门
        hotFragment = new HotFragment();
        //分类
        classifyFragment = new ClassifyFragment();
        //我
        meFragment = new MeFragment();
//得到manager

        manager = getSupportFragmentManager();
        tran = manager.beginTransaction();
        tran.replace(R.id.id_content, home_pageFragment, "home_pageFragment");
        tran.commit();
    }

    private void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.id_group);
        radioGroup.setOnCheckedChangeListener(this);
        ra1 = (RadioButton) findViewById(R.id.id_ra1);
        ra2 = (RadioButton) findViewById(R.id.id_ra2);
        ra3 = (RadioButton) findViewById(R.id.id_ra3);
        ra4 = (RadioButton) findViewById(R.id.id_ra4);

    }

    public void clearColor() {

        ra1.setTextColor(Color.BLACK);
        ra2.setTextColor(Color.BLACK);
        ra3.setTextColor(Color.BLACK);
        ra4.setTextColor(Color.BLACK);
    }

    /**
     * 如果需要传递参数 则在这里利用Bundle传递
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        tran = manager.beginTransaction();
        switch (checkedId) {
            case R.id.id_ra1:

                tran.replace(R.id.id_content, home_pageFragment, "home_pageFragment");
                clearColor();
                ra1.setTextColor(Color.RED);
                break;
            case R.id.id_ra2:

                tran.replace(R.id.id_content, hotFragment, "hotFragment");
                clearColor();
                ra2.setTextColor(Color.RED);
                break;
            case R.id.id_ra3:
                tran.replace(R.id.id_content, classifyFragment, "classifyFragment");
                clearColor();
                ra3.setTextColor(Color.RED);
                break;
            case R.id.id_ra4:
                tran.replace(R.id.id_content, meFragment, "meFragemnt");
                clearColor();
                ra4.setTextColor(Color.RED);
                break;
            default:
                break;
        }
        tran.commit();

    }

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

}

