package com.twogroup.biggift.main.lkp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.activity.LoginActivity;
import com.twogroup.biggift.main.activity.MyinformationActivity;
import com.twogroup.biggift.main.activity.SettingsActivity;
import com.twogroup.biggift.main.activity.TwoCodeActivity;
import com.twogroup.biggift.main.utils.ToRoundBitmap;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private ImageView id_userimage, id_twocode, id_setting;
    private TextView id_user, id_integral;
    private View view;
    private boolean islogin;
    private String morenming;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences1;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();

        String morenming1 = sharedPreferences1.getString("morenming", morenming);

        boolean isok = sharedPreferences.getBoolean("islogin", false);
        if (isok) {
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bigGift/tuxiang.jpg");
            //给图片切成圆角 传入bitmap返回bitmap
            ToRoundBitmap toRoundBitmap1 = new ToRoundBitmap();
            Bitmap bitmap1 = toRoundBitmap1.toRoundBitmap(bitmap);
            id_userimage.setImageBitmap(bitmap1);
            id_user.setText(morenming1);
            id_userimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MyinformationActivity.class);
                    startActivity(intent);
                }
            });
        } else {

            id_userimage.setImageResource(R.mipmap.ig_profile_photo_default);
            id_user.setText("未命名");
            id_userimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void initView() {
        id_userimage = (ImageView) view.findViewById(R.id.id_userimage);
        id_twocode = (ImageView) view.findViewById(R.id.id_twocode);
        id_setting = (ImageView) view.findViewById(R.id.id_setting);
        id_user = (TextView) view.findViewById(R.id.id_user);
        id_integral = (TextView) view.findViewById(R.id.id_integral);
        id_twocode.setOnClickListener(this);
        id_setting.setOnClickListener(this);
        id_user.setOnClickListener(this);
//        id_integral.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
        sharedPreferences1 = getActivity().getSharedPreferences("moren", getActivity().MODE_PRIVATE);
        String morenming1 = sharedPreferences1.getString("morenming", morenming);
        boolean isok = sharedPreferences.getBoolean("islogin", false);
        if (isok) {
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bigGift/tuxiang.jpg");
            //给图片切成圆角 传入bitmap返回bitmap
            ToRoundBitmap toRoundBitmap1 = new ToRoundBitmap();
            Bitmap bitmap1 = toRoundBitmap1.toRoundBitmap(bitmap);
            id_userimage.setImageBitmap(bitmap1);
            id_user.setText(morenming1);
            id_userimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MyinformationActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            id_userimage.setImageResource(R.mipmap.ig_profile_photo_default);
            id_user.setText("未命名");
            id_userimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.id_setting:
                intent.setClass(getActivity(), SettingsActivity.class);
                break;
            case R.id.id_twocode:
                intent.setClass(getActivity(), TwoCodeActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
