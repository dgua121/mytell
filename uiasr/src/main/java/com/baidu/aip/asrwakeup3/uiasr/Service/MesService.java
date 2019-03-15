package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.app.IntentService;
import android.content.Intent;

public class MesService extends IntentService {
    public MesService() {
        super("MesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println("qwertyii123456789");
       /* String str = (String) intent.getExtras().get("ssmm");
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        Notification notification = new Notification(R.drawable.ic_launcher, "收到加密信息", System.currentTimeMillis());
        Context applicationContext = getApplicationContext();
        CharSequence charSequence = "收到加密信息";
        CharSequence stringBuffer = new StringBuffer().append(str).append("会话已转移至加密宝").toString();
        try {
            Intent intent2 = new Intent(this, Class.forName("com.dgua.jmdx.MainActivity"));
            intent2.putExtra("ssmm", str);
            notification.setLatestEventInfo(applicationContext, charSequence, stringBuffer, PendingIntent.getActivity(this, 0, intent2, 0));
            notificationManager.notify(1, notification);
        } catch (Throwable e) {
            throw new NoClassDefFoundError(e.getMessage());
        }*/

    }
}
