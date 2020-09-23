package pageLocators_Elements.RAIS;

public class Workflow_AuthorizationPage {
	
	//WFID
	public String wfId_displayedonTop = "//*[@id='work-flow-form']/div[1]/span";
	
	//DF - Associations
	//Department location xpath
	public String department_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1]//fieldset//div";	
	//sealed source 
	public String sealedSource_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div";
	//unsealed source
	public String unsealedSource_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3]//fieldset//div";
	//radiation generator
	public String radGenerator_Xpath ="//*[@id='work-flow-form']//div//form//div//fieldset//div[4]//fieldset//div";
	//Associated Equipment
	public String assoEquipment_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[5]//fieldset//div";
	//person
	public String person_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[6]//fieldset//div";
	
	//filter id
	public String inputField_Xpath = "//input[@id='filter']";
	//************************************************************
	
	//DF -Requested Terms
	//start date
	public String requestedStartDate_Xpath = "//input[@id='StartDate']";
	public String requestedStartDateCalendarControl_Xpath = "//div[input[@id='StartDate']]//div//button";
	
	//end date
	public String requestedEndDate_Xpath = "//input[@id='EndDate']";
	public String requestedEndDateCalendarControl_Xpath = "//div[input[@id='EndDate']]//div//button";
	
	//select one particular date
	public String selectSpecificDate_Xpath = "/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div[3]/div[2]/button";
	
	//max radio activity
	public String maxRadioActivity_Xpath = "//input[@id='MaximumRadioActivity']";
	//unit max radio activity
	public String unitMaxRadioActivity_Xpath = "//*[@id='MaximumRadioActivityId']";
	//import agency
	public String importAgency_Xpath = "//input[@id='ImportAgencyText']";
	
	//upload P&L statement
	public String profitLossStatement_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[5]/div/div/div/div/div/label/a";
	//upload P&L statement
	public String businessRegCertificate_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a";
	//upload P&L statement
	public String lastYearTaxCertificate_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[7]/div/div/div/div/div/label/a";
	
	//save and submit button
	public String saveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn = "//*[@id='work-flow-form']//button[text()='Submit']";
	
	//generic buttons
	public String btnPrefix_Xpath = "//*[@id='work-flow-form']//button[text()='";
	public String btnSuffix_Xpath = "']";
	//**************************************************************
	
	//******************All forms 2 sections generic xpath prefix
	
	public String sectionPrefix_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div//";
	
	public String genericLocatorXpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div";
	//***************************************************************
	
	//DF- completeness check
	//completeness label on top
	public String completenessLabelStepTracker_Xpath = "//*[@id='work-flow-form']//div//span[contains(text(),'Completeness Check')]";
	
	//reviewed by
	public String reviewedByCC = sectionPrefix_Xpath+ "select[@id='ReviewedByCC']";
	//application approved/Rejected
	public String approvedRejectedNotes_Xpath = sectionPrefix_Xpath+"textarea[@id='AppRejNotesCC']";
	
	//proceed to review button
	public String proceedToReviewBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed To Review')]";
	//proceed to review button
	public String rejectAuthBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject Authorization')]";
	//proceed to review button
	public String markIncompleteBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Mark Incomplete')]";
	
	//linked processes
	public String linkedProcess_Xpath = "//div//header//button[@id='collapsible-tab-undefined']/span[contains(text(),'Linked Processes')]";
	
	//R&A id from table
	public String reviewAssessmenWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr[1]/td[1]";
	public String newreviewAssessmenWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'RAA')]";
	public String inspWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'INS')]";
	public String parentWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'Parent')]";
	//*[@id="collapsible-tabpanel-0"]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr[2]/td[2]
	//payment id from table
	public String paymentWFid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr[2]/td[1]";
	public String newPaymentWFid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'PAY')]";
	
	//**************************************************************
	
	//DF- review and assessment
	//authorization remarks
	public String authorizeRejectRemarks_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='column-section-controls']//div//textarea[@id='AuthRemarks']";//*[@id='AuthRemarks']";
	
	//reviewed by
	public String reviewedBy_Xpath = "//*[@id='ReviewedByRA']";
	
	//Authorize and reject button
	public String authorizeBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Authorize')]";
	public String rejectAuthBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject')]";	
	

	//**************************************************************
	
	//DF- Auth terms
	//start date
	public String authStartDate = "//div[input[@id='AuthStartDate']]//div//button" ; //input[@id='AuthStartDate']"
	//end date
	public String authEndDate = "//div[input[@id='AuthEndDate']]//div//button"; //input[@id='AuthEndDate']";
	
	//terms and conditions text
	public String tncText_Xpath = genericLocatorXpath + "//textarea[@id='TermsAndConditions']";
	
	//save and submit button
	public String saveBtn1 = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn1 = "//*[@id='work-flow-form']//div//button[contains(text(),'Submit')]";
		
	//***********************************************************
	
	//DF - Acceptance form
	//check box
	public String acceptTnC_ChkBox_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label[@id='AcceptTOA']"; //*[@id='AcceptTOA']";
	// remarks
	public String remarks_Xpath = genericLocatorXpath + "//textarea[@id='AcceptanceRemarks']";
	//public String remarks_Xpath = "//*[@id='AcceptanceRemarks']";
	
	//save and submit button
	public String acceptBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Accept')]";
	public String declineBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Decline')]";
	
	//*********************************************************
	
	//DF -  Approval
	//Authorization report download
	public String downloadAuthorizationReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//ul//li//span//a//button";
	
	//reviewed by
	public String authorizationApprovedBy_Xpath = "//*[@id='ReviewedByCA']";
	
	//approval/ rejection notes
	public String approvalRejectionNotes_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div//textarea[@id='CertRejRemarks']";
	//public String approvalRejectionNotes_Xpath = "//*[@id='CertRejRemarks']";
		
	//approve and reject button
	public String approveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Approve Authorization')]";
	public String rejectBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject Certificate')]";	
		
	//Page Constants *********************************************************************
	
	//success message xpath
		public String SuccessMsg_XPath = "//*[@id='message-success']";
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
