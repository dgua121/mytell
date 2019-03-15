package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;


public class Dgua11 extends ViewGroup implements OnTouchListener {
    public Bitmap bm2;
    public Bitmap bm3;
    public int index = 0;
    public ImageView mydgua1_2;
    private String str;
    public TextView tva;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1) {
            return true;
        }
        return false;
    }

    public Dgua11(Context context, int i, int i2) {
        super(context);
        this.tva = new TextView(context);
        this.tva.setTextSize(12.0f);
        this.tva.setText("a");
        this.tva.setTextColor(-1);
        this.mydgua1_2 = new ImageView(context);
        this.bm2 = BitmapFactory.decodeResource(getResources(), i);
        this.bm3 = BitmapFactory.decodeResource(getResources(), i2);
        setImage(1);
        addView(this.mydgua1_2);
        addView(this.tva);
        setOnTouchListener(this);
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

    public Dgua11(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tva = new TextView(context);
        this.tva.setTextSize(12.0f);
        this.tva.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        setOnTouchListener(this);
    }

    public void setText(String str) {
        this.str = StringHelper.toDBC(str);
        this.tva.setText(this.str);
        requestLayout();
    }

    public Dgua11(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        this.str = this.tva.getText().toString();
        View childAt2 = getChildAt(1);
        Paint paint = new Paint();
        Rect rect = new Rect();
        paint.setTextSize(12.0f);
        paint.getTextBounds(this.str, 0, this.str.length(), rect);
        int height = ((rect.bottom + rect.height()) * ActivityCommon.densityDpi) / 160;
        int width = ((rect.width() + rect.left) * ActivityCommon.densityDpi) / 160;
        int i5 = ((ActivityCommon.SCREENWIDTH / 4) - width) / 2;
        int i6 = (((ActivityCommon.SCREENHEIGHT / 10) - height) - ((width * 5) / 4)) / 2;
        int i7 = i6 + width;
        childAt.layout(i5, i6, i5 + width, i6 + width);
        childAt2.layout(i5, i7, (i5 + width) + 50, (height + i7) + 50);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH / 4), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }
}
