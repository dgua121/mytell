package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

public class DguaPagerView extends ViewPager {
    public DguaPagerView(Context context) {
        super(context);
    }

    public DguaPagerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        Toast tot = Toast.makeText(context, "匿名内部类实现button点击事件", Toast.LENGTH_LONG);
        tot.show();
    }

    public DguaPagerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return false;
    }
}
