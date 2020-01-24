package pageLocators_Elements.RAIS;

import org.openqa.selenium.WebDriver;


public class AddNewPermRestrictionsPage {

	WebDriver wi;

	//Save button xpath
	//public String savBtn_XPath = "//*[@id=\"permission-restriction\"]/h5/div/div[2]/div/button[3]";	

	//Practice and Title xpath
	public String practice_title_XPath = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[1]/div/div[2]/p";	

	//Region and query xpath
	public String region_query_XPath = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[2]/div/div[2]/p";	

	//District and Entity xpath
	public String district_entity_XPath = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[3]/div/div[2]/p";	

	//Facility and entities xpath
	public String facility_entities_XPath = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[4]/div/div[2]/div/p";	

	//Custom Xpath
	//Save button xpath
	public String savBtn_XPath = "//button[contains(text(),'Save')]";

	//Cancel Button xpath
	public String cancelBtn_XPath = "//button[contains(text(),'Cancel')]";

	//Delete Button xpath
	public String deleteBtn_XPath = "//button[contains(text(),'Delete')]";

	//delete confirmation page on top
	public String deleteRolePermRestrctPopupMsg_XPath = "//div[@id='max-width-dialog-title']";



	//Practice Dropdown xpath
	public String practice_dropdown_XPath = "//select[@id='practiceId']";	

	//Region Dropdown xpath
	public String region_dropdown_XPath = "//select[@id='regionId']";

	//District Dropdown xpath
	public String district_dropdown_XPath = "//select[@id='districtId']";

	//Facility Dropdown xpath
	public String facility_Entities_dropdown_XPath = "//*div[@id='multi-select']";
	//public String facility_Entities_DropdnClick_Xpath = "//*[@id='multi-select']/div/button";
	public String facility_Entities_DropdnClick_Xpath = "//*[@class='down-arrow']";
	public String facility_Entities_DropdnClicknew_Xpath = "//span[contains(text(),'Permissions')]";
	public String dropdownIconClick_XPath = "//*[@id='multi-select']/div/div[2]";

	//facilities Dropdown value selection xpath
	public String facilitiesValue1_dropdown_XPath = "//*[contains(text(),'Facility3')]";	
	public String facilitiesValue2_dropdown_XPath = "//*[contains(text(),'Facility2')]";

	//Delete dialog content
	public String delDataRolePermRestrct_PopupMSGCONTENT_XPath= "//div[@class='dialog-content']";
	
	//Delete popup msg xpath
	public String delDataRolePermRestrict_btnNO_XPath= "//button[contains(text(),'No')]";
	public String delDataRolePermRestrict_btnYES_XPath= "//button[contains(text(),'Yes')]";
	
	//success message
	public String addnewPermRestrict_SuccessMsg_XPath = "//div[@id='message-success']";

	//	
	//	//validation for practice drop down
	//	public String practice_validationMsg_XPath = "//*[contains(@text(),'Practice is required.')]";
	//
	//	//Region Label xpath
	//	public String region_label_XPath = "//label[contains(text(),'Region')]";
	//

	//	
	//	//validation for Region drop down
	//	public String region_validationMsg_XPath = "//*[contains(@text(),'Region is required.')]";
	//
	//	//*[@id="permission-restriction"]/h5/div/div[1]/form/div[2]/div/div[2]/p
	//	
	//	//District Label xpath
	//	public String district_label_XPath = "//label[contains(text(),'District')]";
	//
	//	
	//	
	//	//validation for District drop down
	//		public String district_validationMsg_XPath = "//*[contains(@text(),'District is required.')]";
	//
	//	//Facility Label xpath
	//	public String facility_label_XPath = "//label[contains(text(),'Facility')]";
	//
	//	//Facility Dropdown xpath
	//	public String facility_Entities_dropdown_XPath = "//div[@id='multi-select']";
	//	
	//	//validation for Faility drop down
	//			public String facilities_validationMsg_XPath = "//*[contains(@text(),'Facilities is required.')]";




	//For add new Retrictions screen	
	//Practice or ID Label xpath ----this is same for permission and restriction screen
	//	public String practiceid_label_XPath = "//label[contains(text(),'Practice')]";

	//Practice Dropdown xpath
	public String practiceid_txt_XPath = "//input[@id='name']";	

	//Region Label xpath
	public String query_label_XPath = "//label[contains(text(),'Query')]";

	//Region Dropdown xpath
	public String query_dropdown_XPath = "//select[@id='queryId']";

	//District Label xpath
	public String entityGroup_label_XPath = "//label[contains(text(),'Entity Group')]";

	//District Dropdown xpath
	public String entityGroup_dropdown_XPath = "//select[@id='entityGroupId']";

	//Facility Label xpath
	public String entities_label_XPath = "//label[contains(text(),'Entities')]";

	












	//Page constants ******************************************************
	//AddNew Restrictions page
	public String TITLE_Txt ="Title is required.";
	public String QUERY_Txt ="Query is required.";
	public String ENTITYGRP_Txt ="Entity group is required.";
	public String ENTITIES_Txt ="Entities are required.";

	//AddNew Permissions page
	public String PRACTICE_Txt ="Practice is required.";
	public String REGION_Txt ="Region is required.";
	public String DISTRICT_Txt ="District is required.";
	public String FACILITY_Txt ="Facilities is required.";

	//Delete popup msg xpath
	public String DELETE_DATAROLE_PERMRESTRCT_Txt="Do you really want to delete data role permission ?";

	//successful save msg
	public String DATAROLE_PERM_RESTRCT_SUCCESS_MSG_Txt="New permission to the Data Role has been added successfully.";
	
	public String DELETEROLE_PERMRESTRCT_SUCCESSMSG_TXT = "Permission to the Data Role has been deleted successfully.";
}
