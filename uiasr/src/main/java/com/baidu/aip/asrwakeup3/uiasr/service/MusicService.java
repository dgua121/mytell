package com.baidu.aip.asrwakeup3.uiasr.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;

public class MusicService extends Service {
    //定义音乐播放器变量
    private MediaPlayer mPlayer;
    int name1;
    private final IBinder binder = new MyBinder();

    //该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动时调用该方法
    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("启动。。。。。。。。。。。。。。");
        return super.onStartCommand(intent, flags, startId);
    }

    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }
    };
    MediaPlayer.OnCompletionListener ple = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Message msg = Message.obtain();
            msg.what = 121;
            callService.handler.sendMessage(msg);
            onDestroy();
        }
    };

    @Override
    public void onDestroy() {
        System.out.println("结束d");
        if ((mPlayer != null)) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        super.onDestroy();
    }


    public class MyBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }

    //其他对象通过bindService 方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        int name1 = intent.getIntExtra("name", 7);
        System.out.println("播放音乐。。。。。。。。。。。。。。。。。。。");
        try {
            if (mPlayer != null) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
            }
            mPlayer = MediaPlayer.create(MusicService.this, name1);
            // mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
            if (mPlayer.isPlaying()) {//确保在prepare()之前调用了stop()
                mPlayer.stop();
                mPlayer.release();
            }
            //在线播放音频，使用prepareAsync()
            mPlayer.setOnPreparedListener(preparedListener);
            mPlayer.setOnCompletionListener(ple);
            //  mPlayer.prepareAsync();//在线播放音频，使用prepareAsync()// greate()已经prepare 过了
            mPlayer.setLooping(false);//设置不循环播放
        } catch (Exception e) {

        }
        return binder;
    }

    //其它对象通过unbindService方法通知该Service时该方法被调用
    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("结束u");
        if (mPlayer != null || mPlayer.isPlaying()) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        return super.onUnbind(intent);
    }

    public void pausedmusic() {
        if (mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }

    public void startmusic() {
        if (!mPlayer.isPlaying()) {
            mPlayer.start();
        }
    }


}