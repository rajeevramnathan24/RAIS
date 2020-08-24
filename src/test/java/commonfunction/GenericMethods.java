package commonfunction;


import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class GenericMethods  {

	public static boolean elementClickHighlight = true;
	public static boolean elementAssert;


	//Method for login into application
	public static void loginApplication(WebDriver wdl,String idxPath, String uidText, String pwdXpath, String pwdText, String loginBtnxPath) {

		try {
			//Passing user id values
			//wdl.findElement(By.xpath(idxPath)).sendKeys(uidText);

			sendText(wdl, idxPath, uidText);

			sendText(wdl, pwdXpath, pwdText);

			elementClick(wdl, loginBtnxPath);

			//Adding implicit wait
			wdl.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

			//Entering password
			//wdl.findElement(By.xpath(pwdXpath)).sendKeys(pwdText);

			//Click Login/ submit button
			//wdl.findElement(By.xpath(loginBtnxPath)).click();

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();

		}  

	}

	//Method to wait for element to be loaded
	public static void waitforElement(WebDriver wdprs, String waitElement) {

		try {

			//Initializing explicit wait
			WebDriverWait wait = new WebDriverWait(wdprs, 20);

			//Wait for the visibility of element
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(waitElement)));			

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method to wait for presence of element to be loaded
	public static void waitforPresenceOfElement(WebDriver wdprs, String presenceElement) {

		try {

			//Initializing explicit wait
			WebDriverWait wait = new WebDriverWait(wdprs, 20);

			//Wait for the visibility of element
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(presenceElement)));

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method to refresh to overcome staleness
	public static void waitforElementRefresh(WebDriver wdRef, String refreshElement) {

		try {

			//Initializing explicit wait
			WebDriverWait wait = new WebDriverWait(wdRef, 20);

			//Wait for the visibility of element
			wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(wdRef.findElement(By.xpath(refreshElement)))));

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method to wait for element to be clickable
	public static void elementClickable(WebDriver wdc, String elementClickable) {

		try {

			//Initialising explicit wait
			WebDriverWait wait = new WebDriverWait(wdc, 20);

			//Wait for the element clickable
			wait.until(ExpectedConditions.elementToBeClickable(wdc.findElement(By.xpath(elementClickable))));

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  

	}

	//Method to wait for element to become invisible
	public static void elementInvisible(WebDriver wein, String elementInvisible) {

		try {

			//Initialising explicit wait
			WebDriverWait wait = new WebDriverWait(wein, 20);

			//Wait for the element clickable
			wait.until(ExpectedConditions.invisibilityOf(wein.findElement(By.xpath(elementInvisible))));

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  

	}

	//Method to wait for element to be visible
	public static void waitforvisibilityOfElement(WebDriver wdv, String visibleElement) {

		try {

			//Initializing explicit wait
			WebDriverWait wait = new WebDriverWait(wdv, 20);

			//Wait for the visibility of element
			wait.until(ExpectedConditions.visibilityOf(wdv.findElement(By.xpath(visibleElement))));		

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}


	//Method to send text
	public static void sendText(WebDriver wc, String clickElement, Object valueToSend) {

		try {

			//Clicking on element
			wc.findElement(By.xpath(clickElement)).sendKeys(valueToSend.toString());

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		} 
	}

	//Method to send text via id
	public static void sendText_removeblank(WebDriver wc, String clickElement, Object valueToSend) {

		try {

			//Clicking on element*******************************
			//				WebElement wid = wc.findElement(By.id(clickElement));
			//				wid.click();
			//				wid.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), Keys.DELETE);
			//				wid.sendKeys(valueToSend.toString());

			//Clicking on element*******************************

			wc.findElement(By.xpath(clickElement)).click();
			wc.findElement(By.xpath(clickElement)).sendKeys(valueToSend.toString());
			wc.findElement(By.xpath(clickElement)).click();


		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		} 
	}

	//Method to click on any element, button etc
	public static String getTextValue_donotUse(WebDriver wgt, String textOfElement) {

		try {
			String getTextValue=null;
			//Clicking on element
			wgt.findElement(By.xpath(textOfElement)).getText();

			//return string value
			return getTextValue;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;

		}	catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	//Method to click on any element, button etc
	public static void elementClick(WebDriver wc, String clickElement) {

		try {

			//below is used to highlight only when flag is true
			highLightElement(wc, clickElement, elementClickHighlight);		

			//Adding implicit wait
			wc.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

			//Clicking on element
			wc.findElement(By.xpath(clickElement)).click();

			//Adding implicit wait
			wc.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void tabfromElement(WebDriver wtab, String tabFromElement) {

		//Clicking on element
		wtab.findElement(By.xpath(tabFromElement)).sendKeys(Keys.TAB);
	}

	//Method to to check if element or button etc is visibled
	public static void elementVisible(WebDriver wv, String visibleElement) {

		try {

			//Initialising explicit wait
			WebDriverWait wait = new WebDriverWait(wv, 20);

			//Wait for the element clickable
			wait.until(ExpectedConditions.visibilityOf(wv.findElement(By.xpath(visibleElement))));

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method to clear any element, button etc
	public static void clearElement(WebDriver wc, String clickElement) {

		try {

			//Clicking on element
			wc.findElement(By.xpath(clickElement)).clear();
			wc.findElement(By.xpath(clickElement)).sendKeys("");

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		} 
	}

	//Method to verify if element is enabled
	public static boolean elementEnabled(WebDriver wen, String elementEnabled) {

		//Initialising isEnabled boolean value to false
		boolean isEnabled = false;
		try {

			//below is used to highlight only when flag is true
			highLightElement(wen, elementEnabled, elementClickHighlight);
			
			//Verify if element is enabled
			if(wen.findElement(By.xpath(elementEnabled)).isEnabled()) {

				//Setting the enabled to true
				isEnabled=true;					
			}

			//Return boolean value
			return isEnabled;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return isEnabled;

		}	catch (Exception e) {
			e.printStackTrace();
			return isEnabled;
		} 
	}

	public static boolean objProp(String objType, WebDriver wObj, String objXpath, OptionalInt maxLength, java.util.Optional<String> objClickable) {

		Boolean objectPropertyFLag = false;

		switch(objType) {

		case "Text":

			if (wObj.findElement(By.xpath(objXpath)).isEnabled()
					&& wObj.findElement(By.xpath(objXpath)).isDisplayed())

				//&& maxLength == Integer.valueOf(wObj.findElement(By.xpath(objXpath)).getAttribute("maxlength")))
			{

				//setting return flag as true
				objectPropertyFLag = true;
			}

		case "Label":

			if (wObj.findElement(By.xpath(objXpath)).isEnabled()
					&& wObj.findElement(By.xpath(objXpath)).isDisplayed())	{

				//setting return flag as true
				objectPropertyFLag = true;
			}

		case "Link":

			if (wObj.findElement(By.xpath(objXpath)).isEnabled()
					&& wObj.findElement(By.xpath(objXpath)).isDisplayed())	{

				//setting return flag as true
				objectPropertyFLag = true;
			}


		}

		//Return flag value
		return objectPropertyFLag;

	}

	//Method reads the text value of element and returns text
	public static String getActualTxt(WebDriver wa, String actualXpath) {

		//Initialising string to be returned
		String ActualText=null;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wa, actualXpath, elementClickHighlight);

			//retrieving the text value of the element
			ActualText = wa.findElement(By.xpath(actualXpath)).getText();

			//Return the value of string
			return ActualText;


		} catch (NoSuchElementException e) {

			e.printStackTrace();

			//return null if error
			return null;

		}	catch (Exception e) {
			e.printStackTrace();

			//return null if error
			return null;
		}  		
	}

	//Highlighting element for demos
	public static void highLightElement(WebDriver wHighlight, String elementToHiglight, boolean highlight){

		try {
			//below is used to highlight only when flag is true
			if (highlight == true) {

				//waiting for user experience
				Thread.sleep(300);

				//highlighting element
				JavascriptExecutor js = (JavascriptExecutor) wHighlight;
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
						wHighlight.findElement(By.xpath(elementToHiglight)));

				//setting value as false
				highlight = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String verifyLabel_ButtonProperty(WebDriver wlbBtn, String labelBtnXpath, String LabelBtnText ) {

		//Initialising return flag as false
		String labelButtonText = null ;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wlbBtn, labelBtnXpath, elementClickHighlight);

			//Verify all properties of label or button
			if (wlbBtn.findElement(By.xpath(labelBtnXpath)).isEnabled()
					&& wlbBtn.findElement(By.xpath(labelBtnXpath)).isDisplayed()
					&& LabelBtnText.equalsIgnoreCase(wlbBtn.findElement(By.xpath(labelBtnXpath)).getText())) {

				//setting return flag as true
				labelButtonText = LabelBtnText;
			}
			//return property value
			return labelButtonText;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return labelButtonText;

		}	catch (Exception e) {
			e.printStackTrace();
			return labelButtonText;
		} 
	}

	//Pageload wait time
	public static void pageLoadWait(int loadTime) {

		try {
			try {
				Thread.sleep(loadTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
	}

	public static boolean verifyLabelBtnObjProperty(WebDriver wrlbBtn, String labelBtnXpath ) {

		//Initialising return flag as false
		Boolean objProperty = false;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wrlbBtn, labelBtnXpath, elementClickHighlight);

			//Verify all properties of label or button
			if (wrlbBtn.findElement(By.xpath(labelBtnXpath)).isEnabled()
					&& wrlbBtn.findElement(By.xpath(labelBtnXpath)).isDisplayed()) {

				//setting return flag as true
				objProperty = true;
			}
			//return property value
			return objProperty;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return objProperty;

		}	catch (Exception e) {
			e.printStackTrace();
			return objProperty;
		} 
	}

	public static String verifyTextBoxProperty(WebDriver wTxt, String txtBoxXpath, int maxLengthOfTxtBox, String verifiedtxtboxProperty ) {

		//Initialising return flag as false
		String textBoxProperty = null;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wTxt, txtBoxXpath, elementClickHighlight);

			//Verify all properties of label or button
			if (wTxt.findElement(By.xpath(txtBoxXpath)).isEnabled()
					&& wTxt.findElement(By.xpath(txtBoxXpath)).isDisplayed() 

					//commenting this temporarily
					//&& maxLengthOfTxtBox == Integer.valueOf(wTxt.findElement(By.xpath(txtBoxXpath)).getAttribute("maxlength"))
					) {

				//setting return flag as true
				textBoxProperty = verifiedtxtboxProperty;
			}
			//return property value
			return textBoxProperty;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return textBoxProperty;

		}	catch (Exception e) {
			e.printStackTrace();
			return textBoxProperty;
		} 
	}

	public static String verifycheckBoxProperty(WebDriver wlbBtnDisabled, String labelBtnXpathDisabled, String LabelBtnTextDisabled, Boolean checkBoxStatus ) {

		//Initialising return flag as false
		String disabledlabelButtonText = null ;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wlbBtnDisabled, labelBtnXpathDisabled, elementClickHighlight);

			//Verify all properties of label or button
			if (wlbBtnDisabled.findElement(By.xpath(labelBtnXpathDisabled)).isEnabled() == checkBoxStatus) {

				//setting return flag as true
				disabledlabelButtonText = LabelBtnTextDisabled;
			} else if (wlbBtnDisabled.findElement(By.xpath(labelBtnXpathDisabled)).isEnabled() == checkBoxStatus) {

				//setting return flag as true
				disabledlabelButtonText = LabelBtnTextDisabled;
			}							

			//return property value
			return disabledlabelButtonText;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return disabledlabelButtonText;

		}	catch (Exception e) {
			e.printStackTrace();
			return disabledlabelButtonText;
		} 
	}

	public static String verifySelectedRadioButtonProperty(WebDriver wrdBtn, String rdBtnXpath, String rdBtnLabelXpath, String rdBtnText, String verifiedRdoBtnProperty ) {

		//Initialising return flag as false
		String radioButtonProperty = null;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wrdBtn, rdBtnLabelXpath, elementClickHighlight);

			//Verify all properties of radio button
			if (wrdBtn.findElement(By.xpath(rdBtnXpath)).isSelected() &&
					wrdBtn.findElement(By.xpath(rdBtnXpath)).isEnabled()
					&& wrdBtn.findElement(By.xpath(rdBtnXpath)).isDisplayed()

					&& wrdBtn.findElement(By.xpath(rdBtnLabelXpath)).isDisplayed()
					&& rdBtnText.equalsIgnoreCase(wrdBtn.findElement(By.xpath(rdBtnLabelXpath)).getText())) {

				//setting return flag as true
				radioButtonProperty = verifiedRdoBtnProperty;
			} else if (wrdBtn.findElement(By.xpath(rdBtnXpath)).isSelected() == false &&
					wrdBtn.findElement(By.xpath(rdBtnXpath)).isEnabled()
					&& wrdBtn.findElement(By.xpath(rdBtnXpath)).isDisplayed()

					&& wrdBtn.findElement(By.xpath(rdBtnLabelXpath)).isDisplayed()
					&& rdBtnText.equalsIgnoreCase(wrdBtn.findElement(By.xpath(rdBtnLabelXpath)).getText())) {

				//setting return flag as true
				radioButtonProperty = verifiedRdoBtnProperty;
			}
			//return property value
			return radioButtonProperty;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return radioButtonProperty;

		}	catch (Exception e) {
			e.printStackTrace();
			return radioButtonProperty;
		} 
	}

	// Method to select roles/ functions from left navigation pane
	public static boolean menuName(WebDriver wdmenu, String menuItem, String menuText) {	

		//initialising menuItem
		boolean menuPresent = false;

		try {				

			//below is used to highlight only when flag is true
			//highLightElement(wdmenu, menuItem, elementClickHighlight);

			//Selecting Left role pane xpath and clicking on particular selected role
			WebElement mainleftpaneRoleXpath = wdmenu.findElement(By.xpath(menuItem));

			//Creating list of webelements returned from left pane				
			List<WebElement> menuList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@class='nav-item ']"));

			//Iterating the role list webelement until match is found
			for (WebElement specificMenu : menuList) {

				//locate specific role with text
				if (specificMenu.findElement(By.tagName("a")).getText().equalsIgnoreCase(menuText)) {

					//Clicking on specific role
					menuPresent = true;

				}
			} 
			//return true if present
			return menuPresent;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return menuPresent;

		}	catch (Exception e) {
			e.printStackTrace();
			return menuPresent;
		}  
	}


	//current time
	public static String currentLocalTime() {
		try {
			//current time
			DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmmss");
			LocalTime rawFormat = LocalTime.now();
			String currentTime =rawFormat.format(time);

			//return current time
			return currentTime;

		} catch (NoSuchElementException e) {

			e.printStackTrace();

			//return null if error
			return null;

		}	catch (Exception e) {
			e.printStackTrace();

			//return null if error
			return null;
		}  	
	}

	//Method reads the content of textbox and return it
	public static String getTextBoxContent(WebDriver wtx, String txtXpath) {

		//Initialising string to be returned
		String ActualTextcontent=null;

		try {

			//below is used to highlight only when flag is true
			highLightElement(wtx, txtXpath, elementClickHighlight);

			//retrieving the text value of the element
			ActualTextcontent = wtx.findElement(By.xpath(txtXpath)).getAttribute("value");

			//Return the value of string
			return ActualTextcontent;


		} catch (NoSuchElementException e) {

			e.printStackTrace();

			//return null if error
			return null;

		}	catch (Exception e) {
			e.printStackTrace();

			//return null if error
			return null;
		}  		
	}

	//Javascript PageWait condition
	public static void JSPageWait(WebDriver wb) {
		
		//Initializing JS wait
		//WebDriverWait wait = new WebDriverWait(wb, 30);

		//Wait for the visibility of element
//		wait.until((ExpectedCondition<Boolean>) JDriver ->
//		((JavascriptExecutor) JDriver).executeScript("return window.document.hasHomeMounted").equals("complete"));		
		
		//Page wait
		pageLoadWait(1000);
		
		//starting java script page wait
		
		WebDriverWait wait = new WebDriverWait(wb, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) wb;
		String readyState = js.executeScript("return document.readyState").toString();
//		System.out.println(readyState);
				
		wait.until((ExpectedCondition<Boolean>) wd ->
		   ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		
        //wait.Until(ExpectedConditions.jsReturnsValue("return window.document.hasHomeMounted").equals("complete"));
		
		//For java application
//		((JavascriptExecutor) JDriver).executeScript("return document.readyState").equals("complete"));	

		//					or use the below code
		//					wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));	

	}

	//Using Predicate JS
	public static void PredicateJS(WebDriver wp) {
		Predicate<WebDriver> pageLoaded = wd -> ((JavascriptExecutor) wd).executeScript(
		        "return document.readyState").equals("complete");
//		new FluentWait<WebDriver>(wp).until(pageLoaded);
		
	}
	
	//FluentWait
	public static void fluentWaitPageLoad(WebDriver wft) {
		
//		Wait<WebDriver> wait = new FluentWait<WebDriver>(wft)
//				.withTimeout(Duration.ofSeconds(30))
//				.pollingEvery(Duration.ofSeconds(5))
//				.ignoring(Exception.class);
//		WebElement clickseleniumlink = wait.until(new Function<WebDriver, WebElement>(){
//		
//			public WebElement apply(WebDriver driver ) {
//				return driver.findElement(By.xpath("Test"));
//			}
//		});
	}
	
	//Javascript element click
	//Method to click on any element, button etc
		public static void JClickonElement(WebDriver wj, String clickElement) {

			try {

				//below is used to highlight only when flag is true
				highLightElement(wj, clickElement, elementClickHighlight);		

				//Adding implicit wait
				wj.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

				//Java script click on element
				JavascriptExecutor executor = (JavascriptExecutor) wj;
			     executor.executeScript("arguments[0].click();", wj.findElement(By.xpath(clickElement)));

				//Adding implicit wait
				wj.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

			} catch (NoSuchElementException e) {
				e.printStackTrace();

			}	catch (Exception e) {
				e.printStackTrace();
			} 
		}

	
}
