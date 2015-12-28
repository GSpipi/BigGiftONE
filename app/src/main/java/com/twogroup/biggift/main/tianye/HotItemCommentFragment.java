package com.twogroup.biggift.main.tianye;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twogroup.biggift.main.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotItemCommentFragment extends Fragment {


    public HotItemCommentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_item_comment, container, false);
    }

}
