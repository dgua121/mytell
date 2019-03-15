package com.baidu.aip.asrwakeup3.uiasr.Dailog;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubVmess;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.phoneDguaCtSubV;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaDgV;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaViewBackground;

import java.util.HashMap;
import java.util.Map;


public class PhoneMenuDialog extends Dialog {
    public String a1="aaaaaa";
    public phoneDguaCtSubV dcsv;
    public static Handler handler;
    private Context ct;
    private Map m = new HashMap();
    private inservice myBinder; //这个是我们定义的中间人对象
    public PhoneMenuDialog( Context context, int i, int i2, int i3, int i4) {
        super(context, i4);
        setContentView(i3);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        getDensity(context);
        attributes.width = i;
        attributes.height = i2;
        attributes.gravity = 80;
        attributes.windowAnimations = i4;
        window.setAttributes(attributes);
        ct=context;


        final DguaDgV dguaDgV = (DguaDgV) findViewById(R.id.layoutdialogDguaDgV);

        ((DguaViewBackground) findViewById(R.id.layoutdialogDguaViewBackground21)).mydgua1_1.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }

                dismiss();
                return true;
            }
        });

        ((DguaViewBackground) findViewById(R.id.layoutdialogDguaViewBackground21)).mydgua1_2.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }

                Message m=new Message();
                m.what=1111;
                DguaCtSubVmess.handler.sendMessage(m);
                dismiss();
                return true;
            }
        });

        handler = new Handler() {

            @Override
            public void handleMessage(Message message) {


                    if (message.what == 1111) {
                        Map m = (Map) message.obj;
                         int aaa = (int) ((Map) message.obj).get("n");
                         String sss = (String) ((Map) message.obj).get("p").toString();
                        String aaa1 = (String) ((Map) message.obj).get("m").toString();
                         String sss2 = (String) ((Map) message.obj).get("w").toString();

                        dguaDgV.ds1.setText("已发"+aaa+"条");
                        dguaDgV.ds2.setText(sss2);
                        dguaDgV.ds3.setText(sss);
                        dguaDgV.ds4.setText(aaa1);

                    }

                }

        };
    }

    // 在Activity中，我们通过ServiceConnection接口来取得建立连接与连接意外丢失的回调
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            // 获取服务的操作对象
            myBinder = (inservice) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myBinder = null;
            // 连接断开
        }
    };

    private float getDensity(Context context) {
       /* if (myBinder != null) {
            ct.unbindService(serviceConnection);
        }*/
        return context.getResources().getDisplayMetrics().density;
    }
}
