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

public class E2ETests extends BaseClass
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

	//#2
	@Test(priority=2,enabled=E2E_TestPack.e2eTC2_runStatus)
	public void E2E_FormDesigner(){

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
			GenericMethods.elementClickable(wd, addEntityPage.SaveBtn_XPath);

			//input entity internal name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_internalNameTxtBox_XPath, RaisTestData.Entity_FormBuilder_InternalNameData);

			//input entity Description name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_DescTxtBox_XPath, RaisTestData.Entity_DescriptionData);

			//input entity Singular name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_SingTxtBox_XPath, RaisTestData.Entity_FormBuilder_SingularData);

			//input entity Plural name
			GenericMethods.sendText(wd, addEntityPage.addNewEntity_PluTxtBox_XPath, RaisTestData.Entity_FormBuilder__PluralData);

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
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_FormBuilder_SingularData );

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , RaisTestData.Entity_FormBuilder_SingularData);

			//wait for page load
			GenericMethods.pageLoadWait(6000);
			
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
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , "New Form");
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			System.out.println("Form opened");
			
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.addSectionBtn_XPath);
			
			GenericMethods.pageLoadWait(500);
			GenericMethods.sendText(wd, frmDesign.addSectionPopUp_title_XPath, "TestSection");
									
			
			GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton2_XPath);
			//GenericMethods.elementClick(wd, frmDesign.addSectionColSelectnewbutton3_XPath);
			
			GenericMethods.elementClick(wd, frmDesign.addSectionSaveBtn_XPath);
			
			System.out.println("3 sections created");
			
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
			
			//Clicking on column prop 1
			GenericMethods.elementClick(wd, frmDesign.colProp2_Temp_XPath);	
			
			RAIS_applicationSpecificMethods.multiSelectList(wd, frmDesign.frmDesgn_DropdnClick_Xpath, frmDesign.selectAttrib_Text_dropdown_XPath);
			
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
			
			GenericMethods.sendText(wd, frmDesign.mainFrmPage_TitleTxtbox_XPath, "TestformTitle");
			
			GenericMethods.pageLoadWait(500);
			GenericMethods.elementClick(wd, frmDesign.mainFrmPage_SaveBtn_XPath);
			
			//Clicking on Element
			GenericMethods.waitforElement(wd, dashboardnew.invent_Resources_XPath);
			GenericMethods.elementClickable(wd, dashboardnew.invent_Resources_XPath);
			
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, dashboardnew.invent_Resources_XPath);

			//waiting for link to load and then click
			GenericMethods.elementClickable(wd, dashboardnew.newEntityCreated_XPath);
			GenericMethods.waitforElement(wd, dashboardnew.newEntityCreated_XPath);

			//Clicking on Element
			GenericMethods.elementClick(wd, dashboardnew.newEntityCreated_XPath);
			
			
			
System.out.println("waiting newly created entity page to load");

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();
		}

		finally {
			
			
			//Delete entity
///Delete entity starts below
			
			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Column header filter starts here
			RAIS_applicationSpecificMethods.columnHeaderFilter(wd,entListingPage.entityListingTableColHeader_XPath,
					entListingPage.entityListingTableColHeader_TXT_XPath,RaisTestData.Entity_FormBuilder_SingularData );

			//wait for page load
			GenericMethods.pageLoadWait(500);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wd,entListingPage.entityListingTable_XPath , RaisTestData.Entity_FormBuilder_SingularData);

			//wait for page load
			GenericMethods.pageLoadWait(3000);
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.elementClick(wd, addEntityPage.deleteBtn_XPath);

			//Waiting for delete popup page
			GenericMethods.elementClick(wd, addEntityPage.delEntity_popUpYesBtn_XPath);				

			System.out.println("deleted entity");
			
			//Delete entity ends here
			
			

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
