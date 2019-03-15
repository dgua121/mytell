package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;

public class DguaCtVtell extends ViewGroup {
    Context pc;

    public DguaCtVtell(Context context) {
        super(context);
    }

    public DguaCtVtell(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pc = context;
        addView(new zhanwei(getContext(), "", "", ""));
       // addView(new zhanwei(getContext(), "", "", ""));
    }

    public DguaCtVtell(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }


    public void addView(String str, String str2, String str3) {
        addView(new DguaCtSubVtell(getContext(), str, str2, str3));
    }


    public void removeAll() {
        removeAllViews();
        addView(new zhanwei(getContext(), "", "", ""));
        //addView(new zhanwei(getContext(), "", "", ""));
        requestLayout();
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        View childAt = getChildAt(0);
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        childAt.layout(0, (childCount - 1) * measuredHeight, measuredWidth, childCount * measuredHeight);
       // getChildAt(1).layout(0, 0, measuredWidth, measuredHeight);
        for (int i5 = 1; i5 < childCount; i5++) {
            View childAt2 = getChildAt(i5);
            measuredHeight = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            childAt2.layout(0, (i5 - 1) * measuredHeight2, measuredHeight, measuredHeight2 * i5);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize((ActivityCommon.SCREENHEIGHT * getChildCount()) / 10));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
