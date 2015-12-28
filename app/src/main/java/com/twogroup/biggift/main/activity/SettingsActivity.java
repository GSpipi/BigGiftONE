package com.twogroup.biggift.main.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.twogroup.biggift.main.R;

import java.io.File;
import java.text.DecimalFormat;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private TextView pubuliu;
    private TextView cache_clear;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView cachenum;
    private String cacheSize;
    private TextView invite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setCachenum();
        initview();


    }

    private void setCachenum() {
        // 计算缓存大小

        long fileSize = 0;
        cacheSize = "0KB";
        File filesDir = getFilesDir();// /data/data/package_name/files
        File cacheDir = getCacheDir();// /data/data/package_name/cache

        fileSize += getDirSize(filesDir);
        fileSize += getDirSize(cacheDir);

// 2.2版本才有将应用缓存转移到sd卡的功能

        if (isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            File externalCacheDir = getExternalCacheDir(this);//"<sdcard>/Android/data/<package_name>/cache/"
            fileSize += getDirSize(externalCacheDir);
        }

        if (fileSize > 0) {
            cacheSize = formatFileSize(fileSize);
        }
    }

    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 判断当前版本是否兼容目标版本的方法
     *
     * @param VersionCode
     * @return
     */
    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }

    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {

        // return context.getExternalCacheDir(); API level 8

        // e.g. "<sdcard>/Android/data/<package_name>/cache/"

        return context.getExternalCacheDir();
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


    /**
     * 清除app缓存
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void clearAppCache() {
        //清除webview缓存
        @SuppressWarnings("deprecation")
//            File file = CacheManager.getCacheFileBaseDir();
                CookieSyncManager cookieSyncMngr =
                CookieSyncManager.createInstance(getApplicationContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
//        cookieManager.removeSessionCookies(new ValueCallback<Boolean>() {
//            @Override
//            public void onReceiveValue(Boolean value) {
//                CookieSyncManager.getInstance().sync();
//            }
//        });
        //先删除WebViewCache目录下的文件
        deleteDatabase("webview.db");
        deleteDatabase("webview.db-shm");
        deleteDatabase("webview.db-wal");
        deleteDatabase("webviewCache.db");
        deleteDatabase("webviewCache.db-shm");
        deleteDatabase("webviewCache.db-wal");
        //清除数据缓存
        clearCacheFolder(getFilesDir(), System.currentTimeMillis());
        clearCacheFolder(getCacheDir(), System.currentTimeMillis());
        //2.2版本才有将应用缓存转移到sd卡的功能
        if (isMethodsCompat(Build.VERSION_CODES.FROYO)) {
            clearCacheFolder(getExternalCacheDir(this), System.currentTimeMillis());
        }

    }



//        在项目中经常会使用到WebView 控件,当加载html 页面时,会在/data/data/package_name目录下生成database与cache 两个文件夹。请求的url 记录是保存在WebViewCache.db,而url 的内容是保存在WebViewCache 文件夹下


    /**
     * 清除app缓存
     *
     * @param activity
     */
    public void clearAppCache(Activity activity) {

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Toast.makeText(SettingsActivity.this, "缓存清除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SettingsActivity.this, "缓存清除失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
        new Thread() {
            public void run() {
                Message msg = new Message();
                try {
                    clearAppCache();
                    msg.what = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.what = -1;
                }
                handler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 清除缓存目录
     *
     * @param dir 目录
     * @param *   @return
     */
    private int clearCacheFolder(File dir, long curTime) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, curTime);
                    }
                    if (child.lastModified() < curTime) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }





    private void initview() {
        pubuliu = ((TextView) findViewById(R.id.pubuliu));
        cache_clear = ((TextView) findViewById(R.id.id_cache_cleaner));
        cachenum = ((TextView) findViewById(R.id.cachenum));
        invite = ((TextView) findViewById(R.id.invite));
        invite.setOnClickListener(this);
        cachenum.setText(cacheSize);
        pubuliu.setOnClickListener(this);
        cache_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pubuliu:
                Intent it = new Intent(getBaseContext(), PinterestActivity.class);
                startActivity(it);
                break;
            case R.id.id_cache_cleaner:
                clearAppCache(SettingsActivity.this);
                cachenum.setText("0B");
                break;
            case  R.id.invite:
                showShare();
                break;
        }
    }


}