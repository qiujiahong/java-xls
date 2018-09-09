## 前言
本文介绍如何使用java操作excel，本文默认使用spring boot做实验,代码内需要加入依赖： 

```xml
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi</artifactId>
            <version>3.17</version>
        </dependency>
```

## 代码

### 相关对象

```
HSSFWorkbook excel文档对象
HSSFSheet excel的sheet HSSFRow excel的行
HSSFCell excel的单元格 HSSFFont excel字体
HSSFName 名称 HSSFDataFormat 日期格式
HSSFHeader sheet头
HSSFFooter sheet尾
HSSFCellStyle cell样式
HSSFDateUtil 日期
HSSFPrintSetup 打印
HSSFErrorConstants 错误信息表
```

### 得到Excel常用对象    
```
OIFSFileSystem fs=newPOIFSFileSystem(new FileInputStream("d:/test.xls"));   
//得到Excel工作簿对象    
HSSFWorkbook wb = new HSSFWorkbook(fs);  
//得到Excel工作表对象    
HSSFSheet sheet = wb.getSheetAt(0);   
//得到Excel工作表的行    
HSSFRow row = sheet.getRow(i);  
//得到Excel工作表指定行的单元格    
HSSFCell cell = row.getCell((short) j);  
cellStyle = cell.getCellStyle();//得到单元格样式  
```

### 建立Excel常用对象

```
HSSFWorkbook wb = new HSSFWorkbook();//创建Excel工作簿对象   
HSSFSheet sheet = wb.createSheet("new sheet");//创建Excel工作表对象     
HSSFRow row = sheet.createRow((short)0); //创建Excel工作表的行   
cellStyle = wb.createCellStyle();//创建单元格样式   
row.createCell((short)0).setCellStyle(cellStyle); //创建Excel工作表指定行的单元格   
row.createCell((short)0).setCellValue(1); //设置Excel工作表的值  
```

### 简单的样例

```
@RequestMapping("/get")
    String get(HttpServletRequest request,
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
```


> 更多详细的介绍，可参考[这篇文章](https://www.cnblogs.com/fqfanqi/p/6172223.html)