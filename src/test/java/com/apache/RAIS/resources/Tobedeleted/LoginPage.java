package com.apache.RAIS.resources.Tobedeleted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver wi;
	
	//Username xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"Username\"]")
	public static WebElement userId_XPath;
	
	//Password xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"Password\"]")
	public static WebElement pwd_XPath;

	//Login button
	@FindBy(how = How.XPATH, using = "//*[@id=\"local-login\"]/form/fieldset/div[4]/div[2]/button")
	public static WebElement loginBtn_XPath;
	
	//RIAS+ Text
	@FindBy(how = How.XPATH, using = "/html/body/div/div/h2")
	public static WebElement RIASHeaderLabel_XPath;
		
	//External Login
	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/ul/li[1]/a")
	public static WebElement externalLogin_XPath;
	
	//Internal Login
	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/div/ul/li[2]/a")
	public static WebElement internalLogin_XPath;
	
	//WindowAuth Login
	@FindBy(how = How.XPATH, using = "//*[@id=\"external-login\"]/ul/li/a")
	public static WebElement winAuth_XPath;
	
	public   LoginPage(WebDriver wi){

        this.wi = wi;

        //This initElements method will create all WebElements

        PageFactory.initElements(wi, this);
        
    }   
	
	//Set user name in textbox

  
    //Set password in password textbox

    public void setPassword(String strPassword){

    	pwd_XPath.sendKeys(strPassword);

    }
	
  //Set password in password textbox

    public void ClickLoginBtn(){

    	loginBtn_XPath.click();

    }
	
    
	public static void loginApplication(String uidText, String pwdText) throws Exception{

        //Fill user name

		userId_XPath.sendKeys(uidText);

		Thread.sleep(3000);
		
        //Fill password

		pwd_XPath.sendKeys(pwdText);

        //Click Login button

		loginBtn_XPath.click();          

    }
	
}
