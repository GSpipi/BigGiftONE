package com.twogroup.biggift.main.MyBroadcast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;


/**
 * Created by LKP on 2015/12/23.
 */
public class MyBroadcast extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo gprs = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        Toast.makeText(context,"网络状态改变",Toast.LENGTH_SHORT).show();
        if (!gprs.isConnected() && !wifi.isConnected()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("温馨提示");
            builder.setMessage("当前没有网络");
            builder.setPositiveButton("打开网络", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                    context.startActivity(intent);
                }
            });
            builder.setNeutralButton("不连接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

//            builder.setNegativeButton("不连接", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            }).create().show();
            builder.create().show();



    }
    }
}
