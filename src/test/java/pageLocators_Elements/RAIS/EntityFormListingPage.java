package pageLocators_Elements.RAIS;

public class EntityFormListingPage {
	//Add new form btn
	public String addNewBtn_XPath = "//*[@id='entity-details']//button[text()='Add New']";
	
	public String formId = "entity-details";
		
	public String FacListingaddNewBtn_XPath = "//*[@id='entity-details']//button[2][contains(text(),'Add New')]";
	//public String noEntityForm_XPath = "//*[@id='entity-details']//table//tbody//tr[td[contains(., 'No Entity details found')]]";
	
	//generic column header xpath
	public String generic_Prefix_EntityFrmListingColHeader1_XPath = "//*[@id='entity-details']//table//thead//tr//th//a[span[contains(text(),'";
	
	public String generic_Suffix_EntityFrmListingColHeader1_XPath = "')]]";
	
	//Main xpath for col header for entity record page
	public String Dyamic_GridTable_Prefix_Xpath = "//*[@id='entity-details']//table//thead//tr//th[";
	
	//For entity listing page
	public String EntityListing_GridTable_Prefix_Xpath = "//*[@id='entity']//table//thead//tr//th[";
	
	//For Security profile listing page
	public String SecurityProfileListing_GridTable_Prefix_Xpath = "//*[@id='securityprofiles']//table//thead//tr//th[";
		
	//below 2 are for col header filter and button click
	public String Dynamic_GridTable_Suffix1_Xpath = "]//a[span[contains(text(),'";
	public String Dynamic_GridTable_Suffix2_Xpath = "')]]//span//button";
	
	
	//below is to extract text for col header
	public String Dynamic_colHeaderName_Text_Suffix2_Xpath = "]//a//span";
	
	public String Dynamic_GridTable_TxtInput_Suffix_Xpath = "]//div//div//input[@class='k-textbox']";
	
	
	public String securityProfileLandingPageColHeader1_XPath = "//*[@id='entity-details']//table//thead//tr//th//a[span[contains(text(),'Name')]]";
	public String securityProfileLandingPageColHeaderEntity_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Entity')]]";
	public String securityProfileLandingPageColHeaderField_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Fields')]]";
	public String securityProfileLandingPageColHeaderRestrict_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Restrictions')]]";
	
	public String nameLabel_XPath = "//*[@id='entity-details']//table//thead//tr//th//a[span[contains(text(),'Name')]]//span//button";
	public String officerNameLabel_XPath = "//*[@id='entity-details']//table//thead//tr//th[3]//a[span[contains(text(),'Officer Name')]]//span//button";
	
	public String securityProfileTableList_XPath = "//*[@class='k-grid-table']";
	
	//Name filter
	public String secProfListingTableColHeader_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[text()='Name']]//span//button";
	public String NameColHeader_TXT_XPath = "//*[@id='entity-details']//table//thead//tr//th[2]//div//div//input[@class='k-textbox']";
	public String CityNameColHeader_XPath = "//*[@id='entity-details']//table//thead//tr//th[2]//a[span[text()='City Name']]//span//button";
	public String OfficerNameColHeader_XPath = "//*[@id='entity-details']//table//thead//tr//th[3]//div//div//input[@class='k-textbox']";
	//public String entityListingTableColHeader_TXT_XPath = "//*[@id='entity']//table//thead//tr[2]//th//div//div//input[@class='k-textbox']";
	public String emptyRecordsGrid_XPath = "//*[@id='entity-details']/div/div[3]/div/div[2]/div/div[1]/table/tbody/tr/td";
	
	
	//common xpath prefix for links
	public String savePrefix_XPath = "//*[@id='";
	public String saveSuffix_XPath = "']//button[text()='Save']";
	
	//search text box field
		public String inputSearchFld_Xpath = "//*[@id='entity-details']//div//span[@class='form-group search-control']//div//input[@id='filterText']";
		
		//search button 
		public String searchBtn_Xpath = "//*[@id='entity-details']//div//span[@class='form-group search-control']//button[1]";
		
	//Radio button click on first record
		public String rdoBtn_Xpath = "//*[@id='entity-details']//div//table//tbody//tr[1]/td[1]";
		
	//workflow name drop down
		public String workflowDrpDwn_Xpath = "//*[@id='workflow']";
		
	//workflow initiate button
		public String workflowBtnInitiate = "//*[@id='entity-details']//div//button[text()='Initiate']";

		//success message
		public String workflowInitiateSuccessMsg_XPath = "//div[@id='message-body']";
		
		//workflow status
		public String workflowStatus_Xpath = "//*[@id='entity-details']//div//table//tbody//tr";
		//public String workflowStatus_Xpath = "//*[@id='entity-details']//div//table//tbody//tr//td[3]";
		
		//name xpath
		public String workFlowName_Xpath = "//*[@id='entity-details']//div//table//tbody//tr//td[2]";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants *********************************************************************
		//Security page
	public String SP_ROLELIST_TBLID_Txt = "securityprofiles";
		public String SP_ADDNEW_PROFILE_Txt ="Add New Profile";
		public String NO_RECORDS_Txt = "No Records Found";
		public String NAME_COLHEADER_NAME_Txt = "Name";
		public String SP_COLHEADER_ENTITY_Txt = "Entity";
		public String SP_COLHEADER_FIELD_Txt = "Fields";
		public String AUTHNAME_COLHEADER_Txt = "Authority Name";
		public String OFFICERNAME_COLHEADER_Txt = "Officer Name";
		public String WORKFLOW_INITIATE_SUCCESS_TXT = "Workflow initiated successfully.";

}
