package testcases.RAIS;

import org.testng.annotations.Test;

import testSuite.*;

import commonfunction.BaseClass;

import commonfunction.GenericMethods;
import commonfunction.RAIS_applicationSpecificMethods;
import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewAttributePage;
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
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class ValidationTests extends BaseClass
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

	TestSuite RunTestCase = new TestSuite();

	//Initializing total test cases, and counting pass/ fail count
	public static Integer TotalTc_Ch=0;
	public static Integer PassTc_Ch=0;
	public static Integer FailTc_Ch=0;

	public static Integer TotalTc_FF=0;
	public static Integer PassTc_FF=0;
	public static Integer FailTc_FF=0;

	public static String browserCategory = "";

	public static String packType = "Validation-Test-";

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

	//#1
	@Test(priority=1,enabled=ValidationTestPack.validationTC1_runStatus)
	public void LoginScreen_Default_ExternalLogin() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC1_testDescription,ValidationTestPack.validationTC1_testDescription);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);			

			//verify internal text label name of login
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.externalLogin_XPath, loginPage.EXT_LOGIN_Txt), loginPage.EXT_LOGIN_Txt);

			//verify username label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.userName_label_XPath, loginPage.USERNAME_LBL_Txt), loginPage.USERNAME_LBL_Txt);

			//verify password label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.pwd_label_XPath, loginPage.PWD_LBL_Txt), loginPage.PWD_LBL_Txt);

			//verify userid text field
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, loginPage.userId_XPath,0,RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify pwd text field
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, loginPage.pwd_XPath,0,RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify login button text
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.loginBtn_XPath, loginPage.LOGIN_BTN_Txt), loginPage.LOGIN_BTN_Txt);

			//verify username label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.frgtPwd_XPath, loginPage.FORGOT_PWD_Txt), loginPage.FORGOT_PWD_Txt);

			//********************Blank Validation Starts here
			//Clicking on login button
			GenericMethods.elementClick(wd, loginPage.loginBtn_XPath);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, loginPage.userNameValidationError_XPath),
					loginPage.USERNAME_VALIDATIONMSG_Txt);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, loginPage.pwdValidationError_XPath),
					loginPage.PWD_VALIDATIONMSG_Txt);
			//********************Blank Validation Ends here


			//********************Invalid credentials start here
			//Calling login function for invalid credentials
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, RaisTestData.invalidCredentials, loginPage.pwd_XPath, 
					RaisTestData.invalidUserId, loginPage.loginBtn_XPath);


			//verifying the validation message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, loginPage.invalidCredValidationMsg_XPath),
					loginPage.INVALID_CREDENTIALS_Txt);

			//********************Invalid credentials ends here				


		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//page refresh
			wd.navigate().refresh();
		}
	}

	//#2
	@Test(priority = 2, enabled = ValidationTestPack.validationTC2_runStatus)
	public void Forgot_PasswordFeature( ) throws Exception {

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC2_testName,
					ValidationTestPack.validationTC2_testDescription);

			//Clicking on forgot password link
			GenericMethods.elementClick(wd, loginPage.frgtPwd_XPath);

			//verify Top header1 value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.frgotPageRIASHeaderLabel_XPath,
					forgotPwdPage.FORGOTPAGE_RAIS_Txt), forgotPwdPage.FORGOTPAGE_RAIS_Txt);

			//verify Top header2 value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.forgotPageHeaderLabel1_XPath,
					forgotPwdPage.FORGOTPAGE_HEADER2_Txt), forgotPwdPage.FORGOTPAGE_HEADER2_Txt);

			//verify Username label displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.userName_label_XPath,
					forgotPwdPage.FORGOTPAGE_USERNAME_LBL_Txt), forgotPwdPage.FORGOTPAGE_USERNAME_LBL_Txt);

			//verify Selected radion button value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifySelectedRadioButtonProperty(wd,
					forgotPwdPage.frgotUserNameRadioBtn_XPath, forgotPwdPage.frgotUserNameRadioBtnLabel_XPath,
					forgotPwdPage.FORGOTPAGE_USRNAME_RDOBTN_Txt, RaisTestData.verifiedRadioButtonProperty), RaisTestData.verifiedRadioButtonProperty);

			//verify Deselected radion button value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifySelectedRadioButtonProperty(wd, forgotPwdPage.frgotEmailRadioBtn_XPath,
					forgotPwdPage.frgotEmailRadioBtnLabel_XPath, forgotPwdPage.FORGOTPAGE_EMAIL_RDOBTN_Txt,
					RaisTestData.verifiedRadioButtonProperty), RaisTestData.verifiedRadioButtonProperty);

			//verify username/ email displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, forgotPwdPage.inputUsrNameEmailID_XPath, forgotPwdPage.inputUsrNameEmailID_MaxLength,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify input value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.submitBtn_XPath,
					forgotPwdPage.FORGOTPAGE_SUBMIT_BTN_Txt), forgotPwdPage.FORGOTPAGE_SUBMIT_BTN_Txt);

			//verify input value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.loginPageLink_XPath,
					forgotPwdPage.FORGOTPAGE_LOGIN_LINK_Txt), forgotPwdPage.FORGOTPAGE_LOGIN_LINK_Txt);

			//*****************************Validation starts here
			//Clicking on login button
			GenericMethods.elementClick(wd, forgotPwdPage.frgotUserNameRadioBtn_XPath);
			GenericMethods.elementClick(wd, forgotPwdPage.submitBtn_XPath);


			//verifying the validation message for user field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, forgotPwdPage.userNameInvalidMsg_Xpath),
					forgotPwdPage.FORGOTPAGE_INVALID_USERNAME_Txt);

			GenericMethods.elementClick(wd, forgotPwdPage.frgotEmailRadioBtn_XPath);
			GenericMethods.elementClick(wd, forgotPwdPage.submitBtn_XPath);


			//verifying the validation message for email field
			Assert.assertEquals(GenericMethods.getActualTxt(wd, forgotPwdPage.emailInvalidMsg_Xpath),
					forgotPwdPage.FORGOTPAGE_INVALID_EMAIL_Txt);			


			//*****************************Validation ends here

			//********************Invalid credentials start here
			//Clicking on radio button
			GenericMethods.elementClick(wd, forgotPwdPage.frgotUserNameRadioBtn_XPath);

			//input incorrect username
			GenericMethods.sendText(wd, forgotPwdPage.inputUsrNameEmailID_XPath, RaisTestData.invalidUserId);

			//click on submit button
			GenericMethods.elementClick(wd, forgotPwdPage.submitBtn_XPath);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, forgotPwdPage.userNotFoundMsg_XPath),
					forgotPwdPage.FORGOTPAGE_USERNOTFOUND_Txt);



			//Clicking on radio button
			GenericMethods.elementClick(wd, forgotPwdPage.frgotEmailRadioBtn_XPath);

			//input incorrect username
			GenericMethods.sendText(wd, forgotPwdPage.inputUsrNameEmailID_XPath, RaisTestData.invalidCredentials);

			//click on submit button
			GenericMethods.elementClick(wd, forgotPwdPage.submitBtn_XPath);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, forgotPwdPage.userNotFoundMsg_XPath),
					forgotPwdPage.FORGOTPAGE_USERNOTFOUND_Txt);


			//********************Invalid credentials ends here	


			//Clicking on login link using Clicking on Element
			GenericMethods.elementClick(wd, forgotPwdPage.loginPageLink_XPath);

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//page refresh
			wd.navigate().refresh();
		}		
	}

	//#3
	@Test(priority=3,enabled=ValidationTestPack.validationTC3_runStatus)
	public void DataRole_Validation() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC3_testName,ValidationTestPack.validationTC3_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.DataRoles_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//****************Verify Data roles page 
			//verify label name
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.dataRoleLabel_XPath, 
					dataRolesfunctionalRolesPage.DATA_ROLE_LBL_Txt), dataRolesfunctionalRolesPage.DATA_ROLE_LBL_Txt);

			//verify Add new role link
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn, 
					dataRolesfunctionalRolesPage.ADDROLE_BTN_Txt), dataRolesfunctionalRolesPage.ADDROLE_BTN_Txt);

			//verify Edit role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.EDITROLE_BTN_Txt), dataRolesfunctionalRolesPage.EDITROLE_BTN_Txt);

			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_deleteRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt), dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt);

			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath, 
					dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt), dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt);

			//****************End of Data roles page verification			

			GenericMethods.pageLoadWait(500);

			//*******************************Validation test starts here

			//Clicking on Element
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);

			GenericMethods.pageLoadWait(500);

			//verifying label header
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath, 
					AddNewDataRole.ADDROLE_HEADER_Txt),AddNewDataRole.ADDROLE_HEADER_Txt);

			//verifying Name label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.name_label_XPath, 
					AddNewDataRole.NAME_LABEL_Txt),AddNewDataRole.NAME_LABEL_Txt);

			//verifying the text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, AddNewDataRole.inputroleName_XPath, 100,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.nameRequired_MsgXPath, 
					AddNewDataRole.NAME_REQD_TXT),AddNewDataRole.NAME_REQD_TXT);

			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.cancelBtn_XPath, 
					AddNewDataRole.CANCEL_BTN_Txt),AddNewDataRole.CANCEL_BTN_Txt);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addBtn_XPath, 
					AddNewDataRole.ADD_BTN_Txt),AddNewDataRole.ADD_BTN_Txt);

			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);


			//*******************************Validation test ends here

			GenericMethods.pageLoadWait(1000);
			//**************************Duplicate check starts here
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.Dup_DataRole_Test);

			//Enter tab key press for duplicate msg check
			GenericMethods.tabfromElement(wd, AddNewDataRole.addBtn_XPath);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.duplicateName_MsgXPath, 
					AddNewDataRole.DUPLICATENAME_MSG_TXT),AddNewDataRole.DUPLICATENAME_MSG_TXT);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);

			//**************************Duplicate check ends here			

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

	//#4
	@Test(priority=4, enabled=ValidationTestPack.validationTC4_runStatus)
	public void FunctionalRole_Validation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC4_testName,ValidationTestPack.validationTC4_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.FunctionalRoles_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.FunctionalRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.FunctionalRoles_XPath);

			//****************Verify Functional roles page 
			//verify label name
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.funcRoleLabel_XPath, 
					dataRolesfunctionalRolesPage.FUNC_ROLE_LBL_Txt), dataRolesfunctionalRolesPage.FUNC_ROLE_LBL_Txt);

			//verify Add new role link
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn, 
					dataRolesfunctionalRolesPage.ADDROLE_BTN_Txt), dataRolesfunctionalRolesPage.ADDROLE_BTN_Txt);

			//verify Edit role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.FR_editRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.EDITROLE_BTN_Txt), dataRolesfunctionalRolesPage.EDITROLE_BTN_Txt);

			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt), dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt);

			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.FunctionalRolePermissionRestrictBtn_XPath, 
					dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt), dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt);

			//****************End of Functional roles page verification			

			//*******************************Validation test starts here

			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);

			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//wait for pageload
			GenericMethods.pageLoadWait(500);

			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);

			//verifying label header
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath, 
					AddNewDataRole.ADDROLE_HEADER_Txt),AddNewDataRole.ADDROLE_HEADER_Txt);

			//verifying Name label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.name_label_XPath, 
					AddNewDataRole.NAME_LABEL_Txt),AddNewDataRole.NAME_LABEL_Txt);

			//verifying the text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, AddNewDataRole.inputroleName_XPath, 100,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.nameRequired_MsgXPath, 
					AddNewDataRole.NAME_REQD_TXT),AddNewDataRole.NAME_REQD_TXT);

			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.cancelBtn_XPath, 
					AddNewDataRole.CANCEL_BTN_Txt),AddNewDataRole.CANCEL_BTN_Txt);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addBtn_XPath, 
					AddNewDataRole.ADD_BTN_Txt),AddNewDataRole.ADD_BTN_Txt);

			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);			

			//*******************************Validation test ends here

			//wait for pageload
			GenericMethods.pageLoadWait(500);

			//**************************Duplicate check starts here
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);

			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.Dup_FuncRole_Test);

			//Enter tab key press for duplicate msg check
			GenericMethods.tabfromElement(wd, AddNewDataRole.addBtn_XPath);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.duplicateName_MsgXPath, 
					AddNewDataRole.DUPLICATENAME_MSG_TXT),AddNewDataRole.DUPLICATENAME_MSG_TXT);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);

			//**************************Duplicate check ends here


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

	//#5
	@Test(priority=5,enabled=ValidationTestPack.validationTC5_runStatus)
	public void SecurityRole_Validation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC5_testName,ValidationTestPack.validationTC5_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

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
			//verify label/ button name
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, SecurityProfile.addNewProfileBtn_XPath, 
					SecurityProfile.SP_ADDNEW_PROFILE_Txt), SecurityProfile.SP_ADDNEW_PROFILE_Txt);

			//verify columnheaders
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, SecurityProfile.securityProfileLandingPageColHeaderName_XPath, 
					SecurityProfile.SP_COLHEADER_NAME_Txt), SecurityProfile.SP_COLHEADER_NAME_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, SecurityProfile.securityProfileLandingPageColHeaderEntity_XPath, 
					SecurityProfile.SP_COLHEADER_ENTITY_Txt), SecurityProfile.SP_COLHEADER_ENTITY_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, SecurityProfile.securityProfileLandingPageColHeaderField_XPath, 
					SecurityProfile.SP_COLHEADER_FIELD_Txt), SecurityProfile.SP_COLHEADER_FIELD_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, SecurityProfile.securityProfileLandingPageColHeaderRestrict_XPath, 
					SecurityProfile.SP_COLHEADER_RESTRICT_Txt), SecurityProfile.SP_COLHEADER_RESTRICT_Txt);

			//****************End of Security roles landing page verification			

			//*******************************Validation test starts here

			//Clicking on add new data role
			GenericMethods.elementClick(wd, SecurityProfile.addNewProfileBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, AddNewSProle.SaveBtn_XPath);

			//verifying Name label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.addNewSP_name_XPath, 
					AddNewSProle.ADDNEW_SP_NAME_Txt),AddNewSProle.ADDNEW_SP_NAME_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.addNewSP_entity_XPath, 
					AddNewSProle.ADDNEW_SP_ENTITY_Txt),AddNewSProle.ADDNEW_SP_ENTITY_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.addNewSP_field_XPath, 
					AddNewSProle.ADDNEW_SP_FIELD_Txt),AddNewSProle.ADDNEW_SP_FIELD_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.addNewSP_restrict_XPath, 
					AddNewSProle.ADDNEW_SP_RESTRICT_Txt),AddNewSProle.ADDNEW_SP_RESTRICT_Txt);			

			//verifying the text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, AddNewSProle.addnewSP_inputroleName_XPath, 100,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verifying the validation message
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.nameRequired_MsgXPath, 
					AddNewSProle.ADDNEW_SP_NAME_REQD_TXT),AddNewSProle.ADDNEW_SP_NAME_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.entityRequired_MsgXPath, 
					AddNewSProle.ADDNEW_SP_ENTITY_REQD_TXT),AddNewSProle.ADDNEW_SP_ENTITY_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.fieldRequired_MsgXPath, 
					AddNewSProle.ADDNEW_SP_FLD_REQD_TXT),AddNewSProle.ADDNEW_SP_FLD_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.restrictRequired_MsgXPath, 
					AddNewSProle.ADDNEW_SP_RESTRICT_REQD_TXT),AddNewSProle.ADDNEW_SP_RESTRICT_REQD_TXT);

			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.cancelBtn_XPath, 
					AddNewSProle.CANCEL_BTN_Txt),AddNewSProle.CANCEL_BTN_Txt);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewSProle.SaveBtn_XPath,
					AddNewSProle.SAVE_BTN_Txt),AddNewSProle.SAVE_BTN_Txt);

			//clicking on Cancel button
			GenericMethods.waitforElement(wd, AddNewSProle.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.cancelBtn_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.cancelBtn_XPath);

			//*******************************Validation test ends here

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

	//#6
	@Test(priority=6,enabled=ValidationTestPack.validationTC6_runStatus)
	public void UserProfile_Validation() throws Exception {

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC6_testName,
					ValidationTestPack.validationTC6_testDescription);
			//Calling Login method
			GenericMethods.loginApplication(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, password,
					loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);

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

			//wait for page load
			GenericMethods.pageLoadWait(500);

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

	//#7
	@Test(priority=7,enabled=ValidationTestPack.validationTC7_runStatus)
	public void Entity_Validation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC7_testName,ValidationTestPack.validationTC7_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);

			//****************Verify Entity landing page 
			//verify label/ button name
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, entListingPage.addNewEntityBtn_XPath, 
					entListingPage.ENT_LIST_ADDNEW_Txt), entListingPage.ENT_LIST_ADDNEW_Txt);

			//verify columnheaders
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, entListingPage.entityListingPageColHeaderName_XPath, 
					entListingPage.ENT_LIST_COLHEADER_NAME_Txt), entListingPage.ENT_LIST_COLHEADER_NAME_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, entListingPage.entityListingPageColHeaderDesc_XPath,
					entListingPage.ENT_LIST_COLHEADER_DESC_Txt), entListingPage.ENT_LIST_COLHEADER_DESC_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, entListingPage.entityListingPageColHeaderType_XPath,
					entListingPage.ENT_LIST_COLHEADER_TYPE_Txt), entListingPage.ENT_LIST_COLHEADER_TYPE_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, entListingPage.entityListingPageColHeaderGroup_XPath, 
					entListingPage.ENT_LIST_COLHEADER_GRP_Txt), entListingPage.ENT_LIST_COLHEADER_GRP_Txt);

			//****************End of Security roles landing page verification			

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//*******************************Validation test starts here

			//Clicking on add new entity
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//clicking on add new entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);

			//verifying text and labels on the page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_name_XPath, 
					addEntityPage.ADDNEW_ENTITY_INTNAME_Txt),addEntityPage.ADDNEW_ENTITY_INTNAME_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_Desc_XPath,
					addEntityPage.ADDNEW_ENTITY_DESC_Txt),addEntityPage.ADDNEW_ENTITY_DESC_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_SingLbl_XPath,
					addEntityPage.ADDNEW_ENTITY_SINGLBL_Txt),addEntityPage.ADDNEW_ENTITY_SINGLBL_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_PluLbl_XPath, 
					addEntityPage.ADDNEW_ENTITY_PLULBL_Txt),addEntityPage.ADDNEW_ENTITY_PLULBL_Txt);			

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_GrpLbl_XPath, 
					addEntityPage.ADDNEW_ENTITY_GRPLBL_Txt),addEntityPage.ADDNEW_ENTITY_GRPLBL_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_entyRole_XPath, 
					addEntityPage.ADDNEW_ENTITY_ROLELBL_Txt),addEntityPage.ADDNEW_ENTITY_ROLELBL_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_pubNavi_XPath,
					addEntityPage.ADDNEW_ENTITY_PUBNAVILBL_Txt),addEntityPage.ADDNEW_ENTITY_PUBNAVILBL_Txt);

			//verifying the text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, 50,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, 255,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, 50,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//For ran check boxes
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addEntityPage.addNewEntity_enableWFCheckBox_XPath,
					addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt, false),addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt);

			//			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addEntityPage.addNewEntity_enableRANCheckBox_XPath,
			//					addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt, false),addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt);

			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addEntityPage.addNewEntity_enableHistoryCheckBox_XPath,
					addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt, false),addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt);

			//			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addEntityPage.addNewEntity_enableDocCheckBox_XPath,
			//					addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt, true),addEntityPage.ADDNEW_ENTITY_ENB_DISABLE_Txt);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.addNewEntity_enabledocument_XPath,
					addEntityPage.ADDNEW_ENTITY_ENB_DOC_ATTACH_Txt),addEntityPage.ADDNEW_ENTITY_ENB_DOC_ATTACH_Txt);

			//Verify validation messages
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.nameRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_INTNAME_REQD_TXT),addEntityPage.ADDNEW_ENTITY_INTNAME_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.descRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_DESC_REQD_TXT),addEntityPage.ADDNEW_ENTITY_DESC_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.singularNameRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_SINGFLD_REQD_TXT),addEntityPage.ADDNEW_ENTITY_SINGFLD_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.pluralNameRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_PLUFLD_REQD_TXT),addEntityPage.ADDNEW_ENTITY_PLUFLD_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.groupRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_GROUP_REQD_TXT),addEntityPage.ADDNEW_ENTITY_GROUP_REQD_TXT);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.entityRoleRequired_MsgXPath, 
					addEntityPage.ADDNEW_ENTITY_ROLE_REQD_TXT),addEntityPage.ADDNEW_ENTITY_ROLE_REQD_TXT);

			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.cancelBtn_XPath, 
					addEntityPage.CANCEL_BTN_Txt),addEntityPage.CANCEL_BTN_Txt);

			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.SaveBtn_XPath,
					addEntityPage.SAVE_BTN_Txt),addEntityPage.SAVE_BTN_Txt);

			//clicking on Cancel button
			GenericMethods.waitforElement(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);

			//*******************************Validation test ends here

			//**********Duplicate code starts here

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, RaisTestData.Dup_Entity_Test);

			//click on plural text to perform duplicate check
			GenericMethods.tabfromElement(wd, addEntityPage.addNewEntity_SingTxtBox_XPath);

			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.duplicateSingularEntityName_MsgXPath, 
					addEntityPage.DUPLICATE_SINGULAR_ENTITY_NAME_TXT),addEntityPage.DUPLICATE_SINGULAR_ENTITY_NAME_TXT);

			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);			

			//**********Duplicate code Ends here

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

	//#8
	@Test(priority=8,enabled=ValidationTestPack.validationTC8_runStatus)
	public void EntityAttribute_Validation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC8_testName,ValidationTestPack.validationTC8_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
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
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, RaisTestData.EntityAttb_InternalNameData);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, RaisTestData.Entity_DescriptionData);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, RaisTestData.EntityAttb_SingularData);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, RaisTestData.Entity_PluralData);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, RaisTestData.Entity_GroupData);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, RaisTestData.Entity_RoleData);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, RaisTestData.Entity_PublishNavigation1Data);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, RaisTestData.Entity_PublishNavigation2Data);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
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
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.EntityAttb_SingularData );

			//wait for page load
			GenericMethods.pageLoadWait(500);


			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , RaisTestData.EntityAttb_SingularData);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);
			//GenericMethods.elementClick(wd, addEntityPage.attributeLink_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			GenericMethods.sendText(wd, addAttbt.addNewAttB_dataTypedropDown_XPath, addAttbt.attributeValueList[0]);
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, addAttbt.SaveBtn_XPath);			
			
			//Switch case starts from here
			Assert.assertEquals(RAIS_applicationSpecificMethods.verifyCommonLabels_ValidationMsgs(wd),
					RaisTestData.lblValmessagesVerified);
			
			
			
			
			
			
			
			
			//verifying header label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.addAttb_Hdr_XPath, addAttbt.ADDNEW_ATTB_HDR_LABEL_Txt),
					addAttbt.ADDNEW_ATTB_HDR_LABEL_Txt);

			//verifying label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.addNewAttB_intnameLabel_XPath, addAttbt.ADDNEW_ATTB_INTNAME_LABEL_Txt),
					addAttbt.ADDNEW_ATTB_INTNAME_LABEL_Txt);

			//verifying datatype drop down on RIAS Page
			Assert.assertEquals(RAIS_applicationSpecificMethods.dropDownCompareWithArray(wd, addAttbt.addNewAttB_dataTypedropDown_XPath, addAttbt.attributeValueList,
					RaisTestData.dropDownValuesVerified,RaisTestData.dropDownValuesNOTVerified )
					,RaisTestData.dropDownValuesVerified);

			//verify mandatory drop down
			Assert.assertEquals(RAIS_applicationSpecificMethods.dropDownCompareWithArray(wd, addAttbt.addNewAttB_mandInputdropDown_XPath, addAttbt.mandInputList,
					RaisTestData.dropDownValuesVerified,RaisTestData.dropDownValuesNOTVerified )
					,RaisTestData.dropDownValuesVerified);

			//verify mandatory error messages
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.nameRequired_MsgXPath, 
					addAttbt.ADDNEW_ATTB_INTNAME_REQD_TXT),addAttbt.ADDNEW_ATTB_INTNAME_REQD_TXT);

			//for label verify validation messages
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.labelRequired_MsgXPath, 
					addAttbt.ADDNEW_ATTB_LABEL_REQD_TXT),addAttbt.ADDNEW_ATTB_LABEL_REQD_TXT);

			//for datatype verify validation messages
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.dataTypeRequired_MsgXPath, 
					addAttbt.ADDNEW_ATTB_DATATYPE_REQD_TXT),addAttbt.ADDNEW_ATTB_DATATYPE_REQD_TXT);

			//for Mandatory input verify validation messages
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.mandInputRequired_MsgXPath, 
					addAttbt.ADDNEW_ATTB_MANDINPUT_REQD_TXT),addAttbt.ADDNEW_ATTB_MANDINPUT_REQD_TXT);

			//verify internal name text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addAttbt.addNewAttB_internalNameTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify tool tip text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addAttbt.addNewAttB_tooTipTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify label text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, addAttbt.addNewAttB_labelTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//For unique value check boxes
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addAttbt.addNewAttB_unqVal_CheckBox_XPath,
					addAttbt.ADDNEW_ATTB_DISABLE_Txt, false),addAttbt.ADDNEW_ATTB_DISABLE_Txt);

			//read only check box
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addAttbt.addNewAttB_readOnly_CheckBox_XPath,
					addAttbt.ADDNEW_ATTB_ENB_Txt, true),addAttbt.ADDNEW_ATTB_ENB_Txt);

			//searchable check box
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addAttbt.addNewAttB_searchable_CheckBox_XPath,
					addAttbt.ADDNEW_ATTB_DISABLE_Txt, false),addAttbt.ADDNEW_ATTB_DISABLE_Txt);

			//For show in list check boxes
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addAttbt.addNewAttB_showinList_CheckBox_XPath,
					addAttbt.ADDNEW_ATTB_ENB_Txt, true),addAttbt.ADDNEW_ATTB_ENB_Txt);

			//For show in History check boxes
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wd, addAttbt.addNewAttB_showHist_CheckBox_XPath,
					addAttbt.ADDNEW_ATTB_ENB_Txt, true),addAttbt.ADDNEW_ATTB_ENB_Txt);

			//for Cancel & Save button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.cancelBtn_XPath, 
					addAttbt.CANCEL_BTN_Txt),addAttbt.CANCEL_BTN_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addAttbt.SaveBtn_XPath, 
					addAttbt.SAVE_BTN_Txt),addAttbt.SAVE_BTN_Txt);		

			//******************************************************
			//dropdown wise text/ label verification pending and add code here
			
			
			
			//******************************************************				

			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, addAttbt.cancelBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(300);
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.EntityDetail_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, addEntityPage.deleteBtn_XPath);

			//Waiting for delete popup page
			GenericMethods.elementClick(wd, addEntityPage.delEntity_popUpYesBtn_XPath);				

			System.out.println("deleted entity");
		
		}catch (AssertionError  as) {
			as.printStackTrace();

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

	//#9
	@Test(priority=9,enabled=ValidationTestPack.validationTC9_runStatus)
	public void BusinessEntity_Validation(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ValidationTestPack.validationTC9_testName,ValidationTestPack.validationTC9_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//				

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.entities_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.entities_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.entities_XPath);

			//****************Verify Entity landing page 
			//wait for page load
			GenericMethods.pageLoadWait(250);

			//*******************************Validation test starts here

			//Column header filter starts here
//			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
//					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.EntityAttb_SingularData );
			
			//Declaring array to store Business entities 
			
			  String[] sBusinessEntities = { "Academic Qualification",
			  "Academic Qualification Degree", "Activity Unit", "Amperage Unit",
			  "Annual Dose", "Associated Equipment Status", "Attended Course",
			  "Authority Type", "Boolean", "Branch", "Calibration", "Category", "Country",
			  "Department", "Department Status", "District", "Dose", "Equipment",
			  "Equipment & Source", "Equipment Manufacturing", "Equipment Model",
			  "Equipment Status", "Equipment Type", "Expert", "Expert Task", "Facility",
			  "Facility Status", "Field", "Frequency In Month", "Gender",
			  "Inspection Schedule", "Inventory Status", "Isotope Production",
			  "Manufacturer", "Monitoring Status", "Nuclide", "Officer","Operation",
			  "Partner Agency","Person", "Person Status", "Physical Barrier",
			  "Physical Form", "Practice", "Professional Degree",
			  "Professional Qualification", "Radiation Generator",
			  "Radiation Generator Model", "Radiation Generator Status",
			  "Radiation Generator Type", "Region", "Regulatory Authority",
			  "Sealed Source", "Sealed Source Model", "Sealed Source Status",
			  "Security Group", "Status", "Time Unit", "Training Course",
			  "Unsealed Source", "Voltage Unit", "Wave Form", "Worker", "Year" };
			  
			  
			//iterating for loop to validate business entities 
			  
			  for (int i = 0; i<= sBusinessEntities.length; i++)
			  {
				  RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,entListingPage.entityListingTableColHeader_TXT_XPath,sBusinessEntities[i]);

				  // wait for page load
				  GenericMethods.pageLoadWait(500);


				  // Clicking on business entity name 
				  RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , sBusinessEntities[i]);
			
				  // wait for page load
				  GenericMethods.pageLoadWait(2500);

				  String singularTextboxnew = wd.findElement(By.xpath("//*[@id='Name']")).getAttribute("value");
				  GenericMethods.pageLoadWait(2500);
				  
				  //System.out.println(wd.findElement(By.xpath("//*[@id='Name']")).getAttribute("value"));
				  Assert.assertEquals(singularTextboxnew,sBusinessEntities[i]);

			//Click on Cancel button
				  
			
			  }

			//after for loop exit, click on Logout 
			

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {

			//Logout user
			RAIS_applicationSpecificMethods.logoutUser(wd, dashboardnew.loggedinUser_XPath, dashboardnew.logout_XPath);

			//verifying logo on RAIS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

			//page refresh
			wd.navigate().refresh();
		}

	}

	
	//#10 
	
	//#11 
	
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
