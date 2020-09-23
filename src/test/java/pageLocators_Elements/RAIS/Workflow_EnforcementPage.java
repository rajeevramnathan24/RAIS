package pageLocators_Elements.RAIS;

public class Workflow_EnforcementPage {
	
	//source location xpath
	public String sourceLocation_Xpath = "//*[@id='SourceLocation']";
	
	//source status 
	public String sourceStatus_Xpath = "//*[@id='SourceStatus']";

	//date field
	public String sourceStatusDate_Xpath = "//*[@id='Date']";

	//IMport Date
	public String importDate_Xpath ="//*[@id='ImportDate']"; 
	
	//security plan
	public String securityPlan_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div[2]/div[2]/fieldset/div/div/div/div[2]/div/div/div/div/div/label/a";

	//import custom number
	public String importCustomNum_Xpath = "//*[@id='CustomsNumber']";

	//bill lading number
	public String importBillLadingNum_XPath = "//*[@id='BillLadingNumber']";

	//bill lading date
	public String importBillLadingdate_XPath = "//*[@id='BillLadingDate']";

	//save button on FApage1
	public String saveBtn_FAPage1_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[1]";
	
	//submit buttom on FAPage1
	public String submitBtn_FAPage1_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[2]";
	
	//success message xpath
		public String SuccessMsg_XPath = "//*[@id='message-success']";
		
	//approval form status date
	public String approvalFrmStatusDate_Xpath = "//*[@id='StatusDate']";

	//remarks status
	public String approvalFrmRemarks_Xpath = "//*[@id='Remarks']";
	
	//approved by drop down
	public String approvalFrmApprovedBy_Xpath = "//*[@id='ReviewedBy']";
	
	//approve button
	public String approveBtn_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[1]";

	//Page Constants *********************************************************************
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
