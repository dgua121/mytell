package com.baidu.aip.asrwakeup3.uiasr.Service;

import java.io.UnsupportedEncodingException;

public class ByteOrStringHelper {
    private static String CHAR_ENCODE = "ISO-8859-1";

    public static void configCharEncode(String str) {
        CHAR_ENCODE = str;
    }

    public static byte[] StringToByte(String str) {
        return StringToByte(str, CHAR_ENCODE);
    }

    public static String ByteToString(byte[] bArr) {
        return ByteToString(bArr, CHAR_ENCODE);
    }

    public static byte[] StringToByte(String str, String str2) {
        byte[] bArr = (byte[]) null;
        if (str != null) {
            try {
                if (!str.trim().equals("")) {
                    return str.getBytes(str2);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return bArr;
            }
        }
        return new byte[0];
    }

    public static String ByteToString(byte[] bArr, String str) {
        String str2 = (String) null;
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        }
    }
}
