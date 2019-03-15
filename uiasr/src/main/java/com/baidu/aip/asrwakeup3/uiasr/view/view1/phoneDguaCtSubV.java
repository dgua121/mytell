package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmessage;
import java.util.Calendar;


public class phoneDguaCtSubV extends ViewGroup implements OnGestureListener {

    public DguaCtSubV1 ds1;
    public DguaCtSubV2 ds2;
    public DguaCtSubV3 ds3;
    private Context ct;
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
        Toast.makeText(getContext(), "电话", Toast.LENGTH_SHORT).show();
        ds1.seton();

        if(ds2.getText().equals("电话")) {
           new Thread() {
               @Override
               public void run() {
                   super.run();
                   Calendar cal=Calendar.getInstance();
                   String time_cal="ph"+cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+cal.get(Calendar.DATE)+
                           +cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.MILLISECOND);
                   Test test1 = new Test(ct);
                   test1.importSheet(time_cal);
               }
           }.start();
       }
        if(ds2.getText().equals("去重")) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Test test1 = new Test(ct);
                    int a=test1.updata();
                    Test test2 = new Test(ct);
                    test2.querysize();
                    System.out.println("去重后有："+a+"个数据");

                }
            }.start();
        }
        if(ds2.getText().equals("短信")) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    Calendar cal=Calendar.getInstance();
                    String time_cal="me"+cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+cal.get(Calendar.DATE)
                            +cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.MILLISECOND);
                    Testmessage test1 = new Testmessage(ct);
                    test1.importSheet(time_cal);
                }
            }.start();
        }
        if(ds2.getText().equals("清空")) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                       Testmessage test1 = new Testmessage(ct);
                    test1.rename();
                    Message msg = new Message();
                    msg.what = 1241;
                    msg.obj = 0;
                   // ActivityCommon.tf1.handler.sendMessage(msg);
                }
            }.start();
        }

        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        ds1.setdown();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public phoneDguaCtSubV(Context context, String str, String str2, String str3) {
        super(context);
        ct = context;
        setBackgroundResource(R.drawable.dgualist);
        this.ds1 = new DguaCtSubV1(context);
        this.ds2 = new DguaCtSubV2(context);
        this.ds3 = new DguaCtSubV3(context);
        this.ds1.setText(str);
        this.ds2.setText(str2);
        this.ds3.setText(str3);
        addView(this.ds3);
        addView(this.ds2);
        addView(this.ds1);
        this.mGestureDetector = new GestureDetector(context, this);
        setLongClickable(true);
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    public phoneDguaCtSubV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public phoneDguaCtSubV(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            childAt.layout(i5, 0, childAt.getMeasuredWidth() + i5, childAt.getMeasuredHeight());
            i5 += childAt.getMeasuredWidth();
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENHEIGHT / 10));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
