package com.baidu.aip.asrwakeup3.uiasr;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import com.baidu.aip.asrwakeup3.uiasr.view.Dgua11;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtV;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaPagerView;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaViewBackground1;
import com.baidu.aip.asrwakeup3.uiasr.view.FragAdapter;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment1;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.TestFragment2;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.Top;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends FragmentActivity {
    public static int SCREENHEIGHT;
    public static int SCREENWIDTH;
    public static DguaViewBackground1 bGround1;
    public static int densityDpi;
    public static Fragment[] fgs=new Fragment[3];
    public static Mymessage mm;
    public static Top t1;
    public static TestFragment tf1;
    public static TestFragment2 tf11;
    public static TestFragment1 tf22;
    private FragAdapter adapter;
    private DguaCtV dct;
    public DguaPagerView pager;
    public  int badgeCount=0;

    public class Mymessage extends Handler {

        public Mymessage(MainActivity mainActivity, Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what == 888) {
                pager.setCurrentItem(0);
            }
            if (message.what == 355) {
                super.handleMessage(message);
            } else {
                super.handleMessage(message);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        //LogCatBroadcaster.start(this);
        super.onCreate(bundle);
        requestWindowFeature(1);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREENHEIGHT = displayMetrics.heightPixels;
        SCREENWIDTH = displayMetrics.widthPixels;
        densityDpi = (int) displayMetrics.ydpi;
        setContentView(R.layout.layout1);
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = (float) ((int) displayMetrics.density);

        double sqrt = Math.sqrt(Math.pow((double) i, (double) 2) + Math.pow((double) i2, (double) 2)) / ((double) (((float) 160) * f));
        mm = new Mymessage(this, getMainLooper());
        t1 = (Top) findViewById(R.id.activitymainTop1);
        t1.setText("新消息", "");

        bGround1 = (DguaViewBackground1) findViewById(R.id.activitymainDguaViewBackground11);
        bGround1.mydgua1_1 = new Dgua11(this, R.drawable.lx1, R.drawable.lx2) {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MainActivity.t1.setText("联系人", "添加");
                    MainActivity.bGround1.setViewColor(1);
                    pager.setCurrentItem(0);
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        bGround1.mydgua1_1.setText("联系人");
        bGround1.addView(bGround1.mydgua1_1);

        bGround1.mydgua1_3 = new Dgua11(this, R.drawable.ms1, R.drawable.ms2) {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MainActivity.bGround1.setViewColor(3);
                    pager.setCurrentItem(2);
                    MainActivity.t1.setText("密钥组", "");
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        bGround1.mydgua1_3.setText("密钥组");
        bGround1.addView(bGround1.mydgua1_3);
        bGround1.mydgua1_2 = new Dgua11( this, R.drawable.hh1, R.drawable.hh2) {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MainActivity.bGround1.setViewColor(2);
                    pager.setCurrentItem(1);
                    MainActivity.t1.setText("新消息", "");
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        bGround1.mydgua1_2.setText("新消息");
        bGround1.addView(bGround1.mydgua1_2);
        bGround1.mydgua1_4 = new Dgua11( this, R.drawable.aaa, R.drawable.aaa2) {

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    MainActivity.bGround1.setViewColor(4);
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        bGround1.mydgua1_4.setText("百宝箱");
        bGround1.addView(bGround1.mydgua1_4);


        tf1=TestFragment.newInstance( R.layout.fglayout, R.id.testText);
         fgs[0] = tf1;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("id", 0);
        fgs[0].setArguments(bundle2);

        tf11=TestFragment2.newInstance( R.layout.fglayout3, R.id.fglayout3DguaCtV1);
        fgs[1] = tf11;
        bundle2 = new Bundle();
        bundle2.putInt("id", 2);
        fgs[1].setArguments(bundle2);

       tf22=TestFragment1.newInstance( R.layout.fglayout2, R.id.message);
        fgs[2] = tf22;
        bundle2 = new Bundle();
        bundle2.putInt("id", 1);
        fgs[2].setArguments(bundle2);
        adapter = new FragAdapter(getSupportFragmentManager(),fgs) ;
        pager = (DguaPagerView) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);
        pager.setCurrentItem(0);
    }


    @Override
    protected void onResume() {
        MainActivity.t1.setText("新消息", "");
        MainActivity.bGround1.setViewColor(2);
        pager.setCurrentItem(1);
        super.onResume();
    }

    @Override
    protected void onStart() {

        ShortcutBadger.removeCount(this);
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }
}

