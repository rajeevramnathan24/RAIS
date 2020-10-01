package constants;

import java.util.ArrayList;
import java.util.Arrays;

public class RaisTestData {

	//Test Data 
	public static String verifiedTextBoxProperty = "Valid Textbox properties";
	public static String verifiedRadioButtonProperty = "Valid Radio Button properties";

	//invalid credentials
	public static String invalidUserId = "ABC";
	public static String invalidCredentials = "ABCD@YAHOO.COM";

	public static String raisAdminUserId = "Administrator";
	public static String raisAdminUserPwd = "Pass123$";





	//Dashboard page constants
	public static String DashboardText = "Dashboard"; //"DASHBOARD";
	public static String RegProcessText = "Regulatory Processes"; //"REGULATORY PROCESSES";
	public static String InventResourceMainMenu = "Inventory & Resources"; //"INVENTORY & RESOURCES";
	public static String InventorySubMenuText = "Inventory";
	public static String ResourcesSubMenuText = "Resources";
	public static String RptStatsText = "REPORTS & STATISTICS";
	public static String SettingsText = "Settings"; //"SETTINGS";
	public static String AdministrationMainMenu = "Administration"; //"ADMINISTRATION";
	public static String UserMgmtSubMenuText = "User Management";
	public static String ToolsSubMenu = "Tools";
	public static String CustomizeSubMenu = "Customization";
	public static String CommonTablesSubMenu = "Common Tables";
	public static String NotificationText = "Notification";
	public static String HelpText = "Help";
	public static String AuthorizationSubmenu = "Authorizations";
	
	//Menu DR/FR
	public static String dataRole = "Data Roles";
	public static String functionalRole = "Functional Roles";
	public static String securityProfileMenu = "Security Profiles";

	//Authorizations
	public static String importAuthWF = "Import Authorization Workflow";
	public static String exportAuthWF = "Export Authorization Workflow";
	public static String equipManuFacAuthWF = "Equipment Manufacturing Workflow";
	public static String ispProdAuthWF = "Isotope Production Authorization";
	public static String releaseAuthWF = "Release Authorization Workflow";
	public static String storageAuthWF = "Storage Authorization Workflow";
	public static String useAuthWF = "Use Authorization Workflow";
	public static String transferAuthWF = "Transfer Authorization Workflow";
	public static String transportAuthWF = "Transport Authorization Workflow";
	
	public static String [] faWFList = {
			
			"FollowUpAction Import Workflow",						//0//
			"FollowUpAction Export Workflow",						//1//
			"FollowUpAction Transfer Workflow",						//2//
			"FollowUpAction Transport Workflow",					//3//
			"FollowUpAction Equip Manufacturing Workflow",		//4//
			"FollowUpAction Isotope Production Workflow"			//5//
			
	};
	

	//Data role test data
	public static String DataRole_Test = "DataRoleAutomationUser";
	public static String FuncRole_Test = "FunctionalRoleAutomationUser";
	public static String SecurityRole_Test = "SecurityRoleAutomationUser";
	public static String Dup_DataRole_Test = "All Data";
	public static String OWNF_DataRole_Test = "Own Facility";
	public static String Licensee_FuncRole_Test = "Licensee";
	public static String Attb_Text = "Attributes";
	public static String EntityDetail_Text = "Entity Detail";
	public static String LinkedForms_Text = "Linked Forms";
	
	//Test Data for M2Exit criteria
	public static String FR_Prim_ext_A = "FR_primary";
	public static String FR_Sec_ext_A = "FR_secondary";
	public static String FR_Sec_ext_D = "fr_prim_ext_D";
	public static String PrimAuthType = "AuthTypePrimary";

	//Entity data
	public static String Entity_InternalNameData = "TestEntityAutomationUser";
	public static String Entity_DescriptionData = "TestEntityDescriptionAutomationUser";
	public static String Entity_SingularData = "TestSingularNameAutomationUser";
	public static String Entity_PluralData = "TestPluralNameAutomationUser";
	public static String Entity_GroupData = "Common";
	public static String Entity_RoleData = "Inventory";
	public static String Entity_MasterRole = "Master";
	public static String Entity_InvWithHistoryRole = "Inventory With History";
	public static String Entity_PublishNavigation1Data = "Inventory & Resources";
	public static String Entity_PublishNavigation2Data = "Inventory";
	
