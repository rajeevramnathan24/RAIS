package pageLocators_Elements.RAIS;

public class Workflow_ReviewAssessment_Page {

	//DF -  External Review
	//external assessment buttons  location xpath
	public String externalAssessmentRequiredBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'External Assessment Required')]";	
	public String externalAssessmentNOTRequiredBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Not')]";	

	//************************************************************

	//DF -  Internal Review remarks
	public String internalReviewRemarksTxt_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[1][@class='column-section-controls']//div//textarea[@id='IntReviewRemarks']";
	//public String internalReviewRemarksTxt_Xpath = "//*[@id='IntReviewRemarks']";
	public String requestInformationBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Request Information')]";	
	public String inspectionNeededBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Inspection Needed')]";
	public String proceedBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed')]";
	
	//step tracker internal review remarks form
	public String internalReviewReviewStepTracker_Xpath ="//*[@id='work-flow-form']//div//span[contains(text(),'Internal Review Remarks -  Without External Review')]";
	
	//linked processes
	public String linkedProcess_Xpath = "//div//header//button[@id='collapsible-tab-undefined']/span[contains(text(),'Linked Processes']";
	
	//R&A id from table
	public String inspectionWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr[1]/td[1]";

	//************************************************************

	//DF -  recommendation
	public String recommendationRemarksTxt_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[1][@class='column-section-controls']//div//textarea[@id='ReviewRecommendation']";
	//public String recommendationRemarksTxt_Xpath = "//*[@id='ReviewRecommendation']";
	
	//external assessment buttons  location xpath
	public String improvementNeededBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Improvement Needed')]";	
	public String proceedFurtherBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed Further')]";	
	//************************************************************

	//DF -  Approval
	//R&A report download
	public String downloadReviewAssessmentReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//ul//li//span//a//button";
	
	//reviewed by
	public String reviewAssessmentApprovedBy_Xpath = "//*[@id='ReviewedBy']";
	
	//approval/ rejection notes
	public String approvalRejectionNotes_Xpath = "//*[@id='AppRejNotes']";
		
	//approve and reject button
	public String approveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Approve')]";
	public String rejectBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject')]";	
	
	
	//Page Constants *********************************************************************

	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";



}
