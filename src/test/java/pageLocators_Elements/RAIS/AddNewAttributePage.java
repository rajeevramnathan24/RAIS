package pageLocators_Elements.RAIS;

public class AddNewAttributePage {
	//Add new security profile page
	public String SaveBtn_XPath = "//button[text()='Save']";
	public String cancelBtn_XPath = "//button[text()='Cancel']";
	public String deleteBtn_XPath = "//button[text()='Delete']";

	//labels and buttons
	//header
	public String addAttb_Hdr_XPath = "//*[@class='MuiDialog-root application-dialog undefined']//div//h2//span[text()='Add Attribute']";


	//label Fields in attb page
	public String commonLabelxpath = "//*[@class='MuiDialog-root application-dialog undefined']";
	public String addNewAttB_intnameLabel_XPath = commonLabelxpath +"//label[text()='Internal Name']";
	public String addNewAttB_tooTipLabel_XPath = commonLabelxpath +"//label[text()='Tooltip']";
	public String addNewAttB_Label_XPath = commonLabelxpath +"//label[text()='Label']";
	public String addNewAttB_dataType_Label_XPath = commonLabelxpath +"//label[text()='Data Type']";
	public String addNewAttB_mandInput_Label_XPath = commonLabelxpath +"//label[text()='Mandatory Inputs']";

	public String addNewAttB_linkEntity_Label_XPath = commonLabelxpath +"//label[text()='Linked Entity']";
	public String addNewAttB_subType_Label_XPath = commonLabelxpath +"//label[text()='Sub Type']";
	public String addNewAttB_noOfLines_Label_XPath = commonLabelxpath +"//label[text()='No. of lines']";
	public String addNewAttB_length_Label_XPath = commonLabelxpath +"//label[text()='Length']";
	public String addNewAttB_tag_Label_XPath = commonLabelxpath +"//label[text()='Tag']";


	//Check box label	
	public String addNewAttB_unqVal_Label_XPath = "//label[@id='isUnique']//span[text()='Unique Values']";
	public String addNewAttB_readOnly_Label_XPath = "//label[@id='isReadonly']//span[text()='Read Only']";
	public String addNewAttB_searchable_Label_XPath = "//label[@id='isSearchable']//span[text()='Searchable']";
	public String addNewAttB_showinList_Label_XPath = "//label[@id='showInListView']//span[text()='Show in list view']";
	public String addNewAttB_showHist_Label_XPath = "//label[@id='showInHistory']//span[text()='Show in History']";

	//Check box tick
	public String chkBox = "//span//input[@type='checkbox']";
	public String chkBoxStatusSelected = "//*span[contains(@class, 'Mui-checked')]";
	public String chkBoxStatusNOTSelected = "//*span[contains(@class, 'Mui-disabled')]";


	public String addNewAttB_unqVal_CheckBox_XPath = "//label[@id='isUnique']"+chkBox;
	public String addNewAttB_readOnly_CheckBox_XPath = "//label[@id='isReadonly']//span//input[@type='checkbox']";
	public String addNewAttB_searchable_CheckBox_XPath = "//label[@id='isSearchable']"+chkBox;
	public String addNewAttB_showinList_CheckBox_XPath = "//label[@id='showInListView']"+chkBox;
	public String addNewAttB_showHist_CheckBox_XPath = "//label[@id='showInHistory']"+chkBox;

	//checkbox selected status ONLY For RAN
	public String addNewAttB_unqVal_CheckBoxSelected_XPath = "//label[@id='isUnique']"+chkBoxStatusSelected;
	public String addNewAttB_searchable_CheckBoxSelected_XPath = "//label[@id='isSearchable']"+chkBoxStatusSelected;

	//checkbox NOT selected status 
	public String addNewAttB_unqVal_CheckBoxNOTSelected_XPath = "//label[@id='isUnique']"+chkBoxStatusNOTSelected;
	public String addNewAttB_searchable_CheckBoxNOTSelected_XPath = "//label[@id='isSearchable']"+chkBoxStatusNOTSelected;
	public String addNewAttB_readOnly_CheckBoxNOTSelected_XPath = "//label[@id='isReadonly']"+chkBoxStatusNOTSelected;
	public String addNewAttB_showinList_CheckBoxNOTSelected_XPath = "//label[@id='showInListView']"+chkBoxStatusNOTSelected;
	public String addNewAttB_showHist_CheckBoxNOTSelected_XPath = "//label[@id='showInHistory']"+chkBoxStatusNOTSelected;

