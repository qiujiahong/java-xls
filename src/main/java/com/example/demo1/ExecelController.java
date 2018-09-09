package com.example.demo1;

import com.example.demo1.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;

@RestController
@Slf4j
public class ExecelController {

    @RequestMapping("/get")
    String get(HttpServletRequest request,
               HttpServletResponse response) {
        try {

            ExcelFile excelFile = new ExcelFile();
            //添加列宽度属性
            excelFile.addWidth(new ExcelCellWidth(0,30));
            //添加一格子内容
            excelFile.add(new ExcelItem(0,0,"test1"));
            excelFile.add(new ExcelItem(0,2,"test2"));
            excelFile.add(new ExcelItem(3,4,3,3,"测试"));//3~4行，3列
            //添加一列内容
            excelFile.add(new ExcelListItem(4,1, Arrays.asList("你好","我不好")));

            //写文件
            WriteExcel.write( response, excelFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "OK";
    }

    @RequestMapping("/get1")
    String get1(HttpServletRequest request,
               HttpServletResponse response) {
        try {
            String fileName = "文件.xls";
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("GBK"), "iso8859-1"));
            OutputStream os = response.getOutputStream();

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("第一页");

            //columnIndex - 列（0～n） ,width - 宽度 in units of 1/256th of a character width 3000的话就是11.7左右
            //sheet.setColumnWidth(0, 8000);
            HSSFRow row = sheet.createRow(0);  //行
            HSSFCell cell = row.createCell(0); //列
            cell.setCellValue("测试");

            sheet.addMergedRegion(new CellRangeAddress(1, 4, 3, 5));

            HSSFRow row1 = sheet.createRow(1);  //行
            HSSFCell cell1 = row1.createCell(3); //列
            cell1.setCellValue("测试123");

            HSSFCellStyle style = workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
            style.setAlignment(HorizontalAlignment.CENTER);//水平
            cell1.setCellStyle(style);

            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "OK";
    }

}
