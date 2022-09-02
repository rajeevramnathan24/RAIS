package pageLocators_Elements.RAIS;

public class Workflow_AuthorizationPage {
	
	//WFID
	public String wfId_displayedonTop = "//*[@id='work-flow-form']//div[@class='page-left-section']//span[2]";
	
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
	
	//for equipment workflow
	public String requestedStartDateEQPCalendarControl_Xpath = "//div[input[@id='ReqStartDate']]//div//button";
	
	//for release workflow
	public String releaseDateCalendarControl_Xpath = "//div[input[@id='ReleaseDate']]//div//button";
	
	//end date
	public String requestedEndDate_Xpath = "//input[@id='EndDate']";
	public String requestedEndDateCalendarControl_Xpath = "//div[input[@id='EndDate']]//div//button";
	
	//for equipment workflow
	public String requestedEndDateEQPCalendarControl_Xpath = "//div[input[@id='ReqEndDate']]//div//button";
	
	//select one particular date
	public String selectSpecificDate_Xpath = "/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div[3]/div[2]/button";
	
	//max radio activity
	public String maxRadioActivity_Xpath = "//*[@id='MaximumRadioActivity']";  //"//input[@id='MaximumRadioActivity']";
	public String maxRadioActivity_Use_Storage_Trn_Trf_Xpath = "//*[@id='MaximumRadioactivity']";
	
	//unit max radio activity
	public String unitMaxRadioActivity_Xpath = "//*[@id='MaximumRadioActivityId']";
	public String unitMaxRadioActivity_Use_Storage_Trn_Trf_Xpath = "//*[@id='MaximumRadioactivityId']";
	
	//import agency
	public String importAgency_Xpath = "//input[@id='ImportAgency']";
	public String importDate_Xpath = "//div[input[@id='ImportDate']]//div//button";
	public String exportDate_Xpath = "//div[input[@id='ExportDate']]//div//button";
	
	//export agency
	public String exportAgency_Xpath = "//input[@id='ExportAgency']";		//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='ExportAgency']";
	
