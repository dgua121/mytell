package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.Context;
import android.os.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test {

    public phonedb db;

    public Test(Context c) {

        db = new phonedb(c);
    }

    public void importSheet(String str) {
        try {
            // 1
            if (str != null) {
                FileInputStream iss = new FileInputStream(new File("/storage/emulated/0/Download/phone.xls"));
                Workbook book = Workbook.getWorkbook(iss);
                // 3
                Sheet sheet = book.getSheet(0);
                // 4
                for (int j = 1; j < sheet.getRows(); ++j) {
                    // 5
                    String a = sheet.getCell(0, j).getContents();
                    String a1 = sheet.getCell(1, j).getContents();
                    String a2 = sheet.getCell(2, j).getContents();
                    int aaaa=a2.length();
                    if(aaaa==11) {
                        String ss = a2.substring(0, 3);
                        if (ss.equals("371")||ss.equals("150") || ss.equals("151") || ss.equals("152") || ss.equals("157") || ss.equals("158") || ss.equals("159") || ss.equals("134") || ss.equals("135") || ss.equals("136") || ss.equals("137") || ss.equals("138") || ss.equals("139") || ss.equals("182") || ss.equals("187") || ss.equals("188") || ss.equals("147")) {
                            db.insertData(a, a1, a2, str, "a4");
                        } else {
                            db.insertData(a, a1, a2, str, "a4");
                            if (j % 20 == 0) {
                                Message msg = new Message();
                                msg.what = 122;
                                msg.obj = (int) j;
                              //  ActivityCommon.tf1.handler.sendMessage(msg);
                            }
                        }
                    }
                    else if(aaaa==13){
                        String ss = a2.substring(2, 5);
                        if (ss.equals("371")||ss.equals("150") || ss.equals("151") || ss.equals("152") || ss.equals("157") || ss.equals("158") || ss.equals("159") || ss.equals("134") || ss.equals("135") || ss.equals("136") || ss.equals("137") || ss.equals("138") || ss.equals("139") || ss.equals("182") || ss.equals("187") || ss.equals("188") || ss.equals("147")) {
                            db.insertData(a, a1, a2, str, "a4");
                        } else {
                            db.insertData(a, a1, a2, str, "a4");
                            if (j % 20 == 0) {
                                Message msg = new Message();
                                msg.what = 122;
                                msg.obj = (int) j;
                               // ActivityCommon.tf1.handler.sendMessage(msg);
                            }
                        }
                    }

                }
                Message msg = new Message();
                msg.what = 1221;
               // ActivityCommon.tf1.handler.sendMessage(msg);
                //只有增加数据 需要调用关闭
                db.close();
                book.close();
                System.out.println("结束");
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

    public String querywork(int a,String s) {
        return db.querywork(a,s);
    }

    public String queryData(int a) {
        return db.queryData(a);
    }
    public String queryData2() {
        return db.queryData2();
    }


    public String queryDataname(int a) {
        return db.queryDataname(a);
    }
    public  int querynumber(){
        return db.querysize();
    }

    public  void querysize(){
        int a=db.querysize();
        Message msg = new Message();
        msg.what = 1222;
        msg.obj = a;
       // ActivityCommon.tf1.handler.sendMessage(msg);
    }
    public int updata(){
       return db.updata();
    }
    public void updatamessage(String a){
         db.updatamessage(a);
    }
    public HashSet<String> gettime(){

        return db.gettime();
    }
    public void rename(){
        db.rename();
    }
}