	public static String Entity_Publish_Inv_Res = "Inventory & Resources";
	public static String Entity_PublishNav_Inventory = "Inventory";
	public static String Entity_PublishNav_Resources = "Resources";
	public static String Dup_Entity_Test = "Branch";
	public static String EntityAttb_InternalNameData = "TestEntityAttributeAutomationUser";
	public static String EntityAttb_SingularData = "TestEntityAttributeAutomationUser";

	//Form builder
	public static String Entity_FormBuilder_InternalNameData = "TestEntityFormBuilderAutoUser";
	public static String Entity_FormBuilder_SingularData = "SingularDataFormBuilderAutoUser";
	public static String Entity_FormBuilder__PluralData = "PluralDataFormBuilderAutoUser";


	//Attributes
	public static String dropDownValuesVerified = "Dropdown values verified ";
	public static String dropDownValuesNOTVerified = "Dropdown values NOT verified ";
	public static String lblValmessagesNOTVerified = "Labels and validation messages NOT verified";
	public static String lblValmessagesVerified = "Labels and validation messages verified";

	public static String chkBox_internalName = "chkBoxFrmBldrAuto";
	public static String chkBox_toolTip = "chkBoxtooltipFrmBldr";
	public static String chkBox_label = "Radioactive Material";

	public static String chkBoxList_internalName = "chkBoxListFrmBldrAuto";
	public static String chkBoxList_toolTip = "chkBoxListtooltipFrmBldr";
	public static String chkBoxList_label = "Check Box List";

	public static String numeric_internalName = "nmricFrmBldrAuto";
	public static String numeric_toolTip = "nmrictooltipFrmBldr";
	public static String numeric_label = "Postal Code";

	public static String text_internalName = "txtFrmBldrAuto";
	public static String text_toolTip = "txttooltipFrmBldr";
	public static String text_label = "City Name";	

	public static String date_internalName = "dteFrmBldrAuto";
	public static String date_toolTip = "dtetooltipFrmBldr";
	public static String date_label = "dtelblFrmBldr";

	public static String dateTime_internalName = "dteTimeFrmBldrAuto";
	public static String dateTime_toolTip = "dteTimetooltipFrmBldr";
	public static String dateTime_label = "dteTimelblFrmBldr";

	public static String guid_internalName = "guidFrmBldrAuto";
	public static String guid_toolTip = "guidtooltipFrmBldr";
	public static String guid_label = "guidlblFrmBldr";

	public static String image_internalName = "imgFrmBldrAuto";
	public static String image_toolTip = "imgtooltipFrmBldr";
	public static String image_label = "imglblFrmBldr";

	public static String memo_internalName = "memoFrmBldrAuto";
	public static String memo_toolTip = "memotooltipFrmBldr";
	public static String memo_label = "memolblFrmBldr";

	public static String multiUsers_internalName = "multuserFrmBldrAuto";
	public static String multiUsers_toolTip = "multusertooltipFrmBldr";
	public static String multiUsers_label = "multuserFrmBldr";

	public static String wrkFlowAct_internalName = "wFlowFrmBldrAuto";
	public static String wrkFlowAct_toolTip = "wFlowtooltipFrmBldr";
	public static String wrkFlowAct_label = "wFlowlblFrmBldr";

	public static String timeInt_internalName = "tintervalFrmBldrAuto";
	public static String timeInt_toolTip = "tintervaltooltipFrmBldr";
	public static String timeInt_label = "tintervallblFrmBldr";

	public static String ran_toolTip = "RANtooltipFrmBldr";
	public static String ran_label = "RANlblFrmBldr";

	public static String user_internalName = "userFrmBldrAuto";
	public static String user_toolTip = "usertooltipFrmBldr";
	public static String user_label = "userlblFrmBldr";

	public static String lookUp_internalName = "lkupFrmBldrAuto";
	public static String lookUp_toolTip = "lkuptooltipFrmBldr";
	public static String lookUp_label = "lkuplblFrmBldr";

	public static String lookUpValue_internalName = "lkValFrmBldrAuto";
	public static String lookUpValue_toolTip = "lkValtooltipFrmBldr";
	public static String lookUpValue_label = "lkVallblFrmBldr";

	public static String multiLookUp_internalName = "multLkUpFrmBldrAuto";
	public static String multiLookUp_toolTip = "multLkUpFrmBldr";
	public static String multiLookUp_label = "multLkUplblFrmBldr";	