	//Trasnfer agency
	public String recipientFacility_Xpath = "//select[@id='DestinationFacility']";
	public String recipientDepartment_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='RecipientDepartment']";
	public String transferDateCalendarControl_Xpath = "//div[input[@id='DateofTransfer']]//div//button";
	public String transferType_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='TransferType']";
	public String durationOfTransfer_Xpath = "//input[@id='DurationofTransfer']";
	
	//generic doc uploads and position
	public String prefix_Doc1_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[";
	public String suffix_Doc1_Xpath = "][@class='column-section-controls']//label//a";
	// for confirm payment
	public String prefixConfirmPayment_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset//div[2][@class='col-sm-6 col-flex-1 renderer-section-2']//div["; //4][@class='column-section-controls']//label//a";
	
	//upload P&L statement
	//public String profitLossStatement_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[5]/div/div/div/div/div/label/a";
	public String profitLossStatement_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[8][@class='column-section-controls']//label//a";				//"//*[@id='work-flow-form']//div//form//div//fieldset//div[input[@id='Document3']]//label//a"; //[contains(text(),'Business Registration Certificate')]";
	public String profitLossStatement_Storage_Use_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[6][@class='column-section-controls']//label//a";
	
	//upload P&L statement
	//public String businessRegCertificate_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[6]/div/div/div/div/div/label/a";
	public String businessRegCertificate_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[6][@class='column-section-controls']//label//a";
	public String businessRegCertificate_Eqp_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//label//a";
	public String businessRegCertificate_Storage_Use_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[4][@class='column-section-controls']//label//a";
	//public String businessRegCertificate_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[input[@id='Document2']]//label//a";//[contains(text(),'Business Registration Certificate')]";
	//upload P&L statement
	//public String lastYearTaxCertificate_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset/div/div/div/div[7]/div/div/div/div/div/label/a";
	public String lastYearTaxCertificate_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[7][@class='column-section-controls']//label//a";//[contains(text(),'Last Year Tax Certificate')]";
	public String lastYearTaxCertificate_Storage_Use_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[5][@class='column-section-controls']//label//a";
	public String lastYearTaxCertificate_Eqp_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[4][@class='column-section-controls']//label//a";
	
	
	//Application submitted by
	public String applicationSubmittedBy = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='ApplicationSubmittedBy']";
	public String dateOfApplication_Xpath = "//input[@id='date-time-picker']";
	
	//save and submit button
	public String saveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn = "//*[@id='work-flow-form']//button[contains(text(),'Submit')]";
	
	//generic buttons
	public String btnPrefix_Xpath = "//*[@id='work-flow-form']//button[text()='";
	public String btnSuffix_Xpath = "']";
	
	//Transport data form
	public String packageType_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='PackageType']";
	public String packageCategory_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='Packagecategory']";	
	public String transportIndex_Xpath = "//input[@id='Transportindex']";
	public String transportMode_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//div[4][@class='column-section-controls']//fieldset//div";
	public String consignorFacility_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='ConsignorFacility']";
	public String originMemo_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//textarea[@id='Origin']";
	public String consigneeFacility_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='ConsigneeFacility']";
	public String destinationMemo_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//textarea[@id='Destination']";
	public String dateOfShipmentCalendarControl_Xpath = "//div[input[@id='Dateofshipment']]//div//button";
	public String dateOfReceiptCalendarControl_Xpath = "//div[input[@id='Dateofreceipt']]//div//button";
	public String exclusiveYesNo_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='Exclusiveuseyn']";
	public String specialArrangement_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//select[@id='Specialarrangementyn']";
	
	//**************************************************************
	
	//******************All forms 2 sections generic xpath prefix
	
	public String sectionPrefix_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div//";
	
	public String genericLocatorXpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div";
	//***************************************************************
	
	//DF- completeness check
	//completeness label on top
	public String completenessLabelStepTracker_Xpath = "//*[@id='work-flow-form']//div//span[contains(text(),'Completeness Check')]";
	
	//reviewed by
	public String reviewedByCC = "//select[@id='ReviewedByCC']";
	//application approved/Rejected
	public String approvedRejectedNotes_Xpath = "//textarea[@id='AppRejNotesCC']";
	
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
	public String newreviewAssessmenWFtid_Xpath = "//td[contains(text(),'Review and Assessment')]"; // "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'RA')]";
	public String inspWFtid_Xpath = "//td[contains(text(),'Authorization Inspection')]"; ////*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'INS')]";
	public String parentWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'Parent')]";
	//*[@id="collapsible-tabpanel-0"]/div/div/div/div/div[2]/div/div[1]/table/tbody/tr[2]/td[2]
	//payment id from table
	public String paymentWFid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr[2]/td[1]";
	public String newPaymentWFid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr//td[contains(text(),'PAY')]";
	
	//check box for isotope production
	public String raaApplicable_ChkBox_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label[@id='RAAapplicable']"; ////label[@id='RAAapplicable']
	
	//**************************************************************
	//DF- incompleteness check form
	public String incompleteRemarks_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//textarea[@id='IncompleteRemarks']";
		
	//***************************************************************
	
	//DF- review and assessment
	//authorization remarks
	public String authorizeRejectRemarks_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='column-section-controls']//div//textarea[@id='AuthRemarks']";//*[@id='AuthRemarks']";
	
	//reviewed by
	public String reviewedBy_Xpath = "//*[@id='ReviewedByRA']";
	
	//Authorize and reject button
	public String authorizeBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Authorize')]";
	public String rejectAuthBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject')]";	
	

	//**************************************************************
	
	//DF- Auth terms //input[@id='AuthStartDate']
	//start date
	public String authStartDate = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div[input[@id='AuthStartDate']]//div//button";  //div[input[@id='AuthStartDate']]//div//button"; //*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div[input[@id='AuthStartDate']]//div//button" ; //input[@id='AuthStartDate']"
	//end date
	public String authEndDate = "//*[@id='work-flow-form']//div//form//div//fieldset//div[4][@class='column-section-controls']//div[input[@id='AuthEndDate']]//div//button"; //input[@id='AuthEndDate']";
	
	//transfer WF auth date
	public String authDateOfTransfer = "//*[@id='work-flow-form']//div//form//div//fieldset//div[5][@class='column-section-controls']//div[input[@id='AuthDateofTransfer']]//div//button"; //*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='column-section-controls']//div[input[@id='AuthStartDate']]//div//button" ;
	//Auth transfer duration text
	public String authTransferDuration_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[6][@class='column-section-controls']//div//input[@id='AuthTransferDuration']";
	
	//Isotope prod auth condition xpath
	public String authMaxRadAct_Xpath = "//input[@id='AuthMaximumRadioActivity']";
	public String authMaxRadActUnit_Xpath = "AuthMaximumRadioActivityId";
	
	
	//terms and conditions text
	public String tncText_Xpath = genericLocatorXpath + "//textarea[@id='TermsAndConditions']";
	
	//save and submit button
	public String saveBtn1 = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn1 = "//*[@id='work-flow-form']//div//button[contains(text(),'Submit')]";
	public String proceedApprovalBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed for Approval')]";
	public String issueAuthTermsBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Issue Authorization Terms')]";
		
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
	public String downloadWordAuthorizationReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div[@class='document-format clearfix']//ul//li[1]//span//a//button";	//*[@id='work-flow-form']//div//form//div//fieldset//div[3]//ul//li[1]//span//a//button";
	public String downloadPdfAuthorizationReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div[@class='document-format clearfix']//ul//li[2]//span//a//button";	//*[@id='work-flow-form']//div//form//div//fieldset//div[3]//ul//li[2]//span//a//button";
	
	//reviewed by
	public String authorizationApprovedBy_Xpath = "//*[@id='ReviewedByCA']";
	
	//approval/ rejection notes
	public String approvalRejectionNotes_Xpath = "//textarea[@id='ApprovalRemarks']"; 
	//*[@id='work-flow-form']//div//form//div//fieldset//div[3][@class='column-section-controls']//div//textarea[@id='CertRejRemarks']";
	//public String approvalRejectionNotes_Xpath = "//*[@id='CertRejRemarks']";
		
	//approve and reject button
	public String approveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Approve Authorization')]";
	public String rejectBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject Certificate')]";	
	public String noReminderNotificationBtn_Xpath = "//button[contains(text(),'Reminder Notification Not Needed')]";
		
	//Page Constants *********************************************************************
	
	//success message xpath
		public String SuccessMsg_XPath = "//*[@id='message-success']";
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
