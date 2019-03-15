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

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;

public class DguaCtSubV3 extends ViewGroup {
    public int index = 0;
    private String str;
    private TextView tva;

    public DguaCtSubV3(Context context) {
        super(context);
        this.tva = new TextView(context);
        this.tva.setTextSize(14.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.tva);
    }

    public DguaCtSubV3(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tva = new TextView(context);
        this.tva.setTextSize(14.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setcolor() {
        this.tva.setTextColor(Color.RED);
    }

    public void setText(String str) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(this.str);
    }

    public String getText() {
        return this.tva.getText().toString();
    }

    public DguaCtSubV3(Context context, AttributeSet attributeSet, int i) {
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
            this.str = this.tva.getText().toString();
            View childAt = getChildAt(i5);
            Paint paint = new Paint();
            Rect rect = new Rect();
            paint.setTextSize(14.0f);
            paint.getTextBounds(this.str, 0, this.str.length(), rect);
            int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
            int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
            int i6 = ((ActivityCommon.SCREENWIDTH / 5) - width) / 2;
            int i7 = ((ActivityCommon.SCREENHEIGHT / 10) - height) / 2;
            childAt.layout(i6, i7, (width + i6) + 50, (height + i7) + 50);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH *2/ 5), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }
}
