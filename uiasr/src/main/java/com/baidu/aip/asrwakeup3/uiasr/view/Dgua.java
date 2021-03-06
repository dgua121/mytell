package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;


public class Dgua extends ViewGroup implements OnTouchListener {
    public int index = 0;
    private String str;
    private TextView tva;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            setBackgroundResource(R.drawable.down11);
            return true;
        } else if (motionEvent.getAction() != 1) {
            return false;
        } else {
            setBackgroundResource(R.drawable.dgua3);
            return true;
        }
    }

    public Dgua(Context context) {
        super(context);
        this.tva = new TextView(context);
        this.tva.setTextSize(16.0f);
        this.tva.setText("a");
        this.tva.setTextColor(SupportMenu.CATEGORY_MASK);
        addView(this.tva);
        setOnTouchListener(this);
    }

    public Dgua(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tva = new TextView(context);
        this.tva.setTextSize(16.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        setOnTouchListener(this);
    }

    public void setText(String str) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(this.str);
    }

    public Dgua(Context context, AttributeSet attributeSet, int i) {
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
            paint.setTextSize(16.0f);
            paint.getTextBounds(this.str, 0, this.str.length(), rect);
            int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
            int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
            int i6 = ((ActivityCommon.SCREENWIDTH / 3) - width) / 2;
            int i7 = ((ActivityCommon.SCREENHEIGHT / 10) - height) / 2;
            childAt.layout(i6, i7, (width + i6) + 50, (height + i7) + 50);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH / 3), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }
}
