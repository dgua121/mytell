package com.baidu.aip.asrwakeup3.uiasr.view.view1;

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
import android.widget.Toast;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaDgSubV1;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaDgSubV2;
//import java.io.Parcelable;

public class DguaDgV extends ViewGroup implements OnGestureListener {
    public boolean bn = false;
    public DguaDgSubV1 ds1;
    public DguaDgSubV2 ds2;
    public DguaDgSubV1 ds3;
    public DguaDgSubV2 ds4;
    private GestureDetector mGestureDetector;

    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }

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
       // Toast.makeText(getContext(), "35688", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        Parcelable dguaModel = new DguaModel("", this.ds2.getText(), "", "", 0, 1);
        Bundle bundle = new Bundle();
        bundle.putParcelable("mMyDate", dguaModel);
        intent.putExtra("bundle", bundle);
        try {
            intent.setClass(getContext(), Class.forName("com.dgua.jmdx.view.DguaTalkActivity"));
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
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public DguaDgV(Context context, String str, String str2, String str3) {
        super(context);
        this.ds1 = new DguaDgSubV1(context);
        this.ds2 = new DguaDgSubV2(context);
        this.ds3 = new DguaDgSubV1(context);
        this.ds4 = new DguaDgSubV2(context);
        this.ds1.setText(str);
        this.ds2.setText(str2);
        this.ds3.setText(str3);
        addView(this.ds1);
        addView(this.ds2);
        addView(this.ds3);
        addView(this.ds4);
        this.mGestureDetector = new GestureDetector(context, this);
        setLongClickable(true);
        setOnTouchListener(new OnTouchListener() {
           /* private final DguaDgV this$0;

            {
                this.this$0 = r1;
            }

            static DguaDgV access$0(AnonymousClass100000000 anonymousClass100000000) {
                return anonymousClass100000000.this$0;
            }*/

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    public DguaDgV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua3);
        this.ds1 = new DguaDgSubV1(context);
        this.ds2 = new DguaDgSubV2(context);
        this.ds3 = new DguaDgSubV1(context);
        this.ds4 = new DguaDgSubV2(context);
        addView(this.ds1);
        addView(this.ds2);
        addView(this.ds3);
        addView(this.ds4);
    }

    public DguaDgV(Context context, AttributeSet attributeSet, int i) {
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
        int measuredHeight = childAt.getMeasuredHeight();
        childAt.layout(0, 0, measuredWidth, measuredHeight);
        getChildAt(2).layout(0, measuredHeight, measuredWidth, measuredHeight * 2);
        childAt = getChildAt(1);
        int measuredWidth2 = childAt.getMeasuredWidth();
        int measuredHeight2 = childAt.getMeasuredHeight();
        childAt.layout(measuredWidth, 0, measuredWidth + measuredWidth2, measuredHeight2);
        getChildAt(3).layout(measuredWidth, measuredHeight, measuredWidth2 + measuredWidth, measuredHeight2 * 2);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 5));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
