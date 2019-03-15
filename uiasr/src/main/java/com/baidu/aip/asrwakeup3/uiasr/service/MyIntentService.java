package com.baidu.aip.asrwakeup3.uiasr.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;


import com.baidu.aip.asrwakeup3.uiasr.R;

import java.util.Timer;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService implements MediaPlayer.OnCompletionListener {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.dgua.myapplication.activity.mini.action.FOO";
    private static final String ACTION_BAZ = "com.example.dgua.myapplication.activity.mini.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.dgua.myapplication.activity.mini.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.dgua.myapplication.activity.mini.extra.PARAM2";
    private MediaPlayer mediaPlayer;
    public int onStartCommand(Intent intent, int flag, int startId) {
        // TODO Auto-generated method stub
        super.onStartCommand(intent, flag, startId);
        String name = intent.getStringExtra("name");
         Timer mTimer = new Timer(); // 计时器
        if (!name.equals("12321")) {


            } else if (name.equals("12321"))

            {
                mediaPlayer = MediaPlayer.create(this, R.raw.kcb);
                mediaPlayer.setOnCompletionListener(this);

                if (!mediaPlayer.isPlaying()) {

                       // mediaPlayer.prepare();
                        mediaPlayer.start();
                        // 允许循环播放
                        mediaPlayer.setLooping(false);

                    // 开始播放

                }

            }
            return START_STICKY;
        }



    public void onCompletion(MediaPlayer player) {
        // TODO Auto-generated method stub
        // 结束Service
        onDestroy();
        stopSelf();
    }

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {

        //mediaPlayer.stop();
       // mediaPlayer.release();
       // mediaPlayer.reset();
        super.onDestroy();
    }
}
