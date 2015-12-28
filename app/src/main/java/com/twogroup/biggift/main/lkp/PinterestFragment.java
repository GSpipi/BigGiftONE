package com.twogroup.biggift.main.lkp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.twogroup.biggift.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PinterestFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {


    private View view;
    private RadioGroup radiogroup;
    private ViewPager viewPager;
    private List<Fragment> framentData = new ArrayList<>();

    private static final String path1 = "http://service.picasso.adesk.com/v1/wallpaper/category/4e4d610cdf714d2966000000/wallpaper?order=new&adult=false&first=1&limit=26";
//    private static final String path2 = "http://service.picasso.adesk.com/v1/wallpaper/category/4e4d610cdf714d2966000000/album?order=new&adult=false&first=1&limit=20";
    private static final String path2 = "http://service.picasso.adesk.com/v1/wallpaper/category/4e4d610cdf714d2966000000/wallpaper?order=hot&adult=false&first=1&limit=26";
    private static final String path3 = "http://service.picasso.adesk.com/v1/wallpaper/category/4e4d610cdf714d2966000000/wallpaper?order=new&adult=false&first=0&skip=21&limit=26";
    private static final String path5 = "http://service.picasso.adesk.com/v1/wallpaper/album/55b5dd6c69401b78554ceec8/wallpaper?order=2&adult=true&first=2&limit=26";
    private static final String path4 = "http://service.picasso.adesk.com/v1/wallpaper/wallpaper?order=hot&adult=false&first=1&limit=26";
    private HorizontalScrollView hsv;

    public PinterestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pinterest, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();

    }

    private void initData() {
        PictureFragment picture = new PictureFragment();
        Bundle bundle = new Bundle();
        for (int i = 0; i < 5; i++) {
            picture = new PictureFragment();
            switch (i) {
                case 0:
                    bundle.putString("path", path1);
                    break;
                case 1:
                    bundle.putString("path", path2);
                    break;
                case 2:
                    bundle.putString("path", path3);
                    break;
                case 3:
                    bundle.putString("path", path4);
                    break;
                case 4:
                    bundle.putString("path", path5);
                    break;
                default:
                    break;
            }
            picture.setArguments(bundle);
            framentData.add(picture);
        }

    }

    private void initView() {
        radiogroup = (RadioGroup) view.findViewById(R.id.id_group);
        viewPager = ((ViewPager) view.findViewById(R.id.id_viewPager));
        hsv = (HorizontalScrollView) view.findViewById(R.id.id_scrollview);
        radiogroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(this);
        FragmentPagerAdapter fra = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return framentData.size();
            }

            @Override
            public Fragment getItem(int position) {
                return framentData.get(position);
            }
        };
        viewPager.setAdapter(fra);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) view.findViewById(checkedId);
        int index = group.indexOfChild(rb);
        viewPager.setCurrentItem(index);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton rb = (RadioButton) radiogroup.getChildAt(position);
        rb.setChecked(true);
        int left = rb.getLeft();
//		 hsv.scrollTo(left, 0);
        int width = rb.getMeasuredWidth();
        DisplayMetrics dMetrics = getResources().getDisplayMetrics();
        int widthPixels = dMetrics.widthPixels;
        int len = left + width / 2 - widthPixels / 2;
        hsv.scrollTo(len, 0);// 滑动ScroollView

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