	//Text box xpath
	public String addNewAttB_internalNameTxtBox_XPath = commonLabelxpath+"//div//input[@id='internalName']";

	//Tool tip text box
	public String addNewAttB_tooTipTxtBox_XPath = commonLabelxpath+"//div//input[@id='tooltip']";

	//Label text box
	public String addNewAttB_labelTxtBox_XPath = commonLabelxpath+"//div//input[@id='name']";

	//datatype & Mand inputs
	public String addNewAttB_dataTypedropDown_XPath="//*[@id='dataType']";
	public String addNewAttB_mandInputdropDown_XPath="//*[@id='requirement']";

	//Attributes
	public String attributeValueList [] = 
		{		"Checkbox", 
				"Date",
				"Date Time",
				"Checkbox List",
				"Image",
				"Lookup",
				"Lookup with Value",
				"Memo",
				"Multi lookup",
				"Multiple documents",
				"Multiple Users",
				"Numeric",
				"RAN",
				"Single document",
				"Text",
				"Time Interval",
				"User",
				"Workflow Action"};
	
	//Entity Attribute list
	//Attributes
		public String EntityattributeValueList [] = 
			{		"Checkbox", 
					"Date",
					"Date Time",
					"Checkbox List",
					"Image",
					"Lookup",
					"Lookup with Value",
					"Memo",
					"Multi lookup",
					"Multiple documents",
					"Multiple Users",
					"Numeric",
					"RAN",
					"Single document",
					"Text",
					"Time Interval",
					"User"};

	public String mandInputList [] = {"Mandatory","Recommended","Optional"};

	public String subTypeList [] = {"Integer","Rational","Scientific","Year"} ;

	//linked entity dropdown xpath  targetEntityId
	public String addNewAttB_linkedEntitydropDown_XPath="//*[@id='targetEntityId']";

	//sub Type dropdown xpath  targetEntityId
	public String addNewAttB_subTypedropDown_XPath="//*[@id='subType']";

	//noofLines text box xpath
	public String addNewAttB_noOfLinesTxtBox_XPath = commonLabelxpath+"//div//input[@id='numberOfLines']";

	//Length text box xpath
	public String addNewAttB_lengthTxtBox_XPath = commonLabelxpath+"//div//input[@id='size']";

	//Tag text box xpath
	public String addNewAttB_tagTxtBox_XPath = commonLabelxpath+"//div//input[@id='tag']";

	//Validation msgs xpath
	public String nameRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Internal Name is required with alphanumeric values and without spaces.']"	;
	public String labelRequired_MsgXPath ="//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Label is required']"	;
	public String dataTypeRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Data type is required']"	;
	public String mandInputRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Mandatory inputs are required']";
	public String linkedEntityRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Linked entity is required.']";
	public String subTypeRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Sub type is required.']";

	//duplicate name msg
	public String duplicateInternalName_XPath="";

	//success message
	public String addNewAttB_SuccessMsg_XPath = "//div[@id='message-body']";
	public String deleteEntity_PopTitleXPath = "//*[@id='max-width-dialog-title']";
	public String delEntity_PopupMSGTxt_XPath= "//div[@class='dialog-content']";
	public String delEntity_PopupNoBtn_XPath = "//div[@class='dialog-action']"; 

	//Pop message yes/ no buttons
	public String delEntity_popUpNoBtn_XPath = "//button[contains(text(),'No')]";
	public String delEntity_popUpYesBtn_XPath = "//button[contains(text(),'Yes')]";


	//Attribute link
	public String attributeLink_XPath = "add-entity";

	//Add attribute listing page
	public String attributeListingPage_AddnewAttbBtn_XPath = "//*[@id='add-entity']//button[text()='Add New Attribute']";
	
