package pageLocators_Elements.RAIS;

public class EntityFormListingPage {
	//Add new form btn
	public String addNewBtn_XPath = "//*[@id='entity-details']//button[text()='Add New']";
	
	public String FacListingaddNewBtn_XPath = "//*[@id='entity-details']//button[2][contains(text(),'Add New')]";
	//public String noEntityForm_XPath = "//*[@id='entity-details']//table//tbody//tr[td[contains(., 'No Entity details found')]]";
	
	//generic column header xpath
	public String generic_Prefix_EntityFrmListingColHeader1_XPath = "//*[@id='entity-details']//table//thead//tr//th//a[span[contains(text(),'";
	
	public String generic_Suffix_EntityFrmListingColHeader1_XPath = "')]]";
	
	public String Dyamic_GridTable_Prefix_Xpath = "//*[@id='entity-details']//table//thead//tr//th[";
	public String Dynamic_GridTable_Suffix1_Xpath = "]//a[span[contains(text(),'";
	public String Dynamic_GridTable_Suffix2_Xpath = "')]]//span//button";
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants *********************************************************************
		//Security page
	public String SP_ROLELIST_TBLID_Txt = "securityprofiles";
		public String SP_ADDNEW_PROFILE_Txt ="Add New Profile";
		public String NO_SECURITYPROFILE_Txt = "No security profiles found";
		public String NAME_COLHEADER_NAME_Txt = "Name";
		public String SP_COLHEADER_ENTITY_Txt = "Entity";
		public String SP_COLHEADER_FIELD_Txt = "Fields";
		public String AUTHNAME_COLHEADER_Txt = "Authority Name";
		public String OFFICERNAME_COLHEADER_Txt = "Officer Name";

}
