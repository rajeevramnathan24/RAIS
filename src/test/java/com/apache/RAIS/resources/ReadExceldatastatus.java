package com.apache.RAIS.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExceldatastatus {

	public static Object TC1_runStatus = null; //Verify login screen default values
	public final static boolean TC2_runStatus = true; //Login application
	public final static boolean TC3_runStatus = true; // Testing failed test cases
	public final static boolean TC4_runStatus = true; // No execution TBD
	public final static boolean TC5_runStatus = true; // No execution TBD
	public final static boolean TC6_runStatus = true;  // Validation test case for Add new permission page
	public final static boolean TC7_runStatus = true; // Validation test case for Add new Retriction page
	public final static boolean TC8_runStatus = true; // Passed
	public final static boolean TC9_runStatus = true; // failed
	public final static boolean TC10_runStatus = true;
	public final static boolean TC11_runStatus = true;
	
	public static void main(String[] args) throws Exception {
		try {
			FileInputStream file = new FileInputStream(new File("C:\\workspace1\\com.apache.RAIS\\src\\test\\java\\com\\apache\\RAIS\\resources\\Testcases.xlsx"));
		
			//Flag to set sanity/ custom or all test cases
		 String pack = "sanity";
		 
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        
      //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheet("TCstatus");
			
        int newRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        
        for (int colCount = 1; colCount <= newRowCount ; colCount ++) {
        	
        	if (pack.equalsIgnoreCase("sanity")) {
				String cellvalue = sheet.getRow(colCount).getCell(3).getStringCellValue();
				System.out.println(cellvalue);
				
				
				
				if(cellvalue== "Yes") {
					TC1_runStatus = true;
					
					
				}
			}
        	else if (pack.equalsIgnoreCase("custom")) {
        		String cellvalue = sheet.getRow(colCount).getCell(5).getStringCellValue();
				System.out.println(cellvalue);
        	}
        }
        
        workbook.close();
        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
