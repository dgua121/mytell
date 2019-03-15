package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Dgua1;

public class DguaEditText extends ViewGroup {
    public Dgua1 b;
    private EditText et;

    public DguaEditText(Context context) {
        super(context);
    }

    public DguaEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.et = new EditText(context);
        this.et.setLines(2);
        this.et.setBackgroundColor(-1);
        setBackgroundResource(R.drawable.dgua4);
        this.et.setHint("请务必保存好密匙！！！");
        this.et.setTextSize((float) 15);
        addView(this.et);
        this.b = new Dgua1(context);
        this.b.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.b.setText("发 送");
        addView(this.b);
    }

    public DguaEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    public void settext(String str) {
        this.et.setText(str);
    }

    public String gettext() {
        return this.et.getText().toString();
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        getChildAt(0).layout(4, 4, (ActivityCommon.SCREENWIDTH * 3) / 4, (ActivityCommon.SCREENHEIGHT / 10) - 4);
        int childCount = getChildCount();
        for (int i5 = 1; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            childAt.getMeasuredWidth();
            childAt.getMeasuredHeight();
            childAt.layout((ActivityCommon.SCREENWIDTH * 3) / 4, 0, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT / 10);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
        getChildAt(0).measure((ActivityCommon.SCREENWIDTH * 3) / 4, ActivityCommon.SCREENHEIGHT / 10);
        getChildAt(1).measure((ActivityCommon.SCREENWIDTH * 1) / 4, ActivityCommon.SCREENHEIGHT / 10);
    }
}
