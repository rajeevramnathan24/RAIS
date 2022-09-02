package testcases.RAIS;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testSuite.*;

import commonfunction.BaseClass;

import commonfunction.GenericMethods;
import commonfunction.RAIS_applicationSpecificMethods;
import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewEntityFormDetailsPage;
import pageLocators_Elements.RAIS.AddNewEntityPage;
import pageLocators_Elements.RAIS.AddNewPermRestrictionsPage;
import pageLocators_Elements.RAIS.AddNewRolePage;
import pageLocators_Elements.RAIS.AddNewSecurityProfilePage;
import pageLocators_Elements.RAIS.AddNewUserDetailsPage;
import pageLocators_Elements.RAIS.DashboardPage;
import pageLocators_Elements.RAIS.DataRoles_FunctionalRolesPage;
import pageLocators_Elements.RAIS.EntityFormListingPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.Workflow_FAPage;
import pageLocators_Elements.RAIS.ForgotPasswordPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class WF_FollowupAction_Tests extends BaseClass
{		
	//Instantiating individual pages
	LoginPage loginPage = new LoginPage();
	EntityFormListingPage entityRecordListing = new EntityFormListingPage();
	
	Workflow_FAPage FA_WFPage = new Workflow_FAPage();
	
	TestSuite RunTestCase = new TestSuite();

	//passcurrent time
	//private static String localTime = GenericMethods.currentLocalTime();
	public static String packType = "Followup-Action-Test-";

	//Initializing total test cases, and counting pass/ fail count
	public static Integer TotalTc_Ch=0;
	public static Integer PassTc_Ch=0;
	public static Integer FailTc_Ch=0;
	public static Integer TotalTc_FF=0;
	public static Integer PassTc_FF=0;
	public static Integer FailTc_FF=0;
	public static String browserCategory = "";	
	public static String TESTSTATUS = null;
	
	//Common Test Data***************NoCHANGE REQUIRED*********************************
	String regulatoryProcessMainMenu = RaisTestData.RegProcessText;
	String authorization = RaisTestData.AuthorizationSubmenu;	
	String faWorkFlowName = null;	
	String licenseeRole = null; //LICIMP
	String regulatorRole = "REG";
	//String pwd = "Pass123$";
	//********************************CHANGE REQUIRED*****************************
	String ParentWorkFlowName = RaisTestData.exportAuthWF;
	String WFid_FAtobeExecuted = "EXP/0001/WF"; //EM/0001/WF Equip facility1		IMP/0003/WF		TRP/0001/WF Transp facility2	IP/0001/WF  TRF/0001/WF EXP/0001/WF
	//*********************************************************************
	
	//DF1 test data
	String [] fwActionDataFormInput = RaisTestData.faDataFormInput;
	String [] statusOfWorkflow = RaisTestData.workflowStatuses; 
	
	//*********************************************************************			
	String RemarksTxt = "This step has been is Reviewed/ approved.";
	String remarksUpdated= "Updated text";
	String ApprovedByName = "Vishal";
	//*********************************************************************

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

	//#F1 - Import
	@Test(priority=1,enabled=true)
	public void fa_HappyPath() throws Exception {

		try {

			//Setting Test name and description on report
			SettingRptTestName_TestDesc("Followp Action Test case","Followp Action Test case");			
			
			//Call the method to set user and followup workflow name
			licenseeRole = RAIS_applicationSpecificMethods.getUserFollowupWFName(ParentWorkFlowName).get(0).toString();
			faWorkFlowName = RAIS_applicationSpecificMethods.getUserFollowupWFName(ParentWorkFlowName).get(1).toString();
			
			System.out.println(licenseeRole);
			System.out.println(faWorkFlowName);				

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on parent Auth WF menu and name
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization,ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);
			
			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, WFid_FAtobeExecuted);
			
			//verify workflow statuses
			//RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);
			
			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);
			
			//Initiate Workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, faWorkFlowName);
			
			//page wait
			GenericMethods .JSPageWait(wd);
			
			//extract workflowid
			final Object workflowId = RAIS_applicationSpecificMethods.trimWorkflowid(wd, FA_WFPage.wfId_displayedonTop);			
			System.out.println(workflowId);
			
			
			
			
			
			//Verify draft status and proceed - commenting it for now so that bug is fixed
			//*************************************
			
			//Clicking on followup action menu
			//RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, followUpActionWFName[0]);
			
			//Waiting for popup to load
			//GenericMethods .JSPageWait(wd);
			
			//search for required record
			//RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
			
			//verify workflow statuses
			//RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[0], true);			
			
			
			
			
			
			//*************************Starting with first data form			
			RAIS_applicationSpecificMethods.followUpActionDataForm(wd, faWorkFlowName, fwActionDataFormInput, 
					FA_WFPage.submitBtn_FAPage1_Xpath);			
			
			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, faWorkFlowName);
			
			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);
			
			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
			
			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[1], true);
			
			//*************************Starting with Approval data form
			//calling approval form method
			RAIS_applicationSpecificMethods.followUpActionApprovalDataForm(wd, RemarksTxt, ApprovedByName, FA_WFPage.approveBtn_Xpath);

			//page refresh
			wd.navigate().refresh();
			
			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);
			
			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization,faWorkFlowName);
			
			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);
			
			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
			
			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, statusOfWorkflow[2], false);			

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

	//#F2 - Import
	@Test(priority=2,enabled=true)
	public void fa_AlternatePaths() throws Exception {

			try {								

				//Setting Test name and description on report
				SettingRptTestName_TestDesc("Followp Action Test case","Followp Action Test case");
				
				//Call the method to set user and followup workflow name
				licenseeRole = RAIS_applicationSpecificMethods.getUserFollowupWFName(ParentWorkFlowName).get(0).toString();
				faWorkFlowName = RAIS_applicationSpecificMethods.getUserFollowupWFName(ParentWorkFlowName).get(1).toString();
				
				System.out.println(licenseeRole);
				System.out.println(faWorkFlowName);	

				//Calling Login method
				GenericMethods.loginApplication
				(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
						password, loginPage.loginBtn_XPath);

				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				//Clicking on parent Auth WF menu and name
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization,ParentWorkFlowName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, WFid_FAtobeExecuted);
				
				//verify workflow statuses
				//RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);
				
				//click on the record
				GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);
				
				//Initiate Workflow
				RAIS_applicationSpecificMethods.initiateWorkflow(wd, faWorkFlowName);
				
				//page wait
				GenericMethods .JSPageWait(wd);
				
				//extract workflowid
				final Object workflowId = RAIS_applicationSpecificMethods.trimWorkflowid(wd, FA_WFPage.wfId_displayedonTop);			
				System.out.println(workflowId);
				
				//*************************Starting with first data form			
				RAIS_applicationSpecificMethods.followUpActionDataForm(wd, faWorkFlowName, fwActionDataFormInput, 
						FA_WFPage.submitBtn_FAPage1_Xpath);			
				
				//logout and relogin with new user
				RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, faWorkFlowName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
				
				//verify workflow statuses
				RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[1], true);
				
				//*************************Starting with Approval data form
				//calling approval form method
				RAIS_applicationSpecificMethods.followUpActionApprovalDataForm(wd, RemarksTxt, ApprovedByName, FA_WFPage.rejectBtn_Xpath);

				//page refresh
				wd.navigate().refresh();
								
				//logout and relogin with new user
				RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,licenseeRole,password);
								
				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, faWorkFlowName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
				
				//verify workflow statuses
				RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[3], true);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//direct click on submit button
				GenericMethods.elementClick(wd, FA_WFPage.submitBtn_FAPage1_Xpath);
				
				//logout and relogin with new user
				RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, faWorkFlowName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
				
				//verify workflow statuses
				RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[1], true);
				
				//*************************Starting with Approval data form
				//calling approval form method
				RAIS_applicationSpecificMethods.followUpActionApprovalDataForm(wd, remarksUpdated, ApprovedByName, FA_WFPage.approveBtn_Xpath);
				
				//page refresh
				wd.navigate().refresh();
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
								
				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization,faWorkFlowName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, workflowId);
				
				//verify workflow statuses
				RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,statusOfWorkflow[2], true);		

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

	
	
	//#F1
	@Test(priority=2,enabled=false)
	public void DemoWF_Test() throws Exception {

			try {
				
				Thread.sleep(6000);
				
				//Test Data
				
				
				
				String regulatoryProcessMainMenu = RaisTestData.RegProcessText;
				String authorization = RaisTestData.AuthorizationSubmenu;
				String ParentWorkFlowName = RaisTestData.importAuthWF;
				String FA_WFName = "FollowUpAction Import Workflow";
				
				String licenseeRole = "LICImp";
				String regulatorRole = "REGVP";
				String pwd = "Pass123$";
				//*********************************************************************
				String WFid_FAtobeExecuted = "IMP/0003/WF Import Facility 1 Execution";
				//*********************************************************************
				
				//DF1 test data
				String sourceLocation = "Import Facility 1 Execution";
				String sourceStatus = "In-Process";
				String genericDate = "08/10/2020";
				String securityPlanDoc_Path = "C:\\Temp\\securityPlan.pdf";
				String customsNumber = "455500P231/22";
				String bill_LadingNum = "455500P231/223434";
				String RemarksTxt = "This Followup action has been approved by Regulator role.";
				String ApprovedByName = "Vishal";
				
				

				//Setting Test name and description on report
				SettingRptTestName_TestDesc("Followp Action Test case","Followp Action Test case");

				//Calling Login method
				GenericMethods.loginApplication
				(wd, loginPage.userId_XPath, "LICEQP", loginPage.pwd_XPath, 
						pwd, loginPage.loginBtn_XPath);

				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, "Equipment Manufacturing Workflow");
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, "EM/0002/WF");
				
				//click on first record
				//GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);
				
				GenericMethods .JSPageWait(wd);
				
				//click on department multilookup
				
				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div");
