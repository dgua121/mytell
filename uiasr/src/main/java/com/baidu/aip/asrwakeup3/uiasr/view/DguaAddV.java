package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;


public class DguaAddV extends ViewGroup {
    public int index = 0;
    private String str;
    private TextView tva;

    public DguaAddV(Context context) {
        super(context);
    }

    public DguaAddV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua2);
        this.tva = new TextView(context);
        this.tva.setTextSize(18.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.tva.setText("新增");
        addView(this.tva);
    }

    public void setText(String str) {
        this.tva.setText(str);
        this.str = str;
    }

    public DguaAddV(Context context, AttributeSet attributeSet, int i) {
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
            Paint paint = new Paint();
            Rect rect = new Rect();
            paint.setTextSize(18.0f);
            paint.getTextBounds(this.str, 0, this.str.length(), rect);
            int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
            int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
            int i6 = (ActivityCommon.SCREENWIDTH - width) / 2;
            int i7 = ((ActivityCommon.SCREENHEIGHT / 10) - height) / 2;
            childAt.layout(i6, i7, width + i6, height + i7);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }
}