	public static String multiDocs_internalName = "multDocFrmBldrAuto";
	public static String multiDocs_toolTip = "multDoctooltipFrmBldr";
	public static String multiDocs_label = "multDoclblFrmBldr";

	public static String singDocs_internalName = "singleDocFrmBldrAuto";
	public static String singDocs_toolTip = "singleDoctooltipFrmBldr";
	public static String singDocs_label = "singleDoclblFrmBldr";

	public static String facility_LinkedEntity = "Facility";
	public static String year_LinkedEntity = "Year";
	public static String branch_LinkedEntity = "Branch";
	public static int text_lines = 1;
	public static int text_length = 3;
	public static String common_tag = "TagNameAuto";
	public static String suppliers_PracticeCat = "Suppliers";
	public static String inOperation_Txt = "In Operation";
	
	public static String chkBoxSelected = "Check Box selected";
	public static String chkBoxNOTSelected = "Check Box not selected";

	//Linked form
	public static String linkedFormName = "Default Form";
	public static String sectionTitle = "Section 2";

	//Business Entities
	public static String[] businessEntityList = { "Academic Qualification",  //0th value
			"Academic Degrees", 			//1st
			"Activity Unit", 				//2nd value
			"Amperage Unit", //3
			"Annual Dose", //4
			"Associated Equipment Status",//5
			"Attended Course", //6
			"Authority Type", //7
			"Booleans", //8
			"Branch", //9
			"Calibration", //10
			"Category", //11
			"Country",//12
			"Department", //13
			"Department Status", //14
			"Districts", //15
			"Dose", //16
			"Equipment", //17
			"Equipment Status", //18
			"Status",//19
			"Equipment & Source", //20
			"Equipment Manufacturing", //21
			"Equipment Model",//22
			"Equipment Type", //23
			"Experts", //24
			"Expert Task", //25
			"Facility",//26
			"Facility Status", //27
			"Fields", //28
			"Frequency In Month", //29
			"Gender",//30
			"Inspection Schedule", //31
			"Inventory Status", //32
			"Isotope Production",//33
			"Manufacturer", //34
			"Monitoring Status", //35
			"Nuclide", //36
			"Officers",//37
			"Operation",//38
			"Partner Agencies",//39
			"Person", //40
			"Person Status", //41
			"Physical Barrier",//42
			"Physical Form", //43
			"Practices", //44
			"Professional Degree",//45
			"Professional Qualification", //46
			"Radiation Generator", //47
			"Radiation Generator Model",//48
			"Radiation Generator Status", //49
			"Radiation Generator Type", //50
			"Regions", //51
			"Regulatory Authority",//52
			"Sealed Source", //53
			"Sealed Source Model", //54
			"Sealed Source Status",//55
			"Security Group", //56
			"Time Unit", //57
			"Training Course",//58
			"Unsealed Source", //59
			"Voltage Unit", //60
			"Wave Form", //61
			"Worker", //62
			"Year",//63
			"Users",//64
			"Authorities and Organizations", //65
			"Entities"};//66
	
	//Single field BE
	public static String oneInputField = "NameOnly";
	public static String demoData = "1Demo Data";
	public static String [] singleFldBE_Admin_CommonTbl = { //"Academic Degrees",
			"Booleans",
			"Categories of Sealed Sources",
			"Countries",
			"Emergency Response Level", //"Enforcement Statuses",
			"Event Causes", //"Extent of Events", - failing
			"Fields","Gender",
			"Health Consequences",
			"Monitoring Statuses",
			"Package Categories",
			"Package Types",
			"Person Statuses",
			"Physical Barriers",
			"Physical Forms",
			"Practice Categories",
			"Professional Degrees", //"Radiological Consequences",
			"Regulatory Reference Statuses",
			"Regulatory Reference Types",
			"Report Types",
			"Security Groups", //"Source Statuses",
			"Source Transfer Types",
			"Wave Forms",
			"Workflow Statuses"};
	
	public static String[] singleFldBE_Admin_Custom = 
//		{"Non-Compliance Major Categories",
//		"Dose Extremity Organ Lists"}; - failing
				{"Non-Compliance Major Categories",
						"Source Transport Mode",
				"Partner Agency Service Providers",
				"Inspection Statuses"};
		
