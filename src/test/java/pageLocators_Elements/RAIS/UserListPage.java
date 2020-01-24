package pageLocators_Elements.RAIS;

public class UserListPage {
	//Add profile btn
	public String addNewUserBtn_XPath = "//button[text()='Add New User']";
	public String noUsersMsg_XPath = "//*[@id='users']//table//tbody//tr[td[contains(., 'No users found')]]";
	public String userListColHeaderName_XPath = "//*[@id='users']//table//thead//tr//th//a[span[contains(text(),'Name')]]";
	public String userListColHeaderEmail_XPath = "//*[@id='users']//table//thead//tr//th//a[span[contains(text(),'Email')]]";
	public String userListColHeaderFuncRole_XPath = "//*[@id='users']//table//thead//tr//th//a[span[contains(text(),'Functional Role')]]";
	public String userListColHeaderStatus_XPath = "//*[@id='users']//table//thead//tr//th//a[span[contains(text(),'Status')]]";
	
	public String nameLabel_XPath = "//*[@id='users']//label[contains(., 'No security profiles found')]";
	
	public String userListTable_XPath = "//*[@class='k-grid-table']";
	
	
	
	
	
	
	//Page Constants *********************************************************************
		//User list page
		public String ADDNEW_USER_Txt ="Add New User";
		public String NO_USERS_Txt = "No users found";
		public String USERLIST_COLHEADER_NAME_Txt = "Name";
		public String USERLIST_COLHEADER_EMAIL_Txt = "Email";
		public String USERLIST_COLHEADER_FUNCROLES_Txt = "Functional Role";
		public String USERLIST_COLHEADER_STATUS_Txt = "Status";

}
