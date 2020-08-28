package pageLocators_Elements.RAIS;

public class EntityListingPage {
	//buttons and column headers
	public String addNewEntityBtn_XPath = "//*[@id='entity']//button[text()='Add New Entity']";
	public String noEntitiesFound_XPath = "//*[@id='entity']//table//tbody//tr[td[contains(., 'No Entities found')]]";
	public String entityListingPageColHeaderName_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[contains(text(),'Entity Name')]]";
	public String entityListingPageColHeaderDesc_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[contains(text(),'Description')]]";
	public String entityListingPageColHeaderType_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[contains(text(),'Type')]]";
	public String entityListingPageColHeaderGroup_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[contains(text(),'Group')]]";
	
	public String entityListingTable_XPath = "//*[@class='k-grid-table']";
	
	//EntityFilter
	public String entityListingTableColHeader_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[text()='Entity Name']]//span//button";
	public String entityListingTableColHeader_TXT_XPath = "//*[@id='entity']//table//thead//tr//th//div//div//input[@class='k-textbox']";
	
	public String entityNoRecordsonGrid_XPath = "//*[@id='entity']/div/div[2]/div/div[1]/div/div[2]/div/div[1]/table/tbody/tr/td";
	
	
	
	
	
	//Page Constants *********************************************************************
		//Security page
	public String SP_ROLELIST_TBLID_Txt = "securityprofiles";
	
	
		public String ENT_LIST_ADDNEW_Txt ="Add New Entity";
		public String NO_ENTITY_Txt = "No Entities found";
		public String ENT_LIST_COLHEADER_NAME_Txt = "Entity Name";
		public String ENT_LIST_COLHEADER_DESC_Txt = "Description";
		public String ENT_LIST_COLHEADER_TYPE_Txt = "Type";
		public String ENT_LIST_COLHEADER_GRP_Txt = "Group";
		public String NO_ENTITY_RECORDS_Txt = "No entity available";

}
