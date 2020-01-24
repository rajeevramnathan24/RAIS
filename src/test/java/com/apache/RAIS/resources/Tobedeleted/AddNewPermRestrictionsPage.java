package com.apache.RAIS.resources.Tobedeleted;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddNewPermRestrictionsPage {

	WebDriver wi;
	
	//Save button xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[2]/div/button[3]")
	public WebElement savBtn_XPath;
	
	//Practice and Title xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[1]/div/div[2]/p")
	public WebElement practice_title_XPath;
	
	//Region and query xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[2]/div/div[2]/p")
	public WebElement region_query_XPath;
	
	//District and Entity xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[3]/div/div[2]/p")
	public WebElement district_entity_XPath;
		
	//Facility and entities xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[1]/form/div[4]/div/div[2]/div/p")
	public WebElement facility_entities_XPath;
	
	//Cancel Button xpath
	@FindBy(how = How.XPATH, using = "//*[@id=\"permission-restriction\"]/h5/div/div[2]/div/button[1]")
	public WebElement cancelBtn_XPath;

	
	
	public AddNewPermRestrictionsPage(WebDriver wi){

        this.wi = wi;

        //This initElements method will create all WebElements

        PageFactory.initElements(wi, this);
        
    }   
	
		
}
