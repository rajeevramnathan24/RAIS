package pageLocators_Elements.RAIS;

public class SecurityProfilePage {
	//Add profile btn
	public String addNewProfileBtn_XPath = "//*[@id='securityprofiles']//button[text()='Add New Profile']";
	public String noSecurityProfiles_XPath = "//*[@id='securityprofiles']//table//tbody//tr[td[contains(., 'No security profiles found')]]";
	public String securityProfileLandingPageColHeaderName_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Name')]]";
	public String securityProfileLandingPageColHeaderEntity_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Entity')]]";
	public String securityProfileLandingPageColHeaderField_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Fields')]]";
	public String securityProfileLandingPageColHeaderRestrict_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[contains(text(),'Restrictions')]]";
	
	public String nameLabel_XPath = "//*[@id='securityprofile']//label[contains(., 'No security profiles found')]";
	
	public String securityProfileTableList_XPath = "//*[@class='k-grid-table']";
	
	//EntityFilter
	public String secProfListingTableColHeader_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//a[span[text()='Name']]//span//button";
	public String secProfListingTableColHeader_TXT_XPath = "//*[@id='securityprofiles']//table//thead//tr//th//div//div//input[@class='k-textbox']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants *********************************************************************
		//Security page
	public String SP_ROLELIST_TBLID_Txt = "securityprofiles";
		public String SP_ADDNEW_PROFILE_Txt ="Add New Profile";
		public String NO_SECURITYPROFILE_Txt = "No security profiles found";
		public String SP_COLHEADER_NAME_Txt = "Name";
		public String SP_COLHEADER_ENTITY_Txt = "Entity";
		public String SP_COLHEADER_FIELD_Txt = "Fields";
		public String SP_COLHEADER_RESTRICT_Txt = "Restrictions";

}
