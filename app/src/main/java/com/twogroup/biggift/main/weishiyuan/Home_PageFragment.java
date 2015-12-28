package com.twogroup.biggift.main.weishiyuan;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.weishiyuan.Fragment.Fragment1;
import com.twogroup.biggift.main.weishiyuan.Fragmentools.HomeCommonFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_PageFragment extends Fragment {

    private List<String> titlelist = new ArrayList<String>();

    private List<Fragment> fragmentList = new ArrayList<Fragment>();


    private String path1="http://api.liwushuo.com/v2/channels/118/items?limit=20&offset=0&gender=1&generation=1";
    private String path2="http://api.liwushuo.com/v2/channels/121/items?limit=20&offset=0&gender=1&generation=1";
    private String path3="http://api.liwushuo.com/v2/channels/111/items?limit=20&offset=0&gender=1&generation=1";
    private String path4="http://api.liwushuo.com/v2/channels/129/items?limit=20&offset=0&gender=1&generation=1";
    private String path5="http://api.liwushuo.com/v2/channels/30/items?limit=20&offset=0&gender=1&generation=1";
    private String path6="http://api.liwushuo.com/v2/channels/120/items?limit=20&offset=0&gender=1&generation=1";
    private String path7="http://api.liwushuo.com/v2/channels/125/items?limit=20&offset=0&gender=1&generation=1";



    ViewPager vp;
    Fragment1 f1;
    HomeCommonFragment f2,f3,f4,f5,f6,f7,f8;
    public Home_PageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_home__page, container, false);
        initview(view);
        return view;
    }


    private void initview(View view) {

        TabLayout tabl = (TabLayout) view.findViewById(R.id.HpTab);


        vp = (ViewPager) view.findViewById(R.id.HP_Vp);
vp.setOffscreenPageLimit(4);
        //给标题
        titlelist.add("精彩");
        titlelist.add("美食");
        titlelist.add("数码");
        titlelist.add("礼物");
        titlelist.add("海淘");
        titlelist.add("生日");
        titlelist.add("涨姿势");
        titlelist.add("创意生活");
        //初始化Fragment
        f1=new Fragment1();
        f2=new HomeCommonFragment(path1);
        f3=new HomeCommonFragment(path2);
        f4=new HomeCommonFragment(path3);
        f5=new HomeCommonFragment(path4);
        f6=new HomeCommonFragment(path5);
        f7=new HomeCommonFragment(path6);
        f8=new HomeCommonFragment(path7);
        //向Fragment里面添加数据
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        fragmentList.add(f4);
        fragmentList.add(f5);
        fragmentList.add(f6);
        fragmentList.add(f7);
        fragmentList.add(f8);


//        vp.setAdapter(new MyItemAdapter(getFragmentManager(),titlelist));
        tabl.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabl.addTab(tabl.newTab().setText(titlelist.get(0)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(1)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(2)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(3)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(4)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(5)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(6)));
        tabl.addTab(tabl.newTab().setText(titlelist.get(7)));

        MyItemAdapter MIadapter = new MyItemAdapter(getChildFragmentManager(),fragmentList);
        vp.setAdapter(MIadapter);//设置viewpage适配器
        tabl.setupWithViewPager(vp);
        tabl.setTabsFromPagerAdapter(MIadapter);
    }

    class MyItemAdapter extends FragmentPagerAdapter {

        private List<Fragment> mViewList;
        public MyItemAdapter(FragmentManager fm,List<Fragment> fragmentList) {
            super(fm);

            this.mViewList = fragmentList;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titlelist.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mViewList.get(position);
        }

        @Override
        public int getCount() {
            return 8;
        }






    }


}
