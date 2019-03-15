package com.baidu.aip.asrwakeup3.uiasr.view.view1;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.Dailog.messwork;
import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.gj.Test;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmessage;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmework;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class TestFragment1 extends Fragment {
    public Context ct;

    public Handler handler = new Handler() {
        private String[] arr;
        private List<HashSet> mhs;
        private DguaModel[] mydata;
        private messwork ms;

        @Override
        public void handleMessage(Message message) {
            if (textView != null && getArguments().getInt("id") == 1) {
                ActivityUiRecog.t1.dg.setOnTouchListener(new OnTouchListener() {

                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    Test t1 = new Test(getContext());
                                    // t1.gettime();
                                    Testmessage t2 = new Testmessage(getContext());
                                    // t2.gettime();

                                    List<HashSet> a = new ArrayList<HashSet>();
                                    a.add(t1.gettime());
                                    a.add(t2.gettime());
                                    Message m = new Message();
                                    m.what = 1111;
                                    m.obj = a;
                                //    ActivityCommon.tf11.handler.sendMessage(m);
                                }
                            }.start();

                            return true;
                        }
                        return false;
                    }
                });

                if (message.what == 1111) {
                    this.mhs = (List<HashSet>) message.obj;
                    if (this.mhs != null) {
                        List<Map<String, Object>> l1 = new ArrayList<Map<String, Object>>();
                        List<Map<String, Object>> l2 = new ArrayList<Map<String, Object>>();

                        for (String s : (HashSet<String>) (mhs.get(0))) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("text", s);
                            l1.add(map);
                        }
                        for (String s : (HashSet<String>) (mhs.get(1))) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("text", s);
                            l2.add(map);
                        }
                        ms = new messwork(getContext(), ActivityCommon.SCREENWIDTH, ActivityCommon.SCREENWIDTH, R.layout.messwork, R.style.dialog1, l1, l2);
                        ms.show();
                    }
                }
                //gengxin
                if (message.what == 123) {

                    ((MessageView) textView).removeAll();
                    System.out.println("发送完毕删除任务");
                    new Thread() {
                        public void run() {
                            super.run();
                            Testmework t1 = new Testmework(getContext());
                            int j = t1.querysize();
                            // for (int i = 1; i <= j; i++) {
                            Map m = new Testmework(getContext()).querylast();
                           // if(m==null){
                            if (!m.get("other").toString().equals("true")) {
                                Message me = new Message();
                                me.what = 1231;
                                me.obj = m;
                                handler.sendMessage(me);

                                System.out.println("发送完毕----->" + new Testmework(getContext()).querylast().get("other").toString());
                            }
                            //  }
                        }
                    }.start();

                }
                if (message.what == 1233) {
                    Toast.makeText(getContext(), "one card", Toast.LENGTH_SHORT).show();
                    Message msg = new Message();
                    msg.what = 123;
                    handler.sendMessage(msg);
                }
                if (message.what == 1231) {

                    ((MessageView) textView).addView("", "开始任务", "", (Map) message.obj);

                }

                if (message.what == 124) {
                    Map m = (Map) message.obj;
                    final int aaa = (int) ((Map) message.obj).get("n");
                    final String sss = (String) ((Map) message.obj).get("p").toString();
                    if ((int) (((MessageView) textView).getChildCount()) == 2) {

                        ((MessageView) textView).getchild1(1).setNU(aaa);
                        Message me1 = new Message();
                        me1.what = 1111;
                        //me1.arg2 = aaa;
                        me1.obj=m;
                        ((DguaCtSubV1)(((MessageView) textView).getchild1(1))).pd.handler.sendMessage(me1);
                    }
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            System.out.println("第n数据---->" + aaa);
                            new Testmework(getContext()).upda(aaa, sss);
                        }
                    }.start();
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

    public TestFragment1() {

    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static TestFragment1 newInstance(int param1, int param2) {
        System.out.println("查找………" + param1);
        System.out.println("查找………创建" + param2);

        TestFragment1 fragment = new TestFragment1();
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
        }

        View inflate = layoutInflater.inflate(mylayout, viewGroup, false);
        this.textView = (ViewGroup) inflate.findViewById(myviewgroup);
        this.myScrollView = (ViewGroup) inflate.findViewById(this.sc);
        System.out.println(getArguments().getInt("id"));


        Message msg = new Message();
        msg.what = 123;
        handler.sendMessage(msg);
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
                this.handler.sendEmptyMessageDelayed(2, (long) 20);
            }
            super.setUserVisibleHint(z);
        }
        if (z) {
            this.handler.sendEmptyMessageDelayed(2, (long) 20);
        }
        super.setUserVisibleHint(z);
    }
}
