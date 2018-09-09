package com.example.demo1.domain;

import lombok.Data;
import java.util.List;

@Data
public class ExcelListItem {
    private Integer row;    //行，最小为0
    private Integer col;   //列，最小为0
    private List<String> values; //具体的值

    public ExcelListItem(Integer row, Integer col, List<String> values) {
        this.row = row;
        this.col = col;
        this.values = values;
    }
}
