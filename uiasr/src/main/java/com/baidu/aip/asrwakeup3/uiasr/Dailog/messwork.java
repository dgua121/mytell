package com.baidu.aip.asrwakeup3.uiasr.Dailog;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.gj.Testmework;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.DguaCtSubVmess;
import com.baidu.aip.asrwakeup3.uiasr.view.view1.MessageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class messwork extends Dialog {

    public String a1;
    public String a2;
    public String a3;
    public Context cctt;
    public DguaCtSubVmess dcsv;
    int time=35000;
    String s1="",s2="";
    private List<Map<String, Object>> data, data2;//一个集合
    private Map ay = new HashMap();



    public messwork(Context context, int i, int i2, int i3, int i4, List<Map<String, Object>> d1, List<Map<String, Object>> d2)

    {
        super(context, i4);
        setContentView(i3);
        final TextView textView = (TextView) findViewById(R.id.textView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 当拖动条的滑块位置发生改变时触发该方法,在这里直接使用参数progress，即当前滑块代表的进度值
                textView.setText(Integer.toString(progress));

                time=progress*1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Log.e("------------", "开始滑动！");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Log.e("------------", "停止滑动！");

                //ay.put("time",time);
            }
        });
        data = d1;
        data2 = d2;
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        //数据源
        //data = new ArrayList<>();
        //创建一个SimpleAdapter适配器
        //第一个参数：上下文，第二个参数：数据源，第三个参数：item子布局，第四、五个参数：键值对，获取item布局中的控件id
        final SimpleAdapter s_adapter = new SimpleAdapter(context, d1, R.layout.layout2, new String[]{"text"}, new int[]{R.id.tvv1});
        //控件与适配器绑定
        spinner.setAdapter(s_adapter);
        String s=((Map)(data.get(0))).get("text").toString();
        s1=s;
        //点击事件
        //spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=((Map)(data.get(position))).get("text").toString();
                s1=s;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
        //数据源
        // data = new ArrayList<>();
        //创建一个SimpleAdapter适配器
        //第一个参数：上下文，第二个参数：数据源，第三个参数：item子布局，第四、五个参数：键值对，获取item布局中的控件id
        final SimpleAdapter s_adapter1 = new SimpleAdapter(context, d2, R.layout.layout2, new String[]{"text"}, new int[]{R.id.tvv1});
        //控件与适配器绑定
        spinner1.setAdapter(s_adapter1);

        String ss=((Map)(data.get(0))).get("text").toString();
        s2=ss;
        //spinner1.setSelection(0, true);
        //点击事件
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s=((Map)(data.get(position))).get("text").toString();
                s2=s;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button bt=findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new Thread(){
                   @Override
                   public void run() {
                       super.run();
                       Calendar cal = Calendar.getInstance();
                        String work = "messwork" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE)
                               + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE)+cal.get(Calendar.MILLISECOND);

                       Map m=new Testmework(getContext()).querylast();

                        if(m!=null&& m.get("other").toString().equals("true")){

                           Testmework t1=new Testmework(getContext());
                           if(time<35000){
                               time=35000;
                           }
                           t1.importSheet(s1,s2,time,work,"false1");
                           t1.mdb.close();
                           Message msg = new Message();
                           msg.what = 123;
                         //  ActivityCommon.tf11.handler.sendMessage(msg);
                       }else {
                           Message msg = new Message();
                           msg.what = 1233;
                           //ActivityCommon.tf11.handler.sendMessage(msg);

                       }

                       dismiss();
                   }
               }.start();


            }
        });

    }


    /**
     * 测试：加载数据列，监听选择
     */
    private float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


}
