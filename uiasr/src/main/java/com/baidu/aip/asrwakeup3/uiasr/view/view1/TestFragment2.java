package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;


import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;


public class TestFragment2 extends Fragment {
    public Context ct;
    public Handler handler = new Handler() {
        private String[] arr;
        private List<DguaModel> mhs;
        private DguaModel[] mydata;

        @Override
        public void handleMessage(Message message) {
            if (textView != null && getArguments().getInt("id") == 2) {
                ActivityUiRecog.t1.dg.setOnTouchListener(new OnTouchListener() {


                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1) {
                            return true;
                        }
                        return false;
                    }
                });

                if (message.what == 111) {
                    this.mhs = (List) message.obj;
                    if (this.mhs != null) {
                        System.out.println("58558585585");
                        ((DguaCtV) textView).removeAll();
                        int badgeCount = 1;
                        for (DguaModel dguaModel : this.mhs) {
                            //((DguaCtV) textView).addView("New", dguaModel.getaddress(), "");
                            badgeCount++;
                        }

                        ShortcutBadger.applyCount(getContext(), badgeCount);
                    }
                }
            }
        }
    };
    public ViewGroup myScrollView;
    private int mylayout;
    private int myviewgroup;
    private int sc;
    public ViewGroup textView;

    public static Bundle args;

    public TestFragment2() {

    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static TestFragment2 newInstance(int param1, int param2) {
        System.out.println("查找………"+param1);
        System.out.println("查找………创建"+param2);

        TestFragment2 fragment = new TestFragment2();
        args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getArguments() != null) {
            mylayout = args.getInt(ARG_PARAM1);
            myviewgroup = args.getInt(ARG_PARAM2);

            System.out.println("查找………"+mylayout);
            System.out.println("查找………创建"+myviewgroup);
        }

        View inflate = layoutInflater.inflate(mylayout, viewGroup, false);
        this.textView = (ViewGroup) inflate.findViewById(myviewgroup);
        this.myScrollView = (ViewGroup) inflate.findViewById(this.sc);
        if (getArguments().getInt("id") == 2) {
            Intent intent = new Intent();
            intent.putExtra("what", 3);
            intent.setAction("com.dgua.jmdx.Service.InMessageData");
            intent.setPackage(getContext().getPackageName());//android 5.0之后必需显性调用
           // getContext().startService(intent);
        }
        ((DguaCtVtell) textView).addView("", "开启机器人", "");
        return inflate;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean z) {
        if (this.textView != null) {
            if (z) {
                this.handler.sendEmptyMessageDelayed(3, (long) 20);
            }
            super.setUserVisibleHint(z);
        }
        if (z) {
            this.handler.sendEmptyMessageDelayed(3, (long) 20);
        }
        super.setUserVisibleHint(z);
    }
}
