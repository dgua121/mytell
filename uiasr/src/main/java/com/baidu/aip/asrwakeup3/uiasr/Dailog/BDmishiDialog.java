package com.baidu.aip.asrwakeup3.uiasr.Dailog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.view.MishiView2;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;


public class BDmishiDialog extends Dialog {
    public static MishiView2 mv;
    public String a1;
    public String a2;
    public String a3;
    public Context cctt;
    public Top t1;

    public BDmishiDialog(Context context, int i, int i2, int i3, int i4) {
        super(context, i4);
        setContentView(i3);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        getDensity(context);
        attributes.width = i - 50;
        attributes.height = i2;
        attributes.gravity = 80;
        attributes.windowAnimations = i4;
        window.setAttributes(attributes);
        this.t1 = (Top) findViewById(R.id.layoutdialogmsTop1);
        this.t1.setText("指定密钥", "");
        mv = (MishiView2) findViewById(R.id.layoutdialogmsMishiView1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        try {
            intent.setClass(getContext(), Class.forName("com.dgua.jmdx.MainActivity"));
            getContext().startActivity(intent);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
