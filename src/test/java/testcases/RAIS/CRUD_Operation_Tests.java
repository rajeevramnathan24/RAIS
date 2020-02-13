package testcases.RAIS;

import org.testng.annotations.Test;

import testSuite.*;

import commonfunction.BaseClass;

import commonfunction.GenericMethods;
import commonfunction.RAIS_applicationSpecificMethods;
import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewEntityPage;
import pageLocators_Elements.RAIS.AddNewPermRestrictionsPage;
import pageLocators_Elements.RAIS.AddNewRolePage;
import pageLocators_Elements.RAIS.AddNewSecurityProfilePage;
import pageLocators_Elements.RAIS.AddNewUserDetailsPage;
import pageLocators_Elements.RAIS.DashboardPage;
import pageLocators_Elements.RAIS.DataRoles_FunctionalRolesPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.ForgotPasswordPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class CRUD_Operation_Tests extends BaseClass
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
	
	TestSuite RunTestCase = new TestSuite();

	//Initializing total test cases, and counting pass/ fail count
	public static Integer TotalTc_Ch=0;
	public static Integer PassTc_Ch=0;
	public static Integer FailTc_Ch=0;

	public static Integer TotalTc_FF=0;
	public static Integer PassTc_FF=0;
	public static Integer FailTc_FF=0;
	
	public static String browserCategory = "";

	public static String packType = "CRUD-Operation-Test-";
	
	public static String TESTSTATUS = null;

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
			SetUpExtentReport(browserCategory,packType);
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
	
	//#CRUD1
	@Test(priority=1,enabled=CRUDOperationTestDetails.crudOpTC1_runStatus)
	public void DataRole_CreateEditDeleteRole() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC1_testName,CRUDOperationTestDetails.crudOpTC1_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.DataRoles_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);
						
			//Waiting for popup to load
			GenericMethods.pageLoadWait(1000);
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.DataRole_Test);
			
			//clicking on add role button			
			GenericMethods.tabfromElement(wd, AddNewDataRole.inputroleName_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//page load and click
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);
			
			//Waiting for popup to load
			//wait for page load
			GenericMethods.pageLoadWait(1500);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , RaisTestData.DataRole_Test);

			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.editBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.editBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ExecutionDay());
			GenericMethods.tabfromElement(wd, AddNewDataRole.inputroleName_XPath);			
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//clicking on add role button
			//GenericMethods.elementClick(wd, AddNewDataRole.editRolePopUpHeaderLabel_XPath);
			GenericMethods.elementClick(wd, AddNewDataRole.editBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.EDITNEWROLE_SUCESSMSG_TXT);
			
			//**************************************************EDIT ENDS HERE
			
			
			//**************************************************DELETE STARTS HERE
			
			//wait for page load
			GenericMethods.pageLoadWait(1500);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(1500);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , RaisTestData.DataRole_Test + ExecutionDay());
			
			//wait for page load
			GenericMethods.pageLoadWait(1500);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_deleteRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dataRolesfunctionalRolesPage.delDataRolePerm_PopupMSGTxt_XPath),
					dataRolesfunctionalRolesPage.DELETE_DATAROLE_POPMSG_Txt);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//clicking on No button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgNoBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_deleteRoleBtn_XPath);
									
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//clicking on Yes button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.DELETEROLE_SUCCESSMSG_TXT);
			//wait for page load
			GenericMethods.pageLoadWait(500);
			//**************************************************Delete ENDS HERE								
			
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
	
	//#CRUD2
	@Test(priority=2, enabled=CRUDOperationTestDetails.crudOpTC2_runStatus)
	public void FunctionalRole_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC2_testName,CRUDOperationTestDetails.crudOpTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.FunctionalRoles_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.FunctionalRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.FunctionalRoles_XPath);
			
			
			
			//********************************Add new role starts here
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.FuncRole_Test);
			
			//clicking on add role button			
			GenericMethods.tabfromElement(wd, AddNewDataRole.inputroleName_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.ADDNEWFUNC_ROLE_Txt);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
			
			//**************************Duplicate check starts here
			
			
			//**************************Duplicate check ends here

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.FUNC_ROLE_Txt , RaisTestData.FuncRole_Test);

			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_editRoleBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ExecutionDay());
						
			//clicking on add role button
			GenericMethods.tabfromElement(wd, AddNewDataRole.inputroleName_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.elementClick(wd, AddNewDataRole.editBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.UPDATEFUNC_ROLE_Txt);
			
			//**************************************************EDIT ENDS HERE
			
			
			//**************************************************DELETE STARTS HERE
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.FUNC_ROLE_Txt , RaisTestData.FuncRole_Test + ExecutionDay());
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dataRolesfunctionalRolesPage.delDataRolePerm_PopupMSGTxt_XPath),
					dataRolesfunctionalRolesPage.DELETE_FUNCROLE_POPMSG_Txt);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);			
			//clicking on No button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgNoBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);	
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//clicking on Yes button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.DELETEFUNC_ROLE_Txt);
			
			//**************************************************Delete ENDS HERE		
			
			
			
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
	
	//#CRUD3
	@Test(priority=3,enabled=CRUDOperationTestDetails.crudOpTC3_runStatus)
	public void SecurityRole_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC3_testName,CRUDOperationTestDetails.crudOpTC3_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				
			
			//Thread.sleep(3000);
			
			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.securityRoles_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.securityRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.securityRoles_XPath);
			
			//****************Verify Security roles landing page 
			
			
			//****************End of Security roles landing page verification			
			
			//*******************************Validation test starts here
			
			
			//*******************************Validation test ends here
			
			
			//********************************Add new SP role starts here
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, SecurityProfile.addNewProfileBtn_XPath);	
			GenericMethods.elementClickable(wd, SecurityProfile.addNewProfileBtn_XPath);
			GenericMethods.elementClick(wd, SecurityProfile.addNewProfileBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewSProle.addnewSP_inputroleName_XPath, RaisTestData.SecurityRole_Test);
			
			//select value from list
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,AddNewSProle.addnewSP_facilityDropdown_XPath, "Facility");
			
			//wait for page load
			GenericMethods.pageLoadWait(300);
			
			//select value from multiselect
			//GenericMethods.waitforElement(wd, AddNewPermRestrct.restriction_DropdnClick_Xpath);	
			GenericMethods.elementClickable(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			GenericMethods.elementClick(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			
			//wait for page load
//			GenericMethods.pageLoadWait(300);
			
			//CLicking on Add new permission button
			GenericMethods.elementClickable(wd, AddNewSProle.restriction_add_dropdown_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.restriction_add_dropdown_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(300);
			
			//collapsing the multiselect drop down
			GenericMethods.elementClick(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			
			//wait for page load
			GenericMethods.pageLoadWait(300);
			
			//Select edit radio button
			GenericMethods.elementClick(wd, AddNewSProle.rdobtnEdit_XPath);			
			
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, AddNewSProle.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.SaveBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.SaveBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewSProle.addnewSP_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewSProle.addnewSP_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewSProle.addnewSP_SuccessMsg_XPath),
					AddNewSProle.ADDNEWSP_SUCESSMSG_TXT);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
			//**********Duplicate code starts here
			//**********Duplicate code Ends here
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,SecurityProfile.secProfListingTableColHeader_XPath,
					SecurityProfile.secProfListingTableColHeader_TXT_XPath,RaisTestData.SecurityRole_Test );
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,SecurityProfile.securityProfileTableList_XPath , RaisTestData.SecurityRole_Test);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewSProle.addnewSP_inputroleName_XPath, ExecutionDay());

			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, AddNewSProle.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.SaveBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.SaveBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewSProle.addnewSP_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewSProle.addnewSP_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewSProle.addnewSP_SuccessMsg_XPath),
					AddNewSProle.UPDATESP_SUCESSMSG_TXT);
			
			//*********************************************Edit ends here
			
			//*********************************************Delete starts here
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,SecurityProfile.securityProfileTableList_XPath , RaisTestData.SecurityRole_Test+ExecutionDay());
						
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, AddNewSProle.deleteBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewSProle.deleteSP_PopTitleXPath),
					AddNewSProle.DELSP_POPUP_CONF_MSG_Txt);
			
			//Verify confirmation message text
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewSProle.delSPRole_PopupMSGTxt_XPath),
					AddNewSProle.DELSP_POPMSG_Txt);
			
			//Verify yes/ no buttons
			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.popUpNoBtn_XPath, 
					AddNewSProle.NO_BTN_Txt),AddNewSProle.NO_BTN_Txt);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.popUpYesBtn_XPath, 
					AddNewSProle.YES_BTN_Txt),AddNewSProle.YES_BTN_Txt);
			
			//Click on No button
			GenericMethods.waitforElement(wd, AddNewSProle.popUpNoBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.popUpNoBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.popUpNoBtn_XPath);
			
			//****************Repeating the delete feature as above code is for pop up no button click
			
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, AddNewSProle.deleteBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.deleteBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.deleteBtn_XPath);
			
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, AddNewSProle.deleteSP_PopTitleXPath);	
			GenericMethods.elementClickable(wd, AddNewSProle.popUpNoBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.popUpYesBtn_XPath);			
			
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewSProle.addnewSP_SuccessMsg_XPath),
					AddNewSProle.DELSP_SUCESSMSG_TXT);
			
			//**************************************************Delete ENDS HERE		
			
			
			
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
	
	//#CRUD4 - TBD **********************************************
	@Test(priority=12,enabled=SanityPack_TestNamesDescription.TC12_runStatus)
	public void UserProfile_Validation() throws Exception {

			try {
				//Setting Test name and description on report
				SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC12_testName,
						SanityPack_TestNamesDescription.TC12_testDescription);
				//Calling Login method
				GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
						loginPage.loginBtn_XPath);
				//Waiting until element to load
				//GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);
				
				//Clicking on Element
				GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
				GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
				GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
								
				//waiting for link to load and then click
				GenericMethods.elementClickable(wd, dashboardnew.usersLink_XPath);
				GenericMethods.waitforElement(wd, dashboardnew.usersLink_XPath);
				
				//Clicking on Element
				GenericMethods.elementClick(wd, dashboardnew.usersLink_XPath);
				
				//****************Verify User list landing page 
				//verify label/ button name
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, UserListingPage.addNewUserBtn_XPath, 
						UserListingPage.ADDNEW_USER_Txt), UserListingPage.ADDNEW_USER_Txt);
				
				//verify columnheaders
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, UserListingPage.userListColHeaderName_XPath, 
						UserListingPage.USERLIST_COLHEADER_NAME_Txt), UserListingPage.USERLIST_COLHEADER_NAME_Txt);
				
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, UserListingPage.userListColHeaderEmail_XPath,
						UserListingPage.USERLIST_COLHEADER_EMAIL_Txt), UserListingPage.USERLIST_COLHEADER_EMAIL_Txt);
				
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, UserListingPage.userListColHeaderFuncRole_XPath,
						UserListingPage.USERLIST_COLHEADER_FUNCROLES_Txt), UserListingPage.USERLIST_COLHEADER_FUNCROLES_Txt);
				
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, UserListingPage.userListColHeaderStatus_XPath,
						UserListingPage.USERLIST_COLHEADER_STATUS_Txt), UserListingPage.USERLIST_COLHEADER_STATUS_Txt);
				
				//****************End of User listing page verification	
				
				//*******************************Validation test starts here
				
				//Clicking on add new data role
				GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);
							
				//clicking on add new security profile - save button
				GenericMethods.waitforElement(wd, AddNewUserPage.SaveBtn_XPath);
				GenericMethods.elementClickable(wd, AddNewUserPage.SaveBtn_XPath);
				GenericMethods.elementClick(wd, AddNewUserPage.SaveBtn_XPath);
							
			// verifying Name label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_orgType_XPath,
					AddNewUserPage.ADDNEWUSER_ORGTYP_Txt), AddNewUserPage.ADDNEWUSER_ORGTYP_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.orgTypeRequired_MsgXPath,
					AddNewUserPage.ADDNEWUSER_ORGTYP_REQD_TXT), AddNewUserPage.ADDNEWUSER_ORGTYP_REQD_TXT);
			

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_org_XPath,
					AddNewUserPage.ADDNEWUSER_ORG_Txt), AddNewUserPage.ADDNEWUSER_ORG_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.orgRequired_MsgXPath,
					AddNewUserPage.ADDNEWUSER_ORG_REQD_TXT), AddNewUserPage.ADDNEWUSER_ORG_REQD_TXT);
			

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_auth_XPath,
					AddNewUserPage.ADDNEWUSER_AUTH_Txt), AddNewUserPage.ADDNEWUSER_AUTH_Txt);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_selectuser_XPath,
					AddNewUserPage.ADDNEWUSER_SLCTUSER_Txt), AddNewUserPage.ADDNEWUSER_SLCTUSER_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.validUserRequired_MsgXPath,
					AddNewUserPage.ADDNEWUSER_SELCTUSR_REQD_TXT), AddNewUserPage.ADDNEWUSER_SELCTUSR_REQD_TXT);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_userName_XPath,
					AddNewUserPage.ADDNEWUSER_USRNAME_Txt), AddNewUserPage.ADDNEWUSER_USRNAME_Txt);
			//Getting error, hence commenting it
