package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.view.Dgua;

public class DguaViewBackground extends ViewGroup {
    public Dgua mydgua1_1;
    public Dgua mydgua1_2;
    public Dgua mydgua1_3;

    public DguaViewBackground(Context context) {
        super(context);
    }

    public DguaViewBackground(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua3);
        this.mydgua1_1 = new Dgua(context);
        this.mydgua1_1.setText("A");
        addView(this.mydgua1_1);
        this.mydgua1_2 = new Dgua(context);
        this.mydgua1_2.setText("B");
        addView(this.mydgua1_2);
        this.mydgua1_3 = new Dgua(context);
        this.mydgua1_3.setText("C");
        addView(this.mydgua1_3);
    }

    public DguaViewBackground(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            childAt.layout(i5 * measuredWidth, 0, measuredWidth + (i5 * measuredWidth), childAt.getMeasuredHeight());
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
