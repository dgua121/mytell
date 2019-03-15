package com.baidu.aip.asrwakeup3.uiasr.call.tell;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.Log;



public class ContactsUtils {

    /**
     * 修改联系人手机号
     *
     * @param rawRawContactId
     *
     */
    public void update(Context context, String rawRawContactId, String nuwname) {
        ContentValues values = new ContentValues();
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, nuwname);
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);

        String Where = ContactsContract.Data.RAW_CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?";
        String[] WhereParams = new String[]{rawRawContactId, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, };

        context.getContentResolver().update(ContactsContract.Data.CONTENT_URI, values, Where, WhereParams);
    }

    /**
     * 根据手机号查询通讯录中对应的ContactId
     * @param context
     * @param number
     * @return 通讯录中对应的ContactId
     */
    public static String getContactId(Context context, String number) {
        Cursor c = null;
        try {
            c = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
            if (c != null && c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    if (PhoneNumberUtils.compare(number, c.getString(1))) {
                        return c.getString(0);
                    }
                    c.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.e("tag", "getContactId error:", e);
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return null;
    }



}
