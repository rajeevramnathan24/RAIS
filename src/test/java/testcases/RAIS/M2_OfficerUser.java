package testcases.RAIS;

import org.testng.annotations.Test;

import testSuite.*;

import commonfunction.BaseClass;

import commonfunction.GenericMethods;
import commonfunction.RAIS_applicationSpecificMethods;
import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewAttributePage;
import pageLocators_Elements.RAIS.AddNewEntityFormDetailsPage;
import pageLocators_Elements.RAIS.AddNewEntityPage;
import pageLocators_Elements.RAIS.AddNewPermRestrictionsPage;
import pageLocators_Elements.RAIS.AddNewRolePage;
import pageLocators_Elements.RAIS.AddNewSecurityProfilePage;
import pageLocators_Elements.RAIS.AddNewUserDetailsPage;
import pageLocators_Elements.RAIS.DashboardPage;
import pageLocators_Elements.RAIS.DataRoles_FunctionalRolesPage;
import pageLocators_Elements.RAIS.EntityFormListingPage;
import pageLocators_Elements.RAIS.EntityInventoryDetailsPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.ForgotPasswordPage;
import pageLocators_Elements.RAIS.FormDesignerPage;
import pageLocators_Elements.RAIS.LinkedFormListingPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.security.GeneralSecurityException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class M2_OfficerUser extends BaseClass
{		
	//Instantiating individual pages
	LoginPage loginPage = new LoginPage();
	ForgotPasswordPage forgotPwdPage = new ForgotPasswordPage();
	DashboardPage dashboardnew = new DashboardPage();
	DataRoles_FunctionalRolesPage dataRolesfunctionalRolesPage = new DataRoles_FunctionalRolesPage();
	AddNewPermRestrictionsPage AddNewPermRestrct = new AddNewPermRestrictionsPage();
	AddNewRolePage AddNewDataRole = new AddNewRolePage();
	SecurityProfilePage SecurityProfile = new SecurityProfilePage();
	AddNewSecurityProfilePage AddNewSProle = new AddNewSecurityProfilePage();
	UserListPage UserListingPage = new UserListPage();
	AddNewUserDetailsPage AddNewUserPage = new AddNewUserDetailsPage();
	EntityListingPage entListingPage = new EntityListingPage();
	AddNewEntityPage addEntityPage = new AddNewEntityPage();
	AddNewAttributePage addAttbt = new AddNewAttributePage();
	FormDesignerPage frmDesign = new FormDesignerPage();
	LinkedFormListingPage linkedFrmListing = new LinkedFormListingPage();	
	EntityFormListingPage entityFrmListingPage = new EntityFormListingPage();
	AddNewEntityFormDetailsPage entityFrmDetailspage = new AddNewEntityFormDetailsPage ();
	EntityInventoryDetailsPage entityInvtpage = new EntityInventoryDetailsPage();

	//passcurrent time
	private static String localTime = GenericMethods.currentLocalTime();

	TestSuite RunTestCase = new TestSuite();

	//Initializing total test cases, and counting pass/ fail count
	public static Integer TotalTc_Ch=0;
	public static Integer PassTc_Ch=0;
	public static Integer FailTc_Ch=0;

	public static Integer TotalTc_FF=0;
	public static Integer PassTc_FF=0;
	public static Integer FailTc_FF=0;

	public static String browserCategory = "";

	public static String packType = "M2-OfficerCreation-";

	public static String TESTSTATUS = null;

	//Defining all Menu/ sub menu/ business entity name
	public static String adminMainMenu = RaisTestData.AdministrationMainMenu;
	public static String UserMgmtSubMenu = RaisTestData.UserMgmtSubMenuText;
	public static String CustomizeSubMenu = RaisTestData.CustomizeSubMenu;
	public static String CommonTblSubMenu = RaisTestData.CommonTablesSubMenu;

	public static String invenResMainMenu = RaisTestData.InventResourceMainMenu;
	public static String InvSubMenuName = RaisTestData.InventorySubMenuText;
	public static String ResrcSubMenuName = RaisTestData.ResourcesSubMenuText;


	//primary entity name
	public static String entitiesMenu = RaisTestData.businessEntityList[66]; // Entity	system;
	public static String Primay_EntityInternalName = "PrimaryUserEntityInternalName"+localTime;
	public static String primary_EntityNameSingular = "EntityPrimarySingular"+localTime;
	public static String primary_EntityNamePlural = "EntityPrimaryPlural"+localTime;

	//Test data details
	public static String OfficerEntityName = RaisTestData.businessEntityList[37]; //Officer Entity
	public static String UsersEntity = RaisTestData.businessEntityList[64]; // User Entity


	//User creation details
	//Internal/ external user
	public static String userType = RaisTestData.INTERNAL_USER;	
	//User screen
	public String orgTypeName = RaisTestData.REG_AUTHORITY_DATA;
	public static String AuthType = RaisTestData.AUTH_TYPE_DATA; // Authority and Org type data

	public static String externalOfficerName = "Officer"+userType; //Officer Name
	public static String userEmail = "RAIS_Test1@e-zest.in";//pallavi.parbat@e-zest.in		vishalparbat@gmail.com
	public static String UserID = "RAIS_Test1"; //User Name // PP //VP
	public static String secUserName = "RAIS_Test1secondary";
	public static String tertiaryUserName = "VPtertiaryUser";	
	//public static String internalUserEmail = "surabhi.deshpande@e-zest.in";

	//secondary entity name
	public static String Sec_EntityInternalName = "SecondaryUserEntityInternalName"+localTime;
	public static String Sec_EntityNameSingular = "EntitySecondarySingular"+localTime;
	public static String Sec_EntityNamePlural = "EntitySecondaryPlural"+localTime;

	//FR Test data
	public static String fr_PrimExt_A = RaisTestData.FR_Prim_ext_A+localTime;
	public static String fr_SecExt_A = RaisTestData.FR_Sec_ext_A+localTime;
	public static String fr_SecExt_D = RaisTestData.FR_Sec_ext_D;
	public static String DR = RaisTestData.DATAROLE_ALL_DATA;


	public boolean TestData_primUserEntity, TestData_secUserEntity = false;
	public boolean flag_FR_PrimExtUser, flag_FR_PrimSecUser = false;
	public boolean flag_AuthType = false;
	public boolean flag_externalOfficer,flag_internalOfficer = false;
	public boolean flag_primary, flag_secondary = false;

	//Data role functional role Menu
	public static String dataRoleMenu = RaisTestData.dataRole;
	public static String funcRoleMenu = RaisTestData.functionalRole;

	//Authority business entity name
	public static String Auth_OrgEntity = RaisTestData.businessEntityList[65]; // User Entity	

	//Optional text
	public String Optional = RaisTestData.OPTIONAL_TEXT;




	//Before class ################################
	@Parameters({"browserType"})
	@BeforeClass
	public void beforeClass(String browserType) {

		try {

			//Assigning browser type to browser category
			browserCategory = browserType;

			//Loading config file for url, uid and password
			LoadConfigfile();

			//Thisvalue will pass from parameter
			SetUpExtentReport(browserCategory,packType );
			//SetUpExtentReport(GeneralConstants.runBrowserChrome);

			//Initializing Browser and launch Application. This will pass from parameter
			BrowserSetup_LaunchNew(browserCategory);
			//BrowserSetup_LaunchNew(GeneralConstants.runBrowserChrome);

			//Loading Test cases run flag and test case names, description
			//TestSuite.Sanity_TestCases_Set();




		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Test case starts here ******************************

	//#1 - Test data of Entity, Functional role and Authority type
	@Test(priority=1,enabled=false)
	public void TestDataCreation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("Test Data Creation","Creation of Test Data");

			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);


			//Entity 1 creation started********************************************************************************
			System.out.println("entity1 creation started");

			//calling entity creation method and set flag value
			TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, Primay_EntityInternalName, primary_EntityNameSingular, primary_EntityNamePlural, 
					RaisTestData.Entity_GroupData, RaisTestData.Entity_RoleData, RaisTestData.Entity_Publish_Inv_Res, RaisTestData.Entity_PublishNav_Inventory);

			System.out.println("entity1 creation complete");

			//Entity 2 creation started*****************************
			System.out.println("entity2 creation started");

			//calling entity creation method and set flag value
			TestData_secUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, Sec_EntityInternalName, Sec_EntityNameSingular, Sec_EntityNamePlural, 
					RaisTestData.Entity_GroupData, RaisTestData.Entity_RoleData, RaisTestData.Entity_Publish_Inv_Res, RaisTestData.Entity_PublishNav_Resources);

			System.out.println("entity2 creation complete");
			//Entity creation Complete********************************************************************************





			//FR creation for primary started********************************************************************************
			//FR for primary user started
			System.out.println("FR creation for primary started");

			flag_FR_PrimExtUser = RAIS_applicationSpecificMethods.createFR(wd, fr_PrimExt_A, RaisTestData.Entity_GroupData, primary_EntityNameSingular, 
					true, true, true, true);

			//FR 2 secondary creation started*****************************
			//FR for secondary user started
			System.out.println("FR creation for secondary started");

			flag_FR_PrimSecUser = RAIS_applicationSpecificMethods.createFR(wd, fr_SecExt_A, RaisTestData.Entity_GroupData, Sec_EntityNameSingular, 
					true, true, true, true);


			System.out.println("FR creation complete");
			//FR creation Complete********************************************************************************
			//*****************************Secondary FR role Ends here








			//Adding data to Authority & Org starts here*************************************************
			//wait for page load
			GenericMethods.JSPageWait(wd);

			//AuthType test data starts here for primary user
			//Clicking on Authority & organisation menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, CommonTblSubMenu, Auth_OrgEntity);

			//calling generic method to call Authority and Organisations entity data input
			flag_AuthType = RAIS_applicationSpecificMethods.createEntityRecord(wd, Auth_OrgEntity, Optional, AuthType, Optional,RaisTestData.ADD_MODE_TEXT );
			//Adding data to Authority & Org ends here*************************************************







			// OFFICER creation page ******************************************************************************************
			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Clicking on Officers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, invenResMainMenu, InvSubMenuName, OfficerEntityName);

			//calling generic method to call Officer entity data input
			flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, OfficerEntityName, externalOfficerName, AuthType, userEmail,RaisTestData.ADD_MODE_TEXT);

			System.out.println("officer external record creation complete");

			//wait for page load
			GenericMethods.JSPageWait(wd);

			// OFFICER creation completed ****************************************************************************************




		}catch (NoSuchElementException  noElement) {

			TestData_primUserEntity = false;
			TestData_secUserEntity = false;
			noElement.printStackTrace();

		}catch (Exception  e) {
			TestData_primUserEntity = false;
			TestData_secUserEntity = false;
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}

	}


	//#2 - Officer User creation
	@Test(priority=2,enabled=false)
	public void User_PrimaryUserCreation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Creation of Primary External user","Primary Extenal User is successfully created");

		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//////////*******************************************Add new Primary user starts here

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, UserMgmtSubMenu, UsersEntity);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

			System.out.println("user creation started");

			//Create primary user
			flag_primary = RAIS_applicationSpecificMethods.createUser(wd, RaisTestData.primary,userType,
					orgTypeName, AuthType, externalOfficerName, UserID, fr_PrimExt_A, DR);


			//wait for page load
			GenericMethods.JSPageWait(wd);

			//End of primary user creation

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {


			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}


	}

	//#3 - Secondary 
	@Test(priority=3,enabled=false)
	public void ExternalUser_SecondaryUserCreation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Creation of Secondary External user","Secondary External User is successfully created");

		try {

			/// **********************************************secondary usercreation starts

			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, UserMgmtSubMenu, UsersEntity);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, UserListingPage.Dyamic_GridTable_Prefix_Xpath, 1,
					UserListingPage.Dynamic_GridTable_Suffix1_Xpath, UserListingPage.USERLIST_COLHEADER_NAME_Txt, 
					UserListingPage.Dynamic_GridTable_Suffix2_Xpath, UserListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, externalOfficerName);

			//wait for page load
			GenericMethods.JSPageWait(wd);			

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,UserListingPage.userListTable_XPath , externalOfficerName);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//click on secondary sub account
			GenericMethods.elementClick(wd, AddNewUserPage.secUserLeftLink_Xpath);

			//input secondary user name
			GenericMethods.sendText(wd, AddNewUserPage.secUserNameid_Xpath, secUserName);

			//clicking on save button
			GenericMethods.elementClick(wd, AddNewUserPage.secUserNameSaveBtn_Xpath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			flag_secondary = RAIS_applicationSpecificMethods.createUser(wd, Optional, orgTypeName,userType,
					AuthType, externalOfficerName, UserID, fr_SecExt_A, DR);			

			//*****************************************************End of secondary user creation					


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {


			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}
	}

	//4 - Primary User login creation
	@Test(priority=4, enabled=false)
	public void VerifyPrimaryUserlogin() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Verify Primary user access","Verify entity level access of Primary Extenal User after it is successfully created");

		try {

			//calling method for primary_secondary login
			RAIS_applicationSpecificMethods.primary_secondary_Login(wd, userType, UserID, password, RaisTestData.primary);

			if(userType == "External") {

				//verify menu
				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, invenResMainMenu, InvSubMenuName, primary_EntityNamePlural);				

			} else {

				System.out.println("loggedin");

				//verify menu
				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, CustomizeSubMenu, primary_EntityNamePlural);

			}

			//Page wait
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);			


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {


			if (userType == "External") {

				//wait for page load
				GenericMethods.pageLoadWait(2000);
				GenericMethods.JSPageWait(wd);

				//Logout user
				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

				//verifying logo on RIAS Page
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

				//page refresh
				wd.navigate().refresh();
			}
		}
	}

	//5 - Secondary User login creation
	@Test(priority=5, enabled=false)
	public void SecondaryUserlogin() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Verify Secondary user access","Verify entity level access of Secondary Extenal User after it is successfully created");

		try {

			//calling method for primary_secondary login
			RAIS_applicationSpecificMethods.primary_secondary_Login(wd, userType, UserID, password, secUserName);

			if (userType == "External") {

				//verify menu
				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, invenResMainMenu, ResrcSubMenuName, Sec_EntityNamePlural);

			} else {

				//verify menu
				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, CustomizeSubMenu	, Sec_EntityNamePlural);

			}

			//Page wait
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//execute for external secondary only

			if(userType =="External") {

				//wait for page load
				GenericMethods.pageLoadWait(2000);

				//Logout user
				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

				//verifying logo on RIAS Page
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

				//page refresh
				wd.navigate().refresh();
			}
		}
	}

	//6 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=6, enabled=true)
	public void BusinessEntity_RecordCRUD_Operations_Admin_CommonTable() {
		
		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Verify CRUD OPerations of Business Entity Record","Verify Business entity, record can be added, edited and deleted successfully");

		try {
			
			//Entities under common tables with 1 field
			String BEname [] = RaisTestData.singleFldBE_Admin_CommonTbl;
			
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			for(int i=0;i<BEname.length;i++) {

				//wait for page load
				GenericMethods.JSPageWait(wd);

				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, CommonTblSubMenu,BEname[i]);

				//***********************************************Create entity record
				//calling generic method to call Officer entity data input
				flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", "TestAuto", Optional, Optional, RaisTestData.ADD_MODE_TEXT);

				////**********************************************EDIT MODE STARTS Here						
				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto");

				//Edit entity record
				//calling generic method to call Officer entity data edit
				flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", localTime, Optional, Optional, Optional);

				////***********************************************delete starts here
				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto"+localTime);

				//delete entity record data
				RAIS_applicationSpecificMethods.deleteEntityRecord(wd);			

			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {


			//				//Logout user
			//				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);
			//
			//				//verifying logo on RIAS Page
			//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();

		}
	}

	//7 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=7, enabled=false)
	public void BusinessEntity_RecordCRUD_Operations_Admin_Cust() {
			
			//Setting Test name and description on report
			SettingRptTestName_TestDesc("Verify CRUD OPerations of Business Entity Record","Verify Business entity, record can be added, edited and deleted successfully");

			try {
				
				//Initialising entity names
				String BEname [] = RaisTestData.singleFldBE_Admin_Custom;
				
				//Calling Login method
				GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
						loginPage.loginBtn_XPath);

				for(int i=0;i<BEname.length;i++) {

					//wait for page load
					GenericMethods.JSPageWait(wd);

					//Clicking on User menu
					RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, adminMainMenu, CustomizeSubMenu,BEname[i]);

					//***********************************************Create entity record
					//calling generic method to call Officer entity data input
					flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", "TestAuto", Optional, Optional, RaisTestData.ADD_MODE_TEXT);

					////**********************************************EDIT MODE STARTS Here						
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto");

					//Edit entity record
					//calling generic method to call Officer entity data edit
					flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", localTime, Optional, Optional, Optional);

					////***********************************************delete starts here
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto"+localTime);

					//delete entity record data
					RAIS_applicationSpecificMethods.deleteEntityRecord(wd);			

				}


			}catch (NoSuchElementException  noElement) {
				noElement.printStackTrace();

			}catch (Exception  e) {
				e.printStackTrace();

			} finally {


				//				//Logout user
				//				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);
				//
				//				//verifying logo on RIAS Page
				//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

				//page refresh
				wd.navigate().refresh();

			}
		}

		
	//8 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=8, enabled=false)
	public void BusinessEntity_RecordCRUD_Operations_InvRes_Inv() {
				
				//Setting Test name and description on report
				SettingRptTestName_TestDesc("Verify CRUD OPerations of Business Entity Record","Verify Business entity, record can be added, edited and deleted successfully");

				try {
					
					//Initialising entity names
					String BEname [] = RaisTestData.singleFldBE_Inv_Inv;
					
					//Calling Login method
					GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
							loginPage.loginBtn_XPath);

					for(int i=0;i<BEname.length;i++) {

						//wait for page load
						GenericMethods.JSPageWait(wd);

						//Clicking on User menu
						RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, invenResMainMenu, InvSubMenuName,BEname[i]);

						//***********************************************Create entity record
						//calling generic method to call Officer entity data input
						flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", "TestAuto", Optional, Optional, RaisTestData.ADD_MODE_TEXT);

						////**********************************************EDIT MODE STARTS Here						
						//Grid filter and click on entity record listing page
						RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto");

						//Edit entity record
						//calling generic method to call Officer entity data edit
						flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", localTime, Optional, Optional, Optional);

						////***********************************************delete starts here
						//Grid filter and click on entity record listing page
						RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto"+localTime);

						//delete entity record data
						RAIS_applicationSpecificMethods.deleteEntityRecord(wd);			

					}


				}catch (NoSuchElementException  noElement) {
					noElement.printStackTrace();

				}catch (Exception  e) {
					e.printStackTrace();

				} finally {


					//				//Logout user
					//				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);
					//
					//				//verifying logo on RIAS Page
					//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

					//page refresh
					wd.navigate().refresh();

				}
			}

	//9 - Single Attribute Business Entity - Entity record CRUD operations
		@Test(priority=9, enabled=false)
		public void BusinessEntity_RecordCRUD_Operations_InvRes_Res() {
					
					//Setting Test name and description on report
					SettingRptTestName_TestDesc("Verify CRUD OPerations of Business Entity Record","Verify Business entity, record can be added, edited and deleted successfully");

					try {
						
						//Initialising entity names
						String BEname [] = RaisTestData.singleFldBE_Inv_Res;
						
						//Calling Login method
						GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
								loginPage.loginBtn_XPath);

						for(int i=0;i<BEname.length;i++) {

							//wait for page load
							GenericMethods.JSPageWait(wd);

							//Clicking on User menu
							RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, invenResMainMenu, ResrcSubMenuName,BEname[i]);

							//***********************************************Create entity record
							//calling generic method to call Officer entity data input
							flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", "TestAuto", Optional, Optional, RaisTestData.ADD_MODE_TEXT);

							////**********************************************EDIT MODE STARTS Here						
							//Grid filter and click on entity record listing page
							RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto");

							//Edit entity record
							//calling generic method to call Officer entity data edit
							flag_externalOfficer = RAIS_applicationSpecificMethods.createEntityRecord(wd, "NameOnly", localTime, Optional, Optional, Optional);

							////***********************************************delete starts here
							//Grid filter and click on entity record listing page
							RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, "TestAuto"+localTime);

							//delete entity record data
							RAIS_applicationSpecificMethods.deleteEntityRecord(wd);			

						}


					}catch (NoSuchElementException  noElement) {
						noElement.printStackTrace();

					}catch (Exception  e) {
						e.printStackTrace();

					} finally {


						//				//Logout user
						//				RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);
						//
						//				//verifying logo on RIAS Page
						//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

						//page refresh
						wd.navigate().refresh();

					}
				}

	
	
	
	

	//#22
	@Test(priority=22,enabled=E2E_TestPack.e2eTC2_runStatus)
	public void E2E_FormDesigner(){

		//Setting entity names
		String internalName = RaisTestData.Entity_FormBuilder_InternalNameData + localTime;
		String DescripTxt = RaisTestData.Entity_DescriptionData + localTime;
		String singularName = RaisTestData.Entity_FormBuilder_SingularData ;
		String pluralName = RaisTestData.Entity_FormBuilder__PluralData ;

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC2_testName,E2E_TestPack.e2eTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);			

			//********************************Add new Entity starts here

			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			//GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, DescripTxt);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, RaisTestData.Entity_GroupData);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, RaisTestData.Entity_RoleData);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, RaisTestData.Entity_PublishNavigation1Data);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, RaisTestData.Entity_PublishNavigation2Data);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			//GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);

			//Add entity completed and below is for selecting particular entity
			//wait for page load
			GenericMethods.pageLoadWait(1000);			

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,singularName);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);

			//wait for page load
			GenericMethods.pageLoadWait(3000);

			//			//Waiting until element to load
			//			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);
			//GenericMethods.elementClick(wd, addEntityPage.attributeLink_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			//***Attribute creation method
			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[0]);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[11]);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[14]);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific form - main form
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListing.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			System.out.println("Form opened");

			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);

			GenericMethods.pageLoadWait(500);
			GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, RaisTestData.sectionTitle);

			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton3_XPath);

			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);

			System.out.println("2 sections created");

			///Adding colproperties on first section

			GenericMethods.pageLoadWait(500);

			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_ChkBox_dropdown_XPath);

			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			///Adding colproperties on 2nd section

			GenericMethods.pageLoadWait(500);

			//Clicking on column prop 2 to add text field
			GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	

			GenericMethods.pageLoadWait(500);

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(250);

			//Clicking on column prop 2 to add numeric field
			//GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);


			GenericMethods.pageLoadWait(500);
			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			///Adding colproperties on 3rd section

			//			GenericMethods.pageLoadWait(500);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.colProp3_Temp_XPath);		
			//			
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//			GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			//GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");

			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, linkedFrmListing.addNewFormBtn_XPath);	
			GenericMethods.elementClickable(wd, linkedFrmListing.addNewFormBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			wd.navigate().refresh();
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);

			//String newEntityCreatedXPATH = RAIS_applicationSpecificMethods.dashboardSubMenuDynamicXpath(pluralName);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);

			//Clicking on Element
			//RAIS_applicationSpecificMethods.roleSelect_Click(wd, "sub-menu", newEntityCreatedXPATH);
			GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			System.out.println("waiting newly created entity page to load");


			//************** below is temporariliy not used
			//initialising dyanmic xpath
			String dynamicTextBox_Xpath = null ;String dynamic_CheckBox_Xpath = null ; String dynamicNumericBox_Xpath = null ;

			dynamicTextBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.text_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicTextBox_Xpath, 
					RaisTestData.text_label), RaisTestData.text_label);



			dynamic_CheckBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.chkBox_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamic_CheckBox_Xpath, 
					RaisTestData.chkBox_label), RaisTestData.chkBox_label);


			dynamicNumericBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.numeric_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicNumericBox_Xpath, 
					RaisTestData.numeric_label), RaisTestData.numeric_label);


			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(2000);

			//TO be ocntinued
			//verify check box label
			Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath),
					RaisTestData.chkBox_label);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//verify sample text field label and relevant input field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Txt_XPath),
					RaisTestData.text_label);
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath, 12,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//verify numeric field label and relevant input field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Numeric_XPath),
					RaisTestData.numeric_label);
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, 50,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);



			//verification of fields ends above

			//input data into sample check box fields
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, "Inspection Form");

			//selecting checkbox
			GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);				

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath,"Vienna");

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, "1060");

			//Tabelement from current field
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath);				

			// Clicking on save butto
			//GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);				
			//Clicking on Element				

			System.out.println("data visible on column");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "Vienna"),
					"Vienna");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "1060"),
					"1060");



		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {




			//New delete entity method starts from here
			RAIS_applicationSpecificMethods.deleteEntity(wd, singularName);

			//**************************************************Delete ENDS HERE	


			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}

	}

	//#33 - Latest one use this
	@Test(priority=33,enabled=E2E_TestPack.e2eTC3_runStatus)
	public void E2E_FormDesigner_Business(){

		//Test Data Details - IMP************************************************************************************************************
		String singularName = RaisTestData.businessEntityList[3];   ///Amperage Unit Business entity singular name listed on entities list	*
		String pluralName = dashboardnew.AmpUnit_XPath ;			///Amperage Units Business entity plural name listed main menu			*
		//***********************************************************************************************************************************
		//Number of attributes																												*
		int attributeCount = 0;																											//	*
		//***********************************************************************************************************************************
		//Attribute details																													*
		String attb_chkbox = addAttbt.attributeValueList[0];																			//	*
		String attb_numeric_Int = addAttbt.attributeValueList[10];																		//	*
		String attb_txtbox = addAttbt.attributeValueList[13];																			//	*
		//***********************************************************************************************************************************
		//increment/ add attb xpath to be added in form designer screen column properties												//	*
		String columnPropDropdown [] = {																								//	*
				frmDesign.selectAttrib_ChkBox_dropdown_XPath,																			//	*
				frmDesign.selectAttrib_Text_dropdown_XPath,																				//	*
				frmDesign.selectAttrib_Numeric_dropdown_XPath																			//	*
		};																														//	*
		//***********************************************************************************************************************************
		//on form designer page, below attribute names are used																			//	*			
		//System attribute - not to be altered																							//	*
		String frmName = entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath;	//Label Name - Name									*	
		String frmNameValue = "mA";																										//	*
		//	*
		//Business attribute - this should not be altered																				//	*
		String frmFactor = entityFrmDetailspage.entityFormDetailsPage_FactorFld_XPath;	//Label Name - Factor								*
		String frmFactorValue = "1";																									//	*
		//	*
		//Custom attribute starts here																									//	*
		String frmChkBoxLabel = RaisTestData.chkBox_label;		//Custom attribute label name												*
		String frmChkBox = entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath;	//Label Name - Radioactive Material					*
		//	*	
		String frmNumericLabel = RaisTestData.numeric_label;																			//	*
		String frmNumeric = entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath;	//Label Name - Postal code					*
		String frmNumericValue = "1060"; 																								//	*
		//	*
		String frmTextLabel = RaisTestData.text_label;																					//	*
		String frmTextFld = entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath;	//Label Name - City Name						*
		String frmTextValue = "Unit Current";																							//	*
		//***********************************************************************************************************************************
		//number of attributes X - button from form designer - to be included in for loop - count depends on number of attributes			*
		String deleteAttb_frmDesigner = frmDesign.delete_Attrb_4_XPath;																	//	*
		//***********************************************************************************************************************************
		//Column header xpath - generate here to be used in column header filter
		String ColHeader_XPath = "//*[@id='entity-details']//table//thead//tr//th[2]//a[span[text()='"+RaisTestData.text_label+"']]//span//button";
		//***********************************************************************************************************************************


		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC3_testName,E2E_TestPack.e2eTC3_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);			


			//wait for page load
			GenericMethods.pageLoadWait(500);
			//					

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,singularName);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);

			//wait for page load
			GenericMethods.pageLoadWait(3000);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			//*************************************************************************Attribute creation method - add attributes here

			//****************************Attrb 1 starts
			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[0]);  ///CheckBox

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);
			//****************************Attrb 1 Ends

			//****************************Attrb 2 starts
			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[10]);   ///Numeric

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			//****************************Attrb 2 Ends

			//****************************Attrb 3 starts - LAST ATTB

			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[13]);  ///Textbox

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//****************************Attrb 3 Ends

			//*****************************************************Design form starts here
			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific form - main form
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListing.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			System.out.println("Form opened");

			System.out.println("Adding data to only 1 section");

			///Adding colproperties on first section

			GenericMethods.pageLoadWait(1000);

			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.oneSection_ColProp_XPath);

			//Page load
			GenericMethods.pageLoadWait(250);

			//***********************************************************************Drop down select of attribute starts here

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_ChkBox_dropdown_XPath);

			//Page load
			//GenericMethods.pageLoadWait(500);

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);

			//wait for page load
			//GenericMethods.pageLoadWait(250);

			//Clicking on column prop 2 to add numeric field
			//GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);


			//GenericMethods.pageLoadWait(500);
			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//clicking on main form page save button
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, linkedFrmListing.addNewFormBtn_XPath);	
			GenericMethods.elementClickable(wd, linkedFrmListing.addNewFormBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			wd.navigate().refresh();
			GenericMethods.pageLoadWait(1000);

			//*************************************************Open business entity from main menu starts here
			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//String newEntityCreatedXPATH = RAIS_applicationSpecificMethods.dashboardSubMenuDynamicXpath(pluralName);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.AmpUnit_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.AmpUnit_XPath);

			//Clicking on Element
			//RAIS_applicationSpecificMethods.roleSelect_Click(wd, "sub-menu", newEntityCreatedXPATH);
			GenericMethods.elementClick(wd, dashboardnew.AmpUnit_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			System.out.println("waiting newly created entity page to load");

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(1000);

			//verification of fields ends above

			//input data into form starts here *******************************************************************************
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, "mA");

			//selecting checkbox
			GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);	

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_FactorFld_XPath,"1");

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, "1060");

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath,"Unit Current");


			// Clicking on save butto
			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);				
			//Clicking on Element				

			System.out.println("data visible on column");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "mA"),
					"mA");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "1"),
					"1");

			GenericMethods.pageLoadWait(5000);///////////////// *****************************************************************************logical break

			//***********************************************************************Delete entity attrb starts here
			RAIS_applicationSpecificMethods.mainMenu_SubMenu_Click(wd, dashboardnew.administration_XPath, dashboardnew.AmpUnit_XPath);

			//Click on column filter
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entityFrmListingPage.CityNameColHeader_XPath,
					entityFrmListingPage.NameColHeader_TXT_XPath,"Unit Current");

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Apply filter of unit current
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , "Unit Current");

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//clicking on delete button and wait for successmsg
			RAIS_applicationSpecificMethods.Delete_And_Wait(wd, addEntityPage.deleteBtn_XPath, addEntityPage.delEntity_popUpYesBtn_XPath, 
					addEntityPage.addnewEntity_SuccessMsg_XPath);

			//clicking on admin and entities link
			RAIS_applicationSpecificMethods.mainMenu_SubMenu_Click(wd, dashboardnew.administration_XPath, dashboardnew.entities_XPath);

			//Click on column filter
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,singularName);


			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Apply filter of unit current
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific form - main form
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListing.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//****************************clicking on delete button for all 3 fields
			GenericMethods.elementClick(wd, frmDesign.delete_Attrb_4_XPath);
			GenericMethods.elementClick(wd, frmDesign.delete_Attrb_4_XPath);
			GenericMethods.elementClick(wd, frmDesign.delete_Attrb_4_XPath);

			GenericMethods.pageLoadWait(500);
			GenericMethods.waitforElement(wd, frmDesign.mainFrmPage_SaveBtn_XPath);	
			GenericMethods.elementClickable(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);

			//					wait for page load
			GenericMethods.pageLoadWait(2000);
			//					GenericMethods.waitforElement(wd, addEntityPage.attributeLink_XPath);	
			//					GenericMethods.elementClickable(wd, addEntityPage.attributeLink_XPath);



			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);

			//*****************************************************Delete  attribute1 step
			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Click on column filter
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,addAttbt.attrbListingColHeader_XPath,
					addAttbt.attrbListingColHeader_TXT_XPath,RaisTestData.text_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Apply filter of Cityname
			RAIS_applicationSpecificMethods.LinkClickonGrid(wd,addAttbt.attrbListingTable_XPath , RaisTestData.text_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on delete button and wait for successmsg
			RAIS_applicationSpecificMethods.Delete_And_Wait(wd, addAttbt.deleteBtn_XPath, addAttbt.delEntity_popUpYesBtn_XPath, 
					addAttbt.Attrb_SuccessMsg_XPath);

			//clicking on clear filter
			GenericMethods.elementClick(wd, addAttbt.ClearbuttonFilter_XPath);

			//*****************************************************Delete  attribute2 step
			//Input text without clicking on column header - No need to click on filter as filter input field is already opened
			GenericMethods.sendText(wd, addAttbt.attrbListingColHeader_TXT_XPath,RaisTestData.numeric_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Apply filter of Postal code
			RAIS_applicationSpecificMethods.LinkClickonGrid(wd,addAttbt.attrbListingTable_XPath , RaisTestData.numeric_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on delete button and wait for successmsg
			RAIS_applicationSpecificMethods.Delete_And_Wait(wd, addAttbt.deleteBtn_XPath, addAttbt.delEntity_popUpYesBtn_XPath, 
					addAttbt.Attrb_SuccessMsg_XPath);

			//clicking on clear filter
			GenericMethods.elementClick(wd, addAttbt.ClearbuttonFilter_XPath);

			//*****************************************************Delete  attribute3 step
			//Input text without clicking on column header - No need to click on filter as filter input field is already opened
			GenericMethods.sendText(wd, addAttbt.attrbListingColHeader_TXT_XPath,RaisTestData.chkBox_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Apply filter of checkbox
			RAIS_applicationSpecificMethods.LinkClickonGrid(wd,addAttbt.attrbListingTable_XPath , RaisTestData.chkBox_label);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on delete button and wait for successmsg
			RAIS_applicationSpecificMethods.Delete_And_Wait(wd, addAttbt.deleteBtn_XPath, addAttbt.delEntity_popUpYesBtn_XPath, 
					addAttbt.Attrb_SuccessMsg_XPath);

			//clicking on clear filter
			GenericMethods.elementClick(wd, addAttbt.ClearbuttonFilter_XPath);					


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {




			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}

	}


	//#44 - Officer creation
	@Test(priority=44,enabled=E2E_TestPack.e2eTC4_runStatus)
	public void officerCreation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC4_testName,E2E_TestPack.e2eTC4_testDescription);

		String InvResmainMenuName = RaisTestData.InventResourceMainMenu;
		String InvsubMenuName = RaisTestData.InventorySubMenuText;
		String OfficerEntityName = RaisTestData.businessEntityList[37]; //Officer Entity
		String AdminMainMenuName = RaisTestData.AdministrationMainMenu;
		String UserMgmtsubMenuName = RaisTestData.UserMgmtSubMenuText;
		String CommonTablesubMenuName = RaisTestData.CommonTablesSubMenu;
		String UsersEntity = RaisTestData.businessEntityList[64]; // User Entity
		String Auth_OrgEntity = RaisTestData.businessEntityList[65]; // User Entity	
		String OfficerName = RaisTestData.OFFICER_NAME_DATA ; //Officer Name
		String AuthType = RaisTestData.AUTH_TYPE_DATA; // Authority and Org type data
		String OfficerUserName = "OfficerLicenseeUser"; //User Name
		String userEmail = "vishalparbat@gmail.com";

		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Officers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, InvsubMenuName, OfficerEntityName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);

			//input officer name
			GenericMethods.sendText_removeblank(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, OfficerName);

			//input Gender type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityFrmDetailspage.entityFormDetailsPage_genderFld_Xpath, RaisTestData.GENDER_FEMALE_DATA);

			//********************Click on Authority link to create Auth & Org Record test data

			GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_AuthHyperLink_XPath);

			//Page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);			

			//Input Name
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, AuthType);

			//Legal basis data
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

			//Chair person data
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_ChairPrsonFld_Xpath, RaisTestData.CHAIRPERSON_DATA);
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_ChairPrsonFld_Xpath);

			//clicking on save button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);			
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//********************End of test data creation Auth & Org 
			//page wait
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//input Authority type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityFrmDetailspage.entityFormDetailsPage_authorityFld_Xpath, AuthType);

			//multiselect officer task
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.restriction_DropdnClick_Xpath, entityFrmDetailspage.LicenseeFR_MultiSelect_XPath);

			//input Email
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_emailFld_Xpath, userEmail);			

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_emailFld_Xpath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);			

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(2000);

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, AdminMainMenuName, UserMgmtsubMenuName, UsersEntity);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_orgType_XPath, RaisTestData.REG_AUTHORITY_DATA);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation input
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_org_XPath, AuthType);

			//External user
			GenericMethods.elementClick(wd, AddNewUserPage.addnewUser_ExternalrdobtnAuth_XPath);

			//Enter officer name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_selectUser_XPath, OfficerName);

			//Enter user ID name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_userName_XPath, OfficerUserName);

			//Selecting functional role
			GenericMethods.elementClick(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath);
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath, entityFrmDetailspage.LicenseeFR_MultiSelect_XPath);

			//Select Data role
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_DataRole_Xpath, RaisTestData.DATAROLE_ALL_DATA);

			//click on user Activate
			GenericMethods.elementClick(wd, AddNewUserPage.addNewUserDetail_statusActive_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewUserPage.SaveBtn_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(10000);

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);


			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, OfficerUserName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(3000);



		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {


			//wait for page load
			GenericMethods.pageLoadWait(2000);



			//Click on dashboard
			//			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);		

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}


	}

	//#5 - Expert creation
	@Test(priority=5,enabled=E2E_TestPack.e2eTC5_runStatus)
	public void ExpertCreation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC5_testName,E2E_TestPack.e2eTC5_testDescription);

		String InvResmainMenuName = RaisTestData.InventResourceMainMenu;
		String InvsubMenuName = RaisTestData.InventorySubMenuText;
		String ExpertEntityName = RaisTestData.businessEntityList[24]; //Expert Entity
		String AdminMainMenuName = RaisTestData.AdministrationMainMenu;
		String UserMgmtsubMenuName = RaisTestData.UserMgmtSubMenuText;
		String CustomizesubMenuName = RaisTestData.CustomizeSubMenu;
		String UsersEntity = RaisTestData.businessEntityList[64]; // User Entity
		String PartnerAgencyEntity = RaisTestData.businessEntityList[39]; // Partner agency Entity		
		String OrgType = RaisTestData.PARTNER_AGENCY_NAME_DATA;
		String expertUserName = RaisTestData.EXPERT_USERNAME_DATA;

		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Clicking on Officers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, InvsubMenuName, ExpertEntityName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Cancel button
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);

			//input officer name
			GenericMethods.sendText_removeblank(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath, expertUserName);

			//input Email
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_NewemailFld_Xpath, RaisTestData.TEST_EMAIL_DATA);		

			//********************Click on Add new Partner agency link to create Partner Agency Record test data

			GenericMethods.elementClick(wd, entityInvtpage.addNewPartAgency);

			//Page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);			

			//Input Name
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, OrgType);

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath);

			//clicking on save button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);			
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//********************End of test data creation Partner Agency
			//page wait
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//input Authority type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_PartAgency_XPath, OrgType);							

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_PartAgency_XPath);

			//Waiting until element to load and click on Next button for History
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityInvtpage.nextBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(500);
			GenericMethods.JSPageWait(wd);

			//Clicking on Date field
			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateOnly_Xpath);

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_dateOnly_Xpath);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, entityInvtpage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.SaveBtn_XPath);	
			GenericMethods.elementClick(wd, entityInvtpage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(1000);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(500);

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, AdminMainMenuName, UserMgmtsubMenuName, UsersEntity);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_orgType_XPath, RaisTestData.PART_AGENCY_ORG_DATA);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation input
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_org_XPath, OrgType);

			//External user
			GenericMethods.elementClick(wd, AddNewUserPage.addnewUser_ExternalrdobtnAuth_XPath);

			//Enter officer name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_selectUser_XPath, expertUserName);

			//Enter user ID name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_userName_XPath, RaisTestData.TestUserName);

			//Selecting functional role
			GenericMethods.elementClick(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath);
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath, entityFrmDetailspage.Regulator_MultiSelect_XPath);

			//Select Data role
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_DataRole_Xpath, RaisTestData.DATAROLE_SECURITY);

			//click on user Activate
			GenericMethods.elementClick(wd, AddNewUserPage.addNewUserDetail_statusActive_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewUserPage.cancelBtn_XPath);


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Delete officer here******************************************************1

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on Officer menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, InvsubMenuName, ExpertEntityName);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.NAME_COLHEADER_NAME_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, expertUserName);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , expertUserName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Deleting Authority test data ******************************************************2
			//Clicking on Authority menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd,AdminMainMenuName, CustomizesubMenuName , PartnerAgencyEntity);

			//Delete officer here
			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.NAME_COLHEADER_NAME_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, OrgType);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , OrgType);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}


	}

	//#6 - Facility creation
	@Test(priority=6,enabled=E2E_TestPack.e2eTC6_runStatus)
	public void FacilityCreation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC6_testName,E2E_TestPack.e2eTC6_testDescription);

		String InvResmainMenuName = RaisTestData.InventResourceMainMenu;
		String ResourceSubMenuName = RaisTestData.ResourcesSubMenuText;
		String FacilityEntityName = RaisTestData.businessEntityList[26]; //Facility Entity
		String AdminMainMenuName = RaisTestData.AdministrationMainMenu;
		String UserMgmtsubMenuName = RaisTestData.UserMgmtSubMenuText;
		String UsersEntity = RaisTestData.businessEntityList[64]; // User Entity
		String FacStatusEntity = RaisTestData.businessEntityList[27]; // Facility status Entity		
		String OrgTypeFacility = RaisTestData.facility_LinkedEntity;
		String OrgFacility = RaisTestData.ORG_FAC_STATUS_DATA;
		String facilityUser = RaisTestData.FACILITY_USERNAME_DATA;


		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Clicking on Officers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, ResourceSubMenuName, FacilityEntityName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Cancel button
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);

			//			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath, RaisTestData.FACILITY_USERNAME_DATA);

			GenericMethods.pageLoadWait(2000);

			//input officer name
			//			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath, facilityUser);
			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath);
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath, RaisTestData.FACILITY_USERNAME_DATA);
			//			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath);

			//********************Click on Add new facility status link to create facility status Record test data - commented to fix the bug

			//Clicking on add new facility status link
			GenericMethods.elementClick(wd, entityInvtpage.addNewFacilityStatus);			
			GenericMethods.elementClick(wd, entityInvtpage.addNewFacilityStatus);

			//Page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);		
			GenericMethods.elementClick(wd, entityFrmDetailspage.cancelBtn_XPath);
			//																	
			//Input Name - commented below
			//			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, OrgFacility);

			//setting tab element to enable save button
			//			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath);

			//clicking on save button
			//			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			//			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);			
			//			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//********************End of test data creation Partner Agency
			//page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//input Authority type - commenting below for bug******************************

			//			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_facilityStatusFld_Xpath);

			//			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_facilityStatusFld_Xpath, OrgFacility);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_facilityStatusFld_Xpath, "Closed");

			//Input legal person name
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_LegalPerson_XPath, RaisTestData.LEGAL_BASIS_DATA);

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_LegalPerson_XPath);

			//Waiting until element to load and click on Next button for History
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityInvtpage.nextBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Next button for History
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//select Datetime picker
			//			GenericMethods.elementClick(wd, "//*[@id='entity-inventory-form']//div[@class='control-renderer']//div//button");

			GenericMethods.JClickonElement(wd, "//*[@id='entity-inventory-form']//div[@class='control-renderer']//div//button");

			//			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateTimeFld_Xpath);
			//			GenericMethods.pageLoadWait(300);
			//			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateTimeBtn_Xpath);			
			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateTimeOKBtn_Xpath);
			//			
			//input Authority type - commenting the below
			//			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_facilityStatusFld_Xpath, OrgFacility);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_facilityStatusFld_Xpath, "Closed");

			//Input fax
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_faxFld_Xpath, "1");

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_faxFld_Xpath);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, entityInvtpage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.SaveBtn_XPath);	
			GenericMethods.elementClick(wd, entityInvtpage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(2000);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.FacListingaddNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.FacListingaddNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(500);

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, AdminMainMenuName, UserMgmtsubMenuName, UsersEntity);

			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_orgType_XPath, OrgTypeFacility);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation input
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_org_XPath, facilityUser);

			//External user
			GenericMethods.elementClick(wd, AddNewUserPage.addnewUser_ExternalrdobtnAuth_XPath);

			//Enter user ID name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_userName_XPath, RaisTestData.TestUserName);

			//Selecting functional role
			GenericMethods.elementClick(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath);
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath, entityFrmDetailspage.Regulator_MultiSelect_XPath);

			//Select Data role
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_DataRole_Xpath, RaisTestData.DATAROLE_SECURITY);

			//click on user Activate
			GenericMethods.elementClick(wd, AddNewUserPage.addNewUserDetail_statusActive_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewUserPage.cancelBtn_XPath);


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Delete officer here******************************************************1

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on facility entity
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, ResourceSubMenuName, FacilityEntityName);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.NAME_COLHEADER_NAME_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, facilityUser);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , facilityUser);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Deleting Facility status data ******************************************************2 - Code commented to bypass bug
			//			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd,InvResmainMenuName, ResourceSubMenuName,2 , FacStatusEntity);
			//
			//			//Delete officer here
			//			//Column header filter starts here
			//			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
			//					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.NAME_COLHEADER_NAME_Txt, 
			//					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, OrgFacility);
			//
			//			//Clicking on specific Role created
			//			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , OrgFacility);
			//
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.JSPageWait(wd);
			//
			//			//clicking on delete and popup msg
			//			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);
			//
			//			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}


	}



	//#10 - Calibration record - inventory with history
	@Test(priority=10,enabled=E2E_TestPack.e2eTC10_runStatus)
	public void Calibration_AddNewData() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC10_testName,E2E_TestPack.e2eTC10_testDescription);

		String mainMenuName = "ADMINISTRATION";
		String subMenuName = "Customization";
		int position = 3;
		String entityName = "Calibration";				

		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//Click on dashboard
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//Clicking on workers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, mainMenuName, subMenuName, entityName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);

			//input officer name
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath, RaisTestData.CALIB_NAME_DATA);
			//			GenericMethods.sendText_removeblank(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, RaisTestData.OFFICER_NAME_DATA);

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_inputNameFld_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityInvtpage.nextBtn_XPath);			

			//wait for page load
			GenericMethods.pageLoadWait(500);			

			//Page 2 starts here ******************************
			//input Facility type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_facilityFld_Xpath, RaisTestData.FACILITY_NAME_DATA);

			//input department type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityInvtpage.invenFormDetails_deptFld_Xpath, RaisTestData.DEPT_NAME_DATA);

			//select Datetime picker
			//GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_dateTimeFld_Xpath, RaisTestData.DATE_TIME_PICKR_DATA);			
			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateTimeBtn_Xpath);			
			GenericMethods.elementClick(wd, entityInvtpage.invenFormDetails_dateTimeOKBtn_Xpath);


			//input certificate name
			GenericMethods.sendText(wd, entityInvtpage.invenFormDetails_certFld_Xpath, RaisTestData.CERTI_DATA);

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityInvtpage.invenFormDetails_certFld_Xpath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityInvtpage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityInvtpage.SaveBtn_XPath);

			//Page 2 ends here ******************************

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);			

			//wait for page load
			GenericMethods.pageLoadWait(500);


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Delete officer here
			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityInvtpage.invenFormDetails_NAME_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, RaisTestData.CALIB_NAME_DATA);

			//wait for page load
			//			GenericMethods.pageLoadWait(500);
			GenericMethods.JSPageWait(wd);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , RaisTestData.CALIB_NAME_DATA);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Click on dashboard
			//			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//GenericMethods.elementClick(wd, dashboardnew.administration_XPath);			

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

		}


	}


	//#??- Officer CRUD operation
	@Test(priority=88,enabled=false)
	public void officerCreation_CRUDOperation() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC4_testName,E2E_TestPack.e2eTC4_testDescription);

		String InvResmainMenuName = RaisTestData.InventResourceMainMenu;
		String InvsubMenuName = RaisTestData.InventorySubMenuText;
		String OfficerEntityName = RaisTestData.businessEntityList[37]; //Officer Entity
		String AdminMainMenuName = RaisTestData.AdministrationMainMenu;
		String UserMgmtsubMenuName = RaisTestData.UserMgmtSubMenuText;
		String CommonTablesubMenuName = RaisTestData.CommonTablesSubMenu;
		String UsersEntity = RaisTestData.businessEntityList[64]; // User Entity
		String Auth_OrgEntity = RaisTestData.businessEntityList[65]; // User Entity		

		try {
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//Click on dashboard
			//			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Officers menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, InvsubMenuName, OfficerEntityName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);

			//input officer name
			GenericMethods.sendText_removeblank(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, RaisTestData.OFFICER_NAME_DATA);

			//input Gender type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityFrmDetailspage.entityFormDetailsPage_genderFld_Xpath, RaisTestData.GENDER_FEMALE_DATA);

			//********************Click on Authority link to create Auth & Org Record test data

			GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_AuthHyperLink_XPath);

			//Page wait
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);			

			//Input Name
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, RaisTestData.AUTH_TYPE_DATA);

			//Legal basis data
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

			//Chair person data
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_ChairPrsonFld_Xpath, RaisTestData.CHAIRPERSON_DATA);
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_ChairPrsonFld_Xpath);

			//clicking on save button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);			
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//********************End of test data creation Auth & Org 
			//page wait
			GenericMethods.pageLoadWait(2000);
			GenericMethods.JSPageWait(wd);

			//input Authority type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityFrmDetailspage.entityFormDetailsPage_authorityFld_Xpath, RaisTestData.AUTH_TYPE_DATA);

			//multiselect officer task
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.restriction_DropdnClick_Xpath, entityFrmDetailspage.LicenseeFR_MultiSelect_XPath);

			//input Email
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_emailFld_Xpath, RaisTestData.TEST_EMAIL_DATA);			

			//setting tab element to enable save button
			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_emailFld_Xpath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmDetailspage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);			

			//wait for page load
			GenericMethods.JSPageWait(wd);
			GenericMethods.pageLoadWait(2000);

			//Clicking on User menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, AdminMainMenuName, UserMgmtsubMenuName, UsersEntity);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wd, UserListingPage.addNewUserBtn_XPath);
			GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_orgType_XPath, RaisTestData.REG_AUTHORITY_DATA);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//Organisation input
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_org_XPath, RaisTestData.AUTH_TYPE_DATA);

			//External user
			GenericMethods.elementClick(wd, AddNewUserPage.addnewUser_ExternalrdobtnAuth_XPath);

			//Enter officer name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_selectUser_XPath, RaisTestData.OFFICER_NAME_DATA);

			//Enter user ID name
			GenericMethods.sendText(wd, AddNewUserPage.addnewUser_userName_XPath, RaisTestData.TestUserName);

			//Selecting functional role
			GenericMethods.elementClick(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath);
			RAIS_applicationSpecificMethods.multiSelectList(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath, entityFrmDetailspage.LicenseeFR_MultiSelect_XPath);

			//Select Data role
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewUserPage.addnewUser_DataRole_Xpath, RaisTestData.DATAROLE_ALL_DATA);

			//click on user Activate
			GenericMethods.elementClick(wd, AddNewUserPage.addNewUserDetail_statusActive_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewUserPage.cancelBtn_XPath);


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Delete officer here******************************************************1

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on Officer menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, InvResmainMenuName, InvsubMenuName, OfficerEntityName);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 3,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.OFFICERNAME_COLHEADER_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, RaisTestData.OFFICER_NAME_DATA);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , RaisTestData.OFFICER_NAME_DATA);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Deleting Authority test data ******************************************************2
			//Clicking on Authority menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd,AdminMainMenuName, CommonTablesubMenuName , Auth_OrgEntity);

			//Delete officer here
			//Column header filter starts here
			RAIS_applicationSpecificMethods.DynamicGridFilter(wd, entityFrmListingPage.Dyamic_GridTable_Prefix_Xpath, 2,
					entityFrmListingPage.Dynamic_GridTable_Suffix1_Xpath, entityFrmListingPage.AUTHNAME_COLHEADER_Txt, 
					entityFrmListingPage.Dynamic_GridTable_Suffix2_Xpath, entityFrmListingPage.Dynamic_GridTable_TxtInput_Suffix_Xpath, RaisTestData.AUTH_TYPE_DATA);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entityFrmListingPage.securityProfileTableList_XPath , RaisTestData.AUTH_TYPE_DATA);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wd);

			//clicking on delete and popup msg
			RAIS_applicationSpecificMethods.deleteFormRecord(wd, entityFrmDetailspage.deleteBtn_XPath, entityFrmDetailspage.popUpYesBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Click on dashboard
			//			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);		

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}


	}


	//#55 - Do not use
	@Test(priority=88,enabled=false)
	public void E2E_BusinessEntityFormDesigner(){

		//Setting entity names

		String bSingularName = RaisTestData.businessEntityList[9] ;
		String bPluralName = RaisTestData.businessEntityList[9] ;

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC3_testName,E2E_TestPack.e2eTC3_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);			

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,bSingularName);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , bSingularName);

			//wait for page load
			GenericMethods.pageLoadWait(3000);

			//				//Waiting until element to load
			//				GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//				GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//				GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);
			//GenericMethods.elementClick(wd, addEntityPage.attributeLink_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			//***Attribute creation method
			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[0]);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(3000);

			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[10]);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);

			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[13]);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific form - main form
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListing.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			System.out.println("Form opened");

			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);

			GenericMethods.pageLoadWait(500);
			GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, RaisTestData.sectionTitle);

			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton3_XPath);

			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);

			System.out.println("2 sections created");

			///Adding colproperties on first section

			GenericMethods.pageLoadWait(500);

			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on column prop 2 to add numeric field
			//GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);

			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			//				///Adding colproperties on 2nd section
			//				
			//				GenericMethods.pageLoadWait(600);
			//				
			//				//Clicking on column prop 2 to add text field
			//				GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//				
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				//Clicking on column prop 2 to add numeric field
			//				//GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//				
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			//				
			//				
			//				GenericMethods.pageLoadWait(500);
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//				GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			///Adding colproperties on 3rd section

			//				GenericMethods.pageLoadWait(500);
			//				
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.colProp3_Temp_XPath);		
			//				
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			//				
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//				GenericMethods.pageLoadWait(500);

			//***************End of adding col prop

			//GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");

			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, linkedFrmListing.addNewFormBtn_XPath);	
			GenericMethods.elementClickable(wd, linkedFrmListing.addNewFormBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			wd.navigate().refresh();
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//String newEntityCreatedXPATH = RAIS_applicationSpecificMethods.dashboardSubMenuDynamicXpath(pluralName);

			String businessEntityName = RAIS_applicationSpecificMethods.createCustomXpath(dashboardnew.prefixBusinessEntity_XPath, bPluralName
					, dashboardnew.suffixBusinessEntity_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, businessEntityName);
			GenericMethods.waitforElement(wd, businessEntityName);

			//Clicking on Element
			//RAIS_applicationSpecificMethods.roleSelect_Click(wd, "sub-menu", newEntityCreatedXPATH);
			GenericMethods.elementClick(wd, businessEntityName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);



			//************** below is temporariliy not used
			//initialising dyanmic xpath
			String dynamicTextBox_Xpath = null ;String dynamic_CheckBox_Xpath = null ; String dynamicNumericBox_Xpath = null ;

			dynamicTextBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.text_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicTextBox_Xpath, 
					RaisTestData.text_label), RaisTestData.text_label);



			dynamic_CheckBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.chkBox_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamic_CheckBox_Xpath, 
					RaisTestData.chkBox_label), RaisTestData.chkBox_label);


			dynamicNumericBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
							RaisTestData.numeric_label, 
							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicNumericBox_Xpath, 
					RaisTestData.numeric_label), RaisTestData.numeric_label);


			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);

			//page wait
			GenericMethods.pageLoadWait(2000);

			//TO be ocntinued
			//verify check box label
			//					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath),
			//							RaisTestData.chkBox_label);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//verify sample text field label and relevant input field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Txt_XPath),
					RaisTestData.text_label);
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath, 12,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//verify numeric field label and relevant input field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Numeric_XPath),
					RaisTestData.numeric_label);
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, 50,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);



			//verification of fields ends above

			//input data into sample check box fields
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath);

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, "Main Branch");

			//input data on dropdown fields
			//GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputFld_XPath, "Hospital");
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, entityFrmDetailspage.entityFormDetailsPage_inputFld_XPath, "Hospital");


			//selecting checkbox
			//					GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);				

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath,"Vienna");

			//input data on sample numeric and text fields
			GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, "1060");


			// Clicking on save butto
			GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);				
			//Clicking on Element				

			System.out.println("data visible on column");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "Vienna"),
					"Vienna");

			Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "1060"),
					"1060");

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//No delete of business entity required here


			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}

	}


	//#88 - Exit scenarios WIP
	@Test(priority=44,enabled=false)
	public void E2E_VerifyUserAccess() {

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC2_testName,E2E_TestPack.e2eTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Menu click to open Facility
			RAIS_applicationSpecificMethods.mainMenu_SubMenu_Click(wd, dashboardnew.invent_Resources_XPath, dashboardnew.district_XPath);

			//************************Add new data in entity
			//launch add new entity page
			RAIS_applicationSpecificMethods.addNewEntityData(wd, entityFrmListingPage.addNewBtn_XPath);

			//input list data
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='Region']", "north");

			//input Name data into fields
			RAIS_applicationSpecificMethods.inputEntityData(wd,entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath,RaisTestData.PRACTICE_MEDICAL_DATA);

			GenericMethods.tabfromElement(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath);

			//click on save
			RAIS_applicationSpecificMethods.ClickOnSave(wd, entityFrmDetailspage.SaveBtn_XPath);


			//************************Add new data in entity ends




		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			GenericMethods.pageLoadWait(1000);

			//delete entity data starts from here
			RAIS_applicationSpecificMethods.deleteDistrictsData(wd, RaisTestData.PRACTICE_MEDICAL_DATA);

			//**************************************************Delete ENDS HERE	


			//Logout user
			//RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RIAS Page
			//Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			//wd.navigate().refresh();
		}



	}

	//Testing Main menu generic
	@Test(priority=55,enabled=false)
	public void mainmenuCheck() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(E2E_TestPack.e2eTC3_testName,E2E_TestPack.e2eTC3_testDescription);

		//Calling Login method
		GenericMethods.loginApplication
		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
				password, loginPage.loginBtn_XPath);

		RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, "INVENTORY & RESOURCES", "Inventory", "Unsealed Sources");
		//		RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, "ADMINISTRATION", "User Management", 1, "Web Service Account");
		//		RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, "ADMINISTRATION", "Common Tables", 3, "Workflows");

		GenericMethods.pageLoadWait(5000);
		//Logout user
		RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

	}

	//#99 - continued for 99
	@Test(priority=99,enabled=false)
	public void e2e_FormDesigner2() {

		String calibrationXpath = "//*[@id='main-menu']//a[contains(text(),'Calibrations')]";

		//Setting Test name and description on report
		SettingRptTestName_TestDesc("Demo Test Name","Description");

		//Calling Login method
		GenericMethods.loginApplication
		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
				password, loginPage.loginBtn_XPath);

		//Waiting until element to load
		//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

		//Clicking on Element
		GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
		GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
		GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);

		//waiting for link to load and then click
		GenericMethods.elementClickable(wd, calibrationXpath);
		GenericMethods.waitforElement(wd, calibrationXpath);

		//Clicking on Element
		GenericMethods.elementClick(wd, calibrationXpath);

		//**************
		//waiting for link to load and then click
		GenericMethods.elementClickable(wd, "//*[@id='entity-details']//button[text()='Add New']");
		GenericMethods.waitforElement(wd, "//*[@id='entity-details']//button[text()='Add New']");

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//Clicking on Element
		GenericMethods.elementClick(wd, "//*[@id='entity-details']//button[text()='Add New']");

		//page wait
		GenericMethods.pageLoadWait(2000);

		//TO be ocntinued
		//verify check box label
		Assert.assertEquals(GenericMethods.getActualTxt(wd, "//*[@id='entity-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Sample Checkbox']"),
				RaisTestData.chkBox_label);

		//verify numeric field label and relevant input field
		Assert.assertEquals(GenericMethods.getActualTxt(wd, "//*[@id='entity-form']//label[text()='Sample Numeric field']"),
				RaisTestData.numeric_label);
		Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, "//*[@id='nmricFrmBldrAuto']", 50,
				RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

		//verify sample text field label and relevant input field
		Assert.assertEquals(GenericMethods.getActualTxt(wd, "//*[@id='entity-form']//label[text()='Sample Text field']"),
				RaisTestData.text_label);
		Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, "//*[@id='txtFrmBldrAuto']", 12,
				RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

		//verification of fields ends above

		//input data into sample check box fields
		//waiting for link to load and then click
		GenericMethods.elementClickable(wd, "//*[@id='entity-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Sample Checkbox']");
		GenericMethods.waitforElement(wd, "//*[@id='entity-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Sample Checkbox']");

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//input data on certificate number
		GenericMethods.sendText(wd, "//*[@id='CertificateNumber']", 
				"111");


		//Clicking on Element
		GenericMethods.elementClick(wd, "//*[@id='entity-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Sample Checkbox']");

		//input data on sample numeric and text fields
		GenericMethods.sendText(wd, "//*[@id='nmricFrmBldrAuto']", 
				"333");

		//input data on sample numeric and text fields
		GenericMethods.sendText(wd, "//*[@id='txtFrmBldrAuto']", 
				"Sample data");


		// Clicking on save butto
		GenericMethods.elementClickable(wd, "//*[@id='entity-form']//button[text()='Save']");
		GenericMethods.waitforElement(wd, "//*[@id='entity-form']//button[text()='Save']");

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//Clicking on Element
		GenericMethods.elementClick(wd, "//*[@id='entity-form']//button[text()='Save']");

		//page wait
		GenericMethods.pageLoadWait(2000);



	}


	//Test case Ends here *********************************


	//After each method reporting pass/ fail in report
	@AfterMethod
	public void getResult(ITestResult result) {

		//Calling verify pass-fail method which will return pass or fail.
		TESTSTATUS = verifyPassFail(result, TestName);

		//Below is used for counting passed and failed test cases
		if(browserCategory.equalsIgnoreCase(BrowserChrome)) {

			//Increment pass test case count
			PassTc_Ch = totalPasscount;
			FailTc_Ch = totalFailcount;

		}else if(browserCategory.equalsIgnoreCase(BrowserFF)) {

			//Increment pass test case count
			PassTc_FF = totalPasscount;
			FailTc_FF = totalFailcount;

		}	
	}

	//After entire test set below code to execute
	@AfterTest
	public void tearDown() {

		try {
			//Flush all report details
			closeExtentReport();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//After class browser close and email sending##############################
	@AfterClass
	public void afterClass() {
		try {
			//close browser session
			BrowserClose();
			TotalTc_Ch = PassTc_Ch+FailTc_Ch;
			System.out.println("Printing Total TC = " + TotalTc_Ch);
			System.out.println("Total pass Test cases " +PassTc_Ch);
			System.out.println("Total Fail Test cases " +FailTc_Ch);

			System.out.println("Printing Total TC = " + TotalTc_FF);
			System.out.println("Total pass Test cases " +PassTc_FF);
			System.out.println("Total Fail Test cases " +FailTc_FF);

			//Reset count
			totalPasscount = 0;
			totalFailcount = 0;			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterSuite
	public void afterSuite( ) throws Exception {

		//Send Mail feature commented
		//sendMailReport(PassTc, FailTc);
		//sendMailReport(PassTc_Ch, FailTc_Ch,PassTc_FF, FailTc_FF);


	}
}
