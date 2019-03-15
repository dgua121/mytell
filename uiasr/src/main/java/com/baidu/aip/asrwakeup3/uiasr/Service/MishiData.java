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

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;
import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityUiRecog;
import com.baidu.aip.asrwakeup3.uiasr.model.DguaModel;
import com.baidu.aip.asrwakeup3.uiasr.view.DguaTalkActivity;

import java.util.ArrayList;
import java.util.List;

public class MishiData extends IntentService {
    public static final int DELETE = 1;
    public static final int INSERT = 2;
    public static final int SELECTSET = 3;
    public static final int SELECTSETMS = 4;
    public static final int SELECTSETMS2 = 6;
    public static final int UPDATA = 5;
    helper hp1;

    class helper extends SQLiteOpenHelper {
        private static final String CREATTABLE2 = "create table dguaMishi(id integer primary key autoincrement ,tab text,address text,mishi text,message text,time integer,zt integer)";
        public static final int version = 1;

        @Override
        public void close() {
            getWritableDatabase().close();
        }

        public helper(MishiData mishiData, Context context) {
            super(context, "dguajmb2.db", (CursorFactory) null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CREATTABLE2);
            for (int i = 0; i < 9; i++) {
                sQLiteDatabase.execSQL("insert into dguaMishi(tab,address,mishi,message,time,zt) values(?,?,?,?,?,?)", new Object[]{"", "", new StringBuffer().append("Hello Dgua!%&@").append(i).toString(), "", new Integer(i), new Integer(0)});
            }
        }

        public void insert(DguaModel dguaModel) {
            getWritableDatabase().execSQL("insert into dguaMishi(tab,address,mishi,message,time,zt) values(?,?,?,?,?,?)", new Object[]{dguaModel.gettab(), dguaModel.getaddress(), dguaModel.getmishi(), dguaModel.getmessage(), new Integer(dguaModel.gettime()), new Integer(dguaModel.getzt())});
            System.out.println("密匙data插入成功");
            getWritableDatabase().close();
        }

        public void UpDate(DguaModel dguaModel) {
            getWritableDatabase().execSQL("update dguaMishi set mishi=? where time=?", new Object[]{dguaModel.getmishi(), new Integer(dguaModel.gettime())});
            getWritableDatabase().close();
        }

        public void Delete(DguaModel dguaModel) {
            getWritableDatabase().execSQL("delete from dguaMishi where mishi=?", new Object[]{dguaModel.getmishi()});
            getWritableDatabase().close();
        }

        public List<DguaModel> select() {
            List<DguaModel> arrayList = new ArrayList();
            Cursor rawQuery = getWritableDatabase().rawQuery("select tab,address,mishi,message,time,zt from dguaMishi", (String[]) null);
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

        public List<DguaModel> select(int i) {
            List<DguaModel> arrayList = new ArrayList();
            Cursor rawQuery = getWritableDatabase().rawQuery("select tab,address,mishi,message,time,zt from dguaMishi where time=?", new String[]{String.valueOf(i)});
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
            sQLiteDatabase.execSQL("alter table dguaMishi rename to dguasound222");
            sQLiteDatabase.execSQL(CREATTABLE2);
           // sQLiteDatabase.execSQL("insert into dguaMishi(tab,address,mishi,message,time,zt) from dguasound222");
            sQLiteDatabase.execSQL("drop table dguasound222");
            Log.i("asdf", "数据库更新");
        }
    }

    public MishiData() {
        super("MishiData");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        this.hp1 = new helper(this, this);
        DguaModel dguaModel = (DguaModel) null;
        List list = (List) null;
        List select;
        Message obtainMessage;
        switch (intent.getExtras().getInt("what")) {
            case 1:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable
                        ("mMyDate");
                System.out.println("查找………");
                this.hp1.Delete(dguaModel);
                return;
            case 2:
                dguaModel = (DguaModel) intent.getExtras().getBundle("bundle").getParcelable
                        ("mMyDate");
                System.out.println("查找………");
                this.hp1.insert(dguaModel);
                return;
            case 3:
                System.out.println("功999mmss");
                select = this.hp1.select();
                System.out.println("密匙data查询成功");
             /* //  obtainMessage = ActivityUiRecog.tf22.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                ActivityUiRecog.tf22.handler.sendMessage(obtainMessage);*/
                return;
            case 4:
               /* select = this.hp1.select(((DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate")).gettime());
                System.out.println("密匙data查询成功mmss");
                obtainMessage = ActivityCommon.tf22.handler.obtainMessage();
                obtainMessage.what = 111;
                obtainMessage.obj = select;
                ActivityCommon.tf22.handler.sendMessage(obtainMessage);
                System.out.println("功mmss");*/
                return;
            case 5:
                this.hp1.UpDate((DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate"));
                System.out.println("xiugaichenggong000098");
                return;
            case 6:
                select = this.hp1.select(((DguaModel) intent.getExtras().getBundle("bundle").getParcelable("mMyDate")).gettime());
                System.out.println("密匙data查询成功mmss");
                obtainMessage = DguaTalkActivity.mm.obtainMessage();
                obtainMessage.what = 1111;
                obtainMessage.obj = select;
                DguaTalkActivity.mm.sendMessage(obtainMessage);
                System.out.println("功mmss");
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
