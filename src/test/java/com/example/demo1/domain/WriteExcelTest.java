package com.example.demo1.domain;

import org.junit.Test;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class WriteExcelTest {

    @Test
    public void write() {
        File file = FileService.open("./out.csv");
        PrintStream ps = FileService.getPrintStream(file);
        ps.append("name,age,address\n");
        ps.append("nick,12,nanshan\n");
        ps.append("12中文测试,12,南山\n");
        ps.close();
    }
}