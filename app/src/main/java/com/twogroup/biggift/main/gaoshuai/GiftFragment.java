package com.twogroup.biggift.main.gaoshuai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.twogroup.biggift.main.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends Fragment {


    private View view;
    private RelativeLayout gift_top;
    private RadioGroup giftradiogroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11, rb12, rb13, rb14, rb15, rb16, rb17;
    private StickyGridHeadersGridView stickyGridHeaders;
    private ScrollView scrollView;

    public GiftFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gift, container, false);

        initView();
        initData();

        return view;
    }

    private void initData() {
        RequestParams params = new RequestParams("http://api.liwushuo.com/v2/item_categories/tree");
        Log.e("GGGGGGGGGG", "111111111");
        x.http().get(params, new Callback.CommonCallback<String>() {

            private List<GiftFirstUtils> firstList;
            public JSONArray categories;
            private List<GiftSecondUtils> gridList = new ArrayList<GiftSecondUtils>();

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject data = JSON.parseObject(result).getJSONObject("data");
                    categories = data.getJSONArray("categories");
                    firstList = JSON.parseArray(categories.toJSONString(), GiftFirstUtils.class);

                    for (int i = 0; i < categories.size(); i++) {
                        JSONObject jsonObject = categories.getJSONObject(i);
                        JSONArray subcategories = jsonObject.getJSONArray("subcategories");
                        List<GiftSecondUtils> giftSecondUtilses = JSON.parseArray(subcategories.toJSONString(), GiftSecondUtils.class);

                        for (int j = 0; j < giftSecondUtilses.size(); j++) {
                            GiftSecondUtils giftSecondUtils = giftSecondUtilses.get(j);
                            gridList.add(giftSecondUtils);
                        }
                    }
                    Log.e("GGGGGGGGGG", "22222---" + firstList.size());


                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (int a = 0; a < firstList.size(); a++) {
                    firstList.get(a).setHeadId(a + 1);
                    for (int b = 0; b < gridList.size(); b++) {
                        if (firstList.get(a).getId() == gridList.get(b).getParent_id()) {
                            gridList.get(b).setHeadId(a + 1);
                        }
                    }
                }

                Log.d("sssssssssss", "onSuccess: "+gridList.get(0).getHeadId());

                stickyGridHeaders.setAdapter(new GiftGridAdapter(getContext(), firstList, gridList,stickyGridHeaders,giftradiogroup));


                setName(firstList);
            }


            private void setName(List<GiftFirstUtils> firstList) {
                rb1.setText(firstList.get(0).getName());
                rb2.setText(firstList.get(1).getName());
                rb3.setText(firstList.get(2).getName());
                rb4.setText(firstList.get(3).getName());
                rb5.setText(firstList.get(4).getName());
                rb6.setText(firstList.get(5).getName());
                rb7.setText(firstList.get(6).getName());
                rb8.setText(firstList.get(7).getName());
                rb9.setText(firstList.get(8).getName());
                rb10.setText(firstList.get(9).getName());
                rb11.setText(firstList.get(10).getName());
                rb12.setText(firstList.get(11).getName());
                rb13.setText(firstList.get(12).getName());
                rb14.setText(firstList.get(13).getName());
                rb15.setText(firstList.get(14).getName());
                rb16.setText(firstList.get(15).getName());
                rb17.setText(firstList.get(16).getName());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void initView() {
        gift_top = ((RelativeLayout) view.findViewById(R.id.gift_top));
        giftradiogroup = ((RadioGroup) view.findViewById(R.id.gift_radiogroup));
        scrollView = ((ScrollView) view.findViewById(R.id.gift_scrollView));
        rb1 = ((RadioButton) view.findViewById(R.id.gift_radiobutton1));
        rb2 = ((RadioButton) view.findViewById(R.id.gift_radiobutton2));
        rb3 = ((RadioButton) view.findViewById(R.id.gift_radiobutton3));
        rb4 = ((RadioButton) view.findViewById(R.id.gift_radiobutton4));
        rb5 = ((RadioButton) view.findViewById(R.id.gift_radiobutton5));
        rb6 = ((RadioButton) view.findViewById(R.id.gift_radiobutton6));
        rb7 = ((RadioButton) view.findViewById(R.id.gift_radiobutton7));
        rb8 = ((RadioButton) view.findViewById(R.id.gift_radiobutton8));
        rb9 = ((RadioButton) view.findViewById(R.id.gift_radiobutton9));
        rb10 = ((RadioButton) view.findViewById(R.id.gift_radiobutton10));
        rb11 = ((RadioButton) view.findViewById(R.id.gift_radiobutton11));
        rb12 = ((RadioButton) view.findViewById(R.id.gift_radiobutton12));
        rb13 = ((RadioButton) view.findViewById(R.id.gift_radiobutton13));
        rb14 = ((RadioButton) view.findViewById(R.id.gift_radiobutton14));
        rb15 = ((RadioButton) view.findViewById(R.id.gift_radiobutton15));
        rb16 = ((RadioButton) view.findViewById(R.id.gift_radiobutton16));
        rb17 = ((RadioButton) view.findViewById(R.id.gift_radiobutton17));
        stickyGridHeaders = ((StickyGridHeadersGridView) view.findViewById(R.id.classify_gift_headGridView));
    }

    class HeadItem {
        private String title;
        private int headId;
    }

    class GridItem {
        private String imgUrl;
        private String name;
        private String headName;
        private int headId;


    }

}

