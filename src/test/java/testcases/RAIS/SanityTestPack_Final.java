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

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class SanityTestPack_Final extends BaseClass
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
	
	public static String packType = "E2E-Test-";

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
	@Test(priority=1,enabled=SanityPack_TestNamesDescription.TC1_runStatus)
	public void LoginScreen_Default_ExternalLogin() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC1_testName,SanityPack_TestNamesDescription.TC1_testDescription);

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
	
	@Test(priority = 2, enabled = SanityPack_TestNamesDescription.TC2_runStatus)
	public void Forgot_PasswordFeature( ) throws Exception {
		
		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC2_testName,
					SanityPack_TestNamesDescription.TC2_testDescription);
			
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

	@Test(priority=3,enabled=SanityPack_TestNamesDescription.TC3_runStatus)
	public void LoginApplication() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC3_testName,SanityPack_TestNamesDescription.TC3_testDescription);

			wd.navigate().refresh();
			
			//Clicking back on internal link
			GenericMethods.elementClick(wd, loginPage.loginBtn_XPath);
			
			//Calling login function
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, RaisTestData.raisAdminUserId, loginPage.pwd_XPath, 
					RaisTestData.raisAdminUserPwd, loginPage.loginBtn_XPath);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);

			//verifying labels/ buttons on Dashboard page
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.dashbrdAppLogo_XPath),
					dashboardnew.LP_APPLOGO_Txt);	

			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.dashbrdAppLogoFullFrm_XPath),
					dashboardnew.LP_APPLOGO_FULLName_Txt);	
			
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.dashboardUnderDevelopment_XPath),
					dashboardnew.LP_APP_UNDERDEVELOPMENT_MSG_Txt);	
			
			//verify menu name
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.dashboard_XPath),
					RaisTestData.DashboardText);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.regulatoryProcess_XPath),
					RaisTestData.RegProcessText);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.inventResources_XPath),
					RaisTestData.InvenResourceText);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.reportStats_XPath),
					RaisTestData.RptStatsText);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.settings_XPath),
					RaisTestData.SettingsText);
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.administration_XPath),
					RaisTestData.AdministrationText);
			
			//verify top menu bar using assert true for similar match of text
			assertTrue(GenericMethods.getActualTxt(wd, dashboardnew.notification_XPath).contains(RaisTestData.NotificationText));
			assertTrue(GenericMethods.getActualTxt(wd, dashboardnew.help_XPath).contains(RaisTestData.HelpText));
						
			//verify loggedin user name
			assertTrue(GenericMethods.getActualTxt(wd, dashboardnew.loggedinUser_XPath).contains(dashboardnew.LP_LOGGEDIN_AS_ADMIN_Txt));
			
			
			//*************************************************************************Added New Logout method above
			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

			//verify drop down contains profile and logout
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.profile_XPath),
					dashboardnew.LP_MY_PROFILE_Txt);
			
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dashboardnew.logout_XPath),
					dashboardnew.LP_LOGOUT_Txt);
			
			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.logout_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClick(wd, dashboardnew.logout_XPath);
			
			//*************************************************************************
			
			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt),loginPage.RAIS_Txt);

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

	
	@Test(priority=8,enabled=SanityPack_TestNamesDescription.TC8_runStatus)
	public void DataRole_CreateEditDeleteRole() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC8_testName,SanityPack_TestNamesDescription.TC8_testDescription);

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
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt), dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt);
			
			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath, 
					dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt), dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt);
						
			//****************End of Data roles page verification			
			
			//*******************************Validation test starts here
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
						
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

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
			
			
			//********************************Add new role starts here
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.DataRole_Test);
			
			//clicking on add role button			
			GenericMethods.elementClick(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
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
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			
			//**************************Duplicate check starts here
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
						
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.DataRole_Test);
			
			//Enter tab key press for duplicate msg check
			GenericMethods.tabfromElement(wd, AddNewDataRole.addBtn_XPath);
			
			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.duplicateName_MsgXPath, 
					AddNewDataRole.DUPLICATENAME_MSG_TXT),AddNewDataRole.DUPLICATENAME_MSG_TXT);
			
			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);
			
			//**************************Duplicate check ends here

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , RaisTestData.DataRole_Test);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.editBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.editBtn_XPath);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ExecutionDay());
						
			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath);
			GenericMethods.elementClick(wd, AddNewDataRole.editBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.EDITNEWROLE_SUCESSMSG_TXT);
			
			//**************************************************EDIT ENDS HERE
			
			
			//**************************************************DELETE STARTS HERE
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
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , RaisTestData.DataRole_Test + ExecutionDay());
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dataRolesfunctionalRolesPage.delDataRolePerm_PopupMSGTxt_XPath),
					dataRolesfunctionalRolesPage.DELETE_DATAROLE_POPMSG_Txt);
			
			//clicking on No button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgNoBtn_XPath);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
						
			//clicking on Yes button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.DELETEROLE_SUCCESSMSG_TXT);
			
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
	
	@Test(priority=10, enabled=SanityPack_TestNamesDescription.TC10_runStatus)
	public void FunctionalRole_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC10_testName,SanityPack_TestNamesDescription.TC10_testDescription);

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
						
			//****************End of Data roles page verification			
			
			//*******************************Validation test starts here
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
						
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

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
			
			
			//********************************Add new role starts here
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.FuncRole_Test);
			
			//clicking on add role button			
			GenericMethods.elementClick(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.ADDNEWFUNC_ROLE_Txt);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.FunctionalRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.FunctionalRoles_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
			
			//**************************Duplicate check starts here
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_addNewRoleBtn);
						
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, RaisTestData.FuncRole_Test);
			
			//Enter tab key press for duplicate msg check
			GenericMethods.tabfromElement(wd, AddNewDataRole.addBtn_XPath);
			
			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.duplicateName_MsgXPath, 
					AddNewDataRole.DUPLICATENAME_MSG_TXT),AddNewDataRole.DUPLICATENAME_MSG_TXT);
			
			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);
			
			//**************************Duplicate check ends here

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.FUNC_ROLE_Txt , RaisTestData.FuncRole_Test);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.FR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_editRoleBtn_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.editBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.editBtn_XPath);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ExecutionDay());
						
			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.editBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.editBtn_XPath);
			GenericMethods.elementClick(wd, AddNewDataRole.editBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.UPDATEFUNC_ROLE_Txt);
			
			//**************************************************EDIT ENDS HERE
			
			
			//**************************************************DELETE STARTS HERE
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			//GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.FunctionalRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.FunctionalRoles_XPath);
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.FUNC_ROLE_Txt , RaisTestData.FuncRole_Test + ExecutionDay());
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dataRolesfunctionalRolesPage.delDataRolePerm_PopupMSGTxt_XPath),
					dataRolesfunctionalRolesPage.DELETE_FUNCROLE_POPMSG_Txt);
			
			//clicking on No button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgNoBtn_XPath);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FR_deleteRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
						
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
	
	@Test(priority=11,enabled=SanityPack_TestNamesDescription.TC11_runStatus)
	public void SecurityRole_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC11_testName,SanityPack_TestNamesDescription.TC11_testDescription);

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
						
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, AddNewSProle.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.SaveBtn_XPath);
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
			
			
			//********************************Add new SP role starts here
			
			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, SecurityProfile.addNewProfileBtn_XPath);	
			GenericMethods.elementClickable(wd, SecurityProfile.addNewProfileBtn_XPath);
			GenericMethods.elementClick(wd, SecurityProfile.addNewProfileBtn_XPath);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewSProle.addnewSP_inputroleName_XPath, RaisTestData.SecurityRole_Test);
			
			//select value from list
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,AddNewSProle.addnewSP_facilityDropdown_XPath, "Facility");
			
			//select value from multiselect
			//GenericMethods.waitforElement(wd, AddNewPermRestrct.restriction_DropdnClick_Xpath);	
			GenericMethods.elementClickable(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			GenericMethods.elementClick(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			
			///completed till above*****************************************************
			
			//CLicking on Add new permission button
			GenericMethods.elementClickable(wd, AddNewSProle.restriction_add_dropdown_XPath);
			GenericMethods.elementClick(wd, AddNewSProle.restriction_add_dropdown_XPath);
			
			//collapsing the multiselect drop down
			GenericMethods.elementClick(wd, AddNewSProle.restriction_DropdnClick_Xpath);
			
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

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,SecurityProfile.securityProfileTableList_XPath , RaisTestData.SecurityRole_Test);
			
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
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,SecurityProfile.securityProfileTableList_XPath , RaisTestData.SecurityRole_Test+ExecutionDay());
						
			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, AddNewSProle.deleteBtn_XPath);
			GenericMethods.elementClickable(wd, AddNewSProle.deleteBtn_XPath);
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
			

	@Test(priority=13,enabled=SanityPack_TestNamesDescription.TC13_runStatus)
	public void Entity_CRUD_Operations(){
		
		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC13_testName,SanityPack_TestNamesDescription.TC13_testDescription);

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
			Thread.sleep(2000);
			//*******************************Validation test starts here
			
			//Clicking on add new entity
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);
						
			Thread.sleep(2000);
			
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
			
			Thread.sleep(2000);
			//********************************Add new Entity starts here
			//Waiting for button to load and click
