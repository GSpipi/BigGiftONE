package com.twogroup.biggift.main.tianye;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by M on 2015/12/18.
 */
public class GetIv extends AsyncTask<String,Integer,byte[]> {


    ImageView iv;

    public GetIv(ImageView iv){
        this.iv = iv;
    }

    @Override
    protected byte[] doInBackground(String... strings) {
        byte[] b = null;
        String str = strings[0];
        Log.e("doInBackground: ", "-------url="+str);
        b = HttpUtils.getimageview(str);
        Log.e("doInBackground: ","--------得到的图片byte数组"+b.length);
        return b;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        Log.e("onPostExecute: ","----bytes的长度"+bytes.length );
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        Log.e("onPostExecute: ","--------bitmap"+bitmap );
        iv.setImageBitmap(bitmap);
    }
}
