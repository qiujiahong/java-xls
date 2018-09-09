package com.example.demo1.domain;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Slf4j
public class WriteExcel {
    public static boolean write(HttpServletResponse response, ExcelFile excelFile){
        try
        {
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(excelFile.getFileName().getBytes("GBK"), "iso8859-1"));
            OutputStream os = response.getOutputStream();
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("第一页");

            //0、声明一个居中样式后面调用
            HSSFCellStyle style = workbook.createCellStyle();
            style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直
            style.setAlignment(HorizontalAlignment.CENTER);//水平

            //1、处理列宽度
            for (ExcelCellWidth item:excelFile.getCellsWidth()) {
                sheet.setColumnWidth(item.getCol(), item.getWidth());
            }

            //2、处理写入单个数据
            for (ExcelItem item:excelFile.getContents()) {
                if(item.needMerge()){
                    sheet.addMergedRegion(new CellRangeAddress(
                            item.getRow(), item.getEndRow(), item.getCol(), item.getEndCol()));
                }
                HSSFRow row = sheet.getRow(item.getRow());
                if(row == null)
                    row = sheet.createRow(item.getRow());  //行
                HSSFCell cell = row.createCell(item.getCol()); //列
                cell.setCellValue(item.getValue());
                cell.setCellStyle(style);
            }

            //3、处理写入连续数据
            for (ExcelListItem item:excelFile.getListContents()) {
                Integer rowNum = item.getRow();
                Integer colNum = item.getCol();
                for (String value:item.getValues()) {
                    HSSFRow row = sheet.getRow(rowNum);
                    if(row==null)
                        row = sheet.createRow(rowNum);  //行
                    HSSFCell cell = row.createCell(colNum); //列
                    cell.setCellValue(value);
                    cell.setCellStyle(style);
                    colNum++;
                }
            }
            workbook.write(os);
            os.flush();
            os.close();
            return  true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return  false;
        }
    }
}
