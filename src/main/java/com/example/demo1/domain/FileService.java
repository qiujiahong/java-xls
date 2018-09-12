package com.example.demo1.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileService {

    /**
     * 打开文件
     * @param filePath
     * @return
     */
    public static   File  open(String filePath){
        File file=null;
        try {
             file = new File(filePath);
            delFile(file);
            PrintStream ps;
            ps = new PrintStream(new FileOutputStream(file,true));
            ps.append(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            ps.close();
            return  file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  null;
        }finally {
        }
    }

    /**
     * 创建一个写入流
     * @param file
     * @return
     */
    public static PrintStream getPrintStream(File file){
        try {
            PrintStream ps;
            ps = new PrintStream(new FileOutputStream(file,true));
            return ps;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 删除文件
     * @param file
     */
    public static void delFile(File file){
        if(file.exists()&&file.isFile())
            file.delete();
    }

}
