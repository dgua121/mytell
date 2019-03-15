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
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;

public class DguaDgSubV1 extends ViewGroup {
    public int index = 0;
    private String str;
    private TextView tva;

    public DguaDgSubV1(Context context) {
        super(context);
        setBackgroundResource(R.drawable.dialog);
        this.tva = new TextView(context);
        this.tva.setTextSize(10.0f);
        this.tva.setText("");
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.tva);
    }

    public DguaDgSubV1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setText(String str) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(this.str);
    }

    public String getText() {
        return this.tva.getText().toString();
    }

    public DguaDgSubV1(Context context, AttributeSet attributeSet, int i) {
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
            paint.setTextSize(10.0f);
            paint.getTextBounds(this.str, 0, this.str.length(), rect);
            int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
            int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
            int i6 = (((ActivityCommon.SCREENWIDTH * 3) / 10) - width) / 10;
            int i7 = ((ActivityCommon.SCREENHEIGHT / 10) - height) / 2;
            childAt.layout(i6, i7, (width + i6) + 250, (height + i7) + 50);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize((ActivityCommon.SCREENWIDTH * 3) / 10), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }
}
