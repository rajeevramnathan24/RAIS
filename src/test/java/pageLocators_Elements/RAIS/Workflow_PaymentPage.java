package pageLocators_Elements.RAIS;

public class Workflow_PaymentPage {
	
	//DF - Attach invoice
	//upload invoice xpath
	public String uploadInvoice_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label//a[contains(text(),'Invoice')]";
	public String newuploadInvoice_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div[5][@class='column-section-controls']//label//a";  //*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset//div//label//a";
	
	//save and submit button
	public String saveBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Save')]";
	public String submitBtn = "//*[@id='work-flow-form']//div//button[contains(text(),'Submit')]";
	//************************************************************
	
	//DF -confirm payment terms
	//amout paid
	public String amountPaid_Xpath = "//input[@id='AmountPaid']";
	//payment medium
	public String paymentMedium_Xpath = "//input[@id='PayMedium']";
	//receipt
	public String paymentReceipt_Xpath = "//*[@id='work-flow-form']//div//form//div//fieldset//div//label//a[contains(text(),'Receipt')]";
	public String newPaymentReceipt_Xpath = "//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset//div[2][@class='col-sm-6 col-flex-1 renderer-section-2']//div[4][@class='column-section-controls']//label//a"; 
	//*[@id='work-flow-form']/div[3]/div[2]/form/div/div[2]/fieldset//div//label//a";
	

	//Page Constants *********************************************************************
	
	//status pending & paid
	public String STATUS_PENDING_Txt = "Pending";
	public String STATUS_PAID_Txt = "Paid";
	
	//Record updated
	public String successfulMsg_Txt = "Record updated successfully";
	


}
