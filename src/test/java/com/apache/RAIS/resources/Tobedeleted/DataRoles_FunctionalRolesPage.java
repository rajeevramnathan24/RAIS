package com.apache.RAIS.resources.Tobedeleted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DataRoles_FunctionalRolesPage {

	WebDriver wi;
	
	//Data Roles permission tab xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"datarole\"]/div/div[2]/div/div[2]/div/div[1]/div/div/div/a[1]/span[1]")
	public WebElement dataRolePermTab_XPath;
	
	//Add new permission/ restrictions btn xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"datarole\"]/div/div[2]/div/div[1]/div/button[3]")	
	public WebElement addNewPermissionRestrictBtn_XPath;

	//Data roles restrictions xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"datarole\"]/div/div[2]/div/div[2]/div/div[1]/div/div/div/a[2]/span[1]")
	public WebElement dataRoleRestrictionTab_XPath;

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
	
	public DataRoles_FunctionalRolesPage(WebDriver wi){

        this.wi = wi;

        //This initElements method will create all WebElements

        PageFactory.initElements(wi, this);
        
    }   
	
    
	
	
}
