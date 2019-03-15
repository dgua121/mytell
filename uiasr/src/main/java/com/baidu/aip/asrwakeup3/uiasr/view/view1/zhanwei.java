package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubV1;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubV2;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubV3;


public class zhanwei extends ViewGroup {
    public TextView ds1;
    public DguaCtSubV2 ds2;
    public DguaCtSubV3 ds3;

    public zhanwei(Context context, String str, String str2, String str3) {
        super(context);
        this.ds1 = new TextView(context);
        this.ds2 = new DguaCtSubV2(context);
        this.ds3 = new DguaCtSubV3(context);
        this.ds1.setText(str);
        this.ds2.setText(str2);
        this.ds3.setText(str3);
        addView(this.ds1);
        addView(this.ds2);
        addView(this.ds3);
    }

    public zhanwei(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public zhanwei(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(i5, 0, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight());
            i5 += childAt.getMeasuredWidth();
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
