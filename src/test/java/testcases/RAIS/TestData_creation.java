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
import pageLocators_Elements.RAIS.FormDesignerPage;
import pageLocators_Elements.RAIS.LinkedFormListingPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class TestData_creation extends BaseClass
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

	//passcurrent time
	public static String localTime = GenericMethods.currentLocalTime();

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
	@BeforeClass
	public void beforeClass() {

		try {

			//Assigning browser type to browser category
			browserCategory = "Chrome";

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

	//#1
	@Test(priority=1,enabled=false)
	public void E2E_Publish_Custom_Entity(){

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(SanityPack_TestNamesDescription.TC13_testName,SanityPack_TestNamesDescription.TC13_testDescription);

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

	//#2
	@Test(priority=2,enabled=false)
	public void TestData_Entity_AttributeCreation(){

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
			//			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			//			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, "TestDataSetupInternalName" + localTime);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, RaisTestData.Entity_DescriptionData + localTime);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, "TestDataSetupSingularName" + localTime);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, "TestDataSetupPluralName" + localTime);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, RaisTestData.Entity_GroupData);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, RaisTestData.Entity_RoleData);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, RaisTestData.Entity_PublishNavigation1Data);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, RaisTestData.Entity_PublishNavigation2Data);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			//			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
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
					entListingPage.entityListingTableColHeader_TXT_XPath,"TestDataSetupSingularName"+ localTime );

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , "TestDataSetupSingularName"+ localTime);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//			//Waiting until element to load
			//			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);
			//GenericMethods.elementClick(wd, addEntityPage.attributeLink_XPath);




			//Loop to create attributes for all types
			for (int i = 0; i <= 17; i++) { 

				//Temp code insert here
				//Waiting until element to load
				//GenericMethods.elementInvisible(wd, addEntityPage.waitForLoader_XPath);

				//**********************
				//				//				
				//				//wait for page load
				//				GenericMethods.pageLoadWait(1000);
				//				
				//				//Waiting until element to load
				//				GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
				//				GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
				//				GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
				//				

				//*************************
				//Add data for check box

				//wait for page load
				GenericMethods.pageLoadWait(2000);
				GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

				if (addAttbt.EntityattributeValueList[i] != "User" ) {

					//Wait for page load
					GenericMethods.pageLoadWait(600);

					//***Attribute creation method
					RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[i]);

				} else {

					//Waiting until element to load
					//GenericMethods.elementInvisible(wd, addEntityPage.waitForLoader_XPath);

					//Waiting until element to load
					//					GenericMethods.waitforElement(wd, addAttbt.cancelBtn_XPath);	
					//					GenericMethods.elementClickable(wd, addAttbt.cancelBtn_XPath);
					//					GenericMethods.waitforvisibilityOfElement(wd, addAttbt.cancelBtn_XPath);

					//wait for page load
					GenericMethods.pageLoadWait(500);
					GenericMethods.elementClick(wd, addAttbt.cancelBtn_XPath);
					//GenericMethods.pageLoadWait(300);

					//wait for page load
					GenericMethods.pageLoadWait(500);
				}

			} 
			////			
			////			//wait for page load
			//			GenericMethods.pageLoadWait(3000);
			//
			//			//Clicking on attribute link on left pane
			//			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			//Clicking on specific form - main form
			//			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , "New Form");
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			System.out.println("Form opened");
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, "TestSection");
			//									
			//			
			//			GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//						
			//			GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);
			//			
			//			System.out.println("3 sections created");
			//			
			//			///Adding colproperties on first section
			//			
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);
			//						
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_ChkBox_dropdown_XPath);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//***************End of adding col prop
			//			
			//			///Adding colproperties on 2nd section
			//			
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//			
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//			GenericMethods.pageLoadWait(500);

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

			//			//***************End of adding col prop
			//			
			//			GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			//			
			//			//Clicking on Element
			//			GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);
			//
			//			//waiting for link to load and then click
			//			GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			//			GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);
			//
			//			//Clicking on Element
			//			GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);
			//			
			//			
			//System.out.println("Entity created");

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


	//#33 - Single Business Entity Creation only- No form designer
	@Test(priority=3,enabled=false)
	public void BUSINESS_ENTITYCREATION(){

		//Setting entity names
		String internalName = "AuthoritiesandOrganizations";
		String DescripTxt = "Authorities and Organizations";
		String singularName = "Authorities and Organizations";
		String pluralName = "Authorities and Organizations";
		String GroupName = "Common"; //Facilities and authorities   Common
		String EntityRole = "Master";
		String PublisInNav1 = "Administration"; //Administration		Inventory & Resources Regulatory Processes
		String PublisInNav2 = "Common Tables"; //Inventory		Common Tables Authorization

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC2_testName,E2E_TestPack.e2eTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, "Superadmin", loginPage.pwd_XPath, 
					"Pass123$", loginPage.loginBtn_XPath);

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
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, DescripTxt);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, GroupName);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, EntityRole);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, PublisInNav1);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, PublisInNav2);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);

			//Add entity completed and below is for selecting particular entity
			//wait for page load
			GenericMethods.pageLoadWait(1000);			

			//			//Waiting for button to load and click
			//			GenericMethods.waitforElement(wd, entListingPage.addNewEntityBtn_XPath);	
			//			GenericMethods.elementClickable(wd, entListingPage.addNewEntityBtn_XPath);
			//
			//			//wait for page load
			//			GenericMethods.pageLoadWait(500);
			//
			//			//Column header filter starts here
			//			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
			//					entListingPage.entityListingTableColHeader_TXT_XPath,singularName);
			//
			//			//wait for page load
			//			GenericMethods.pageLoadWait(500);
			//
			//			//Clicking on specific Role created
			//			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);
			//
			//			//wait for page load
			//			GenericMethods.pageLoadWait(3000);
			//			
			////			//Waiting until element to load
			////			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			////			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			////			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//
			//			//Clicking on attribute link on left pane
			//			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);
			//			//GenericMethods.elementClick(wd, addEntityPage.attributeLink_XPath);
			//
			//			//wait for page load
			//			GenericMethods.pageLoadWait(2000);
			//			
			//			//Waiting until element to load
			//			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			
			//			//Add data for check box
			//			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//
			//			//Wait for page load
			//			GenericMethods.pageLoadWait(2000);
			//			
			//			//***Attribute creation method
			//			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[0]);
			//			
			//			//Waiting until element to load
			//			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			//Add data for check box
			//			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			//Wait for page load
			//			GenericMethods.pageLoadWait(2000);
			//			
			//			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[10]);
			//			
			//			//Waiting until element to load
			//			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			//Add data for check box
			//			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//			//Wait for page load
			//			GenericMethods.pageLoadWait(2000);
			//			
			//			RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[13]);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(2000);
			//
			//			//Clicking on attribute link on left pane
			//			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			//Clicking on specific form - main form
			//			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListing.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			System.out.println("Form opened");
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, RaisTestData.sectionTitle);
			//									
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//			//GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton3_XPath);
			//			
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);
			//			
			//			System.out.println("2 sections created");
			//			
			//			///Adding colproperties on first section
			//			
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);
			//						
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_ChkBox_dropdown_XPath);
			//			
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//***************End of adding col prop
			//			
			//			///Adding colproperties on 2nd section
			//			
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//Clicking on column prop 2 to add text field
			//			GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//			
			//			GenericMethods.pageLoadWait(500);
			//			
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(250);
			//			
			//			//Clicking on column prop 2 to add numeric field
			//			//GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//			
			//			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			//			
			//			
			//			GenericMethods.pageLoadWait(500);
			//			//Clicking on column prop 1
			//			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//			GenericMethods.pageLoadWait(500);
			//			
			//			//***************End of adding col prop
			//			
			//			///Adding colproperties on 3rd section
			//			
			////			GenericMethods.pageLoadWait(500);
			////			
			////			//Clicking on column prop 1
			////			GenericMethods.elementClick(wd, frmDesign.colProp3_Temp_XPath);		
			////			
			////			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			////			
			////			//Clicking on column prop 1
			////			GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			////			GenericMethods.pageLoadWait(500);
			//			
			//			//***************End of adding col prop
			//			
			//			//GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");
			//			
			//			GenericMethods.pageLoadWait(500);
			//			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			//			
			//			//Waiting for button to load and click
			//			GenericMethods.waitforElement(wd, linkedFrmListing.addNewFormBtn_XPath);	
			//			GenericMethods.elementClickable(wd, linkedFrmListing.addNewFormBtn_XPath);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			wd.navigate().refresh();
			//			GenericMethods.pageLoadWait(1000);
			//						
			//			//Clicking on Element
			//			GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			//			GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);
			//
			//			//String newEntityCreatedXPATH = RAIS_applicationSpecificMethods.dashboardSubMenuDynamicXpath(pluralName);
			//			
			//			//waiting for link to load and then click
			//			GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			//			GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);
			//
			//			//Clicking on Element
			//			//RAIS_applicationSpecificMethods.roleSelect_Click(wd, "sub-menu", newEntityCreatedXPATH);
			//			GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);
			//			
			//			//wait for page load
			//			GenericMethods.pageLoadWait(1000);
			//			
			//			System.out.println("waiting newly created entity page to load");
			//			
			//			
			////************** below is temporariliy not used
			//			//initialising dyanmic xpath
			//			String dynamicTextBox_Xpath = null ;String dynamic_CheckBox_Xpath = null ; String dynamicNumericBox_Xpath = null ;
			//			
			//			dynamicTextBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//							RaisTestData.text_label, 
			//							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);			
			//			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicTextBox_Xpath, 
			//					RaisTestData.text_label), RaisTestData.text_label);
			//			
			//			
			//			
			//			dynamic_CheckBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//							RaisTestData.chkBox_label, 
			//							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			//			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamic_CheckBox_Xpath, 
			//					RaisTestData.chkBox_label), RaisTestData.chkBox_label);
			//			
			//			
			//			dynamicNumericBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//					(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//							RaisTestData.numeric_label, 
			//							entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			//			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicNumericBox_Xpath, 
			//					RaisTestData.numeric_label), RaisTestData.numeric_label);
			//			
			//			
			//		//waiting for link to load and then click
			//				GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			//				GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			//
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				//Clicking on Element
			//				GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);
			//				
			//				//page wait
			//				GenericMethods.pageLoadWait(2000);
			//				
			//				//TO be ocntinued
			//				//verify check box label
			//				Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath),
			//						RaisTestData.chkBox_label);
			//
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				//verify sample text field label and relevant input field
			//				Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Txt_XPath),
			//						RaisTestData.text_label);
			//				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath, 12,
			//						RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				//verify numeric field label and relevant input field
			//				Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Numeric_XPath),
			//						RaisTestData.numeric_label);
			//				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, 50,
			//						RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			//				
			//				
			//				
			//				//verification of fields ends above
			//				
			//				//input data into sample check box fields
			//				//waiting for link to load and then click
			//				GenericMethods.elementClickable(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			//				GenericMethods.waitforElement(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			//				
			//				//input data on sample numeric and text fields
			//				GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, "Inspection Form");
			//				
			//				//selecting checkbox
			//				GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);				
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//								
			//				//input data on sample numeric and text fields
			//				GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath,"Vienna");
			//				
			//				//input data on sample numeric and text fields
			//				GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, "1060");
			//				
			//						
			//				// Clicking on save butto
			//				GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);
			//				GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			//				GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);
			//
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);				
			//				//Clicking on Element				
			//				
			//				System.out.println("data visible on column");
			//				
			//				Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "Vienna"),
			//						"Vienna");
			//				
			//				Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "1060"),
			//						"1060");

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

	//#44 - Single Business Entity Creation only- No form designer
	@Test(priority=3,enabled=false)
	public void BUSINESS_ENTITYCREATION_FORMDESIGNER(){

		//Setting entity names
		String internalName = "SecurityGroup"; // parent entity
		String DescripTxt = "Security Group";
		String singularName = "Security Group";
		String pluralName = "Security Groups";
		String GroupName = "Common";
		String EntityRole = "Master";
		String PublisInNav1 = "Administration";
		String PublisInNav2 = "Common Tables";

		String Attribute1 = "Lookup";
		String LinkedEntity = "Regions"; // child1 entity

		String Attribute2 = "Lookup";
		String LinkedEntity2 = "Branch";// child2 entity

		String Attribute3 = "Lookup";
		String LinkedEntity3 = "Academic Qualification Degree";// child2 entity

		LinkedFormListingPage linkedFrmListingforBusinessEntity = new LinkedFormListingPage();

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC2_testName,E2E_TestPack.e2eTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, "Superadmin", loginPage.pwd_XPath, 
					"Pass123$", loginPage.loginBtn_XPath);

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
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, DescripTxt);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, GroupName);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, EntityRole);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, PublisInNav1);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, PublisInNav2);

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
					entListingPage.entityListingTableColHeader_TXT_XPath,singularName);

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);

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
			GenericMethods.pageLoadWait(1000);

			//***Attribute creation method custom for BUSINESS ONLY
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, Attribute1,LinkedEntity);

			GenericMethods.pageLoadWait(2000);



			//Waiting until element to load
			GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			GenericMethods.pageLoadWait(1000);

			//Add data for check box
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//Wait for page load
			GenericMethods.pageLoadWait(2000);


			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on specific form - main form
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,linkedFrmListingforBusinessEntity.linkdFrmListingPageTable_XPath , RaisTestData.linkedFormName);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			System.out.println("Form opened");

			//*********************************Fit multiple drop down selections below

			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, "//*[@class='MuiDialog-root application-dialog undefined']//form//div//button[@class='select-column']//span");


			GenericMethods.pageLoadWait(500);

			//Clicking on column prop 1
			//GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);

			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, "//*[text()='"	+LinkedEntity+	"']");

			GenericMethods.pageLoadWait(500);

			//*********************************Fit multiple drop down selections below




			//Clicking on column prop 1
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);

			//Waiting for button to load and click
			GenericMethods.waitforElement(wd, linkedFrmListingforBusinessEntity.addNewFormBtn_XPath);	
			GenericMethods.elementClickable(wd, linkedFrmListingforBusinessEntity.addNewFormBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);

			wd.navigate().refresh();
			GenericMethods.pageLoadWait(1000);
			//				
			//				//***************End of adding col prop
			//				
			//				///Adding colproperties on 2nd section
			//				
			//				GenericMethods.pageLoadWait(500);
			//				
			//				//Clicking on column prop 2 to add text field
			//				GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//				
			//				GenericMethods.pageLoadWait(500);
			//				
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(250);
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
			//				
			//				//***************End of adding col prop
			//				
			//				///Adding colproperties on 3rd section
			//				
			////				GenericMethods.pageLoadWait(500);
			////				
			////				//Clicking on column prop 1
			////				GenericMethods.elementClick(wd, frmDesign.colProp3_Temp_XPath);		
			////				
			////				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Numeric_dropdown_XPath);
			////				
			////				//Clicking on column prop 1
			////				GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			////				GenericMethods.pageLoadWait(500);
			//				
			//				//***************End of adding col prop
			//				
			//				//GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");
			//				
			//				GenericMethods.pageLoadWait(500);
			//				GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			//				
			//				//Waiting for button to load and click
			//				GenericMethods.waitforElement(wd, linkedFrmListing.addNewFormBtn_XPath);	
			//				GenericMethods.elementClickable(wd, linkedFrmListing.addNewFormBtn_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				wd.navigate().refresh();
			//				GenericMethods.pageLoadWait(1000);
			//							
			//				//Clicking on Element
			//				GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			//				GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);
			//
			//				//String newEntityCreatedXPATH = RAIS_applicationSpecificMethods.dashboardSubMenuDynamicXpath(pluralName);
			//				
			//				//waiting for link to load and then click
			//				GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			//				GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);
			//
			//				//Clicking on Element
			//				//RAIS_applicationSpecificMethods.roleSelect_Click(wd, "sub-menu", newEntityCreatedXPATH);
			//				GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				System.out.println("waiting newly created entity page to load");
			//				
			//				
			////************** below is temporariliy not used
			//				//initialising dyanmic xpath
			//				String dynamicTextBox_Xpath = null ;String dynamic_CheckBox_Xpath = null ; String dynamicNumericBox_Xpath = null ;
			//				
			//				dynamicTextBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//						(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//								RaisTestData.text_label, 
			//								entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);			
			//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicTextBox_Xpath, 
			//						RaisTestData.text_label), RaisTestData.text_label);
			//				
			//				
			//				
			//				dynamic_CheckBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//						(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//								RaisTestData.chkBox_label, 
			//								entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamic_CheckBox_Xpath, 
			//						RaisTestData.chkBox_label), RaisTestData.chkBox_label);
			//				
			//				
			//				dynamicNumericBox_Xpath = RAIS_applicationSpecificMethods.createCustomXpath
			//						(entityFrmListingPage.generic_Prefix_EntityFrmListingColHeader1_XPath, 
			//								RaisTestData.numeric_label, 
			//								entityFrmListingPage.generic_Suffix_EntityFrmListingColHeader1_XPath);
			//				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wd, dynamicNumericBox_Xpath, 
			//						RaisTestData.numeric_label), RaisTestData.numeric_label);
			//				
			//				
			//			//waiting for link to load and then click
			//					GenericMethods.elementClickable(wd, entityFrmListingPage.addNewBtn_XPath);
			//					GenericMethods.waitforElement(wd, entityFrmListingPage.addNewBtn_XPath);
			//
			//					//wait for page load
			//					GenericMethods.pageLoadWait(1000);
			//					
			//					//Clicking on Element
			//					GenericMethods.elementClick(wd, entityFrmListingPage.addNewBtn_XPath);
			//					
			//					//page wait
			//					GenericMethods.pageLoadWait(2000);
			//					
			//					//TO be ocntinued
			//					//verify check box label
			//					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath),
			//							RaisTestData.chkBox_label);
			//
			//					//wait for page load
			//					GenericMethods.pageLoadWait(1000);
			//					
			//					//verify sample text field label and relevant input field
			//					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Txt_XPath),
			//							RaisTestData.text_label);
			//					Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath, 12,
			//							RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			//					
			//					//wait for page load
			//					GenericMethods.pageLoadWait(1000);
			//					
			//					//verify numeric field label and relevant input field
			//					Assert.assertEquals(GenericMethods.getActualTxt(wd, entityFrmDetailspage.entityFormDetailsPage_Numeric_XPath),
			//							RaisTestData.numeric_label);
			//					Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, 50,
			//							RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);
			//					
			//					
			//					
			//					//verification of fields ends above
			//					
			//					//input data into sample check box fields
			//					//waiting for link to load and then click
			//					GenericMethods.elementClickable(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			//					GenericMethods.waitforElement(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);
			//					
			//					//input data on sample numeric and text fields
			//					GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNameFld_XPath, "Inspection Form");
			//					
			//					//selecting checkbox
			//					GenericMethods.elementClick(wd, entityFrmDetailspage.entityFormDetailsPage_Checkbox_XPath);				
			//					
			//					//wait for page load
			//					GenericMethods.pageLoadWait(1000);
			//									
			//					//input data on sample numeric and text fields
			//					GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputTextFld_XPath,"Vienna");
			//					
			//					//input data on sample numeric and text fields
			//					GenericMethods.sendText(wd, entityFrmDetailspage.entityFormDetailsPage_inputNumericFld_XPath, "1060");
			//					
			//							
			//					// Clicking on save butto
			//					GenericMethods.elementClickable(wd, entityFrmDetailspage.SaveBtn_XPath);
			//					GenericMethods.waitforElement(wd, entityFrmDetailspage.SaveBtn_XPath);
			//					GenericMethods.elementClick(wd, entityFrmDetailspage.SaveBtn_XPath);
			//
			//					//wait for page load
			//					GenericMethods.pageLoadWait(1000);				
			//					//Clicking on Element				
			//					
			//					System.out.println("data visible on column");
			//					
			//					Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "Vienna"),
			//							"Vienna");
			//					
			//					Assert.assertEquals(RAIS_applicationSpecificMethods.gridGetText(wd, entityFrmListingPage.securityProfileTableList_XPath, "1060"),
			//							"1060");

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

	//demo business entity creation - OLD DND
	@Test(priority=77,enabled=false)
	public void businessEntityCreation() {



		//Initialising creation flag
		Boolean entityPresent = false;

		System.out.println(RAIS_applicationSpecificMethods.getEntityCreationDetails().size());

		ArrayList<Object> entityData = new ArrayList<>(RAIS_applicationSpecificMethods.getEntityCreationDetails());
		ArrayList<Object> ent = new ArrayList<>();

		ArrayList<String> listOfStrings = new ArrayList<>(RAIS_applicationSpecificMethods.getEntityCreationDetails().size());

		//ArrayList<String> listOfStrings1 = ArrayList<Object>(RAIS_applicationSpecificMethods.getEntityCreationDetails());
		//listOfStrings.addAll(RAIS_applicationSpecificMethods.getEntityCreationDetails().toString());

		//entityData = RAIS_applicationSpecificMethods.getEntityCreationDetails();
		//entityData = Arrays.asList(RAIS_applicationSpecificMethods.getEntityCreationDetails());

		int entityCount = RAIS_applicationSpecificMethods.getEntityCreationDetails().size();

		System.out.println(entityCount);

		//		for(int arl = 0; arl<entityData.size();arl++) {
		//			
		System.out.println(entityData.get(0).toString());
		//		}


		for( entityCount = 0; entityCount <RAIS_applicationSpecificMethods.getEntityCreationDetails().size(); entityCount++) {

			//System.out.println(RAIS_applicationSpecificMethods.getEntityCreationDetails().get(0).toString());
			//System.out.println(Arrays.toString(entityData.get(4).toString()));
			//System.out.println(entityData.get(6).toString());
			System.out.println(entityData.size());



			//ArrayList<Object> entityRecord = ArrayList<Object>(entityData.get(0).toString());

			//ent= entityData;

			for(int i = 0; i<entityData.size();i++) {

				System.out.println(entityData.get(0).toString());
				String str[] = new String[entityData.size()]; 

				// ArrayList to Array Conversion 
				for (int j = 0; j < entityData.size(); j++) { 

					// Assign each value to String array 
					str[j] = entityData.get(j).toString(); 
					System.out.println(j);


				}

			}

			System.out.println(RAIS_applicationSpecificMethods.getEntityCreationDetails().get(0).toString());

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("BE creation","BE creation");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, 
					RaisTestData.CustomizeSubMenu,RaisTestData.businessEntityList[66]);



			//		Boolean TestData_primUserEntity = RAIS_applicationSpecificMethods.createEntity(wd, entityInternalName, singLabelName, pluralLabelName, 
			//				commonGroup, inventoryRole, MainMenuInv_Res, subMenu,addMode,menuEntities);

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
		}
	}

	//#ANKUR Data
	@Test(priority=2,enabled=false)
	public void AnkurTestData_Entity_AttributeCreation(){

		//Setting entity names
		String internalName = "UnsealedSourceStatus";
		String DescripTxt = "Unsealed Source Status";
		String singularName = "Unsealed Source Status";
		String pluralName = "Unsealed Source Status";
		String GroupName = "Common"; //Facilities and authorities   Common
		String EntityRole = "Master"; //Inventory	Master
		String PublisInNav1 = "Administration"; //Administration		Inventory & Resources Regulatory Processes
		String PublisInNav2 = "Common Tables"; //Inventory		Common Tables Authorization

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc(E2E_TestPack.e2eTC2_testName,E2E_TestPack.e2eTC2_testDescription);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, "Superadmin", loginPage.pwd_XPath, 
					"Pass123$", loginPage.loginBtn_XPath);

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

			//				//********************************Add new Entity starts here
			//
			//wait for page load
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, entListingPage.addNewEntityBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//clicking on entity - save button
			GenericMethods.waitforElement(wd, addEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, DescripTxt);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_grpDropDown_XPath, GroupName);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_roleDropDown_XPath, EntityRole);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi1DropDown_XPath, PublisInNav1);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,addEntityPage.addNewEntity_pubNavi2DropDown_XPath, PublisInNav2);

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
					entListingPage.entityListingTableColHeader_TXT_XPath,singularName );

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , singularName);

			//wait for page load
			GenericMethods.pageLoadWait(2000);

			//				//Waiting until element to load
			//				GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			//				GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//				GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);

			//Clicking on attribute link on left pane
			RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.Attb_Text);


			GenericMethods.pageLoadWait(2000);
			GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);


			//RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Checkbox", "Facility");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Lookup", "FacilityStatus");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Text", "LegalPerson");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Multi lookup", "Practice");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Lookup", "Region");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Lookup", "District");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Memo", "Address");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Numeric", "Phone");
			RAIS_applicationSpecificMethods.CreateBusinessattributeData(wd, "Numeric", "Fax");

			//				
			//				//Loop to create attributes for all types
			//				for (int i = 0; i <= 16; i++) { 
			//					
			//					//Temp code insert here
			//					//Waiting until element to load
			//					//GenericMethods.elementInvisible(wd, addEntityPage.waitForLoader_XPath);
			//					
			//					//**********************
			////					//				
			////					//wait for page load
			////					GenericMethods.pageLoadWait(1000);
			////					
			////					//Waiting until element to load
			////					GenericMethods.waitforElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);	
			////					GenericMethods.elementClickable(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			////					GenericMethods.waitforvisibilityOfElement(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			////					
			//					
			//					//*************************
			//					//Add data for check box
			//					
			//					//wait for page load
			//					GenericMethods.pageLoadWait(2000);
			//					GenericMethods.elementClick(wd, addEntityPage.attributeListingPage_AddnewAttbBtn_XPath);
			//					  
			//					if (addAttbt.attributeValueList[i] != "User") {
			//					
			//					//Wait for page load
			//					GenericMethods.pageLoadWait(600);
			//					
			//					//***Attribute creation method
			//					RAIS_applicationSpecificMethods.attributeDataInput(wd, addAttbt.attributeValueList[i]);
			//					
			//					} else {
			//						
			//						//Waiting until element to load
			//						//GenericMethods.elementInvisible(wd, addEntityPage.waitForLoader_XPath);
			//						
			//						//Waiting until element to load
			////						GenericMethods.waitforElement(wd, addAttbt.cancelBtn_XPath);	
			////						GenericMethods.elementClickable(wd, addAttbt.cancelBtn_XPath);
			////						GenericMethods.waitforvisibilityOfElement(wd, addAttbt.cancelBtn_XPath);
			//
			//						//wait for page load
			//						GenericMethods.pageLoadWait(500);
			//						GenericMethods.elementClick(wd, addAttbt.cancelBtn_XPath);
			//						//GenericMethods.pageLoadWait(300);
			//						
			//						//wait for page load
			//						GenericMethods.pageLoadWait(500);
			//					}
			//									 
			//		        } 
			////				
			////				//wait for page load
			//				GenericMethods.pageLoadWait(3000);
			//
			//				//Clicking on attribute link on left pane
			//				RAIS_applicationSpecificMethods.roleSelect_Click(wd,addEntityPage.attributeLink_XPath , RaisTestData.LinkedForms_Text);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				
			//				//Clicking on specific form - main form
			//				RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , "New Form");
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				System.out.println("Form opened");
			//				
			//				GenericMethods.pageLoadWait(500);
			//				GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);
			//				
			//				GenericMethods.pageLoadWait(500);
			//				GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, "TestSection");
			//										
			//				
			//				GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//							
			//				GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);
			//				
			//				System.out.println("3 sections created");
			//				
			//				///Adding colproperties on first section
			//				
			//				GenericMethods.pageLoadWait(500);
			//				
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.colProp1_Temp_XPath);
			//							
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_ChkBox_dropdown_XPath);
			//				
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.selectAttrib_popPageSaveBtn_XPath);
			//				GenericMethods.pageLoadWait(500);
			//				
			//				//***************End of adding col prop
			//				
			//				///Adding colproperties on 2nd section
			//				
			//				GenericMethods.pageLoadWait(500);
			//				
			//				//Clicking on column prop 1
			//				GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			//				
			//				RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			//				
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

			//				//***************End of adding col prop
			//				
			//				GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");
			//				
			//				GenericMethods.pageLoadWait(500);
			//				GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			//				
			//				//Clicking on Element
			//				GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			//				GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);
			//				
			//				//wait for page load
			//				GenericMethods.pageLoadWait(1000);
			//				GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);
			//
			//				//waiting for link to load and then click
			//				GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			//				GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);
			//
			//				//Clicking on Element
			//				GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);
			//				
			//				
			//System.out.println("Entity created");

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






	//*********************************Starts from here 3-Nov-2020

	//users creation for licensee
	@Test(priority=66,enabled=true)
	public void licenseeUsersCreation(){

		try {
			//Thread.sleep(3000);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("user creation","user creation");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
//			RAIS_applicationSpecificMethods.genericMenuItemClick(wd, RaisTestData.AdministrationMainMenu,RaisTestData.UserMgmtSubMenuText, 
//					RaisTestData.businessEntityList[64]);
			
			RAIS_applicationSpecificMethods.genericMenuItemClick(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					RaisTestData.businessEntityList[64]);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);				

			ArrayList<String> wfName = RaisTestData.workflowNames;

			for(int i=0; i<wfName.size();i++) {

				String userId = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(wfName.get(i).toString()).get(0).toString();
				String fac = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(wfName.get(i).toString()).get(1).toString();
				String dept = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(wfName.get(i).toString()).get(2).toString();
				String uName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(wfName.get(i).toString()).get(8).toString();
				String uEmail = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(wfName.get(i).toString()).get(9).toString();

				System.out.println(wfName.get(i).toString());
				System.out.println(userId);
				System.out.println(fac);
				System.out.println(dept);
				System.out.println(uName);
				System.out.println(uEmail);

				GenericMethods.elementClick(wd, UserListingPage.addNewUserBtn_XPath);

				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				GenericMethods.elementClick(wd, "//*[@id='user']//div//span[contains(text(),'Worker')]");		

				GenericMethods .JSPageWait(wd);

				GenericMethods.sendText_removeblank(wd, "//input[@id='email']", uEmail);/////////////////////////////////////////////

				GenericMethods .JSPageWait(wd);

				GenericMethods.elementClick(wd, "//*[@id='user']//div//button[text()='Next']");

				GenericMethods .JSPageWait(wd);
				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='Facility']",
						fac);//////////////////////////////////////////////////////////////////////////////////////////

				GenericMethods .JSPageWait(wd);

				GenericMethods.sendText(wd, "//input[@id='Name']", 
						uName);////////////////////////////////////////////////////////////////////////////////////////////////

				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='Gender']",
						"Male");

				GenericMethods.elementClick(wd, "//*[@id='user']/div/div[2]/div/div/div[3]/div/div[2]/div[1]/div[2]/div");

				GenericMethods .JSPageWait(wd);
				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='Facility']",
						fac);///////////////////////////////////////////////////////////////////////////////////////////

				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='Department']",
						dept);///////////////////////////////////////////////////////////////////////////////////////////////

				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='WorkerStatus']",
						"Active in the Facility"); 

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//form//div//fieldset//div//select[@id='MonitoringStatus']",
						"Monitored");

				GenericMethods .JSPageWait(wd);				

				RAIS_applicationSpecificMethods.scrollToElement_Click(wd, "//*[@id='user']/div/div[2]/div/div/div[3]/div/div[2]/div[1]/div[2]/div/button[2]");
				//GenericMethods.elementClick(wd, "//*[@id='user']//div//button[text()='Save']");

				GenericMethods .JSPageWait(wd);
				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//select[@id='authentication']","External");

				RAIS_applicationSpecificMethods.multiSelect_UserCreationFR(wd, "//*[@id='user']//div//fieldset//div", 
						"Licensee");

				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, "//*[@id='user']//div//select[@id='status']",
						"Activate");

				GenericMethods.sendText(wd, "//input[@id='externalUsername']", 
						userId);////////////////////////////////////////////////////////////////////////////////////////////////////////

				GenericMethods.tabfromElement(wd, "//input[@id='externalUsername']");
				GenericMethods .JSPageWait(wd);

				RAIS_applicationSpecificMethods.scrollToTop(wd);

				GenericMethods.elementClick(wd, "//*[@id='user']//div//button[text()='Finish']");

				GenericMethods .JSPageWait(wd);
				GenericMethods .JSPageWait(wd);

				GenericMethods.elementClick(wd, "//*[@id='user']//div//button[text()='Cancel']");

				GenericMethods .JSPageWait(wd);
				GenericMethods .JSPageWait(wd);

			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Logout user
		RAIS_applicationSpecificMethods.logout(wd);		


	}


	//users creation for licensee
	@Test(priority=66,enabled=false)
	public void regulator_RARUsersCreation(){

		try {
			//Thread.sleep(3000);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("user creation","user creation");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.genericMenuItemClick(wd, RaisTestData.AdministrationMainMenu,RaisTestData.UserMgmtSubMenuText, 
					RaisTestData.businessEntityList[64]);

			//calling regulator user creation method
			//RAIS_applicationSpecificMethods.createRegRarUser(wd, "Expert");			

			//calling regulator user creation method
			RAIS_applicationSpecificMethods.createRegRarUser(wd, "Officer");		


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Logout user
		RAIS_applicationSpecificMethods.logout(wd);		


	}


	//latest with excel read and multi arraylist
	@Test(priority=99,enabled=false)
	public void businessEntityCreation2() {

		try {
			//Setting Test name and description on report
			SettingRptTestName_TestDesc("BE creation","BE creation");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on entities menu
			RAIS_applicationSpecificMethods.genericMenuItemClick(wd, RaisTestData.AdministrationMainMenu, 
					RaisTestData.customizePowerToolSubMenu,RaisTestData.businessEntityList[66]);

			//Read excel sheet containing entity list and returining it as record set in arraylist
			ArrayList<ArrayList<Object> > entityRecordSet = new ArrayList<ArrayList<Object> >();			
			entityRecordSet= RAIS_applicationSpecificMethods.getEntityCreationDetails();

			for (int i = 1; i<entityRecordSet.size();i++) {

				System.out.println("--------------------------------");
				System.out.println("Internal Name - "+entityRecordSet.get(i).get(0));
				System.out.println("Description - "+entityRecordSet.get(i).get(1));
				System.out.println("Singular Name - "+entityRecordSet.get(i).get(2));
				System.out.println("Plural Name - "+entityRecordSet.get(i).get(3));
				System.out.println("Entity Group Name - "+entityRecordSet.get(i).get(4));
				System.out.println("Entity Role Name - "+entityRecordSet.get(i).get(5));
				System.out.println("Publish and navigation Menu Name - "+entityRecordSet.get(i).get(6));
				System.out.println("Publish and navigation Sub-menu name - "+entityRecordSet.get(i).get(7));
				System.out.println("Enable WF status - "+entityRecordSet.get(i).get(8));
				System.out.println("Enable RAN status - "+entityRecordSet.get(i).get(9));

				ArrayList<Object> entityDetail = new ArrayList<Object>();

				entityDetail.add(0, entityRecordSet.get(i).get(0));
				entityDetail.add(1, entityRecordSet.get(i).get(1));
				entityDetail.add(2, entityRecordSet.get(i).get(2));
				entityDetail.add(3, entityRecordSet.get(i).get(3));
				entityDetail.add(4, entityRecordSet.get(i).get(4));
				entityDetail.add(5, entityRecordSet.get(i).get(5));
				entityDetail.add(6, entityRecordSet.get(i).get(6));
				entityDetail.add(7, entityRecordSet.get(i).get(7));
				entityDetail.add(8, entityRecordSet.get(i).get(8)); //from col 10
				entityDetail.add(9, entityRecordSet.get(i).get(9)); //from col 11

				System.out.println(entityDetail);				

				//Entity creation code below****************************************************************	

				Boolean TestData_primUserEntity = RAIS_applicationSpecificMethods.addEditBusinessEntity(wd, entityDetail, RaisTestData.ADD_MODE_TEXT);

				if (TestData_primUserEntity == true) {

					//print value of entity created
					System.out.println(entityRecordSet.get(i).get(2).toString() + " Entity creation successful");

				} else {

					System.out.println(entityRecordSet.get(i).get(2) + " creation failed");
				}

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

				//Entity creation code ENDS****************************************************************				
			}


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//entityDetails = multipleEntities;		

	}

	//setting Functional role permissions as part of data setup
	//#6Default DR permissions/ restriction 
	@Test(priority=17,enabled=false)
	public void setFRLicensee_Reg_RAR_TestData() {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("Setup Default FR for all 3 roles","Verify user is able to setup Default FR for all 3 roles");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//Clicking on entities menu
//			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
//					RaisTestData.functionalRole);
			
			RAIS_applicationSpecificMethods.genericMenuItemClick(wd, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					RaisTestData.functionalRole);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);
			
			System.out.println(RaisTestData.allRoleNames.length);
			
			
			for(int i =0; i<RaisTestData.allRoleNames.length; i++) {
				
				//clicking on left data role
				RAIS_applicationSpecificMethods.DRFR_Edit_Delete(wd, RaisTestData.allRoleNames[i], RaisTestData.OPTIONAL_TEXT,
						RaisTestData.OPTIONAL_TEXT);

				//clicking on permissions tab
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.dataRolePermTab_XPath);
	
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//click on new permission button
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FunctionalRolePermissionRestrictBtn_XPath);
				
				//select entity group from dropdown
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd,"//*[@id='nav-tabpanel-0']//div//form//div//select[@id='entityGroupId']", 
						"Enforcement");
				
				//select newly added facility from list
				RAIS_applicationSpecificMethods.multiSelect_DR_FR_Page(wd, "All","Add New Permission" );
				
				GenericMethods.JSPageWait(wd);
				
				//checkbox
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Add')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Edit')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Delete')]");
				
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.savePermRestrict_XPath);
				
				GenericMethods.JSPageWait(wd);
				
				RAIS_applicationSpecificMethods.clickTextOnGrid(wd, "Boolean");
				
				GenericMethods.JSPageWait(wd);
				
				//checkbox
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Add')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Edit')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Delete')]");
				
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.savePermRestrict_XPath);
				
				GenericMethods.JSPageWait(wd);
				
				RAIS_applicationSpecificMethods.clickTextOnGrid(wd, "Department");
				
				GenericMethods.JSPageWait(wd);
				
				//checkbox
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Add')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Edit')]");
				
				GenericMethods.elementClick(wd, "//*[@id='nav-tabpanel-0']//div//form//div//label//span[contains(text(),'Delete')]");
				
				GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.savePermRestrict_XPath);
				
				GenericMethods.JSPageWait(wd);
			
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
