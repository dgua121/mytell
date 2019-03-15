package com.baidu.aip.asrwakeup3.uiasr.gj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.baidu.aip.asrwakeup3.uiasr.activity.ActivityCommon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.baidu.aip.asrwakeup3.core.overead.MicrophoneServer.TAG;

public class phonedb {

    private static final String TABLENAME = "student1";
    private static final String CREATETABLE = "CREATE TABLE " + TABLENAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT ,other TEXT,name TEXT,tell TEXT,other1 TEXT,other2 TEXT)";
    public Context ct;
    SQLiteDatabase db;

    public phonedb(Context a) {
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
        // SQLiteDatabase db = null;
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

    public void insertData(String a, String b, String c, String d, String e) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("other", a);
        values.put("name", b);
        values.put("tell", c);
        values.put("other1", d);
        values.put("other2", e);
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

    public void updateData() {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        // values.put("name", "赵四");
        values.put("tell", "18860386130");
        //values.put("address", "a");
        //values.put("ps", "b");
        // values.put("other", "e");
        /**
         * 数据的更新
         * 参数一：要更新的数据所在的表名
         * 参数二：新的数据SQLiteDatabase db
         * 参数三：要更新数据的查找条件
         * 参数四：条件的参数
         */
        db.update(TABLENAME, values, "name=?", new String[]{"d"});
        db.close();
    }

    public String queryData(Integer id) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where _id = ? ", new String[]{id.toString()});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            int personid = cursor1.getInt(cursor1.getColumnIndex("_id"));
            String name = cursor1.getString(cursor1.getColumnIndex("name"));
            String phone = cursor1.getString(cursor1.getColumnIndex("tell"));
            // System.out.println(phone+name);
            db.close();
            return phone;
        }
        db.close();
        return "18860386130";
    }
    public String queryData2() {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where other2 = ? ", new String[]{"a4"});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            System.out.println("iiiiiiiiiiiiiiiiiooooooooooooooooooooo");
            String other = cursor1.getString(cursor1.getColumnIndex("other"));
            String other1 = cursor1.getString(cursor1.getColumnIndex("other1"));
            Integer personid = cursor1.getInt(cursor1.getColumnIndex("_id"));
            String name = cursor1.getString(cursor1.getColumnIndex("name"));
            String phone = cursor1.getString(cursor1.getColumnIndex("tell"));
            ContentValues values = new ContentValues();
             values.put("name", name);
            values.put("other", other);
            values.put("other1", other1);
            values.put("other2", "b1");
            values.put("tell", phone);
            /**
             * 数据的更新
             * 参数一：要更新的数据所在的表名
             * 参数二：新的数据SQLiteDatabase db
             * 参数三：要更新数据的查找条件
             * 参数四：条件的参数
             */
            db.update(TABLENAME, values, "_id=?", new String[]{ personid.toString()});
            System.out.println("tttttttttttttttttttttttoooooooooooooooooo"+phone);
            db.close();
            return phone;
        }
        db.close();
        return "18860386130";
    }


    public String querywork(int a,String s){
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where other1 = ?", new String[]{s});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            for (int i = 1; i < a; i++) {
                if (!cursor1.isLast()) {
                    cursor1.moveToNext();
                   /* while((cursor1.getString(cursor1.getColumnIndex("other2"))).equals("已发送")) {
                        cursor1.moveToNext();
                    }*/
                }
            }
            if (cursor1!=null &&!(cursor1.getString(cursor1.getColumnIndex("other2"))).equals("已发送")) {
                String name = cursor1.getString(cursor1.getColumnIndex("name"));
                String phone = cursor1.getString(cursor1.getColumnIndex("tell"));
                if (!name.equals("")) {
                    db.close();
                    return phone + (name.substring(0, 1));

                } else {
                    db.close();
                    return phone;
                }
            }
            else{
                db.close();
                return "10000";
            }
        }
        db.close();
        return "10000";
    }

    public String queryDataname(Integer id) {
        //SQLiteDatabase db1 = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where _id = ?", new String[]{id.toString()});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {

            int personid = cursor1.getInt(cursor1.getColumnIndex("_id"));
            String name = cursor1.getString(cursor1.getColumnIndex("name"));
            String phone = cursor1.getString(cursor1.getColumnIndex("tell"));
            //System.out.println(phone);
            if (name != null) {
                db.close();
                return phone + (name.substring(0, 1));

            } else {
                db.close();
                return phone;
            }
        }
        db.close();
        return "18860386130";
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
        db.delete(TABLENAME, "name = ?", new String[]{"赵四"});

        db.close();
    }

    public boolean find(String a) {

        //SQLiteDatabase db = ct.openOrCreateDatabase("text.db", MODE_PRIVATE, null);
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLENAME + " where tell = ?", new String[]{a});
        if (cursor1.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            return false;
        }
        return true;
    }
