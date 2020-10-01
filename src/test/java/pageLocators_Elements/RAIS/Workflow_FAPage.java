package pageLocators_Elements.RAIS;

public class Workflow_FAPage {
	
	//WFID
	public String wfId_displayedonTop = "//*[@id='work-flow-form']/div[1]/span";
	
	//source location xpath
	public String sourceLocation_Xpath = "//*[@id='SourceLocation']";
	
	//source status 
	public String sourceStatus_Xpath = "//*[@id='SourceStatus']";

	//date field
	public String sourceStatusDate_Xpath = "//div[input[@id='Date']]//div//button"; //"//*[@id='Date']"; //"//div[input[@id='AuthStartDate']]//div//button"

	//IMport & export Date
	public String importDate_Xpath ="//div[input[@id='ImportDate']]//div//button"; //"//*[@id='ImportDate']"; 
	public String exportDate_Xpath ="//div[input[@id='CustomsExportDate']]//div//button"; 
	
	
	//security plan
	public String securityPlan_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div[2]/div[2]/fieldset/div/div/div/div[2]/div/div/div/div/div/label/a";

	//import custom number
	public String importCustomNum_Xpath = "//*[@id='CustomsNumber']";

	//bill lading number
	public String importBillLadingNum_XPath = "//*[@id='BillLadingNumber']";

	//bill lading date
	public String importBillLadingdate_XPath = "//div[input[@id='BillLadingDate']]//div//button"; //"//*[@id='BillLadingDate']";

	//save button on FApage1
	public String saveBtn_FAPage1_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[1]";
	
	//submit buttom on FAPage1
	public String submitBtn_FAPage1_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Submit')]"; //"//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[2]";
	//public String submitBtn1 = "//*[@id='work-flow-form']//div//button[contains(text(),'Submit')]";
	
	//success message xpath
	public String SuccessMsg_XPath = "//*[@id='message-success']";
		
	//approval form status date
	public String approvalFrmStatusDate_Xpath = "//div[input[@id='StatusDate']]//div//button"; //"//*[@id='StatusDate']";

	//remarks status
	public String approvalFrmRemarks_Xpath = "//*[@id='Remarks']";
	
	//approved by drop down
	public String approvalFrmApprovedBy_Xpath = "//*[@id='ReviewedBy']";
	
	//approve button
	public String approveBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Approve')]"; //"//*[@id='work-flow-form']/div[3]/div[2]/div[1]/div/button[1]"
	public String rejectBtn_Xpath = "//*[@id='work-flow-form']//div//button[contains(text(),'Reject')]";
	//select one particular date
	public String selectSpecificDate_Xpath = "/html/body/div[7]/div[3]/div/div[2]/div[2]/div/div[3]/div[2]/button";
	
	//source returned
	//check box
	public String sourceReturnedToParent_ChkBox_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label[@id='SourceReturned']";
	public String receipientCountryApproval_ChkBox_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label[@id='RecCountryApp']";
	
	//Return date
	public String returnDate_XPath = "//div[input[@id='ReturnDate']]//div//button";

	//Page Constants *********************************************************************
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
