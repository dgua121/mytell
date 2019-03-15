package com.baidu.aip.asrwakeup3.uiasr.call.tell;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class call {
    Context ct;
    public call(Context c){
        ct=c;
    }
    public void start(String a){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + a);
        intent.setData(data);
        ct.startActivity(intent);

    }
}