//				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[1]/div/div/div/div/div/fieldset/div/div[1]");
				
				GenericMethods .JSPageWait(wd);
				
				GenericMethods.sendText(wd, "//input[@id='filter']", "Equip Dept2");
//				GenericMethods.sendText(wd, "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[1]/div/div/div/div/div/fieldset/div/div[2]/div/div/input[@id='filter']"
//						, "Equip Dept2");
				
//				GenericMethods.JClickonElement(wd, "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[1]/div/div/div/div/div/fieldset/div/div[2]/div/div");
//				
//				RAIS_applicationSpecificMethods.rbSendText(wd,  "Equip Dept2");
				
				
				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div//ul/li[1]/label");
				
				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div");
				
				//GenericMethods.sendText_removeblank(wc, clickElement, valueToSend);
				
				//GenericMethods.JClickonElement(wj, clickElement);
				
				JavascriptExecutor jse = (JavascriptExecutor) wd;
				jse.executeScript("arguments[0].scrollIntoView(true);", wd.findElement(By.xpath("//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div")));
				
				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div");
				
				GenericMethods .JSPageWait(wd);
				
				GenericMethods.sendText(wd, "//input[@id='filter']", "Equip facility2 - Sealed source");
				
				
				
				
				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.sourceLocation_Xpath, sourceLocation);
				
				//input source status
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.sourceStatus_Xpath, sourceStatus);
				
				//input source date
				GenericMethods.sendText(wd, FA_WFPage.sourceStatusDate_Xpath, genericDate);
				
				//input import date
				GenericMethods.sendText(wd, FA_WFPage.importDate_Xpath, genericDate);
				
				//upload security plan doc
				RAIS_applicationSpecificMethods.clickAndUploadFile(wd, FA_WFPage.securityPlan_Xpath,securityPlanDoc_Path, null);
				
				//input customs number
				GenericMethods.sendText(wd, FA_WFPage.importCustomNum_Xpath, customsNumber);
				
				//input Bills of number
				GenericMethods.sendText(wd, FA_WFPage.importBillLadingNum_XPath, bill_LadingNum);
				
				//input bill lading date
				GenericMethods.sendText(wd, FA_WFPage.importBillLadingdate_XPath, genericDate);
				
				//click on submit button
				GenericMethods.elementClick(wd, FA_WFPage.submitBtn_FAPage1_Xpath);
				
				//Waiting for popup to load
				//GenericMethods .JSPageWait(wd);
				//waiting for success message
				GenericMethods.waitforElement(wd, FA_WFPage.SuccessMsg_XPath);
				GenericMethods.elementVisible(wd, FA_WFPage.SuccessMsg_XPath);
				
				//verify success message
				String submitSuccessMsg = GenericMethods.getActualTxt(wd, FA_WFPage.SuccessMsg_XPath);
				Assert.assertEquals(submitSuccessMsg,FA_WFPage.successfulMsg_Txt);
				
				//Logout licensee user
				RAIS_applicationSpecificMethods.logout(wd);
				System.out.println("Logout success");
				
				//login using Regulator
				GenericMethods.loginApplication
				(wd, loginPage.userId_XPath, regulatorRole, loginPage.pwd_XPath, 
						pwd, loginPage.loginBtn_XPath);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, FA_WFName);
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, "workflowId");
				
				//verify workflow statuses
				String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
				Assert.assertEquals(wfStatus_Submit,RaisTestData.WF_Status_Submitted_Txt);
							
				//click on first record
				GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);
				
				//Waiting for popup to load
				GenericMethods.JSPageWait(wd);
				
				//input approval status date
				GenericMethods.sendText(wd, FA_WFPage.approvalFrmStatusDate_Xpath, genericDate);
				
				//input remarks on approval form
				GenericMethods.sendText(wd, FA_WFPage.approvalFrmRemarks_Xpath, RemarksTxt);
				
				//select approved by role by regulator
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.approvalFrmApprovedBy_Xpath, ApprovedByName);
				
				//scroll to bottom
				RAIS_applicationSpecificMethods.jScrollToBottom(wd);
				
				//click on submit button
				GenericMethods.elementClick(wd, FA_WFPage.approveBtn_Xpath);
				
				//Waiting for popup to load
				//GenericMethods .JSPageWait(wd);
				//waiting for success message
				GenericMethods.waitforElement(wd, FA_WFPage.SuccessMsg_XPath);
				GenericMethods.elementVisible(wd, FA_WFPage.SuccessMsg_XPath);
				
				//verify success message
				String approveSuccessMsg = GenericMethods.getActualTxt(wd, FA_WFPage.SuccessMsg_XPath);
				Assert.assertEquals(approveSuccessMsg,FA_WFPage.successfulMsg_Txt);
				
				//page refresh
				wd.navigate().refresh();
				
				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				//Clicking on followup action menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization,FA_WFName);
				
				//search for required record
				RAIS_applicationSpecificMethods.basicSearchRecord(wd, "workflowId");
				
				//verify workflow statuses
				String wfStatus_Complete = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
				Assert.assertEquals(wfStatus_Complete,RaisTestData.WF_Status_Completed_Txt);
																	

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

	

	
	//Pending to test
	@Test(enabled=false)
	public void testPending() {
		
		//Calling Login method
				GenericMethods.loginApplication
				(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
						password, loginPage.loginBtn_XPath);

				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);

				//Clicking on entities menu
				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CommonTablesSubMenu, 
						RaisTestData.businessEntityList[65]);

				//Waiting for popup to load
				GenericMethods .JSPageWait(wd);
				
				//click on add new
				GenericMethods.elementClick(wd, entityRecordListing.addNewBtn_XPath);
				
				//click on organigram link
