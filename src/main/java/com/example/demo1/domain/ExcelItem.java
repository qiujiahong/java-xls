package com.example.demo1.domain;


import lombok.Data;

@Data
public class ExcelItem {
    private Integer row;    //行，最小为0
    private Integer col;   //列，最小为0
    private String value;

    private Integer endRow; //结束行，如果结束列和列不相等,则表示需要合并
    private Integer endCol;//结束列，如果结束列和列不相等,则表示需要合并

    public ExcelItem(Integer row, Integer col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public ExcelItem(Integer row, Integer endRow, Integer endCol, Integer col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.endRow = endRow;
        this.endCol = endCol;
    }

    /**
     * 是否需要合并
     *
     * @return true-需要合并单元格
     */
    public boolean needMerge() {
        if (endCol != null && endRow != null &&
                (!endCol.equals(0) || !endRow.equals(0))) {
            return true;
        }
        return false;
    }
}
