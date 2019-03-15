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
import android.view.Window;
import android.view.WindowManager;

import com.android.server.telecom.components.out.OutDivMessage;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.call.tell.ContactsUtils;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubVmess;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaDgV;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaViewBackground;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.phoneDguaCtSubV;

import java.util.HashMap;
import java.util.Map;

public class Dialog1 extends Dialog {
    public String a1 = "aaaaaa";
    public phoneDguaCtSubV dcsv;
    public static Handler handler;
    private Context ct;
    public String str = "";
    public String state = "C";
    DguaDgV dguaDgV;
    private Map m = new HashMap();
    private inservice myBinder; //这个是我们定义的中间人对象

    public Dialog1(Context context, int i, int i2, int i3, int i4) {
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
        ct = context;


        dguaDgV = (DguaDgV) findViewById(R.id.layoutdialogDguaDgV);

        ((DguaViewBackground) findViewById(R.id.layoutdialogDguaViewBackground21)).mydgua1_1.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }
                state = "A";
                dismiss();
                return true;
            }
        });

        ((DguaViewBackground) findViewById(R.id.layoutdialogDguaViewBackground21)).mydgua1_2.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }

                state = "B";
                dismiss();
                return true;
            }
        });

        ((DguaViewBackground) findViewById(R.id.layoutdialogDguaViewBackground21)).mydgua1_3.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    return true;
                }
                if (motionEvent.getAction() != 1) {
                    return false;
                }

                state = "C";
                dismiss();
                return true;
            }
        });


        handler = new Handler() {

            @Override
            public void handleMessage(Message message) {


                if (message.what == 1111) {
                    OutDivMessage m = (OutDivMessage) message.obj;

                    dguaDgV.ds1.setText(m.getname());
                    dguaDgV.ds3.setText(m.getphone());

                    Message msg = new Message();
                    msg.what = 1001;

                    ActivityUiRecog.handler.sendMessage(msg);
                }

                if (message.what == 1000) {
                    if (!message.obj.toString().contains("下一个")) {

                        str = str + message.obj.toString() + ".";

                        dguaDgV.ds2.setText(str);
                        if(message.obj.toString().contains("重点")){
                            state="A";
                        }else{
                            state="B";
                        }

                    } else {
                        dismiss();
                    }

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

    @Override
    public void dismiss() {

        super.dismiss();

        Message msg = new Message();
        msg.what = 1002;
        msg.obj = new OutDivMessage( dguaDgV.ds3.getText(), state,str);

       ActivityUiRecog.handler.sendMessage(msg);
        state="C";

    }
}
