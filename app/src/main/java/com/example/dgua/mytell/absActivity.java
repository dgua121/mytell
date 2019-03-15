package com.example.dgua.mytell;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.baidu.aip.asrwakeup3.core.mini.AutoCheck;
import com.baidu.aip.asrwakeup3.core.recog.MyRecognizer;
import com.baidu.aip.asrwakeup3.core.recog.listener.IRecogListener;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.Message2StatusRecogListener;
import com.baidu.aip.asrwakeup3.uiasr.params.OfflineRecogParams;
import com.baidu.aip.asrwakeup3.uiasr.service.inservice;

import java.util.Map;

/**
 * 识别activity 主要逻辑。
 * ActivityUiRecog为UI部分，不必细看
 */
public abstract class absActivity extends ActivityUiRecog {

    boolean kg = false;
    public inservice myBinder; //这个是我们定义的中间人对象
    private Context ct;

    /**
     * 识别控制器，使用MyRecognizer控制识别的流程
     */
    protected MyRecognizer myRecognizer;

    /*
     * 本Activity中是否需要调用离线命令词功能。根据此参数，判断是否需要调用SDK的ASR_KWS_LOAD_ENGINE事件
     */
    protected boolean enableOffline;

    private static final String TAG = "ActivityAbstractRecog";

    /**
     * @param textId        界面上的说明文字
     * @param enableOffline 展示的activity是否支持离线命令词
     */
    public absActivity(int textId, boolean enableOffline) {
        super(textId);
        this.enableOffline = enableOffline;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DEMO集成步骤 1.1 新建一个回调类，识别引擎会回调这个类告知重要状态和识别结果
        IRecogListener listener = new Message2StatusRecogListener(handler,this);

        // DEMO集成步骤 1.2 初始化：new一个IRecogListener示例 & new 一个 MyRecognizer 示例
        myRecognizer = new MyRecognizer(this, listener);
        if (enableOffline) {
            // 集成步骤 1.3（可选）加载离线资源。offlineParams是固定值，复制到您的代码里即可
            Map<String, Object> offlineParams = OfflineRecogParams.fetchOfflineParams();
            myRecognizer.loadOfflineEngine(offlineParams);
        }

        //ct=this;
       // initsdk();
    }

    /**
     * 开始录音，点击“开始”按钮后调用。
     */
    protected void start() {

        // DEMO集成步骤2.1 拼接识别参数： 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
        final Map<String, Object> params = fetchParams();
        // params 也可以根据文档此处手动修改，参数会以json的格式在界面和logcat日志中打印

        // 复制此段可以自动检测常规错误
        (new AutoCheck(getApplicationContext(), new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainErrorMessage(); // autoCheck.obtainAllMessage();
                        //      txtLog.append(message + "\n");
                        ; // 可以用下面一行替代，在logcat中查看代码
                        // Log.w("AutoCheckMessage", message);
                    }
                }
            }
        }, enableOffline)).checkAsr(params);

        // 这里打印出params， 填写至您自己的app中，直接调用下面这行代码即可。
        // DEMO集成步骤2.2 开始识别
        myRecognizer.start(params);
    }

    /**
     * 开始录音后，手动点击“停止”按钮。
     * SDK会识别不会再识别停止后的录音。
     */
    protected void stop() {
        // DEMO集成步骤4 (可选） 停止录音
        myRecognizer.stop();
    }

    /**
     * 开始录音后，手动点击“取消”按钮。
     * SDK会取消本次识别，回到原始状态。
     */
    protected void cancel() {
        // DEMO集成步骤5 (可选） 取消本次识别
        myRecognizer.cancel();
    }

    /**
     * 销毁时需要释放识别资源。
     */
    @Override
    protected void onDestroy() {
        // DEMO集成步骤3 释放资源
        // 如果之前调用过myRecognizer.loadOfflineEngine()， release()里会自动调用释放离线资源
        myRecognizer.release();
        Log.i(TAG, "onDestory");
        super.onDestroy();
    }


   /* public void inittell() {
        //初始化tell服务器
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (!kg) {

                    if (NetWorkUntil.isWifi(ct)) {
                        // new tellServer(getApplication());
                        new sdkService(ct);
                        new Thread(){
                            @Override
                            public void run() {
                                super.run();
                                String a = NetWorkUntil.getLocalIpAddress(ct);
                                InDivrmi example = RpcClient.lookupService("192.168.0.112", 6790, "example", InDivrmi.class);

                                example.updata(a, 3, 333);

                                while(NetWorkUntil.isWifi(ct)){
                                    try {
                                        sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                    example.hart(3, 333);
                                }
                            }
                        }.start();

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
*/
    public void initmessage(){

    }


    /*public class sdkService implements OutDivrmibaidusdk {

        Context ct;

        public sdkService(Context a) {
            ct = a;
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

        *//*Intent intent1 = new Intent(ct, callService.class);
        ct.startService(intent1);// 启动电话服务*//*
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (myBinder != null) {
                myBinder.startwork(1);
            }

            return 0;
        }
    }
*/
}