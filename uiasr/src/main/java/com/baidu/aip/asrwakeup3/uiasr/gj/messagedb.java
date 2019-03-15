package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;
import java.util.HashSet;

import static android.content.Context.MODE_PRIVATE;

public class messagedb {


    private static final String TABLENAME = "student222";
    private static final String CREATETABLE = "CREATE TABLE " + TABLENAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,mesage TEXT,time TEXT,other TEXT)";
    public Context ct;
    SQLiteDatabase db;

    public messagedb(Context a) {
        ct = a;
        creatTable();
    }

    public void creatTable() {
        /**
         * 创建数据库
         * 参数一：数据库名
         * 参数二：模式，一般为MOE_PRIVATE
         * 参数三：游标工厂对象，一般写null，表示系统自动提供
         */
        db = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        if (!sqlTableIsExist(TABLENAME)) {
            db.execSQL(CREATETABLE);
        }
        //db.close();
    }

    private boolean sqlTableIsExist(String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            //search.db数据库的名字
            // db = ct.openOrCreateDatabase("text.db", Context.MODE_PRIVATE, null);
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    public void insertData(String a, String b, String c) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("mesage", a);
        values.put("time", b);
        values.put("other", c);
        /**
         *插入数据
         * 参数一：要插入的表名
         * 参数二：要插入的空数据所在的行数，第三个参数部位空，则此参数为null
         * 参数三：要插入的数据
         */
        db.insert(TABLENAME, null, values);

    }

    public void close() {
        db.close();
    }



    public String queryData(Integer id) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where _id = ?", new String[]{id + ""});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            String message = cursor1.getString(cursor1.getColumnIndex("mesage"));
            db.close();
            return message;
        }
        db.close();
        return "";
    }


    public int querysize() {

        int id = 0;
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME, null);
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            id++;
            while (!cursor1.isLast()) {
                cursor1.moveToNext();
                id++;
            }
            db.close();
            return id;
        }
        db.close();
        return 0;
    }

    public void delectData() {
        //要删除的数据
        db.delete(TABLENAME, "message = ?", new String[]{"赵四"});
        db.close();
    }
    public HashSet<String> gettime(){
        HashSet<String> ls=new HashSet<String>();
        Cursor cursor11 = db.rawQuery("select * from " + TABLENAME, null);
        if (cursor11.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            while (!cursor11.isLast()) {
                String time = cursor11.getString(cursor11.getColumnIndex("time"));
                ls.add(time);
                cursor11.moveToNext();

            }

        }
        db.close();
        return ls;
    }

    public void rename(){
        Calendar cal = Calendar.getInstance();
        String time_cal = "message" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE)
                + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
        int i = 0, k = 0;
        db.execSQL("alter table student222 rename to " + time_cal);

    }
}