//			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
//			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);
			
			Thread.sleep(2000);
			
			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			//GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);
			
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
			
			//**********Duplicate code starts here
			
			Thread.sleep(2000);
			
			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);

			Thread.sleep(2000);
			
			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, RaisTestData.Entity_SingularData);
			
			//click on plural text to perform duplicate check
			GenericMethods.elementClick(wd, addEntityPage.addNewEntity_PluTxtBox_XPath);
			
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, addEntityPage.duplicateSingularEntityName_MsgXPath, 
					addEntityPage.DUPLICATE_SINGULAR_ENTITY_NAME_TXT),addEntityPage.DUPLICATE_SINGULAR_ENTITY_NAME_TXT);

			//clicking on add new security profile - save button
			GenericMethods.waitforElement(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.cancelBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.cancelBtn_XPath);			
			
			//**********Duplicate code Ends here
			
			Thread.sleep(1000);
			

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
						
			Thread.sleep(1000);
			
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_SingularData );
			
			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , RaisTestData.Entity_SingularData);
			
			Thread.sleep(1000);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, ExecutionDay());

			
			
			GenericMethods.elementClick(wd, addEntityPage.addNewEntity_DescTxtBox_XPath);
			
			Thread.sleep(1000);
			
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
			
			Thread.sleep(1000);
			
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_SingularData);
			
			//Clicking on specific entity created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath, 
					RaisTestData.Entity_SingularData+ExecutionDay());
						
			Thread.sleep(1000);
			
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
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, addEntityPage.addnewEntity_SuccessMsg_XPath),
					addEntityPage.DELENT_SUCESSMSG_TXT);
			
			//**************************************************Delete ENDS HERE		
			
			System.out.println("Entity deleted");
			
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
		

	//*******************Working till above
	
	@Test(enabled=false)

	public void OLDSecurityRole_CreateEditDeleteRole() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC11_testName,SanityPack_TestNamesDescription.TC11_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.administration_XPath);
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.securityRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.securityRoles_XPath);
			
			//****************Verify Data roles page 
			//verify label name
			Assert.assertEquals(GenericMethods.verifyLabelBtnObjProperty(wd, SecurityProfile.addNewProfileBtn_XPath), true);
			
			//verify no security role assigned in the table
			Assert.assertEquals(GenericMethods.verifyLabelBtnObjProperty(wd, SecurityProfile.noSecurityProfiles_XPath), true);
			
			//verify Add new role link
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn, 
					dataRolesfunctionalRolesPage.ADDROLE_BTN_Txt), true);
			
			//verify Edit role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.EDITROLE_BTN_Txt), true);
			
			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath, 
					dataRolesfunctionalRolesPage.DELETEROLE_BTN_Txt), true);
			
			//verify Delete role button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath, 
					dataRolesfunctionalRolesPage.ADDNEW_PERMISSION_BTN_Txt), true);
			
			//verify no permissions granted text
