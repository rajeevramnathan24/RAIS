package com.apache.RAIS.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
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
			FileInputStream file = new FileInputStream(new File("C:\\workspace1\\com.apache.RAIS\\src\\test\\java\\com\\apache\\RAIS\\resources\\businessEntityList.xlsx"));

			//Flag to set sanity/ custom or all test cases
			//String pack = "Name";

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet("entityDetails");

			int newRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			//int colCount = sheet.getColumnWidth(columnIndex);


			for(int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
				Row row = sheet.getRow(rowNumber);
				
				ArrayList<Object> facDetail = new ArrayList<Object>();
				
				facDetail.add(0, row.getCell(1).getStringCellValue().toString());
				facDetail.add(1, row.getCell(2).getStringCellValue().toString());
				facDetail.add(2, row.getCell(3).getStringCellValue().toString());
				facDetail.add(3, row.getCell(4).getStringCellValue().toString());
				facDetail.add(4, row.getCell(5).getStringCellValue().toString());
				facDetail.add(5, row.getCell(6).getStringCellValue().toString());
				facDetail.add(6, row.getCell(8).getStringCellValue().toString());
				facDetail.add(7, row.getCell(9).getStringCellValue().toString());
				facDetail.add(8, row.getCell(10).getStringCellValue().toString());
				

				//System.out.println(facDetail);
				//System.out.println(row.getCell(1).getStringCellValue().toString());
				//System.out.println(row.getCell(2).getStringCellValue().toString());
				
				ArrayList<ArrayList<Object> > multipleEntities = new ArrayList<ArrayList<Object>>();
				
				multipleEntities.add(new ArrayList<Object>());
				
				//multipleEntities.get(0).add(0, "Ignore");
				
				multipleEntities.get(0).add(new ArrayList<Object>(Arrays.asList(
						
						row.getCell(1).getStringCellValue().toString(),
						row.getCell(2).getStringCellValue().toString(),
						row.getCell(3).getStringCellValue().toString(),
						row.getCell(4).getStringCellValue().toString(),
						row.getCell(5).getStringCellValue().toString(),
						row.getCell(6).getStringCellValue().toString(),
						row.getCell(7).getStringCellValue().toString(),
						row.getCell(8).getStringCellValue().toString(),
						row.getCell(9).getStringCellValue().toString()					
						
						)));
				
				System.out.println(multipleEntities);	
				
//				multipleEntities.get(0).add(0,"abc");
//				multipleEntities.get(1).add(0,"xyz");
//				multipleEntities.get(1).add(1,"ijk");
				
				
//				multipleEntities.get(0).add(0, row.getCell(1).getStringCellValue().toString());
//				multipleEntities.get(0).add(1, row.getCell(2).getStringCellValue().toString());
//				multipleEntities.get(0).add(2, row.getCell(3).getStringCellValue().toString());
//				multipleEntities.get(0).add(3, row.getCell(4).getStringCellValue().toString());
//				multipleEntities.get(0).add(4, row.getCell(5).getStringCellValue().toString());
//				multipleEntities.get(0).add(5, row.getCell(6).getStringCellValue().toString());
//				multipleEntities.get(0).add(6, row.getCell(7).getStringCellValue().toString());
//				multipleEntities.get(0).add(7, row.getCell(8).getStringCellValue().toString());
//				multipleEntities.get(0).add(8, row.getCell(9).getStringCellValue().toString());
				
						

//				for(int columnNumber = 0; columnNumber < row.getLastCellNum(); columnNumber++) {
//					Cell cell = row.getCell(columnNumber);
//					if(cell != null) {
//						// do something with the cell
//						System.out.println(row.getCell(1).getStringCellValue().toString());
//						System.out.println(row.getCell(2).getStringCellValue().toString());
//					}
//				}
			}



			//        for (Row row : sheet) {
			//            for (Cell cell : row) {
			//              //System.out.print(cell + "| ");
			//            	
			//            	//String facName =row.getCell(0).getStringCellValue().toString();
			//            	//String facName =cell.getColumnIndex()
			//              
			//              //System.out.print(cell + "| ");
			//              
			//            	//System.out.print(facName + "| ");
			//              
			//            }
			//            System.out.print("\n");
			//          }



			//        for (int rowValue = 1; rowValue <= newRowCount ; rowValue ++) {
			//        	
			//        	if (pack.equalsIgnoreCase("Name")) {
			//				String rowName_entityName = sheet.getRow(rowValue).getCell(1).getStringCellValue();
			//				//System.out.println(entityNameValue );			
			//				
			//				//String facName = sheet.getRow(rowValue).getCell(6).getStringCellValue();
			//				
			//				Row row = CellUtil.getRow(newRowCount, sheet);
			//				
			//				//HSSFCell cell = row.getCell(columnNumber);
			//				
			//			    //Cell cell = CellUtil.getCell(row, columnIndex);
			//				
			//				
			//				
			//				
			//				//System.out.println(rowName_entityName );
			//				
			//				
			//				if(rowName_entityName== "Yes") {
			//					TC1_runStatus = true;
			//					
			//					
			//				}
			//			}
			//        	else if (pack.equalsIgnoreCase("custom")) {
			//        		String cellvalue = sheet.getRow(rowValue).getCell(5).getStringCellValue();
			//				System.out.println(cellvalue);
			//        	}
			//        }

			workbook.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
