package com.baidu.aip.asrwakeup3.uiasr.view;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;


import com.baidu.aip.asrwakeup3.uiasr.Dailog.BDmishiDialog;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.Service.MyAes1;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.MessageView;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;


import java.util.List;

public class DguaTalkActivity extends Activity {
    public static int SCREENHEIGHT;
    public static int SCREENWIDTH;
    public static Mymessage mm;
    DguaEditText det;
    BDmishiDialog dialog22;
    DguaModel mMydate;
    String mishi;
    Boolean mishizt = new Boolean(true);
    MessageView mv;
    private PendingIntent paintent;
    private SmsManager smsManger;
    private Top t1;

    public class Mymessage extends Handler {
        private List<DguaModel> mhs=null;
       private final DguaTalkActivity this$0;
        public Mymessage(DguaTalkActivity dguaTalkActivity, Looper looper) {
            super(looper);
            this.this$0 = dguaTalkActivity;
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what == 111) {
                this.mhs = (List) message.obj;
                System.out.println("asdfggh");
                if (this.mhs != null) {
                    this.this$0.mv.removeAllViews();
                    for (DguaModel dguaModel : this.mhs) {
                        String  decrypt = null;
                        try {
                            decrypt = MyAes1.decrypt(mishi,dguaModel.getmessage());
                            System.out.println("短信详细"+dguaModel.getaddress()+"----"+dguaModel.getmishi()+"----"+decrypt);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (!decrypt.startsWith("?")) {
                          //  this.this$0.mishizt = new Boolean(false);
                           // this.this$0.dialog22 = new BDmishiDialog(this.this$0, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT, R.layout.layout_dialog_ms, R.style.Theme_dialog);
                          //  this.this$0.dialog22.t1.setText("密钥错误，   重新指定", "");
                           // this.this$0.dialog22.show();
                            break;
                        }
                        try {
                            //String str = new String(decrypt, "utf-8");
                            System.out.println(decrypt);
                          //  this.this$0.mv.addView(decrypt, dguaModel.gettab(), "消息：0");
                        } catch (Exception e) {
                        }
                    }
                }
                if (this.this$0.mishizt.booleanValue()) {
                    Intent intent = new Intent();
                    intent.putExtra("what", 0);
                    intent.setAction("com.dgua.jmdx.Service.InMessageData");
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("mMyDate", this.this$0.mMydate);
                    intent.putExtra("bundle", bundle);
                    intent.setPackage(getPackageName());//android 5.0之后必需显性调用
                  //  this.this$0.startService(intent);
                    System.out.println("6886");
                }
            }
            if (message.what == 1111) {
                this.this$0.dialog22.dismiss();
                System.out.println("68");
                this.mhs = (List) message.obj;
                System.out.println("asdfggh");
                if (this.mhs != null) {
                    for (DguaModel dguaModel2 : this.mhs) {
                        this.this$0.mishi = dguaModel2.getmishi();
                        System.out.println(mishi);
                        Intent intent = new Intent();
                        intent.putExtra("what", 4);
                        intent.setAction("com.dgua.jmdx.Service.InMessageData");
                        Bundle bundle2 = new Bundle();
                        bundle2.putParcelable("mMyDate", mMydate);
                        System.out.println(mMydate.getaddress());
                        System.out.println(mhs.toString());
                        intent.putExtra("bundle", bundle2);
                        Bundle a=intent.getParcelableExtra("bundle");
                        DguaModel d=(DguaModel) a.getParcelable("mMyDate");
                        System.out.println("测试传递序列化对象"+mMydate);
                        System.out.println("测试传递序列化对象"+d.getaddress());
                        intent.setPackage(getPackageName());//android 5.0之后必需显性调用
                      //  startService(intent);
                    }
                }
            }
            super.handleMessage(message);
        }
    }
    @Override
    protected void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        requestWindowFeature(1);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREENHEIGHT = displayMetrics.heightPixels;
        SCREENWIDTH = displayMetrics.widthPixels;
        setContentView(R.layout.dialog2);
        this.mv = (MessageView) findViewById(R.id.dialog2MessageView1);
        this.det = (DguaEditText) findViewById(R.id.dialog2DguaEditText1);
        this.det.b.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    send();
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        });
      //  this.dialog22 = new BDmishiDialog(this, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT, R.layout.layout_dialog_ms, R.style.Theme_dialog);
      //  this.dialog22.show();
      //  mm = new Mymessage(this, getMainLooper());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!this.mishizt.booleanValue()) {
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
       // finish();
    }

    @Override
    protected void onResume() {

        this.mMydate = (DguaModel) getIntent().getExtras().getBundle("bundle").getParcelable("mMyDate");
        this.t1 = (Top) findViewById(R.id.dialog2Top1);
        this.t1.setText(this.mMydate.getaddress(), "");
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        try {
            intent.setClass(this, Class.forName("com.dgua.jmdx.ActivityCommon"));
            startActivity(intent);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void send() {
        if (this.det.gettext().length() != 0) {
            this.smsManger = SmsManager.getDefault();
            this.paintent = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
            String ByteToString = null;
            try {
                ByteToString = MyAes1.encrypt(this.mishi,"?"+det.gettext());
                       System.out.println("加密结果" + ByteToString);
           System.out.println("解密结果"+MyAes1.decrypt(this.mishi,ByteToString));
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println( ByteToString+"短信发送"+det.gettext()+this.mishi);
            this.smsManger.sendTextMessage(this.mMydate.getaddress(), (String) null, ByteToString, this.paintent, (PendingIntent) null);
            Message obtainMessage = mm.obtainMessage();
            obtainMessage.what = 888;
            obtainMessage.obj = "发送成功";
            mm.sendMessage(obtainMessage);
            Toast.makeText(this, new StringBuffer().append(this.det.gettext().toString()).append("发送成功！").toString(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("what", 2);
            Time time = new Time();
            time.setToNow();
            Parcelable dguaModel = new DguaModel("out", this.mMydate.getaddress(), mishi, ByteToString, ((((time.month * 100000000) + (time.monthDay * 1000000)) + (time.hour * 10000)) + (time.minute * 100)) + time.second, 1);
            Bundle bundle = new Bundle();
            bundle.putParcelable("mMyDate", dguaModel);
            intent.putExtra("bundle", bundle);
            intent.setAction("com.dgua.jmdx.Service.InMessageData");
            intent.setPackage(getPackageName());//android 5.0之后必需显性调用
            //startService(intent);
            return;
        }
        Message obtainMessage  = mm.obtainMessage();
        obtainMessage.what = 888;
        obtainMessage.obj = "内容为空，发送失败";
        mm.sendMessage(obtainMessage);
        Toast.makeText(this, "发送失败！", Toast.LENGTH_SHORT).show();
    }
}
