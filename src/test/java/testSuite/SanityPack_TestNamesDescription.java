package testSuite;



public class SanityPack_TestNamesDescription {

	//Test case run - yes/ no flag to be updated here
	public final static boolean TC1_runStatus = false; //Verify login screen default values
	public final static boolean TC2_runStatus = false; //Verify forgot password feature
	public final static boolean TC3_runStatus = false; // Login application and verify headers
	
	public final static boolean TC8_runStatus = false; // Verify for new Data role, add, edit and delete feature
	public final static boolean TC9_runStatus = false; // Verify for a given data role, permissions can be added, edited and deleted
	public final static boolean TC10_runStatus = false; //// Verify for new Functional role, add, edit and delete feature
	public final static boolean TC11_runStatus = false;// Verify CRUD operations for security profile
	public final static boolean TC12_runStatus = false;// Verify CRUD operations for users
	public final static boolean TC13_runStatus = true;// Verify CRUD operations for Entity
	
	public final static boolean TC4_runStatus = false; // No execution TBD
	public final static boolean TC5_runStatus = false; // No execution TBD
	public final static boolean TC6_runStatus = false;  // Validation test case for Add new permission page
	public final static boolean TC7_runStatus = false; // Validation test case for Add new Retriction page
	
	
	
	//Validation Tests
	public final static boolean validationTC1_runStatus = false; //Verify login screen default values
	public final static String validationTC1_testName = "Default value verification"; 
	public final static String validationTC1_testDescription = "Verify default values of Login Screen";
	
	public final static boolean validationTC2_runStatus = false; //Verify forgot password feature
	public final static String validationTC2_testName = "Default value verification"; 
	public final static String validationTC2_testDescription = "Verify Forgot password feature";
	
	
	
	
	// Test case Names and description
	public final static String TC1_testName = "Default value verification"; 
	public final static String TC1_testDescription = "Verify default values of Login Screen"; 
	
	public final static String TC2_testName = "Default value verification"; 
	public final static String TC2_testDescription = "Verify Forgot password feature"; 
	
	public final static String TC3_testName = "Login Application"; 
	public final static String TC3_testDescription = "Verify Admin User is able to login into RAIS Application"; 
	
	public final static String TC4_testName = "Test case 4"; 
	public final static String TC4_testDescription = "Test case 4 description"; 
	
	public final static String TC5_testName = "Test case 5"; 
	public final static String TC5_testDescription = "Test case 5 description"; 
	
	public final static String TC6_testName = "Verify Validation messages"; 
	public final static String TC6_testDescription = "Verify Validation messages on Add new Permission page"; 
	
	public final static String TC7_testName = "Verify Validation messages"; 
	public final static String TC7_testDescription = "Verify Validation messages on Add new Restriction page"; 
	
	public final static String TC8_testName = "Verify CRUD Operations on Data role"; 
	public final static String TC8_testDescription = "Verify user is able to Add new role, Edit existing role and delete the newly created role"; 
	
	public final static String TC9_testName = "Verify Add new permissions"; 
	public final static String TC9_testDescription = "Verify user is able to add/ Update permission for new role"; 
	
	public final static String TC10_testName = "Verify CRUD Operations on Functional role"; 
	public final static String TC10_testDescription = "Verify user is able to Add new role, Edit existing role and delete the newly created role";
	
	public final static String TC11_testName = "Verify CRUD Operations on Security role"; 
	public final static String TC11_testDescription = "Verify user is able to Add new Security role, Edit existing role and delete the newly created role";
	
	public final static String TC12_testName = "Verify CRUD Operations on Users"; 
	public final static String TC12_testDescription = "Verify Admin user is able to Edit existing user";
	
	public final static String TC13_testName = "Verify CRUD Operations on Entity"; 
	public final static String TC13_testDescription = "Verify Admin user is able to Add, Edit and delete Entity";
	
	
	
}
