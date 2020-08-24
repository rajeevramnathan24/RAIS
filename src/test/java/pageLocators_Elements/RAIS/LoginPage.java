package pageLocators_Elements.RAIS;


public class LoginPage {

	//Locator details
	public String userId_XPath = "//*[@id='Username']";
	public String pwd_XPath = "//*[@id='Password']";
//	public String loginBtn_XPath = "//button[contains(text(),'Login')]";
	public String loginBtn_XPath = "//*[@id='btnLogIn']";
	public String RIASHeaderLabel_XPath = "//*[@class='form-signin-heading']";
	
	public String externalLogin_XPath = "//*[contains(@href,'#local-login')]";
	public String internalLogin_XPath = "//*[contains(@href,'#external-login')]";
	
	public String winAuthBtn_XPath = "//*[@id='external-login']";
	
	//Labels xpath
	public String userName_label_XPath = "//label[contains(text(),'Username')]";
	public String pwd_label_XPath = "//label[contains(text(),'Password')]";
	
	public String frgtPwd_XPath = "//*[contains(@href,'/idp/Account/ForgotPassword')]";
	
	//validation for Login page
	public String userName_validationMsg_XPath = "//span[contains(@text(),'The Username field is required.')]";
	public String pwd_validationMsg_XPath = "//span[contains(@text(),'The Password field is required.')]";
	
	public String userNameValidationError_XPath = "//*[@id='Username-error']";
	public String pwdValidationError_XPath = "//*[@id='Password-error']";
	public String invalidCredValidationMsg_XPath = "//*[@class='error-message']";
	
	//primary user/ secondary user login page
	public String primaryUser_Dropdown_Xpath = "//*[@id='UserAccount']";
	
	//submit button
	public String submitBtn_XPath = "//button[contains(text(),'Submit')]";
	
	
	
	
	//Page Constants ********************************************************
	//Login page
	public String RAIS_Txt ="RAIS+";
	public String EXT_LOGIN_Txt ="External Login";
	public String LOGIN_BTN_Txt ="Login";
	public String INT_LOGIN_Txt ="Internal Login";
	public String WINDOWS_AUTH_Txt ="Windows Authentication";	
	public String FORGOT_PWD_Txt = "Forgot Password";
	public String USERNAME_LBL_Txt = "Username";
	public String PWD_LBL_Txt = "Password";
	public String USERNAME_VALIDATIONMSG_Txt = "Username is required";
	public String PWD_VALIDATIONMSG_Txt = "Password is required";
	public String INVALID_CREDENTIALS_Txt = "Please enter the valid credentials.";
	public String PRIMARY_Txt = "Primary";
	
	
}
