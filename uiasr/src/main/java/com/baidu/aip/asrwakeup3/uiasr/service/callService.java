package com.baidu.aip.asrwakeup3.uiasr.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.android.server.telecom.components.in.InDeivMessage;
import com.android.server.telecom.components.in.InDivrmi;
import com.android.server.telecom.components.in.wximage;
import com.android.server.telecom.components.myandroid.rmi.RpcClient;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.Dialog1;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.NetWorkUntil;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gongju.HttpUtil;
import com.baidu.aip.asrwakeup3.uiasr.gongju.unitData;
import com.baidu.aip.asrwakeup3.uiasr.save.TestClient;
import com.baidu.aip.asrwakeup3.uiasr.save.TestClient1;

import org.json.JSONArray;
import org.json.JSONObject;

public class callService extends Service {
    int index = 0;
    private final IBinder binder = new callService.MyBinder();
    String session = "";
    String phone;
    String ip="192.168.0.115";
    String name = "kehu";
    String sort = "C";
    int ccc = 0;
    public boolean kg = true;
    public boolean kg1 = false;
    private Context ct;
    Intent intent3 = new Intent();
    public static Handler handler;
    private int play = 1;
    InDivrmi example;
    private void handleMsg(Message msg) {

        if (msg.what == 1111) {
            nextnb();//拨打下一个电话playSound
        }
        //服务器端返回消息
        if (msg.what == 130 && msg.obj != null) {
            String clientInputStr = msg.obj.toString();
            if (clientInputStr.equals("over")) {

                System.out.println("对方电话挂断");
                //电话挂断状态
                over();
                stopService(intent3);
                //结束识别
                ccc = 0;

            } else if (clientInputStr.equals("ALERTING")) {

                //电话呼出成功

            } else if (clientInputStr.equals("ACTIVE")) {
                play = 1;
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(4000);
                            open();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                }.start();

                System.out.println("开始开场白");
            }
        }
        //停止播放音乐
        if (msg.what == 121) {
            System.out.println("中断，，，，，，，，，，，，，");
            if (!(play == 4 || play == 12)) {
                //stopService(intent3);

              // new TestClient1(getApplication(),ip,"stop");

            }
        }
        //本端主动挂断电话
        if (msg.what == 125) {
            ActivityCommon.str = "over";//挂断电话
            ccc = 0;
            System.out.println("挂断电话。。。。。。。。。。。。。");
        }

