package com.baidu.aip.asrwakeup3.uiasr.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.SerializableMap;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmessage;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mewkservice extends Service {

    public boolean kg = true;
    private boolean kg2 = true;

    private final IBinder binder = new mewkservice.MyBinder();

    public Map mp = null;
    public int idd = 0;
    private Context ct;
    private int phonen;
    public Handler handler = new Handler() {

        public void handleMessage(Message message) {

            if (message.what == 1111) {

            }
        }

    };


    //该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动时调用该方法
    @Override
    public void onCreate() {
        super.onCreate();
        ct = getApplication();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                while (kg) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void pausedwork() {
        phonen = 0;
    }

    public void startwork(int a) {
        idd=a;
        if (kg2) {
             new Thread() {
                @Override
                public void run() {
                    super.run();
                    kg2 = false;
                    int k = idd;
                    int messagen = new Testmessage(ct).querynumber();
                    phonen = new Test(ct).querynumber();
                    String messags = "";
                    //idd++;

                    for (; idd <phonen+1; ++idd) {
                        Test t = new Test(ct);
                        String phone = "";
                        System.out.println("idd------->"+idd);
                        if (idd == (phonen)) {
                            phone = t.querywork(idd, mp.get("phone").toString());
                            if (!phone.equals("10000")) {
                                System.out.println("idd---phone---->"+idd+"--phone-->"+phone);
                            Message m = new Message();
                            m.what = 123;
                            new Testmework(ct).upda(0,mp.get("work").toString());

                           // ActivityUiRecog.tf11.handler.sendMessage(m);

                            k++;
                            if (k < messagen) {

                                Testmessage t2 = new Testmessage(ct);
                                messags = t2.queryData(k);

                            } else {
                                k = 1;
                            }

                            String str1 = "Q" + phone + messags;
                            System.out.println(str1);

                            if (str1.contains("Q")) {

                                if (str1.length() >= 12) {

                                    sendSMS(str1.substring(1, 12), str1.substring(12, str1.length()), idd);
                                    Test t1 = new Test(getApplication());
                                    t1.updatamessage(str1.substring(1, 12));
                                }
                                try {
                                    //sleep(1000);
                                    sleep((int) (mp.get("time")));
                                } catch (Exception e) {

                                }
                            }
                            }

                        } else if (idd < (phonen)) {

                            phone = t.querywork(idd, mp.get("phone").toString());
                            if (!phone.equals("10000")) {
                                k++;
                                if (k < messagen) {

                                    Testmessage t2 = new Testmessage(ct);
                                    messags = t2.queryData(k);

                                } else {
                                    k = 1;
                                }

                                String str1 = "Q" + phone + messags;
                                System.out.println(str1);

                                if (str1.contains("Q")) {

                                    if (str1.length() >= 12) {

                                        Message m = new Message();
                                        m.what = 124;
                                        Map mo = new HashMap();
                                        mo.put("p", str1.substring(1, 12));
                                        mo.put("m", str1.substring(12, str1.length()));
                                        mo.put("n", idd);
                                        mo.put("w", mp.get("work").toString());
                                        m.obj = mo;
                                      //  ActivityUiRecog.tf11.handler.sendMessage(m);

                                        sendSMS(str1.substring(1, 12), str1.substring(12, str1.length()), idd);
                                        Test t1 = new Test(getApplication());
                                        t1.updatamessage(str1.substring(1, 12));
                                    }
                                    try {
                                       // sleep(1000);
                                        sleep((int) (mp.get("time")));
                                    } catch (Exception e) {

                                    }
                                }
                            }

                        }
                    }
                    kg2 = true;
                    idd = 0;
                }
            }.start();
        }

    }

    private void sendSMS(String phoneNumber, String message, int i) {
        System.out.println("99--->" + phoneNumber);

            // 获取短信管理器
            android.telephony.SmsManager smsManager = android.telephony.SmsManager
                    .getDefault();
            // 拆分短信内容（手机短信长度限制）
            List<String> divideContents = smsManager.divideMessage(message);
            for (String text : divideContents) {
                smsManager.sendTextMessage(phoneNumber, null, text, null, null);
            }
//        Toast.makeText(this,phoneNumber+": "+message,Toast.LENGTH_SHORT).show();

        }



    @Override
    public void onDestroy() {
        kg = false;
        super.onDestroy();
    }

    public class MyBinder extends Binder implements inservice {

        public mewkservice getService() {
            return mewkservice.this;
        }

        @Override
        public void startwork(int aaa) {
            mewkservice.this.startwork(aaa);
        }

        @Override
        public void pausedwork() {
            mewkservice.this.pausedwork();
        }
    }

    //其他对象通过bindService 方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle = intent.getExtras();

        mp = ((SerializableMap) bundle.get("map")).getMap();
        if (mp.size()!=0) {
            if (mp.get("other").toString().equals("false1")) {
                idd = 0;
            } else {
                idd = Integer.parseInt(mp.get("other").toString());
            }
        }
        return binder;
    }

    //其它对象通过unbindService方法通知该Service时该方法被调用
    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }


}