	public String attrbListingColHeader_XPath = "//*[@id='add-entity']//table//thead//tr//th[1]//span//button";
	public String attrbListingColHeader_TXT_XPath = "//*[@id='add-entity']//table//thead//tr//th//div//div//input[@class='k-textbox']";
	
	public String attrbListingTable_XPath = "//*[@class='k-grid-table']";
	
	//success message
	public String Attrb_SuccessMsg_XPath = "//div[@id='message-body']";
	
	//CLear button on filter
	public String ClearbuttonFilter_XPath = "//*[@id='add-entity']//table//thead//tr//th//div//div//div//button";




















	//Page Constants *********************************************************************
	//Add new Entity profile page
	public String ADDNEW_ATTB_HDR_LABEL_Txt = "Add Attribute";
	public String ADDNEW_ATTB_INTNAME_LABEL_Txt = "Internal Name";
	public String ADDNEW_ATTB_TOOLTIP_LABEL_Txt = "Tooltip";
	public String ADDNEW_ATTB_LABEL_Txt = "Label";
	public String ADDNEW_ATTB_DATATYPE_LABEL_Txt = "Data Type";
	public String ADDNEW_ATTB_MANDINPUT_LABEL_Txt = "Mandatory Inputs";
	public String ADDNEW_ATTB_LINKDENTITY_LABEL_Txt = "Linked Entity";
	public String ADDNEW_ATTB_SUBTYPE_LABEL_Txt = "Sub Type";
	public String ADDNEW_ATTB_LENGTH_LABEL_Txt = "Length";
	public String ADDNEW_ATTB_NO_OF_LINES_LABEL_Txt = "No. of lines";
	public String ADDNEW_ATTB_TAG_LABEL_Txt = "Tag";
	
	
	//Enable txt
	public String ADDNEW_ATTB_UNQVAL_Txt = "Unique Values";
	public String ADDNEW_ATTB_READONLY_Txt = "Read Only";
	public String ADDNEW_ATTB_SEARCH_Txt = "Searchable";
	public String ADDNEW_ATTB_SHOWINLIST_Txt = "Show in list view";
	public String ADDNEW_ATTB_SHOWINHIST_Txt = "Show in History";
	
	//For testing other check box conditions
	public String ADDNEW_ATTB_RAN_UNIQ_CHKBOX_Txt = "RAN Unique CheckBox";
	public String ADDNEW_ATTB_RAN_SEARCH_CHKBOX_Txt = "RAN Searchable CheckBox";
	public String ADDNEW_ATTB_USR_LOOKUP_UNIQ_CHKBOX_Txt = "Users_Lookup Unique CheckBox";
	public String ADDNEW_ATTB_USR_LOOKUP_NUM_SEARCH_CHKBOX_Txt = "Users_Lookup_Numeric Searchable CheckBox";
	
	
	
	//Validation msgs
	public String ADDNEW_ATTB_INTNAME_REQD_TXT = "Internal Name is required with alphanumeric values and without spaces.";
	public String ADDNEW_ATTB_LABEL_REQD_TXT = "Label is required";
	public String ADDNEW_ATTB_DATATYPE_REQD_TXT = "Data type is required";
	public String ADDNEW_ATTB_MANDINPUT_REQD_TXT = "Mandatory inputs are required";
	public String ADDNEW_ATTB_LINKEDENTY_REQD_TXT = "Linked entity is required.";
	public String ADDNEW_ATTB_SUBTYPE_REQD_TXT = "Sub type is required.";
	
	//duplicate name msg
	public String DUPLICATE_ENTITY_NAME_TXT="This Internal Name already exists. Please enter a different Internal Name";
	public String DUPLICATE_SINGULAR_ENTITY_NAME_TXT="This Singular Label already exists. Please enter a different Singular Label";


	//disabled
	public String ADDNEW_ATTB_DISABLE_Txt= "Disabled";
	public String ADDNEW_ATTB_ENB_Txt= "Enabled";

	//CheckBox verified messages
	public String ADDNEW_ATTB_CHKBOX_VERIFIED_Txt = "Check Box properties verified";
	public String ADDNEW_ATTB_CHKBOX_NOT_VERIFIED_Txt = "Check Box properties NOT verified";

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
