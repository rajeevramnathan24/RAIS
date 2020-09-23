package pageLocators_Elements.RAIS;

public class AddNewSecurityProfilePage {
	//Add new security profile page
	public String SaveBtn_XPath = "//*[@id='securityprofile']//button[text()='Save']";
	public String cancelBtn_XPath = "//*[@id='securityprofile']//button[text()='Cancel']";
	public String deleteBtn_XPath = "//*[@id='securityprofile']//button[text()='Delete']";
	
	//labels and buttons
	public String addNewSP_name_XPath = "//*[@id='securityprofile']//label[contains(text(),'Name')]";
	public String addNewSP_entity_XPath = "//*[@id='securityprofile']//label[contains(text(),'Entity')]";
	public String addNewSP_field_XPath = "//*[@id='securityprofile']//label[contains(text(),'Fields')]";
	public String addNewSP_restrict_XPath = "//*[@id='securityprofile']//label[contains(text(),'Restriction')]";
	
	//Text box xpath
	public String addnewSP_inputroleName_XPath = "//input[@id='name']";
	
	//facility dropdown selectedEntity
	public String addnewSP_entityNameDropdown_XPath = "//*[@id='entityInternalName']";
	
	//Multi select dropdown
	public String restriction_DropdnClick_Xpath = "//div[@class='mul-input col-form-control']";
	
	//value to select for multi select
	public String restriction_add_dropdown_XPath = "//*[contains(text(),'Address')]";
	public String restriction_dept_dropdown_XPath = "//*[contains(text(),'Department')]";
	
	//radio button text click
	public String rdobtnEdit_XPath = "//*[@id='securityprofile']//label[2]//span[2][contains(text(),'Edit')]";
	public String rdobtnView_XPath = "//*[@id='securityprofile']//label[1]//span[2][contains(text(),'View')]";
	
	//Validation msgs xpath
	public String nameRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Name is required')]"	;
	public String entityRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Entity is required')]"	;
	public String fieldRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Fields are required')]"	;
	public String restrictRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Restriction is required')]"	;
	
	//success message
		public String secProf_Msg_XPath = "//div[@id='message-body']";
		public String deleteSP_PopTitleXPath = "//*[@id='max-width-dialog-title']";
		public String delSPRole_PopupMSGTxt_XPath= "//div[@class='dialog-content']";
		public String delSPRole_PopupNoBtn_XPath = "//div[@class='dialog-action']"; 
		
		//Pop message yes/ no buttons
		public String popUpNoBtn_XPath = "//button[contains(text(),'No')]";
		public String popUpYesBtn_XPath = "//*[@id='securityprofile']/div/div[2]/div/div/div[2]/div[2]/div/button[2]";
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants *********************************************************************
		//Add new Security profile page
		public String ADDNEW_SP_NAME_Txt = "Name";
		public String ADDNEW_SP_ENTITY_Txt = "Entity";
		public String ADDNEW_SP_FIELD_Txt = "Fields";
		public String ADDNEW_SP_RESTRICT_Txt = "Restriction";
		
		//Validation msgs
		public String ADDNEW_SP_NAME_REQD_TXT = "Name is required";
		public String ADDNEW_SP_ENTITY_REQD_TXT = "Entity is required";
		public String ADDNEW_SP_FLD_REQD_TXT = "Fields are required";
		public String ADDNEW_SP_RESTRICT_REQD_TXT = "Restriction is required";
		
		//Buttons
		public String SAVE_BTN_Txt = "Save";
		public String CANCEL_BTN_Txt = "Cancel";
		public String NO_BTN_Txt = "No";
		public String YES_BTN_Txt = "Yes";
		
		//New SP msgs
		public String DELSP_POPUP_CONF_MSG_Txt="Confirmation";
		public String ADDNEWSP_SUCESSMSG_TXT = "New Security Profile has been added successfully.";
		public String UPDATESP_SUCESSMSG_TXT = "Security Profile has been updated successfully.";
		public String DELSP_SUCESSMSG_TXT = "Security Profile has been deleted successfully.";
		
		public String DELSP_POPMSG_Txt = "Do you really want to delete this security profile?";
		

}
