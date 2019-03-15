package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;

import java.util.ArrayList;
import java.util.List;

public class PhoneData extends IntentService {
    public static final int DELETE = 1;
    public static final int INSERT = 2;
    public static final int SELECTSET = 3;
    helper hp1;
    private LocalBroadcastManager mLocalBroadcastManager;
    class helper extends SQLiteOpenHelper {
        private static final String CREATTABLE2 = "create table student1(_id INTEGER PRIMARY KEY AUTOINCREMENT ,other TEXT,name TEXT,tell TEXT,other1 TEXT,other2 TEXT)";
        public static final int version = 1;

        @Override
        public void close()
        {
            getWritableDatabase().close();
        }

        public helper(PhoneData phoneData, Context context) {
            super(context, "text.db", (CursorFactory) null, 1);

        }

        @Override
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CREATTABLE2);

            System.out.println("查找………创建");
        }

        public void insert(DguaModel dguaModel) {
            getWritableDatabase().execSQL("insert into student1(tab,address,mishi,message,time,zt) values(?,?,?,?,?,?)", new Object[]{dguaModel.gettab(), dguaModel.getaddress(), dguaModel.getmishi(), dguaModel.getmessage(), new Integer(dguaModel.gettime()), new Integer(dguaModel.getzt())});
            getWritableDatabase().close();
        }

        public void UpDate(DguaModel dguaModel) {

            getWritableDatabase().execSQL("update student1 set tab=?,mishi=? where address=?", new Object[]{dguaModel.gettab(), dguaModel.getmishi(), dguaModel.getaddress()});
            getWritableDatabase().close();
        }

        public void Delete(DguaModel dguaModel) {
            System.out.println("shanchu...");
            getWritableDatabase().execSQL("delete from student1 where address=?", new Object[]{dguaModel.getaddress()});
            getWritableDatabase().close();
        }

        public List<DguaModel> select() {
            System.out.println("查找………查找1");

            List<DguaModel> arrayList = new ArrayList();
            Cursor rawQuery = getWritableDatabase().rawQuery("select tab,address,mishi,message,time,zt from dguaPhone", (String[]) null);
            if (rawQuery.moveToFirst()) {
                arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
                while (rawQuery.moveToNext()) {
                    arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
                }
                System.out.println("查找………查找2");
                getWritableDatabase().close();
                return arrayList;
            }
            System.out.println("查找………查找3");
            getWritableDatabase().close();
            return new ArrayList();
        }

        @Override
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("alter table student1 rename to dguasound22");
            sQLiteDatabase.execSQL(CREATTABLE2);
            sQLiteDatabase.execSQL("insert into student1(tab,address,mishi,message,time,zt) from dguasound22");
            sQLiteDatabase.execSQL("drop table dguasound22");
            Log.i("asdf", "数据库更新");
        }
    }

    public PhoneData() {
        super("PhoneData");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

            System.out.println("查找………1234567");


       this.hp1 = new helper(this, this);
        DguaModel dguaModel = (DguaModel) null;
        List list = (List) null;
        switch (intent.getExtras().getInt("what")) {
            case 1:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");
                System.out.println("查找………");
                this.hp1.Delete(dguaModel);
                List select11 = this.hp1.select();
              /*  Message obtainMessage11 = ActivityUiRecog.tf1.handler.obtainMessage();
                obtainMessage11.what = 111;
                obtainMessage11.obj = select11;
                ActivityUiRecog.tf1.handler.sendMessage(obtainMessage11);*/
                return;
            case 2:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");
                System.out.println("查找………");
                this.hp1.insert(dguaModel);
                List select1 = this.hp1.select();
                /*Message obtainMessage1 = ActivityUiRecog.tf1.handler.obtainMessage();
                obtainMessage1.what = 111;
                obtainMessage1.obj = select1;
                ActivityUiRecog.tf1.handler.sendMessage(obtainMessage1);*/
                return;
            case 3:

                System.out.println("查找………按钮进入");
                List select = this.hp1.select();
                /*Message obtainMessage = ActivityUiRecog.tf1.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                ActivityUiRecog.tf1.handler.sendMessage(obtainMessage);*/
                return;

            case 4:
                System.out.println("查找………按钮进入");
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");

                this.hp1.Delete(dguaModel);
                System.out.println("查找………按钮进入");
                List select4 = this.hp1.select();
               /* Message obtainMessage4 = ActivityUiRecog.tf1.handler.obtainMessage();
                obtainMessage4.what = 111;
                obtainMessage4.obj = select4;
                ActivityUiRecog.tf1.handler.sendMessage(obtainMessage4);*/
                return;
            default:
                return;
        }
    }

    @Override
    public void onDestroy() {
        this.hp1.close();
        super.onDestroy();
    }
}
