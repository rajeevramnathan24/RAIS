package pageLocators_Elements.RAIS;

public class DashboardPage {

	
	//Dashboard App Logo xpath
	//public String dashbrdAppLogo_XPath = "/html/body/div[1]/div[2]/nav/div/a/span[1]";	 
	public String dashbrdAppLogo_XPath = "//span[@class='app-logo']";
	
	//Dashboard App Logo text xpath
	public String dashbrdAppLogoFullFrm_XPath = "//span[@class='app-logo-text']";	 
	
	//dashboard is under developement
//	public String dashboardUnderDevelopment_XPath = "//*[@id='dashboard']//h3[contains(text(),'Dashboard is under development')]";

	//Top menus
	public String mainMenu_XPath = "//*[@class='navbarCollapse']";
	
	//logged in user xpath
	
	//public String loggedinUser_XPath = "/html/body/div[1]/div[1]/div/div/nav/div/ul/li[4]/div/div";	 
	//public String loggedinUser_XPath = "//*[@class='nav-item dropdown']//div[@class='nav-link show']//div[contains(text(),'Administrator')]";
	//public String loggedinUser_XPath = "//*[@class='app-login dropdown-toggle']"	;
	
	public String loggedinUser_XPath = "/html/body/div[1]/div[1]/div/div/nav/div/ul/li[4]/div";
	
	
	public String xx = "//a[text()='Logout']/@href";
	public String newRoleName_XPath = "//*[@class='nav-item dropdown']//div//div[contains(text(),'Administrator')]";
	
	//public String loggedinUser_XPath= "//div[@class='nav-link show']//div[@title='Logout User']";

	//logout xpath
	public String logout_XPath = "//*[@class='nav-link show']//ul//li//a[text()='Logout']";
	public String profile_XPath = "//*[contains(@href,'Profile')]";	
	//public String logout_XPath = "//*[contains(@href,'Logout')]";
	
	public String newlogOut_XPath1 = "//*[@class='nav-item dropdown']//div//ul//li//a[@href='/Home/Profile']";
	public String newlogOut_XPath2 = "//*[@class='nav-item dropdown']//div//ul//li//a[text()='Logout']";
	//public String logout_XPath = "//*div[@class='dropdown-menu profile-dropdown show']//a[contains(text(),'Logout']";
	
	//*[@href='/idp%2FAccount/Logout']";
	//Dashboard menu Xpath
	//public String dashboard_XPath = "//*[@id='main-menu']/ul/li[2]/a";
	//public String dashboard_XPath = "//div[@class='navbarCollapse']//ul//li//a[contains(text(),'Dashboard']";
	public String dashboard_XPath = "//*[@id='main-menu']/li[2]/a";
	
	public String regulatoryProcess_XPath = "//*[@id='main-menu']/li[3]/a";
	public String inventResources_XPath = "//*[@id='main-menu']/li[4]/a";
	public String reportStats_XPath = "//*[@id='main-menu']/li[5]/a";
	public String settings_XPath = "//*[@id='main-menu']/li[6]/a";
	//Administration xpath
	//public String administration_XPath = "//*[@id='main-menu']/li[7]/a";	
	
	public String administration_XPath = "//*[@id='main-menu']//li//a[text()='Administration']"	;
	public String invent_Resources_XPath = "//*[@id='main-menu']//li//a[text()='Inventory & Resources']"	;
	
	//Top most menu item
	//public String notification_XPath = "//*[@class='right-section header-td']/ul/li[2]/a";
	public String notification_XPath = "//div[@class='header-row']//a[contains(text(),'Notification ')]";
	public String help_XPath = "//div[@class='header-row']//a[contains(text(),'Help')]";
	public String searchIcon_XPath = "//*[@class='searchIcon']";
	public String administrator_XPath = "//*[@class='app-login dropdown-toggle']";
	
	//Data roles xpath
	public String DataRoles_XPath = "//a[@href='/Administration/DataRole']";	
	
	//Data roles xpath
	//public String FunctionalRoles_XPath = "//a[@href='/Administration/FunctionalRole']";	
	//public String FunctionalRoles_XPath = "//*[@id='main-menu']//a[@href='/Administration/FunctionalRole']";
	public String FunctionalRoles_XPath = "//*[@id='main-menu']//a[text()='Functional Roles']"	;
	
	//Security roles xpath
	//public String securityRoles_XPath = "//a[@href='/Administration/SecurityProfile']";	
	public String securityRoles_XPath = "//*[@id='main-menu']//a[text()='Security Profiles']"	;
	
	//User profile link
	public String usersLink_XPath = "//a[@href='/Administration/User']";
	
	//Entities xpath
	public String entities_XPath = "//*[@id='main-menu']//div//ul//li//a[text()='Entities']";
	
	//Restriction tab xpath
	public String restrictTab_XPath = "//*[@id=\"datarole\"]/div/div[3]/div/div[2]/div/div[1]/div/div/div/a[2]/span[1]";	
	
	//xpath for newly created entity
	//public String newEntityCreated_XPath = "//*[@id='main-menu']//a[text()='"+RaisTestData.Entity_PluralData +"']"	;
	public String newEntityCreated_XPath = "//*[@id='main-menu']//a[contains(text(),'PluralDataFormBuilderAutoUser')]";
	public String AmpUnit_XPath = "//*[@id='main-menu']//a[contains(text(),'Amperage Units')]";
	
	public String prefixBusinessEntity_XPath = "//*[@id='main-menu']//a[contains(text(),'";
	public String suffixBusinessEntity_XPath = "')]";
	
	//Facility xpath
	public String facility_XPath = "//*[@id='main-menu']/li[4]/div/div[2]/ul/li[3]/a[text()='Facilities']";
	
	//District xpath
	public String district_XPath = "//*[@id='main-menu']/li[4]/div/div[2]/ul/li[1]/a[text()='Districts']";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Page Constants *********************************************************************
	//Landing page
	public String LP_APPLOGO_Txt ="RAIS+";
	public String LP_APPLOGO_FULLName_Txt ="Regulatory Authority Information System";
	public String LP_APP_UNDERDEVELOPMENT_MSG_Txt = "Dashboard is under development";
	public String LP_LOGGEDIN_AS_ADMIN_Txt = "Administrator ";
	public String LP_MY_PROFILE_Txt = "My Profile";
	public String LP_LOGOUT_Txt = "Logout";
}
