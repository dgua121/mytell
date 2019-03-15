package com.baidu.aip.asrwakeup3.uiasr.view.view2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;

public class SubMishiView extends ViewGroup {
    public Bitmap bm2;
    public Bitmap bm3;
    int i1;
    public int index = 0;
    public ImageView mydgua1_2;
    int n;
    private String str;
    public TextView tva;

    public SubMishiView(Context context) {
        super(context);
        this.tva = new TextView(context);
        this.tva.setTextSize(18.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mydgua1_2 = new ImageView(context);
        this.bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.msv01);
        this.bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.msv2);
        setImage(1);
        addView(this.mydgua1_2);
        addView(this.tva);
    }

    public void setImage(int i) {
        switch (i) {
            case 1:
                this.mydgua1_2.setImageBitmap(this.bm2);
                return;
            case 2:
                this.mydgua1_2.setImageBitmap(this.bm3);
                return;
            default:
                return;
        }
    }

    public SubMishiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setText(String str) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(str);
        requestLayout();
    }

    public SubMishiView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        View childAt2 = getChildAt(1);
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(18.0f);
        paint.getTextBounds(this.str, 0, this.str.length(), rect);
        this.i1 = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
        this.n = ((rect.left + rect.width()) * ActivityCommon.densityDpi) / 160;
        childAt2.layout(0, 0, this.n + 20, this.i1 + 20);
        childAt.layout(this.n, this.i1, (ActivityCommon.SCREENWIDTH / 6) + this.n, (ActivityCommon.SCREENWIDTH / 6) + this.i1);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(((ActivityCommon.SCREENWIDTH / 6) + this.n) + 20), MeasureSpec.getSize(((ActivityCommon.SCREENWIDTH / 6) + this.i1) + 20));
    }
}
