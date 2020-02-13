package pageLocators_Elements.RAIS;

public class FormDesignerPage {
	
	//Generic id
	public String frmDesgnCommonxpath = "//*[@id='form-designer']";
	public String commonPopUpxpath = "//*[@class='MuiDialog-root application-dialog undefined']";
	public String commonColPropPageXPath = "//*[class='MuiDialog-root application-dialog dialog-column-properties']";
	
	
	//Add new security profile page
	public String mainFrmPage_SaveBtn_XPath = frmDesgnCommonxpath+"//button[text()='Save']";
	public String mainFrmPage_TitleTxtbox_XPath =frmDesgnCommonxpath+ "//div//input[@id='name']";
	public String cancelBtn_XPath = "//button[text()='Cancel']";
	public String savContiBtn_XPath = "//button[text()='Save And Continue']";
	public String addSectionBtn_XPath = frmDesgnCommonxpath +"//button[text()='Add Section']";

	//Add section popup
	
	public String addSectionPopUp_title_XPath =commonPopUpxpath+ "//div//input[@id='title']";
	public String addSectionColSelect3_XPath = frmDesgnCommonxpath +"//div//(button[@class='select-column'])[2]"; // Not working
	
	
	public String addSectionColSelectnewbutton2_XPath =commonPopUpxpath+ "//form//div[@class='form-group'][2]//div//button[@class='select-column'][2]//span";//
	//public String addSectionColSelectnewbutton2_XPath = "*//button[@class='select-column'][2]//span//span[@class='icon-bar'][2]";// this will work. Button to create 1 section
	public String addSectionColSelectnewbutton3_XPath = "//button[@class='select-column'][3]"; // this will work for button to create 3 sections
	public String addSectionSaveBtn_XPath = commonPopUpxpath+"//button[text()='Save']"; // save button for saving sections
	
	//After saving sections, below is to click column link
	public String colProp1_XPath = commonPopUpxpath + 
			"//[@class='form-group'][2]//div[@class='row']//div[@class='col-sm-6 col-flex-1 design-section-3'][1]//div//div//a[text()='Column Properties']";
	public String colProp2_XPath = "//*[@class='column-section-wrap form-section']//div[@class'row']//div[@class='col-sm-6 col-flex-1 design-section-3'][1]//div//div//a[text()='Column Properties']";
	public String colProp3_XPath = "//div[@class='col-sm-6 col-flex-1 design-section-3'][2]//a[text()='Column Properties']"; 
	
	public String colProp1_Temp_XPath=frmDesgnCommonxpath + "//form//div[3]//div[@class='col-sm-6 col-flex-1 design-section-2'][1]//a[text()='Column Properties']"; //		+"//*[@class='col-sm-6 col-flex-1 design-section-2'][1]//div//a[text()='Column Properties']";
	public String colProp2_Temp_XPath=frmDesgnCommonxpath + "//form//div[3]//div[@class='col-sm-6 col-flex-1 design-section-2'][2]//a[text()='Column Properties']";
	public String colProp3_Temp_XPath="//*[@class='column-section-wrap form-section']/div/div[3]/div/div/a";
	
	//select attributes for column prop from popup
	public String selectAttb_FromColProp_XPath =commonColPropPageXPath+ "//div//input[@id='title']";
	
	//Multi select dropdown
		public String frmDesgn_DropdnClick_Xpath = "//div[@class='mul-input col-form-control']";
		
		//value to select for multi select
		public String selectAttrib_ChkBox_dropdown_XPath = "//*[text()='Radioactive Material']";
		public String selectAttrib_Numeric_dropdown_XPath = "//*[text()='Postal Code']";
		public String selectAttrib_Text_dropdown_XPath = "//*[text()='City Name']";
		public String selectAttrib_popPageSaveBtn_XPath = "/html/body/div[6]/div[3]/div/div/div[3]/div/button[2]"; // save button for saving sections
		
		
		
				
		
	//labels and buttons
	//header
	public String addAttb_Hdr_XPath = "//*[@class='MuiDialog-root application-dialog undefined']//div//h2//span[text()='Add Attribute']";


	//label Fields in attb page
	
	
	//column icon
		public String frmDesgn_1colIcon_XPath = frmDesgnCommonxpath +"//div//button//span//span[@class='icon-bar-selected']";
		public String frmDesgn_2colIcon_XPath = frmDesgnCommonxpath +"//div//button[@class='select-column'][2]";
		public String frmDesgn_3colIcon_XPath = frmDesgnCommonxpath +"//div//button//span//span[@class='icon-bar-selected'][3]";
	
	public String addNewAttB_intnameLabel_XPath = frmDesgnCommonxpath +"//label[text()='Internal Name']";
	public String addNewAttB_tooTipLabel_XPath = frmDesgnCommonxpath +"//label[text()='Tooltip']";
	public String addNewAttB_Label_XPath = frmDesgnCommonxpath +"//label[text()='Label']";
	public String addNewAttB_dataType_Label_XPath = frmDesgnCommonxpath +"//label[text()='Data Type']";
	public String addNewAttB_mandInput_Label_XPath = frmDesgnCommonxpath +"//label[text()='Mandatory Inputs']";

	public String addNewAttB_linkEntity_Label_XPath = frmDesgnCommonxpath +"//label[text()='Linked Entity']";
	public String addNewAttB_subType_Label_XPath = frmDesgnCommonxpath +"//label[text()='Sub Type']";
	public String addNewAttB_noOfLines_Label_XPath = frmDesgnCommonxpath +"//label[text()='No. of lines']";
	public String addNewAttB_length_Label_XPath = frmDesgnCommonxpath +"//label[text()='Length']";
	public String addNewAttB_tag_Label_XPath = frmDesgnCommonxpath +"//label[text()='Tag']";


