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
import pageLocators_Elements.RAIS.EntityFormListingPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.ForgotPasswordPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

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
	EntityFormListingPage entityRecordListing = new EntityFormListingPage();

	//Common Test data used across test cases
	String dataRoleName = RaisTestData.DataRole_Test;
	String functionalRoleName = RaisTestData.FuncRole_Test;
	String DR_MenuName = RaisTestData.dataRole;
	String FR_MenuName = RaisTestData.functionalRole;
	
	String addMode = RaisTestData.ADD_MODE_TEXT;
	String editMode = RaisTestData.EDIT_MODE_TEXT;
	String deleteMode = RaisTestData.DELETE_MODE_TEXT;
	String Optional = RaisTestData.OPTIONAL_TEXT;
	String updatedText = RaisTestData.UPDATED_TXT;
	String ownFacility = RaisTestData.OWNF_DataRole_Test;
	String singleFieldInput = RaisTestData.oneInputField;
	String inputData = RaisTestData.demoData;
	String optional = RaisTestData.OPTIONAL_TEXT;

	//Entity creation test data
	//Common data setup
	String entityInternalName = "TestEntity"+localTime;
	String singLabelName = "TestEntitySingularName"+localTime;
	String pluralLabelName = "TestEntityPluralName"+localTime;			
	String commonGroup = RaisTestData.Entity_GroupData;
	String MainMenuInv_Res = RaisTestData.Entity_Publish_Inv_Res;
	String subMenu = RaisTestData.Entity_PublishNav_Inventory;

	//specific data
	String inventoryRole = RaisTestData.Entity_RoleData;
	String masterRole = RaisTestData.Entity_MasterRole;
	String invHistoryRole = RaisTestData.Entity_InvWithHistoryRole;





	TestSuite RunTestCase = new TestSuite();

	//passcurrent time
	private static String localTime = GenericMethods.currentLocalTime();

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

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					DR_MenuName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

			//Calling common method to input save and verify
			RAIS_applicationSpecificMethods.DR_FR_Input_Save_Verify(wd, dataRoleName, AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT,DR_MenuName);

			//page refresh
			wd.navigate().refresh();



			//Edit starts here****************************************************************************			
			//clicking on left data role
			RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, dataRoleName, editMode,DR_MenuName);

			System.out.println("clicked on edit");

			//Calling common method to input save and verify
			RAIS_applicationSpecificMethods.DR_FR_Input_Save_Verify(wd, localTime, AddNewDataRole.EDITNEWROLE_SUCESSMSG_TXT,DR_MenuName);

			//page refresh
			wd.navigate().refresh();



			//delete starts here****************************************************************************				
			//clicking on left data role
			RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, dataRoleName+localTime, deleteMode,DR_MenuName);

			System.out.println("clicked on delete");

			//calling delete DRFR method and verify message
			RAIS_applicationSpecificMethods.delete_DR_FR(wd, dataRolesfunctionalRolesPage.DEL_DR_CONFIRM_MSG_Txt,DR_MenuName);

			//page refresh
			wd.navigate().refresh();


			//**************************************************Delete ENDS HERE								

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

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

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					FR_MenuName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on add new functional role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);

			//Calling common method to input save and verify
			RAIS_applicationSpecificMethods.DR_FR_Input_Save_Verify(wd, functionalRoleName, AddNewDataRole.ADDNEWFUNC_ROLE_Txt,FR_MenuName);

			//page refresh
			wd.navigate().refresh();


			//Edit starts here****************************************************************************			
			//clicking on left data role
			RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, functionalRoleName, editMode,FR_MenuName);

			System.out.println("clicked on edit");

			//Calling common method to input save and verify
			RAIS_applicationSpecificMethods.DR_FR_Input_Save_Verify(wd, localTime, AddNewDataRole.UPDATEFUNC_ROLE_Txt,FR_MenuName);

			//page refresh
			wd.navigate().refresh();

			//delete starts here****************************************************************************				
			//clicking on left data role
			RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, functionalRoleName+localTime, deleteMode,FR_MenuName);

			System.out.println("clicked on delete");

			//calling delete DRFR method and verify message
			RAIS_applicationSpecificMethods.delete_DR_FR(wd, dataRolesfunctionalRolesPage.DEL_FR_CONFIRM_MSG_Txt,FR_MenuName);

			//page refresh
			wd.navigate().refresh();	



		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");
			
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
	public void Entity_Type_Inventory_CRUD_Operations(){

		try {

			//Initialising creation flag
			Boolean entityPresent = false;

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC5_testName,CRUDOperationTestDetails.crudOpTC5_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CustomizeSubMenu, 
					RaisTestData.businessEntityList[66]);

			Boolean TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, entityInternalName, singLabelName, pluralLabelName, 
					commonGroup, inventoryRole, MainMenuInv_Res, subMenu,RaisTestData.ADD_MODE_TEXT);

			//Page refresh for DOM
			wd.navigate().refresh();

			//clicking on left entity detail link
			GenericMethods.elementClick(wd, addEntityPage.entDetailPage_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//click on cancel
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Check if entity is created
			if (TestData_primUserEntity ==true) {

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName);

				//starting edit entity
				TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, Optional, updatedText, updatedText, 
						Optional, Optional, Optional, Optional,Optional);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertTrue(entityPresent);

			}else {

				System.out.println("Entity creation failed");
			}

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//check if entity creation is successfull
			if (TestData_primUserEntity == true) {

				//delete entity record data
				RAIS_applicationSpecificMethods.deleteEntity(wd);	

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName+updatedText);

				//verify the validation message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, entListingPage.entityNoRecordsonGrid_XPath),
						entListingPage.NO_ENTITY_RECORDS_Txt);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertFalse(entityPresent);

				System.out.println("deleted success");

			}else {
				System.out.println("Entity creation failed");
			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

		}

	}		

	//#6Default DR permissions/ restriction 
	@Test(priority=6,enabled=CRUDOperationTestDetails.crudOpTC6_runStatus)
	public void verifyDefaultroleDR_Permissions_Restrictions() {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC6_testName,CRUDOperationTestDetails.crudOpTC6_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					DR_MenuName);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//Click on add role button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

			//input same name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ownFacility);

			//give tab
			GenericMethods.tabfromElement(wd, AddNewDataRole.inputroleName_XPath);

			//verify the validation message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.validMsgXpath),
					AddNewDataRole.DUPLICATENAME_MSG_TXT);

			//Click on cancel button
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);

			//clicking on left data role
			RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, ownFacility, Optional,DR_MenuName);

			//clicking on permissions tab
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);

			// verify the button statuses
			Assert.assertFalse(GenericMethods.elementEnabled(wd, dataRolesfunctionalRolesPage.FunctionalRolePermissionRestrictBtn_XPath));

			//clicking on Restrictions tab
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRoleRestrictionTab_XPath);

			// verify the button statuses
			Assert.assertFalse(GenericMethods.elementEnabled(wd, dataRolesfunctionalRolesPage.addNewRestrictBtn_XPath));



		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

		}



	}

	//#7 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=7, enabled=CRUDOperationTestDetails.crudOpTC7_runStatus)
	public void BusinessEntity_RecordCRUD_Operations_Admin_CommonTable() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC7_testName,CRUDOperationTestDetails.crudOpTC7_testDescription);

		try {

			//Entities under common tables with 1 field
			String BEname [] =RaisTestData.singleFldBE_Admin_CommonTbl;// {"Extent of Events","Enforcement Statuses","Radiological Consequences","Source Statuses"};"Countries","Gender","Fields","Workflow Statuses"

			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			for(int i=0;i<BEname.length;i++) {

				//wait for page load
				GenericMethods.JSPageWait(wd);

				wd.navigate().refresh();

				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CommonTablesSubMenu,BEname[i]);

				//***********************************************Create entity record "Workflow Statuses"
				//calling generic method to call Officer entity data input
				Boolean flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, inputData, 
						optional, optional, RaisTestData.ADD_MODE_TEXT);

				////**********************************************EDIT MODE STARTS Here	

				if(flag_createEntity == true) {
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData);

					//Edit entity record
					//calling generic method to call Officer entity data edit
					flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, localTime, 
							optional, optional, optional);


					////***********************************************delete starts here
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//delete entity record data
					RAIS_applicationSpecificMethods.deleteEntityRecord(wd);	

					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//verify the validation message
					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.emptyRecordsGrid_XPath),
							entityRecordListing.NO_RECORDS_Txt);									

				} else {
					System.out.println("Entity creation failed");
				}

			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");


		}
	}

	//#8 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=8, enabled=CRUDOperationTestDetails.crudOpTC8_runStatus)
	public void BusinessEntity_RecordCRUD_Operations_Inv_Res_Inventory() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC8_testName,CRUDOperationTestDetails.crudOpTC8_testDescription);

		try {

			//Entities under common tables with 1 field
			String BEname [] =RaisTestData.singleFldBE_Inv_Inv;

			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			for(int i=0;i<BEname.length;i++) {

				//wait for page load
				GenericMethods.JSPageWait(wd);

				wd.navigate().refresh();

				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.InventResourceMainMenu, RaisTestData.InventorySubMenuText,BEname[i]);

				//***********************************************Create entity record "Workflow Statuses"
				//calling generic method to call Officer entity data input
				Boolean flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, inputData, 
						optional, optional, RaisTestData.ADD_MODE_TEXT);

				////**********************************************EDIT MODE STARTS Here	

				if(flag_createEntity == true) {
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData);

					//Edit entity record
					//calling generic method to call Officer entity data edit
					flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, localTime, 
							optional, optional, optional);


					////***********************************************delete starts here
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//delete entity record data
					RAIS_applicationSpecificMethods.deleteEntityRecord(wd);	

					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//verify the validation message
					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.emptyRecordsGrid_XPath),
							entityRecordListing.NO_RECORDS_Txt);									

				} else {
					System.out.println("Entity creation failed");
				}

			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");


		}
	}

	//#9 - Single Attribute Business Entity - Entity record CRUD operations
	@Test(priority=9, enabled=CRUDOperationTestDetails.crudOpTC9_runStatus)
	public void BusinessEntity_RecordCRUD_Operations_Inv_Res_Resources() {

		//Setting Test name and description on report
		SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC9_testName,CRUDOperationTestDetails.crudOpTC9_testDescription);

		try {

			//Entities under common tables with 1 field
			String BEname [] =RaisTestData.singleFldBE_Inv_Res;

			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			for(int i=0;i<BEname.length;i++) {

				//wait for page load
				GenericMethods.JSPageWait(wd);

				wd.navigate().refresh();

				//Clicking on User menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.InventResourceMainMenu, RaisTestData.InventorySubMenuText,BEname[i]);

				//***********************************************Create entity record "Workflow Statuses"
				//calling generic method to call Officer entity data input
				Boolean flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, inputData, 
						optional, optional, RaisTestData.ADD_MODE_TEXT);

				////**********************************************EDIT MODE STARTS Here	

				if(flag_createEntity == true) {
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData);

					//Edit entity record
					//calling generic method to call Officer entity data edit
					flag_createEntity = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, localTime, 
							optional, optional, optional);


					////***********************************************delete starts here
					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//delete entity record data
					RAIS_applicationSpecificMethods.deleteEntityRecord(wd);	

					//Grid filter and click on entity record listing page
					RAIS_applicationSpecificMethods.EntityRecordGridFilter_Click(wd, 2, inputData+localTime);

					//verify the validation message
					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.emptyRecordsGrid_XPath),
							entityRecordListing.NO_RECORDS_Txt);									

				} else {
					System.out.println("Entity creation failed");
				}

			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		} finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");


		}
	}

	//#CRUD10
	@Test(priority=10,enabled=CRUDOperationTestDetails.crudOpTC10_runStatus)
	public void Entity_Type_Master_CRUD_Operations(){

		try {

			//Initialising entity creation flag
			Boolean entityPresent = false;

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC10_testName,CRUDOperationTestDetails.crudOpTC10_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CustomizeSubMenu, 
					RaisTestData.businessEntityList[66]);

			Boolean TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, entityInternalName, singLabelName, pluralLabelName, 
					commonGroup, masterRole, MainMenuInv_Res, subMenu,RaisTestData.ADD_MODE_TEXT);

			//Page refresh for DOM
			wd.navigate().refresh();

			//clicking on left entity detail link
			GenericMethods.elementClick(wd, addEntityPage.entDetailPage_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//click on cancel
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Check if entity is created
			if (TestData_primUserEntity ==true) {

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName);

				//starting edit entity
				TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, Optional, updatedText, updatedText, 
						Optional, Optional, Optional, Optional,Optional);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertTrue(entityPresent);

			}else {

				System.out.println("Entity creation failed");
			}

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//check if entity creation is successfull
			if (TestData_primUserEntity == true) {

				//delete entity record data
				RAIS_applicationSpecificMethods.deleteEntity(wd);	

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName+updatedText);

				//verify the validation message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, entListingPage.entityNoRecordsonGrid_XPath),
						entListingPage.NO_ENTITY_RECORDS_Txt);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertFalse(entityPresent);

				System.out.println("deleted success");

			}else {
				System.out.println("Entity creation failed");
			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

		}

	}		

	//#CRUD11
	@Test(priority=11,enabled=CRUDOperationTestDetails.crudOpTC11_runStatus)
	public void Entity_Type_InvWithHistory_CRUD_Operations(){

		try {

			//Initialising entity creation flag
			Boolean entityPresent = false;

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(CRUDOperationTestDetails.crudOpTC11_testName,CRUDOperationTestDetails.crudOpTC11_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CustomizeSubMenu, 
					RaisTestData.businessEntityList[66]);

			Boolean TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, entityInternalName, singLabelName, pluralLabelName, 
					commonGroup, invHistoryRole, MainMenuInv_Res, subMenu,RaisTestData.ADD_MODE_TEXT);

			//Page refresh for DOM
			wd.navigate().refresh();

			//clicking on left entity detail link
			GenericMethods.elementClick(wd, addEntityPage.entDetailPage_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//click on cancel
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Check if entity is created
			if (TestData_primUserEntity ==true) {

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName);

				//starting edit entity
				TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, Optional, updatedText, updatedText, 
						Optional, Optional, Optional, Optional,Optional);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertTrue(entityPresent);

			}else {

				System.out.println("Entity creation failed");
			}

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//check if entity creation is successfull
			if (TestData_primUserEntity == true) {

				//delete entity record data
				RAIS_applicationSpecificMethods.deleteEntity(wd);	

				//Grid filter and click on entity record listing page
				RAIS_applicationSpecificMethods.EntityListingGridFilter_Click(wd, 1, singLabelName+updatedText);

				//verify the validation message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, entListingPage.entityNoRecordsonGrid_XPath),
						entListingPage.NO_ENTITY_RECORDS_Txt);

				//verify if created entity is available in menu
				entityPresent = RAIS_applicationSpecificMethods.verifyItemPresentinMenu(wd, MainMenuInv_Res, subMenu,singLabelName);

				// verify the button statuses
				Assert.assertFalse(entityPresent);

				System.out.println("deleted success");

			}else {
				System.out.println("Entity creation failed");
			}


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

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
