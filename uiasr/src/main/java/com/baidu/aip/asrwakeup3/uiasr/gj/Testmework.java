package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.Context;
import android.os.Message;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Testmework {
    public meworkdb mdb;
    public Testmework(Context c) {
        mdb=new meworkdb(c);
    }

    public void importSheet(String a, String b,Integer c, String d,String e) {

                    mdb.insertData(a, b, c,d,e);

                }

    public Map queryData(int a) {

        return mdb.queryData(a);
    }
    public Map querylast() {

        return mdb.queryLast();
    }
    public void upda(int a,String b){
        mdb.updateData(a,b);
    }


    public  int querysize(){
        return mdb.querysize();

    }
    public HashSet<String> gettime(){

        return mdb.gettime();
    }
}
