package com.baidu.aip.asrwakeup3.uiasr.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.server.telecom.components.in.wximage;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gongju.AuthService;
import com.baidu.aip.asrwakeup3.core.inputstream.InFileStream;
import com.baidu.aip.asrwakeup3.core.util.MyLogger;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.gongju.WifiTool;
import com.baidu.aip.asrwakeup3.uiasr.service.callService;
import com.baidu.aip.asrwakeup3.uiasr.service.mewkservice;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaPagerView;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaViewBackground1;
import com.baidu.aip.asrwakeup3.uiasr.view.FragAdapter;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment1;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment2;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class ActivityCommon extends FragmentActivity {
    public static Top t1;
    protected TextView txtLog;
    protected Button btn;
    protected Button setting;
    protected TextView txtResult;
    public static String tockn;
    public static Handler handler;
    public static String session = "";
    protected final int layout;
    public Socket socket = null;
    public static volatile String str = "h";//qingliangjitongbu
    public int index = 0, count = 0;
    public static DguaViewBackground1 bGround1;
    public static int SCREENHEIGHT;
    public static int SCREENWIDTH;
    public static int densityDpi;
   /* public FragAdapter adapter;
    public DguaPagerView pager;
    public static Fragment[] fgs = new Fragment[3];
    public static TestFragment tf1;
    public static TestFragment2 tf22;
    public static TestFragment1 tf11;*/
    Intent intent;
    private SoundPool sp;
    private HashMap<Integer, Integer> hm;
    private float volume;
    private int currentID;
    public ImageView iv;
boolean k=false;
    public static CircleProgressView cv;
    public ActivityCommon(int textId) {
        this(textId, R.layout.common);
    }

    public ActivityCommon(int textId, int layout) {
        super();
        this.layout = layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setStrictMode();
        InFileStream.setContext(this);
        setContentView(layout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREENHEIGHT = displayMetrics.heightPixels;
        SCREENWIDTH = displayMetrics.widthPixels;
        densityDpi = (int) displayMetrics.ydpi;
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = (float) ((int) displayMetrics.density);
        double sqrt = Math.sqrt(Math.pow((double) i, (double) 2) + Math.pow((double) i2, (double) 2)) / ((double) (((float) 160) * f));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                handleMsg(msg);
            }
        };
        cv=findViewById(R.id.circleProgressView);
        iv = findViewById(R.id.imageView);
        t1 = (Top) findViewById(R.id.activitymainTop111);
        t1.setText("自动拨号", "配置");
        t1.setNU(0);
        t1.dg.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    System.out.println("按钮按下………");
                    if (ActivityUiRecog.settingActivityClass != null) {
                        ActivityUiRecog.running = true; // 是否该Activity依旧需要运行
                        Intent intent = new Intent(getApplicationContext(), ActivityUiRecog.settingActivityClass);
                        startActivityForResult(intent, 1);
                    }

                    return true;
                } else if (motionEvent.getAction() == 1) {
                    System.out.println("按钮松开………");
                    return true;
                } else {
                    System.out.println("按钮………");

                    return false;
                }
            }
        });


        MyLogger.setHandler(handler);
        initPermission();
        AuthService.getAuth();

      /*  initwifi();

        intent = new Intent(this, mewkservice.class);
        startService(intent);// 启动短信服务*/
    }

    public  void sethart() {
        if (k) {
            System.out.println("tell------hart111");
            cv.setRadius(270);
            cv.setStokewidth(40);
            cv.setTextSize(50);
            cv.setColor(Color.GRAY, Color.RED, Color.BLUE);
            cv.setSpeed(20);
            // cv.setProgress(1);
            //cv.startProgress();
            k = false;
        } else {
            System.out.println("tell------hart222");
            cv.setRadius(240);
            cv.setStokewidth(40);
            cv.setTextSize(50);
            cv.setColor(Color.RED, Color.RED, Color.BLUE);
            cv.setSpeed(20);
            // cv.setProgress(2);
            //cv.startProgress();
            k = true;
        }
    }


  /*  private void initwifi(){

        WifiTool wi = new WifiTool();
        wi.getServerIP();

    }*/

  /*  public void nextnbms(final int mss1, final String ssss) {

        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    index = index + 1;
                    String str1 = "Q" + new Test(ActivityCommon.this).queryDataname(index) + ssss;
                    System.out.println(str1);
                    if (str1.contains("Q")) {
                        System.out.println("888990" + str1.length());
                        if (str1.length() >= 12) {
                            //sendSMS(str1.substring(1, 12), str1.substring(12, str1.length()));
                            t1.setNU((int) (index));
                            System.out.println(str1);
                        }

                        try {
                            sleep(mss1);
                        } catch (Exception e) {
                        }
                    } else {
                        break;
                    }

                }
            }
        }.start();
    }*/

   /* private void initSoundPool() {
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        hm = new HashMap<Integer, Integer>();
        hm.put(1, sp.load(getApplicationContext(), R.raw.kcb, 1));

    }*/

   /* private void playSound(int num, int loop) {
        AudioManager am = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float currentSound = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxSound = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = currentSound / maxSound;
        currentID = sp.play(hm.get(num), volume, volume, 1, loop, 1.0f);
    }
*/
    protected abstract void start();

    protected void stop() {
        session = "";
    }

    protected void handleMsg(Message msg) {
       /* if (msg.what == 888) {
          //  pager.setCurrentItem(0);
        }*/
        if (msg.what == 10000) {
            tockn = msg.obj.toString();
        }
        if (msg.what == 1045) {
           sethart();
           wximage wx=(wximage) msg.obj;
           byte[] ff=wx.bimp;
           if (ff==null){
               iv.setVisibility(View.GONE);
               t1.setText("微信UP","配置");
           }else{
               try {
                   t1.setText("请扫码","配置");
                   byte[] in = ff;
                   Bitmap bitmap = BitmapFactory.decodeByteArray(in, 0, in.length);
                   iv.setImageBitmap(bitmap);
                   iv.setVisibility(View.VISIBLE);


               } catch (Exception e) {
                   e.printStackTrace();
               }
           }

        }
       /* if (msg.what == 666) {
            //nextnbms(msg.arg1, msg.obj.toString());//发送短信
        }*/
        //服务器连接成功
        if (msg.what == 123) {
            socket = (Socket) msg.obj;
            if (socket != null) {
                //  new hart();
                new Thread() {
                    public void run() {
                        PrintWriter pw = null;
                        BufferedReader br = null;
                        try {
                            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                            while (true && pw != null && br != null) {
                                String clientInputStr = br.readLine();
                               // System.out.println("收到--------" + clientInputStr);
                                if (!(clientInputStr.equals("h") || clientInputStr.equals("b"))) {
                                    if (clientInputStr.equals("ACTIVE")) {
                                        //playSound(1, 0);
                                    }
                                    Message msg = new Message();
                                    msg.what = 130;
                                    msg.obj = clientInputStr;
                                    callService.handler.sendMessage(msg);
                                    System.out.println(clientInputStr);
                                    count = 0;
                                    System.out.println("发送--------b");
                                    pw.println("b");
                                    pw.flush();
                                    str = "h";
                                } else if (clientInputStr.equals("b")) {
                                    System.out.println("发送------------h");
                                    pw.println("h");
                                    pw.flush();
                                    str = "h";
                                } else if (clientInputStr.equals("h")) {
                                    try {
                                        //System.out.println("fasong-----------" + str);
                                        pw.println(str);
                                        pw.flush();
                                        //str = "h";
                                    } catch (Exception e) {
                                        count = 0;
                                    }
                                }

                            }

                        } catch (Exception e) {
                            pw.close();
                            if (br != null) {
                                try {
                                    br.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }

                            }
                        }
                    }

                }.start();
            }
        }
    }

   /* private class hart implements Runnable {
        @Override
        public void run() {
            while (true) {
                count = 0;
                count++;
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count > 3) {
                    System.out.println("断开连接");
                    // socket=null;
                    break;
                }
            }
        }
    }*/

    protected abstract void cancel();

    protected void initView() {

    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.INTERNET,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.SEND_SMS,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    private void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());

    }
/*

    //发短信
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("99" + phoneNumber);
        // 获取短信管理器
        android.telephony.SmsManager smsManager = android.telephony.SmsManager
                .getDefault();
        // 拆分短信内容（手机短信长度限制）
        List<String> divideContents = smsManager.divideMessage(message);
        for (String text : divideContents) {
            smsManager.sendTextMessage(phoneNumber, null, text, null, null);
        }
        System.out.println("88899" + phoneNumber);
    }
*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }
}