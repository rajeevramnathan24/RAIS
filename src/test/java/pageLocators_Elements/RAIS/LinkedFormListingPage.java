package pageLocators_Elements.RAIS;

public class LinkedFormListingPage {
	//buttons and column headers
	public String addNewFormBtn_XPath = "//*[@id='add-entity']//button[text()='Add New Form']";
	//public String noEntitiesFound_XPath = "//*[@id='entity']//table//tbody//tr[td[contains(., 'No Entities found')]]";
	public String linkdFrmListingPageColHeaderfrmName_XPath = "//*[@id='add-entity']//table//thead//tr//th//a[span[contains(text(),'Form Name')]]";
		
	public String linkdFrmListingPageTable_XPath = "//*[@class='k-grid-table']";
	
	//EntityFilter
	public String entityListingTableColHeader_XPath = "//*[@id='entity']//table//thead//tr//th//a[span[text()='Entity Name']]//span//button";
	public String entityListingTableColHeader_TXT_XPath = "//*[@id='entity']//table//thead//tr//th//div//div//input[@class='k-textbox']";
	
	
	
	//Page Constants *********************************************************************
		//Security page
	//public String SP_ROLELIST_TBLID_Txt = "securityprofiles";
	
	
		public String LINKED_FRM_LIST_ADDNEWFRM_Txt ="Add New Form";
		//public String NO_ENTITY_Txt = "No Entities found";
		public String LINKED_FRM_LIST_COLHEADER_FRMNAME_Txt = "Form Name";
//		public String ENT_LIST_COLHEADER_DESC_Txt = "Description";
//		public String ENT_LIST_COLHEADER_TYPE_Txt = "Type";
//		public String ENT_LIST_COLHEADER_GRP_Txt = "Group";

}
