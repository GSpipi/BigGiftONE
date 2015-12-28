package com.twogroup.biggift.main;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.twogroup.biggift.main.MyBroadcast.MyBroadcast;

import org.xutils.x;


public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
