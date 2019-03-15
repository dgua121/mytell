package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.Dailog.PhoneMenuDialog;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.CircleProgressView;
import com.baidu.aip.asrwakeup3.uiasr.gongju.StringHelper;

public class DguaCtSubV1 extends ViewGroup implements GestureDetector.OnGestureListener {
    private boolean kg = true, kg2 = false;
    public CircleProgressView cv;
    private GestureDetector mGestureDetector;
    public int id = 0;
    public static PhoneMenuDialog pd;

    public DguaCtSubV1(Context context) {
        super(context);
        this.cv = new CircleProgressView(context);
        this.mGestureDetector = new GestureDetector(context, (GestureDetector.OnGestureListener) this);
        setLongClickable(true);
        pd = new PhoneMenuDialog(getContext(), ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT*3/10, R.layout.layout_dialog, R.style.dialog1);
        //pd.show();
        setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {

                return mGestureDetector.onTouchEvent(motionEvent);

            }
        });
    }

    public void seton() {
        if (kg) {
            addView(this.cv);
            requestLayout();
            kg = false;
        }
    }

    public void setdown() {
        if (!kg) {
            removeView(getChildAt(0));
            requestLayout();
            kg = true;
        }
    }

    public void setText(String str) {
        this.cv.setRadius(60);
        this.cv.setStokewidth(5);
        this.cv.setTextSize(20);
        this.cv.setColor(Color.GRAY, Color.RED, Color.BLUE);
        this.cv.setSpeed(20);
        this.cv.setProgress(0);
    }

    public void setNU(int a) {
        this.cv.setRadius(60);
        this.cv.setStokewidth(5);
        this.cv.setTextSize(20);
        this.cv.setColor(Color.GRAY, Color.RED, Color.BLUE);
        this.cv.setSpeed(20);
        this.cv.setProgress(a);
    }

    public int getText() {
        return this.cv.getProgress();
    }

    public DguaCtSubV1(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        if (childCount > 0) {
            View childAt = getChildAt(0);
            childAt.layout(0, 0, ActivityCommon.SCREENHEIGHT / 10, ActivityCommon.SCREENHEIGHT / 10);
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize((ActivityCommon.SCREENWIDTH * 1) / 5), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (id == 1 && !kg) {
            pd.show();
            //new PhoneMenuDialog(getContext(), ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENWIDTH, R.layout.layout_dialog, R.style.dialog1).show();
            //setdown();
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        return false;
    }
}
