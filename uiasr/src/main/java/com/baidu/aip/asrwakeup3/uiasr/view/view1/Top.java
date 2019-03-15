package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.CircleProgressView;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;


public class Top extends ViewGroup {
    public Dgua1 dg;
    public int index = 0;
    private String str;
    private TextView tva;

    public Top(Context context) {
        super(context);
        this.tva = new TextView(context);
        this.tva.setTextSize(18.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.tva);
    }

    public Top(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua5);
        this.tva = new TextView(context);
        this.tva.setTextSize(18.0f);
        this.tva.setTextColor(-1);
        this.tva.setText("");
        addView(this.tva);
        this.dg = new Dgua1(context);
        addView(this.dg);

    }

    public void setText(String str, String str2) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(this.str);
        this.dg.setText(StringHelper.toDBC(str2));
    }

    public Top(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    public void setNU(int a){
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.str = this.tva.getText().toString();
        View childAt = getChildAt(0);
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(18.0f);
        paint.getTextBounds(this.str, 0, this.str.length(), rect);
        int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
        int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
        int i5 = (ActivityCommon.SCREENWIDTH - width) / 2;
        int i6 = ((ActivityCommon.SCREENHEIGHT / 10) - height) / 2;
        childAt.layout(i5, i6, (width + i5) + 50, (height + i6) + 50);
        getChildAt(1).layout((ActivityCommon.SCREENWIDTH * 3) / 4, 0, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT / 10);
       // getChildAt(2).layout(0, 0, ActivityCommon.SCREENHEIGHT / 10, ActivityCommon.SCREENHEIGHT / 10);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
