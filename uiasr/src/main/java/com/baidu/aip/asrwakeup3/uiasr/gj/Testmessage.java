package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.Context;
import android.os.Message;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Testmessage {

    public messagedb mdb;

    public Testmessage(Context c) {
        mdb = new messagedb(c);
    }

    public void importSheet(String str) {
        try {
            // 1
            if (str != null) {
                FileInputStream iss = new FileInputStream(new File("/storage/emulated/0/Download/message.xls"));
                // 2
                Workbook book = Workbook.getWorkbook(iss);
                // 3
                Sheet sheet = book.getSheet(0);
                int row = sheet.getRows();
                Sheet sheet1 = book.getSheet(1);
                int row1 = sheet1.getRows();
                Sheet sheet2 = book.getSheet(2);
                int row2 = sheet2.getRows();
                Sheet sheet3 = book.getSheet(3);
                int row3 = sheet3.getRows();
                Sheet sheet4 = book.getSheet(4);
                int row4 = sheet4.getRows();
                int aaa = 1;
                // 4
                for (int j1 = 0; j1 < row1; ++j1) {
                    // 5
                    for (int j2 = 0; j2 < row2; ++j2) {
                        for (int j3 = 0; j3 < row3; ++j3) {
                            for (int j = 0; j < row; ++j) {
                                for (int j4 = 0; j4 < row4; ++j4) {
                                    String a = sheet.getCell(0, j).getContents() + sheet1.getCell(0, j1).getContents() + sheet2.getCell(0, j2).getContents() + sheet3.getCell(0, j3).getContents() + sheet4.getCell(0, j4).getContents();
                                    mdb.insertData(a, str, "");
                                    aaa++;
                                }
                            }
                            Message msg = new Message();
                            msg.what = 124;
                            msg.obj = (int) aaa;
                         //   ActivityCommon.tf1.handler.sendMessage(msg);
                        }
                    }
                }

                Message msg = new Message();
                msg.what = 1241;
              //  ActivityCommon.tf1.handler.sendMessage(msg);
                //只有增加数据 需要调用关闭
                mdb.close();
                book.close();
                System.out.println("结束");
            }
        } catch (IOException |
                BiffException e)

        {
            e.printStackTrace();
        }

    }

    public String queryData(int a) {
        return mdb.queryData(a);
    }

    public int querynumber() {
        return mdb.querysize();
    }

    public void querysize() {
        int a = mdb.querysize();
        Message msg = new Message();
        msg.what = 1242;
        msg.obj = a;
       // ActivityCommon.tf1.handler.sendMessage(msg);
    }

    public HashSet<String> gettime() {

        return mdb.gettime();
    }

    public void rename() {
        mdb.rename();
    }
}
