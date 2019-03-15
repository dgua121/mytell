package com.baidu.aip.asrwakeup3.uiasr.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.baidu.aip.asrwakeup3.uiasr.R;

public class MymusicService extends Service {
    private MediaPlayer mediaPlayer;
    int op;
    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }
    };
    MediaPlayer.OnCompletionListener ple = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {

            if (op== R.raw.kcb4||op==R.raw.kcb12){
                Message msg1 = Message.obtain();
                msg1.what = 125;
                callService.handler.sendMessage(msg1);
            }else {
                Message msg11 = Message.obtain();
                msg11.what = 121;
                callService.handler.sendMessage(msg11);
            }
        }
    };
    public MymusicService() {
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("guanbi..................");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {

             op = intent.getIntExtra("name", 7);

            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, op);
                mediaPlayer.setOnPreparedListener(preparedListener);
                mediaPlayer.setOnCompletionListener(ple);
                mediaPlayer.setLooping(false);
                System.out.println("QIDONG....................");
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }
}
