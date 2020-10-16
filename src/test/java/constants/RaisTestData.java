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
	
	//Authorizations
		public static String importAuthWF = "Import Authorization Workflow";
		public static String exportAuthWF = "Export Authorization Workflow";
		public static String equipManuFacAuthWF = "Equipment Manufacturing Workflow";
		public static String ispProdAuthWF = "Isotope Production Authorization";
		//public static String ispProdAuthWF = "Isotope Production Authorization Workflow";
		public static String releaseAuthWF = "Release Authorization Workflow";
		public static String storageAuthWF = "Storage Authorization Workflow";
		public static String useAuthWF = "Use Authorization Workflow";
		public static String transferAuthWF = "Transfer Authorization Workflow";
		public static String transportAuthWF = "Transport Authorization Workflow";
		
		//workFlowNames
		public static ArrayList<String> workflowNames = new ArrayList<String>(Arrays.asList(					
				"Import Authorization Workflow",							
				"Export Authorization Workflow",					
				"Equipment Manufacturing Workflow", 					
				"Isotope Production Authorization", 	
				"Release Authorization Workflow",		
				"Storage Authorization Workflow",	
				"Use Authorization Workflow",		
				"Transfer Authorization Workflow",
				"Transport Authorization Workflow"
				));	
	
	public static ArrayList<String> associationsInputData_import = new ArrayList<String>(Arrays.asList(					
			"LICIMP",							
			"Import Facility2",					
			"Import Dept2", 					
			"Imp facility 2 -sealed source", 	
			"Import2 - Unsealed source",		
			"Import facility- Radiation Gen2",	
			"Imp facility 2 - Asso equip",		
			"Imp2 - person",
			"LicenseeImportFacility",
			"rais_test1@e-zest.in"
			));
	
	public static ArrayList<String> associationsInputData_export = new ArrayList<String>(Arrays.asList(					
			"LICExp",									//0//export user name
			"Exp facility2",							//1//export facility
			"Exp Dept2", 								//2//export department name import
			"Exp facility2 - Sealed source", 			//3//export  sealed source name
			"Exp facility2- unsealed source",			//4//export  unsealed source name
			"Exp facility2 - Rad Gen",					//5//export  radiation generator name
			"Exp facility2 - associated equipment",		//6//export associated equipment name
			"Exp facility2 - person",					//7//export person
			"LicenseeExportFacility",					//8//name
			"rais_test2@e-zest.in"						//9//email
			)); 
			
	public static ArrayList<String> associationsInputData_storage = new ArrayList<String>(Arrays.asList(					
			"LICStorage",									//0//export user name
			"Storage facility2",							//1//export facility
			"Storage Dept2", 								//2//export department name import
			"Storage facility2- Sealed source", 			//3//export  sealed source name
			"Storage facility2- unsealed source",			//4//export  unsealed source name
			"Storage facility2-Rad gen",					//5//export  radiation generator name
			"Storage facility2 - associated equipment",		//6//export associated equipment name
			"Storage facility2 - person",					//7//export person
			"LicenseeStorageFacility",						//8//name
			"str@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> associationsInputData_use = new ArrayList<String>(Arrays.asList(					
			"LICUse",									//0//export user name
			"Use facility2",							//1//export facility
			"Use Dept2", 								//2//export department name import
			"Use facility2- Sealed source", 			//3//export  sealed source name
			"Use facility2- unsealed source",			//4//export  unsealed source name
			"Use facility2 - Rad Gen",					//5//export  radiation generator name
			"Use facility2- associated equipment",		//6//export associated equipment name
			"Use facility2 - person",					//7//export person
			"LicenseeUseFacility",						//8//name
			"use@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> associationsInputData_equipmentMWF = new ArrayList<String>(Arrays.asList(					
			"LICEQP",									//0//export user name
			"Equip facility2",							//1//export facility
			"Equip Dept2", 								//2//export department name import
			"Equip facility2 - Sealed source", 			//3//export  sealed source name
			"Equip facility2- unsealed source",			//4//export  unsealed source name
			"Equip facility2 - Rad Gen",					//5//export  radiation generator name
			"Equip facility2 - associated equipment",		//6//export associated equipment name
			"Equip facility2- person",						//7//export person
			"LicenseeEqpFacility",							//8//name
			"rais_test3@e-zest.in"							//9//email
			));
	
	public static ArrayList<String> associationsInputData_isotopeProdWF = new ArrayList<String>(Arrays.asList(					
			"LICISP",									//0//export user name
			"Isotope facility2",							//1//export facility
			"Isotop Dep2", 								//2//export department name import
			"Isotope facility2 - Sealed source", 			//3//export  sealed source name
			"Isotope facility2- unsealed source",			//4//export  unsealed source name
			"Isotope facility2 - Rad Gen",					//5//export  radiation generator name
			"Isotope facility2 - associated equipment",		//6//export associated equipment name
			"Isotope facility2 - person",					//7//export person
			"LicenseeIsotopeFacility",						//8//name
			"isp@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> associationsInputData_release = new ArrayList<String>(Arrays.asList(					
			"LICREL",									//0//export user name
			"Rel facility2",							//1//export facility
			"Rel Dept2", 								//2//export department name import
			"Rel facility2 - Sealed source", 			//3//export  sealed source name
			"Rel facility2- unsealed source",			//4//export  unsealed source name
			"Rel facility2 - Rad Gen",					//5//export  radiation generator name
			"Rel facility2 - associated equipment",		//6//export associated equipment name
			"Rel facility2 - person",					//7//export person
			"LicenseeReleaseFacility",						//8//name
			"rel@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> associationsInputData_transfer = new ArrayList<String>(Arrays.asList(					
			"LICTRANSF",									//0//export user name
			"Transf facility2",							//1//export facility
			"Transf Dept2", 								//2//export department name import
			"Transf facility2 - Sealed source", 			//3//export  sealed source name
			"Transf facility2- unsealed source",			//4//export  unsealed source name
			"Transf facility2-Rad Gen",					//5//export  radiation generator name
			"Transf facility2 - associated equipment",		//6//export associated equipment name
			"Transf facility2 - person",					//7//export person
			"LicenseeTransferFacility",						//8//name
			"transf@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> associationsInputData_transport = new ArrayList<String>(Arrays.asList(					
			"LICTRNSP",									//0//export user name
			"Transp facility2",							//1//export facility
			"Transp Dept2", 								//2//export department name import
			"Transp facility2 - Sealed source", 			//3//export  sealed source name
			"Transp facility2- unsealed source",			//4//export  unsealed source name
			"Transport Facility2 - Rad Gen",					//5//export  radiation generator name
			"Transp facility2 - associated equipment",		//6//export associated equipment name
			"Transp facility2 - person",					//7//export person
			"LicenseeTransportFacility",						//8//name
			"trnsp@yopmail.com"								//9//email
			));
	
	public static ArrayList<String> inputRequestedTermsDataForm = new ArrayList<String>(Arrays.asList(					
			"1.2E+45",							//0//Max radioactivity
			"Bq",								//1//Unit
			"Lifeline Import",					//2//agency name
			"C:\\Temp\\PL_Doc.pdf",				//3//doc1	
			"C:\\Temp\\BReg.pdf",				//4//doc2
			"C:\\Temp\\Tax.pdf",				//5//doc3
			"Partner Agency 1",					//6//partner agency for export
			"Transf facility2",					//7//Transfer facility for Transfer
			"Transf Dept2",						//8//Transfer Department for Transfer
			"Permanent",						//9//Transfer Department for Transfer
			"10",								//10//duration of Transfer and used in transport
			
			"A",								//11//package type - transport
			"III-Yellow",						//12//package category - transport
			"Inland Water Way",					//13//mode of transport - transport
			"Transp facility2",					//14//consignor/ consignee facility - transport
			"Mumbai",							//15//origin - transport
			"Pune"								//16//destination - transport		
			));
	
	public static ArrayList<String> commonInputData = new ArrayList<String>(Arrays.asList(					
			"Submit",									//0// submit button
			"Yes",										//1// Yes value to be used in Inspection Scope data form
			"No",										//2// No value to be used in Inspection scope data form
			"This step has been is Reviewed/ approved",	//3// Memo field Reviewed by field
			"Proceed Without Announcement",				//4// Button for inspection
			"Accept",									//5//Auth terms page
			"Vishal",									//6//approved by
			"This step has been is Incomplete/ Reviewed/ approved/ rejected- Demo. ",	//7//
			"Vishal",										//8//
			"Decline",										//9//decline acceptance form
			"Updated comments"								//10// updated text
			));	
	
	public static ArrayList<String> paymentInputData = new ArrayList<String>(Arrays.asList(	
			"C:\\Temp\\Invoice.pdf",			//0//invoice path	
			"C:\\Temp\\Receipt.pdf",			//1//Receipt path
			"6000",								//2//Amount
			"card",								//3//Payment mode
			"Skip",								//4// Skip
			"Attach Invoice",					//5// Attach invoice form
			"Confirm Payment"					//6// Confirm payment Data form
			));
	
