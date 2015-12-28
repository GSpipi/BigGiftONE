package com.twogroup.biggift.main.tianye;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class HttpUtils {

    public static String getJsonData(String path) {
        String json = null;

        try {
            //建立一个url对象
            URL url=new URL(path);
            //得到打开的连接对象
            HttpURLConnection conn= ((HttpURLConnection) url.openConnection());
            //设置请求超时与请求方式
            conn.setReadTimeout(5*1000);
            conn.setRequestMethod("GET");
            //从连接中获取一个输入流对象
            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();

            String str=null;
            while((str=br.readLine())!=null){
                stringBuilder.append(str);
            }
            json=stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    public static byte[] getimageview(String path){
        byte[] json = null;

        try {
            //建立一个url对象
            URL url=new URL(path);
            //得到打开的连接对象
            HttpURLConnection conn= ((HttpURLConnection) url.openConnection());
            //设置请求超时与请求方式
            conn.setReadTimeout(5*1000);
            conn.setRequestMethod("GET");
            //从连接中获取一个输入流对象
            InputStream inputStream=conn.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader br=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();


            String str=null;
            while((str=br.readLine())!=null){
                stringBuilder.append(str);
            }
            json=stringBuilder.toString().getBytes();
            Log.e("getimageview: ","json="+json.length);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}