public void rename(){
    Calendar cal = Calendar.getInstance();
    String time_cal = "phone" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE)
            + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
    int i = 0, k = 0;
    db.execSQL("alter table student1 rename to " + time_cal);

}
    //去重
    public int updata() {
        Calendar cal = Calendar.getInstance();
        String time_cal = "a" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DATE)
                + cal.get(Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE);
        int i = 0, k = 0;
        db.execSQL("alter table student1 rename to " + time_cal);
        db.execSQL(CREATETABLE);
        Cursor cursor11 = db.rawQuery("select * from " + time_cal, null);
        if (cursor11.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            do {
                String phone = cursor11.getString(cursor11.getColumnIndex("tell"));
                if (find(phone)) {
                    String name = cursor11.getString(cursor11.getColumnIndex("name"));
                    int personid = cursor11.getInt(cursor11.getColumnIndex("_id"));
                    String other1 = cursor11.getString(cursor11.getColumnIndex("other1"));
                    String other2 = cursor11.getString(cursor11.getColumnIndex("other2"));
                    ContentValues values = new ContentValues();
                    values.put("other", personid);
                    values.put("name", name);
                    values.put("tell", phone);
                    values.put("other1", other1);
                    values.put("other2", other2);
                    /**
                     *插入数据
                     * 参数一：要插入的表名
                     * 参数二：要插入的空数据所在的行数，第三个参数部位空，则此参数为null
                     * 参数三：要插入的数据
                     */
                    db.insert(TABLENAME, null, values);
                    i++;
                    k++;
                } else {
                    k++;
                }
                Message msg = new Message();
                msg.what = 123;
                msg.obj = k;
               // ActivityCommon.tf1.handler.sendMessage(msg);
                cursor11.moveToNext();

            } while (!cursor11.isLast());

        }
        db.execSQL("drop table " + time_cal);
        db.close();
        Message msg = new Message();
        msg.what = 1231;
       // ActivityCommon.tf1.handler.sendMessage(msg);
        return i;
    }

    public void updatamessage(String a) {
        ContentValues mm = new ContentValues();
        Cursor cursor11 = db.rawQuery("select * from " + TABLENAME+ " where tell = ?", new String[]{a});


            if (cursor11.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
            {
                String name = cursor11.getString(cursor11.getColumnIndex("name"));
                int personid = cursor11.getInt(cursor11.getColumnIndex("_id"));
                String other1 = cursor11.getString(cursor11.getColumnIndex("other1"));
                String other2 = cursor11.getString(cursor11.getColumnIndex("other2"));
                /*ContentValues values = new ContentValues();
                values.put("other", personid);
                values.put("name", name);
                values.put("tell", a);
                values.put("other1", "已发送");
                values.put("other2", other2);
                //String[] args = {String.valueOf(other1)};*/
                System.out.println("dddd----------->"+other1);
                //db.update(TABLENAME, mm, "other1=?", new String[]{String.valueOf(other1)});
                db.execSQL("update student1 set other2 = ? where tell =?",new String[]{"已发送",a});
                db.close();
            }
            else {
                db.close();
            }
    }

    public HashSet<String> gettime(){
        HashSet<String> ls=new HashSet<String>();
        Cursor cursor11 = db.rawQuery("select * from " + TABLENAME, null);
        if (cursor11.moveToFirst())//Move the cursor to the first row. This method will return false if the cursor is empty.
        {
            //String time2 = cursor11.getString(cursor11.getColumnIndex("other2"));
            while (!cursor11.isLast()) {

                String time = cursor11.getString(cursor11.getColumnIndex("other1"));
                String time2 = cursor11.getString(cursor11.getColumnIndex("other2"));
                //System.out.println("quanoooooo______________>"+time2);
                if(!time2.equals("已发送")) {
                    //System.out.println("meifasongoooooo______________>");
                    ls.add(time);
                   //
                }
                cursor11.moveToNext();
            }

        }
        db.close();
        if(ls.size()==0){
            ls.add("已发送");
            return ls;
        }else{
            return ls;
        }

    }


}
