package com.baidu.aip.asrwakeup3.uiasr.Dailog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaMishiSubV;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;

public class MishiMenuDialog extends Dialog {
    public String a1;
    public String a2;
    public String a3;
    public Context cctt;
    public DguaMishiSubV dcsv;

    public MishiMenuDialog(DguaMishiSubV dguaMishiSubV, Context context, int i, int i2, int i3, int i4) {
        super(context, i4);
        this.dcsv = dguaMishiSubV;
        setContentView(i3);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        getDensity(context);
        attributes.width = i;
        attributes.height = i2;
        attributes.gravity = 80;
        attributes.windowAnimations = i4;
        window.setAttributes(attributes);
        this.a1 = this.dcsv.ds1.getText();
        this.a2 = this.dcsv.ds2.getText();
       // ((Top) findViewById(R.id.activitymainTop11)).setText(this.a2, "");
    }

    private float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