        if (msg.what == 100001) {
            final String ss = msg.obj.toString();

            try {
                new Thread() {
                    public void run() {
                        InDeivMessage idm1=new InDeivMessage("kcb88.wav",1);
                        example.startwork(idm1);

                    }}.start();
                new Thread() {
                    public void run() {

                        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/bot/chat";
                        String str1 = new unitData().data(ss, phone + index, phone + index, session);
                        String result1 = null;
                        String sss = "";
                        try {
                            result1 = HttpUtil.post(talkUrl, ActivityCommon.tockn, "application/json", str1);
                            JSONObject jsonObject = new JSONObject(result1);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("result"));
                            JSONObject jsonObject11 = new JSONObject(jsonObject1.getString("response"));
                            JSONArray jsonArray2 = new JSONArray(jsonObject11.getString("action_list"));
                            JSONObject jsonObject3 = new JSONObject(jsonArray2.get(0).toString());
                            sss = jsonObject3.getString("say");
                            session = (new JSONObject(jsonObject1.getString("bot_session"))).toString();
                            String aw = jsonObject3.getJSONObject("refine_detail").getString("interact");
                            if (aw.equals("select")) {
                                String aww = jsonObject3.getJSONObject("refine_detail").getJSONArray("option_list").getJSONObject(0).getString("option");
                                String str11 = new unitData().data(aww, phone + index, phone + index, session);
                                result1 = HttpUtil.post(talkUrl, ActivityCommon.tockn, "application/json", str11);
                                JSONObject jsonObject10 = new JSONObject(result1);
                                JSONObject jsonObject110 = new JSONObject(jsonObject10.getString("result"));
                                JSONObject jsonObject111 = new JSONObject(jsonObject110.getString("response"));
                                JSONArray jsonArray21 = new JSONArray(jsonObject111.getString("action_list"));
                                JSONObject jsonObject31 = new JSONObject(jsonArray21.get(0).toString());
                                sss = jsonObject31.getString("say");
                                System.out.println(sss);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (sss.equals("需要多少钱？")) {
                            sort = "B";
                            // playSound(8, 0);
                            play = 8;

                            InDeivMessage idm=new InDeivMessage("kcb8.mp3",1);
                            example.startwork(idm);
                           /* intent3.putExtra("name", R.raw.kcb8);
                            new TestClient1(getApplication(),ip,"kcb8.mp3");
*/
                            System.out.println("开始对话a");

                        } else if (sss.equals("这要根据你资产和负债才能算出来这个没问题的，那你名下有没有按揭房或者保单车子？")) {
                            sort = "B";
                            //playSound(2, 0);
                            play = 2;
                            InDeivMessage idm=new InDeivMessage("kcb2.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");

                        } else if (sss.equals("请澄清一下：打工")) {
                            sort = "B";
                            //playSound(3, 0);
                            play = 3;
                            InDeivMessage idm=new InDeivMessage("kcb3.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("没事的可以了解下")) {
                            sort = "C";
                            play = 4;
                            InDeivMessage idm=new InDeivMessage("kcb4.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("利息是根据你资质来定的")) {
                            sort = "A";
                            //playSound(5, 0);
                            play = 5;
                            InDeivMessage idm=new InDeivMessage("kcb5.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("手续费肯定有的我们是按照银行规定收取的")) {
                            sort = "A";
                            //playSound(6, 0);
                            play = 6;
                            InDeivMessage idm=new InDeivMessage("kcb6.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("请澄清一下：打卡工资社保")) {
                            sort = "B";
                            play = 10;
                            InDeivMessage idm=new InDeivMessage("kcb10.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("请澄清一下：营业执照")) {
                            sort = "B";
                            //playSound(11, 0);
                            play = 11;
                            InDeivMessage idm=new InDeivMessage("kcb11.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else if (sss.equals("嗯你情况我这边也了解了，我让我们经理给你回电话吧，他更加专业")) {
                            sort = "A";
                            play = 12;
                            InDeivMessage idm=new InDeivMessage("kcb12.mp3",1);
                            example.startwork(idm);
                            System.out.println("开始对话a");
                        } else {
                            ccc++;
                            if (ccc <= 1) {
                                play = 7;
                                //intent3.putExtra("name", R.raw.kcb7);
                                InDeivMessage idm=new InDeivMessage("kcb7.mp3",1);
                                example.startwork(idm);
                               // example.startwork()
                                //new TestClient1(getApplication(),ip,"kcb7.mp3");
                                //startService(intent3);
                                System.out.println("开始对话a 没有听清");
                            } else {
                                // over();
                                play = 12;
                                InDeivMessage idm=new InDeivMessage("kcb12.mp3",1);
                                example.startwork(idm);
                            }
                        }
                    }
                }.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class MyBinder extends Binder implements inservice {

        public callService getService() {
            return callService.this;
        }

        @Override
        public void startwork(int a) {
            callService.this.startwork();
        }

        @Override
        public void pausedwork() {
            callService.this.pausedwork();
        }
    }

    public callService() {
    }



    //启动识别
    private void open() {

        System.out.println("启动识别");
        Message msg1 = new Message();
        msg1.what = 999;
        msg1.obj = ActivityUiRecog.STATUS_NONE;
        ActivityUiRecog.handler.sendMessage(msg1);//启动识别引擎
    }

    //关闭识别
    private void over() {
        System.out.println("关闭识别");
        Message msg1 = new Message();
        msg1.what = 999;
        msg1.obj = ActivityUiRecog.STATUS_STOPPED;
        ActivityUiRecog.handler.sendMessage(msg1);//关闭识别引擎
        new Thread() {
            @Override
            public void run() {
                new TestClient(getApplicationContext(), name, sort, phone);
                super.run();
            }
        }.start();
    }

    public void nextnb() {
        index = index + 1;
        sort = "C";
        ccc = 0;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ActivityCommon.str = new Test(getApplication()).queryData2();
                phone = ActivityCommon.str;
                System.out.println(phone);
                session = "";
            }
        }.start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ct = getApplication();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }
        };
        inittell();
    }

    public void inittell() {

        System.out.println("初始化tell服务器");
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    System.out.println("初始化tell服务器11111111111");
                    if (NetWorkUntil.isWifi(ct)) {
                        System.out.println("初始化tell服务器222222222");

                        String a = NetWorkUntil.getLocalIpAddress(ct);
                        example = RpcClient.lookupService("106.12.203.91", 6790, "example", InDivrmi.class);
                        System.out.println("初始化tell服务器3333333");
                        example.updata(a, 3, 300);

                        while(NetWorkUntil.isWifi(ct)){
                            try {
                                sleep(1267);

                               wximage wx =example.hart(3, 300);

                                Message msg1 = new Message();
                                msg1.what = 1045;
                                msg1.obj = wx;
                                ActivityCommon.handler.sendMessage(msg1);

                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        }

                    }
                    try {
                        sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();
    }


    private void startwork() {

        /*intent3.setClass(this, MymusicService.class);
        intent3.setPackage(getPackageName());
        nextnb();*/
        open();
    }

    private void pausedwork() {
        over();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {

        return binder;

    }
}
