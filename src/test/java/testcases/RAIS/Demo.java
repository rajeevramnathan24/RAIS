package testcases.RAIS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import commonfunction.RAIS_applicationSpecificMethods;

public class Demo {
	
	
	public static void main(String[] args) {
		
		//add to numbers a + b = c		
		
		//ArrayList<ArrayList<Object> > menuNames = new ArrayList<ArrayList<Object> >();
		
		ArrayList<ArrayList<ArrayList<Object>>>  menuNames = new ArrayList<ArrayList<ArrayList<Object>>> ();
		
		
		menuNames= getDataFromExcel("Test");
		
		System.out.println(menuNames.size());
		
		ArrayList<ArrayList<Object>> entityNamesList = new ArrayList<ArrayList<Object>>();
		
		entityNamesList.add(menuNames.get(0).get(0));
		entityNamesList.add(menuNames.get(0).get(1));
		System.out.println("Values coming from 1st tab "+ entityNamesList);
		System.out.println("Count of items from 1st tab " +entityNamesList.size());
		
		ArrayList<Object> entNames = new ArrayList<Object> ();
		
		for(int entityListCounter = 0;entityListCounter <entityNamesList.size(); entityListCounter++ ) {
			
			
			entNames.add(menuNames.get(0).get(entityListCounter).get(1));
			
			String entityName = menuNames.get(0).get(entityListCounter).get(1).toString();
			String subMenuName = menuNames.get(0).get(entityListCounter).get(3).toString();
			
			System.out.println("entity name is " + entityName);
			System.out.println("sub menu name is " + subMenuName);
		}
		
		System.out.println(entNames);
		
		//ArrayList<Object> entNames = new ArrayList<Object> ();
		//entNames.add(menuNames.get(0).get(0).get(0));// = menuNames.get(1)
		//entNames.add(menuNames.get(0).get(0).get(1));
		//entNames.add(menuNames.get(0).get(0).get(2));
		
		
		
		
		
		
		ArrayList<ArrayList<Object> > x = new ArrayList<ArrayList<Object> >();
		
		
		
		//x= getDataFromExcel("Test","Nuclides" );
		
		System.out.println(x.size()); 
		
		int recordList = x.size();
		System.out.println(recordList);
		
		for (int i = 1; i<x.size();i++) {
			
			System.out.println("--------------------------------");
			System.out.println("Internal Name - "+x.get(i).get(0));
			System.out.println("Description - "+x.get(i).get(1));
			System.out.println("Singular Name - "+x.get(i).get(2));
			System.out.println("Plural Name - "+x.get(i).get(3));
			System.out.println("Entity Role Name - "+x.get(i).get(4));
			System.out.println("Publish and navigation Name - "+x.get(i).get(5));
			System.out.println("Sub-menu name - "+x.get(i).get(6));
			System.out.println("Sub-menu name - "+x.get(i).get(7));
			//System.out.println("Sub-menu name - "+x.get(i).get(11).toString());
			
		}
		
				
		System.out.println("The multidimensional arraylist is :");
	      //System.out.println(multi_dimensional().get(1).get(1));
		
			System.out.println(multi_dimensionalObject());
	      
	      System.out.println(multi_dimensionalObject().get(1).get(0));
	      
	      System.out.println(multi_dimensionalObject().get(1).get(1));
	      
	      System.out.println(multi_dimensionalObject().get(2).get(0));
	      
	      System.out.println(multi_dimensionalObject().get(2).get(1));
	      
	    //Adding elements to a List
	      //List<String> list = new ArrayList<String>();
	      //list.add(multi_dimensional().get(1).toString());
	     
	     // System.out.println(list.get(0));
	      
	      
	      
	      
	      
	      	      
	      
		
	}
	
	public static ArrayList<ArrayList<Integer> > multi_dimensional() {
		ArrayList<ArrayList<Integer> > x = new ArrayList<ArrayList<Integer> >();
	      x.add(new ArrayList<Integer>());
	      x.get(0).add(0, 45);
	      x.add(new ArrayList<Integer>(Arrays.asList(56, 67, 89)));
	      x.get(1).add(0, 67);
	      x.get(1).add(4, 456);
	      x.add(2, new ArrayList<>(Arrays.asList(23, 32)));
	      x.add(new ArrayList<Integer>(Arrays.asList(83, 64, 77)));
	      x.add(new ArrayList<>(Arrays.asList(8)));
	      return x;
	      
	   }
	