//				GenericMethods.elementClick(wd, 
//						"//*[@id='entity-form']/div/div/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a");
				
				RAIS_applicationSpecificMethods.clickAndUploadFile(wd, "//*[@id='entity-form']/div/div/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a",
						"C:\\Temp\\Demo1.pdf", null);
		
				 System.out.println("Upload successful");
				
				
		
		
		
		//System.out.println(RAIS_applicationSpecificMethods.trimWorkflowid(" Initiated Manually - IMP/0002/WF - Import Facility1"));
		
		//System.out.println(RAIS_applicationSpecificMethods.trimWorkflowid(" Initiated Manually - IMP/FA/0001/WF - IMP/0011/WF"));
		
		String string = " Initiated Manually - IMP/0002/WF - Import Facility1";
        string = string.replace(" Initiated Manually - ", "");
        string = string.replace("- ", "");
        System.out.println(string);
        
        String string1 = " Initiated Manually - IMP/FA/0001/WF - IMP/0011/WF";
        string1 = string1.replace(" Initiated Manually - ", "");
        string1 = string1.replace("- ", "");
        System.out.println(string1);
		
		
		//Calling Login method
		GenericMethods.loginApplication
		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
				password, loginPage.loginBtn_XPath);

		//Waiting for popup to load
		GenericMethods .JSPageWait(wd);

		//Clicking on entities menu
		RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, "Authorizations", 
				"Import Authorization Workflow");

		//Waiting for popup to load
		GenericMethods .JSPageWait(wd);
		
		RAIS_applicationSpecificMethods.basicSearchRecord(wd, "IMP/0011/WF Import Facility 1 Execution");
		
		//Waiting for popup to load
		GenericMethods .JSPageWait(wd);
				
		System.out.println(GenericMethods.getActualTxt(wd, "//*[@id='entity-details']//div//table//tbody//tr[1]/td[2]"));
		
		System.out.println(GenericMethods.getActualTxt(wd, "//*[@id='entity-details']//div//table//tbody//tr[1]/td[3]"));
		
		RAIS_applicationSpecificMethods.initiateWorkflow(wd, "FollowUpAction Import Workflow");
		
		//Waiting for popup to load
		GenericMethods .JSPageWait(wd);

		System.out.println();
		
		
		
		
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
