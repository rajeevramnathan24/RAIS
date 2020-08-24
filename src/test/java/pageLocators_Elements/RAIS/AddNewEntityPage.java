package pageLocators_Elements.RAIS;

public class AddNewEntityPage {
	//Add new security profile page
	public String SaveBtn_XPath = "//*[@id='add-entity']//button[text()='Save']";
	public String cancelBtn_XPath = "//*[@id='add-entity']//button[text()='Cancel']";
	public String deleteBtn_XPath = "//*[@id='add-entity']//button[text()='Delete']";

	//labels and buttons
	public String addNewEntity_name_XPath = "//*[@id='add-entity']//label[contains(text(),'Internal Name')]";
	public String addNewEntity_Desc_XPath = "//*[@id='add-entity']//label[contains(text(),'Description')]";
	public String addNewEntity_SingLbl_XPath = "//*[@id='add-entity']//label[contains(text(),'Singular')]";
	public String addNewEntity_PluLbl_XPath = "//*[@id='add-entity']//label[contains(text(),'Plural')]";
	public String addNewEntity_GrpLbl_XPath = "//*[@id='add-entity']//label[contains(text(),'Group')]";
	public String addNewEntity_entyRole_XPath = "//*[@id='add-entity']//label[text()='Entity Role']";
	public String addNewEntity_pubNavi_XPath = "//*[@id='add-entity']//label[text()='Publish in Navigation']";

	public String waitForLoader_XPath = "//*[@id='overlayWrapper']//div[@class='loading']";



	//Text box xpath
	public String addNewEntity_internalNameTxtBox_XPath = "//*[@id='internalName']";

	//Description text box
	public String addNewEntity_DescTxtBox_XPath = "//*[@id='description']";

	//Singular text box
	public String addNewEntity_SingTxtBox_XPath = "//*[@id='Name']";

	//Plural text box
	public String addNewEntity_PluTxtBox_XPath = "//*[@id='pluralName']";

	//group dropdown selectedEntity
	public String addNewEntity_grpDropDown_XPath = "//*[@id='GroupId']";

	//Entity role dropdown selectedEntity
	public String addNewEntity_roleDropDown_XPath = "//*[@id='Role']";		

	//Entity publish navigation dropdown selectedEntity
	public String addNewEntity_pubNavi1DropDown_XPath = "//*[@id='parentNavigationId']";

	//Entity publish navigation dropdown selectedEntity
		public String addNewEntity_pubNavi2DropDown_XPath = "//*[@id='navigationId']";		
	
	//Check box label	
	public String addNewEntity_enableWF_XPath = "//label[@id='CanHaveWorkflows']//span[contains(text(),'WorkFlows')]";	
	public String addNewEntity_enableRAN_XPath = "//label[@id='hasRAN']//span[contains(text(),'RAN')]";		
	public String addNewEntity_enableHistory_XPath = "//label[@id='MaintainHistory']//span[contains(text(),'History')]";
	public String addNewEntity_enabledocument_XPath = "//label[@id='CanHaveDocumentAttachment']//span[contains(text(),'Document')]";
	
	//Check box tick
	public String addNewEntity_enableWFCheckBox_XPath = "//label[@id='CanHaveWorkflows']//span//input[@type='checkbox']";
	public String addNewEntity_enableRANCheckBox_XPath = "//label[@id='hasRAN']//span//input[@type='checkbox']";
	public String addNewEntity_enableHistoryCheckBox_XPath = "//label[@id='MaintainHistory']//span//input[@type='checkbox']";
	public String addNewEntity_enableDocCheckBox_XPath = "//label[@id='CanHaveDocumentAttachment']//span//input[@type='checkbox']";
	
	//Validation msgs xpath
	public String nameRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Internal Name is required with alphanumeric values and without spaces.']"	;
	public String descRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Description is required.']"	;
	public String singularNameRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Name is required.']"	;
	public String pluralNameRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Plural Label is required.']"	;
	public String groupRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Group is required.']"	;
	public String entityRoleRequired_MsgXPath = "//*[@id='add-entity']//p[text()='Entity Role is required.']"	;
	public String duplicateEntityName_MsgXPath = "//*[@id='add-entity']//*div//p[text()='This Internal Name already exists. Please enter a different Internal Name']"	;
	public String duplicateSingularEntityName_MsgXPath = "//*[@id='add-entity']//p[text()='This Singular Label already exists. Please enter a different Singular Label']"	;
	