	public static ArrayList<ArrayList<Object>> multi_dimensionalObject() {
		
		ArrayList<ArrayList<Object> > x = new ArrayList<ArrayList<Object> >();
		
	      //x.add(new ArrayList<Object>());
	      //x.get(0).add(0, 45);
	      
	      x.add(0, new ArrayList<>(Arrays.asList(45, 99)));
	      x.add(1, new ArrayList<>(Arrays.asList("value0", "value1")));
	      x.add(2, new ArrayList<>(Arrays.asList("3recordSet0", "3recordSet1")));
	      
	      //x.add(new ArrayList<Object>(Arrays.asList(56, 67, 89)));
	      //x.get(1).add(0, 67);
	      //x.get(1).add(4, 456);
	      
	      //x.add(2, new ArrayList<>(Arrays.asList(23, 32)));
	      x.add(new ArrayList<Object>(Arrays.asList(83, 64, 77)));
	      x.add(new ArrayList<>(Arrays.asList(8)));
	      return x;
	      
	   }
	
	//Generic method to read excel file and return arraylist of data - xlsx type of file
		public static ArrayList<ArrayList<ArrayList<Object>>> getDataFromExcel(Object fileName) {

			//Define arraylist record set
			
			
			ArrayList<ArrayList<ArrayList<Object>>>  entireFileData= new ArrayList<ArrayList<ArrayList<Object>>> ();
					
					try {
						
						//File path and name
						FileInputStream file = new FileInputStream	(new File("C:\\workspace1\\com.apache.RAIS\\src\\test\\java\\com\\apache\\RAIS\\resources\\"+fileName.toString()+".xlsx"));
						
						//Create Workbook instance holding reference to .xlsx file
						XSSFWorkbook workbook = new XSSFWorkbook(file);

						//Get first/desired sheet from the workbook
						//XSSFSheet sheetold = workbook.getSheet(sheetName.toString());
						
						int sheetcount = workbook.getNumberOfSheets();
						
						
						
						System.out.println("Total Sheet in excel "+ sheetcount);
						
						for(int counter =0; counter<sheetcount; counter++) {
							
							ArrayList<ArrayList<Object> > fullRecordSet= new ArrayList<ArrayList<Object> >();
							
							System.out.println(workbook.getSheetName(counter));
						
							//Get first/desired sheet from the workbook
							XSSFSheet sheet = workbook.getSheetAt(counter); //workbook.getSheet(sheetName.toString());
						
						
						//iterate between the number of rows in excel sheet
						for(int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
							
							//RecordSet position
							int recordSetPosition = 0;
							
							//current row number
							Row row = sheet.getRow(rowNumber);
							
							//current column counter
							int colCounter = sheet.getRow(rowNumber).getLastCellNum();
							
							System.out.println("Total Rows "+sheet.getLastRowNum());						
							System.out.println("Total columns "+colCounter);						
							System.out.println("Current row number " + rowNumber);
								
								
							//Initialise and fetch the details into single record array list							
								ArrayList<Object> singleListOfRecord = new ArrayList<Object>();
								
								//loop between the columns
								for(int colNumber = 0; colNumber < colCounter; colNumber++ ) {
									
									//Initialise the contents to string variable to convert and trim to remove trailing spaces
									//String record = row.getCell(colNumber).toString().trim();
									
									//Assigning row value to cell for formatting of all values								
									Cell cell = row.getCell(colNumber);
									
									//Using date formatter to converting any data to string
									DataFormatter formatter = new DataFormatter();
									
									//Initialise the contents to string variable to convert and trim to remove trailing spaces
									String record = formatter.formatCellValue(cell).trim();
									
											//adding smallest record one by one								
											singleListOfRecord.add(colNumber, record);																	
									}							
															
								//Add single record set to full record set
								//fullRecordSet.add(singleListOfRecord);		
								fullRecordSet.add(recordSetPosition, singleListOfRecord);
								
								recordSetPosition = recordSetPosition +1;
						}
						
						entireFileData.add(counter, fullRecordSet);
						}
						
						
						
						//System.out.println(fullRecordSet);		

						//Close excel workbook
						workbook.close();	
						
						//return entire sheet in arraylist
						return entireFileData;
						
					} catch (FileNotFoundException e) {
						System.out.println("File reading error");
						
						e.printStackTrace();

					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						
						e.printStackTrace();

					}
					catch (Exception e) {
						System.out.println("Index out of bound ARRAY exception");
						
						e.printStackTrace();

					}		
					
					//return entire sheet in arraylist
					return entireFileData;
					
						
				}

	
	
	
}	








	// insurance policy
	
	// input age 0 to 18, then category = child
	
	// input age 19 to 59, then category = adult
	
	// input age 60 and above, then category = Sr. citizen
	


