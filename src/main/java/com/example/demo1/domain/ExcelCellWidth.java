package com.example.demo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExcelCellWidth {
    private Integer col;
    private Integer width;  //多少个字宽度

    /**
     *
     * @return
     */
    public Integer getWidth() {
        return width*256;
    }
}
