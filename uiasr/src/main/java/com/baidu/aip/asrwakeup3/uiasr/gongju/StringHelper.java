package com.baidu.aip.asrwakeup3.uiasr.gongju;

public class StringHelper {
    public static String toDBC(String str) {
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] == '　') {
                toCharArray[i] = ' ';
            } else if (toCharArray[i] > '＀' && toCharArray[i] < '｟') {
                toCharArray[i] = (char) (toCharArray[i] - 652480);
            }
            i++;
        }
        return new String(toCharArray);
    }
}
