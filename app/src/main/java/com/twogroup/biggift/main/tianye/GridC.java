package com.twogroup.biggift.main.tianye;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 2015/12/17.
 */
public class GridC extends AsyncTask<String, Integer, List<JsonData>> {

    Context context;
    GridView gv;
    public static List<JsonData> list = new ArrayList<JsonData>();

    public GridC(GridView gv, Context context) {
        this.context = context;
        this.gv = gv;
    }

    @Override
    protected List<JsonData> doInBackground(String... strings) {

        String str = strings[0];
        try {
            String s = HttpUtils.getJsonData(str);
            list = GridJson.json(s);
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    protected void onPostExecute(List<JsonData> list) {
        super.onPostExecute(list);
        gv.setAdapter(new HDFAdapter(list, context));
    }
}
