package pageLocators_Elements.RAIS;



public class AddNewRolePage {

	//Locator details
	public String inputroleName_XPath = "//input[@id='name']";
	public String inputInternalName_XPath = "//*[@id='internalName']";
	public String resetBtn_XPath = "/html/body/div[6]/div[3]/div/div/div[3]/div/button[1]";
	
	//public String addBtn_XPath = "/html/body/div[6]/div[3]/div/div/div[3]/div/button[3]";
	public String addRolePopUpHeaderLabel_XPath = "//*[@id='max-width-dialog-title']//h2//span[text()='Add Role']";
	public String editRolePopUpHeaderLabel_XPath = "//*[@id='max-width-dialog-title']//h2//span[text()='Edit Role']";
	public String nameLabel_XPath = "/html/body/div[6]/div[3]/div/div/div[2]/div/div/form/div[1]/div/div[1]/label";
	public String internalNameLabel_XPath = "/html/body/div[6]/div[3]/div/div/div[2]/div/div/form/div[2]/div/div[1]/label";
	public String nameisReq_XPath = "/html/body/div[6]/div[3]/div/div/div[2]/div/div/form/div[1]/div/div[2]/p";
	public String intNameisReq_XPath = "/html/body/div[6]/div[3]/div/div/div[2]/div/div/form/div[2]/div/div[2]/p";
	
	public String name_label_XPath = "//label[contains(text(),'Name')]";
	
	//public String editBtn_XPath = "//button[text()='Save']";
	public String editBtn_XPath = "//div[@class='dialog-action']//div//button[text()='Save']";
	
	//public String addBtn_XPath = "//button[text()='Save']";
	public String addBtn_XPath = "//div[@class='dialog-action']//div//button[text()='Save']";
	public String SaveBtn_XPath = "//div[@id='datarole']//div//button[2][text()='Save']";
	public String FRSaveBtn_XPath = "//div[@id='functionalrole']//div//button[2][text()='Save']";
	
	//public String addBtn_XPath = "//button[@title='Save']";
	
	//public String cancelBtn_XPath = "//button[text()='Cancel']";
	public String cancelBtn_XPath = "//div[@id='datarole']//div//button[text()='Cancel']";
	public String FRcancelBtn_XPath = "//div[@id='functionalrole']//div//button[text()='Cancel']";
	
	//success message
	public String addnewRole_SuccessMsg_XPath = "//div[@id='message-success']";
	
	//validation msgXPath
	public String nameRequired_MsgXPath = "//div[@class='dialog-content']//p[contains(text(),'Name is required.')]"	;//							"//*p[contains(text(),'Name is required.')]";
	public String duplicateName_MsgXPath = "//p[contains(text(),'This Name already exists. Please enter a different Name')]";
	//24-Aug
	public String validMsgXpath = "//*[@id='name-helper-text']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants ********************************************************
	//Add role page
	public String ADDROLE_HEADER_Txt ="Add Role";
	public String NAME_LABEL_Txt ="Name*";
	public String INTERNAL_NAME_LABEL_Txt ="Internal Name*";
	public String RESET_BTN_Txt ="Reset";
	public String CANCEL_BTN_Txt ="Cancel";
	public String ADD_BTN_Txt ="Save";
	public String NAME_REQD_TXT = "Name is required.";
	public String DUPLICATENAME_MSG_TXT = "This Name already exists. Please enter a different Name";
	public String ADDNEWROLE_SUCESSMSG_TXT = "New Data Role has been added successfully.";
	public String EDITNEWROLE_SUCESSMSG_TXT = "Data Role has been updated successfully.";
	public String DELETEROLE_SUCCESSMSG_TXT = "Data Role has been deleted successfully.";
	public String DUPDR_TXT = "Duplicate data not allowed.";
	
	public String ADDNEWFUNC_ROLE_Txt = "New Functional Role has been added successfully.";
	public String UPDATEFUNC_ROLE_Txt = "Functional Role has been updated successfully.";
	public String DELETEFUNC_ROLE_Txt = "Functional Role has been deleted successfully.";
	
}
