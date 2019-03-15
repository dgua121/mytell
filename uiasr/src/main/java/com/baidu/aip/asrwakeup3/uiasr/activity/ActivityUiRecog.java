package com.baidu.aip.asrwakeup3.uiasr.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.android.server.telecom.components.in.InDivrmi;
import com.android.server.telecom.components.myandroid.rmi.RpcClient;
import com.android.server.telecom.components.myandroid.rmi.RpcServer;
import com.android.server.telecom.components.out.OutDivMessage;
import com.android.server.telecom.components.out.OutDivrmibaidusdk;
import com.baidu.aip.asrwakeup3.core.recog.IStatus;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.Dialog1;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.PhoneMenuDialog;
import com.baidu.aip.asrwakeup3.uiasr.Dailog.messwork;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.call.commn.addphone;
import com.baidu.aip.asrwakeup3.uiasr.call.tell.call;
import com.baidu.aip.asrwakeup3.uiasr.gj.NetWorkUntil;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.params.AllRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.CommonRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.NluRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.OfflineRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.params.OnlineRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.service.callService;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;
import com.baidu.aip.asrwakeup3.uiasr.setting.AllSetting;
import com.baidu.aip.asrwakeup3.uiasr.setting.NluSetting;
import com.baidu.aip.asrwakeup3.uiasr.setting.OfflineSetting;
import com.baidu.aip.asrwakeup3.uiasr.setting.OnlineSetting;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 识别的基类Activity。 ActivityCommon定义了通用的UI部分
 * 封装了识别的大部分逻辑，包括MyRecognizer的初始化，资源释放
 * <p>
 * <p>
 * 集成流程代码，只需要一句： myRecognizer.start(params);具体示例代码参见startRough()
 * =》.实例化 myRecognizer   new MyRecognizer(this, listener);
 * =》 实例化 listener  new MessageStatusRecogListener(null);
 * </p>
 * 集成文档： http://ai.baidu.com/docs#/ASR-Android-SDK/top 集成指南一节
 * demo目录下doc_integration_DOCUMENT
 * ASR-INTEGRATION-helloworld  ASR集成指南-集成到helloworld中 对应 ActivityMiniRecog
 * ASR-INTEGRATION-TTS-DEMO ASR集成指南-集成到合成DEMO中 对应 ActivityUiRecog
 * <p>
 * 大致流程为
 * 1. 实例化MyRecognizer ,调用release方法前不可以实例化第二个。参数中需要开发者自行填写语音识别事件的回调类，实现开发者自身的业务逻辑
 * 2. 如果使用离线命令词功能，需要调用loadOfflineEngine。在线功能不需要。
 * 3. 根据识别的参数文档，或者demo中测试出的参数，组成json格式的字符串。调用 start 方法
 * 4. 在合适的时候，调用release释放资源。
 * <p>
 */

public abstract class ActivityUiRecog extends ActivityCommon implements IStatus {
    int ii = 0;
    private messwork ms;
    boolean kg = false;
    public inservice myBinder; //这个是我们定义的中间人对象
    private Context ct;
    public static Mymessage mm;
    public static InterstitialAd mInterstitialAd;
    private AdView mAdView;

    public boolean kg2 = true;
    Dialog1 pd;
    OutDivMessage od = null;
    InDivrmi example = null;
    /*
     * Api的参数类，仅仅用于生成调用START的json字符串，本身与SDK的调用无关
     */
    private final CommonRecogParams apiParams;

    public static Class settingActivityClass;

    public class Mymessage extends Handler {

        public Mymessage(ActivityUiRecog mainActivity, Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
           /* if (message.what == 888) {
                pager.setCurrentItem(0);
            }*/
            if (message.what == 355) {
                super.handleMessage(message);
            } else {
                super.handleMessage(message);
            }
        }
    }

    /**
     * 控制UI按钮的状态
     */
    public int status;

    /**
     * 日志使用
     */
    private static final String TAG = "ActivityUiRecog";


    /**
     * 开始录音后，取消这次录音。SDK会取消本次识别，回到原始状态。点击“取消”按钮后调用。
     */
    protected abstract void cancel();

    public static boolean running = false;

    public ActivityUiRecog(int textId) {
        super(textId);
        String className = getClass().getSimpleName();
        if (className.equals("MainActivity") || className.equals("ActivityUiDialog")) {
            settingActivityClass = OnlineSetting.class;
            apiParams = new OnlineRecogParams();
        } else if (className.equals("ActivityOfflineRecog")) {
            settingActivityClass = OfflineSetting.class;
            apiParams = new OfflineRecogParams();
        } else if (className.equals("ActivityNlu")) {
            settingActivityClass = NluSetting.class;
            apiParams = new NluRecogParams();
        } else if (className.equals("ActivityAllRecog")) {
            settingActivityClass = AllSetting.class;
            apiParams = new AllRecogParams();
        } else {
            throw new RuntimeException("PLEASE DO NOT RENAME DEMO ACTIVITY, current name:" + className);
        }
    }

    protected Map<String, Object> fetchParams() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        //  上面的获取是为了生成下面的Map， 自己集成时可以忽略
        Map<String, Object> params = apiParams.fetch(sp);
        //  集成时不需要上面的代码，只需要params参数。
        return params;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiParams.initSamplePath(this);
        ct = getApplication();

