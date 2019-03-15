package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.text.format.Time;
import android.util.Log;


import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;

import me.leolin.shortcutbadger.ShortcutBadger;

public class Broadcast extends BroadcastReceiver {
    String number;
    PendingIntent paintent;
    String sm = "";
    SmsManager smsManger;

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("短信处理");
        this.smsManger = SmsManager.getDefault();
        this.paintent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        Object[] objArr = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objArr) {
            SmsMessage createFromPdu = SmsMessage.createFromPdu((byte[]) obj);
            this.number = createFromPdu.getOriginatingAddress();
            this.sm = new StringBuffer().append(this.sm).append(createFromPdu.getMessageBody()).toString();
            Log.i("qwe", this.sm);
        }
       // System.out.println(new StringBuffer().append("信息字数").append(ByteOrStringHelper.StringToByte(this.sm).length).toString());

        if (sm.matches("^[A-Fa-f0-9]+$")) {

            try {
                Intent intent2 = new Intent(context, Class.forName("com.dgua.jmdx.Service.MesService"));
                intent2.putExtra("ssmm", this.number);
                //context.startService(intent2);
                Intent intent3 = new Intent();
                intent3.putExtra("what", 2);
                Time time = new Time();
                time.setToNow();
                Parcelable dguaModel = new DguaModel("in", this.number.substring(number.length()-11), "", this.sm, time.second + ((((time.month * 100000000) + (time.monthDay * 1000000)) + (time.hour * 10000)) + (time.minute * 100)), 0);
                Bundle bundle = new Bundle();
                bundle.putParcelable("mMyDate", dguaModel);
                intent3.putExtra("bundle", bundle);
                intent3.setAction("com.dgua.jmdx.Service.InMessageData");
                intent3.setPackage(context.getPackageName());//android 5.0之后必需显性调用
                context.startService(intent3);

                ShortcutBadger.applyCount(context, 1);

            } catch (Throwable e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
    }
}