	//duplicate name msg
	public String duplicateInternalName_XPath="";

	//success message
	public String addnewEntity_SuccessMsg_XPath = "//div[@id='message-body']";
	public String deleteEntity_PopTitleXPath = "//*[@id='max-width-dialog-title']";
	public String delEntity_PopupMSGTxt_XPath= "//div[@class='dialog-content']";
	public String delEntity_PopupNoBtn_XPath = "//div[@class='dialog-action']"; 

	//Pop message yes/ no buttons
	public String delEntity_popUpNoBtn_XPath = "//*[@id='add-entity']//button[contains(text(),'No')]";
	public String delEntity_popUpYesBtn_XPath = "//*[@id='add-entity']/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/button[2]";
	//*[@id='add-entity']/div/div[2]/div/div/div[2]/div/div/div[2]/div[2]/div/button[2]

	//Attribute link
	public String attributeLink_XPath = "add-entity";
	
	//Add attribute listing page
	public String attributeListingPage_AddnewAttbBtn_XPath = "//*[@id='add-entity']//button[text()='Add New Attribute']";
	
	//close success message diaog
	public String addnewEntity_closeMsg_Xpath = "//*[@id='message-success']//span//a[@id='close-btn-success']";

	
//Entity link on left menu pane
	public String entDetailPage_XPath = "//*[@id='add-entity']/div/div[1]/div/div[2]/div[2]/div/div[1]/ul/li[1]/a/span";

















	//Page Constants *********************************************************************
	//Add new Entity profile page
	public String ADDNEW_ENTITY_INTNAME_Txt = "Internal Name";
	public String ADDNEW_ENTITY_DESC_Txt = "Description";
	public String ADDNEW_ENTITY_SINGLBL_Txt = "Singular Label";
	public String ADDNEW_ENTITY_PLULBL_Txt = "Plural Label";
	public String ADDNEW_ENTITY_GRPLBL_Txt = "Group";
	public String ADDNEW_ENTITY_ROLELBL_Txt = "Entity Role";
	public String ADDNEW_ENTITY_PUBNAVILBL_Txt = "Publish in Navigation";
	
	//Enable txt
	public String ADDNEW_ENTITY_ENB_WFLBL_Txt = "Enable WorkFlows";
	public String ADDNEW_ENTITY_ENB_RANLBL_Txt = "Enable RAN";
	public String ADDNEW_ENTITY_ENB_HISLBL_Txt = "Enable History";
	public String ADDNEW_ENTITY_ENB_DOC_ATTACH_Txt = "Enable Document Attachments";
	
	//disabled
	public String ADDNEW_ENTITY_ENB_DISABLE_Txt= "Disabled";
	

	//Validation msgs
	public String ADDNEW_ENTITY_INTNAME_REQD_TXT = "Internal Name is required with alphanumeric values and without spaces.";
	public String ADDNEW_ENTITY_DESC_REQD_TXT = "Description is required.";
	public String ADDNEW_ENTITY_SINGFLD_REQD_TXT = "Name is required.";
	public String ADDNEW_ENTITY_PLUFLD_REQD_TXT = "Plural Label is required.";
	public String ADDNEW_ENTITY_GROUP_REQD_TXT = "Group is required.";
	public String ADDNEW_ENTITY_ROLE_REQD_TXT = "Entity Role is required.";

	//duplicate name msg
	public String DUPLICATE_ENTITY_NAME_TXT="This Internal Name already exists. Please enter a different Internal Name";
	public String DUPLICATE_SINGULAR_ENTITY_NAME_TXT="This Singular Label already exists. Please enter a different Singular Label";
	
	
	//Buttons
	public String SAVE_BTN_Txt = "Save";
	public String CANCEL_BTN_Txt = "Cancel";
	public String NO_BTN_Txt = "No";
	public String YES_BTN_Txt = "Yes";

	//New SP msgs
	public String DELENT_POPUP_CONF_MSG_Txt="Confirmation";
	public String ADDNEWENT_SUCESSMSG_TXT = "New entity has been added successfully.";
	public String UPDENT_SUCESSMSG_TXT = "Entity has been updated successfully.";
	public String DELENT_SUCESSMSG_TXT = "Entity is deleted successfully";

	public String DELENTITY_POPMSG_Txt = "Are you sure you want to delete this entity?";
	
	


}