	//Check box label	
	public String addNewAttB_unqVal_Label_XPath = "//label[@id='isUnique']//span[text()='Unique Values']";
	public String addNewAttB_readOnly_Label_XPath = "//label[@id='isReadonly']//span[text()='Read Only']";
	public String addNewAttB_searchable_Label_XPath = "//label[@id='isSearchable']//span[text()='Searchable']";
	public String addNewAttB_showinList_Label_XPath = "//label[@id='showInListView']//span[text()='Show in list view']";
	public String addNewAttB_showHist_Label_XPath = "//label[@id='showInHistory']//span[text()='Show in History']";

	//Check box tick
	public String chkBox = "//span//input[@type='checkbox']";
	public String addNewAttB_unqVal_CheckBox_XPath = "//label[@id='isUnique']"+chkBox;
	public String addNewAttB_readOnly_CheckBox_XPath = "//label[@id='isReadonly']//span//input[@type='checkbox']";
	public String addNewAttB_searchable_CheckBox_XPath = "//label[@id='isSearchable']"+chkBox;
	public String addNewAttB_showinList_CheckBox_XPath = "//label[@id='showInListView']"+chkBox;
	public String addNewAttB_showHist_CheckBox_XPath = "//label[@id='showInHistory']"+chkBox;


	//Text box xpath
	public String FrmDsgn_sectionName_TxtBox_XPath = frmDesgnCommonxpath+"//div//input[@type='text']";

	//Tool tip text box
	public String addNewAttB_tooTipTxtBox_XPath = frmDesgnCommonxpath+"//div//input[@id='tooltip']";

	//Label text box
	public String addNewAttB_labelTxtBox_XPath = frmDesgnCommonxpath+"//div//input[@id='name']";

	//datatype & Mand inputs
	public String addNewAttB_dataTypedropDown_XPath="//*[@id='dataType']";
	public String addNewAttB_mandInputdropDown_XPath="//*[@id='requirement']";

	//Attributes
	public String attributeValueList [] = {"Checkbox", "Date","Guid","Image","Lookup","Lookup with Value","Memo","Multi lookup","Multiple documents",
			"Multiple Users","Numeric","RAN","Single document","Text","Time Interval","User","Workflow Action"};

	public String mandInputList [] = {"Mandatory","Recommended","Optional"};

	public String subTypeList [] = {"Integer","Rational","Scientific","Year"} ;

	//linked entity dropdown xpath  targetEntityId
	public String addNewAttB_linkedEntitydropDown_XPath="//*[@id='targetEntityId']";

	//sub Type dropdown xpath  targetEntityId
	public String addNewAttB_subTypedropDown_XPath="//*[@id='subType']";

	//noofLines text box xpath
	public String addNewAttB_noOfLinesTxtBox_XPath = frmDesgnCommonxpath+"//div//input[@id='numberOfLines']";
	
	//Length text box xpath
	public String addNewAttB_lengthTxtBox_XPath = frmDesgnCommonxpath+"//div//input[@id='size']";
	
	//Tag text box xpath
		public String addNewAttB_tagTxtBox_XPath = frmDesgnCommonxpath+"//div//input[@id='tag']";

	//Validation msgs xpath
	public String nameRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Internal Name is required with alphanumeric values and without spaces.']"	;
	public String labelRequired_MsgXPath ="//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Label is required']"	;
	public String dataTypeRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Data type is required']"	;
	public String mandInputRequired_MsgXPath = "//*[@class='MuiDialog-root application-dialog undefined']//p[text()='Mandatory inputs are required']";

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




















	//Page Constants *********************************************************************
	//Add new Entity profile page
	public String ADDNEW_ATTB_HDR_LABEL_Txt = "Add Attribute";
	public String ADDNEW_ATTB_INTNAME_LABEL_Txt = "Internal Name";
	public String ADDNEW_ATTB_TOOLTIP_LABEL_Txt = "Tooltip";
	public String ADDNEW_ATTB_LABEL_Txt = "Label";
	public String ADDNEW_ATTB_DATATYPE_LABEL_Txt = "Data Type";
	public String ADDNEW_ATTB_MANDINPUT_LABEL_Txt = "Mandatory Inputs";


	//Enable txt
	public String ADDNEW_ATTB_UNQVAL_Txt = "Unique Values";
	public String ADDNEW_ATTB_READONLY_Txt = "Read Only";
	public String ADDNEW_ATTB_SEARCH_Txt = "Searchable";
	public String ADDNEW_ATTB_SHOWINLIST_Txt = "Show in list view";
	public String ADDNEW_ATTB_SHOWINHIST_Txt = "Show in History";

	//Validation msgs
	public String ADDNEW_ATTB_INTNAME_REQD_TXT = "Internal Name is required with alphanumeric values and without spaces.";
	public String ADDNEW_ATTB_LABEL_REQD_TXT = "Label is required";
	public String ADDNEW_ATTB_DATATYPE_REQD_TXT = "Data type is required";
	public String ADDNEW_ATTB_MANDINPUT_REQD_TXT = "Mandatory inputs are required";

	//duplicate name msg
	public String DUPLICATE_ENTITY_NAME_TXT="This Internal Name already exists. Please enter a different Internal Name";
	public String DUPLICATE_SINGULAR_ENTITY_NAME_TXT="This Singular Label already exists. Please enter a different Singular Label";


	//disabled
	public String ADDNEW_ATTB_DISABLE_Txt= "Disabled";
	public String ADDNEW_ATTB_ENB_Txt= "Enabled";

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
