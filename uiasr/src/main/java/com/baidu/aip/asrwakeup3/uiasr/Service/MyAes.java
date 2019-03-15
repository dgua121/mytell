package com.baidu.aip.asrwakeup3.uiasr.Service;

import android.support.v4.view.MotionEventCompat;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MyAes {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] encry(byte[] raw, byte[] input) throws Exception {  // 加密
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES"); // 根据上一步生成的密匙指定一个密匙（密匙二次加密？）
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");  // 获得Cypher实例对象
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);  // 初始化模式为加密模式，并指定密匙
        byte[] encode = cipher.doFinal(input);  // 执行加密操作。 input为需要加密的byte数组
        System.out.println("加密对比1传入"+parseByte2HexStr(input));
        System.out.println("加密对比1传出"+parseByte2HexStr(encode));
        return Base64.encode(encode,Base64.DEFAULT);                         // 返回加密后的密文（byte数组)
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] decry(byte[] raw, byte[] encode) throws Exception{ // 解密


        byte[] m= Base64.decode(encode, Base64.DEFAULT);
        System.out.println("解密对比1"+parseByte2HexStr(m));
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        System.out.println("jie密开始111");
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        System.out.println("jie密开始222");

        cipher.init(Cipher.DECRYPT_MODE, keySpec,new IvParameterSpec(
                new byte[cipher.getBlockSize()]));   // 解密的的方法差不多，只是这里的模式不一样
        System.out.println("jie密开始333");


        byte[] decode = cipher.doFinal(m);  // 加解密都通过doFinal方法来执行最终的实际操作
        System.out.println("jie密开始555"+parseByte2HexStr(decode));
        return decode;
    }



    public static String parseByte2HexStr(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            if (toHexString.length() == 1) {
                toHexString = new StringBuffer().append('0').append(toHexString).toString();
            }
            stringBuffer.append(toHexString.toUpperCase());
        }
        return stringBuffer.toString();
    }


    private static byte[] getRawKey(byte[] seed) throws NoSuchAlgorithmException{



        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG"); // 获得一个随机数，传入的参数为默认方式。
        sr.setSeed(seed);  // 设置一个种子，这个种子一般是用户设定的密码。也可以是其它某个固定的字符串
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");  // 获得一个key生成器（AES加密模式）
        keyGen.init(128, sr);      // 设置密匙长度128位
        SecretKey key = keyGen.generateKey();  // 获得密匙
        byte[] raw = key.getEncoded();   // 返回密匙的byte数组供加解密使用
        return raw;
    }

    public static byte[] parseHexStr2Byte(String str) throws NoSuchAlgorithmException{
       if (str.length() < 1) {
            return (byte[]) null;
        }
        System.out.println("zifu11"+str.length());
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {

            bArr[i] = (byte) (charToByte(str.toCharArray()[2*i]) << 4 | charToByte(str.toCharArray()[2*i + 1]));

            /*bArr[i] = (byte) ((Integer.parseInt(str.substring(i * 2, (i * 2) + 1), 16) * 16)
                    + Integer.parseInt(str.substring((i * 2) + 1, (i * 2) + 2), 16));*/
        }

        System.out.println("zifu22"+str);
        return bArr;
    }

    public static  byte[] decryptString(String seed, String encode) throws Exception{
        System.out.println("jie密开始1"+encode);
        byte[] raw1 = getRawKey(parseHexStr2Byte(seed));
        System.out.println("jie密开始2"+seed);
        byte[]  decode = decry(raw1, parseHexStr2Byte(encode));
        // byte[]  decode = decry(raw1, parseHexStr2Byte(encode));
        System.out.println("jie密开始3"+parseByte2HexStr(decode));
        return decode;
    }

    public static  byte[] encryptString(String seed, String input) throws Exception{
        byte[] raw = getRawKey(parseHexStr2Byte(seed));
        byte[] encode = encry(raw, parseHexStr2Byte(input));

        System.out.println("加密流程1"+parseByte2HexStr(input.getBytes()));
        System.out.println("加密开始2"+seed);
        System.out.println("加密开始3"+encode);


// return new String(encode);  // 这里返回加密后的密文字符串是有问题的，后面再解释。
        return encode;
    }



    public static byte[] encrypt2(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            byte[] bytes = str.getBytes("utf-8");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return (byte[]) null;
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return (byte[]) null;
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return (byte[]) null;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return (byte[]) null;
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return (byte[]) null;
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return (byte[]) null;
        }
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