	public static ArrayList<String> menuSubMenu  = new ArrayList<String>(Arrays.asList("Admin_ComMenu","Admin_CustMenu","InvRes_InvMenu","InvRes_ResMenu"));
	
	public static ArrayList<String> entName = new ArrayList<String>(Arrays.asList("Officer Tasks",	"Responses"));
	
	public static String [] singleFldBE_Inv_Inv = {"Officer Tasks",	"Responses", "Sources"};
	
	public static String [] singleFldBE_Inv_Res = {"Facility Statuses","Regions","Worker Statuses"};
	
	public static String [] Group_singleFldBE_Menu = {"Admin_ComMenu","Admin_CustMenu","InvRes_InvMenu","InvRes_ResMenu"};

	
	public static String [] twoFldBE_Admin_CommonTbl = {"Radiation Generator Types"};//,"Types of Associated Equipments"};
	
	public static String [] twoFldBE_Admin_Custom = {"Person Tasks"};
	
	public static String [] twoFldBE_Inv_Res = {"Worker Tasks"};
	
	//Dummy practices
	public static String PRACTICE_MEDICAL_DATA = "Practice Medical1";
	public static String PRACTICE_IND_DATA = "Practice Industry";
	
	public static String OPTIONAL_TEXT = "";
	public static String ADD_MODE_TEXT = "Add";
	public static String EDIT_MODE_TEXT = "Edit";
	public static String DELETE_MODE_TEXT = "Delete";
	
	public static String errorText = "Error";
	public static String successText = "Success";
	
	//Officer name
	public static String OFFICER_NAME_DATA = "ExternalOfficer";
	public static String INTERNAL_OFFICER_NAME_DATA = "internalOfficer";
	public static String GENDER_FEMALE_DATA = "Female";
	public static String GENDER_MALE_DATA = "Male";
	public static String AUTH_TYPE_DATA="AuthTypeAutomation";
	public static String PARTNER_AGENCY_NAME_DATA="PAOrgTestData";
	public static String LEGAL_BASIS_DATA = "LegalBasisAutomation";
	public static String CHAIRPERSON_DATA = "ChairPersonAutomation";
	public static String TEST_EMAIL_DATA = "AutoEmail@yopmail.com";
	public static String ORG_FAC_STATUS_DATA="FacilityStatusTestData";
	public static String FACILITY_USERNAME_DATA = "FacilityStatusTestData";
	public static String EXTERNAL_USER = "External";
	public static String INTERNAL_USER = "Internal";
	
	public static String primary = "Primary";
	public static String secondary = "Secondary";
	
	//Expert Data
	public static String EXPERT_USERNAME_DATA = "ExpertOfficer";

	//UserName
	public static String TestUserName = "AutoUser";

	//Facility data
	public static String FACILITY_NAME_DATA = "Facility 1";

	//Department data
	public static String DEPT_NAME_DATA = "Department 1";

	//Calibration data
	public static String CALIB_NAME_DATA = "AutoCalibration";

	//Date time data
	public static String DATE_TIME_PICKR_DATA = "05/03/2020 18:36";

	//Certificate data
	public static String CERTI_DATA = "666";

	//Users organisation
	public static String REG_AUTHORITY_DATA = "Regulatory Authority";
	public static String PART_AGENCY_ORG_DATA = "Partner Agency"; 

	//Data Role
	public static String DATAROLE_ALL_DATA = "All Data";
	public static String DATAROLE_SECURITY = "Security";
	
	//updated text
	public static String UPDATED_TXT = "Updated";
	
	//WF Statuses
	public static String WF_Status_Submitted_Txt = "Submitted";
	public static String WF_Status_Completed_Txt = "Completed";
	
	public static String [] faDataFormInput = {
			
			"Import Facility2",						//0// Source location - import facility
			"In-Process",							//1// Source Status
			"C:\\Temp\\securityPlan.pdf",			//2// security plan
			"455500P231/22",						//3//customs number
			"455500P231/223434",					//4// Bill lading number	
			"Exp facility2",						//5//Source location - Export facility
			"Equip facility2",						//6//Source location - EQP facility
			"Isotope facility2",					//7//Source location - Isotope Prod facility
			"Transf facility2",						//8//Source location - Transfer facility
			"Transp facility2"						//9//Source location - Transport facility	
	};

	public static String [] workflowStatuses = {
			"Draft",								//0//
			"Submitted",							//1//
			"Completed",							//2//
			"Reassigned"							//3//
	};

}
