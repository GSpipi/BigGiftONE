package com.twogroup.biggift.main.gaoshuai;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by gaoshuai on 2015/12/22.
 */
public class GridViewLayout extends GridView {


    public GridViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
