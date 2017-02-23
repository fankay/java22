package com.kaishengit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {

        //1.创建工作表
        Workbook workbook = new HSSFWorkbook();

        //2.创建sheet页
        Sheet sheet = workbook.createSheet("2017-02-23财务流水");

        //3.创建行 从0开始
        Row row = sheet.createRow(0);
        Cell c1 = row.createCell(0);
        c1.setCellValue("业务流水号");
        row.createCell(1).setCellValue("创建日期");
        row.createCell(2).setCellValue("类型");
        row.createCell(3).setCellValue("金额");
        row.createCell(4).setCellValue("业务模块");
        row.createCell(5).setCellValue("业务流水号");
        row.createCell(6).setCellValue("状态");
        row.createCell(7).setCellValue("备注");
        row.createCell(8).setCellValue("创建人");
        row.createCell(9).setCellValue("确认人");
        row.createCell(10).setCellValue("确认日期");





        FileOutputStream fileOutputStream = new FileOutputStream("D:/data.xls");
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();




    }
}
