package pageLocators_Elements.RAIS;

public class Workflow_Inspection_Parent_ChildPage {
	
	//Childinspection
	//DF - Inspection scope
	
	//inspectionid
	public String inspectionChildWFtid_Xpath = "//*[@id='collapsible-tabpanel-0']//div//table//tbody//tr[1]/td[1]";
	
	//Department location xpath
	public String department_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[2]//fieldset//div";	
	//public String department_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[2][@class='column-section-controls']//fieldset//div";
	
	//public String department_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[2]//fieldset//div";
	//sealed source 
	public String sealedSource_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[3]//fieldset//div";	
	//public String sealedSource_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//div[1]//div//div[3]//div//div//div//div//div//fieldset//div";
	//unsealed source
	public String unsealedSource_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[4]//fieldset//div";
	//radiation generator
	public String radGenerator_Xpath ="//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='col-sm-6 col-flex-1 renderer-section-2']//div[5]//fieldset//div";
	//Associated Equipment
	public String assoEquipment_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[6]//fieldset//div";
	
	//regular inspection dropdown
	public String regularInspection_Xpath = "//*[@id='RegularInspection']"; 
	
	//Full inspection dropdown
	public String fullInspection_Xpath = "//*[@id='FullInspection']";
	
	//scheduled date of inspection
	public String scheduledDateofInspection_Xpath = "//div[input[@id='ScheduledDate']]//div//button";
	
	//select one particular date
	public String selectSpecificDate_Xpath = "/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div[3]/div[2]/button";
	
	//scope of inspection
	public String scopeOfInspection_Xpath = "//*[@id='ScopeOfInspection']";
	
	//Announce and direct inspection button
	public String announceInspectionBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Announce Inspection')]";
	public String withoutAnnounceInspectionBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed Without Announcement')]";
	
	//generic buttons
	public String btnPrefix_Xpath = "//*[@id='work-flow-form']//button[text()='";
	public String btnSuffix_Xpath = "']";
	
	//************************************************************
	
	//DF- External inspection DF
	public String externalInspectionBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'External')]";
	public String internalInspectionBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Internal')]";
	//************************************************************
	
	
	//DF- Internal inspection
	//scheduled date of inspection
	public String internalInspectionDate_Xpath = "//div[input[@id='IntInspectionDate']]//div//button";//input[@id='IntInspectionDate']";
	public String internalInspectionFindings_Xpath = "//*[@id='IntInspectionFindings']";
	
	//Announce and direct inspection button
	public String requestInfoBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Request Information')]";
	public String proceedInspectionBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Proceed')]";
	//************************************************************
	
	//DF- Inspection conclusion
	//Inspection conclusion
	public String inspectionConclusion_Xpath = "//*[@id='InspectionConclusion']"; //*[@id="InspectionConclusion"]
	
	//save and submit button
	public String saveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn = "//*[@id='work-flow-form']//button[text()='Submit']";
	//************************************************************
	
	//DF- Inspection approval
	//Inspection report download
	public String downloadInspectionReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='column-section-controls']//div//ul//li//span//a//button";
	//public String downloadInspectionReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//ul//li//span//a//button//span[1]";
	//public String downloadInspectionReport_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[1][@class='column-section-controls']//div//ul//li//span//a//button//span[1]";
		
	//approved by
	public String inspectionApprovedBy_Xpath = "//*[@id='ReviewedByApp']";
	
	//approval or rejection notes
	public String approvalRejectionNotes_Xpath = "//*[@id='AppRejNotes']";
	
	//approve and reject button
	public String approveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Approve')]";
	public String rejectBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject')]";
		
	//step tracker internal review remarks form
	public String inspectionApprovalStepTracker_Xpath ="//*[@id='work-flow-form']//div//span[contains(text(),'Inspection Approval')]";
			

	//Page Constants *********************************************************************
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
