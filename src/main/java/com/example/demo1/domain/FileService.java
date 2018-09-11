package com.example.demo1.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileService {
    public static void WriteStringToFile(String filePath,String text) {
        try {
            if(filePath == null) {
                return;
            }
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file,true));
            //ps.println("http://www.jb51.net");// 往文件里写入字符串
            ps.append(text);// 在已有的基础上添加字符串
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {

        }
    }



    public static void delFile(String filePath){
        if(filePath == null) {
            return;
        }
        File file=new File(filePath);
        if(file.exists()&&file.isFile())
            file.delete();
    }

}