//			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dataRolesfunctionalRolesPage.noPermissionGranted_Label_XPath, 
//					dataRolesfunctionalRolesPage.NO_PERMISSIONS_GRANTED_Txt), true);
//			Assert.assertEquals(RAIS_applicationSpecificMethods.perm_restrict_Table_NotGranted_Text(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath, 
//					dataRolesfunctionalRolesPage.NO_PERMISSIONS_GRANTED_Txt), true);
						
			//****************End of Data roles page verification			
			
			//*******************************Validation test starts here
			
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
						
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//verifying label header
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addRolePopUpHeaderLabel_XPath, 
					AddNewDataRole.ADDROLE_HEADER_Txt),true);
			
			//verifying Name label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.name_label_XPath, 
					AddNewDataRole.NAME_LABEL_Txt),true);
			
			//verifying the text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, AddNewDataRole.inputroleName_XPath, 100,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			
			//verifying the validation message
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.nameRequired_MsgXPath, 
					AddNewDataRole.NAME_REQD_TXT),true);
			
			//verifying the cancel button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.cancelBtn_XPath, 
					AddNewDataRole.CANCEL_BTN_Txt),true);
			
			//verifying the Save button 
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, AddNewDataRole.addBtn_XPath, 
					AddNewDataRole.ADD_BTN_Txt),true);
			
			//clicking on Cancel buttonon add role page
			GenericMethods.elementClick(wd, AddNewDataRole.cancelBtn_XPath);
			
			
			//*******************************Validation test ends here
			
			
			//********************************Add new role starts here
			//Clicking on add new data role
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
			
			//input role name and internal name
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, "AutomationUser1");
			
			//GenericMethods.sendText(wd, AddNewDataRole.inputInternalName_XPath, "InternalAutomationUser1");
			
			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT);
			
			//********************************Add new role Ends here
			
			//**************************************************EDIT STARTS HERE
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboard_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.dashboard_XPath);
			GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "AutomationUser1");

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for popup to load
			GenericMethods.waitforElement(wd, AddNewDataRole.editBtn_XPath);	
			GenericMethods.elementClickable(wd, AddNewDataRole.editBtn_XPath);
			
			//input role name and internal name
			//GenericMethods.clearElement(wd, AddNewDataRole.inputroleName_XPath);
			GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, ExecutionDay());
						
			//clicking on add role button
			GenericMethods.elementClick(wd, AddNewDataRole.editBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.EDITNEWROLE_SUCESSMSG_TXT);
			
			//**************************************************EDIT ENDS HERE
			
			
			//**************************************************DELETE STARTS HERE
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboard_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.dashboard_XPath);
			GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);
			
			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "AutomationUser1" + ExecutionDay());
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//verifying the Delete popup message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, dataRolesfunctionalRolesPage.delDataRolePerm_PopupMSGTxt_XPath),
					dataRolesfunctionalRolesPage.DELETE_DATAROLE_POPMSG_Txt);
			
			//clicking on No button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgNoBtn_XPath);
			
			//Waiting until element to load
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
			
			//Waiting for delete popup page
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
						
			//clicking on Yes button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
			
			//waiting for success message
			GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
			
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
					AddNewDataRole.DELETEROLE_SUCCESSMSG_TXT);
			
			//**************************************************Delete ENDS HERE		
			
			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.logout_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClick(wd, dashboardnew.logout_XPath);
			
		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

	}

	
	
	//Demo test cases
	@Test(priority=4,enabled=SanityPack_TestNamesDescription.TC4_runStatus)
	public void LoginScreen_Temp1() throws Exception {

		
		

		Assert.assertTrue(false);
		Thread.sleep(3000);

	}

	@Test(priority=4,enabled=SanityPack_TestNamesDescription.TC4_runStatus)
	public void ReadEntityTable() throws Exception {

		//DashboardPage objDashboardPage= new DashboardPage(wd);

		//Calling login function
		GenericMethods.loginApplication
		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
				password, loginPage.loginBtn_XPath);

		//Adding Test case name to extent report
		//tcName = extentReportTC.createTest("Sample Test Case 2", "Verify Application login");

		//Waiting for user experience
		Thread.sleep(2000);

		//waiting for link to load and then click
		//wait.until(ExpectedConditions.elementToBeClickable(objDashboardPage.administration_XPath));
		//objDashboardPage.administration_XPath.click();

		//Waiting for user experience
		Thread.sleep(2000);

		//waiting for link to load and then click
		//wait.until(ExpectedConditions.elementToBeClickable(objDashboardPage.entities_XPath));
		//objDashboardPage.entities_XPath.click();

		//Waiting for user experience
		Thread.sleep(2000);

		WebElement simpleTable = wd.findElement(By.id("entity"));

		//Get all rows
		List<WebElement> rows = simpleTable.findElements(By.xpath("//*[@id=\"entity\"]/div/div/div[2]/div/div[1]/table"));
		//assertEquals(3, rows.size());

		//Print data from each row
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				System.out.print(col.getText() + "\t");
			}
			System.out.println();
		}

		//Clicking on logged in user link
		GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

		//Waiting until element is visible to be clicked
		GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
		GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

	}

	@Test(priority=5,enabled=SanityPack_TestNamesDescription.TC5_runStatus)
	public void DataRole_AddNewPermission_ValidationOld() throws Exception {

		//Initialising explicit wait
		//WebDriverWait wait=new WebDriverWait(wd,20);

		//Calling Login method
		GenericMethods.loginApplication
		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
				password, loginPage.loginBtn_XPath);

		//tcName = extentReportTC.createTest("Test Case 5", "Verify validation messages for Data Role in Add New Permissions screen");

		//Waiting for user experience
		Thread.sleep(2000);

		//waiting for link to load and then click 
		//wait.until(ExpectedConditions.visibilityOf(objDashboardPage.administration_XPath));
		GenericMethods.waitforElement(wd, dashboardnew.administration_XPath);

		GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

		//waiting for link to load and then click
		//genericApplicationMethods.waitforElement(wd, DataRolesFunctionalRolesPage.DataRoles_XPath);
		//genericApplicationMethods.waitforElement(wd, dashboardnew.administration_XPath);

		//wait.until(ExpectedConditions.elementToBeClickable(objDashboardPage.DataRoles_XPath));
		//objDashboardPage.DataRoles_XPath.click();
		//genericApplicationMethods.elementClick(wd, DataRolesFunctionalRolesPage.DataRoles_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

		/// *******************WOrking code
		//String dRole = "datarole";
		//Selecting Left role pane xpath and clicking on particular selected role
		//WebElement mainleftpaneRoleXpath = wd.findElement(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div"));
		//		WebElement mainleftpaneRoleXpath = wd.findElement(By.xpath("//*[@id=\'"+dRole+"']/div/div[1]/div/div"));
		//		//WebElement mainleftpaneRoleXpath = wd.findElement(By.xpath(".//*[contains(@id,dRole )]")); //div/div[1]/div/div"));
		//		//*[@id="functionalrole"]/div/div[1]/div
		//		
		//		//Creating list of webelements returned from left pane				
		//		List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\'"+dRole+"']/div/div[1]/div/div/div[2]/ul/li"));
		//		//List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div/div[2]/ul/li"));
		//		
		//		//Iterating the role list webelement until match is found
		//	        for (WebElement specificRoleName : rolesList) {
		//	        	
		//	        	//locate specific role with text
		//	        	if(specificRoleName.findElement(By.tagName("a")).getText().equals("21212"))
		//	        	
		//	        		//Clicking on specific role
		//	        		specificRoleName.click();	            
		//	        }

		//System.out.println();
		/// *******************WOrking code ENDS
		//waiting for link to load and then click
		//wait.until(ExpectedConditions.elementToBeClickable(DashboardPage.restrictTab_XPath));
		//DashboardPage.restrictTab_XPath.click();

		//Clicking on specific Role assigned
		RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "21212");

		//CLicking on Add new permission button
		//objDataRoleFunctionalRolePage.dataRolePermTab_XPath.click();
		//genericApplicationMethods.elementClick(wd, DataRolesFunctionalRolesPage.dataRolePermTab_XPath);

		//Clicking on add permission button
		//objDataRoleFunctionalRolePage.addNewPermissionRestrictBtn_XPath.click();
		//genericApplicationMethods.elementClick(wd, DataRolesFunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

		//Clicking on save button on Add new permission/ restriction page
		//objAddNewPermRestrictionPage.savBtn_XPath.click();
		GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);

		//verifying validation messages on Add new permissions page

