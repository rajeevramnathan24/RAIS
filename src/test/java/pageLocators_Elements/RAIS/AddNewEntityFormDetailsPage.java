package pageLocators_Elements.RAIS;

public class AddNewEntityFormDetailsPage {
	//Add new security profile page
	public String SaveBtn_XPath = "//*[@id='entity-form']//button[text()='Save']";
	public String cancelBtn_XPath = "//*[@id='entity-form']//button[text()='Cancel']";
	public String deleteBtn_XPath = "//*[@id='entity-form']//button[text()='Delete']";

	//labels and buttons
	public String entityFormDetailsPage_Checkbox_XPath = "//*[@id='entity-form']//label[@id='chkBoxFrmBldrAuto']//span[text()='Radioactive Material']";
	public String entityFormDetailsPage_Txt_XPath="//*[@id='entity-form']//form//div[2]//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]//label[text()='City Name']";
	public String entityFormDetailsPage_Numeric_XPath = "//*[@id='entity-form']//form//div[2]//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]//label[text()='Postal Code']";
	//public String entityFormDetailsPage_Numeric_XPath = "//*[@id='entity-form']//div//div//div//div[1]//form//div[2]//div[2]//div//div[2]//div//div[2]//div//div//div[1]//label[text()='Postal Code']";

	//div[@class='col-sm-6 col-flex-1 renderer-section-2'][2]

	//Numeric field txt xpath
	public String entityFormDetailsPage_inputNumericFld_XPath = "//*[@id='nmricFrmBldrAuto']";

	//Text field txt xpath
	public String entityFormDetailsPage_inputTextFld_XPath = "//*[@id='txtFrmBldrAuto']";

	//Name field txt xpath
	public String entityFormDetailsPage_inputNameFld_XPath = "//*[@id='Name']";
	
	//Practice field txt xpath
	public String entityFormDetailsPage_practiceFld_XPath = "//*[@id='Practice']";

	//Factor field xpath
	public String entityFormDetailsPage_FactorFld_XPath = "//*[@id='Factor']";

	//Name field name xpath
	public String entityFormDetailsPage_inputFld_XPath = "//*[@id='Field']";

	//Gender drop down
	public String entityFormDetailsPage_genderFld_Xpath = "//*[@id='Gender']";
	
	//Authority drop down
	public String entityFormDetailsPage_authorityFld_Xpath = "//*[@id='Authority']";
	//public String entityFormDetailsPage_AuthHyperLink_XPath = "//*[@id='entity-form']//div[@class='column-section']//div[3]//span[contains(text(),'Authority')]";
	public String entityFormDetailsPage_AuthHyperLink_XPath =  "//*[@id='entity-form']/div/div/div/div[1]/form/div/div[2]/div/div/div/div[3]/div/div/div[2]/span/a";
	
	//Email field name Xpath
	public String entityFormDetailsPage_emailFld_Xpath = "//*[@id='Email']";
	
	//Legal basis
	public String entityFormDetailsPage_legalBasisFld_Xpath = "//*[@id='LegalPerson']";
	
	//Chairperson xpath
	public String entityFormDetailsPage_ChairPrsonFld_Xpath = "//*[@id='ChairPerson']";
	
	//RAN field txt xpath
	public String entityFormDetailsPage_RANFld_XPath = "//*[@id='RAN']";
	
	//facility Status xpath
	public String entityFormDetailsPage_status_Xpath = "//*[@id='FacilityStatus']";
	
	//practice multi select
	public String pracMultiSelect_Xpath = "//*[@id='nav-tabpanel-0']//div[@class='mul-input col-form-control']";
	
	//region field xpath
	public String entityFormDetailsPage_region_Xpath = "//*[@id='Region']";
	
	//District field xpath
	public String entityFormDetailsPage_district_Xpath = "//*[@id='District']";
		
	//Multi select dropdown
	public String restriction_DropdnClick_Xpath = "//div[@class='mul-input col-form-control']";
	
	//multiselect drop down for FR value select on User mgmt screen
	public String userMgmt_FRdrpdwn_Xpath = "//*[@id='user']//div[@class='mul-input col-form-control']";
	
	//inspection frequency
	public String inspFrequency_XPath ="//*[@id='InspectionFrequency']"; 
	public String inspFrequencyID_XPath ="//*[@id='InspectionFrequencyId']";
	public String practiceCat_XPath ="//*[@id='PracticeCategory']";
	
	//filter id
	public String idFilter = "//*[@id='filter']";
	
	public String topErrorMsg = "//*[@id='error-content']";
	

	//value to select for multi select
	public String restriction_add_dropdown_XPath = "//*[contains(text(),'Address')]";
	public String restriction_dept_dropdown_XPath = "//*[contains(text(),'Department')]";
	public String LicenseeFR_MultiSelect_XPath = "//*[contains(text(),'License')]";
	public String Regulator_MultiSelect_XPath = "//*[text()='Regulator']";
	public String userMgmt_MultiSelectFR_XPath = "//*[@id='userForm']//div[@class='mul-input col-form-control']//label[contains(text(),'Licensee')]";
	public String userMgmt_MultiSelectFR_XPath1 = "//*[@id='userForm']///div//div[2]//div//div//div[2]//label[contains(text(),'Licensee')]";

	//FR concatenate
	public String FR_prefix = "//*[text()='";
	public String FR_suffix = "']";
	
	//radio button text click
	public String rdobtnEdit_XPath = "//*[@id='securityprofile']//span[contains(text(),'Edit')]";

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
	public String popUpYesBtn_XPath = "//*[@id='entity-form']/div/div[2]/div[2]/div/div/div[2]/div[2]/div/button[2]";






















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

	//officer task name
	public String LICENSE_Txt = "License";
	
	//Buttons
	public String SAVE_BTN_Txt = "Save";
	public String CANCEL_BTN_Txt = "Cancel";
	public String NO_BTN_Txt = "No";
	public String YES_BTN_Txt = "Yes";

	//New SP msgs
	public String DELSP_POPUP_CONF_MSG_Txt="Confirmation";
	public String ADDNEWRECORD_SUCESSMSG_TXT = "Record created successfully";
	public String UPDATERECORD_SUCESSMSG_TXT = "Record updated successfully";
	public String DELRECORD_SUCCESSMSG_TXT = "Record deleted successfully";

	public String DELSP_POPMSG_Txt = "Do you really want to delete this security profile?";
	
	public String topErrorMsg_Txt = "This record can not be deleted as this is already referenced into the system.";


}
