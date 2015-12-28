package com.twogroup.biggift.main.tianye;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.twogroup.biggift.main.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements GridView.OnItemClickListener{

    private View view;
    private GridView gridView;
    private String path = "http://api.liwushuo.com/v2/items?limit=20&gender=2&generation=4&offset=0";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot_door, container, false);
        init();
        return view;
    }

    private void init() {
        gridView = ((GridView) view.findViewById(R.id.gridView));
        isNetworkAvailable(getContext());
        new GridC(gridView,getActivity()).execute(path);
        gridView.setOnItemClickListener(this);

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            Toast.makeText(context,"没有网络！",Toast.LENGTH_SHORT).show();
        } else {
              //如果仅仅是用来判断网络连接
              //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(),HotItemActivity.class);
        intent.putExtra("1",i);
        startActivity(intent);
    }
}