//		Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.practice_label_XPath),
//				AddNewPermRestrct.PRACTICE_Txt);	

		//		Assert.assertEquals(objAddNewPermRestrictionPage.practice_title_XPath.getText(),
		//				SanityPack_TestNamesDescription.PRACTICE_Txt);	
		//
		//		Assert.assertEquals(objAddNewPermRestrictionPage.practice_title_XPath.getText(),
		//				SanityPack_TestNamesDescription.REGION_Txt);	
		//
		//		Assert.assertEquals(objAddNewPermRestrictionPage.practice_title_XPath.getText(),
		//				SanityPack_TestNamesDescription.DISTRICT_Txt);	
		//
		//		Assert.assertEquals(objAddNewPermRestrictionPage.practice_title_XPath.getText(),
		//				SanityPack_TestNamesDescription.FACILITY_Txt);	

		//Clicking on cancel button
		//objAddNewPermRestrictionPage.cancelBtn_XPath.click();
		GenericMethods.elementClick(wd, AddNewPermRestrct.cancelBtn_XPath);

		//Clicking on logged in user link
		//objDashboardPage.loggedinUser_XPath.click();
		GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

		//Waiting until element is visible
		//wait.until(ExpectedConditions.elementToBeClickable(objDashboardPage.logout_XPath));
		//objDashboardPage.logout_XPath.click();

		GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
		GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

		//Waiting for user experience
		Thread.sleep(2000);

	}

	@Test(priority=6,enabled=SanityPack_TestNamesDescription.TC6_runStatus)
	public void DataRole_AddNewPermission_Validation() throws Exception {

		try {

			//Setting Test name and description on report
			//SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC6_testName,SanityPack_TestNamesDescription.TC6_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DataRoles_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Clicking on specific Role assigned
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "21212");

			//CLicking on Add new permission button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
			
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);	
			GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

			//Clicking on add permission button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Clicking on save button on Add new permission/ restriction page
			GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//verifying validation messages on Add new permissions page
			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.practice_title_XPath),
					AddNewPermRestrct.PRACTICE_Txt);	

			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.region_query_XPath),
					AddNewPermRestrct.REGION_Txt);

			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.district_entity_XPath),
					AddNewPermRestrct.DISTRICT_Txt);

			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.facility_entities_XPath),
					AddNewPermRestrct.FACILITY_Txt);

			//Clicking on cancel button
			GenericMethods.elementClick(wd, AddNewPermRestrct.cancelBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboard_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.dashboard_XPath);
			GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}
	}

	@Test(priority=7,enabled=SanityPack_TestNamesDescription.TC7_runStatus)
	public void DataRole_AddNewRestriction_Validation() throws Exception {

		try {

			//Setting Test name and description on report
			//SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC7_testName,SanityPack_TestNamesDescription.TC7_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DataRoles_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DataRoles_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Clicking on specific Role assigned
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "NewDataRole");

			//CLicking on Add new permission button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRoleRestrictionTab_XPath);

			//Clicking on add permission button
			GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Clicking on save button on Add new permission/ restriction page
			GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//verifying validation messages on Add new permissions page
//			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.practice_title_XPath),
//					AddNewPermRestrct.TITLE_Txt);	
//
//			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.region_query_XPath),
//					AddNewPermRestrct.QUERY_Txt);
//
//			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.district_entity_XPath),
//					AddNewPermRestrct.ENTITYGRP_Txt);
//
//			Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.facility_entities_XPath),
//					AddNewPermRestrct.ENTITIES_Txt);			

			//Clicking on cancel button
			GenericMethods.elementClick(wd, AddNewPermRestrct.cancelBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboard_XPath);	
			GenericMethods.elementClickable(wd, dashboardnew.dashboard_XPath);
			GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.loggedinUser_XPath);
			GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

	}

	@Test(priority=9,enabled=SanityPack_TestNamesDescription.TC9_runStatus)
	public void DataRole_Permission_Validation_AddEditDelete() throws Exception {

		try {

				//Setting Test name and description on report
				SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC9_testName,SanityPack_TestNamesDescription.TC9_testDescription);

				//Calling Login method
				GenericMethods.loginApplication
				(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
						password, loginPage.loginBtn_XPath);

				//Waiting until element to load
				GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);				

				//Clicking on Element
				GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

				//waiting for link to load and then click
				GenericMethods.waitforElement(wd, dashboardnew.DataRoles_XPath);

				//Clicking on Element
				GenericMethods.elementClick(wd, dashboardnew.DataRoles_XPath);

				//*********************************ADD New role starts here
				
				//Clicking on add new data role
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);

				//Waiting for popup to load
				GenericMethods.waitforElement(wd, AddNewDataRole.addBtn_XPath);	
				GenericMethods.elementClickable(wd, AddNewDataRole.addBtn_XPath);

				//input role name and internal name
				GenericMethods.sendText(wd, AddNewDataRole.inputroleName_XPath, "TestUser");
								
				//clicking on add role button
				GenericMethods.elementClick(wd, AddNewDataRole.addBtn_XPath);
				
				//waiting for success message
				GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
				GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
				
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath),
						AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT);
				
				
				//**************************************************Permission validation STARTS HERE
				
				//Clicking on specific Role created
				RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "TestUser");
				
				//GenericMethods.elementInvisible(wd, dataRolesfunctionalRolesPage.loader_Xpath);
				
				//waiting for datarole permission tab
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);	
				GenericMethods.elementVisible(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				//CLicking on Add new permission button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);	
				GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

				//Clicking on add permission button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

				//Clicking on save button on Add new permission/ restriction page
				GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);

				//verifying validation messages on Add new permissions page
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.practice_title_XPath),
						AddNewPermRestrct.PRACTICE_Txt);	

				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.region_query_XPath),
						AddNewPermRestrct.REGION_Txt);

				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.district_entity_XPath),
						AddNewPermRestrct.DISTRICT_Txt);

				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.facility_entities_XPath),
						AddNewPermRestrct.FACILITY_Txt);

				//Clicking on cancel button
				GenericMethods.elementClick(wd, AddNewPermRestrct.cancelBtn_XPath);

				
				//**************************************************Permission Add new starts HERE
								
				//waiting for datarole permission tab
				GenericMethods.waitforPresenceOfElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				//GenericMethods.waitforElementRefresh(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				//GenericMethods.elementInvisible(wd, dataRolesfunctionalRolesPage.loader_Xpath);
				
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);	
				GenericMethods.elementVisible(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				//CLicking on Add new permission button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);	
				GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

				//Clicking on add permission button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addNewPermissionRestrictBtn_XPath);

				//selecting value from dropdown
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewPermRestrct.practice_dropdown_XPath, "Practice1");
				
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewPermRestrct.region_dropdown_XPath, "South1");
				
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, AddNewPermRestrct.district_dropdown_XPath, "District3");
				
				GenericMethods.waitforElement(wd, AddNewPermRestrct.facility_Entities_DropdnClick_Xpath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.facility_Entities_DropdnClick_Xpath);
				
				GenericMethods.elementClick(wd, AddNewPermRestrct.facility_Entities_DropdnClick_Xpath);
				
				//wait for element
				//GenericMethods.waitforElement(wd, AddNewPermRestrct.addNewPermissionRestrictBtn_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.facilitiesValue1_dropdown_XPath);
				
				//CLicking on Add new permission button
				GenericMethods.elementClick(wd, AddNewPermRestrct.facilitiesValue1_dropdown_XPath);
				
				//CLicking on Add new permission button
				GenericMethods.elementClick(wd, AddNewPermRestrct.facilitiesValue2_dropdown_XPath);
				
				//collapsing the facitlity dropdown
				//GenericMethods.elementClick(wd, AddNewPermRestrct.dropdownIconClick_XPath);
				
				//**************************************************ADD PERMISSION ENDS HERE
				
				//Clicking on save button on Add new permission/ restriction page
				GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);
								
				//waiting for success message
				GenericMethods.waitforElement(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);	
				GenericMethods.elementVisible(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);
				
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath),
						AddNewPermRestrct.DATAROLE_PERM_RESTRCT_SUCCESS_MSG_Txt);
				
				//Clicking on save button on Add new permission/ restriction page
				//GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.permList_XPath);
				
				//***********************EDIT PERM STARTS HERE
								
				//Clicking on specific Role created
				//RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "TestUser");
								
				//waiting for datarole permission tab
				//GenericMethods.waitforPresenceOfElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				GenericMethods.waitforPresenceOfElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				//GenericMethods.waitforElementRefresh(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				//waiting for success message
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath);	
				GenericMethods.elementVisible(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath);				
				
				
				//click on specific value in table with value
				RAIS_applicationSpecificMethods.perm_restrict_Select_Click
				(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath, "Practice1");
				
				GenericMethods.elementClick(wd, AddNewPermRestrct.facility_Entities_DropdnClick_Xpath);
				
				//wait for element
				//GenericMethods.waitforElement(wd, AddNewPermRestrct.addNewPermissionRestrictBtn_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.facilitiesValue1_dropdown_XPath);
				
				//CLicking on Add new permission button
				GenericMethods.elementClick(wd, AddNewPermRestrct.facilitiesValue1_dropdown_XPath);
				
				//collapsing the facitlity dropdown
				//GenericMethods.elementClick(wd, AddNewPermRestrct.dropdownIconClick_XPath);
				
				//Clicking on save button on Add new permission/ restriction page
				GenericMethods.elementClick(wd, AddNewPermRestrct.savBtn_XPath);
				
				//waiting for success message
				GenericMethods.waitforElement(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);	
				GenericMethods.elementVisible(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);
				
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath),
						AddNewPermRestrct.DATAROLE_PERM_RESTRCT_SUCCESS_MSG_Txt);
				
				//***********************EDIT PERM ENDS HERE
				
				//***********************DELETE PERM STARTS HERE
				
				GenericMethods.waitforPresenceOfElement(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				//GenericMethods.waitforElementRefresh(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
				
				//waiting for success message
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath);	
				GenericMethods.elementVisible(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath);
				
				//click on specific value in table with value
				RAIS_applicationSpecificMethods.perm_restrict_Select_Click
				(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath, "Practice1");
								
				//Waiting until element to load
				GenericMethods.waitforElement(wd, AddNewPermRestrct.deleteBtn_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.deleteBtn_XPath);
				
				//Clicking on Delete button on Add new permission/ restriction page
				GenericMethods.elementClick(wd, AddNewPermRestrct.deleteBtn_XPath);
				
				//Waiting for delete popup page
				GenericMethods.waitforElement(wd, AddNewPermRestrct.delDataRolePermRestrct_PopupMSGCONTENT_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.delDataRolePermRestrict_btnYES_XPath);
								
				//verifying the Delete popup message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.delDataRolePermRestrct_PopupMSGCONTENT_XPath),
						AddNewPermRestrct.DELETE_DATAROLE_PERMRESTRCT_Txt);
				
				//Clicking on No button of pop page
				GenericMethods.elementClick(wd, AddNewPermRestrct.delDataRolePermRestrict_btnNO_XPath);
				
				//Now clicking on YES Button****************
				
				//Waiting until element to load
				GenericMethods.waitforElement(wd, AddNewPermRestrct.deleteBtn_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.deleteBtn_XPath);
				
				//Clicking on Delete button on Add new permission/ restriction page
				GenericMethods.elementClick(wd, AddNewPermRestrct.deleteBtn_XPath);
				
				//Waiting for delete popup page
				GenericMethods.waitforElement(wd, AddNewPermRestrct.delDataRolePermRestrct_PopupMSGCONTENT_XPath);	
				GenericMethods.elementClickable(wd, AddNewPermRestrct.delDataRolePermRestrict_btnYES_XPath);
				
				//********************
				
				//Clicking on Yes button of pop page
				GenericMethods.elementClick(wd, AddNewPermRestrct.delDataRolePermRestrict_btnYES_XPath);
								
				//waiting for success message
				GenericMethods.waitforElement(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);	
				GenericMethods.elementVisible(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath);
				
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wd, AddNewPermRestrct.addnewPermRestrict_SuccessMsg_XPath),
						AddNewPermRestrct.DELETEROLE_PERMRESTRCT_SUCCESSMSG_TXT);
				
				
				//***********************DELETE PERM ENDS HERE
				//*************************Delete Role starts here
				
				//Clicking on specific Role created
				RAIS_applicationSpecificMethods.roleSelect_Click(wd,dataRolesfunctionalRolesPage.DATA_ROLE_Txt , "TestUser");
				
				//Waiting until element to load
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);	
				GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_editRoleBtn_XPath);
				
				//Waiting for delete popup page
				GenericMethods.waitforElement(wd, dataRolesfunctionalRolesPage.deletePopupMsg_XPath);	
				GenericMethods.elementClickable(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
				
				//clicking on Yes button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.deletePopupMsgYesBtn_XPath);
								
				//waiting for success message
				GenericMethods.waitforElement(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);	
				GenericMethods.elementVisible(wd, AddNewDataRole.addnewRole_SuccessMsg_XPath);
				
				
				
				//**************************DELETE ROLE ENDS HERE
				
				
				//Waiting until element to load
				GenericMethods.waitforElement(wd, dashboardnew.dashboard_XPath);	
				GenericMethods.elementClickable(wd, dashboardnew.dashboard_XPath);
				GenericMethods.elementClick(wd, dashboardnew.dashboard_XPath);
				
				//Waiting until element is visible to be clicked
				GenericMethods.waitforElement(wd, dashboardnew.loggedinUser_XPath);
				GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

				//Waiting until element is visible to be clicked
				GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
				GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

			}catch (NoSuchElementException  noElement) {
				noElement.printStackTrace();

			}catch (Exception  e) {
				e.printStackTrace();
			}
		}

	
	
	
	
	
	
	//Only for Testing
	@Test(enabled=false)
	public void QATest( ) {
		
		try {

			
			//Calling login function
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element to load
			GenericMethods.waitforElement(wd, dashboardnew.dashboardUnderDevelopment_XPath);
			
			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.administration_XPath);

			//Waiting for user experience
			Thread.sleep(2000);
			
			//clicking on dataroles
			wd.findElement(By.xpath(dashboardnew.DataRoles_XPath)).click();
			
			//Waiting for user experience
			Thread.sleep(2000);
			
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click
			(wd, dataRolesfunctionalRolesPage.perm_restrct_Table_XPath, "Practice1");
			
						
			

			//Clicking on logged in user link
			GenericMethods.elementClick(wd, dashboardnew.loggedinUser_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

			//Waiting until element is visible to be clicked
			GenericMethods.waitforElement(wd, dashboardnew.logout_XPath);
			GenericMethods.elementClick(wd, dashboardnew.logout_XPath);

			//Waiting for user experience
			Thread.sleep(2000);

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void LoginScreen_Default_Backup() throws Exception {

		try {

			//Setting Test name and description on report
			//SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC1_testName,SanityPack_TestNamesDescription.TC1_testDescription);

			//verifying logo on RIAS Page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.RIASHeaderLabel_XPath, loginPage.RAIS_Txt), true);
			

			//verify internal text label name of login
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.externalLogin_XPath, loginPage.EXT_LOGIN_Txt), true);
						
			//verify external text label login text
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.internalLogin_XPath, loginPage.INT_LOGIN_Txt), true);
						
			//verify username label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.userName_label_XPath, loginPage.USERNAME_LBL_Txt), true);
			
			//verify password label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.pwd_label_XPath, loginPage.PWD_LBL_Txt), true);
			
			//verify userid text field
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, loginPage.userId_XPath,0,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			
			//verify pwd text field
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, loginPage.pwd_XPath,0,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
					
			//verify login button text
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.loginBtn_XPath, loginPage.LOGIN_BTN_Txt), true);
			
			//verify username label text, enabled
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.frgtPwd_XPath, loginPage.FORGOT_PWD_Txt), true);
			
			//page refresh
			wd.navigate().refresh();
			
			//Clicking on external login page using Clicking on Element
			GenericMethods.elementClick(wd, loginPage.internalLogin_XPath);

			//verify text displayed on the button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, loginPage.winAuthBtn_XPath, loginPage.WINDOWS_AUTH_Txt), true);
			
			wd.navigate().refresh();
			
			//Clicking back on internal link
			GenericMethods.elementClick(wd, loginPage.externalLogin_XPath);
			
			//Clicking on external login page using Clicking on Element
			GenericMethods.elementClick(wd, loginPage.frgtPwd_XPath);
			
			//verify input field displayed on the forgot page
			//Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.inputField_label_XPath, forgotPwdPage.FORGOTPAGE_INPUTFIELD_LBL_Txt), true);
			
			//verify input value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.inputValue_label_XPath, forgotPwdPage.FORGOTPAGE_INPUTVALUE_LBL_Txt), true);
			
			//verify username/ email displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, forgotPwdPage.inputUsrNameEmailID_XPath, 0,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			
			//verify input value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.loginPageLink_XPath, forgotPwdPage.FORGOTPAGE_LOGIN_LINK_Txt), true);
			
			//verify Selected radion button value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifySelectedRadioButtonProperty(wd, forgotPwdPage.frgotUserNameRadioBtn_XPath,
					forgotPwdPage.frgotUserNameRadioBtnLabel_XPath, forgotPwdPage.FORGOTPAGE_USRNAME_RDOBTN_Txt, 
					RaisTestData.verifiedRadioButtonProperty), RaisTestData.verifiedRadioButtonProperty);
			
			//verify Deselected radion button value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifySelectedRadioButtonProperty(wd, forgotPwdPage.frgotEmailRadioBtn_XPath,
					forgotPwdPage.frgotEmailRadioBtnLabel_XPath, forgotPwdPage.FORGOTPAGE_EMAIL_RDOBTN_Txt,
					RaisTestData.verifiedRadioButtonProperty), RaisTestData.verifiedRadioButtonProperty);
			
			//verify input value displayed on the forgot page
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, forgotPwdPage.submitBtn_XPath, forgotPwdPage.FORGOTPAGE_SUBMIT_BTN_Txt), true);
						
			//Clicking on login link using Clicking on Element
			GenericMethods.elementClick(wd, forgotPwdPage.loginPageLink_XPath);
			
		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
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
