package com.baidu.aip.asrwakeup3.uiasr.Dailog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;

public class DguaDailogPhone extends Dialog {
    private static int default_height = 120;
    private static int default_width = 160;
    EditText et1;
    EditText et2;

    public DguaDailogPhone(Context context, int i, int i2) {
        this(context, default_width, default_height, i, i2);
    }

    public DguaDailogPhone(Context context, int i, int i2, int i3, int i4) {
        super(context, i4);
        setContentView(i3);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        getDensity(context);
        attributes.width = i;
        attributes.height = i2;
        attributes.gravity = 48;
        attributes.windowAnimations = i4;
        window.setAttributes(attributes);
        this.et1 = (EditText) findViewById(R.id.addEditText1);
        this.et2 = (EditText) findViewById(R.id.addEditText2);
        et1.setHeight(i2/10);
        et2.setHeight(i2/10);
        Top top = (Top) findViewById(R.id.addTop1);
        top.setText("新建联系人", "保存");
        top.dg.setOnTouchListener(new OnTouchListener() {
            /*private final DguaDailogPhone this$0;

            {
                this.this$0 = r1;
            }

            static DguaDailogPhone access$0(AnonymousClass100000000 anonymousClass100000000) {
                return anonymousClass100000000.this$0;
            }*/

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                if (!(et1.getText().toString().equals("") || et2.getText().toString().equals(""))) {
                    Intent intent = new Intent();
                    intent.putExtra("what", 2);
                    Parcelable dguaModel = new DguaModel(et1.getText().toString(), et2.getText().toString(), "", "", 0, 0);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("mMyDate", dguaModel);
                    intent.putExtra("bundle", bundle);
                    intent.setAction("com.dgua.jmdx.Service.PhoneData");
                    intent.setPackage(getContext().getPackageName());
                    getContext().startService(intent);
                    dismiss();
                }
                return true;
            }
        });
    }

    private float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