//		
//	public static String [] associationsInputData_export = { 
//			"LICExp",									//0//export user name
//			"Exp facility2",							//1//export facility
//			"Exp Dept2", 								//2//export department name import
//			"Exp facility2 - Sealed source", 			//3//export  sealed source name
//			"Exp facility2- unsealed source",			//4//export  unsealed source name
//			"Exp facility2 - Rad Gen",					//5//export  radiation generator name
//			"Exp facility2 - associated equipment",		//6//export associated equipment name
//			"Exp facility2 - person",					//7//export person
//};		
			
	public static String [] remaining =		{
			"Submit",									//5// submit button
			"Yes",										//6// Yes value to be used in Inspection Scope data form
			"No",										//7// No value to be used in Inspection scope data form
			"This step has been is Reviewed/ approved",	//8// Memo field Reviewed by field
			"Proceed Without Announcement",				//9// Button for inspection
			"Accept"									//10//Auth terms page
	
};
	
	//Regulator and RAR role and user creation details
	public static ArrayList<String> regulator_RAR_Data = new ArrayList<String>(Arrays.asList(	
			"Reg",								//0//Regulator role name	
			"RAR",								//1//RAR role name
			"??",								//2//Amount
			"??",								//3//Payment mode
			"??",								//4// Skip
			"?? Invoice",					//5// Attach invoice form
			"?? Payment"					//6// Confirm payment Data form
			));
	
	

}
