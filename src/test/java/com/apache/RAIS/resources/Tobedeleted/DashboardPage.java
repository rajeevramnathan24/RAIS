package com.apache.RAIS.resources.Tobedeleted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {

	WebDriver wi;
	
	//Dashboard App Logo xpath
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div/div[1]/div/div")
	public WebElement dashbrdAppLogo_XPath;
	
	//Dashboard App Logo text xpath
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div/div[1]/div/span")
	public WebElement dashbrdAppLogoFullFrm_XPath;

	//logged in user xpath
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div/nav/div/ul/li[4]/div/div")
	public WebElement loggedinUser_XPath;

	//logout xpath
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[1]/div/div/nav/div/ul/li[4]/div/ul/li[2]/a")
	public WebElement logout_XPath;
	
	//Administration xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"navbarCollapse\"]/ul/li[7]/a")
	public WebElement administration_XPath;
	
	//Data roles xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"navbarCollapse\"]/ul/li[7]/div/div[1]/ul/li[1]/a")
	public WebElement DataRoles_XPath;
	
	//Entities xpath
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div[2]/div/div/div/div[2]/div/ul/li[6]/div/div[3]/ul/li[2]/a")
	public WebElement entities_XPath;
	
	//Restriction tab xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"datarole\"]/div/div[3]/div/div[2]/div/div[1]/div/div/div/a[2]/span[1]")
	public WebElement restrictTab_XPath;
	
	public DashboardPage(WebDriver wi){

        this.wi = wi;

        //This initElements method will create all WebElements

        PageFactory.initElements(wi, this);
        
    }   
	
	//Set user name in textbox

    public void inputUserName1(String strUserName){

    	dashbrdAppLogo_XPath.sendKeys(strUserName);     
    }

    //Set password in password textbox

    public void setPassword1(String strPassword){

    	dashbrdAppLogo_XPath.sendKeys(strPassword);

    }
	
  //Set password in password textbox

    public void ClickLoginBtn1(){

    	dashbrdAppLogo_XPath.click();

    }
	
    
	
	
}