        cv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0&&kg2) {
                    System.out.println("按钮按下………");
                    return true;
                }else if (motionEvent.getAction() == 1&&kg2) {
                    System.out.println("按钮松开………");
                    kg2=false;
                    iii();
                    return true;
                } else {
                    return false;
                }
            }

        });

        initsdk();

        inittell();

    }

    @Override
    protected void initView() {
        super.initView();
    }

    protected void handleMsg(Message msg) {
        super.handleMsg(msg);
        switch (msg.what) { // 处理MessageStatusRecogListener中的状态回调
            case STATUS_FINISHED:
                if (msg.arg2 == 1) {
//                    txtResult.setText(msg.obj.toString());
                    System.out.print(msg.obj.toString());
                }
                status = msg.what;
                updateBtnTextByStatus();
                break;
            case STATUS_NONE:
            case STATUS_READY:
            case STATUS_SPEAKING:
            case STATUS_RECOGNITION:
                status = msg.what;
                updateBtnTextByStatus();
                break;
            default:
                break;
        }
        if (msg.what == 999) {
            status = (int) msg.obj;
            switch (status) {
                case STATUS_NONE: // 初始状态
                    start();
                    status = STATUS_WAITING_READY;
                    updateBtnTextByStatus();
                    break;
                case STATUS_WAITING_READY: // 调用本类的start方法后，即输入START事件后，等待引擎准备完毕。
                case STATUS_READY: // 引擎准备完毕。
                case STATUS_SPEAKING: // 用户开始讲话
                case STATUS_FINISHED: // 一句话识别语音结束
                case STATUS_RECOGNITION: // 识别中
                    stop();
                    status = STATUS_STOPPED; // 引擎识别中
                    updateBtnTextByStatus();
                    break;
                case STATUS_LONG_SPEECH_FINISHED: // 长语音识别结束
                case STATUS_STOPPED: // 引擎识别中
                    cancel();
                    status = STATUS_NONE; // 识别结束，回到初始状态
                    updateBtnTextByStatus();
                    break;
                default:
                    break;
            }
        }

        if (msg.what == 1001) {

            if (myBinder != null) {
                ii++;
                cv.setProgress(ii);
                myBinder.startwork(1);
            }
        }
        if (msg.what == 1002) {
            if (myBinder != null) {


                myBinder.pausedwork();

            }
            send((OutDivMessage) msg.obj);
            iii();


        }

    }

    public void send(final OutDivMessage o) {

        if (NetWorkUntil.isWifi(ct)) {

            example = RpcClient.lookupService("106.12.203.91", 6790, "example", InDivrmi.class);

            try {

                new Thread() {
                    public void run() {
                        example.updata(o);

                    }
                }.start();


            } catch (Exception e) {
                e.printStackTrace();

            }


        }
    }

    private void updateBtnTextByStatus() {
        switch (status) {
            case STATUS_NONE:
                break;
            case STATUS_WAITING_READY:
            case STATUS_READY:
            case STATUS_SPEAKING:
            case STATUS_RECOGNITION:
                break;
            case STATUS_LONG_SPEECH_FINISHED:
            case STATUS_STOPPED:
                break;
            default:
                break;
        }
    }

    public void inittell() {
        //初始化tell服务器
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (!kg) {

                    if (NetWorkUntil.isWifi(ct)) {

                        new sdkService(ct);

                        kg = true;
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

    public class sdkService implements OutDivrmibaidusdk {

        //Context ct;

        public sdkService(Context a) {
            //  ct = a;
            System.out.println("服務器東");
            try {
                // create the RMI server
                RpcServer rpcServer = new RpcServer();
                // register a service under the name example
                // the service has to implement an interface for the magic to work
                rpcServer.registerService("sdk", this);
                // start the server at port 6789
                rpcServer.start(6792);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("服務qidong------over");
            }
        }


        @Override
        public int endsdk() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (myBinder != null) {


                myBinder.pausedwork();

            }

            return 0;
        }


        @Override
        public int startwork(OutDivMessage outDivMessage) {

        /*Intent intent1 = new Intent(ct, callService.class);
        ct.startService(intent1);// 启动电话服务*/
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (myBinder != null) {
                ii++;
                cv.setProgress(ii);
                myBinder.startwork(1);
            }

            return 0;
        }
    }

    public void iii() {

        if (NetWorkUntil.isWifi(ct)) {

            example = RpcClient.lookupService("106.12.203.91", 6790, "example", InDivrmi.class);

            try {
                sleep(1267);
                new Thread() {
                    public void run() {

                        od = example.gettwork();
                        if (od != null) {
                            new addphone(ct).addContact(od.getname(), od.getphone());

                            new call(ct).start(od.getphone());

                        } else {
                            System.out.println("9999999999999999999999");
                        }
                    }
                }.start();


            } catch (Exception e) {
                e.printStackTrace();

            }


        }
    }

    public void initsdk() {
        Intent intent = new Intent(ct, callService.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        startService(intent);// 启动电话服务
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

    public void onStart() {
        super.onStart();
        if (od != null) {
            pd = new Dialog1(this, ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENHEIGHT * 3 / 10, R.layout.layout_dialog, R.style.dialog1);

            pd.show();
            pd.setCancelable(false);
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message msg1 = new Message();
            msg1.what = 1111;
            msg1.obj = od;
            Dialog1.handler.sendMessage(msg1);

        }
        //
    }


}
