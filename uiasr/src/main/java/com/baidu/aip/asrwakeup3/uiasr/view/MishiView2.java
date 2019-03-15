package com.baidu.aip.asrwakeup3.uiasr.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.view.view2.SubMishiView;

//import java.io.Parcelable;

public class MishiView2 extends ViewGroup {
    public int wx;

    /* renamed from: com.dgua.jmdx.view.MishiView2$100000000 */
    class AnonymousClass100000000 implements OnTouchListener {
        private final MishiView2 this$0;
        private final Context val$paramContext;

        AnonymousClass100000000(MishiView2 mishiView2, Context context) {
            this.this$0 = mishiView2;
            this.val$paramContext = context;
        }

       /* static MishiView2 access$0(AnonymousClass100000000 anonymousClass100000000) {
            return anonymousClass100000000.this$0;
        }*/

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Intent intent = new Intent();
                intent.putExtra("what", 6);
                Parcelable dguaModel = new DguaModel("", "", "", "", ((SubMishiView) view).index, 0);
                Bundle bundle = new Bundle();
                bundle.putParcelable("mMyDate", dguaModel);
                intent.putExtra("bundle", bundle);
                intent.setAction("com.baidu.aip.asrwakeup3.uiasr.Service.MishiData");
                intent.setPackage(getContext().getPackageName());//android 5.0之后必需显性调用
                this.val$paramContext.startService(intent);
                this.this$0.setimage(((SubMishiView) view).index);
                return true;
            } else if (motionEvent.getAction() == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    /* renamed from: com.dgua.jmdx.view.MishiView2$100000001 */
    class AnonymousClass100000001 implements OnTouchListener {
        private final MishiView2 this$0;
        private final Context val$paramContext;

        AnonymousClass100000001(MishiView2 mishiView2, Context context) {
            this.this$0 = mishiView2;
            this.val$paramContext = context;
        }

       /* static MishiView2 access$0(AnonymousClass100000001 anonymousClass100000001) {
            return anonymousClass100000001.this$0;
        }
*/
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Intent intent = new Intent();
                intent.putExtra("what", 6);
                Parcelable dguaModel = new DguaModel("", "", "", "", ((SubMishiView) view).index, 0);
                Bundle bundle = new Bundle();
                bundle.putParcelable("mMyDate", dguaModel);
                intent.putExtra("bundle", bundle);
                intent.setAction("com.baidu.aip.asrwakeup3.uiasr.Service.MishiData");
                intent.setPackage(getContext().getPackageName());//android 5.0之后必需显性调用
                this.val$paramContext.startService(intent);
                this.this$0.setimage(((SubMishiView) view).index);
                return true;
            } else if (motionEvent.getAction() == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void setViewColor() {
    }

    public MishiView2(Context context) {
        super(context);
        for (int i = 0; i < 3; i++) {
            SubMishiView subMishiView = new SubMishiView(context);
            subMishiView.index = i;
            subMishiView.setText(new StringBuffer().append(i).append("1122").toString());
            subMishiView.setOnTouchListener(new AnonymousClass100000000(this, context));
            addView(subMishiView);
        }
    }

    public MishiView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBackgroundResource(R.drawable.dgua3);
        for (int i = 0; i < 3; i++) {
            SubMishiView subMishiView = new SubMishiView(context);
            subMishiView.index = i;
            subMishiView.setText(new StringBuffer().append(i).append("").toString());
            subMishiView.setOnTouchListener(new AnonymousClass100000001(this, context));
            addView(subMishiView);
        }
    }

    public MishiView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setimage(int i) {
        switch (i) {
            case 0:
                ((SubMishiView) getChildAt(0)).setImage(2);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 1:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(2);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 2:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(2);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 3:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(2);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 4:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(2);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 5:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(2);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 6:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(2);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 7:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(2);
                ((SubMishiView) getChildAt(8)).setImage(1);
                return;
            case 8:
                ((SubMishiView) getChildAt(0)).setImage(1);
                ((SubMishiView) getChildAt(1)).setImage(1);
                ((SubMishiView) getChildAt(2)).setImage(1);
                ((SubMishiView) getChildAt(3)).setImage(1);
                ((SubMishiView) getChildAt(4)).setImage(1);
                ((SubMishiView) getChildAt(5)).setImage(1);
                ((SubMishiView) getChildAt(6)).setImage(1);
                ((SubMishiView) getChildAt(7)).setImage(1);
                ((SubMishiView) getChildAt(8)).setImage(2);
                return;
            default:
                return;
        }
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        int childCount = getChildCount();
        int i6 = 0;
        int i7 = 0;
        while (i5 < childCount) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            this.wx = (ActivityCommon.SCREENWIDTH - (measuredWidth * 3)) / 4;
            int i8 = (this.wx + measuredWidth) + i6;
            i6 = (this.wx + ((this.wx + measuredHeight) * i7)) + measuredHeight;
            if (i8 > i3) {
                i7++;
                i6 = measuredWidth + this.wx;
                i8 = (this.wx + ((this.wx + measuredHeight) * i7)) + measuredHeight;
            } else {
                int i9 = i6;
                i6 = i8;
                i8 = i9;
            }
            childAt.layout(i6 - measuredWidth, (i8 - measuredHeight) - ((this.wx * 4) / 5), i6, i8 - ((this.wx * 4) / 5));
            i5++;
        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize(ActivityCommon.SCREENWIDTH - ((this.wx * 8) / 5)));
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
