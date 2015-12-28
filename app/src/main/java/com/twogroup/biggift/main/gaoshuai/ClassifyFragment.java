package com.twogroup.biggift.main.gaoshuai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.twogroup.biggift.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends Fragment {

    private View view;
    private RadioButton button_gift;
    private RadioButton button_strategy;
    private ImageView search;
    private ViewPager classify_viewpager;
    private RadioGroup rediogroup;
    private List<Fragment> fragmentList = new ArrayList<>();

    public ClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_classify, container, false);

        initView();
        initFragmentData();
        initPagerAgapter();

        initSearch();

        classify_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radio = (RadioButton) rediogroup.getChildAt(position);
                radio.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rediogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                int dex = group.indexOfChild(radioButton);
                classify_viewpager.setCurrentItem(dex);
            }

        });
        classify_viewpager.setOffscreenPageLimit(1);// 预加载
        return view;

    }

    private void initSearch() {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initFragmentData() {

        fragmentList.add(new StrategyFragment());
        fragmentList.add(new GiftFragment());
    }

    private void initPagerAgapter() {
        classify_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

    }

    private void initView() {
        rediogroup = ((RadioGroup) view.findViewById(R.id.classify_radiogroup));
        button_gift = ((RadioButton) view.findViewById(R.id.classify_gift));
        button_strategy = ((RadioButton) view.findViewById(R.id.classify_strategy));
        search = ((ImageView) view.findViewById(R.id.classify_search));
        classify_viewpager = ((ViewPager) view.findViewById(R.id.classify_viewpager));

    }
}
