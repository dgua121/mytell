package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.baidu.aip.asrwakeup3.uiasr.Dailog.MishiMenuDialog;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;

//import java.io.Parcelable;

public class DguaMishiSubV extends ViewGroup implements OnGestureListener {
    public boolean bn = false;
    public DguaDgSubV1 ds1;
    public DguaDgSubV2 ds2;
    private GestureDetector mGestureDetector;

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        setBackgroundResource(R.drawable.down11);
        Intent intent = new Intent();
        Parcelable dguaModel = new DguaModel("", this.ds2.getText().substring(3), "", "", 0, 1);
        Bundle bundle = new Bundle();
        bundle.putParcelable("mMyDate", dguaModel);
        intent.putExtra("bundle", bundle);
        try {
            intent.setClass(getContext(), Class.forName("com.baidu.aip.asrwakeup3.uiasr.view.DguaTalkActivity"));
            getContext().startActivity(intent);
            return false;
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
      //  new MishiMenuDialog(this, getContext(), ActivityCommon.SCREENWIDTH, 480, R.layout.layout_dialog, R.style.Theme_dialog).show();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public DguaMishiSubV(Context context, String str, String str2, String str3) {
        super(context);
        this.ds1 = new DguaDgSubV1(context);
        this.ds2 = new DguaDgSubV2(context);
        this.ds1.setText(str);
        this.ds2.setText(str2);
        addView(this.ds1);
        addView(this.ds2);
        this.mGestureDetector = new GestureDetector(context, this);
        setLongClickable(true);
        setOnTouchListener(new OnTouchListener() {
            /*private final DguaMishiSubV this$0;

            {
                this.this$0 = r1;
            }

            static DguaMishiSubV access$0(AnonymousClass100000000 anonymousClass100000000) {
                return anonymousClass100000000.this$0;
            }*/

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    public DguaMishiSubV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DguaMishiSubV(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        int measuredWidth = childAt.getMeasuredWidth();
        childAt.layout(0, 0, measuredWidth, childAt.getMeasuredHeight());
        childAt = getChildAt(1);
        childAt.layout(measuredWidth, 0, childAt.getMeasuredWidth() + measuredWidth, childAt.getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
