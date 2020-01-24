package testSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestSuite {

	//Test case run - yes/ no flag to be updated here
	public static boolean TC1_runStatus; //Verify login screen default values
	public static boolean TC2_runStatus; //Login application
	public static boolean TC3_runStatus; // Testing failed test cases
	public static boolean TC4_runStatus; // No execution TBD
	public static boolean TC5_runStatus; // No execution TBD
	public static boolean TC6_runStatus;  // Validation test case for Add new permission page
	public static boolean TC7_runStatus; // Validation test case for Add new Retriction page
	public static boolean TC8_runStatus; // Passed
	public static boolean TC9_runStatus; // failed
	public static boolean TC10_runStatus;
	public static boolean TC11_runStatus;

	public static void TestPack(Object packtype) {
		
		System.out.println();
		
		
	}

	public static void Sanity_TestCases_Set( ) {

		//Test case run - yes/ no flag to be updated here
		TC1_runStatus = true; //Verify login screen default values
		TC2_runStatus = true; //Login application
		TC3_runStatus = false; // Testing failed test cases
		TC4_runStatus = false; // No execution TBD
		TC5_runStatus = false; // No execution TBD
		TC6_runStatus = true;  // Validation test case for Add new permission page
		TC7_runStatus = true; // Validation test case for Add new Retriction page
		TC8_runStatus = true; // Passed
		TC9_runStatus = true; // failed
		TC10_runStatus = false;
		TC11_runStatus = false;

	}

	public static void All_TestCases_Set( ) {

		//Test case run - yes/ no flag to be updated here
		TC1_runStatus = true; //Verify login screen default values
		TC2_runStatus = true; //Login application
		TC3_runStatus = true; // Testing failed test cases
		TC4_runStatus = true; // No execution TBD
		TC5_runStatus = true; // No execution TBD
		TC6_runStatus = true;  // Validation test case for Add new permission page
		TC7_runStatus = true; // Validation test case for Add new Retriction page
		TC8_runStatus = true; // Passed
		TC9_runStatus = true; // failed
		TC10_runStatus = true;
		TC11_runStatus = true;

	}

	public static void Custom_TestCases_Set( ) {

		//Test case run - yes/ no flag to be updated here
		TC1_runStatus = true; //Verify login screen default values
		TC2_runStatus = true; //Login application
		TC3_runStatus = true; // Testing failed test cases
		TC4_runStatus = false; // No execution TBD
		TC5_runStatus = false; // No execution TBD
		TC6_runStatus = true;  // Validation test case for Add new permission page
		TC7_runStatus = false; // Validation test case for Add new Retriction page
		TC8_runStatus = true; // Passed
		TC9_runStatus = false; // failed
		TC10_runStatus = false;
		TC11_runStatus = false;

	}

	public static void Read_excel_SheetinArray() {

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
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Read_excel_SheetDirect() {

		try {
			FileInputStream file = new FileInputStream(new File("C:\\workspace1\\com.apache.RAIS\\src\\test\\java\\com\\apache\\RAIS\\resources\\Testcases.xlsx"));

			//Flag to set sanity/ custom or all test cases
			String pack = "sanity";

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet("TCstatus");

			int newRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			//Hard coding of row/ col value of true/ false to be passed

			workbook.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Test case Names and description
	public final static String TC1_testName = "Default value verification"; 
	public final static String TC1_testDescription = "Verify default values of Login Screen"; 

	public final static String TC2_testName = "Login Application"; 
	public final static String TC2_testDescription = "Verify Admin User is able to login into RAIS Application"; 

	public final static String TC3_testName = "FAILED Test case 3"; 
	public final static String TC3_testDescription = "FAILED Test case 3 description"; 

	public final static String TC4_testName = "Test case 4"; 
	public final static String TC4_testDescription = "Test case 4 description"; 

	public final static String TC5_testName = "Test case 5"; 
	public final static String TC5_testDescription = "Test case 5 description"; 

	public final static String TC6_testName = "Verify Validation messages"; 
	public final static String TC6_testDescription = "Verify Validation messages on Add new Permission page"; 

	public final static String TC7_testName = "Verify Validation messages"; 
	public final static String TC7_testDescription = "Verify Validation messages on Add new Restriction page"; 

	public final static String TC8_testName = "Verify Validation messages"; 
	public final static String TC8_testDescription = "Verify Validation messages on Add new Role page"; 

	public final static String TC9_testName = "Failed Test case 9"; 
	public final static String TC9_testDescription = "FAILED Test case 9 description"; 

}
