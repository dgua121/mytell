package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.Dailog.PhoneMenuDialog;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.messwork;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.SerializableMap;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmework;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;
import com.baidu.aip.asrwakeup3.uiasr.service.mewkservice;
import com.google.android.gms.ads.AdRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DguaCtSubVmess extends phoneDguaCtSubV {


    private Map m = new HashMap();
    private Context ct;
    private inservice myBinder; //这个是我们定义的中间人对象
    public static Handler handler;

    public DguaCtSubVmess(Context context, String str, String str2, String str3, Map map) {

        super(context, str, str2, str3);

        handler = new Handler() {

            @Override
            public void handleMessage(Message message) {


                if (message.what == 1111) {

                    if (myBinder != null) {
                        myBinder.pausedwork();
                    }
                    ds1.setdown();

                }

            }

        };


        ct = context;
        m = map;
        ds1.id = 1;
        final SerializableMap myMap = new SerializableMap();
        myMap.setMap(m);//将map数据添加到封装的myMap中
        Intent intent = new Intent(ct, mewkservice.class);
        Bundle bundle = new Bundle();

        bundle.putSerializable("map", myMap);
        intent.putExtras(bundle);
        ct.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    protected void finalize( )
    {
        destory();
    }

    public void destory() {
        if (myBinder != null) {
            ct.unbindService(serviceConnection);
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {

        return super.onDown(motionEvent);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        ds1.seton();
        if (myBinder != null) {
           Map m= new Testmework(getContext()).querylast();
           if (m.get("other").toString().equals("false1")){
               myBinder.startwork(1);
           }else{
               int a= Integer.parseInt(m.get("other").toString());
               myBinder.startwork(a);
               System.out.println(a);
           }
        }

        if (ActivityUiRecog.mInterstitialAd.isLoaded()) {
            ActivityUiRecog.mInterstitialAd.show();
        } else {
        }
           return false;
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


    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

}
