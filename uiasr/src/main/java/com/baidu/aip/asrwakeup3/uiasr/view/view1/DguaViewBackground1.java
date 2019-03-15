package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.view.Dgua11;

public class DguaViewBackground1 extends ViewGroup {
    public Dgua11 mydgua1_1;
    public Dgua11 mydgua1_2;
    public Dgua11 mydgua1_3;
    public Dgua11 mydgua1_4;
    public TextView tv1;

    public DguaViewBackground1(Context context) {
        super(context);
    }

    public DguaViewBackground1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua3);
        this.tv1 = new TextView(context);
        this.tv1.setBackgroundResource(R.drawable.dgua4);
        addView(this.tv1);
    }

    public DguaViewBackground1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    public void setViewColor() {
        this.mydgua1_1.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mydgua1_2.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mydgua1_3.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mydgua1_4.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setViewColor(int i) {
        switch (i) {
            case 1:
                this.mydgua1_1.tva.setTextColor(-16776961);
                this.mydgua1_2.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_3.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_4.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_1.setImage(2);
                this.mydgua1_2.setImage(1);
                this.mydgua1_3.setImage(1);
                this.mydgua1_4.setImage(1);
                return;
            case 2:
                this.mydgua1_1.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_2.tva.setTextColor(-16776961);
                this.mydgua1_3.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_4.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_1.setImage(1);
                this.mydgua1_2.setImage(2);
                this.mydgua1_3.setImage(1);
                this.mydgua1_4.setImage(1);
                return;
            case 3:
                this.mydgua1_1.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_2.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_3.tva.setTextColor(-16776961);
                this.mydgua1_4.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_1.setImage(1);
                this.mydgua1_2.setImage(1);
                this.mydgua1_3.setImage(2);
                this.mydgua1_4.setImage(1);
                return;
            case 4:
                this.mydgua1_1.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_2.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_3.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.mydgua1_4.tva.setTextColor(-16776961);
                this.mydgua1_1.setImage(1);
                this.mydgua1_2.setImage(1);
                this.mydgua1_3.setImage(1);
                this.mydgua1_4.setImage(2);
                return;
            default:
                return;
        }
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        getChildAt(0).layout(0, 0, ActivityCommon.SCREENWIDTH, 3);
        int childCount = getChildCount();
        for (int i5 = 1; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            childAt.layout((i5 - 1) * measuredWidth, 3, measuredWidth + ((i5 - 1) * measuredWidth), childAt.getMeasuredHeight() + 3);
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
