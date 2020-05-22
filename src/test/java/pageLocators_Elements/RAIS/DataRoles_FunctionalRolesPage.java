package pageLocators_Elements.RAIS;



public class DataRoles_FunctionalRolesPage {
		
	//Data Roles permission tab xpath
	public String dataRolePermTab_XPath = "//*[@id='Permissions']";
	public String dataRoleRestrictionTab_XPath = "//*[@id='Restrictions']";
	//public String dataRolePermTab_XPath = "//div/a[@href='Permissions']";
			//span[contains(text(),'Permissions')]";
	//*[contains(@text(),'Region is required.')]
	//a[href='Permissions']
	//Add new permission/ restrictions btn xpath
	//public String addNewPermissionRestrictBtn_XPath = "//*[@id=\"datarole\"]/div/div[2]/div/div[1]/div/button[3]";
	public String addNewPermissionRestrictBtn_XPath = "//*[@id='AddNewPermission']";
	//public String addNewPermissionRestrictBtn_XPath = "//button[@title='Add New Permissions']";
	public String FunctionalRolePermissionRestrictBtn_XPath = "//button[text()='Add New Permission']";
	public String functionalRoleaddNewPermissionRestrictBtn_XPath = "//button[normalize-space(text())='Add New Permission']";
	//Loader xpath
	public String loader_Xpath = "//div[@class='loading']";

	//Data roles restrictions xpath
	//public String dataRoleRestrictionTab_XPath = "//*[@id=\"datarole\"]/div/div[2]/div/div[2]/div/div[1]/div/div/div/a[2]/span[1]";	 
	//public String dataRoleRestrictionTab_XPath = "//*[contains(@href,'Restrictions')]";	 

	//logout xpath
	public String logout_XPath = "/html/body/div[1]/div[1]/div/div/nav/div/ul/li[4]/div/ul/li[2]/a";	 
	
	//Administration xpath
	public String administration_XPath = "//*[@id=\"navbarCollapse\"]/ul/li[7]/a";	 
	
	//Data roles xpath
	public String DataRoles_XPath = "//*[@id=\"navbarCollapse\"]/ul/li[7]/div/div[1]/ul/li[1]/a";	 
	
	//Entities xpath
	public String entities_XPath = "/html/body/div[1]/div[2]/div/div/div/div[2]/div/ul/li[6]/div/div[3]/ul/li[2]/a";	 
	
	//Restriction tab xpath
	public String restrictTab_XPath = "//*[@id=\"datarole\"]/div/div[3]/div/div[2]/div/div[1]/div/div/div/a[2]/span[1]";	
	
	//Left pane id xpath to search data/ functional roles
	public String dataRoleFuncRoleLeftPane_id_XPath = "//*[@id=\\'";
	
	//Left pane xpath to search data/ functional roles
	public String dataRoleFuncRoleLeftPane_XPath = "']/div/div[1]/div/div";
	
	//Left pane entire table xpath to search data/ functional roles
	public String dataRoleFuncRoleLeftPane_Table_XPath = "']/div/div[1]/div/div/div[2]/ul/li";
	
	public String dataRoleLabel_XPath = "//div[@class='list-group-title d-flex']//h4[contains(text(),'Data Role')]";
	public String funcRoleLabel_XPath = "//div[@class='list-group-title d-flex']//h4[contains(text(),'Functional Role')]";
	
	//Add new role btn
	//public String addNewRoleBtn = "//*[@id=\"datarole\"]/div/div[1]/div/div/div[3]/button/span[1]";
	//public String addNewRoleBtn = "//*[@id='datarole']/div/div[1]/div/div/div[1]/span/a";
	//public String addNewRoleBtn = "//*[@id='AddNewRole']";
	public String FR_addNewRoleBtn = "//*[@id='functionalrole']//div//span//a[@id='AddNewRole']";
	
	public String DR_addNewRoleBtn = "//*[@id='datarole']//div//span//a[@id='AddNewRole']";
	
	//Edit role btn
	//public String editRoleBtn_XPath = "//button[text()='Edit Role']";
	public String FR_editRoleBtn_XPath = "//*[@id='functionalrole']//div[@class='button-group ml-auto']//button[text()='Edit Role']";
	public String DR_editRoleBtn_XPath = "//*[@id='datarole']//div[@class='button-group ml-auto']//button[text()='Edit Role']";
		
	//Delete role btn
	public String FR_deleteRoleBtn_XPath = "//*[@id='functionalrole']//div[@class='button-group ml-auto']//button[text()='Delete Role']";
	public String DR_deleteRoleBtn_XPath = "//*[@id='datarole']//div[@class='button-group ml-auto']//button[text()='Delete Role']";
	
	//Security role link for functional page
	public String securityRoleLink_XPath = "//div[@class='button-group ml-auto']//a[text(),'Security Profiles']";
	
	//No permissions granted text
	public String noPermissionGranted_Label_XPath = "//table[@class='k-grid-table']//tbody//tr[@class='k-grid-norecords']//td[contains(text(),'No permissions granted to this Role')]";
	
	
//delete confirmation page on top
	public String deletePopupMsg_XPath = "//div[@id='max-width-dialog-title']";
	public String deletePopupMsgYesBtn_XPath = "//button[contains(text(),'Yes')]";
	public String deletePopupMsgNoBtn_XPath = "//button[contains(text(),'No')]";
	public String delDataRolePerm_PopupMSGTxt_XPath= "//div[@class='dialog-content']";
    
	//Permission list table
	public String permList_XPath = "//*[@id='nav-tabpanel-0']/div/div/div[2]/div/div[1]/table/tbody/tr/td[1]";
	
	//permission and restriction table details
	//public String perm_restrct_Table_XPath = "//table[@class='k-grid-table']";
	public String perm_restrct_Table_XPath = "//*[@id='nav-tabpanel-0']";
	
	public String restrct_XPath = "";
	
	
	
	
	
	
	
	
	
	
	//Page Constants *******************************************
	//Role based constants
		public String DATA_ROLE_Txt = "datarole";
		public String FUNC_ROLE_Txt = "functionalrole";
		public String DATA_ROLE_LBL_Txt = "Data Role";
		public String FUNC_ROLE_LBL_Txt = "Functional Role";
		public String ADDROLE_BTN_Txt = "Add New Role";
		public String EDITROLE_BTN_Txt = "Edit Role";
		public String DELETEROLE_BTN_Txt = "Delete Role";
		public String ADDNEW_PERMISSION_BTN_Txt = "Add New Permission";
		public String NO_PERMISSIONS_GRANTED_Txt="No permissions granted to this Role";
		public String SECURITY_PROFILE_Txt = "Security Profiles";
		
		//Delete pop-up message text compare
		public String DELETE_DATAROLE_POPMSG_Txt="Deleting this role will delete all the permissions and restrictions associated with it. "
				+ "Are you sure you want to delete this role?";
		public String DELETE_FUNCROLE_POPMSG_Txt="Deleting this role will delete all the permissions associated with it. "
				+ "Are you sure you want to delete this role?";
	
}
