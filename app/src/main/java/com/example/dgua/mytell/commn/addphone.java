package com.example.dgua.mytell.commn;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Data;

public class addphone  {
    Context ct;
public  addphone(Context a){
    ct=a;
}
        // 一个添加联系人信息的例子
        public void addContact(String name, String phoneNumber) {
            // 创建一个空的ContentValues
            ContentValues values = new ContentValues();

            // 向RawContacts.CONTENT_URI空值插入，
            // 先获取Android系统返回的rawContactId
            // 后面要基于此id插入值
            Uri rawContactUri = ct.getContentResolver().insert(RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(rawContactUri);
            values.clear();

            values.put(Data.RAW_CONTACT_ID, rawContactId);
            // 内容类型
            values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
            // 联系人名字
            values.put(StructuredName.GIVEN_NAME, name);
            // 向联系人URI添加联系人名字
            ct.getContentResolver().insert(Data.CONTENT_URI, values);
            values.clear();

            values.put(Data.RAW_CONTACT_ID, rawContactId);
            values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
            // 联系人的电话号码
            values.put(Phone.NUMBER, phoneNumber);
            // 电话类型
            values.put(Phone.TYPE, Phone.TYPE_MOBILE);
            // 向联系人电话号码URI添加电话号码
            ct.getContentResolver().insert(Data.CONTENT_URI, values);
            values.clear();

            values.put(Data.RAW_CONTACT_ID, rawContactId);
            values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);
            // 联系人的Email地址
            values.put(Email.DATA, "zhangphil@xxx.com");
            // 电子邮件的类型
            values.put(Email.TYPE, Email.TYPE_WORK);
            // 向联系人Email URI添加Email数据
            ct.getContentResolver().insert(Data.CONTENT_URI, values);

           // Toast.makeText(this, "联系人数据添加成功", Toast.LENGTH_SHORT).show();
        }
    }