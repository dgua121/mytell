package com.baidu.aip.asrwakeup3.uiasr.view.view2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.MishiView;

//import java.io.Parcelable;

public class MishiText extends ViewGroup {
    public DguaMishibutton back;
    public DguaMishibutton change;
    public EditText et1;
    public int index;
    public int ip = -1;
    public MishiView mv;
    public DguaMishibutton save;
    public TextView tv1,tv3;
    public EditText tv2;

    class AnonymousClass100000000 implements OnTouchListener {
        private final MishiText this$0;
        private final Context val$c;

        AnonymousClass100000000(MishiText mishiText, Context context) {
            this.this$0 = mishiText;
            this.val$c = context;
        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                Intent intent = new Intent();
                intent.putExtra("what", 5);
                Parcelable dguaModel = new DguaModel("", "", this.this$0.getText(), "", this.this$0.ip, 0);
                Bundle bundle = new Bundle();
                bundle.putParcelable("mMyDate", dguaModel);
                intent.putExtra("bundle", bundle);
                intent.setAction("com.baidu.aip.asrwakeup3.uiasr.Service.MishiData");
                intent.setPackage(getContext().getPackageName());
                this.val$c.startService(intent);
                this.this$0.index = 0;
                this.this$0.requestLayout();
                return true;
            } else if (motionEvent.getAction() == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public MishiText(Context context) {
        super(context);
    }

    public MishiText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.save = new DguaMishibutton(context);
        this.save.setText("更改");
        this.save.setOnTouchListener(new AnonymousClass100000000(this, context));
        this.change = new DguaMishibutton(context);
        this.change.setText("取消");
        this.change.setOnTouchListener(new OnTouchListener() {
            /*private final MishiText this$0;

            {
                this.this$0 = r1;
            }

            static MishiText access$0(AnonymousClass100000001 anonymousClass100000001) {
                return anonymousClass100000001.this$0;
            }*/

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                index = 0;
                requestLayout();
                return true;
            }
        });
        this.et1 = new EditText(context);
        this.et1.setBackgroundResource(R.drawable.dgua);
        this.tv1 = new TextView(context);
        this.tv3 = new TextView(context);
        this.tv2 = new EditText(context);
        this.tv2.setBackgroundResource(R.drawable.dgua);
        this.tv2.setTextSize((float) 18);
        this.tv2.setGravity(16);
        this.tv2.setText("1000");
        this.tv1.setBackgroundResource(R.drawable.dgua);
        this.tv1.setTextSize((float) 18);
        this.tv1.setGravity(16);
        this.tv1.setText("\n触摸可修改");
        this.tv1.setOnTouchListener(new OnTouchListener() {
           /* private final MishiText this$0;

            {
                this.this$0 = r1;
            }

            static MishiText access$0(AnonymousClass100000002 anonymousClass100000002) {
                return anonymousClass100000002.this$0;
            }*/

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                index = 1;
                requestLayout();
                return true;
            }
        });
        this.tv3.setBackgroundResource(R.drawable.dgua);
        this.tv3.setTextSize((float) 18);
        this.tv3.setGravity(16);
        this.tv3.setText("发送");
        this.tv3.setOnTouchListener(new OnTouchListener() {
           /* private final MishiText this$0;

            {
                this.this$0 = r1;
            }

            static MishiText access$0(AnonymousClass100000002 anonymousClass100000002) {
                return anonymousClass100000002.this$0;
            }*/

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    Message message=new Message();
                    message.what=666;
                    message.arg1= Integer.parseInt(tv2.getText().toString());
                    message.obj=tv1.getText();
                   ActivityCommon.handler.sendMessage(message);
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                return true;
            }
        });
        this.mv = new MishiView(context);
        addView(this.et1);
        addView(this.tv1);
        addView(this.save);
        addView(this.change);
        addView(this.mv);
        addView(this.tv2);
        addView(this.tv3);
    }

    public void setText(String str) {
        this.tv1.setText(str);
        requestLayout();
    }

    public void setimage(int i) {
        switch (i) {
            case 0:
                ((SubMishiView) this.mv.getChildAt(0)).setImage(2);
                ((SubMishiView) this.mv.getChildAt(1)).setImage(1);
                ((SubMishiView) this.mv.getChildAt(2)).setImage(1);
                return;
            case 1:
                ((SubMishiView) this.mv.getChildAt(0)).setImage(1);
                ((SubMishiView) this.mv.getChildAt(1)).setImage(2);
                ((SubMishiView) this.mv.getChildAt(2)).setImage(1);
                return;
            case 2:
                ((SubMishiView) this.mv.getChildAt(0)).setImage(1);
                ((SubMishiView) this.mv.getChildAt(1)).setImage(1);
                ((SubMishiView) this.mv.getChildAt(2)).setImage(2);
                return;
            default:
                return;
        }
    }

    public String getText() {
        return this.et1.getText().toString();
    }

    public MishiText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override
    public void addView(View view) {
        super.addView(view);
    }

    @Override
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(4);
        if (this.index == 0) {
            getChildAt(1).layout(((MishiView) getChildAt(4)).wx, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENWIDTH - ((MishiView) getChildAt(4)).wx, ActivityCommon.SCREENWIDTH+(ActivityCommon.SCREENHEIGHT / 5));
            getChildAt(0).layout(((MishiView) getChildAt(4)).wx, ActivityCommon.SCREENWIDTH, ((MishiView) getChildAt(4)).wx, ActivityCommon.SCREENWIDTH);
            getChildAt(2).layout(0, ActivityCommon.SCREENWIDTH, 0, ActivityCommon.SCREENWIDTH);
            getChildAt(3).layout(0, ActivityCommon.SCREENWIDTH, 0, ActivityCommon.SCREENWIDTH);
            childAt.layout(0, ActivityCommon.SCREENHEIGHT*5/10, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENWIDTH);
            View childAt2 = getChildAt(2);
            int measuredHeight = childAt2.getMeasuredHeight();
            getChildAt(5).layout(0, ( (ActivityCommon.SCREENHEIGHT *2/ 5)-100) , 700, ( (ActivityCommon.SCREENHEIGHT *2/ 5)));
            getChildAt(6).layout(900, ( (ActivityCommon.SCREENHEIGHT*2 / 5)-100) , ActivityCommon.SCREENWIDTH, ( (ActivityCommon.SCREENHEIGHT*2 / 5)));

        }
        if (this.index == 1) {
            getChildAt(0).layout(((MishiView) getChildAt(4)).wx, 20, ActivityCommon.SCREENWIDTH - ((MishiView) getChildAt(4)).wx, (ActivityCommon.SCREENHEIGHT / 5) - 20);
            getChildAt(1).layout(((MishiView) getChildAt(4)).wx, 20, ((MishiView) getChildAt(4)).wx, 20);
            View childAt2 = getChildAt(2);
            int measuredWidth = childAt2.getMeasuredWidth();
            int measuredHeight = childAt2.getMeasuredHeight();
            childAt2.layout(((MishiView) getChildAt(4)).wx, (ActivityCommon.SCREENHEIGHT / 5) - 20, ((MishiView) getChildAt(4)).wx + measuredWidth, ((ActivityCommon.SCREENHEIGHT / 5) + measuredHeight) - 20);
            getChildAt(3).layout((ActivityCommon.SCREENWIDTH - ((MishiView) getChildAt(4)).wx) - measuredWidth, (ActivityCommon.SCREENHEIGHT / 5) - 20, ActivityCommon.SCREENWIDTH - ((MishiView) getChildAt(4)).wx, (measuredHeight + (ActivityCommon.SCREENHEIGHT / 5)) - 20);
            childAt.layout(0, (ActivityCommon.SCREENHEIGHT * 3) / 10, ActivityCommon.SCREENWIDTH, childAt.getMeasuredHeight() + ((ActivityCommon.SCREENHEIGHT * 3) / 10));
            getChildAt(5).layout(0, ( (ActivityCommon.SCREENHEIGHT *2/ 5)-100) , 700, ( (ActivityCommon.SCREENHEIGHT *2/ 5)));
            getChildAt(6).layout(800, ( (ActivityCommon.SCREENHEIGHT*2 / 5)-100) , ActivityCommon.SCREENWIDTH, ( (ActivityCommon.SCREENHEIGHT*2 / 5)));

        }
    }

    @Override
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(ActivityCommon.SCREENWIDTH), MeasureSpec.getSize((getChildAt(4).getMeasuredHeight() + (ActivityCommon.SCREENHEIGHT / 5)) + ((this.index * ActivityCommon.SCREENHEIGHT) / 10)));
        for (int i3 = 0; i3 < 6; i3++) {
            getChildAt(i3).measure(0, 0);
        }
    }
}