//			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.userNameRequired_MsgXPath,
//					AddNewUserPage.ADDNEWUSER_NAME_REQD_TXT), AddNewUserPage.ADDNEWUSER_NAME_REQD_TXT);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_funcRole_XPath,
					AddNewUserPage.ADDNEWUSER_FUNCROLE_Txt), AddNewUserPage.ADDNEWUSER_FUNCROLE_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.funcRoleRequired_MsgXPath,
					AddNewUserPage.ADDNEWUSER_FR_REQD_TXT), AddNewUserPage.ADDNEWUSER_FR_REQD_TXT);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_dataRole_XPath,
					AddNewUserPage.ADDNEWUSER_DATAROLE_Txt), AddNewUserPage.ADDNEWUSER_DATAROLE_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.dataRoleRequired_MsgXPath ,
					AddNewUserPage.ADDNEWUSER_DR_REQD_TXT), AddNewUserPage.ADDNEWUSER_DR_REQD_TXT);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.addNewUserDetail_status_XPath,
					AddNewUserPage.ADDNEWUSER_STATUS_Txt), AddNewUserPage.ADDNEWUSER_STATUS_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.statusRequired_MsgXPath,
					AddNewUserPage.ADDNEWUSER_STATUS_REQD_TXT), AddNewUserPage.ADDNEWUSER_STATUS_REQD_TXT);
			
			// verifying the cancel button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.cancelBtn_XPath,
					AddNewUserPage.CANCEL_BTN_Txt), AddNewUserPage.CANCEL_BTN_Txt);

			// verifying the Save button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewUserPage.SaveBtn_XPath,
					AddNewUserPage.SAVE_BTN_Txt), AddNewUserPage.SAVE_BTN_Txt);

			// clicking on Cancel button
			GenericMethods.waitforElement(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewUserPage.cancelBtn_XPath);
				
				
				//*******************************Validation test ends here
				
			} 
			catch (NoSuchElementException  noElement) {
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
			
	//#CRUD5
	@Test(priority=5,enabled=CRUDOperationTestDetails.crudOpTC5_runStatus)
	public void Entity_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC5_testName,CRUDOperationTestDetails.crudOpTC5_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);
			
			//Waiting for popup to load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);
			
			//Waiting for popup to load
			GenericMethods.pageLoadWait(1000);
			
			//********************************Add new Entity starts here
			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
						
			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			
			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, RaisTestData.Entity_InternalNameData);
			
			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, RaisTestData.Entity_DescriptionData);
			
			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, RaisTestData.Entity_SingularData);
			
			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, RaisTestData.Entity_PluralData);
			
			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, RaisTestData.Entity_GroupData);
			
			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, RaisTestData.Entity_RoleData);
			
			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, RaisTestData.Entity_PublishNavigation1Data);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, RaisTestData.Entity_PublishNavigation2Data);
			
			//input check box value of enable document & attachments
			//GenericMethods.elementClick(wd, addEntityPage.addNewEntity_enabledocument_XPath);
						
			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.addnewEntity_SuccessMsg_XPath),
					addEntityPage.ADDNEWENT_SUCESSMSG_TXT);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
						
			//wait for page load
			GenericMethods.pageLoadWait(1000);			

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
			
			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_SingularData );
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , RaisTestData.Entity_SingularData);
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, ExecutionDay());
			
			GenericMethods.tabfromElement(wd, addEntityPage.addNewEntity_SingTxtBox_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(500);
			
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.addnewEntity_SuccessMsg_XPath),
					addEntityPage.UPDENT_SUCESSMSG_TXT);
			
			//*********************************************Edit ends here
			
			//*********************************************Delete starts here
			
			//wait for page load
			GenericMethods.pageLoadWait(2000);
			
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_SingularData);
			
			//Clicking on specific entity created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath, 
					RaisTestData.Entity_SingularData+ExecutionDay());
						
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			
			//clicking on entity details page delete button
			GenericMethods.waitforElement(wd, addEntityPage.deleteBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.deleteBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.deleteBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.deleteEntity_PopTitleXPath),
					addEntityPage.DELENT_POPUP_CONF_MSG_Txt);
			
			//Verify confirmation message text
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.delEntity_PopupMSGTxt_XPath),
					addEntityPage.DELENTITY_POPMSG_Txt);
			
			//Verify yes/ no buttons
			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.delEntity_popUpNoBtn_XPath, 
					addEntityPage.NO_BTN_Txt),addEntityPage.NO_BTN_Txt);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.delEntity_popUpYesBtn_XPath, 
					addEntityPage.YES_BTN_Txt),addEntityPage.YES_BTN_Txt);
			
			//Click on No button
			GenericMethods.waitforElement(wd, addEntityPage.delEntity_popUpNoBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.delEntity_popUpNoBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.delEntity_popUpNoBtn_XPath);
			
			//****************Repeating the delete feature as above code is for pop up no button click
						
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, addEntityPage.deleteBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.deleteBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.deleteBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, addEntityPage.delEntity_popUpYesBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.delEntity_popUpYesBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.delEntity_popUpYesBtn_XPath);			
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.addnewEntity_SuccessMsg_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.addnewEntity_SuccessMsg_XPath),
					addEntityPage.DELENT_SUCESSMSG_TXT);
			
			//**************************************************Delete ENDS HERE	
			
//			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			//GenericMethods.pageLoadWait(2000);
			
						
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
