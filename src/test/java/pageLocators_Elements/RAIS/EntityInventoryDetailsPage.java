package pageLocators_Elements.RAIS;

public class EntityInventoryDetailsPage {
	//Entity Inventory details page
	public String InvPageid_XPath = "//*[@id='entity-inventory-form']";

	public String SaveBtn_XPath = InvPageid_XPath+"//button[text()='Save']";
	public String cancelBtn_XPath = InvPageid_XPath+"//button[text()='Cancel']";
	public String deleteBtn_XPath = InvPageid_XPath+"//button[text()='Delete']";
	public String nextBtn_XPath = InvPageid_XPath+"//button[contains(text(),'Next')]";

	//labels and buttons
	public String invenFormDetails_Checkbox_XPath = "//*[@id='entity-inventory-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Radioactive Material']";
	public String invenFormDetails_Txt_XPath="//*[@id='entity-inventory-form']//form//div[2]//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]//label[text()='City Name']";
	public String invenFormDetails_Numeric_XPath = "//*[@id='entity-inventory-form']//form//div[2]//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]//label[text()='Postal Code']";
	//public String invenFormDetails_Numeric_XPath = "//*[@id='entity-inventory-form']//div//div//div//div[1]//form//div[2]//div[2]//div//div[2]//div//div[2]//div//div//div[1]//label[text()='Postal Code']";

	//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]

	//Numeric field txt xpath
	public String invenFormDetails_inputNumericFld_XPath = "//*[@id='nmricFrmBldrAuto']";

	//Text field txt xpath
	public String invenFormDetails_inputTextFld_XPath = "//*[@id='txtFrmBldrAuto']";

	//Name field txt xpath
	public String invenFormDetails_inputNameFld_XPath = "//*[@id='Name']";

	//Factor field xpath
	public String invenFormDetails_FactorFld_XPath = "//*[@id='Factor']";

	//Name field name xpath
	public String invenFormDetails_inputFld_XPath = "//*[@id='Field']";

	//Gender drop down
	public String invenFormDetails_genderFld_Xpath = "//*[@id='Gender']";

	//Authority drop down
	public String invenFormDetails_authorityFld_Xpath = "//*[@id='Authority']";

	//Email field name Xpath
	public String invenFormDetails_emailFld_Xpath = "//*[@id='Email']";
	public String invenFormDetails_NewemailFld_Xpath = "//*[@id='eMail']";
	

	//Facility field name Xpath
	public String invenFormDetails_facilityFld_Xpath = "//*[@id='Facility']";
	public String invenFormDetails_facilityStatusFld_Xpath = "//*[@id='FacilityStatus']";
	
	//Department field name Xpath
	public String invenFormDetails_deptFld_Xpath = "//*[@id='Department']";
	
	//Partner Agency field xpath
	public String invenFormDetails_PartAgency_XPath = "//*[@id='PartnerAgency']";
	
	//Legal person field xpath
	public String invenFormDetails_LegalPerson_XPath = "//*[@id='LegalPersonNew']";
	
	//Datetime picker field Xpath
	public String invenFormDetails_dateOnly_Xpath = "//*[@id='date-picker-dialog']";
	public String invenFormDetails_dateTimeFld_Xpath = "//*[@id='date-time-picker']";
	//public String invenFormDetails_dateTimeBtn_Xpath = "//*[class='MuiFormControl-root MuiTextField-root col-form-control col-date-control MuiFormControl-marginNormal']//button//span";
	public String invenFormDetails_dateTimeBtn_Xpath = "//*[@class='control-renderer']//button//span";
	
	
	//public String invenFormDetails_dateTimeOKBtn_Xpath = "//*[@class='MuiDialogActions-root MuiDialogActions-spacing']//button[2]//span[contains(text(),'OK')]";
	public String invenFormDetails_dateTimeOKBtn_Xpath = "//*[@class='MuiDialogActions-root MuiDialogActions-spacing']//button//span[contains(text(),'OK')]";

	//certificate name field xpath
	public String invenFormDetails_certFld_Xpath = "//*[@id='CertificateNo']";
	
	//Fax xpath
	public String invenFormDetails_faxFld_Xpath = "//*[@id='Fax']";

	//Multi select dropdown
	public String restriction_DropdnClick_Xpath = "//div[@class='mul-input col-form-control']";

	//value to select for multi select
	public String restriction_add_dropdown_XPath = "//*[contains(text(),'Address')]";
	public String restriction_dept_dropdown_XPath = "//*[contains(text(),'Department')]";
	public String officerType_MultiSelect_XPath = "//*[contains(text(),'License')]";

	//radio button text click
	public String rdobtnEdit_XPath = "//*[@id='securityprofile']//span[contains(text(),'Edit')]";
	
	//Expert page partner Addnew
	public String addNewPartAgency = InvPageid_XPath+"//div[2]/div[1]/form/div/div[2]/div/div/div/div[3]/div/div/div[2]/span/a";
	
	//Facility Addnew link
	public String addNewFacilityStatus = InvPageid_XPath+"//div[2]/div[1]/form/div/div[2]/div/div[1]/div/div[3]/div/div/div[2]/span/a";

	//Validation msgs xpath
	public String nameRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Name is required')]"	;
	public String entityRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Entity is required')]"	;
	public String fieldRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Fields are required')]"	;
	public String restrictRequired_MsgXPath = "//*[@id='securityprofile']//p[contains(text(),'Restriction is required')]"	;

	//success message
	public String form_SuccessMsg_XPath = "//div[@id='message-body']";
	public String form_PopTitleXPath = "//*[@id='max-width-dialog-title']";
	public String form_PopupMSGTxt_XPath= "//div[@class='dialog-content']";
	public String form_PopupNoBtn_XPath = "//div[@class='dialog-action']"; 

	//Pop message yes/ no buttons
	public String popUpNoBtn_XPath = "//button[contains(text(),'No')]";
	public String popUpYesBtn_XPath = "//button[contains(text(),'Yes')]";






















	//Page Constants *********************************************************************
	//Add new Security profile page
	public String invenFormDetails_NAME_Txt = "Name";
	public String invenFormDetails_ENTITY_Txt = "Entity";
	public String invenFormDetails_FIELD_Txt = "Fields";
	
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
