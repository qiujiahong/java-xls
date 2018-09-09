package com.example.demo1.domain;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelFile {
    String fileName = "文件.xls";

    List<ExcelItem> contents = new ArrayList<>();           //单个分离元素
    List<ExcelListItem> listContents = new ArrayList<>();    //加入连续的一行

    List<ExcelCellWidth> cellsWidth = new ArrayList<>();    //设置设宽度属性


    public void add(ExcelListItem excelListItem) {
        listContents.add(excelListItem);
    }

    /**
     * 添加元素
     *
     * @param excelItem
     */
    public void add(ExcelItem excelItem) {
        contents.add(excelItem);
    }

    /**
     * 添加宽度
     * @param width
     */
    public void addWidth(ExcelCellWidth width) {
        cellsWidth.add(width);
    }

    /**
     * 添加宽度
     *
     * @param widths
     */
    public void addWidth(List<ExcelCellWidth> widths) {
        for (ExcelCellWidth item : widths
        ) {
            cellsWidth.add(item);
        }
    }
}
