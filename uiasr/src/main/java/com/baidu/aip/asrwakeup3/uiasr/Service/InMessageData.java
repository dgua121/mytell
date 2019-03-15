package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import com.baidu.aip.asrwakeup3.uiasr.R;
import com.baidu.aip.asrwakeup3.uiasr.MainActivity;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaTalkActivity;

import java.util.ArrayList;
import java.util.List;

//import java.io.Parcelable;

public class InMessageData extends IntentService {
    public static final int DELETE = 1;
    public static final int INSERT = 2;
    public static final int SELECTSET = 3;
    public static final int SELECTSETME = 4;
    public static final int UPDATA = 0;
    helper hp1;

    class helper extends SQLiteOpenHelper {
        private static final String CREATTABLE2 = "create table dguaInMessage(id integer primary key autoincrement ,tab text,address text,mishi text,message text,time integer,zt integer)";
        public static final int version = 1;
        public Context context1 ;
        //private final InMessageData this$0;

       /* static InMessageData access$0(helper helper) {
            return helper.this$0;
        }*/

        @Override
        public void close()
        {
            getWritableDatabase().close();
        }

        public helper(InMessageData inMessageData, Context context) {

            super(context, "dguajmb3.db", (CursorFactory) null, 1);
            context1=context;
            //this = inMessageData;
        }

        @Override
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CREATTABLE2);
        }

        public void insert(DguaModel dguaModel) {
            getWritableDatabase().execSQL("insert into dguaInMessage(tab,address,mishi,message,time,zt) values(?,?,?,?,?,?)", new Object[]{dguaModel.gettab(), dguaModel.getaddress(), dguaModel.getmishi(), dguaModel.getmessage(), new Integer(dguaModel.gettime()), new Integer(dguaModel.getzt())});
            System.out.println("短信data插入成功"+dguaModel.getaddress()+"----"+dguaModel.getmishi()+"----"+dguaModel.getmessage());
        }

        public void UpDate(DguaModel dguaModel) {
            getWritableDatabase().execSQL("update dguaInMessage set zt=? where address like ?", new Object[]{new Integer(1), new StringBuffer().append("%").append(dguaModel.getaddress().substring(3)).toString()});
        }

        public void Delete(DguaModel dguaModel) {
            getWritableDatabase().execSQL("delete from dguaInMessage where address=?,time=?", new Object[]{dguaModel.getaddress(), new Integer(dguaModel.gettime())});
        }

        public List<DguaModel> select() {
            List<DguaModel> arrayList = new ArrayList();
            Cursor rawQuery = getWritableDatabase().rawQuery("select tab,address,mishi,message,time,zt from dguaInMessage where zt=0", (String[]) null);
            if (!rawQuery.moveToFirst()) {
                getWritableDatabase().close();
                return (List) null;
            }
            arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
            //int badgeCount = 1;

            while (rawQuery.moveToNext()) {
                arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
           // badgeCount++;

        }

            //ShortcutBadger.applyCount(context1, badgeCount);

            getWritableDatabase().close();
            return arrayList;
        }

        public List<DguaModel> select(String str) {
            System.out.println("短信查询---->");


            List<DguaModel> arrayList = new ArrayList();
            Cursor rawQuery = getWritableDatabase().rawQuery("select tab,address,mishi,message,time,zt from dguaInMessage where address =?", new String[]{String.valueOf(str)});
            if (rawQuery.moveToFirst()) {
                arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
                while (rawQuery.moveToNext()) {
                    arrayList.add(new DguaModel(rawQuery.getString(rawQuery.getColumnIndex("tab")), rawQuery.getString(rawQuery.getColumnIndex("address")), rawQuery.getString(rawQuery.getColumnIndex("mishi")), rawQuery.getString(rawQuery.getColumnIndex("message")), rawQuery.getInt(rawQuery.getColumnIndex("time")), rawQuery.getInt(rawQuery.getColumnIndex("zt"))));
                }
                getWritableDatabase().close();
                return arrayList;
            }
            getWritableDatabase().close();
            return (List) null;
        }

        @Override
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("alter table dguaInMessage rename to dguasound222");
            sQLiteDatabase.execSQL(CREATTABLE2);
            sQLiteDatabase.execSQL("insert into dguaInMessage(tab,address,mishi,message,time,zt) from dguasound222");
            sQLiteDatabase.execSQL("drop table dguasound222");
            Log.i("asdf", "数据库更新");
        }
    }

    public InMessageData() {
        super("InMessageData");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        this.hp1 = new helper(this, this);
        DguaModel dguaModel = (DguaModel) null;
        List list = (List) null;
        List select;
        Message obtainMessage;
        switch (intent.getExtras().getInt("what")) {
            case 0:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");
                System.out.println("查找………");
                this.hp1.UpDate(dguaModel);
                return;
            case 1:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");
                System.out.println("查找………");
                this.hp1.Delete(dguaModel);
                return;
            case 2:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate");
                System.out.println("添加短信………999");
                hp1.insert(dguaModel);
                dguaModel= ((DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate"));
                select = this.hp1.select(dguaModel.getaddress());
                System.out.println("短信查询-->"+dguaModel.getaddress());
                obtainMessage = MainActivity.tf11.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                DguaTalkActivity.mm.sendMessage(obtainMessage);
                return;
            case 3:
                select = this.hp1.select();
                System.out.println("短信查询成功。。。");
                obtainMessage = MainActivity.tf11.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                MainActivity.tf11.handler.sendMessage(obtainMessage);
                return;
            case 4:

                dguaModel= ((DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate"));
                select = this.hp1.select(dguaModel.getaddress());
                System.out.println("短信查询-->"+dguaModel.getaddress());
                obtainMessage = MainActivity.tf11.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                DguaTalkActivity.mm.sendMessage(obtainMessage);
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
