package org.example.utils;

import org.apache.logging.log4j.core.util.JsonUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;

public class UtilExcel {




    public  static Workbook book;
    public  static Sheet sheet;

    public  static  String SHEET_NAME =System.getProperty("user.dir")+"src/main/java/org/example/resources/TestData1.xlsx";



    @Test
    public Object[][] getTestDataFromExcel(String sheet_name) throws IOException {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(SHEET_NAME);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        

        book = WorkbookFactory.create(fis);
        sheet = book.getSheet(sheet_name);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {

                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }

        }

        return data;
    }



    @DataProvider
    public Object[][] getData() throws IOException {
        return  getTestDataFromExcel("Sheet1");
    }
}
