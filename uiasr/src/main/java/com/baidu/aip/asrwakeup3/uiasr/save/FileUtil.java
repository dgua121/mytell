package com.baidu.aip.asrwakeup3.uiasr.save;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	/**
     * 根据byte数组，生成文件
     * filePath  文件路径
     * fileName  文件名称（需要带后缀，如*.jpg、*.java、*.xml）
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static byte[] getData(String filePath) {
    	byte[] fileByte=null;
    	File file = new File(filePath);
    	FileInputStream fis;
		try {
			fis = new FileInputStream(file);
    	ByteArrayOutputStream bos = new ByteArrayOutputStream(1024000);
    	
    	byte[] b = new byte[1024000];
    	int len = -1;
    	while((len = fis.read(b)) != -1) {
    	    bos.write(b, 0, len);
    	}
    	 fileByte = bos.toByteArray();
   
    } catch (Exception e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
		return fileByte;
}
}
