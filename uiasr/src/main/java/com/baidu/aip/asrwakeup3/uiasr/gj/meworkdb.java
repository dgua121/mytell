package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class meworkdb {

    private static final String TABLENAME = "student3";
    private static final String CREATETABLE = "CREATE TABLE " + TABLENAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,message TEXT,phone TEXT,time INTEGER,work TEXT,other TEXT)";
    public Context ct;
    SQLiteDatabase db;

    public meworkdb(Context a) {
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

    public void insertData(String a, String b, Integer c, String d, String e) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("message", a);
        values.put("phone", b);
        values.put("time", c);
        values.put("work", d);
        values.put("other", e);
        /**
         *插入数据
         * 参数一：要插入的表名
         * 参数二：要插入的空数据所在的行数，第三个参数部位空，则此参数为null
         * 参数三：要插入的数据
         */
        db.insert(TABLENAME, null, values);

        db.close();
    }

    public void close() {
        db.close();
    }

    public Map queryData(Integer id) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
       // id++;
        Map m = new HashMap();
        m.put("other", "true");
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where _id = ?", new String[]{id + ""});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            String message = cursor1.getString(cursor1.getColumnIndex("message"));
            String phone = cursor1.getString(cursor1.getColumnIndex("phone"));
            String work = cursor1.getString(cursor1.getColumnIndex("work"));
            Integer time = cursor1.getInt(cursor1.getColumnIndex("time"));
            String other = cursor1.getString(cursor1.getColumnIndex("other"));

            m.put("message", message);
            m.put("phone", phone);
            m.put("time", time);
            m.put("work", work);
            m.put("other", other);

            db.close();
            return m;
        }
        db.close();

        return m;
    }
    public Map queryLast() {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Map m = new HashMap();
        m.put("other", "true");
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME, null);
        if (cursor1.moveToLast())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            String message = cursor1.getString(cursor1.getColumnIndex("message"));
            String phone = cursor1.getString(cursor1.getColumnIndex("phone"));
            String work = cursor1.getString(cursor1.getColumnIndex("work"));
            Integer time = cursor1.getInt(cursor1.getColumnIndex("time"));
            String other = cursor1.getString(cursor1.getColumnIndex("other"));

            m.put("message", message);
            m.put("phone", phone);
            m.put("time", time);
            m.put("work", work);
            m.put("other", other);

            db.close();
            System.out.println("other----->"+other);
            return m;
        }
        db.close();
           // m.put("other", "false1");
        return m;
    }


    public void updateData(int aa, String ssss) {
        ContentValues mm = new ContentValues();
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME, null);

        if (aa != 0) {

            if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
            {
                String message = cursor1.getString(cursor1.getColumnIndex("message"));
                String phone = cursor1.getString(cursor1.getColumnIndex("phone"));
                String work = cursor1.getString(cursor1.getColumnIndex("work"));
                Integer time = cursor1.getInt(cursor1.getColumnIndex("time"));

                mm.put("message", message);
                mm.put("phone", phone);
                mm.put("time", time);
                mm.put("work", work);
                mm.put("other", aa + "");
                System.out.println("fasong over"+aa);
                db.update(TABLENAME, mm, "work=?", new String[]{work});
                db.close();
            }
        } else {
            if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
            {
                String message = cursor1.getString(cursor1.getColumnIndex("message"));
                String phone = cursor1.getString(cursor1.getColumnIndex("phone"));
                String work = cursor1.getString(cursor1.getColumnIndex("work"));
                Integer time = cursor1.getInt(cursor1.getColumnIndex("time"));
                mm.put("message", message);
                mm.put("phone", phone);
                mm.put("time", time);
                mm.put("work", work);
                mm.put("other", "true");

                System.out.println("fasong over true");
                db.update(TABLENAME, mm, "work=?", new String[]{work});
                if(aa==0){
                    db.delete(TABLENAME, "other = ?", new String[]{"true"});
                }
                db.close();
            }
        }
        /**
         * 数据的更新
         * 参数一：要更新的数据所在的表名
         * 参数二：新的数据SQLiteDatabase db
         * 参数三：要更新数据的查找条件
         * 参数四：条件的参数
         */


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

    public void delectData(String s) {
        //要删除的数据
        db.delete(TABLENAME, "work = ?", new String[]{s});

        db.close();
    }

    public HashSet<String> gettime() {
        HashSet<String> ls = new HashSet<String>();
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
}
