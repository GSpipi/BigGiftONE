package com.twogroup.biggift.main.tianye;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by M on 2015/12/17.
 */
public class GridJson {

    public static List<JsonData> json(String url) {
        List<JsonData> list = new ArrayList<JsonData>();
        List<String> mlist = null;
        String str = url.substring(url.indexOf("{"));
        try {
            JSONObject js1 = new JSONObject(str);
            JSONObject js2 = js1.getJSONObject("data");
            JSONArray js3 = js2.getJSONArray("items");
            JsonData data;
            for (int i = 0; i < js3.length() ; i++) {
                mlist=new ArrayList<String>();

                JSONObject js4 = js3.getJSONObject(i);
                JSONObject js5 = js4.getJSONObject("data");
                data = new JsonData();
                data.setIv(js5.getString("cover_image_url"));
                data.setName(js5.getString("name"));
                data.setPrice(js5.getString("price"));
                data.setCount(js5.getString("favorites_count"));
                data.setDescribe(js5.getString("description"));
                data.setTaobao(js5.getString("purchase_url"));
                JSONArray js6 = js5.getJSONArray("image_urls");
                for (int j = 0; j <js6.length() ; j++) {
                    mlist.add(js6.getString(j));
                }
                data.setList(mlist);
                Log.e("json: ",""+mlist.size() );
                list.add(data);
                mlist=null;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
