package pageLocators_Elements.RAIS;

public class AddNewUserDetailsPage {
	//Add new security profile page
	public String SaveBtn_XPath = "//button[text()='Save']";
	public String cancelBtn_XPath = "//button[text()='Cancel']";
	public String deleteBtn_XPath = "//button[text()='Delete']";
	
	//labels and buttons
	public String addNewUserDetail_orgType_XPath = "//*[@id='user']//label[text()='Organisation Type']";
	public String addNewUserDetail_org_XPath = "//*[@id='user']//label[text()='Organisation']";
	public String addNewUserDetail_auth_XPath = "//*[@id='user']//label[text()='Authentication']";
	public String addNewUserDetail_selectuser_XPath = "//*[@id='user']//label[text()='Select User']";
	public String addNewUserDetail_userName_XPath = "//*[@id='user']//label[text()='User Name']";
	public String addNewUserDetail_email_XPath = "//*[@id='user']//label[text()='Email Address']";
	public String addNewUserDetail_funcRole_XPath = "//*[@id='user']//label[text()='Functional Role']";
	public String addNewUserDetail_dataRole_XPath = "//*[@id='user']//label[text()='Data Role']";
	public String addNewUserDetail_status_XPath = "//*[@id='user']//label[text()='Status']";
	
	//Select user text box xpath
	public String addnewUser_selectUser_XPath = "//*[@name='selectUser']";
	public String addnewUser_userName_XPath = "//*[@name='username']";
	
	//dropdown details
	public String addnewUser_orgType_XPath = "//*[@id='organisationType']";
	public String addnewUser_org_XPath = "//*[@id='organisation']";
	
	//radio button text click
	public String addnewUser_rdobtnAuth_XPath = "//*[@id='authentication']//span[text()='Edit']";
	
	//Validation msgs xpath
	public String orgTypeRequired_MsgXPath = "//*[@id='user']//p[text()='Organisation Type is required.']"	;
	public String orgRequired_MsgXPath = "//*[@id='user']//p[text()='Organisation is required.']"	;
	public String validUserRequired_MsgXPath = "//*[@id='user']//p[text()='Please select valid user.']"	;
	public String userNameRequired_MsgXPath = "//*[@id='user']//p[contains(text(),'user name.')]"	;
	public String emailRequired_MsgXPath = "//*[@id='user']//p[text()='Email is required.']"	;
	public String funcRoleRequired_MsgXPath = "//*[@id='user']//p[text()='Please select at least 1 Functional Role.']"	;
	public String dataRoleRequired_MsgXPath = "//*[@id='user']//p[text()='Data Role is required.']"	;
	public String statusRequired_MsgXPath = "//*[@id='user']//p[text()='Please select status.']"	;
	
	
	//Page Constants *********************************************************************
		//Add new Security profile page
		public String ADDNEWUSER_ORGTYP_Txt = "Organisation Type";
		public String ADDNEWUSER_ORG_Txt = "Organisation";
		public String ADDNEWUSER_AUTH_Txt = "Authentication";
		public String ADDNEWUSER_SLCTUSER_Txt = "Select User";
		public String ADDNEWUSER_USRNAME_Txt = "User Name";
		public String ADDNEWUSER_EMAIL_Txt = "Email Address";
		public String ADDNEWUSER_FUNCROLE_Txt = "Functional Role";
		public String ADDNEWUSER_DATAROLE_Txt = "Data Role";
		public String ADDNEWUSER_STATUS_Txt = "Status";
		
		
		//Validation msgs
		public String ADDNEWUSER_ORGTYP_REQD_TXT = "Organisation Type is required.";
		public String ADDNEWUSER_ORG_REQD_TXT = "Organisation is required.";
		public String ADDNEWUSER_SELCTUSR_REQD_TXT = "Please select valid user.";
		public String ADDNEWUSER_NAME_REQD_TXT = "Please enter user name.";
		public String ADDNEWUSER_EMAIL_REQD_TXT = "Email is required.";
		public String ADDNEWUSER_FR_REQD_TXT = "Please select at least 1 Functional Role.";
		public String ADDNEWUSER_DR_REQD_TXT = "Data Role is required.";
		public String ADDNEWUSER_STATUS_REQD_TXT = "Please select status.";
		
		
		//Buttons
		public String SAVE_BTN_Txt = "Save";
		public String CANCEL_BTN_Txt = "Cancel";
				
		
		

}
