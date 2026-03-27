package utils;

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

   
    private static final Logger log =
            LogManager.getLogger(ExcelUtil.class);

    public static Object[][] getData(String path, String LoginData) {

        try {

            log.info("Reading Excel file from path: {}", path);

            FileInputStream fis = new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis);

            Sheet sheet = wb.getSheet(LoginData);

            if (sheet == null) {
                log.error("Sheet NOT FOUND: {}", LoginData);
                throw new RuntimeException("Sheet NOT FOUND: " + LoginData);
            }

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            log.info("Total Rows: {}", rows);
            log.info("Total Columns: {}", cols);

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {

                Row row = sheet.getRow(i);

                for (int j = 0; j < cols; j++) {

                    Cell cell = row.getCell(j);

                    data[i - 1][j] =
                            (cell == null) ? "" : cell.toString();
                }
            }

            wb.close();
            fis.close();

            log.info("Excel data successfully loaded");

            return data;

        } catch (Exception e) {

            log.error("Excel Read Failed", e);

            throw new RuntimeException("Excel Read Failed", e);
        }
    }
}