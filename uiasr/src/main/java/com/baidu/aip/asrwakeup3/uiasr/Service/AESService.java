package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AESService extends IntentService {
    @Override
    protected void onHandleIntent(Intent intent) {
    }

    public AESService() {
        super("AESService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) null;
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        Bundle bundleExtra = intent.getBundleExtra("aaa");
        String string = bundleExtra.getString("mess");
        String string2 = bundleExtra.getString("key");
        int i3 = bundleExtra.getInt("sta");
        if (i3 == 1) {
            try {
                MyAes1.encrypt(string, string2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i3 == 2) {
            try {
                MyAes1.decrypt( string,string2);
            } catch (Exception e) {
                e.printStackTrace();
            }
             //MyAes1.decrypt( string2,ByteOrStringHelper.StringToByte(string));
        }
        return super.onStartCommand(intent, i, i2);
    }
}
