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
import pageLocators_Elements.RAIS.Workflow_Inspection_Parent_ChildPage;
import pageLocators_Elements.RAIS.Workflow_PaymentPage;
import pageLocators_Elements.RAIS.Workflow_ReviewAssessment_Page;
import pageLocators_Elements.RAIS.ForgotPasswordPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;
import pageLocators_Elements.RAIS.Workflow_AuthorizationPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class WF_Authorization_Tests extends BaseClass
{		
	//Instantiating individual pages
	LoginPage loginPage = new LoginPage();
	EntityFormListingPage entityRecordListing = new EntityFormListingPage();

	Workflow_FAPage FA_WFPage = new Workflow_FAPage();
	Workflow_AuthorizationPage wfAuthorization = new Workflow_AuthorizationPage();
	Workflow_ReviewAssessment_Page wfReview_Assessment = new Workflow_ReviewAssessment_Page();
	Workflow_Inspection_Parent_ChildPage wfInspection = new Workflow_Inspection_Parent_ChildPage();
	Workflow_PaymentPage wfPayment = new Workflow_PaymentPage();

	//workflowID
	public String wfID_tobeExecuted = null;
	String ParentWorkFlowName = null;

	//Test cases common input data
	//Test Data		
	//*********************************************************************
	String regulatoryProcessMainMenu = RaisTestData.RegProcessText;
	String authorization = RaisTestData.AuthorizationSubmenu;
	String facilityMenu = RaisTestData.businessEntityList[26];
	String inventoryMainMenu = RaisTestData.InventResourceMainMenu;
	String resourceSubMenu = RaisTestData.ResourcesSubMenuText;
	//*********************************************************************
	//DF1 test data
	String regulatorRole = RaisTestData.regulator_RAR_Data.get(0).toString();
	String RemarksTxt = RaisTestData.commonInputData.get(7).toString();
	String ApprovedByName = RaisTestData.commonInputData.get(8).toString();
	//*********************************************************************	
	//TC Name, Yes/ No
	final boolean tc1Status = true; String tc1Name = " is executed for E2E Happy path for R&A and Inspection";
	final boolean tc2Status = true; String tc2Name = " is executed for E2E Happy path only R&A";
	final boolean tc3Status = true; String tc3Name = " is executed for Rejection from Completeness Check";
	final boolean tc4Status = true; String tc4Name = " is executed for Rejection from R&A Approval form";
	final boolean tc5Status = true; String tc5Name = " is executed for Rejection of Authorization Terms";
	final boolean tc6Status = true; String tc6Name = " is executed for incomplete Application";
	final boolean tc7Status = true; String tc7Name = " is executed for Rejection of Authorization";
	
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

	public static String packType = "Workflow_Execution_";

	public static String TESTSTATUS = null;

	

	//Before class ################################
	@Parameters({"browserType","workflowName"})
	@BeforeClass
	public void beforeClass(String browserType, String workflowName) {

		try {

			//Assigning browser type to browser category
			browserCategory = browserType;

			ParentWorkFlowName = workflowName;

			//Loading config file for url, uid and password
			LoadConfigfile();

			//appending workflowname to result file
			//packType = packType+workflowName;
			packType = packType+ ParentWorkFlowName.replaceAll("\\s", "")+localTime;
			System.out.println(ParentWorkFlowName);
			System.out.println(packType);
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

	@Parameters({ "workflowName" })
	@Test(priority = 1, enabled = false)
	public void RunTC1(String workflowName) {			


		SettingRptTestName_TestDesc("test desc","test name");
		System.out.println("Running test for "+ParentWorkFlowName);

	}




	//E2E WF - working as of 23sep, This is latest	
	@Test(priority = 99, enabled = tc1Status)
	public void E2E_WorkflowExecution_HappyPathwithInspection() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc1Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			//Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, inputData_Associations_Inspection_DataForm);
			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, 
					false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.inspectionNeededBtn_Xpath);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading inspection Id
			final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);
			System.out.println(inspectionChildId);				

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.inspWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);			


			//****************************************Inspection starts here

			//**************************DF - Inspection Scope

			//RAIS_applicationSpecificMethods.inspectionScopeDataForm(wd,inputData_Associations_Inspection_DataForm);	
			RAIS_applicationSpecificMethods.inspectionScopeDataForm(wd,associationDataInput, wfInspection.withoutAnnounceInspectionBtn_Xpath);	

			//**************************DF - External Inspection requirement

			wd.navigate().refresh(); //####################################################################################Bug here

			//CHECK IF THE CODE FAILS HERE< REFER TO COMMENTED CODE BELOW

			//input data form
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd, wfInspection.internalInspectionBtn_Xpath);


			//**************************DF - Internal Inspection Findings
			//input data form
			RAIS_applicationSpecificMethods.inspectionFindingsDataForm(wd, RemarksTxt, wfInspection.proceedInspectionBtn_Xpath); 


			//**************************DF - Inspection conclusion
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfInspection.inspectionConclusion_Xpath, RemarksTxt, wfInspection.submitBtn);

			//**************************DF - Inspection approval

			//approval input data form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfInspection.downloadInspectionReport_Xpath, 
					wfInspection.inspectionApprovedBy_Xpath, ApprovedByName, 
					wfInspection.approvalRejectionNotes_Xpath, RemarksTxt, false, wfInspection.approveBtn);

			/// **************************Back to R&A form
			//click and expand linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfInspection.inspectionApprovalStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd,RaisTestData.paymentInputData.get(5).toString(),
					wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), 
					wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);

			//***********************************************Starting with acceptance form
			//input data
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RemarksTxt, false, RaisTestData.commonInputData.get(5).toString());

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.approveBtn);

			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);




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

	//E2E WF - working as of 15Oct, This is latest	
	@Test(priority = 101, enabled = tc2Status)
	public void E2E_WorkflowExecution_HappyPathwithRnA() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc2Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);

			//diversion&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


			//			
			//			
			//			
			//
			//			//clicking on step tracker to enable linked processes
			//			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
			//					wfAuthorization.linkedProcess_Xpath);
			//
			//			//reading inspection Id
			//			final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);
			//			System.out.println(inspectionChildId);				
			//
			//			//click on inspection processes direct
			//			GenericMethods.elementClick(wd, wfAuthorization.inspWFtid_Xpath);
			//
			//			//Waiting for popup to load
			//			GenericMethods.JSPageWait(wd);			
			//
			//
			//			//****************************************Inspection starts here
			//
			//			//**************************DF - Inspection Scope
			//
			//			//RAIS_applicationSpecificMethods.inspectionScopeDataForm(wd,inputData_Associations_Inspection_DataForm);	
			//			RAIS_applicationSpecificMethods.inspectionScopeDataForm(wd,associationDataInput, wfInspection.withoutAnnounceInspectionBtn_Xpath);	
			//
			//			//**************************DF - External Inspection requirement
			//
			//			wd.navigate().refresh(); //####################################################################################Bug here
			//
			//			//CHECK IF THE CODE FAILS HERE< REFER TO COMMENTED CODE BELOW
			//
			//			//input data form
			//			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd, wfInspection.internalInspectionBtn_Xpath);
			//
			//
			//			//**************************DF - Internal Inspection Findings
			//			//input data form
			//			RAIS_applicationSpecificMethods.inspectionFindingsDataForm(wd, RemarksTxt, wfInspection.proceedInspectionBtn_Xpath); 
			//
			//
			//			//**************************DF - Inspection conclusion
			//			//input data form
			//			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfInspection.inspectionConclusion_Xpath, RemarksTxt, wfInspection.submitBtn);
			//
			//			//**************************DF - Inspection approval
			//
			//			//approval input data form
			//			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfInspection.downloadInspectionReport_Xpath, 
			//					wfInspection.inspectionApprovedBy_Xpath, ApprovedByName, 
			//					wfInspection.approvalRejectionNotes_Xpath, RemarksTxt, wfInspection.approveBtn);
			//
			//			/// **************************Back to R&A form
			//			//click and expand linked processes
			//			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfInspection.inspectionApprovalStepTracker_Xpath, 
			//					wfAuthorization.linkedProcess_Xpath);
			//
			//			//click on R&A processes direct
			//			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			//
			//			//Waiting for popup to load
			//			GenericMethods.JSPageWait(wd);


			//diversion&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&







			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd,RaisTestData.paymentInputData.get(5).toString(),
					wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), 
					wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);

			//***********************************************Starting with acceptance form
			//input data
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RemarksTxt, false, RaisTestData.commonInputData.get(5).toString());

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.approveBtn);

			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);




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

	//E2E WF - working as of 15Oct, completeness check reject	
	@Test(priority = 121, enabled = tc3Status)
	public void E2E_WorkflowExecution_CompletenessCheckReject() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc3Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.rejectAuthBtn_Xpath, false, ParentWorkFlowName);


			//DIVERSION&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&




			//DIVERSION ENDS &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&






			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Rejected", false);




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

	//E2E WF - working as of 15Oct, rejection on R&A approval form
	@Test(priority = 131, enabled = tc4Status)
	public void E2E_WorkflowExecution_RnAReject() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc4Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);



			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.rejectAuthBtn);




			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Rejected", false);




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

	//E2E WF - working as of 15Oct, This is latest	
	@Test(priority = 141, enabled = tc5Status)
	public void E2E_WorkflowExecution_AuthTermsRejectApprove() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc5Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);




			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd,RaisTestData.paymentInputData.get(5).toString(),
					wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), 
					wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "", true);

			//***********************************************Starting with acceptance form ++++++++++++Decline
			//input data decline TNC
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RemarksTxt, false, RaisTestData.commonInputData.get(9).toString());

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "", true);

			//***********************************************Starting with acceptance form
			//input data decline TNC
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RaisTestData.commonInputData.get(10).toString(),
					true, RaisTestData.commonInputData.get(5).toString());	

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.approveBtn);

			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);




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

	//E2E WF - working as of 15Oct, This is latest	
	@Test(priority = 151, enabled = tc6Status)
	public void E2E_WorkflowExecution_incompleteApplication() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc6Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.markIncompleteBtn_Xpath, 
					false, ParentWorkFlowName);

			//###############################DF - incomplete application###############################
			//submit incomplete application by regulator
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfAuthorization.incompleteRemarks_Xpath, RemarksTxt, 
					wfAuthorization.submitBtn);

			//###############################DF - incomplete application###############################

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,licenseeRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Incomplete Application", true);	

			//calling requested terms DF method to input data
			licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
					ParentWorkFlowName, true, wfAuthorization.submitBtn);

			//Back to regulator role
			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, 
					true, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);





			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd,RaisTestData.paymentInputData.get(5).toString(),
					wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), 
					wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);

			//***********************************************Starting with acceptance form
			//input data
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RemarksTxt, false, RaisTestData.commonInputData.get(5).toString());

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.approveBtn);

			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);




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

	//E2E WF - working as of 15Oct, This is latest	
	@Test(priority = 161, enabled = tc7Status)
	public void E2E_WorkflowExecution_RejectCertificate() {

		try {

			//Thread.sleep(6000);

			System.out.println(ParentWorkFlowName);

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+tc7Name);	

			//Call the method to set user and followup workflow name
			String licenseeRole = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(0).toString();
			String facilityName = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName).get(1).toString();

			ArrayList<Object> associationDataInput = RAIS_applicationSpecificMethods.getDetailsAuthWorkflow(ParentWorkFlowName);

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityName);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);


			//**********************DF - Associations ************************

			Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, associationDataInput);

			//**********************DF - Requested Terms************************

			//intialiseflag
			Boolean licenseeFlow_Flag = false;

			if (associationFormDataInputFlag == true) {

				//page wait
				GenericMethods .JSPageWait(wd);

				//calling requested terms DF method to input data
				licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, 
						ParentWorkFlowName, false, wfAuthorization.submitBtn);

			} else {
				assertEquals("Association form completion successful", "Association form completion failed");

			}

			//to proceed or not
			if (licenseeFlow_Flag == true) {

				//

			} else {
				assertEquals("Input terms form completion successful", "input form completion failed");

			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);




			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd,RaisTestData.paymentInputData.get(5).toString(),
					wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), 
					wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "", true);



			//***********************************************Starting with acceptance form
			//input data decline TNC
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RaisTestData.commonInputData.get(7).toString(),
					false, RaisTestData.commonInputData.get(5).toString());	

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form ++++++++++++++++++Reject certificate

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.rejectBtn);


			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			
			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(2).toString(), RaisTestData.commonInputData.get(10).toString(), true);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "", true);



			//***********************************************Starting with acceptance form
			//input data decline TNC
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RaisTestData.commonInputData.get(10).toString(),
					true, RaisTestData.commonInputData.get(5).toString());	

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);		

			//********************************Last DF - certificate approval form ++++++++++++++++++APPROVE certificate

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, 
					RaisTestData.commonInputData.get(10).toString(), true, wfAuthorization.approveBtn);


			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);


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








	//#F1 - Import auth - Working as of 19Sep
	@Test(priority=2,enabled=false)
	public void DemoWF_Test() throws Exception {

		try {

			Thread.sleep(6000);

			//Test Data		
			//*********************************************************************
			String regulatoryProcessMainMenu = RaisTestData.RegProcessText;
			String authorization = RaisTestData.AuthorizationSubmenu;
			String ParentWorkFlowName = RaisTestData.importAuthWF;
			String facilityMenu = RaisTestData.businessEntityList[26];
			String FA_WFName = "FollowUpAction Import Workflow";
			//*********************************************************************
			String licenseeRole = "LICImp";
			String regulatorRole = "REGVP";
			String pwd = "Pass123$";
			//*********************************************************************
			//wfID_tobeExecuted = "IMP/0079/WF"; // - Import Facility1";
			//*********************************************************************
			//DF1 test data
			String inventoryMainMenu = RaisTestData.InventResourceMainMenu;
			String resourceSubMenu = RaisTestData.ResourcesSubMenuText;
			String facilityOnWhichWFtoBeExecuted = "Import Facility2";
			String deptonWhichWFtoBeExecuted= "Import Dept2";
			String sealedSourceonWhichWFtoBeExecuted= "Imp facility 2 -sealed source";
			String unSealedSourceonWhichWFtoBeExecuted= "Import2 - Unsealed source";
			String radGenonWhichWFtoBeExecuted= "Import facility- Radiation Gen2";
			String assoEquiponWhichWFtoBeExecuted= "Imp facility 2 - Asso equip";
			String maxRadioActivityValue = "1.2E+45";
			String maxRadioActivityUnit = "Bq";
			String importAgencyName = "Lifeline Import";
			String profitLossStatement_Path = "C:\\Temp\\PL_Doc.pdf";
			String businessRegCertificate_Path = "C:\\Temp\\BReg.pdf";
			String lastYearTaxStatement_Path = "C:\\Temp\\Tax.pdf";
			String yesValue = "Yes";
			String noValue = "No";
			String invoice = "C:\\Temp\\Invoice.pdf";
			String receipt = "C:\\Temp\\Receipt.pdf";
			String amountPaid = "6000";
			String paymentMode = "card";
			String RemarksTxt = "This step has been approved by Regulator role.";
			String ApprovedByName = "Vishal Parbat";
			//*********************************************************************
			String sourceStatus = "In-Process";
			String genericDate = "08/10/2020";
			String customsNumber = "455500P231/22";
			String bill_LadingNum = "455500P231/223434";




			//Setting Test name and description on report
			SettingRptTestName_TestDesc("Import Authorization Workflow Execution","Verify Import Authorization Workflow is executed for E2E");

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					pwd, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityOnWhichWFtoBeExecuted);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//click on first record
			//GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**********************DF - Associations ************************

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);

			//page wait
			GenericMethods .JSPageWait(wd);

			//				//****************Temp
			//RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.submitBtn);

			//Thread.sleep(3000);
			//				
			//RAIS_applicationSpecificMethods.scrollToTop(wd);
			//				
			//				System.out.println(GenericMethods.getActualTxt(wd, "/html/body/div[5]/div[1]/div/div/div/ol/li[1]"));
			//				
			//				RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.submitBtn);
			//				
			//				RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.completenessLabelStepTracker_Xpath);
			//				
			//				//***************temp


			//input department on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfAuthorization.department_Xpath, deptonWhichWFtoBeExecuted);
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.sealedSource_Xpath);

			//input sealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfAuthorization.sealedSource_Xpath, sealedSourceonWhichWFtoBeExecuted);
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.unsealedSource_Xpath);

			//input unsealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfAuthorization.unsealedSource_Xpath, unSealedSourceonWhichWFtoBeExecuted);
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.radGenerator_Xpath);

			//input Radiation generator source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfAuthorization.radGenerator_Xpath, radGenonWhichWFtoBeExecuted);
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.assoEquipment_Xpath);

			//input asso equipments source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfAuthorization.assoEquipment_Xpath, assoEquiponWhichWFtoBeExecuted);

			//page wait
			//GenericMethods .JSPageWait(wd);

			//click on submit button
			GenericMethods.elementClick(wd, wfAuthorization.submitBtn);

			//waiting for success message
			//GenericMethods.waitforElement(wd, wfAuthorization.SuccessMsg_XPath);
			//GenericMethods.elementVisible(wd, wfAuthorization.SuccessMsg_XPath);
			//GenericMethods .JSPageWait(wd);

			//verify success message
			//String submitSuccessMsg = GenericMethods.getActualTxt(wd, wfAuthorization.SuccessMsg_XPath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, wfAuthorization.SuccessMsg_XPath),wfAuthorization.successfulMsg_Txt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**********************DF - Requested Terms************************

			//Starting with date
			GenericMethods.elementClick(wd, wfAuthorization.requestedStartDateCalendarControl_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			GenericMethods.elementClick(wd, wfAuthorization.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			GenericMethods.elementClick(wd, wfAuthorization.requestedEndDateCalendarControl_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			GenericMethods.elementClick(wd, wfAuthorization.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//max radioactivity value
			GenericMethods.sendText(wd, wfAuthorization.maxRadioActivity_Xpath, maxRadioActivityValue);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select unit
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfAuthorization.unitMaxRadioActivity_Xpath, maxRadioActivityUnit);
			//page wait
			GenericMethods .JSPageWait(wd);

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.importAgency_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//input import agency
			GenericMethods.sendText(wd, wfAuthorization.importAgency_Xpath, importAgencyName);
			//page wait
			GenericMethods .JSPageWait(wd);

			//upload docs
			RAIS_applicationSpecificMethods.clickAndUploadFile(wd, wfAuthorization.profitLossStatement_Xpath, profitLossStatement_Path);
			//page wait
			//GenericMethods .JSPageWait(wd);

			RAIS_applicationSpecificMethods.clickAndUploadFile(wd, wfAuthorization.businessRegCertificate_Xpath, businessRegCertificate_Path);
			//page wait
			//GenericMethods .JSPageWait(wd);

			RAIS_applicationSpecificMethods.clickAndUploadFile(wd, wfAuthorization.lastYearTaxCertificate_Xpath, lastYearTaxStatement_Path);

			//page wait
			//GenericMethods .JSPageWait(wd);

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.submitBtn);

			//page wait
			GenericMethods .JSPageWait(wd);

			//page refresh
			wd.navigate().refresh();

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on submit button
			//GenericMethods.elementClick(wd, wfAuthorization.submitBtn);

			//waiting for success message
			//GenericMethods.waitforElement(wd, wfAuthorization.SuccessMsg_XPath);
			//GenericMethods.elementVisible(wd, wfAuthorization.SuccessMsg_XPath);
			//GenericMethods .JSPageWait(wd);

			//verify success message
			//String submitSuccessMsg = GenericMethods.getActualTxt(wd, wfAuthorization.SuccessMsg_XPath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, wfAuthorization.SuccessMsg_XPath),wfAuthorization.successfulMsg_Txt);

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
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath),RaisTestData.WF_Status_Submitted_Txt);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//**********************DF - Completeness Check************************

			//input reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfAuthorization.reviewedByCC, ApprovedByName);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//input notes
			GenericMethods.sendText(wd, wfAuthorization.approvedRejectedNotes_Xpath, RemarksTxt);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//scroll to button
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.proceedToReviewBtn_Xpath);

			//waiting for success message
			GenericMethods.JSPageWait(wd);

			//page refresh
			//wd.navigate().refresh();

			//scroll to page top
			RAIS_applicationSpecificMethods.scrollToTop(wd);

			//scroll to element
			//RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.completenessLabelStepTracker_Xpath);

			//click on step tracker
			GenericMethods.elementClick(wd, wfAuthorization.completenessLabelStepTracker_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);

			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			System.out.println(reviewAssessmentId);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here

			//clicking on No external assement button
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);

			//clicking on No external assement button
			//GenericMethods.elementClick(wd, wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);				


			// **********************R&A - DF Internal review Remarks starts here

			//enter internal review remarks
			GenericMethods.sendText_removeblank(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt);

			//scroll to button
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfReview_Assessment.inspectionNeededBtn_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			RAIS_applicationSpecificMethods.scrollToTop(wd);

			GenericMethods.elementClick(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath);

			//click on label of step tracker to navigate to inspection WF
			//GenericMethods.elementClick(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading inspection Id
			final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.inspWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			System.out.println(inspectionChildId);
			System.out.println("inspection triggered");



			//****************************************Inspection starts here

			//**************************DF - Inspection Scope

			//page wait
			GenericMethods .JSPageWait(wd);

			//input department on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfInspection.department_Xpath, deptonWhichWFtoBeExecuted);

			//page wait
			GenericMethods .JSPageWait(wd);

			//using js scroll
			JavascriptExecutor jse = (JavascriptExecutor) wd;
			jse.executeScript("window.scrollBy(0,150)");			

			//input sealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfInspection.sealedSource_Xpath, sealedSourceonWhichWFtoBeExecuted);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//input unsealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfInspection.unsealedSource_Xpath, unSealedSourceonWhichWFtoBeExecuted);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//input Radiation generator source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wd, wfInspection.radGenerator_Xpath, radGenonWhichWFtoBeExecuted);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//regular or full inspection
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfInspection.regularInspection_Xpath, yesValue);

			//page wait
			GenericMethods .JSPageWait(wd);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//regular or full inspection
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfInspection.fullInspection_Xpath, noValue);

			//page wait
			GenericMethods .JSPageWait(wd);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//date of inspection
			//click on date control
			GenericMethods.elementClick(wd, wfInspection.scheduledDateofInspection_Xpath);
			//date of inspection
			//click on date control
			//GenericMethods.elementClick(wd, wfInspection.scheduledDateofInspection_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select date
			GenericMethods.elementClick(wd, wfInspection.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//Scope of inspection
			GenericMethods.sendText_removeblank(wd, wfInspection.scopeOfInspection_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on proceed without announcement
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfInspection.withoutAnnounceInspectionBtn_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**************************DF - External Inspection requirement

			wd.navigate().refresh(); //####################################################################################Bug here

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on submit button
			GenericMethods.elementClick(wd, wfInspection.internalInspectionBtn_Xpath);
			//RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfInspection.internalInspectionBtn_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**************************DF - Internal Inspection Findings

			//date of inspection
			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfInspection.internalInspectionDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on date control
			GenericMethods.elementClick(wd, wfInspection.internalInspectionDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select date
			GenericMethods.elementClick(wd, wfInspection.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//Scope of inspection
			GenericMethods.sendText_removeblank(wd, wfInspection.internalInspectionFindings_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on proceed without announcement
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfInspection.proceedInspectionBtn_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**************************DF - Inspection conclusion

			//Scope of inspection
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfInspection.inspectionConclusion_Xpath);
			GenericMethods.sendText_removeblank(wd, wfInspection.inspectionConclusion_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on submit
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfInspection.submitBtn);

			//page wait
			GenericMethods .JSPageWait(wd);

			//**************************DF - Inspection approval

			//downlod report
			GenericMethods.elementClick(wd, wfInspection.downloadInspectionReport_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfInspection.inspectionApprovedBy_Xpath, ApprovedByName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//input approval notes
			GenericMethods.sendText_removeblank(wd, wfInspection.approvalRejectionNotes_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on approve
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfInspection.approveBtn);

			//page wait
			GenericMethods .JSPageWait(wd);

			System.out.println("Inspection complete");

			/// **************************Back to R&A

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			RAIS_applicationSpecificMethods.scrollToTop(wd);

			//page wait
			GenericMethods .JSPageWait(wd);

			GenericMethods.elementClick(wd, wfInspection.inspectionApprovalStepTracker_Xpath);

			//click on label of step tracker to navigate to R&A WF
			//GenericMethods.elementClick(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading inspection Id
			//final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//System.out.println("Back to R&A");

			//*******************************************R&A Recommendation form starts here

			//page wait
			GenericMethods .JSPageWait(wd);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath);

			//input review recommendations
			GenericMethods.sendText_removeblank(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//clicking on proceed further
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfReview_Assessment.proceedFurtherBtn_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************************R&A Approval form starts here

			//downlod report
			GenericMethods.elementClick(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//input approval notes
			GenericMethods.sendText_removeblank(wd, wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on approve
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfReview_Assessment.approveBtn);

			//page wait
			GenericMethods .JSPageWait(wd);

			System.out.println("R&A complete");

			//scroll to top
			RAIS_applicationSpecificMethods.scrollToTop(wd);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on internal review of R&A
			GenericMethods.elementClick(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading inspection Id
			//final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//System.out.println("Completed R&A");

			//System.out.println("Back to Authorization WF");

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form

			//scroll and add remarks
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.authorizeRejectRemarks_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//input text
			GenericMethods.sendText(wd, wfAuthorization.authorizeRejectRemarks_Xpath, RemarksTxt);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//input reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfAuthorization.reviewedBy_Xpath, ApprovedByName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//scroll to bottom
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.authorizeBtn);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//scroll to top
			RAIS_applicationSpecificMethods.scrollToTop(wd);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on internal review of R&A
			GenericMethods.elementClick(wd, wfAuthorization.completenessLabelStepTracker_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading inspection Id
			//final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfPayment.newuploadInvoice_Xpath);

			//upload invoice
			RAIS_applicationSpecificMethods.clickAndUploadFile(wd,wfPayment.newuploadInvoice_Xpath , invoice);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//scroll to bottom and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfPayment.submitBtn);

			//page refresh and logout
			wd.navigate().refresh();

			//page wait
			GenericMethods .JSPageWait(wd);

			/////////////////CHANGE TO LICENSEE USER

			//Logout licensee user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

			//login using Regulator
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					pwd, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath),RaisTestData.WF_Status_Submitted_Txt);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//scroll to top
			RAIS_applicationSpecificMethods.scrollToTop(wd);

			//page wait
			GenericMethods .JSPageWait(wd);

			//click on internal review of R&A
			GenericMethods.elementClick(wd, wfAuthorization.completenessLabelStepTracker_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//clicking on linked processes
			GenericMethods.elementClick(wd, wfAuthorization.linkedProcess_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//reading inspection Id
			//final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			System.out.println("Completed R&A");

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval

			//Input amount paid
			GenericMethods.sendText(wd, wfPayment.amountPaid_Xpath, amountPaid);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//Input amount paid mode
			GenericMethods.sendText(wd, wfPayment.paymentMedium_Xpath, paymentMode);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);				

			//upload invoice
			RAIS_applicationSpecificMethods.clickAndUploadFile(wd,wfPayment.newPaymentReceipt_Xpath , receipt);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//scroll to bottom and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfPayment.submitBtn);

			//********************************************PAYMENT COMPLETE

			//page refresh and logout
			wd.navigate().refresh();

			//page wait
			GenericMethods .JSPageWait(wd);

			/////////////////CHANGE TO Regulator USER

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
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath),RaisTestData.WF_Status_Submitted_Txt);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//***************************AUTH DF Authorization terms

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//date of inspection
			//click on date control
			GenericMethods.elementClick(wd, wfAuthorization.authStartDate);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select date
			GenericMethods.elementClick(wd, wfAuthorization.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//date of inspection
			//click on date control
			GenericMethods.elementClick(wd, wfAuthorization.authEndDate);

			//page wait
			GenericMethods .JSPageWait(wd);

			//select date
			GenericMethods.elementClick(wd, wfAuthorization.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.tncText_Xpath);

			//page wait
			GenericMethods .JSPageWait(wd);

			//input TNC
			GenericMethods.sendText_removeblank(wd, wfAuthorization.tncText_Xpath, RemarksTxt);

			//page wait
			GenericMethods .JSPageWait(wd);

			//scroll to bottom and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.submitBtn);

			//page wait
			GenericMethods .JSPageWait(wd);

			System.out.println("Autho terms completed ...............");

			//***********************************************************************

			//page refresh and logout
			wd.navigate().refresh();

			//page wait
			GenericMethods .JSPageWait(wd);

			/////////////////CHANGE TO LICENSEE USER

			//Logout licensee user
			RAIS_applicationSpecificMethods.logout(wd);
			System.out.println("Logout success");

			//login using Regulator
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					pwd, loginPage.loginBtn_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath),RaisTestData.WF_Status_Submitted_Txt);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//***********************************************Starting with acceptance form

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//click on accept check box
			GenericMethods.elementClick(wd, wfAuthorization.acceptTnC_ChkBox_Xpath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wd, wfAuthorization.remarks_Xpath);

			//input remarks
			GenericMethods.sendText_removeblank(wd, wfAuthorization.remarks_Xpath, RemarksTxt);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.acceptBtn);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//page refresh and logout
			wd.navigate().refresh();

			//page wait
			GenericMethods .JSPageWait(wd);

			/////////////////CHANGE TO Regulator USER

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
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//Assert.assertEquals(GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath),RaisTestData.WF_Status_Submitted_Txt);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//download certificate
			GenericMethods.elementClick(wd, wfAuthorization .downloadAuthorizationReport_Xpath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//scroll and click
			RAIS_applicationSpecificMethods.jScrollToBottom(wd);

			GenericMethods.sendText_removeblank(wd, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.approveBtn);

			//Completed

			System.out.println("Workflow execution Completed");


			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				
			//				GenericMethods.sendText(wd, "//input[@id='filter']", "Equip Dept2");
			////				GenericMethods.sendText(wd, "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[1]/div/div/div/div/div/fieldset/div/div[2]/div/div/input[@id='filter']"
			////						, "Equip Dept2");
			//				
			////				GenericMethods.JClickonElement(wd, "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[1]/div/div/div/div/div/fieldset/div/div[2]/div/div");
			////				
			////				RAIS_applicationSpecificMethods.rbSendText(wd,  "Equip Dept2");
			//				
			//				
			//				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div//ul/li[1]/label");
			//				
			//				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div");
			//				
			//				//GenericMethods.sendText_removeblank(wc, clickElement, valueToSend);
			//				
			//				//GenericMethods.JClickonElement(wj, clickElement);
			//				
			//				//JavascriptExecutor jse = (JavascriptExecutor) wd;
			//				jse.executeScript("arguments[0].scrollIntoView(true);", wd.findElement(By.xpath("//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div")));
			//				
			//				GenericMethods.elementClick(wd, "//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div");
			//				
			//				GenericMethods .JSPageWait(wd);
			//				
			//				GenericMethods.sendText(wd, "//input[@id='filter']", "Equip facility2 - Sealed source");
			//				
			//				
			//				
			//				
			//				//input source location
			//				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.sourceLocation_Xpath, sourceLocation);
			//				
			//				//input source status
			//				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.sourceStatus_Xpath, sourceStatus);
			//				
			//				//input source date
			//				GenericMethods.sendText(wd, FA_WFPage.sourceStatusDate_Xpath, genericDate);
			//				
			//				//input import date
			//				GenericMethods.sendText(wd, FA_WFPage.importDate_Xpath, genericDate);
			//				
			//				//upload security plan doc
			//				//RAIS_applicationSpecificMethods.clickAndUploadFile(wd, FA_WFPage.securityPlan_Xpath,securityPlanDoc_Path);
			//				
			//				//input customs number
			//				GenericMethods.sendText(wd, FA_WFPage.importCustomNum_Xpath, customsNumber);
			//				
			//				//input Bills of number
			//				GenericMethods.sendText(wd, FA_WFPage.importBillLadingNum_XPath, bill_LadingNum);
			//				
			//				//input bill lading date
			//				GenericMethods.sendText(wd, FA_WFPage.importBillLadingdate_XPath, genericDate);
			//				
			//				//click on submit button
			//				GenericMethods.elementClick(wd, FA_WFPage.submitBtn_FAPage1_Xpath);
			//				
			//				//Waiting for popup to load
			//				//GenericMethods .JSPageWait(wd);
			//				//waiting for success message
			//				GenericMethods.waitforElement(wd, FA_WFPage.SuccessMsg_XPath);
			//				GenericMethods.elementVisible(wd, FA_WFPage.SuccessMsg_XPath);
			//				
			//				//verify success message
			//				//String submitSuccessMsg = GenericMethods.getActualTxt(wd, FA_WFPage.SuccessMsg_XPath);
			//				//Assert.assertEquals(submitSuccessMsg,FA_WFPage.successfulMsg_Txt);
			//				
			//				//Logout licensee user
			//				RAIS_applicationSpecificMethods.logout(wd);
			//				System.out.println("Logout success");
			//				
			//				//login using Regulator
			//				GenericMethods.loginApplication
			//				(wd, loginPage.userId_XPath, regulatorRole, loginPage.pwd_XPath, 
			//						pwd, loginPage.loginBtn_XPath);
			//				
			//				//Waiting for popup to load
			//				GenericMethods .JSPageWait(wd);
			//
			//				//Clicking on followup action menu
			//				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization, FA_WFName);
			//				
			//				//Waiting for popup to load
			//				GenericMethods .JSPageWait(wd);
			//				
			//				//search for required record
			//				RAIS_applicationSpecificMethods.basicSearchRecord(wd, "workflowId");
			//				
			//				//verify workflow statuses
			//				//String wfStatus_Submit = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//				//Assert.assertEquals(wfStatus_Submit,RaisTestData.WF_Status_Submitted_Txt);
			//							
			//				//click on first record
			//				GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);
			//				
			//				//Waiting for popup to load
			//				GenericMethods.JSPageWait(wd);
			//				
			//				//input approval status date
			//				GenericMethods.sendText(wd, FA_WFPage.approvalFrmStatusDate_Xpath, genericDate);
			//				
			//				//input remarks on approval form
			//				GenericMethods.sendText(wd, FA_WFPage.approvalFrmRemarks_Xpath, RemarksTxt);
			//				
			//				//select approved by role by regulator
			//				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, FA_WFPage.approvalFrmApprovedBy_Xpath, ApprovedByName);
			//				
			//				//scroll to bottom
			//				RAIS_applicationSpecificMethods.jScrollToBottom(wd);
			//				
			//				//click on submit button
			//				GenericMethods.elementClick(wd, FA_WFPage.approveBtn_Xpath);
			//				
			//				//Waiting for popup to load
			//				//GenericMethods .JSPageWait(wd);
			//				//waiting for success message
			//				GenericMethods.waitforElement(wd, FA_WFPage.SuccessMsg_XPath);
			//				GenericMethods.elementVisible(wd, FA_WFPage.SuccessMsg_XPath);
			//				
			//				//verify success message
			//				String approveSuccessMsg = GenericMethods.getActualTxt(wd, FA_WFPage.SuccessMsg_XPath);
			//				Assert.assertEquals(approveSuccessMsg,FA_WFPage.successfulMsg_Txt);
			//				
			//				//page refresh
			//				wd.navigate().refresh();
			//				
			//				//Waiting for popup to load
			//				GenericMethods .JSPageWait(wd);
			//
			//				//Clicking on followup action menu
			//				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, authorization,FA_WFName);
			//				
			//				//search for required record
			//				RAIS_applicationSpecificMethods.basicSearchRecord(wd, "workflowId");
			//				
			//				//verify workflow statuses
			//				String wfStatus_Complete = GenericMethods.getActualTxt(wd, entityRecordListing.workflowStatus_Xpath);
			//				Assert.assertEquals(wfStatus_Complete,RaisTestData.WF_Status_Completed_Txt);


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

	//E2E WF - FOr testing
	@Test(priority = 3, enabled = false)
	public void E2E_WorkflowExecution_AFlows() {

		try {

			//Thread.sleep(6000);

			//Test Data		
			//*********************************************************************
			String regulatoryProcessMainMenu = RaisTestData.RegProcessText;
			String authorization = RaisTestData.AuthorizationSubmenu;
			String ParentWorkFlowName = RaisTestData.importAuthWF;
			String facilityMenu = RaisTestData.businessEntityList[26];
			//*********************************************************************
			String licenseeRole = "LICImp";
			String regulatorRole = "REG";
			//String pwd = "Pass123$";
			//*********************************************************************
			//wfID_tobeExecuted = "IMP/0079/WF"; // - Import Facility1";
			//*********************************************************************
			//DF1 test data
			String inventoryMainMenu = RaisTestData.InventResourceMainMenu;
			String resourceSubMenu = RaisTestData.ResourcesSubMenuText;
			String facilityOnWhichWFtoBeExecuted = "Import Facility2";
			//*********************************************************************
			String [] inputData_Associations_Inspection_DataForm = {					
					"Import Dept2", 							//0//department name
					"Imp facility 2 -sealed source", 			//1// sealed source name
					"Import2 - Unsealed source",				//2// unsealed source name
					"Import facility- Radiation Gen2",			//3// radiation generator name
					"Imp facility 2 - Asso equip",				//4// associated equipment name
					"Submit",									//5// submit button
					"Yes",										//6// Yes value to be used in Inspection Scope data form
					"No",										//7// No value to be used in Inspection scope data form
					"This step has been is Reviewed/ approved",	//8// Memo field Reviewed by field
					"Proceed Without Announcement",				//9// Button for inspection
					"Accept"									//10//Auth terms page

			};							
			//*********************************************************************
			String [] inputRequestedTermsDataForm = {
					ParentWorkFlowName,					//0//workflow name
					"1.2E+45",							//1//Max radioactivity
					"Bq",								//2//Unit
					"Lifeline Import",					//3//agency name
					"C:\\Temp\\PL_Doc.pdf",				//4//doc1	
					"C:\\Temp\\BReg.pdf",				//5//doc2
					"C:\\Temp\\Tax.pdf",				//6//doc3
					"Submit",							//7//Button - Submit
					"Proceed"							//8//Proceed button - inspection findings form
			};
			//*********************************************************************
			String [] paymentInputData = {
					"C:\\Temp\\Invoice.pdf",			//0//invoice path	
					"C:\\Temp\\Receipt.pdf",			//1//Receipt path
					"6000",								//2//Amount
					"card",								//3//Payment mode
					"Skip",								//4// Skip
					"Attach Invoice",					//5// Attach invoice form
					"Confirm Payment"					//6// Confirm payment Data form
			};

			String [] commonInputData_Memo_Button_Label = {
					"Save",
					"Submit",
					"This step has been is Reviewed/ approved",


			};

			String RemarksTxt = "This step has been is Reviewed/ approved- Demo";
			String ApprovedByName = "Vishal";
			//*********************************************************************					

			//Setting Test name and description on report
			SettingRptTestName_TestDesc(ParentWorkFlowName+" Execution","Verify "+ParentWorkFlowName+" is executed for E2E Happy path");			

			//Calling Login method
			GenericMethods.loginApplication
			(wd, loginPage.userId_XPath, licenseeRole, loginPage.pwd_XPath, 
					password, loginPage.loginBtn_XPath);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, inventoryMainMenu,resourceSubMenu, facilityMenu);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, facilityOnWhichWFtoBeExecuted);

			//click on the record
			GenericMethods.elementClick(wd, entityRecordListing.rdoBtn_Xpath);

			//initatie the workflow
			RAIS_applicationSpecificMethods.initiateWorkflow(wd, ParentWorkFlowName);

			//page wait
			GenericMethods .JSPageWait(wd);

			//extract workflowid
			wfID_tobeExecuted = RAIS_applicationSpecificMethods.trimWorkflowid(wd, wfAuthorization.wfId_displayedonTop);

			System.out.println(wfID_tobeExecuted);

			//**********************DF - Associations *************************$%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

			//Boolean associationFormDataInputFlag = RAIS_applicationSpecificMethods.inputDataOnAssociationForm_Authorization(wd, inputData_Associations_Inspection_DataForm);


			//**********************DF - Requested Terms************************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% uncomment this if

			//			if (associationFormDataInputFlag == true) {
			//				
			//				//calling requested terms DF method to input data
			//				Boolean licenseeFlow_Flag = RAIS_applicationSpecificMethods.inputDataOnRequestedTerms_Authorization(wd, inputRequestedTermsDataForm);
			//				
			//			} else {
			//				assertEquals("Association form completion successful", "Association form completion failed");
			//				
			//			}

			//logout and relogin with new user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd,regulatorRole,password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);	//IMP/0010/WF		

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"", true);

			//**********************DF - Completeness Check************************

			//calling completeness method
			RAIS_applicationSpecificMethods.authorizationCompletenessCheck(wd, ApprovedByName, RemarksTxt, wfAuthorization.proceedToReviewBtn_Xpath, false, ParentWorkFlowName);

			//clicking on step tracker to enable linked processes
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//reading R&A and Payment Ids
			final String reviewAssessmentId = GenericMethods.getActualTxt(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			final String paymentId = GenericMethods.getActualTxt(wd, wfAuthorization.paymentWFid_Xpath);
			System.out.println(reviewAssessmentId);
			System.out.println(paymentId);


			//click on R&A processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);


			//**************************************R&A DF starts here


			// **********************R&A - DF External review starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd,wfReview_Assessment.externalAssessmentNOTRequiredBtn_Xpath);


			// **********************R&A - DF Internal review Remarks starts here
			//input data
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.internalReviewRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedBtn_Xpath);


			//diversion&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


			//			//clicking on step tracker to enable linked processes
			//			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
			//					wfAuthorization.linkedProcess_Xpath);
			//			
			//			//reading inspection Id
			//			final String inspectionChildId = GenericMethods.getActualTxt(wd, wfAuthorization.inspWFtid_Xpath);
			//			System.out.println(inspectionChildId);				
			//			
			//			//click on inspection processes direct
			//			GenericMethods.elementClick(wd, wfAuthorization.inspWFtid_Xpath);
			//			
			//			//Waiting for popup to load
			//			GenericMethods.JSPageWait(wd);			
			//			
			//			
			//			//****************************************Inspection starts here
			//			
			//			//**************************DF - Inspection Scope
			//			
			//			RAIS_applicationSpecificMethods.inspectionScopeDataForm(wd,inputData_Associations_Inspection_DataForm);			
			//			
			//			//**************************DF - External Inspection requirement
			//			
			//			wd.navigate().refresh(); //####################################################################################Bug here
			//			
			//			//CHECK IF THE CODE FAILS HERE< REFER TO COMMENTED CODE BELOW
			//			
			//			//input data form
			//			RAIS_applicationSpecificMethods.dataFormOnlyButtonClick(wd, wfInspection.internalInspectionBtn_Xpath);
			//			
			//			
			//			//**************************DF - Internal Inspection Findings
			//			//input data form
			//			RAIS_applicationSpecificMethods.inspectionFindingsDataForm(wd, RemarksTxt, wfInspection.proceedInspectionBtn_Xpath); 
			//			
			//		
			//			//**************************DF - Inspection conclusion
			//			//input data form
			//			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfInspection.inspectionConclusion_Xpath, RemarksTxt, wfInspection.submitBtn);
			//			
			//			//**************************DF - Inspection approval
			//			
			//			//approval input data form
			//			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfInspection.downloadInspectionReport_Xpath, 
			//					wfInspection.inspectionApprovedBy_Xpath, ApprovedByName, 
			//					wfInspection.approvalRejectionNotes_Xpath, RemarksTxt, wfInspection.approveBtn);
			//							
			//			/// **************************Back to R&A form
			//			//click and expand linked processes
			//			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfInspection.inspectionApprovalStepTracker_Xpath, 
			//					wfAuthorization.linkedProcess_Xpath);
			//										
			//			//click on R&A processes direct
			//			GenericMethods.elementClick(wd, wfAuthorization.newreviewAssessmenWFtid_Xpath);
			//			
			//			//Waiting for popup to load
			//			GenericMethods.JSPageWait(wd);




			//diversion&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ENDS

			//*******************************************R&A Recommendation form starts here
			//input data form
			RAIS_applicationSpecificMethods.dataFormMemoAndButtonClick(wd, wfReview_Assessment.recommendationRemarksTxt_Xpath, RemarksTxt, 
					wfReview_Assessment.proceedFurtherBtn_Xpath);


			//*******************************************R&A Approval form starts here
			//approval data form input
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfReview_Assessment.downloadReviewAssessmentReport_Xpath, 
					wfReview_Assessment.reviewAssessmentApprovedBy_Xpath, ApprovedByName, 
					wfReview_Assessment.approvalRejectionNotes_Xpath, RemarksTxt, false, wfReview_Assessment.approveBtn);

			//click on step tracker to navigate
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfReview_Assessment.internalReviewReviewStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.parentWFtid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//*******************************DF AUthorization R&A form
			// input data form
			RAIS_applicationSpecificMethods.authorizationReviewAssessmentDataForm(wd, RemarksTxt, ApprovedByName, wfAuthorization.authorizeBtn);

			//scroll and click
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath, 
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			// ***********************************************STARTING WITH PAYMENT

			//calling payment form
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(5).toString(), wfPayment.newuploadInvoice_Xpath);			

			//Logout and change user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify workflow statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd,"Review Complete", true);

			//scroll on top and click on tracker
			RAIS_applicationSpecificMethods.scrollUpClickStepTrackerandclickBottomTab(wd, wfAuthorization.completenessLabelStepTracker_Xpath,
					wfAuthorization.linkedProcess_Xpath);

			//click on inspection processes direct
			GenericMethods.elementClick(wd, wfAuthorization.newPaymentWFid_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wd);

			//***********************PAYMENT approval
			//payment approval
			RAIS_applicationSpecificMethods.paymentAttachInvoiceReceiptDataForm(wd, RaisTestData.paymentInputData.get(6).toString(), wfPayment.newPaymentReceipt_Xpath);


			//********************************************PAYMENT COMPLETE
			//logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);


			//***************************AUTH DF Authorization terms
			//input data
			RAIS_applicationSpecificMethods.authorizationTermsAndConditions(wd, ParentWorkFlowName, 
					inputData_Associations_Inspection_DataForm[7], inputData_Associations_Inspection_DataForm[7], RemarksTxt, false);

			//logout and re-login with different user
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, licenseeRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Review Complete", true);

			//***********************************************Starting with acceptance form
			//input data
			RAIS_applicationSpecificMethods.acceptanceDataForm(wd, RemarksTxt, false, inputData_Associations_Inspection_DataForm[10]);

			////logout and relogin
			RAIS_applicationSpecificMethods.workflowLogoutAndReLoginUser(wd, regulatorRole, password);

			//Clicking on followup action menu
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//click on first record
			GenericMethods.elementClick(wd, entityRecordListing.workFlowName_Xpath);

			//********************************Last DF - certificate approval form

			//approval form
			RAIS_applicationSpecificMethods.approvalDataForm(wd, wfAuthorization.downloadAuthorizationReport_Xpath, 
					wfAuthorization.authorizationApprovedBy_Xpath, ApprovedByName, wfAuthorization.approvalRejectionNotes_Xpath, RemarksTxt, 
					false, wfAuthorization.approveBtn);

			//page refresh
			//wd.navigate().refresh();

			//verify approved state on workflow listing page
			RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, regulatoryProcessMainMenu, authorization, ParentWorkFlowName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wd);

			//search for required record
			RAIS_applicationSpecificMethods.basicSearchRecord(wd, wfID_tobeExecuted);

			//verify statuses
			RAIS_applicationSpecificMethods.verifyWorkflowStatusAndClickRecord(wd, "Approved", false);




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

		//		//Calling Login method
		//				GenericMethods.loginApplication
		//				(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
		//						password, loginPage.loginBtn_XPath);
		//
		//				//Waiting for popup to load
		//				GenericMethods .JSPageWait(wd);
		//
		//				//Clicking on entities menu
		//				RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.AdministrationMainMenu, RaisTestData.CommonTablesSubMenu, 
		//						RaisTestData.businessEntityList[65]);
		//
		//				//Waiting for popup to load
		//				GenericMethods .JSPageWait(wd);
		//				
		//				//click on add new
		//				GenericMethods.elementClick(wd, entityRecordListing.addNewBtn_XPath);
		//				
		//				//click on organigram link
		////				GenericMethods.elementClick(wd, 
		////						"//*[@id='entity-form']/div/div/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a");
		//				
		//				RAIS_applicationSpecificMethods.clickAndUploadFile(wd, "//*[@id='entity-form']/div/div/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a",
		//						"C:\\Temp\\Demo1.pdf");
		//		
		//				 System.out.println("Upload successful");
		//				
		//				
		//		
		//		
		//		
		//		//System.out.println(RAIS_applicationSpecificMethods.trimWorkflowid(" Initiated Manually - IMP/0002/WF - Import Facility1"));
		//		
		//		//System.out.println(RAIS_applicationSpecificMethods.trimWorkflowid(" Initiated Manually - IMP/FA/0001/WF - IMP/0011/WF"));
		//		
		//		String string = " Initiated Manually - IMP/0002/WF - Import Facility1";
		//        string = string.replace(" Initiated Manually - ", "");
		//        string = string.replace("- ", "");
		//        System.out.println(string);
		//        
		//        String string1 = " Initiated Manually - IMP/FA/0001/WF - IMP/0011/WF";
		//        string1 = string1.replace(" Initiated Manually - ", "");
		//        string1 = string1.replace("- ", "");
		//        System.out.println(string1);
		//		
		//		
		//		//Calling Login method
		//		GenericMethods.loginApplication
		//		(wd, loginPage.userId_XPath, userName, loginPage.pwd_XPath, 
		//				password, loginPage.loginBtn_XPath);
		//
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//
		//		//Clicking on entities menu
		//		RAIS_applicationSpecificMethods.Generic_Menu_subMenu_Click(wd, RaisTestData.RegProcessText, "Authorizations", 
		//				"Import Authorization Workflow");
		//
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		RAIS_applicationSpecificMethods.basicSearchRecord(wd, "IMP/0011/WF Import Facility 1 Execution");
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//				
		//		System.out.println(GenericMethods.getActualTxt(wd, "//*[@id='entity-details']//div//table//tbody//tr[1]/td[2]"));
		//		
		//		System.out.println(GenericMethods.getActualTxt(wd, "//*[@id='entity-details']//div//table//tbody//tr[1]/td[3]"));
		//		
		//		RAIS_applicationSpecificMethods.initiateWorkflow(wd, "FollowUpAction Import Workflow");
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//
		//		System.out.println();
		//		
		//		
		//		
		//		
		//		
		//		//Clicking on add new data role
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.DR_addNewRoleBtn);
		//
		//		//Calling common method to input save and verify
		//		RAIS_applicationSpecificMethods.DR_FR_Input_Save_Verify(wd, dataRoleName+localTime, AddNewDataRole.ADDNEWROLE_SUCESSMSG_TXT,DR_MenuName);
		//
		//		//page refresh
		//		//wd.navigate().refresh();
		//
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		//click on new permission button
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.FunctionalRolePermissionRestrictBtn_XPath);
		//		
		//		//**************************************************Add practice HERE	
		//		
		//		//click on practice link
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addPractice_Xpath);
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		//create practice entity
		//		Boolean flag_createPractice = RAIS_applicationSpecificMethods.createEntityRecord(wd, "Practices", "1Demo Practice", 
		//				"6", optional, optional, dataRolesfunctionalRolesPage.formId_Tab1);
		//								
		//		System.out.println(flag_createPractice);
		//		
		//		//**************************************************Add region HERE	
		//		
		//		//click on practice link
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addRegion_Xpath);
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		//create region entity
		//		Boolean flag_createRegion = RAIS_applicationSpecificMethods.createEntityRecord(wd, singleFieldInput, "1Demo Region", 
		//				optional, optional, optional, dataRolesfunctionalRolesPage.formId_Tab1);
		//					
		//		System.out.println(flag_createRegion);
		//		
		//		//**************************************************Add District HERE	
		//
		//		//click on district link
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addDistrict_Xpath);
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		//create practice entity
		//		Boolean flag_createDistrict = RAIS_applicationSpecificMethods.createEntityRecord(wd, "Districts", "1Demo District", 
		//				"1Demo Region", optional, optional, dataRolesfunctionalRolesPage.formId_Tab1);
		//		
		//		System.out.println(flag_createDistrict);
		//		
		//		//**************************************************Add Facility HERE
		//		
		//		//click on facility link
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addFacility_Xpath);
		//		
		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wd);
		//		
		//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_RANFld_XPath, "FAC001"); //RAN
		//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_inputNameFld_XPath, "1Demo Facility"); //Fac Name
		//		
		//		//page wait
		//		GenericMethods.JSPageWait(wd);
		//		
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.createFacility_PracticeSelect);
		//		//page wait
		//		GenericMethods.JSPageWait(wd);
		//		
		//		GenericMethods.sendText(wd, entityRecordDetails.idFilter, "1Demo Practice"); //Practice
		//		//selecting particular item
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.selectedItem);
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.createFacility_PracticeSelect);
		//		
		//		
		//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_region_Xpath, "1Demo Region"); //Region
		//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_district_Xpath, "1Demo District"); //District
		//		
		//		//**************************************** Add facility ends here
		//		
		//		System.out.println("facility created");			
		//		
		//		
		//		wd.navigate().refresh();
		//		
		//		//selecting newly added value in dropdown
		//		RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, dataRolesfunctionalRolesPage.practiceDropdown_Xpath, "1Demo Practice");
		//		
		//		//selecting newly added value in dropdown
		//		RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, dataRolesfunctionalRolesPage.regionDropdown_Xpath, "1Demo Region");
		//		
		//		//selecting newly added value in dropdown
		//		RAIS_applicationSpecificMethods.valueSelectfromDropDown(wd, dataRolesfunctionalRolesPage.districtDropdown_Xpath, "1Demo District");
		//		
		//		//select newly added facility from list
		////		RAIS_applicationSpecificMethods.selectMultiValue(wd, dataRolesfunctionalRolesPage.formId_Tab1,"1Demo Facility");
		//		
		//		//System.out.println(flag_createFacility);			
		//		
		//		//click on save button
		//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.savePermRestrict_XPath);

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
			//			//Flush all report details
			closeExtentReport();
			//			
			//			BrowserClose();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//After class browser close and email sending##############################
	@AfterClass
	public void afterClass() {
		try {

			//Flush all report details
			//closeExtentReport();

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
