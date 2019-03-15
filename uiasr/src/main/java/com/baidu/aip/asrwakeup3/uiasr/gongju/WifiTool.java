package com.baidu.aip.asrwakeup3.uiasr.gongju;

import android.os.Message;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.example.wifi.ScanDeviceTool;

import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.sleep;

public class WifiTool {

    private InetAddress HOST;
    public Socket socket;
    public boolean isConnected;
    public List<String> pList;
    private static final int PORT = 12345;
    private BufferedReader in;
    private ExecutorService mExecutorService = null;
    String line;
    public boolean kg = true;

    public boolean getServerIP() {
        boolean isConnected = false;
        new Thread() {
            @Override
            public void run() {
                ScanDeviceTool scanDeviceTool = new ScanDeviceTool();
                pList = scanDeviceTool.scan();//调用工具类方法开始扫描

                if (pList != null && pList.size() > 0) {

                    for (final String ip : pList) {


                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    HOST = InetAddress.getByName(ip);
                                    socket = new Socket(HOST, PORT);
                                    socket.setSoTimeout(30000);

                                    if (socket != null) {//如果无法建立连接，socket将为空
                                        Message msg = Message.obtain();
                                        msg.what = 123;
                                        msg.obj = socket;
                                        ActivityCommon.handler.sendMessage(msg);
                                        System.out.println("连接成功");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                }
            }
        }.start();
        return isConnected;
    }
}
