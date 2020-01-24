package pageLocators_Elements.RAIS;


public class ForgotPasswordPage {

	//Heading label
	public String frgotPageRIASHeaderLabel_XPath = "//*[@class='form-signin-heading']";

	//Header name
	public String forgotPageHeaderLabel_XPath = "//*[@class='registerTitle']";
	public String forgotPageHeaderLabel1_XPath = "//*[@class='loginTitleSection']";

	//Labels xpath
	public String inputField_label_XPath = "//label[contains(text(),'Input Field')]";
	public String inputValue_label_XPath = "//label[contains(text(),'Input Value')]";

	//username xpath
	public String userName_label_XPath = "//*[@id='lblInputValue']";

	//Locator details
	public String frgotUserNameRadioBtn_XPath = "//*[@id='Username']";
	public String frgotUserNameRadioBtnLabel_XPath = "//label[contains(text(),'Username')]";
	public String frgotEmailRadioBtn_XPath = "//*[@id='Email']";
	public String frgotEmailRadioBtnLabel_XPath = "//label[contains(text(),'Email')]";


	public String inputUsrNameEmailID_XPath = "//*[@id='InputValue']";
	public int inputUsrNameEmailID_MaxLength = 255;

	public String submitBtn_XPath = "//button[contains(text(),'Submit')]";	

	public String loginPageLink_XPath = "//*[contains(@href,'/idp')]";

	//validation error message
	public String userNameInvalidMsg_Xpath="//*[@class='error-message username-null-error-message']";
	public String emailInvalidMsg_Xpath="//*[@class='error-message email-null-error-message']";
	public String userNotFoundMsg_XPath = "//*[@class='error-message']";
	
	






	//Page Constants ********************************************************
	//Login page
	public String FORGOTPAGE_RAIS_Txt ="RAIS+";
	public String FORGOTPAGE_HEADER1_Txt="Forgot your password?";
	public String FORGOTPAGE_HEADER2_Txt="Forgot your password?\nWe'll help you to reset it and get back on track";
	public String FORGOTPAGE_USERNAME_LBL_Txt ="Username";
	public String FORGOTPAGE_INPUTVALUE_LBL_Txt ="Input Value";
	public String FORGOTPAGE_USRNAME_RDOBTN_Txt ="Username";
	public String FORGOTPAGE_EMAIL_RDOBTN_Txt ="Email";
	public String FORGOTPAGE_SUBMIT_BTN_Txt ="Submit";
	public String FORGOTPAGE_LOGIN_LINK_Txt ="Back to Login";
	public String FORGOTPAGE_INVALID_USERNAME_Txt="Username is required";
	public String FORGOTPAGE_INVALID_EMAIL_Txt="Email id is required with valid format";
	public String FORGOTPAGE_USERNOTFOUND_Txt="User details not found";
	
}
