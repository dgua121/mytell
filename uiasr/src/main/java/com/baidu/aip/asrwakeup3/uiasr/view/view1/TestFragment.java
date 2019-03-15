package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.aip.asrwakeup3.uiasr.Service.PhoneData;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmessage;
import com.baidu.aip.asrwakeup3.uiasr.gongju.UriTofilePath;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;


import java.util.List;

import static android.app.Activity.RESULT_OK;


public class TestFragment extends Fragment {
    public Context ct;
    static Test test1;
    private static final int REQUEST_CHOOSEFILE = 0;
    public Handler handler = new Handler() {
        private String[] arr;
        private List<DguaModel> mhs;
        private DguaModel[] mydata;

        @Override
        public void handleMessage(Message message) {
            if (getArguments().getInt("id") == 0) {
                ActivityUiRecog.t1.dg.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            System.out.println("按钮按下………");
                            if (ActivityUiRecog.settingActivityClass != null) {
                                ActivityUiRecog.running = true; // 是否该Activity依旧需要运行
                                Intent intent = new Intent(getContext(), ActivityUiRecog.settingActivityClass);
                                startActivityForResult(intent, 1);
                            }

                            return true;
                        } else if (motionEvent.getAction() == 1) {
                            System.out.println("按钮松开………");
                            return true;
                        } else {
                            System.out.println("按钮………");

                            return false;
                        }
                    }
                });

                if (message.what == 1222) {
                    ((DguaCtV) textView).getchild3(1).setText(message.obj.toString());
                }
                if (message.what == 122) {
                    ((DguaCtV) textView).getchild1(1).setNU((int) (message.obj));
                }
                if (message.what == 1221) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Test test1 = new Test(ct);
                            test1.querysize();
                        }
                    }.start();
                    ((DguaCtV) textView).getchild1(1).setdown();
                }
                if (message.what == 123) {
                    ((DguaCtV) textView).getchild1(1).setNU((int) (message.obj));
                }
                if (message.what == 1231) {
                    ((DguaCtV) textView).getchild1(2).setdown();
                }

                if (message.what == 1242) {
                    ((DguaCtV) textView).getchild3(3).setText(message.obj.toString());
                }
                if (message.what == 124) {
                    ((DguaCtV) textView).getchild1(3).setNU((int) (message.obj));

                }
                if (message.what == 1241) {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            Testmessage test1 = new Testmessage(ct);
                            test1.querysize();
                        }
                    }.start();
                    ((DguaCtV) textView).getchild1(3).setdown();
                    ((DguaCtV) textView).getchild1(4).setdown();
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

    public TestFragment() {
        ct = getContext();
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static TestFragment newInstance(int param1, int param2) {
        TestFragment fragment = new TestFragment();
        args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        return fragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ct = getContext();
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            mylayout = args.getInt(ARG_PARAM1);
            myviewgroup = args.getInt(ARG_PARAM2);
        }
        View inflate = layoutInflater.inflate(mylayout, viewGroup, false);
        textView = (ViewGroup) inflate.findViewById(myviewgroup);
        myScrollView = (ViewGroup) inflate.findViewById(this.sc);
        ((DguaCtV) textView).addView("", "电话", "");
        ((DguaCtV) textView).addView("", "去重", "");
        ((DguaCtV) textView).addView("", "短信", "");
        ((DguaCtV) textView).addView("", "清空", "");

        if (getArguments().getInt("id") == 0) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Test test1 = new Test(ct);
                    test1.querysize();
                    Testmessage test2 = new Testmessage(ct);
                    test2.querysize();
                }
            }.start();


        }
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
                this.handler.sendEmptyMessageDelayed(1, (long) 20);
            }
            super.setUserVisibleHint(z);
        }
        if (z) {
            this.handler.sendEmptyMessageDelayed(1, (long) 20);
        }
        super.setUserVisibleHint(z);
    }
}
