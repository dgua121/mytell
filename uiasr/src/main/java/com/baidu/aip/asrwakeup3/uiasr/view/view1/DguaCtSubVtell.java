package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.Dailog.PhoneMenuDialog;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gongju.WifiTool;
import com.baidu.aip.asrwakeup3.uiasr.service.callService;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;
import com.baidu.aip.asrwakeup3.uiasr.service.mewkservice;

import static java.lang.Thread.sleep;


public class DguaCtSubVtell extends phoneDguaCtSubV {

    private inservice myBinder; //这个是我们定义的中间人对象
    private Context ct;


    public DguaCtSubVtell(Context context, String str, String str2, String str3) {
        super(context, str, str2, str3);
        ct = context;
        Intent intent = new Intent(context, callService.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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

    protected void finalize() {
        destory();
    }

    public void destory() {
        if (myBinder != null) {
            ct.unbindService(serviceConnection);
        }
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {

        Intent intent1 = new Intent(getContext(), callService.class);
        getContext().startService(intent1);// 启动电话服务
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (myBinder != null) {
            myBinder.startwork(1);
        }


        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {


    }
}
