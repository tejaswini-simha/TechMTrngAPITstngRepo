package com.techmapi.auto.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelHandler {

    public static List<Map<String, String>> readExcelToListOfHashMaps(String filePath) throws IOException {
        // Create a FileInputStream object to read the Excel file
        FileInputStream fis = new FileInputStream(new File(filePath));

        // Create an XSSFWorkbook (for .xlsx files)
        Workbook workbook = new XSSFWorkbook(fis);

        // Get the first sheet (or specify the sheet index)
        Sheet sheet = workbook.getSheetAt(0);

        // Create a List to store the HashMaps
        List<Map<String, String>> dataList = new ArrayList<>();

        // Get the header row (first row) to use as keys
        Row headerRow = sheet.getRow(0);
        int numOfColumns = headerRow.getPhysicalNumberOfCells();

        // Iterate through each row starting from the second row (index 1) because the first row is the header
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);

            // Create a HashMap for the current row
            Map<String, String> rowData = new HashMap<>();

            // Iterate through columns in the row
            for (int colNum = 0; colNum < numOfColumns; colNum++) {
                // Get the column name (header)
                String key = headerRow.getCell(colNum).getStringCellValue();

                // Get the value from the current cell (in the current row)
                String value = row.getCell(colNum) != null ? row.getCell(colNum).toString() : "";

                // Add the key-value pair to the row data (HashMap)
                rowData.put(key, value);
            }

            // Add the HashMap for the current row to the List
            dataList.add(rowData);
        }

        // Close the workbook and file input stream
        workbook.close();
        fis.close();

        return dataList;
    }

    public List<Map<String, String>> getExcelData(String filePath) {
    	
    	List<Map<String, String>> excelData = new ArrayList<Map<String, String>>();
        try {
            // Define the path to your Excel file
//            String filePath = "./src/test/resources/users.xlsx";

            // Call the method to read the Excel file and convert it to a List of HashMaps
            excelData = readExcelToListOfHashMaps(filePath);
            // Print the result
//            for (Map<String, String> row : excelData) {
//                for (Map.Entry<String, String> entry : row.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
//                System.out.println("---------------");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelData;
        
    }
}
