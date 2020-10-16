package commonfunction;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewAttributePage;
import pageLocators_Elements.RAIS.AddNewEntityFormDetailsPage;
import pageLocators_Elements.RAIS.AddNewEntityPage;
import pageLocators_Elements.RAIS.AddNewPermRestrictionsPage;
import pageLocators_Elements.RAIS.AddNewRolePage;
import pageLocators_Elements.RAIS.AddNewSecurityProfilePage;
import pageLocators_Elements.RAIS.AddNewUserDetailsPage;
import pageLocators_Elements.RAIS.DashboardPage;
import pageLocators_Elements.RAIS.DataRoles_FunctionalRolesPage;
import pageLocators_Elements.RAIS.EntityFormListingPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.LoginPage;
import pageLocators_Elements.RAIS.SecurityProfilePage;
import pageLocators_Elements.RAIS.UserListPage;
import pageLocators_Elements.RAIS.Workflow_AuthorizationPage;
import pageLocators_Elements.RAIS.Workflow_FAPage;
import pageLocators_Elements.RAIS.Workflow_Inspection_Parent_ChildPage;
import pageLocators_Elements.RAIS.Workflow_PaymentPage;
import testcases.RAIS.WF_FollowupAction_Tests;


public class RAIS_applicationSpecificMethods  {

	// Method to select roles/ functions from left navigation pane
	public static void roleSelect_Click(WebDriver wdi, String roleCategory, String roleName) {	

		try {
			//Selecting Left role pane xpath and clicking on particular selected role
			WebElement mainleftpaneRoleXpath = wdi
					.findElement(By.xpath("//*[@id=\'" + roleCategory + "']/div/div[1]/div/div"));
			//.findElement(By.xpath("//*[@id=\'" + roleCategory + "']/div/div[1]/div/div"));
			//WebElement mainleftpaneRoleXpath = wd.findElement(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div"));

			//Creating list of webelements returned from left pane				
			List<WebElement> rolesList = mainleftpaneRoleXpath
					.findElements(By.xpath("//*[@id=\'" + roleCategory + "']/div/div[1]/div/div/div[2]/ul/li"));
			//List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div/div[2]/ul/li"));

			//Iterating the role list webelement until match is found
			for (WebElement specificRoleName : rolesList) {

				//locate specific role with text
				if (specificRoleName.findElement(By.tagName("a")).getText().equals(roleName))

					//Clicking on specific role
					specificRoleName.click();
			} 

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method to select specific permission or restriction from table
	public static void perm_restrict_Select_Click(WebDriver wdpr, String tableType, String cellTextValue) {

		//flag is used to exit loop
		boolean exitLoop=true;

		//assigning table locators in webelement
		WebElement table = wdpr.findElement(By.xpath((tableType)));

		//assigning row elements to webelement
		List<WebElement> allrows = table.findElements(By.tagName("tr"));

		//looping between each row
		for(WebElement row: allrows){

			//check if flag is true from previous iterations
			if(exitLoop == true) {
				List<WebElement> Cells = row.findElements(By.tagName("td"));


				//looping for every element in the row
				for(WebElement Cell:Cells){

					//check if flag is true first and then check for corresponding text value
					if ((exitLoop ==true) && Cell.getText().equals(cellTextValue) ) {

						//click on the particular cell value
						Cell.click();

						//setting flag to false to exit if and for loop
						exitLoop = false;	            	
					}	

				}


			}
		}

	}

	//Method to select no permission granted text table
	public static String perm_restrict_Table_NotGranted_Text(WebDriver wdtxt, String tableType, String permrestNotGranted) {


		//flag is used to exit loop
		String textValue=null;


		try {
			//assigning table locators in webelement
			WebElement table = wdtxt.findElement(By.xpath((tableType)));
			//assigning row elements to webelement
			List<WebElement> allrows = table.findElements(By.tagName("tr"));
			//looping between each row
			for (WebElement row : allrows) {
				List<WebElement> Cells = row.findElements(By.tagName("td"));

				//looping for every element in the row
				for (WebElement Cell : Cells) {

					//check if flag is true first and then check for corresponding text value
					if (Cell.getText().equalsIgnoreCase(permrestNotGranted)) {

						//click on the particular cell value
						textValue = Cell.getText();
					}
				}

			}
			return textValue;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return textValue;

		}	catch (Exception e) {
			e.printStackTrace();
			return textValue;
		} 

	}

	public static void tableClick_Test(WebDriver wdpr, String tableType, String cellTextValue ) {	



		WebElement tableBody = wdpr.findElement(By.xpath(tableType)); //.findElement(By.tagName("tbody"));
		List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			//List<WebElement> td = row.findElements(By.tagName("td"));
			if (row.findElement(By.tagName("title")).getText().equals(cellTextValue)) {

				//	        	if (td.size() > 0
				//		                && td.get(intCellToFind).getText().equals(cellTextValue)) {
				row.click();
			}
		}
		//return null;

	}

	//selecting value from list
	public static void valueSelectfromDropDown(WebDriver wSelect, String listName, Object valueToSelect ) {

		//pass the webelement of the list
		Select dropdownToSelect = new Select(wSelect.findElement(By.xpath(listName))); 

		//select the specified text
		dropdownToSelect.selectByVisibleText(String.valueOf(valueToSelect));
	}

	//Logout method
	public static void logoutUser(WebDriver wLgout, String loggedinUserXpath, String logoutLink) {

		//page refresh
		//wLgout.navigate().refresh();

		GenericMethods.pageLoadWait(1000);
		//Waiting until element is visible to be clicked
		GenericMethods.waitforElement(wLgout, loggedinUserXpath);
		GenericMethods.elementClickable(wLgout, loggedinUserXpath);
		GenericMethods.elementClick(wLgout, loggedinUserXpath);

		GenericMethods.pageLoadWait(1000);

		//Waiting until element is visible to be clicked
		GenericMethods.waitforElement(wLgout, logoutLink);
		GenericMethods.elementClickable(wLgout, logoutLink);
		//GenericMethods.waitforvisibilityOfElement(wLgout, logoutLink);
		GenericMethods.elementClick(wLgout, logoutLink);

		GenericMethods.pageLoadWait(500);

		//page refresh
		wLgout.navigate().refresh();
	}

	//clicking on table filter and enter particular text
	public static void columnHeaderFilter(WebDriver wFlt, String colHeadfilterXpath, String filterTextXpath,String filterText) {

		GenericMethods.elementClick(wFlt, colHeadfilterXpath);

		GenericMethods.sendText(wFlt, filterTextXpath, filterText);

	}

	//DynamicColum header
	public static void DynamicGridFilter(WebDriver wFlt, 
			String PrefixcolHeadfilterXpath,int position,String SuffixcolHeadfilterXpath1,String ColName, String SuffixcolHeadfilterXpath2, 
			String filterTextXpath,String filterText) {

		String GridColHeader = PrefixcolHeadfilterXpath +position+SuffixcolHeadfilterXpath1+ColName+SuffixcolHeadfilterXpath2;
		String GridTextInput = PrefixcolHeadfilterXpath +position+filterTextXpath;

		GenericMethods.elementClick(wFlt, GridColHeader);

		GenericMethods.sendText(wFlt, GridTextInput, filterText);

	}

	//Dynamic column header and click 21Jun
	public static void EntityRecordGridFilter_Click(WebDriver wGrid,int position,String filterText) {

		try {
			//initialisting entity form listing page
			EntityFormListingPage entFrmListPage = new EntityFormListingPage();

			//Fetch col header name w.r.t position into the string
			String ColName = entFrmListPage.Dyamic_GridTable_Prefix_Xpath + position +entFrmListPage.Dynamic_colHeaderName_Text_Suffix2_Xpath;

			//Get text name of the header
			ColName = GenericMethods.getActualTxt(wGrid, ColName);

			//concatenate prefix, suffix, col header name, position to form xpath
			String GridColHeader = entFrmListPage.Dyamic_GridTable_Prefix_Xpath +position+entFrmListPage.Dynamic_GridTable_Suffix1_Xpath
					+ColName+entFrmListPage.Dynamic_GridTable_Suffix2_Xpath;

			//create dynamic dynamic xpath of text field
			String GridTextInput_xpath = entFrmListPage.Dyamic_GridTable_Prefix_Xpath +position+entFrmListPage.Dynamic_GridTable_TxtInput_Suffix_Xpath;

			//wait for element to load
			GenericMethods.waitforElement(wGrid, GridColHeader);

			//clicking on filter icon on the grid
			GenericMethods.elementClick(wGrid, GridColHeader);

			//wait for element to load
			GenericMethods.waitforElement(wGrid, GridTextInput_xpath);

			//input text value to search
			GenericMethods.sendText(wGrid, GridTextInput_xpath, filterText);

			//wait for page load
			GenericMethods.JSPageWait(wGrid);			

			//Clicking on specific Role created
			perm_restrict_Select_Click(wGrid,entFrmListPage.securityProfileTableList_XPath , filterText);

			//wait for page load
			GenericMethods.JSPageWait(wGrid);
		} catch (Exception e) {

			e.printStackTrace();
		}	

	}


	public static String dropDownCompareWithArray(WebDriver wSelect,String listName, String [] stringArray, String expectedString, String failedValue ) {

		//Initialising flag
		String dropDownValueMatchFlag = null;

		try {
			Select select = new Select(wSelect.findElement(By.xpath(listName)));
			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < stringArray.length; i++) {
					if (we.getText().equals(stringArray[i])) {
						dropDownValueMatchFlag = expectedString;
					} else {
						dropDownValueMatchFlag = failedValue;
					}
				}
			}
			//return flag
			return dropDownValueMatchFlag;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return dropDownValueMatchFlag;

		}	catch (Exception e) {
			e.printStackTrace();
			return dropDownValueMatchFlag;
		} 

	}

	//clicking on table filter and enter particular text
	public static void attributeDataInput(WebDriver watb, String typeOfAttribute) {

		//Initialising Attribute page name below
		AddNewAttributePage addAttbtInput = new AddNewAttributePage();

		//selecting datatype from dropdown as per value passed
		valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_dataTypedropDown_XPath, typeOfAttribute);

		switch (typeOfAttribute) {
		case "Checkbox":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.chkBox_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.chkBox_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.chkBox_label);


			break;

		case "Numeric":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.numeric_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.numeric_toolTip);

			//input label name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.numeric_label);

			//subtype input
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_subTypedropDown_XPath, addAttbtInput.subTypeList[0]);


			break;

		case "Text":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.text_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.text_toolTip);

			//input label name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.text_label);

			//input number of lines 
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_noOfLinesTxtBox_XPath, RaisTestData.text_lines);

			//subtype input
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_lengthTxtBox_XPath, RaisTestData.text_length);


			break;

		case "Date":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.date_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.date_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.date_label);



			break;

		case "Guid":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.guid_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.guid_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.guid_label);




			break;

		case "Image":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.image_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.image_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.image_label);


			break;

		case "Lookup":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.lookUp_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.lookUp_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.lookUp_label);

			//input linked entity
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_linkedEntitydropDown_XPath, RaisTestData.facility_LinkedEntity);




			break;

		case "Lookup with Value":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.lookUpValue_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.lookUpValue_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.lookUpValue_label);

			//input linked entity
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_linkedEntitydropDown_XPath, RaisTestData.year_LinkedEntity);

			//input subtype
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_subTypedropDown_XPath, addAttbtInput.subTypeList[0]);

			//length input
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_lengthTxtBox_XPath, RaisTestData.text_length);




			break;

		case "Memo":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.memo_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.memo_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.memo_label);




			break;

		case "Multi lookup":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.multiLookUp_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.multiLookUp_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.multiLookUp_label);

			//input linked entity
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_linkedEntitydropDown_XPath, RaisTestData.branch_LinkedEntity);




			break;

		case "Multiple documents":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.multiDocs_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.multiDocs_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.multiDocs_label);



			break;

		case "Multiple Users":



			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.multiUsers_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.multiUsers_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.multiUsers_label);




			break;

		case "RAN":

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.ran_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.ran_label);



			break;

		case "Single document":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.singDocs_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.singDocs_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.singDocs_label);




			break;

		case "Time Interval":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.timeInt_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.timeInt_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.timeInt_label);




			break;

		case "User":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.user_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.user_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.user_label);



			break;

		case "Workflow Action":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.wrkFlowAct_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.wrkFlowAct_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.wrkFlowAct_label);




			break;

		case "Select":


			break;

		case "Date Time":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.dateTime_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.dateTime_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.dateTime_label);

			break;

		case "Checkbox List":

			//input internal name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.chkBoxList_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.chkBoxList_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, addAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.chkBoxList_label);

			//input linked entity
			valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_linkedEntitydropDown_XPath, RaisTestData.facility_LinkedEntity);


			break;

		default:
			break;
		}	

		//mandatory input
		valueSelectfromDropDown(watb, addAttbtInput.addNewAttB_mandInputdropDown_XPath, addAttbtInput.mandInputList[0]);

		//click on show in listview
		GenericMethods.elementClick(watb, addAttbtInput.addNewAttB_showinList_CheckBox_XPath);

		//click on show in history
		GenericMethods.elementClick(watb, addAttbtInput.addNewAttB_showHist_CheckBox_XPath);


		//Waiting until element to load
		GenericMethods.waitforElement(watb, addAttbtInput.SaveBtn_XPath);	
		GenericMethods.elementClickable(watb, addAttbtInput.SaveBtn_XPath);
		GenericMethods.waitforvisibilityOfElement(watb, addAttbtInput.SaveBtn_XPath);

		//wait for page load
		GenericMethods.pageLoadWait(500);
		GenericMethods.elementClick(watb, addAttbtInput.SaveBtn_XPath);
		GenericMethods.pageLoadWait(300);

	}

	//select one value from multiple selection list
	public static void multiSelectList(WebDriver wml, Object dropDownXpath, Object valueTobeSelected) {

		//wait for page load
		GenericMethods.JSPageWait(wml);

		//select value from multiselect
		//GenericMethods.waitforElement(wd, AddNewPermRestrct.restriction_DropdnClick_Xpath);	
		//GenericMethods.elementClickable(wml, String.valueOf(dropDownXpath));
		GenericMethods.JClickonElement(wml, String.valueOf(dropDownXpath));

		//wait for page load
		GenericMethods.JSPageWait(wml);

		//pass the value to be sent
		//GenericMethods.elementClickable(wml, String.valueOf(valueTobeSelected));
		GenericMethods.JClickonElement(wml, String.valueOf(valueTobeSelected));

		//wait for page load
		GenericMethods.JSPageWait(wml);

		//collapsing the multiselect drop down
		GenericMethods.JClickonElement(wml, String.valueOf(dropDownXpath));

		//wait for page load
		GenericMethods.JSPageWait(wml);

	}

	//Attributewise validation messages and label verification
	public static String verifyAttribute_ValidationMsgs_Fields(WebDriver wval, String typeOfAttribute) {

		//Initialising Attribute page name below
		AddNewAttributePage addAttbtInput = new AddNewAttributePage();

		//validation msg and field verified string
		String label_ValMsgsStatus = RaisTestData.lblValmessagesNOTVerified;

		try {
			//verifying common labels/ texts
			//verifying header label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addAttb_Hdr_XPath,
					addAttbtInput.ADDNEW_ATTB_HDR_LABEL_Txt), addAttbtInput.ADDNEW_ATTB_HDR_LABEL_Txt);

			//verifying label
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_intnameLabel_XPath,
							addAttbtInput.ADDNEW_ATTB_INTNAME_LABEL_Txt),
					addAttbtInput.ADDNEW_ATTB_INTNAME_LABEL_Txt);

			//verifying datatype drop down on RIAS Page
			Assert.assertEquals(
					dropDownCompareWithArray(wval,
							addAttbtInput.addNewAttB_dataTypedropDown_XPath, addAttbtInput.attributeValueList,
							RaisTestData.dropDownValuesVerified, RaisTestData.dropDownValuesNOTVerified),
					RaisTestData.dropDownValuesVerified);

			//verify mandatory drop down
			Assert.assertEquals(
					dropDownCompareWithArray(wval,
							addAttbtInput.addNewAttB_mandInputdropDown_XPath, addAttbtInput.mandInputList,
							RaisTestData.dropDownValuesVerified, RaisTestData.dropDownValuesNOTVerified),
					RaisTestData.dropDownValuesVerified);

			//for internal name verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.nameRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_INTNAME_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_INTNAME_REQD_TXT);

			//for label verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.labelRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_LABEL_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_LABEL_REQD_TXT);

			//for Mandatory input verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.mandInputRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_MANDINPUT_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_MANDINPUT_REQD_TXT);

			//verify internal name text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wval,
					addAttbtInput.addNewAttB_internalNameTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify tool tip text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wval,
					addAttbtInput.addNewAttB_tooTipTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
					RaisTestData.verifiedTextBoxProperty);

			//verify label text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wval,
					addAttbtInput.addNewAttB_labelTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
					RaisTestData.verifiedTextBoxProperty);

			// verifying common properties of checkboxes of Unique value Label names ****************1
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_unqVal_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt),
					addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt);	

			//read only check box and label text****************2
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wval,
					addAttbtInput.addNewAttB_readOnly_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_readOnly_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_READONLY_Txt),
					addAttbtInput.ADDNEW_ATTB_READONLY_Txt);

			// verifying common properties of checkboxes of searchable Label names ****************3
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_searchable_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SEARCH_Txt),
					addAttbtInput.ADDNEW_ATTB_SEARCH_Txt);

			//For showinlist check boxes and label text****************4
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wval,
					addAttbtInput.addNewAttB_showinList_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt,true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_showinList_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SHOWINLIST_Txt),
					addAttbtInput.ADDNEW_ATTB_SHOWINLIST_Txt);


			//For showinHistory check boxes and label text****************5
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wval,
					addAttbtInput.addNewAttB_showHist_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_showHist_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SHOWINHIST_Txt),
					addAttbtInput.ADDNEW_ATTB_SHOWINHIST_Txt);


			//for Cancel & Save button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.cancelBtn_XPath,
					addAttbtInput.CANCEL_BTN_Txt), addAttbtInput.CANCEL_BTN_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.SaveBtn_XPath,
					addAttbtInput.SAVE_BTN_Txt), addAttbtInput.SAVE_BTN_Txt);

			//Below verification is only for checkboxDate,GUID,Image,Memo,Multiple Users,Time Interval,	WorkFlow Action
			if(typeOfAttribute == addAttbtInput.attributeValueList[0] ||typeOfAttribute == addAttbtInput.attributeValueList[1]||
					typeOfAttribute == addAttbtInput.attributeValueList[2] ||typeOfAttribute == addAttbtInput.attributeValueList[4]||
					typeOfAttribute == addAttbtInput.attributeValueList[6] ||typeOfAttribute == addAttbtInput.attributeValueList[9]||
					typeOfAttribute == addAttbtInput.attributeValueList[14] ||typeOfAttribute == addAttbtInput.attributeValueList[16]) {

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_unqVal_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt),
						addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt);								

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_searchable_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_SEARCH_Txt),
						addAttbtInput.ADDNEW_ATTB_SEARCH_Txt);					

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				//Below condition is for RAN
			} else if (typeOfAttribute == addAttbtInput.attributeValueList[11]){

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_unqVal_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt),
						addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt);								

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_searchable_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_SEARCH_Txt),
						addAttbtInput.ADDNEW_ATTB_SEARCH_Txt);					

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

			}

			switch (typeOfAttribute) {
			case "noValueSelected":					

				//for datatype verify validation messages
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.dataTypeRequired_MsgXPath,
								addAttbtInput.ADDNEW_ATTB_DATATYPE_REQD_TXT),
						addAttbtInput.ADDNEW_ATTB_DATATYPE_REQD_TXT);					

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);


				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_searchable_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_SEARCH_Txt),
						addAttbtInput.ADDNEW_ATTB_SEARCH_Txt);					

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				break;

			case "User":

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);

				//Enter code for checkbox selected or not

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);									

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				break;

			case "Lookup":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_linkEntity_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.linkedEntityRequired_MsgXPath,
								addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);

				//Enter code for checkbox selected or not

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);									

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				break;

			case "Lookup with Value":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_linkEntity_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.linkedEntityRequired_MsgXPath,
								addAttbtInput.ADDNEW_ATTB_LINKEDENTY_REQD_TXT),
						addAttbtInput.ADDNEW_ATTB_LINKEDENTY_REQD_TXT);	

				// verifying sub type label for lookup with value type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_subType_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_SUBTYPE_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_SUBTYPE_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.subTypeRequired_MsgXPath,
								addAttbtInput.ADDNEW_ATTB_SUBTYPE_REQD_TXT),
						addAttbtInput.ADDNEW_ATTB_SUBTYPE_REQD_TXT);	

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);

				//Enter code for checkbox selected or not

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
						addAttbtInput.ADDNEW_ATTB_ENB_Txt);									

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				break;

			case "Multi lookup":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.addNewAttB_linkEntity_Label_XPath,
								addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wval, addAttbtInput.linkedEntityRequired_MsgXPath,
								addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtInput.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				//For unique value check boxes and label text ****************1
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval, addAttbtInput.addNewAttB_unqVal_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);

				//Enter code for checkbox selected or not

				//searchable check box and label text****************3
				Assert.assertEquals(
						GenericMethods.verifycheckBoxProperty(wval,
								addAttbtInput.addNewAttB_searchable_CheckBox_XPath,
								addAttbtInput.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbtInput.ADDNEW_ATTB_DISABLE_Txt);									

				//Setting the string value as verified
				label_ValMsgsStatus = RaisTestData.lblValmessagesVerified;

				break;

			default:
				break;

			}
			return label_ValMsgsStatus;
		} catch (AssertionError  ase) {
			ase.printStackTrace();

			//Return label and validation message status
			return label_ValMsgsStatus;

		}	catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

			//Return label and validation message status
			return label_ValMsgsStatus;
		}catch (Exception  e) {
			e.printStackTrace();

			//Return label and validation message status
			return label_ValMsgsStatus;
		}		
	}


	//Common label and validation messages on attriubte page - NEW Arch
	public static String verifyCommonLabels_ValidationMsgs(WebDriver wcm) {

		//Initialising Attribute page name below
		AddNewAttributePage addAttbtInput = new AddNewAttributePage();

		//validation msg and field verified string
		String commonlabel_ValMsgsStatus = RaisTestData.lblValmessagesNOTVerified;

		try {
			//verifying common labels/ texts
			//verifying header label
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addAttb_Hdr_XPath,
					addAttbtInput.ADDNEW_ATTB_HDR_LABEL_Txt), addAttbtInput.ADDNEW_ATTB_HDR_LABEL_Txt);

			//verifying label
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_intnameLabel_XPath,
							addAttbtInput.ADDNEW_ATTB_INTNAME_LABEL_Txt),
					addAttbtInput.ADDNEW_ATTB_INTNAME_LABEL_Txt);

			//verifying datatype drop down on RIAS Page
			Assert.assertEquals(
					dropDownCompareWithArray(wcm,
							addAttbtInput.addNewAttB_dataTypedropDown_XPath, addAttbtInput.attributeValueList,
							RaisTestData.dropDownValuesVerified, RaisTestData.dropDownValuesNOTVerified),
					RaisTestData.dropDownValuesVerified);

			//verify mandatory drop down
			Assert.assertEquals(
					dropDownCompareWithArray(wcm,
							addAttbtInput.addNewAttB_mandInputdropDown_XPath, addAttbtInput.mandInputList,
							RaisTestData.dropDownValuesVerified, RaisTestData.dropDownValuesNOTVerified),
					RaisTestData.dropDownValuesVerified);

			//for internal name verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.nameRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_INTNAME_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_INTNAME_REQD_TXT);

			//for label verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.labelRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_LABEL_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_LABEL_REQD_TXT);

			//for Mandatory input verify validation messages
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.mandInputRequired_MsgXPath,
							addAttbtInput.ADDNEW_ATTB_MANDINPUT_REQD_TXT),
					addAttbtInput.ADDNEW_ATTB_MANDINPUT_REQD_TXT);

			//verify internal name text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wcm,
					addAttbtInput.addNewAttB_internalNameTxtBox_XPath, 55,
					RaisTestData.verifiedTextBoxProperty), RaisTestData.verifiedTextBoxProperty);

			//verify tool tip text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wcm,
					addAttbtInput.addNewAttB_tooTipTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
					RaisTestData.verifiedTextBoxProperty);

			//verify label text box property
			Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wcm,
					addAttbtInput.addNewAttB_labelTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
					RaisTestData.verifiedTextBoxProperty);

			// verifying common properties of checkboxes of Unique value Label names ****************1
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_unqVal_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt),
					addAttbtInput.ADDNEW_ATTB_UNQVAL_Txt);	

			//read only check box and label text****************2
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wcm,
					addAttbtInput.addNewAttB_readOnly_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_readOnly_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_READONLY_Txt),
					addAttbtInput.ADDNEW_ATTB_READONLY_Txt);

			// verifying common properties of checkboxes of searchable Label names ****************3
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_searchable_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SEARCH_Txt),
					addAttbtInput.ADDNEW_ATTB_SEARCH_Txt);

			//For showinlist check boxes and label text****************4
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wcm,
					addAttbtInput.addNewAttB_showinList_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt,true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_showinList_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SHOWINLIST_Txt),
					addAttbtInput.ADDNEW_ATTB_SHOWINLIST_Txt);


			//For showinHistory check boxes and label text****************5
			Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wcm,
					addAttbtInput.addNewAttB_showHist_CheckBox_XPath, addAttbtInput.ADDNEW_ATTB_ENB_Txt, true),
					addAttbtInput.ADDNEW_ATTB_ENB_Txt);
			Assert.assertEquals(
					GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.addNewAttB_showHist_Label_XPath,
							addAttbtInput.ADDNEW_ATTB_SHOWINHIST_Txt),
					addAttbtInput.ADDNEW_ATTB_SHOWINHIST_Txt);


			//for Cancel & Save button
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.cancelBtn_XPath,
					addAttbtInput.CANCEL_BTN_Txt), addAttbtInput.CANCEL_BTN_Txt);
			Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wcm, addAttbtInput.SaveBtn_XPath,
					addAttbtInput.SAVE_BTN_Txt), addAttbtInput.SAVE_BTN_Txt);

			commonlabel_ValMsgsStatus = RaisTestData.lblValmessagesVerified;
			return commonlabel_ValMsgsStatus;


		} catch (AssertionError  ase) {
			ase.printStackTrace();

			//Return label and validation message status
			return commonlabel_ValMsgsStatus;

		}	catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

			//Return label and validation message status
			return commonlabel_ValMsgsStatus;
		}catch (Exception  e) {
			e.printStackTrace();

			//Return label and validation message status
			return commonlabel_ValMsgsStatus;
		}
	}


	//Switch cases for specific datatypes - NEW Arch
	public static String verifySpecificDataTypes_ValidationMsgs(WebDriver wspf, String typeOfAttribute) {

		//Initialising Attribute page name below
		AddNewAttributePage addAttbtspecific = new AddNewAttributePage();

		String specificfields = RaisTestData.lblValmessagesNOTVerified;

		try {
			switch (typeOfAttribute) {

			case "Lookup":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_linkEntity_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.linkedEntityRequired_MsgXPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Multi lookup":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_linkEntity_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.linkedEntityRequired_MsgXPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Numeric":

				// verifying sub type label for lookup with value type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_subType_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_SUBTYPE_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_SUBTYPE_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.subTypeRequired_MsgXPath,
								addAttbtspecific.ADDNEW_ATTB_SUBTYPE_REQD_TXT),
						addAttbtspecific.ADDNEW_ATTB_SUBTYPE_REQD_TXT);	

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Text":

				// verifying Length label for lookup with value type for label and text box property
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_noOfLines_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_NO_OF_LINES_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_NO_OF_LINES_LABEL_Txt);	

				//verify tool tip text box property
				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wspf,
						addAttbtspecific.addNewAttB_noOfLinesTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
						RaisTestData.verifiedTextBoxProperty);

				// verifying Length label for Text data type type for label and text box property
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_length_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_LENGTH_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LENGTH_LABEL_Txt);	

				//verify tool tip text box property
				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wspf,
						addAttbtspecific.addNewAttB_lengthTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
						RaisTestData.verifiedTextBoxProperty);

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Multiple documents":

				// verifying Length label for lookup with value type for label and text box property
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_tag_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_TAG_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_TAG_LABEL_Txt);	

				//verify tool tip text box property
				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wspf,
						addAttbtspecific.addNewAttB_tagTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
						RaisTestData.verifiedTextBoxProperty);

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Single document":

				// verifying Length label for lookup with value type for label and text box property
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_tag_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_TAG_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_TAG_LABEL_Txt);	

				//verify tool tip text box property
				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wspf,
						addAttbtspecific.addNewAttB_tagTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
						RaisTestData.verifiedTextBoxProperty);

				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			case "Lookup with Value":

				// verifying linked Entity label for lookup data type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_linkEntity_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.linkedEntityRequired_MsgXPath,
								addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LINKDENTITY_LABEL_Txt);	


				// verifying sub type label for lookup with value type for label and validation msg
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_subType_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_SUBTYPE_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_SUBTYPE_LABEL_Txt);	

				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.subTypeRequired_MsgXPath,
								addAttbtspecific.ADDNEW_ATTB_SUBTYPE_REQD_TXT),
						addAttbtspecific.ADDNEW_ATTB_SUBTYPE_REQD_TXT);

				// verifying Length label for lookup with value type for label and text box property
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wspf, addAttbtspecific.addNewAttB_length_Label_XPath,
								addAttbtspecific.ADDNEW_ATTB_LENGTH_LABEL_Txt),
						addAttbtspecific.ADDNEW_ATTB_LENGTH_LABEL_Txt);	

				//verify tool tip text box property
				Assert.assertEquals(GenericMethods.verifyTextBoxProperty(wspf,
						addAttbtspecific.addNewAttB_lengthTxtBox_XPath, 55, RaisTestData.verifiedTextBoxProperty),
						RaisTestData.verifiedTextBoxProperty);


				//Setting the string value as verified
				specificfields = RaisTestData.lblValmessagesVerified;

				break;

			default:
				break;
			}
			return specificfields;
		} catch (AssertionError  ase) {
			ase.printStackTrace();

			//Return label and validation message status
			return specificfields;

		}	catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

			//Return label and validation message status
			return specificfields;
		}catch (Exception  e) {
			e.printStackTrace();

			//Return label and validation message status
			return specificfields;
		}				
	}

	//method to verify specific checkboxes - NEW Arch
	public static String verifySpecificCheckBoxUnqValue(WebDriver wchkb, String chkBoxName ) {

		//Initialising Attribute page name below
		AddNewAttributePage addAttbChkBox = new AddNewAttributePage();

		String chkBoxStatus= addAttbChkBox.ADDNEW_ATTB_CHKBOX_NOT_VERIFIED_Txt;

		try {

			switch (chkBoxName) {
			case "Unique Values":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_Label_XPath, addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt),
						addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_unqVal_CheckBoxNOTSelected_XPath, RaisTestData.chkBoxNOTSelected),
						RaisTestData.chkBoxNOTSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;

			case "Searchable":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_Label_XPath, addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt),
						addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_searchable_CheckBoxNOTSelected_XPath, RaisTestData.chkBoxNOTSelected),
						RaisTestData.chkBoxNOTSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;

			case "RAN Unique CheckBox":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_Label_XPath, addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt),
						addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_searchable_CheckBoxSelected_XPath, RaisTestData.chkBoxSelected),
						RaisTestData.chkBoxSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;

			case "RAN Searchable CheckBox":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_Label_XPath, addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt),
						addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt, false),
						addAttbChkBox.ADDNEW_ATTB_DISABLE_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_searchable_CheckBoxSelected_XPath, RaisTestData.chkBoxSelected),
						RaisTestData.chkBoxSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;

			case "Users_Lookup Unique CheckBox":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_Label_XPath, addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt),
						addAttbChkBox.ADDNEW_ATTB_UNQVAL_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_unqVal_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_ENB_Txt, true),
						addAttbChkBox.ADDNEW_ATTB_ENB_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_searchable_CheckBoxNOTSelected_XPath, RaisTestData.chkBoxNOTSelected),
						RaisTestData.chkBoxNOTSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;

			case "Users_Lookup_Numeric Searchable CheckBox":

				// verifying common properties of checkboxes of Unique value Label names 
				Assert.assertEquals(GenericMethods.verifyLabel_ButtonProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_Label_XPath, addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt),
						addAttbChkBox.ADDNEW_ATTB_SEARCH_Txt);
				//For unique value check boxes property
				Assert.assertEquals(GenericMethods.verifycheckBoxProperty(wchkb,
						addAttbChkBox.addNewAttB_searchable_CheckBox_XPath, addAttbChkBox.ADDNEW_ATTB_ENB_Txt, true),
						addAttbChkBox.ADDNEW_ATTB_ENB_Txt);
				Assert.assertEquals(
						GenericMethods.verifyLabel_ButtonProperty(wchkb,
								addAttbChkBox.addNewAttB_searchable_CheckBoxNOTSelected_XPath, RaisTestData.chkBoxNOTSelected),
						RaisTestData.chkBoxNOTSelected);
				chkBoxStatus = addAttbChkBox.ADDNEW_ATTB_CHKBOX_VERIFIED_Txt;

				break;


			default:
				break;
			}

			return chkBoxStatus;


		} catch (AssertionError  ase) {
			ase.printStackTrace();

			//Return label and validation message status
			return chkBoxStatus;

		}	catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

			//Return label and validation message status
			return chkBoxStatus;
		}catch (Exception  e) {
			e.printStackTrace();

			//Return label and validation message status
			return chkBoxStatus;
		}
	}

	//Create custom XPath
	public static String createCustomXpath(String prefix_Xpath,  String insertTxt,String suffix_Xpath) {

		//Initialising string to be returned
		String finalCustomXpath= "incorrect Xpath";

		try {

			//retrieving the text value of the element
			finalCustomXpath = prefix_Xpath + insertTxt + suffix_Xpath ;

			//Return the value of string
			return finalCustomXpath;


		} catch (NoSuchElementException e) {

			e.printStackTrace();

			//return null if error
			return finalCustomXpath;

		}	catch (Exception e) {
			e.printStackTrace();

			//return null if error
			return finalCustomXpath;
		}  		
	}

	//Create custom XPath for DASHBOARD Page
	public static String dashboardSubMenuDynamicXpath(String insertTxt) {

		//Initialising string to be returned
		String finalCustomXpath= "incorrect Xpath";

		try {

			//retrieving the text value of the element
			finalCustomXpath = "//*[@id='sub-menu']//div[]//div//ul//li//a[text()='" + insertTxt + "']" ;

			//Return the value of string
			return finalCustomXpath;


		} catch (NoSuchElementException e) {

			e.printStackTrace();

			//return null if error
			return finalCustomXpath;

		}	catch (Exception e) {
			e.printStackTrace();

			//return null if error
			return finalCustomXpath;
		}  		
	}


	//delete entity method
	public static void deleteEntity(WebDriver wdelete ,String entityName) {
		//delete entity starts from here

		DashboardPage dashboardPage = new DashboardPage();
		EntityListingPage entListPage = new EntityListingPage();
		AddNewEntityPage addEntPage = new AddNewEntityPage();

		//Clicking on Element
		GenericMethods.waitforElement(wdelete, dashboardPage.administration_XPath);
		GenericMethods.elementClickable(wdelete, dashboardPage.administration_XPath);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
		GenericMethods.elementClick(wdelete, dashboardPage.administration_XPath);

		//waiting for link to load and then click
		GenericMethods.elementClickable(wdelete, dashboardPage.entities_XPath);
		GenericMethods.waitforElement(wdelete, dashboardPage.entities_XPath);

		//Clicking on Element
		GenericMethods.elementClick(wdelete, dashboardPage.entities_XPath);			

		///Delete starts here
		//wait for page load
		GenericMethods.pageLoadWait(2000);

		RAIS_applicationSpecificMethods.columnHeaderFilter(wdelete,entListPage.entityListingTableColHeader_XPath,
				entListPage.entityListingTableColHeader_TXT_XPath,entityName);

		//Clicking on specific entity created
		RAIS_applicationSpecificMethods.perm_restrict_Select_Click(wdelete,entListPage.entityListingTable_XPath, 
				entityName);

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//clicking on entity details page delete button
		GenericMethods.waitforElement(wdelete, addEntPage.deleteBtn_XPath);
		GenericMethods.elementClickable(wdelete, addEntPage.deleteBtn_XPath);
		GenericMethods.elementClick(wdelete, addEntPage.deleteBtn_XPath);


		//clicking on yes button
		//Waiting for delete popup page
		GenericMethods.waitforElement(wdelete, addEntPage.delEntity_popUpYesBtn_XPath);	
		GenericMethods.elementClickable(wdelete, addEntPage.delEntity_popUpYesBtn_XPath);
		GenericMethods.elementClick(wdelete, addEntPage.delEntity_popUpYesBtn_XPath);			

		//Waiting for delete popup page
		GenericMethods.waitforElement(wdelete, addEntPage.addnewEntity_SuccessMsg_XPath);	
		GenericMethods.elementClickable(wdelete, addEntPage.addnewEntity_SuccessMsg_XPath);

		//			//Waiting for button to load and click
		GenericMethods.waitforElement(wdelete, entListPage.addNewEntityBtn_XPath);	
		GenericMethods.elementClickable(wdelete, entListPage.addNewEntityBtn_XPath);

		//**************************************************Delete ENDS HERE	

	}

	//Delete form record
	public static void deleteFormRecord(WebDriver wfrmD, String DeleteBtnXpath, String popYesBtn ) {

		//Page wait
		GenericMethods.pageLoadWait(2000);

		//clicking on entity details page delete button
		GenericMethods.waitforElement(wfrmD, DeleteBtnXpath);
		GenericMethods.elementClickable(wfrmD, DeleteBtnXpath);
		GenericMethods.elementClick(wfrmD, DeleteBtnXpath);


		//clicking on yes button
		//Waiting for delete popup page
		//GenericMethods.waitforElement(wfrmD, addEntPage.delEntity_popUpYesBtn_XPath);
		GenericMethods.waitforElement(wfrmD, popYesBtn);
		GenericMethods.elementClickable(wfrmD, popYesBtn);
		GenericMethods.elementClick(wfrmD, popYesBtn);			

		//wait for page load
		GenericMethods.JSPageWait(wfrmD);

	}

	//Verify grid values
	//Method to select specific permission or restriction from table
	public static String gridGetText(WebDriver wdpr, String tableType, String cellTextValue) {

		//flag is used to exit loop
		boolean exitLoop=true;
		String gridText = null;

		//assigning table locators in webelement
		WebElement table = wdpr.findElement(By.xpath((tableType)));

		//assigning row elements to webelement
		List<WebElement> allrows = table.findElements(By.tagName("tr"));

		//looping between each row
		for(WebElement row: allrows){

			//check if flag is true from previous iterations
			if(exitLoop == true) {
				List<WebElement> Cells = row.findElements(By.tagName("td"));


				//looping for every element in the row
				for(WebElement Cell:Cells){

					//check if flag is true first and then check for corresponding text value
					if ((exitLoop ==true) && Cell.getText().equals(cellTextValue) ) {

						//click on the particular cell value
						gridText = Cell.getText();

						//below is used to highlight only when flag is true
						GenericMethods.highLightElement(wdpr, cellTextValue, GenericMethods.elementClickHighlight);

						//setting flag to false to exit if and for loop
						exitLoop = false;	            	
					}	

				}


			}
		}
		return gridText;

	}

	//Custom Attribute Input data
	//clicking on table filter and enter particular text
	public static void CreateBusinessattributeData(WebDriver watb, String typeOfAttribute, String linkedEntity ) {

		//Initialising Attribute page name below
		AddNewAttributePage CustaddAttbtInput = new AddNewAttributePage();

		GenericMethods.pageLoadWait(1000);

		//selecting datatype from dropdown as per value passed
		valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_dataTypedropDown_XPath, typeOfAttribute);

		switch (typeOfAttribute) {
		case "Checkbox":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);


			break;

		case "Numeric":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input label name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);

			//subtype input
			valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_subTypedropDown_XPath, CustaddAttbtInput.subTypeList[0]);


			break;

		case "Text":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input label name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);

			//input number of lines 
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_noOfLinesTxtBox_XPath, RaisTestData.text_lines);

			//subtype input
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_lengthTxtBox_XPath, RaisTestData.text_length);


			break;

		case "Date":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);



			break;

		case "Guid":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);




			break;

		case "Image":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);


			break;

		case "Lookup":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);

			//input linked entity
			valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_linkedEntitydropDown_XPath, linkedEntity);




			break;

		case "Lookup with Value":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);

			//input linked entity
			valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_linkedEntitydropDown_XPath, linkedEntity);

			//input subtype
			valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_subTypedropDown_XPath, CustaddAttbtInput.subTypeList[0]);

			//length input
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_lengthTxtBox_XPath, RaisTestData.text_length);




			break;

		case "Memo":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);




			break;

		case "Multi lookup":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);

			GenericMethods.pageLoadWait(500);

			//input linked entity
			valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_linkedEntitydropDown_XPath, linkedEntity);




			break;

		case "Multiple documents":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);



			break;

		case "Multiple Users":



			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.multiUsers_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.multiUsers_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.multiUsers_label);




			break;

		case "RAN":

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.ran_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.ran_label);



			break;

		case "Single document":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);




			break;

		case "Time Interval":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);




			break;

		case "User":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, RaisTestData.user_internalName);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, RaisTestData.user_toolTip);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, RaisTestData.user_label);



			break;

		case "Workflow Action":

			//input internal name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_internalNameTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_tooTipTxtBox_XPath, linkedEntity);

			//input tool tip name
			GenericMethods.sendText(watb, CustaddAttbtInput.addNewAttB_labelTxtBox_XPath, linkedEntity);




			break;

		case "Select":


			break;

		default:
			break;
		}	

		//mandatory input
		valueSelectfromDropDown(watb, CustaddAttbtInput.addNewAttB_mandInputdropDown_XPath, CustaddAttbtInput.mandInputList[0]);

		//click on show in listview
		GenericMethods.elementClick(watb, CustaddAttbtInput.addNewAttB_showinList_CheckBox_XPath);

		//click on show in history
		GenericMethods.elementClick(watb, CustaddAttbtInput.addNewAttB_showHist_CheckBox_XPath);


		//Waiting until element to load
		GenericMethods.waitforElement(watb, CustaddAttbtInput.SaveBtn_XPath);	
		GenericMethods.elementClickable(watb, CustaddAttbtInput.SaveBtn_XPath);
		GenericMethods.waitforvisibilityOfElement(watb, CustaddAttbtInput.SaveBtn_XPath);

		//wait for page load
		GenericMethods.pageLoadWait(500);
		GenericMethods.elementClick(watb, CustaddAttbtInput.SaveBtn_XPath);
		GenericMethods.pageLoadWait(300);

	}


	// Method to click on top menu
	public static void Generic_Menu_subMenu_Click(WebDriver wdMenu, String MainMenuName, String subMenu, String childMenu) {	

		//Setting flag to exit loop
		boolean flag = true; int colposition=0;

		//Setting column position
		if(subMenu == "Inventory" || subMenu == "User Management") {

			colposition = 1;
		} 

		//setting col position for other menus
		switch (subMenu) {

		case "Resources":

			colposition = 2;

			break;

		case "Authorizations":

			colposition = 1;

			break;

		case "Inspections":

			colposition = 3;

			break;

		case "Customization":

			colposition = 3;

			break;

		case "Common Tables":

			colposition = 4;

			break;

		default:
			break;
		}

		try {

			//Creating list of webelements returned from left pane				
			List<WebElement> menuList = wdMenu.findElements(By.xpath("//div//ul[@id='main-menu']/li"));
			//List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div/div[2]/ul/li"));

			//Iterating the role list webelement until match is found
			for (WebElement headerName : menuList) {				

				//				System.out.println(headerName);
				//locate specific role with text
				if ((flag==true) && headerName.findElement(By.tagName("a")).getText().equals(MainMenuName)) {

					System.out.println(headerName.findElement(By.tagName("a")).getText());
					headerName.click();

					//GenericMethods.pageLoadWait(600);
					GenericMethods.JSPageWait(wdMenu);

					List<WebElement> subMenuList = headerName.findElements(By.xpath("//div[@class='sub-menu']//div[@class='column']["+colposition+"]"));

					for (WebElement subHeaderName : subMenuList) {

						if ((flag==true) && subHeaderName.findElement(By.tagName("a")).getText().equals(subMenu)) {

							//subHeaderName.click();
							System.out.println(subHeaderName.findElement(By.tagName("a")).getText());

							List<WebElement> childMenuList = subHeaderName.findElements(By.xpath("//ul[@class='sub-menu-column']//li"));

							for (WebElement childName : childMenuList) {

								if ((flag==true) && childName.findElement(By.tagName("a")).getText().equals(childMenu)) {

									System.out.println(childName.findElement(By.tagName("a")).getText());

									//									//GenericMethods.pageLoadWait(600);
									//									GenericMethods.JSPageWait(wdMenu);

									WebElement childElement = childName.findElement(By.tagName("a"));

									JavascriptExecutor jscrollClick = (JavascriptExecutor)wdMenu;

									jscrollClick.executeScript("arguments[0].scrollIntoView()", childElement);

									//									//GenericMethods.pageLoadWait(600);
									//									GenericMethods.JSPageWait(wdMenu);

									//									System.out.println(childName.findElement(By.tagName("a")));

									//									String childMenuName = childName.findElement(By.tagName("a")).toString();
									//									System.out.println(childMenuName);


									//Clicking on specific child
									//									childElement.click();
									//									GenericMethods.JClickonElement(wdMenu, childMenuName);
									JavascriptExecutor executor = (JavascriptExecutor) wdMenu;
									executor.executeScript("arguments[0].click();", childName.findElement(By.tagName("a")));
									//									GenericMethods.JClickonElement(wdMenu, childElement);

									//setting flag as false
									flag= false;
								}

							}

						}
					} 
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Generic - Add new data to any entity method
	public static void addNewEntityData(WebDriver wdEntity, String AddButton) {

		GenericMethods.elementClickable(wdEntity, AddButton);
		GenericMethods.waitforElement(wdEntity, AddButton);

		//wait for page load
		GenericMethods.pageLoadWait(500);

		//Clicking on Element
		GenericMethods.elementClick(wdEntity, AddButton);

		//page wait
		GenericMethods.pageLoadWait(2000);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
	}

	//Save button click
	public static void ClickOnSave(WebDriver wdSave, String SaveBtn) {

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		// Clicking on save button
		GenericMethods.elementClickable(wdSave, SaveBtn);
		GenericMethods.waitforElement(wdSave, SaveBtn);
		GenericMethods.elementClick(wdSave, SaveBtn);

		//wait for page load
		GenericMethods.pageLoadWait(1000);	

	}

	//input Entity data on the form
	public static void inputEntityData(WebDriver wie, String FieldNameXpath, String FieldData) {

		//waiting for link to load and then click
		GenericMethods.elementClickable(wie, FieldNameXpath);
		GenericMethods.waitforElement(wie, FieldNameXpath);

		//wait for page load
		//GenericMethods.pageLoadWait(1000);

		//input data on sample numeric and text fields
		GenericMethods.sendText(wie, FieldNameXpath,FieldData);


	}

	//Delete InventoryResources Entity data
	public static void deleteDistrictsData(WebDriver wdInv, String deleteEntData) {

		//delete entity starts from here

		DashboardPage dashboardPage = new DashboardPage();
		EntityListingPage entListPage = new EntityListingPage();
		AddNewEntityPage addEntPage = new AddNewEntityPage();
		EntityFormListingPage entListingPage = new EntityFormListingPage();

		//Clicking on Element
		GenericMethods.waitforElement(wdInv, dashboardPage.invent_Resources_XPath);
		GenericMethods.elementClickable(wdInv, dashboardPage.invent_Resources_XPath);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
		GenericMethods.elementClick(wdInv, dashboardPage.invent_Resources_XPath);

		//waiting for link to load and then click
		GenericMethods.elementClickable(wdInv, dashboardPage.district_XPath);
		GenericMethods.waitforElement(wdInv, dashboardPage.district_XPath);

		//Clicking on Element
		GenericMethods.elementClick(wdInv, dashboardPage.district_XPath);			

		///Delete starts here
		//wait for page load
		GenericMethods.pageLoadWait(2000);

		columnHeaderFilter(wdInv,entListingPage.NameColHeader_TXT_XPath,
				entListPage.entityListingTableColHeader_TXT_XPath,deleteEntData);

		//Clicking on specific entity created
		perm_restrict_Select_Click(wdInv,entListPage.entityListingTable_XPath, 
				deleteEntData);

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//clicking on entity details page delete button
		GenericMethods.waitforElement(wdInv, addEntPage.deleteBtn_XPath);
		GenericMethods.elementClickable(wdInv, addEntPage.deleteBtn_XPath);
		GenericMethods.elementClick(wdInv, addEntPage.deleteBtn_XPath);


		//clicking on yes button
		//Waiting for delete popup page
		GenericMethods.waitforElement(wdInv, addEntPage.delEntity_popUpYesBtn_XPath);	
		GenericMethods.elementClickable(wdInv, addEntPage.delEntity_popUpYesBtn_XPath);
		GenericMethods.elementClick(wdInv, addEntPage.delEntity_popUpYesBtn_XPath);			

		//Waiting for delete popup page
		GenericMethods.waitforElement(wdInv, addEntPage.addnewEntity_SuccessMsg_XPath);	
		GenericMethods.elementClickable(wdInv, addEntPage.addnewEntity_SuccessMsg_XPath);

		//			//Waiting for button to load and click
		GenericMethods.waitforElement(wdInv, entListPage.addNewEntityBtn_XPath);	
		GenericMethods.elementClickable(wdInv, entListPage.addNewEntityBtn_XPath);

		//**************************************************Delete ENDS HERE	

	}

	//clickon Administration menu and submenu
	public static void mainMenu_SubMenu_Click(WebDriver wdmen, String mainMenu, String subMenu) {

		//Clicking on Element
		GenericMethods.waitforElement(wdmen, mainMenu);
		GenericMethods.elementClickable(wdmen, mainMenu);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
		GenericMethods.elementClick(wdmen, mainMenu);

		//waiting for link to load and then click on submenu
		GenericMethods.elementClickable(wdmen, subMenu);
		GenericMethods.waitforElement(wdmen, subMenu);

		//Clicking on Element
		GenericMethods.elementClick(wdmen, subMenu);			

		///Delete starts here
		//wait for page load
		GenericMethods.pageLoadWait(2000);

	}

	//Delete Entity Attribute from Entity form details page
	public static void Delete_And_Wait(WebDriver wdet, String deletebtnxpath, String popupYesbtnxpath, String successMsgxpath) {

		//wait for page load
		GenericMethods.pageLoadWait(1000);

		//clicking on entity details page delete button
		GenericMethods.waitforElement(wdet, deletebtnxpath);
		GenericMethods.elementClickable(wdet, deletebtnxpath);
		GenericMethods.elementClick(wdet, deletebtnxpath);


		//clicking on yes button
		//Waiting for delete popup page
		GenericMethods.waitforElement(wdet, popupYesbtnxpath);	
		GenericMethods.elementClickable(wdet, popupYesbtnxpath);
		GenericMethods.elementClick(wdet, popupYesbtnxpath);			

		//Waiting for delete popup page
		GenericMethods.waitforElement(wdet, successMsgxpath);	
		GenericMethods.elementClickable(wdet, successMsgxpath);

		//wait for page load
		GenericMethods.pageLoadWait(1000);
		//**************************************************Delete ENDS HERE	



	}

	//Click on grid which is having cellvalue as link - eg: Attribute listing page, clicking on attribute
	//Method to select specific permission or restriction from table
	public static void LinkClickonGrid(WebDriver wlg, String tableType, String cellTextValue) {

		//flag is used to exit loop
		boolean exitLoop=true;

		//assigning table locators in webelement
		WebElement table = wlg.findElement(By.xpath((tableType)));

		//assigning row elements to webelement
		List<WebElement> allrows = table.findElements(By.tagName("tr"));

		//looping between each row
		for(WebElement row: allrows){

			//check if flag is true from previous iterations
			if(exitLoop == true) {
				List<WebElement> Cells = row.findElements(By.tagName("td"));


				//looping for every element in the row
				for(WebElement Cell:Cells){

					//check if flag is true first and then check for corresponding text value
					if ((exitLoop ==true) && Cell.getText().equals(cellTextValue) ) {

						//Click on relevant xpath link locator ending with /a
						Cell.findElement(By.tagName("a")).click();

						//setting flag to false to exit if and for loop
						exitLoop = false;	            	
					}	

				}


			}
		}

	}

	//Create entity
	public static boolean createEntity(WebDriver went,String internalName, String singularName, String pluralName, 
			String entityGroup, String entityRole,
			String publishNav1, String publishNav2, String mode, String pageName) {

		EntityListingPage entityListingPage = new EntityListingPage();
		AddNewEntityPage addNewEntityPage = new AddNewEntityPage();

		//Common test data for entity creation
		String addNewEntityBtn_EntityListingPage = entityListingPage.addNewEntityBtn_XPath;
		String topMessageXpath = addNewEntityPage.addnewEntity_SuccessMsg_XPath;
		String saveButton = null;

		//select savebutton xpath as per pagename

		switch (pageName) {

		case "Entities":

			saveButton = addNewEntityPage.EntityPageSaveBtn_XPath;

			break;

		case "Functional Roles":

			saveButton = addNewEntityPage.FRSaveBtn_XPath;

			break;

		case "Data Roles":

			saveButton = addNewEntityPage.DRSaveBtn_XPath;

			break;

		case "Security Profiles":

			saveButton = addNewEntityPage.SRSaveBtn_XPath;

			break;

		default:

			break;
		}

		try {			

			//applicable for only add entity
			if (mode=="Add") {

				//page wait
				GenericMethods.JSPageWait(went);

				//********************************Add new Entity starts here
				//Waiting for button to load and click
				GenericMethods.waitforElement(went, addNewEntityBtn_EntityListingPage);
				GenericMethods.elementClickable(went, addNewEntityBtn_EntityListingPage);
				GenericMethods.elementClick(went, addNewEntityBtn_EntityListingPage);

				//GenericMethods.waitforElement(went, entityListingPage.addNewEntityBtn_XPath);
				//GenericMethods.elementClickable(went, entityListingPage.addNewEntityBtn_XPath);
				//GenericMethods.elementClick(went, entityListingPage.addNewEntityBtn_XPath);

			}		

			//page wait
			GenericMethods.JSPageWait(went);

			//Applicable only for add mode
			if (mode== "Add") {

				//GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);
				//input entity internal name
				GenericMethods.sendText(went, addNewEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

				//input entity Description name
				GenericMethods.sendText(went, addNewEntityPage.addNewEntity_DescTxtBox_XPath,
						RaisTestData.Entity_DescriptionData);			
			}

			//input entity Singular name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//Applicable only for add mode
			if (mode == "Add") {

				//input entity group name
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(went, addNewEntityPage.addNewEntity_grpDropDown_XPath, entityGroup);

				//input entity role name
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(went, addNewEntityPage.addNewEntity_roleDropDown_XPath, entityRole);

				//input entity publish navigation name
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(went,addNewEntityPage.addNewEntity_pubNavi1DropDown_XPath, publishNav1);
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(went,addNewEntityPage.addNewEntity_pubNavi2DropDown_XPath, publishNav2);

			}

			// verify the check box statuses
			Assert.assertTrue(GenericMethods.elementEnabled(went, addNewEntityPage.addNewEntity_enableWF_XPath));
			Assert.assertTrue(GenericMethods.elementEnabled(went, addNewEntityPage.addNewEntity_enableHistory_XPath));
			Assert.assertTrue(GenericMethods.elementEnabled(went, addNewEntityPage.addNewEntity_enabledocument_XPath));

			if (mode!= "Add") {

				// verify the check box statuses
				Assert.assertTrue(GenericMethods.elementEnabled(went, addNewEntityPage.addNewEntity_enableRAN_XPath));
			}

			//clicking on entity - save button
			GenericMethods.elementClick(went, saveButton);

			//waiting for success message
			GenericMethods.waitforElement(went, topMessageXpath);
			GenericMethods.elementVisible(went, topMessageXpath);

			//Applicable for add mode
			if(mode=="Add") {
				//verifying the add record success message
				Assert.assertEquals(GenericMethods.getActualTxt(went, topMessageXpath),
						addNewEntityPage.ADDNEWENT_SUCESSMSG_TXT);
			}else {
				//verifying the update record success message
				Assert.assertEquals(GenericMethods.getActualTxt(went, topMessageXpath),
						addNewEntityPage.UPDENT_SUCESSMSG_TXT);
			}

			//page wait
			GenericMethods.JSPageWait(went);

			//Setting return value true
			return true;			

		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return false;

		}catch (Exception  e) {

			e.printStackTrace();
			return false;
		}
	}

	//Create functional role
	public static boolean createFR(WebDriver wFuncRole, String roleNameFR, String entityGroupName, String entitytoSelect,
			boolean viewcheckBox,boolean addcheckBox, boolean editcheckBox, boolean delcheckBox) {

		//initialise
		DataRoles_FunctionalRolesPage DRFR_Page = new DataRoles_FunctionalRolesPage();
		AddNewPermRestrictionsPage FR_Permission = new AddNewPermRestrictionsPage();
		AddNewRolePage AddNewRole = new AddNewRolePage();

		try {
			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wFuncRole);

			//Clicking on Officers menu
			Generic_Menu_subMenu_Click(wFuncRole, RaisTestData.AdministrationMainMenu, RaisTestData.UserMgmtSubMenuText, 
					RaisTestData.functionalRole);

			//Clicking on add new data role
			GenericMethods.elementClick(wFuncRole, DRFR_Page.FR_addNewRoleBtn);

			//input role name and internal name
			GenericMethods.sendText(wFuncRole, AddNewRole.inputroleName_XPath, roleNameFR);

			//clicking on add role button			
			GenericMethods.tabfromElement(wFuncRole, AddNewRole.inputroleName_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);
			GenericMethods.elementClick(wFuncRole, AddNewRole.addBtn_XPath);

			//waiting for success message
			GenericMethods.waitforElement(wFuncRole, AddNewRole.addnewRole_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wFuncRole, AddNewRole.addnewRole_SuccessMsg_XPath);

			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wFuncRole, AddNewRole.addnewRole_SuccessMsg_XPath),
					AddNewRole.ADDNEWFUNC_ROLE_Txt);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			//Clicking on specific Role created
			RAIS_applicationSpecificMethods.roleSelect_Click(wFuncRole,DRFR_Page.FUNC_ROLE_Txt , roleNameFR);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			//click on add new permission button
			GenericMethods.JClickonElement(wFuncRole, DRFR_Page.FunctionalRolePermissionRestrictBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			//clicking on entity - save button
			GenericMethods.waitforElement(wFuncRole, FR_Permission.cancelBtn_XPath);
			GenericMethods.elementClickable(wFuncRole, FR_Permission.cancelBtn_XPath);

			//Selecting entity group
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFuncRole, FR_Permission.entityGroup_dropdown_XPath, entityGroupName);

			//select value from multiselect
			GenericMethods.elementClickable(wFuncRole, FR_Permission.MultiSelectDropdnClick_Xpath);
			GenericMethods.elementClick(wFuncRole, FR_Permission.MultiSelectDropdnClick_Xpath);

			//Create xpath of entity name to be clicked
			String entityValuetoClick = RAIS_applicationSpecificMethods.createCustomXpath(FR_Permission.MultiDropdwnValue_prefix_XPath,
					entitytoSelect,FR_Permission.MultiDropdwnValue_suffix_XPath);

			//CLicking on Add new permission button
			GenericMethods.JClickonElement(wFuncRole, entityValuetoClick);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			//collapsing the multiselect drop down
			GenericMethods.JClickonElement(wFuncRole, FR_Permission.MultiSelectDropdnClick_Xpath);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			//clicking on permission checkboxes
			//view check box select
			if(viewcheckBox == true) {
				GenericMethods.elementClick(wFuncRole, FR_Permission.view_Checkbox_XPath);
			}

			//Add check box select
			if(addcheckBox == true) {
				GenericMethods.elementClick(wFuncRole, FR_Permission.add_Checkbox_XPath);
			}

			//Edit check box select
			if(editcheckBox == true) {
				GenericMethods.elementClick(wFuncRole, FR_Permission.edit_Checkbox_XPath);
			}

			//Delete check box select
			if(delcheckBox == true) {
				GenericMethods.elementClick(wFuncRole, FR_Permission.del_Checkbox_XPath);
			}			

			//CLicking on Add new permission button
			GenericMethods.elementClickable(wFuncRole, FR_Permission.savBtn_XPath);
			GenericMethods.elementClick(wFuncRole, FR_Permission.savBtn_XPath);

			//waiting for success message
			GenericMethods.waitforElement(wFuncRole, FR_Permission.addnewPermRestrict_SuccessMsg_XPath);	
			GenericMethods.elementVisible(wFuncRole, FR_Permission.addnewPermRestrict_SuccessMsg_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wFuncRole);

			return true;
		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return false;

		} catch (Exception e) {
			return false;
		}


	}


	//Generic method to add entity record data in any form
	public static boolean createEntityRecord(WebDriver wRecordEntity, String businessEntityName, String nameInput, 
			String nameInput2, String emailInput, String mode, String formName) {

		//Initialising form name
		AddNewEntityFormDetailsPage addEntityRecordPage = new AddNewEntityFormDetailsPage();
		EntityFormListingPage entityListing = new EntityFormListingPage();

		//setting common variable value to reroute
		Boolean entitywith1Input = false;
		boolean entityCreationFLag = false;

		String addButton,saveButton;

		addButton = entityListing.addNewBtn_XPath;
		saveButton = saveBtnGenericPopUp(formName);

		try {

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			//only for Add new record mode
			if(mode=="Add") {

				//Waiting until element to load and click on Add new button
				//				GenericMethods.waitforElement(wRecordEntity, entityListing.addNewBtn_XPath);
				//				GenericMethods.elementClickable(wRecordEntity, entityListing.addNewBtn_XPath);
				GenericMethods.elementClick(wRecordEntity, addButton);

			}

			switch (businessEntityName) {

			case "Authorities and Organizations":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput2);

				//Legal basis data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

				//Chair person data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath, RaisTestData.CHAIRPERSON_DATA);
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath);


				break;

			case "Facilities":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput2);

				//Legal basis data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

				//Chair person data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath, RaisTestData.CHAIRPERSON_DATA);
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath);


				break;

			case "Practices":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//inspection data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.inspFrequency_XPath, nameInput2);

				//input inspection id type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.inspFrequencyID_XPath, 
						RaisTestData.year_LinkedEntity);

				//input practice category type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.practiceCat_XPath, 
						RaisTestData.suppliers_PracticeCat);

				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.practiceCat_XPath);

				break;


			case "Officers":

				//input officer name
				GenericMethods.sendText_removeblank(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//wait for page load
				GenericMethods.JSPageWait(wRecordEntity);

				//input Gender type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_genderFld_Xpath, RaisTestData.GENDER_FEMALE_DATA);

				//input Authority type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_authorityFld_Xpath, nameInput2);

				//Officer value to select from drop, assign to string
				String officerTask = RAIS_applicationSpecificMethods.createCustomXpath(addEntityRecordPage.FR_prefix, addEntityRecordPage.LICENSE_Txt, 
						addEntityRecordPage.FR_suffix);

				//multiselect officer task
				RAIS_applicationSpecificMethods.multiSelectList(wRecordEntity, addEntityRecordPage.restriction_DropdnClick_Xpath, officerTask);

				//input Email
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_emailFld_Xpath, emailInput);			

				//setting tab element to enable save button
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_emailFld_Xpath);

				break;


			case "Booleans":

				//set flag value as true
				entitywith1Input = true;

				break;

			case "Districts":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//select value from drop down
				valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_region_Xpath, nameInput2);

				break;

			case "NameOnly":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//Tab from element
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath);


				break;


			default:
				break;
			}

			if (businessEntityName == "Radiation Generator Types" || businessEntityName == "Types of Associated Equipments"
					||businessEntityName =="Person Tasks" ||businessEntityName == "Worker Tasks") {

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//select value from drop down
				valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_practiceFld_XPath, nameInput2);	

			} else if(entitywith1Input == true) {

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, nameInput);

				//Tab from element
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath);

			}

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			if(formName == "" ) {

				//Waiting until element to load and click on Add new button
				//			GenericMethods.waitforElement(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);
				//			GenericMethods.elementClickable(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);
				GenericMethods.elementClick(wRecordEntity, saveButton);

			} else {

				//click on regular save button
				GenericMethods.JClickonElement(wRecordEntity, saveButton);

				entityCreationFLag = true;
			}

			//waiting for success message
			GenericMethods.waitforElement(wRecordEntity, addEntityRecordPage.form_SuccessMsg_XPath);
			//GenericMethods.elementVisible(wRecordEntity, addEntityRecordPage.form_SuccessMsg_XPath);

			if (mode=="Add") {
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wRecordEntity, addEntityRecordPage.form_SuccessMsg_XPath),
						addEntityRecordPage.ADDNEWRECORD_SUCESSMSG_TXT);
			} else if (mode=="Edit"){

				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wRecordEntity, addEntityRecordPage.form_SuccessMsg_XPath),
						addEntityRecordPage.UPDATERECORD_SUCESSMSG_TXT);
			}

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			//waiting for link to load and then click
			//			GenericMethods.waitforElement(wRecordEntity, entityListing.addNewBtn_XPath);
			//			GenericMethods.elementClickable(wRecordEntity, entityListing.addNewBtn_XPath);			

			//wait for page load
			//			GenericMethods.JSPageWait(wRecordEntity);

			return entityCreationFLag;

		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return entityCreationFLag;

		}catch (Exception  e) {

			e.printStackTrace();
			return entityCreationFLag;
		}

	}

	//method to return menu and submenu based on input
	public static String [] menuSubMenuName(String groupName) {

		//initialising string to return
		//String Full_List[] = null;
		String BEname []  = null;
		String mainMenu = null;
		String subMenu = null;

		switch (groupName) {
		case "Admin_ComMenu":

			//Initialising entity names
			BEname  = RaisTestData.singleFldBE_Admin_CommonTbl;
			mainMenu = RaisTestData.AdministrationMainMenu;;
			subMenu = RaisTestData.CommonTablesSubMenu;

			break;

		case "Admin_CustMenu":

			//Initialising entity names
			BEname  = RaisTestData.singleFldBE_Admin_Custom;
			//			 mainMenu = adminMainMenu;
			//			 subMenu = CustomizeSubMenu;

			break;

		case "InvRes_InvMenu":

			//Initialising entity names
			BEname  = RaisTestData.singleFldBE_Inv_Inv;
			//			 mainMenu = invenResMainMenu;
			//			 subMenu = InvSubMenuName;

			break;

		case "InvRes_ResMenu":

			//Initialising entity names
			BEname  = RaisTestData.singleFldBE_Inv_Res;
			//			 mainMenu = invenResMainMenu;
			//			 subMenu = ResrcSubMenuName;

			break;

		default:
			break;

		}

		return new String[] {mainMenu,subMenu};


	}


	public static List<Object> getmenuSubmenuName() {

		String name = "abs";
		int age = 123;
		boolean b = true;

		return Arrays.asList(name, age, b);
	}

	//User creation method
	public static boolean createUser(WebDriver wUser, String userCategory,String userType, String orgType, String orgName,String typeUserName, String userID, String FR, String DR) {

		//Initialising 
		UserListPage UserListPage = new UserListPage();
		AddNewUserDetailsPage createUserPage = new AddNewUserDetailsPage();
		AddNewEntityFormDetailsPage entityFormpage = new AddNewEntityFormDetailsPage ();

		try {

			//wait for page load
			GenericMethods.JSPageWait(wUser);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wUser, createUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wUser, createUserPage.cancelBtn_XPath);

			//Organisation type
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wUser, createUserPage.addnewUser_orgType_XPath, orgType);

			//wait for page load
			GenericMethods.JSPageWait(wUser);

			//Organisation input
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wUser, createUserPage.addnewUser_org_XPath, orgName);

			//Only for primary user
			if (userCategory == "Primary") {

				//Check for external user condition
				if(userType == "External"){

					//External user radio button click
					GenericMethods.elementClick(wUser, createUserPage.addnewUser_ExternalrdobtnAuth_XPath);

				}else{

					//Internal user radio button click
					GenericMethods.elementClick(wUser, createUserPage.addnewUser_InternalrdobtnAuth_XPath);

				}
			}

			//			//Only for primary user
			//			if (userCategory == "Primary" && userType == "External") {
			//				
			//				//External user
			//				GenericMethods.elementClick(wUser, createUserPage.addnewUser_ExternalrdobtnAuth_XPath);
			//				
			//			}else if (userCategory == "Primary" && userType == "Internal") {
			//				
			//				//External user
			//				GenericMethods.elementClick(wUser, createUserPage.addnewUser_InternalrdobtnAuth_XPath);
			//			}

			//Enter officer name
			GenericMethods.sendText(wUser, createUserPage.addnewUser_selectUser_XPath, typeUserName);

			//page wait
			GenericMethods.JSPageWait(wUser);

			//depending upon primary/ secondary user select userid
			if(userCategory == "Primary") {

				//Enter user ID name
				GenericMethods.sendText(wUser, createUserPage.addnewUser_userName_XPath, userID);

			}

			//wait for page load
			GenericMethods.JSPageWait(wUser);

			//Create custom xpath
			String externalUserFR = RAIS_applicationSpecificMethods.createCustomXpath(entityFormpage.FR_prefix, FR, entityFormpage.FR_suffix);

			//Selecting functional role
			//GenericMethods.elementClick(wd, entityFrmDetailspage.userMgmt_FRdrpdwn_Xpath);
			RAIS_applicationSpecificMethods.multiSelectList(wUser, entityFormpage.userMgmt_FRdrpdwn_Xpath, externalUserFR);
			//		RAIS_applicationSpecificMethods.multiSelectList(wUser, entityFormpage.userMgmt_FRdrpdwn_Xpath, externalUserFR);

			//Select Data role
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wUser, createUserPage.addnewUser_DataRole_Xpath, DR);

			//click on user Activate
			GenericMethods.elementClick(wUser, createUserPage.addNewUserDetail_statusActive_XPath);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wUser, createUserPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wUser, createUserPage.cancelBtn_XPath);
			GenericMethods.elementClick(wUser, createUserPage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.pageLoadWait(1000);
			GenericMethods.JSPageWait(wUser);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wUser, UserListPage.addNewUserBtn_XPath);
			GenericMethods.elementClickable(wUser, UserListPage.addNewUserBtn_XPath);

			return true;
		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//Robotclass to handle windows authentication
	public static void windowsAuthscreen_Login() {

		try {
			Robot rbc = new Robot();

			//RAIS_Test1	

			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);

			//Enter username
			rbc.keyPress(KeyEvent.VK_R); rbc.keyRelease(KeyEvent.VK_R);	
			rbc.keyPress(KeyEvent.VK_A); rbc.keyRelease(KeyEvent.VK_A);
			rbc.keyPress(KeyEvent.VK_I); rbc.keyRelease(KeyEvent.VK_I);
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);

			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);


			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);
			rbc.keyPress(KeyEvent.VK_MINUS); rbc.keyRelease(KeyEvent.VK_MINUS);
			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);
			rbc.keyPress(KeyEvent.VK_T); rbc.keyRelease(KeyEvent.VK_T);
			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_E); rbc.keyRelease(KeyEvent.VK_E);	
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);
			rbc.keyPress(KeyEvent.VK_T); rbc.keyRelease(KeyEvent.VK_T);
			rbc.keyPress(KeyEvent.VK_1); rbc.keyRelease(KeyEvent.VK_1);

			//input tab
			rbc.keyPress(KeyEvent.VK_TAB); rbc.keyRelease(KeyEvent.VK_TAB);

			//password input   RPS%zst$ts@rs%2020
			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_R); rbc.keyRelease(KeyEvent.VK_R);	
			rbc.keyPress(KeyEvent.VK_P); rbc.keyRelease(KeyEvent.VK_P);
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);
			rbc.keyPress(KeyEvent.VK_5); rbc.keyRelease(KeyEvent.VK_5);

			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_Z); rbc.keyRelease(KeyEvent.VK_Z);	
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);
			rbc.keyPress(KeyEvent.VK_T); rbc.keyRelease(KeyEvent.VK_T);

			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);
			rbc.keyPress(KeyEvent.VK_4); rbc.keyRelease(KeyEvent.VK_4);
			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_T); rbc.keyRelease(KeyEvent.VK_T);	
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);

			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);
			rbc.keyPress(KeyEvent.VK_2); rbc.keyRelease(KeyEvent.VK_2);
			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_R); rbc.keyRelease(KeyEvent.VK_R);	
			rbc.keyPress(KeyEvent.VK_S); rbc.keyRelease(KeyEvent.VK_S);

			//Clicking on shift
			rbc.keyPress(KeyEvent.VK_SHIFT);
			rbc.keyPress(KeyEvent.VK_5); rbc.keyRelease(KeyEvent.VK_5);
			//Releasing shift
			rbc.keyRelease(KeyEvent.VK_SHIFT);

			rbc.keyPress(KeyEvent.VK_2); rbc.keyRelease(KeyEvent.VK_2);	
			rbc.keyPress(KeyEvent.VK_0); rbc.keyRelease(KeyEvent.VK_0);
			rbc.keyPress(KeyEvent.VK_2); rbc.keyRelease(KeyEvent.VK_2);	
			rbc.keyPress(KeyEvent.VK_0); rbc.keyRelease(KeyEvent.VK_0);

			//Clicking tab
			rbc.keyPress(KeyEvent.VK_TAB); rbc.keyRelease(KeyEvent.VK_TAB);

			//clicking on signon
			rbc.keyPress(KeyEvent.VK_ENTER); rbc.keyRelease(KeyEvent.VK_ENTER);


		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	//Primary & secondary login
	public static void primary_secondary_Login (WebDriver drvPS, String userType, String userName, String pwd, String userDropDown) {

		//Initialise login page
		LoginPage lgnPage = new LoginPage();

		//page wait
		GenericMethods.JSPageWait(drvPS);

		if(userType == "External") {

			//Calling Login method
			GenericMethods.loginApplication(drvPS, lgnPage.userId_XPath, userName, lgnPage.pwd_XPath, pwd, lgnPage.loginBtn_XPath);

		} else {

			//clicking on internal tab
			GenericMethods.elementClick(drvPS,lgnPage.internalLogin_XPath);

			//page wait
			GenericMethods.JSPageWait(drvPS);

			//clicking on windows auth button
			GenericMethods.elementClick(drvPS,lgnPage.winAuthBtn_XPath);

			//page wait
			GenericMethods.JSPageWait(drvPS);

			//calling robot class
			RAIS_applicationSpecificMethods.windowsAuthscreen_Login();

		}

		//wait for page load
		GenericMethods.JSPageWait(drvPS);

		//selecting user from drop down
		RAIS_applicationSpecificMethods.valueSelectfromDropDown(drvPS, lgnPage.primaryUser_Dropdown_Xpath,userDropDown);

		//click on submit button
		GenericMethods.elementClick(drvPS, lgnPage.submitBtn_XPath);

		//page wait
		GenericMethods.JSPageWait(drvPS);		

	}

	//Delete entity record for CRUD operations
	public static void deleteEntityRecord(WebDriver wdEntityRecord, String msgType) {

		//initialisting entity form listing page
		AddNewEntityFormDetailsPage entRecordpage = new AddNewEntityFormDetailsPage ();

		//*****************
		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);

		//Waiting until element to load and click on delete button
		GenericMethods.elementClick(wdEntityRecord, entRecordpage.deleteBtn_XPath);

		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);

		//Waiting for delete popup page
		GenericMethods.JClickonElement(wdEntityRecord, entRecordpage.popUpYesBtn_XPath);	



		if (msgType == "Success") {
			//Waiting for delete popup page
			GenericMethods.waitforElement(wdEntityRecord, entRecordpage.form_SuccessMsg_XPath);
			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wdEntityRecord, entRecordpage.form_SuccessMsg_XPath),
					entRecordpage.DELRECORD_SUCCESSMSG_TXT);

		} else if (msgType == "Error") {

			//Waiting for delete popup page
			//GenericMethods.waitforElement(wdEntityRecord, entRecordpage.topErrorMsg);

			//page wait
			GenericMethods.JSPageWait(wdEntityRecord);

			//verify the error msg on the top
			Assert.assertEquals(GenericMethods.getActualTxt(wdEntityRecord, entRecordpage.topErrorMsg),
					entRecordpage.topErrorMsg_Txt);

		}
		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);		

	}


	//Pass entity menu location and perform add/ edit and delete functions

	public static void entityAdd_Edit_Delete(WebDriver wAddEditDel, String publishMenu, String publishSubmenu, String entityName, 
			String inputFld1,String inputFld2,String inputFld3,String modeType) {

		try {
			//set flag for add/ edit
			boolean flagAddEdit = false;

			//Clicking on User menu
			Generic_Menu_subMenu_Click(wAddEditDel, publishMenu, publishSubmenu, entityName);
			//***********************************************Create entity record
			//calling generic method to call Officer entity data input
			flagAddEdit = RAIS_applicationSpecificMethods.createEntityRecord(wAddEditDel, entityName, inputFld1, inputFld2,
					inputFld3, modeType, null);
			////**********************************************EDIT MODE STARTS Here						
			//Grid filter and click on entity record listing page
			EntityRecordGridFilter_Click(wAddEditDel, 2, inputFld1);

			//String creation for edit mode
			String editModeinput;


			//Edit entity record
			//calling generic method to call Officer entity data edit
			flagAddEdit = RAIS_applicationSpecificMethods.createEntityRecord(wAddEditDel, entityName, "localTime", "Optional",
					"Optional", "Optional", null);
			////***********************************************delete starts here
			//Grid filter and click on entity record listing page
			EntityRecordGridFilter_Click(wAddEditDel, 2, "TestAuto" + "localTime");
			//delete entity record data
			deleteEntityRecord(wAddEditDel, null);

		}catch (NoSuchElementException  noElement) {
			noElement.printStackTrace();

		}catch (Exception  e) {
			e.printStackTrace();

		}	
	}

	//drag drop using jscript
	public void dragDrop(WebDriver driver, String src, String dst) {
		String js = String.format("$('%s').simulateDragDrop({ dropTarget: '%s'});",src,dst);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("dragndrop_js" + js);
	}

	//Dynamic column header and click 22-Aug only for entity listing page
	public static void EntityListingGridFilter_Click(WebDriver wGrid,int position,String filterText) {

		//initialisting entity form listing page
		EntityFormListingPage entListPage = new EntityFormListingPage();

		//Initialising string as per page
		String gridTablePrefix_Xpath = entListPage.EntityListing_GridTable_Prefix_Xpath;;

		//Fetch col header name w.r.t position into the string
		String ColName = gridTablePrefix_Xpath + position +entListPage.Dynamic_colHeaderName_Text_Suffix2_Xpath;

		//Get text name of the header
		ColName = GenericMethods.getActualTxt(wGrid, ColName);

		//concatenate prefix, suffix, col header name, position to form xpath
		String GridColHeader = gridTablePrefix_Xpath +position+entListPage.Dynamic_GridTable_Suffix1_Xpath
				+ColName+entListPage.Dynamic_GridTable_Suffix2_Xpath;

		//create dynamic dynamic xpath of text field
		String GridTextInput_xpath = gridTablePrefix_Xpath +position+entListPage.Dynamic_GridTable_TxtInput_Suffix_Xpath;

		//wait for page load
		GenericMethods.JSPageWait(wGrid);

		//clicking on filter icon on the grid
		GenericMethods.elementClick(wGrid, GridColHeader);

		//wait for page load
		GenericMethods.JSPageWait(wGrid);
		//		GenericMethods.waitforElement(wGrid, GridTextInput_xpath);
		//		GenericMethods.elementClickable(wGrid, GridTextInput_xpath);

		//input text value to search
		GenericMethods.sendText(wGrid, GridTextInput_xpath, filterText);

		//wait for page load
		GenericMethods.JSPageWait(wGrid);			

		//Clicking on specific Role created
		perm_restrict_Select_Click(wGrid,entListPage.securityProfileTableList_XPath , filterText);

		//wait for page load
		GenericMethods.JSPageWait(wGrid);	

	}

	//Delete individual entity method - 22-Aug
	//Delete entity record for CRUD operations
	public static void deleteEntity(WebDriver wdEntityRecord) {

		//initialisting entity form listing page
		AddNewEntityPage entpage = new AddNewEntityPage ();

		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);

		//Waiting until element to load and click on delete button
		GenericMethods.elementClick(wdEntityRecord, entpage.deleteBtn_XPath);

		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);

		//Waiting for delete popup page
		GenericMethods.JClickonElement(wdEntityRecord, entpage.delEntity_popUpYesBtn_XPath);			

		//		//page wait
		//		GenericMethods.JSPageWait(wdEntityRecord);

		//Waiting for delete popup page
		GenericMethods.waitforElement(wdEntityRecord, entpage.addnewEntity_SuccessMsg_XPath);	
		GenericMethods.elementClickable(wdEntityRecord, entpage.addnewEntity_SuccessMsg_XPath);

		//verifying the success message
		Assert.assertEquals(GenericMethods.getActualTxt(wdEntityRecord, entpage.addnewEntity_SuccessMsg_XPath),
				entpage.DELENT_SUCESSMSG_TXT);

		//page wait
		GenericMethods.JSPageWait(wdEntityRecord);
	}

	//Logout method new 23-Aug
	public static void logout(WebDriver wLgout) {

		//Initialise page
		DashboardPage HomePage = new DashboardPage();			

		//New code
		//page wait
		GenericMethods.JSPageWait(wLgout);

		//Click on top icon to expand logout menu
		GenericMethods.elementClick(wLgout, HomePage.loggedinUser_XPath);

		//page wait
		GenericMethods.JSPageWait(wLgout);

		//Click on logout menu
		GenericMethods.elementClick(wLgout, HomePage.logout_XPath);

		//page wait
		GenericMethods.JSPageWait(wLgout);

		//verifying logout screen text
		Assert.assertEquals(GenericMethods.getActualTxt(wLgout, HomePage.logoutPage_XPath),
				HomePage.LP_SUCCESS_LOGOUT);

		//click on login back to land on login page
		GenericMethods.elementClick(wLgout, HomePage.reLogin_XPath);	

		//page wait
		GenericMethods.JSPageWait(wLgout);

		//page refresh
		wLgout.navigate().refresh();

	}

	// Method to select roles/ functions from left navigation pane
	public static void DRFR_Edit_Delete(WebDriver wdi,String roleName, String mode, String roleType) {	

		//initialising DR & FR page
		DataRoles_FunctionalRolesPage DR_FRPage = new DataRoles_FunctionalRolesPage();

		try {

			String DR_FR_RoleName = "";

			if(roleType == "Data Roles") {

				DR_FR_RoleName= DR_FRPage.edit_delPREFIX_XPath;

			} else {

				DR_FR_RoleName = DR_FRPage.FR_edit_delPREFIX_XPath;
			}

			//Assigning to list webelemt
			List<WebElement> roleList = wdi.findElements(By.xpath(DR_FR_RoleName));

			String edit_Delete;

			//Iterating between the list webelements
			for (int i=0; i<roleList.size();i++) {

				//print index
				//				System.out.println(i);

				WebElement roleName1 = roleList.get(i);

				//Compare the DR/ FR which needs to be clicked based on the param
				if (roleName1.findElement(By.tagName("a")).getText().equals(roleName)) {

					//Increment counter to append to prefix + suffix
					int position=i+1;

					//Print index and role
					//System.out.println(i);
					//System.out.println(roleList.get(1));

					//Click on the selected link
					roleName1.click();

					if (mode=="Edit") {

						//create dynamic xpath
						edit_Delete = DR_FR_RoleName+"["+position+DR_FRPage.edit_Suffix_XPath;

						//pagewait
						GenericMethods.JSPageWait(wdi);

						//clicking on role name		
						GenericMethods.elementClick(wdi, edit_Delete);

					} else if(mode=="Delete") {

						//create dynamic xpath for delete
						edit_Delete = DR_FR_RoleName+"["+position+DR_FRPage.delete_Suffix_XPath;	

						//pagewait
						GenericMethods.JSPageWait(wdi);

						//clicking on role name		
						GenericMethods.elementClick(wdi, edit_Delete);
					}

					//pagewait
					GenericMethods.JSPageWait(wdi);

				}
			}		


		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  
	}

	//Method for save and verify in DR / FR areas
	public static void DR_FR_Input_Save_Verify(WebDriver wsave,String inputText, String verifyText, String roleType) {

		//Initialise DRFR page
		AddNewRolePage DRFR_Add_Verify = new AddNewRolePage();

		try {

			//input role name and internal name
			GenericMethods.sendText(wsave, DRFR_Add_Verify.inputroleName_XPath, inputText);

			//clicking on add role button			
			GenericMethods.tabfromElement(wsave, DRFR_Add_Verify.inputroleName_XPath);
			System.out.println("clicked on addrole");

			if(roleType == "Data Roles") {

				//page load and click
				GenericMethods.elementClick(wsave, DRFR_Add_Verify.SaveBtn_XPath);				

			} else {

				//page load and click
				GenericMethods.elementClick(wsave, DRFR_Add_Verify.FRSaveBtn_XPath);

			}		

			//waiting for success message
			GenericMethods.waitforElement(wsave, DRFR_Add_Verify.addnewRole_SuccessMsg_XPath);
			GenericMethods.elementVisible(wsave, DRFR_Add_Verify.addnewRole_SuccessMsg_XPath);

			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(wsave, DRFR_Add_Verify.addnewRole_SuccessMsg_XPath),
					verifyText);

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  

	}

	//Delete method for DR or FR
	public static void delete_DR_FR(WebDriver wdDRFR, String deleteMsg, String roleType ) {

		//Initialise page
		DataRoles_FunctionalRolesPage deleteDRFR = new DataRoles_FunctionalRolesPage();

		//page wait
		GenericMethods.JSPageWait(wdDRFR);

		if (roleType =="Data Roles" ) {

			//Waiting for delete popup page
			GenericMethods.JClickonElement(wdDRFR, deleteDRFR.deletePopupMsgYesBtn_XPath);	

		} else {

			//Waiting for delete popup page
			GenericMethods.JClickonElement(wdDRFR, deleteDRFR.FR_deletePopupMsgYesBtn_XPath);
		}

		//page wait
		GenericMethods.JSPageWait(wdDRFR);

		//Waiting for delete popup page
		GenericMethods.waitforElement(wdDRFR, deleteDRFR.TopMessage_SuccessMsg_XPath);	
		GenericMethods.elementClickable(wdDRFR, deleteDRFR.TopMessage_SuccessMsg_XPath);

		//verifying the success message
		Assert.assertEquals(GenericMethods.getActualTxt(wdDRFR, deleteDRFR.TopMessage_SuccessMsg_XPath),
				deleteMsg);

		//page wait
		GenericMethods.JSPageWait(wdDRFR);
	}

	// Method to click on top menu
	public static Boolean verifyItemPresentinMenu(WebDriver wdMenu, String MainMenuName, String subMenu, String childMenu) {	

		//Setting flag to exit loop
		boolean flag = true; int colposition=0;

		//Return flag
		boolean elementPresent = false;

		//Setting column position
		if(subMenu == "Inventory" || subMenu == "User Management") {

			colposition = 1;
		} 

		//setting col position for other menus
		switch (subMenu) {

		case "Resources":

			colposition = 2;

			break;

		case "Customization":

			colposition = 3;

			break;

		case "Common Tables":

			colposition = 4;

			break;

		default:
			break;
		}

		try {

			//pagerefresh
			wdMenu.navigate().refresh();

			//page wait
			GenericMethods.JSPageWait(wdMenu);

			//Creating list of webelements returned from left pane				
			List<WebElement> menuList = wdMenu.findElements(By.xpath("//div//ul[@id='main-menu']/li"));
			//List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div/div[2]/ul/li"));

			//Iterating the role list webelement until match is found
			for (WebElement headerName : menuList) {				

				//				System.out.println(headerName);
				//locate specific role with text
				if ((flag==true) && headerName.findElement(By.tagName("a")).getText().equals(MainMenuName)) {

					System.out.println(headerName.findElement(By.tagName("a")).getText());
					headerName.click();

					//GenericMethods.pageLoadWait(600);
					GenericMethods.JSPageWait(wdMenu);

					List<WebElement> subMenuList = headerName.findElements(By.xpath("//div[@class='sub-menu']//div[@class='column']["+colposition+"]"));

					for (WebElement subHeaderName : subMenuList) {

						if ((flag==true) && subHeaderName.findElement(By.tagName("a")).getText().equals(subMenu)) {

							//subHeaderName.click();
							System.out.println(subHeaderName.findElement(By.tagName("a")).getText());

							List<WebElement> childMenuList = subHeaderName.findElements(By.xpath("//ul[@class='sub-menu-column']//li"));

							for (WebElement childName : childMenuList) {

								if ((flag==true) && childName.findElement(By.tagName("a")).getText().equals(childMenu)) {

									System.out.println(childName.findElement(By.tagName("a")).getText());

									//										 //setting return type flag
									elementPresent = true;

									//calling element highligt
									GenericMethods.highLightWebElement(wdMenu, childName.findElement(By.tagName("a")), GenericMethods.elementClickHighlight);

									//setting flag as false
									flag= false;
								}

							}

						}
					} 
				}
			}

			//pagerefresh
			wdMenu.navigate().refresh();

			//return flag value
			return elementPresent;				


		} catch (NoSuchElementException e) {
			e.printStackTrace();

			//return flag value
			return elementPresent;

		}	catch (Exception e) {
			e.printStackTrace();

			//return flag value
			return elementPresent;
		}  
	}

	//create, edit security profile
	public static boolean createEdtDelSecurityProfile(WebDriver wSP,String secProfileName, String selectEntity, String selectFields,String mode) {

		//initialising pages
		SecurityProfilePage SPListingPage = new SecurityProfilePage();
		AddNewSecurityProfilePage addEditSecProfile = new AddNewSecurityProfilePage();

		//Common test data for entity creation
		String addNewSP_ListingPage = SPListingPage.addNewProfileBtn_XPath;
		String topMessageXpath = addEditSecProfile.secProf_Msg_XPath;
		String saveButton = addEditSecProfile.SaveBtn_XPath;
		String deleteButton = addEditSecProfile.deleteBtn_XPath;
		String popupYesBtn = addEditSecProfile.popUpYesBtn_XPath;

		try {			

			//page wait
			GenericMethods.JSPageWait(wSP);

			//Applicable only for add mode
			if (mode== "Add") {

				//click on add new SP button
				GenericMethods.elementClick(wSP, addNewSP_ListingPage);

				//page wait
				GenericMethods.JSPageWait(wSP);

				//input entity internal name
				GenericMethods.sendText(wSP, addEditSecProfile.addnewSP_inputroleName_XPath, secProfileName);

				//input entity group name
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wSP, addEditSecProfile.addnewSP_entityNameDropdown_XPath, selectEntity);

				//page wait
				GenericMethods.JSPageWait(wSP);

				//input field name
				GenericMethods.elementClick(wSP, addEditSecProfile.restriction_DropdnClick_Xpath);
				GenericMethods.elementClick(wSP, addEditSecProfile.restriction_add_dropdown_XPath);
				GenericMethods.elementClick(wSP, addEditSecProfile.restriction_DropdnClick_Xpath);

				//Select edit radio button
				GenericMethods.elementClick(wSP, addEditSecProfile.rdobtnView_XPath);	

				//clicking on entity - save button
				GenericMethods.elementClick(wSP, saveButton);

				//waiting for success message
				GenericMethods.waitforElement(wSP, topMessageXpath);
				GenericMethods.elementVisible(wSP, topMessageXpath);

				//verifying the add record success message
				Assert.assertEquals(GenericMethods.getActualTxt(wSP, topMessageXpath),
						addEditSecProfile.ADDNEWSP_SUCESSMSG_TXT);					

			}else if (mode =="Edit") {

				//input entity internal name
				GenericMethods.sendText(wSP, addEditSecProfile.addnewSP_inputroleName_XPath, secProfileName);

				//clicking on entity - save button
				GenericMethods.elementClick(wSP, saveButton);

				//waiting for success message
				GenericMethods.waitforElement(wSP, topMessageXpath);
				GenericMethods.elementVisible(wSP, topMessageXpath);

				//verifying the update record success message
				Assert.assertEquals(GenericMethods.getActualTxt(wSP, topMessageXpath),
						addEditSecProfile.UPDATESP_SUCESSMSG_TXT);

			}else if (mode == "Delete") {

				//clicking on entity - save button
				GenericMethods.elementClick(wSP, deleteButton);

				//clicking on popup button
				GenericMethods.elementClick(wSP, popupYesBtn);					

				//waiting for success message
				GenericMethods.waitforElement(wSP, topMessageXpath);
				GenericMethods.elementVisible(wSP, topMessageXpath);

				//verifying the update record success message
				Assert.assertEquals(GenericMethods.getActualTxt(wSP, topMessageXpath),
						addEditSecProfile.DELSP_SUCESSMSG_TXT);
			}

			//page wait
			GenericMethods.JSPageWait(wSP);

			//Setting return value true
			return true;			

		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return false;

		}catch (Exception  e) {

			e.printStackTrace();
			return false;
		}
	}

	//Dynamic column header and click 01-Sep only for entity listing page
	public static void SecurityProfListingGrid_Click(WebDriver wSPGrid,int position,String filterText) {

		//initialisting entity form listing page
		EntityFormListingPage entListPage = new EntityFormListingPage();

		//Initialising string as per page
		String gridTablePrefix_Xpath = entListPage.SecurityProfileListing_GridTable_Prefix_Xpath;;

		//Fetch col header name w.r.t position into the string
		String ColName = gridTablePrefix_Xpath + position +entListPage.Dynamic_colHeaderName_Text_Suffix2_Xpath;

		//Get text name of the header
		ColName = GenericMethods.getActualTxt(wSPGrid, ColName);

		//concatenate prefix, suffix, col header name, position to form xpath
		String GridColHeader = gridTablePrefix_Xpath +position+entListPage.Dynamic_GridTable_Suffix1_Xpath
				+ColName+entListPage.Dynamic_GridTable_Suffix2_Xpath;

		//create dynamic dynamic xpath of text field
		String GridTextInput_xpath = gridTablePrefix_Xpath +position+entListPage.Dynamic_GridTable_TxtInput_Suffix_Xpath;

		GenericMethods.waitforElement(wSPGrid, GridColHeader);
		//		GenericMethods.elementClickable(wGrid, GridTextInput_xpath);

		//clicking on filter icon on the grid
		GenericMethods.elementClick(wSPGrid, GridColHeader);

		//wait for page load
		//		GenericMethods.JSPageWait(wSPGrid);
		GenericMethods.waitforElement(wSPGrid, GridColHeader);

		//input text value to search
		GenericMethods.sendText(wSPGrid, GridTextInput_xpath, filterText);

		//wait for page load
		GenericMethods.JSPageWait(wSPGrid);			

		//Clicking on specific Role created
		perm_restrict_Select_Click(wSPGrid,entListPage.securityProfileTableList_XPath , filterText);

		//wait for page load
		GenericMethods.JSPageWait(wSPGrid);	

	}

	//xpath path generator for popup, where Add new <> is present
	public static String saveBtnGenericPopUp(String idName) {

		//Initialise string
		String finalXpath = null;

		//generate dynamic xpath
		finalXpath = idName+"/div/div/div[3]/div/div/div[2]/div/div[1]/div[2]/div/button[2]";

		//return xpath
		return finalXpath;
	}

	//create facility - add/ edit or delete
	public static Boolean createFacility (WebDriver wFac,String facRan, String facName, String facStatus, String facPractice,
			String facRegion, String facDistrict, String mode, String pageName) {

		//Initialising form name
		AddNewEntityFormDetailsPage addEntityRecordPage = new AddNewEntityFormDetailsPage();
		EntityFormListingPage entityListing = new EntityFormListingPage();

		//setting flag to return
		Boolean createFacilityFlag = false;

		try {

			//wait for page load
			GenericMethods.JSPageWait(wFac);

			//only for Add new record mode
			if(mode=="Add") {

				//Input Name
				GenericMethods.sendText(wFac, addEntityRecordPage.entityFormDetailsPage_RANFld_XPath, facRan);

				//Input Name
				GenericMethods.sendText(wFac, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, facName);

				//input status type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFac, addEntityRecordPage.entityFormDetailsPage_status_Xpath, 
						RaisTestData.inOperation_Txt);

				//Legal basis data
				GenericMethods.sendText(wFac, addEntityRecordPage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

				///Add practice here
				//selectMultiValue(wFac, pageName, facPractice);

				//input region type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFac, addEntityRecordPage.entityFormDetailsPage_region_Xpath,facRegion);

				//input district type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFac, addEntityRecordPage.entityFormDetailsPage_district_Xpath,facDistrict);

				//give tab from element
				GenericMethods.tabfromElement(wFac, addEntityRecordPage.entityFormDetailsPage_district_Xpath);

			} else if(mode =="Edit") {

				//Input Name
				GenericMethods.sendText(wFac, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, facName);

			}					

			//wait for page load
			GenericMethods.JSPageWait(wFac);

			//Waiting until element to load and click on Add new button
			GenericMethods.elementClick(wFac, addEntityRecordPage.SaveBtn_XPath);

			//waiting for success message
			GenericMethods.waitforElement(wFac, addEntityRecordPage.form_SuccessMsg_XPath);
			GenericMethods.elementVisible(wFac, addEntityRecordPage.form_SuccessMsg_XPath);

			if (mode=="Add") {
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wFac, addEntityRecordPage.form_SuccessMsg_XPath),
						addEntityRecordPage.ADDNEWRECORD_SUCESSMSG_TXT);
			} else if (mode=="Edit"){

				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wFac, addEntityRecordPage.form_SuccessMsg_XPath),
						addEntityRecordPage.UPDATERECORD_SUCESSMSG_TXT);
			} else {						
				//verifying the success message
				Assert.assertEquals(GenericMethods.getActualTxt(wFac, addEntityRecordPage.form_SuccessMsg_XPath),
						addEntityRecordPage.DELRECORD_SUCCESSMSG_TXT);
			}

			//wait for page load
			GenericMethods.JSPageWait(wFac);

			//return flag
			return createFacilityFlag;

		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return createFacilityFlag;

		}catch (Exception  e) {

			e.printStackTrace();
			return createFacilityFlag;
		}

	}

	//select value from multiselect list for DR &FR
	public static void multiSelect_DR_FR_Page(WebDriver wMulti,String selectValue, String roleType) {	

		try {

			String wrapperName = "//*[@class='multiselect-wrapper']";
			String filterId = "//*[@id='filter']";
			String tabName = null;

			if (roleType == "Add New Permission") {

				tabName = "//*[@id='nav-tabpanel-0']//fieldset//label";

			} else if (roleType == "Add New Restrictions") {

				tabName = "//*[@id='nav-tabpanel-1']//fieldset//label";
			}

			//					//Waiting for popup to load
			GenericMethods .JSPageWait(wMulti);

			//click on multiselect class to expand
			GenericMethods.elementClick(wMulti, wrapperName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wMulti);

			//select value to select
			GenericMethods.sendText_removeblank(wMulti, filterId, selectValue);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wMulti);

			//select first value in the list
			GenericMethods.elementClick(wMulti, tabName);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wMulti);

			//click on multiselect class to collapse
			GenericMethods.elementClick(wMulti, wrapperName);					

		} catch (NoSuchElementException e) {
			e.printStackTrace();

		}	catch (Exception e) {
			e.printStackTrace();
		}  

	}

	//create facility in DR FR popup
	public static Boolean createfacility_DR_FRpopUp(WebDriver wPopup, String facRAN, String FacName, String facStatus, String facReg, String facDist) {

		//Initialising page
		AddNewEntityFormDetailsPage facEntityRecordDetails = new AddNewEntityFormDetailsPage ();
		DataRoles_FunctionalRolesPage DRFRRolesPage = new DataRoles_FunctionalRolesPage();

		try {
			//Waiting for popup to load
			GenericMethods .JSPageWait(wPopup);

			GenericMethods.sendText(wPopup, facEntityRecordDetails.entityFormDetailsPage_RANFld_XPath, facRAN); //RAN
			GenericMethods.sendText(wPopup, facEntityRecordDetails.entityFormDetailsPage_inputNameFld_XPath, FacName); //Fac Name

			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wPopup, DRFRRolesPage.facilityFrm_facilityStatus, facStatus);

			//Waiting for popup to load rais-modal-body
			GenericMethods .JSPageWait(wPopup);
			GenericMethods.elementClick(wPopup, "//*[@id='nav-tabpanel-0']//div//form//div//fieldset//div[@class='multiselect-wrapper']");
			//		GenericMethods.elementClick(wd, "//*[@class='rais-modal-dialog']//div[@class='multiselect-wrapper']");

			//Waiting for popup to load
			GenericMethods .JSPageWait(wPopup);
			//		GenericMethods.sendText_removeblank(wd, "//*[@class='mul-fieldset']//div[@id='filter']", "Dental Xray");
			//		GenericMethods.sendText(wd, "//*[@id='nav-tabpanel-0']//div//form//div//fieldset//div//fieldset//div[@id='filter']", "1Demo Practice");
			//Javascript command
			//		  JavascriptExecutor js = (JavascriptExecutor)wd;
			//		  js.executeScript("document.getElementById('filter').value='1Demo Practice';");
			//		  
			//		  js.executeScript("arguments[0].value='1Demo Practice';", wd.findElement(By.xpath("//*[@id='nav-tabpanel-0']/div/div/div[3]/div/div/div[2]/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[5]/div/div/div/div/div/fieldset/div/div[2]/div/div")));
			//		
			//		
			//		GenericMethods.JClickonElement(wd, "//*[@id='nav-tabpanel-0']/div/div/div[3]/div/div/div[2]/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[5]/div/div/div/div/div/fieldset/div/div[2]/div/div");
			//		GenericMethods.sendText(wd, "//*[@id='nav-tabpanel-0']/div/div/div[3]/div/div/div[2]/div/div[2]/form/div/div[2]/fieldset/div/div/div/div[5]/div/div/div/div/div/fieldset/div/div[2]/div/div", 
			//				"1Demo Practice");
			//		GenericMethods.sendText_removeblank(wd, "//*[@id='nav-tabpanel-0']//div//form//div//fieldset//div//fieldset//div", "Dental Xray");

			GenericMethods.elementClick(wPopup, "//*[@id='nav-tabpanel-0']//div//form//div/fieldset//div//fieldset//div//ul//li[2]//label");
			//		GenericMethods.elementClick(wd, "//*[@class='mul-fieldset']//label");
			//		GenericMethods.elementClick(wd, "//*[@class='multiselect-wrapper']//div[@class='mul-fieldset']//label");

			//Waiting for popup to load
			//			GenericMethods .JSPageWait(wPopup);
			GenericMethods.elementClick(wPopup, "//*[@id='nav-tabpanel-0']//div//form//div//fieldset//div[@class='multiselect-wrapper']");

			//Waiting for popup to load
			GenericMethods .JSPageWait(wPopup);

			//select region and district
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wPopup, DRFRRolesPage.facilityFrm_Region, facReg);

			//Waiting for popup to load
			//GenericMethods .JSPageWait(wPopup);

			//select region and district
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wPopup, DRFRRolesPage.facilityFrm_District, facDist);

			//tab from element
			GenericMethods.tabfromElement(wPopup, DRFRRolesPage.facilityFrm_District);

			//Waiting for popup to load
			//GenericMethods .JSPageWait(wPopup);

			//click on Save button
			GenericMethods.elementClick(wPopup, DRFRRolesPage.facilityPopUp_SaveBtn_Xpath);

			//			GenericMethods.JClickonElement(wPopup, DRFRRolesPage.savePermRestrict_XPath);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wPopup);

			return true;

			//		//**************************************************Add Facility HERE
			//		
			//		//click on facility link
			//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.addFacility_Xpath);
			//		
			//		//Waiting for popup to load
			//		GenericMethods .JSPageWait(wd);
			//		
			//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_RANFld_XPath, "FAC001"); //RAN
			//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_inputNameFld_XPath, "1Demo Facility"); //Fac Name
			//		
			//		//page wait
			//		GenericMethods.JSPageWait(wd);
			//		
			//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.createFacility_PracticeSelect);
			//		//page wait
			//		GenericMethods.JSPageWait(wd);
			//		
			//		GenericMethods.sendText(wd, entityRecordDetails.idFilter, "1Demo Practice"); //Practice
			//		//selecting particular item
			//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.selectedItem);
			//		GenericMethods.elementClick(wd, dataRolesfunctionalRolesPage.createFacility_PracticeSelect);
			//		
			//		
			//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_region_Xpath, "1Demo Region"); //Region
			//		GenericMethods.sendText(wd, entityRecordDetails.entityFormDetailsPage_district_Xpath, "1Demo District"); //District
			//		
			//**************************************** Add facility ends here
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}		


	}

	//search facility or WF record and intiate WF on it
	public static void basicSearchRecord(WebDriver wsin, Object searchRecord) {

		//initialise entity listing page
		EntityFormListingPage entList = new EntityFormListingPage();

		//Waiting for popup to load
		GenericMethods .JSPageWait(wsin);

		//send text
		GenericMethods.sendText(wsin, entList.inputSearchFld_Xpath, searchRecord);

		//click on search button
		GenericMethods.elementClick(wsin, entList.searchBtn_Xpath);

		//Waiting for popup to load
		GenericMethods .JSPageWait(wsin);
	}

	//initiate a Workflow
	public static void initiateWorkflow(WebDriver wInitiateWF, Object workFlowName) {

		//initialise entity listing page
		EntityFormListingPage entListing = new EntityFormListingPage();

		//Adding implicit wait
		GenericMethods .JSPageWait(wInitiateWF);

		//select radio button and initiate Workflow
		GenericMethods.elementClick(wInitiateWF, entListing.rdoBtn_Xpath);

		//click on the record and go ahead
		valueSelectfromDropDown(wInitiateWF, entListing.workflowDrpDwn_Xpath,workFlowName);

		//Click on initiate button
		GenericMethods.elementClick(wInitiateWF, entListing.workflowBtnInitiate);

		//Adding implicit wait
		GenericMethods .JSPageWait(wInitiateWF);

		//verifying the success message
		Assert.assertEquals(GenericMethods.getActualTxt(wInitiateWF, entListing.workflowInitiateSuccessMsg_XPath),
				entListing.WORKFLOW_INITIATE_SUCCESS_TXT);


		//Waiting for popup to load
		GenericMethods .JSPageWait(wInitiateWF);
	}

	//trim workflow id name
	public static String trimWorkflowid(WebDriver wTrim, String extractId) {

		//start trimming
		String idName = GenericMethods.getActualTxt(wTrim, extractId);
		idName = idName.replace("Initiated Manually - ", "");
		//final String Wfid = idName.replace("- ", "");
		final String Wfid = idName.substring(0, 11);

		//return final value
		return Wfid;     

	}

	//method to click and upload file
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);


	}

	public static void clickAndUploadFile(WebDriver wUpload,String clickOnLink, String filePath) {

		try {

			//click on element
			//click on organigram link
			GenericMethods.elementClick(wUpload,clickOnLink);			

			//			Thread.sleep(3000);
			//page wait
			GenericMethods.JSPageWait(wUpload);

			//Setting clipboard with file location
			//setClipboardData(filePath);
			//StringSelection is a class that can be used for copy and paste operations.
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			//System.out.println(filePath);

			//page wait
			GenericMethods.JSPageWait(wUpload);

			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);

			//page wait
			GenericMethods.JSPageWait(wUpload);

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

			//page wait
			GenericMethods.JSPageWait(wUpload);

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//		} catch (InterruptedException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//scroll to bottom
	public static void jScrollToBottom(WebDriver wScroll) {

		//initialise
		JavascriptExecutor js = (JavascriptExecutor) wScroll;

		//This will scroll the web page till end.		
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}

	//Followup Action WF Dataform input
	public static Boolean FA_DataFormInput(WebDriver wFA, String workFlowName) {

		//		//Waiting for popup to load
		//		GenericMethods .JSPageWait(wFA);
		//		
		//		pageName.toString()
		//		
		//		//input source location
		//		RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFA, FA_WFPage.sourceLocation_Xpath, sourceLocation);
		//		
		//		//input source status
		//		RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFA, FA_WFPage.sourceStatus_Xpath, sourceStatus);
		//		
		//		//input source date
		//		GenericMethods.sendText(wFA, FA_WFPage.sourceStatusDate_Xpath, genericDate);
		//		
		//		//input import date
		//		GenericMethods.sendText(wFA, FA_WFPage.importDate_Xpath, genericDate);
		//		
		//		//upload security plan doc
		//		RAIS_applicationSpecificMethods.clickAndUploadFile(wFA, FA_WFPage.securityPlan_Xpath,securityPlanDoc_Path);
		//		
		//		//input customs number
		//		GenericMethods.sendText(wFA, FA_WFPage.importCustomNum_Xpath, customsNumber);
		//		
		//		//input Bills of number
		//		GenericMethods.sendText(wFA, FA_WFPage.importBillLadingNum_XPath, bill_LadingNum);
		//		
		//		//input bill lading date
		//		GenericMethods.sendText(wFA, FA_WFPage.importBillLadingdate_XPath, genericDate);
		//		
		//		//click on submit button
		//		GenericMethods.elementClick(wFA, FA_WFPage.submitBtn_FAPage1_Xpath);
		//		
		//		//Waiting for popup to load
		//		//GenericMethods .JSPageWait(wd);
		//		//waiting for success message
		//		GenericMethods.waitforElement(wFA, FA_WFPage.SuccessMsg_XPath);
		//		GenericMethods.elementVisible(wFA, FA_WFPage.SuccessMsg_XPath);
		//		
		//		//verify success message
		//		String submitSuccessMsg = GenericMethods.getActualTxt(wFA, FA_WFPage.SuccessMsg_XPath);
		//		Assert.assertEquals(submitSuccessMsg,FA_WFPage.successfulMsg_Txt);


		return true;
	}

	//send text using RB class
	public static void rbSendText(WebDriver wUpload,String filePath) {

		try {


			//			Thread.sleep(3000);
			//page wait
			GenericMethods.JSPageWait(wUpload);

			//Setting clipboard with file location
			//setClipboardData(filePath);
			//StringSelection is a class that can be used for copy and paste operations.
			StringSelection stringSelection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

			System.out.println(filePath);

			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);

			robot.keyPress(KeyEvent.VK_ENTER);

			robot.keyRelease(KeyEvent.VK_ENTER);

			//Adding implicit wait
			wUpload.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS) ;

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//		} catch (InterruptedException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//multiSelect for WF
	public static void multiSelect_WFDataforms(WebDriver wmDF, String elementXpath,String textToSelect) {

		//		JavascriptExecutor jse = (JavascriptExecutor) wmDF;
		//		jse.executeScript("arguments[0].scrollIntoView(true);", 
		//				wmDF.findElement(By.xpath(elementXpath)));

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//click to expand multilookup		
		GenericMethods.elementClick(wmDF, elementXpath);

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//input text value that needs to be filtered
		GenericMethods.sendText(wmDF, elementXpath+"//input[@id='filter']", textToSelect);

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//click on first element of the list
		GenericMethods.elementClick(wmDF, elementXpath +"//ul/li[1]/label");

		//wait for multilist to expand
		//GenericMethods .JSPageWait(wmDF);

		//click to collapse the list
		GenericMethods.elementClick(wmDF, elementXpath);

		//wait for multilist to expand
		//GenericMethods .JSPageWait(wmDF);

	}

	//scroll to element ONLY
	public static void scrollToElement_Click(WebDriver wScroll, String elementposition) {

		//using js scroll
		JavascriptExecutor jse = (JavascriptExecutor) wScroll;
		jse.executeScript("arguments[0].scrollIntoView(true);", 
				wScroll.findElement(By.xpath(elementposition)));

		//click on element
		GenericMethods.elementClick(wScroll, elementposition);

		//page wait
		GenericMethods.JSPageWait(wScroll);

	}

	//scroll to elementOnly
	public static void scrollToElement(WebDriver wScroll, String elementposition) {

		//using js scroll
		JavascriptExecutor jse = (JavascriptExecutor) wScroll;
		jse.executeScript("arguments[0].scrollIntoView(true);", 
				wScroll.findElement(By.xpath(elementposition)));

		//page wait
		GenericMethods.JSPageWait(wScroll);

	}

	//scroll to page topmost
	public static void scrollToTop(WebDriver wTop) {

		//jscript to scroll to page top
		((JavascriptExecutor) wTop).executeScript("window.scrollTo(0,-document.body.scrollHeight)");

		// option 2 - ((JavascriptExecutor) wTop).executeScript("window.scrollTo(0,-250)");
	}

	//workflow data forms associations
	public static Boolean inputDataOnAssociationForm_Authorization(WebDriver wAssoDF, ArrayList<Object>formData) {

		//Initialise authorization - association page
		Workflow_AuthorizationPage wfAssociationDataForm = new Workflow_AuthorizationPage();

		try {
			//input department on DF-Associations page
			multiSelect_WFDataforms(wAssoDF, wfAssociationDataForm.department_Xpath, formData.get(2).toString());
			scrollToElement(wAssoDF, wfAssociationDataForm.sealedSource_Xpath);

			//input sealed source on DF-Associations page
			multiSelect_WFDataforms(wAssoDF, wfAssociationDataForm.sealedSource_Xpath, formData.get(3).toString());
			scrollToElement(wAssoDF, wfAssociationDataForm.unsealedSource_Xpath);

			//input unsealed source on DF-Associations page
			multiSelect_WFDataforms(wAssoDF, wfAssociationDataForm.unsealedSource_Xpath, formData.get(4).toString());
			scrollToElement(wAssoDF, wfAssociationDataForm.radGenerator_Xpath);

			//input Radiation generator source on DF-Associations page
			multiSelect_WFDataforms(wAssoDF, wfAssociationDataForm.radGenerator_Xpath, formData.get(5).toString());
			scrollToElement(wAssoDF, wfAssociationDataForm.assoEquipment_Xpath);

			if(formData.get(0).toString() != "LICISP" && formData.get(0).toString() != "LICTRANSF") {

				//input asso equipments source on DF-Associations page
				multiSelect_WFDataforms(wAssoDF, wfAssociationDataForm.assoEquipment_Xpath, formData.get(6).toString());

			}

			//generate btn xpath
			String btnClick = wfAssociationDataForm.submitBtn;

			//click on submit button
			GenericMethods.elementClick(wAssoDF, btnClick);

			//page wait
			GenericMethods .JSPageWait(wAssoDF);

			//return value
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	//workflow data form req terms
	public static Boolean inputDataOnRequestedTerms_Authorization(WebDriver wRT,String workflowName, Boolean incompleteApplication, String buttonClick) {

		//Initialise authorization - association page
		Workflow_AuthorizationPage wfRequestedTermsForm = new Workflow_AuthorizationPage();	

		//scroll down
		JavascriptExecutor jse = (JavascriptExecutor) wRT;

		try {

			if(incompleteApplication== false) {

				//Align here
				//input data based on wf name

				switch (workflowName) {


				case "Import Authorization Workflow":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);					

					//page wait
					GenericMethods .JSPageWait(wRT);

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.importAgency_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//input import agency - 3rd in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.importAgency_Xpath, RaisTestData.inputRequestedTermsDataForm.get(2).toString());
					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Export Authorization Workflow":		

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);	

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.exportAgency_Xpath);  //// Export Agency ???????

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select from drop down - 3rd in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.exportAgency_Xpath, RaisTestData.inputRequestedTermsDataForm.get(6).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Equipment Manufacturing Workflow":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateEQPCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateEQPCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Release Authorization Workflow":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.releaseDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Use Authorization Workflow":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Storage Authorization Workflow":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Isotope Production Authorization":

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedStartDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//Starting with date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.requestedEndDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Transfer Authorization Workflow":

					//page wait
					GenericMethods .JSPageWait(wRT);
					GenericMethods .JSPageWait(wRT);

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.recipientFacility_Xpath, RaisTestData.inputRequestedTermsDataForm.get(7).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");	

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.recipientDepartment_Xpath, RaisTestData.inputRequestedTermsDataForm.get(8).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");	

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");					

					//page wait
					//GenericMethods.JSPageWait(wRT);

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.transferType_Xpath, RaisTestData.inputRequestedTermsDataForm.get(9).toString());		

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");	

					//page wait
					GenericMethods.JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.transferDateCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//input import agency - 3rd in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.durationOfTransfer_Xpath, RaisTestData.inputRequestedTermsDataForm.get(10).toString());
					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				case "Transport Authorization Workflow":

					//page wait
					GenericMethods .JSPageWait(wRT);
					GenericMethods .JSPageWait(wRT);

					//package type
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.packageType_Xpath, RaisTestData.inputRequestedTermsDataForm.get(11).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");

					//page wait
					GenericMethods .JSPageWait(wRT);

					//package category
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.packageCategory_Xpath, RaisTestData.inputRequestedTermsDataForm.get(12).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");

					//page wait
					GenericMethods .JSPageWait(wRT);

					//transport index
					GenericMethods.sendText(wRT, wfRequestedTermsForm.transportIndex_Xpath, RaisTestData.inputRequestedTermsDataForm.get(10).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");

					//page wait
					GenericMethods .JSPageWait(wRT);

					//input transport mode
					multiSelect_WFDataforms(wRT, wfRequestedTermsForm.transportMode_Xpath, RaisTestData.inputRequestedTermsDataForm.get(13).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");

					//page wait
					GenericMethods .JSPageWait(wRT);

					//max radioactivity value - first item in array
					GenericMethods.sendText(wRT, wfRequestedTermsForm.maxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(0).toString());

					//select unit - second in array
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.unitMaxRadioActivity_Use_Storage_Trn_Trf_Xpath, RaisTestData.inputRequestedTermsDataForm.get(1).toString());

					//scroll down
					jse.executeScript("window.scrollBy(0,50)");

					//page wait
					GenericMethods .JSPageWait(wRT);

					//package category
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.consignorFacility_Xpath, RaisTestData.inputRequestedTermsDataForm.get(14).toString());

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.originMemo_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//Origin transport
					GenericMethods.sendText(wRT, wfRequestedTermsForm.originMemo_Xpath , RaisTestData.inputRequestedTermsDataForm.get(15).toString());

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.consigneeFacility_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//package category
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.consigneeFacility_Xpath, RaisTestData.inputRequestedTermsDataForm.get(14).toString());

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.destinationMemo_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//Origin transport
					GenericMethods.sendText(wRT, wfRequestedTermsForm.destinationMemo_Xpath , RaisTestData.inputRequestedTermsDataForm.get(16).toString());

					//scroll to element
					GenericMethods.tabfromElement(wRT, wfRequestedTermsForm.destinationMemo_Xpath);

					//scrollToElement(wRT, wfRequestedTermsForm.dateOfShipmentCalendarControl_Xpath);	

					//page wait
					GenericMethods.JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.dateOfShipmentCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//scroll to element
					//scrollToElement(wRT, wfRequestedTermsForm.dateOfReceiptCalendarControl_Xpath);	

					//page wait
					GenericMethods.JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.dateOfReceiptCalendarControl_Xpath);

					//page wait
					GenericMethods .JSPageWait(wRT);

					//select specific date
					GenericMethods.elementClick(wRT, wfRequestedTermsForm.selectSpecificDate_Xpath);

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.exclusiveYesNo_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					//package category
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.exclusiveYesNo_Xpath, RaisTestData.commonInputData.get(2).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					//scroll to element
					scrollToElement(wRT, wfRequestedTermsForm.specialArrangement_Xpath);	

					//page wait
					GenericMethods .JSPageWait(wRT);

					//package category
					valueSelectfromDropDown(wRT, wfRequestedTermsForm.specialArrangement_Xpath, RaisTestData.commonInputData.get(2).toString());

					//page wait
					GenericMethods .JSPageWait(wRT);

					break;

				default:
					break;
				}

				//common data input for all forms *******************************************
				//scroll to bottom
				jScrollToBottom(wRT);

				//upload docs - 4th in array
				clickAndUploadFile(wRT, wfRequestedTermsForm.profitLossStatement_Xpath, RaisTestData.inputRequestedTermsDataForm.get(3).toString());
				//page wait
				//GenericMethods .JSPageWait(wd);

				clickAndUploadFile(wRT, wfRequestedTermsForm.businessRegCertificate_Xpath, RaisTestData.inputRequestedTermsDataForm.get(4).toString());
				//page wait
				//GenericMethods .JSPageWait(wd);

				clickAndUploadFile(wRT, wfRequestedTermsForm.lastYearTaxCertificate_Xpath, RaisTestData.inputRequestedTermsDataForm.get(5).toString());

			}

			//scroll to element and click on button
			scrollToElement_Click(wRT, buttonClick);

			//page wait
			GenericMethods .JSPageWait(wRT);

			//return true
			return true;

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}

	//verify workflow status and click on record
	public static void verifyWorkflowStatusAndClickRecord(WebDriver wStatusClick, String status, Boolean clickNeeded) {

		//intialise page
		EntityFormListingPage wfListingPage = new EntityFormListingPage();

		//Waiting for popup to load
		GenericMethods.JSPageWait(wStatusClick);

		if(status !="") {

			//verify status of on listing page
			Assert.assertTrue((GenericMethods.getActualTxt(wStatusClick, wfListingPage.workflowStatus_Xpath).contains(status)));

		}

		if(clickNeeded == true) {

			//click on first record
			GenericMethods.elementClick(wStatusClick, wfListingPage.workFlowName_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wStatusClick);
		}
	}

	//Workflow logout and re-login with new user
	public static void workflowLogoutAndReLoginUser(WebDriver wLoginLogout, String userName, String passwordValue) {

		//initialise login page
		LoginPage loginLogoutPage = new LoginPage();

		//page refresh
		wLoginLogout.navigate().refresh();

		//page wait
		GenericMethods .JSPageWait(wLoginLogout);

		//Logout licensee user
		RAIS_applicationSpecificMethods.logout(wLoginLogout);
		System.out.println("Logout success");		

		//Start as NEW TC From below			
		//login using Regulator
		GenericMethods.loginApplication
		(wLoginLogout, loginLogoutPage.userId_XPath, userName, loginLogoutPage.pwd_XPath, 
				passwordValue, loginLogoutPage.loginBtn_XPath);

		//Waiting for popup to load
		GenericMethods .JSPageWait(wLoginLogout);

	}

	//Authorization - completeness check
	public static void authorizationCompletenessCheck(WebDriver wComp, String reviewedBy,String memoTxt, Object btnToClick, Boolean incompleteApplication, String workflowName) {

		try {
			//initialise page
			Workflow_AuthorizationPage wfAuthCompletenessCheck = new Workflow_AuthorizationPage();

			//Waiting for popup to load
			GenericMethods.JSPageWait(wComp);
			
			if (incompleteApplication == false) {
				
				//input reviewed by
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wComp, wfAuthCompletenessCheck.reviewedByCC, reviewedBy);

				//Waiting for popup to load
				GenericMethods.JSPageWait(wComp);

				//input notes
				GenericMethods.sendText(wComp, wfAuthCompletenessCheck.approvedRejectedNotes_Xpath, memoTxt);

				//Waiting for popup to load
				GenericMethods.JSPageWait(wComp);

				if(workflowName == "Isotope Production Authorization Workflow") {

					//add code for Radioactivity Aggregation Applicable
					//click on check box
					GenericMethods.elementClick(wComp, wfAuthCompletenessCheck.raaApplicable_ChkBox_Xpath);

				}
			}

			//scroll to button
			RAIS_applicationSpecificMethods.scrollToElement_Click(wComp, btnToClick.toString());

			//waiting for success message
			GenericMethods.JSPageWait(wComp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	//scrollto top and click on step tracker
	public static void scrollUpClickStepTrackerandclickBottomTab(WebDriver wLinkedP, Object stepTracker, Object linkedProcessTab) {

		try {

			//scroll to page top
			RAIS_applicationSpecificMethods.scrollToTop(wLinkedP);

			//scroll to element
			//RAIS_applicationSpecificMethods.scrollToElement_Click(wd, wfAuthorization.completenessLabelStepTracker_Xpath);

			//click on step tracker
			GenericMethods.elementClick(wLinkedP,stepTracker.toString());

			//Waiting for popup to load
			GenericMethods.JSPageWait(wLinkedP);

			//clicking on linked processes
			GenericMethods.elementClick(wLinkedP,linkedProcessTab.toString());

			//Waiting for popup to load
			GenericMethods.JSPageWait(wLinkedP);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	//Data form only button click
	public static void dataFormOnlyButtonClick(WebDriver wDFBtn, Object buttonToClick) {

		//Waiting for popup to load
		GenericMethods.JSPageWait(wDFBtn);

		//clicking on No external assement button
		RAIS_applicationSpecificMethods.scrollToElement_Click(wDFBtn, buttonToClick.toString());

		//Waiting for popup to load
		GenericMethods.JSPageWait(wDFBtn);
	}

	//Only Memo and click button
	public static void dataFormMemoAndButtonClick(WebDriver wMem, Object memoXpath, String memoText, Object buttonXpath) {

		try {
			//Waiting for popup to load
			GenericMethods.JSPageWait(wMem);

			//enter internal review remarks
			GenericMethods.sendText_removeblank(wMem, memoXpath.toString(), memoText);

			//scroll to button
			RAIS_applicationSpecificMethods.scrollToElement_Click(wMem, buttonXpath.toString());

			//Waiting for popup to load
			GenericMethods.JSPageWait(wMem);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	//inspection scope input form
	public static void inspectionScopeDataForm(WebDriver wInspection, ArrayList<Object> inspectionScopeInputData, String buttonToBeClicked) {

		//Initialise inspection scope form
		Workflow_Inspection_Parent_ChildPage inspectionScopeDF = new Workflow_Inspection_Parent_ChildPage();

		//String clickButton = inspectionScopeDF.btnPrefix_Xpath +inspectionScopeInputData.get(4).toString() + inspectionScopeDF.btnSuffix_Xpath;

		try {
			//page wait
			GenericMethods .JSPageWait(wInspection);

			//input department on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wInspection, inspectionScopeDF.department_Xpath, inspectionScopeInputData.get(2).toString());

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//using js scroll
			JavascriptExecutor jse = (JavascriptExecutor) wInspection;
			jse.executeScript("window.scrollBy(0,150)");			

			//input sealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wInspection, inspectionScopeDF.sealedSource_Xpath, inspectionScopeInputData.get(3).toString());

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//input unsealed source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wInspection, inspectionScopeDF.unsealedSource_Xpath, inspectionScopeInputData.get(4).toString());

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//input Radiation generator source on DF-Associations page
			RAIS_applicationSpecificMethods.multiSelect_WFDataforms(wInspection, inspectionScopeDF.radGenerator_Xpath, inspectionScopeInputData.get(5).toString());

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//regular or full inspection
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wInspection, inspectionScopeDF.regularInspection_Xpath, RaisTestData.commonInputData.get(1).toString());

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//regular or full inspection
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wInspection, inspectionScopeDF.fullInspection_Xpath, RaisTestData.commonInputData.get(2).toString());

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//date of inspection
			//click on date control
			GenericMethods.elementClick(wInspection, inspectionScopeDF.scheduledDateofInspection_Xpath);
			//date of inspection
			//click on date control
			//GenericMethods.elementClick(wd, wfInspection.scheduledDateofInspection_Xpath);

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//select date
			GenericMethods.elementClick(wInspection, inspectionScopeDF.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//page scroll
			jse.executeScript("window.scrollBy(0,150)");

			//Scope of inspection
			//GenericMethods.sendText_removeblank(wInspection, inspectionScopeDF.scopeOfInspection_Xpath, inspectionScopeInputData[8].toString());
			GenericMethods.sendText_removeblank(wInspection, inspectionScopeDF.scopeOfInspection_Xpath, RaisTestData.commonInputData.get(3).toString());

			//page wait
			GenericMethods .JSPageWait(wInspection);

			//click on proceed without announcement
			RAIS_applicationSpecificMethods.scrollToElement_Click(wInspection, buttonToBeClicked);

			//page wait
			GenericMethods .JSPageWait(wInspection);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Inspection findings data form
	public static void inspectionFindingsDataForm(WebDriver wInsFind, String remarksText, Object buttonName) {

		//Initialise inspection scope form
		Workflow_Inspection_Parent_ChildPage inspectionFindingsDF = new Workflow_Inspection_Parent_ChildPage();

		//String clickButton = inspectionFindingsDF.btnPrefix_Xpath +buttonName + inspectionFindingsDF.btnSuffix_Xpath;

		try {
			//date of inspection
			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wInsFind, inspectionFindingsDF.internalInspectionDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wInsFind);

			//click on date control
			GenericMethods.elementClick(wInsFind, inspectionFindingsDF.internalInspectionDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wInsFind);

			//select date
			GenericMethods.elementClick(wInsFind, inspectionFindingsDF.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wInsFind);

			//Scope of inspection
			GenericMethods.sendText_removeblank(wInsFind, inspectionFindingsDF.internalInspectionFindings_Xpath, remarksText);

			//page wait
			GenericMethods .JSPageWait(wInsFind);

			//click on proceed without announcement
			RAIS_applicationSpecificMethods.scrollToElement_Click(wInsFind, buttonName.toString());

			//page wait
			GenericMethods .JSPageWait(wInsFind);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Generic method for approval form
	public static void approvalDataForm(WebDriver wApproval, Object downLoadReportBtnXpath, Object approvedByXpath, String approvedBy,
			Object approveRejectMemoXpath, String approveRejectTxt, Boolean previouslyRejected, Object approveRejectBtn) {

		try {
			//downlod report
			GenericMethods.elementClick(wApproval, downLoadReportBtnXpath.toString());

			//page wait
			GenericMethods .JSPageWait(wApproval);

			//select reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wApproval, approvedByXpath.toString(), approvedBy);
			
			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wApproval, approveRejectMemoXpath.toString());

			//page wait
			GenericMethods .JSPageWait(wApproval);
			
			if(previouslyRejected == false) {		
				
				//input approval notes
				GenericMethods.sendText_removeblank(wApproval, approveRejectMemoXpath.toString(), approveRejectTxt);

				//page wait
				GenericMethods .JSPageWait(wApproval);
				
			}
			
			//click on approve
			RAIS_applicationSpecificMethods.scrollToElement_Click(wApproval, approveRejectBtn.toString());

			//page wait
			GenericMethods .JSPageWait(wApproval);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//R&A data form
	public static void authorizationReviewAssessmentDataForm(WebDriver wRnA, String remarksTxt, String approvedByName, Object buttonName) {

		//initialise page
		Workflow_AuthorizationPage wfAuthRADataForm = new Workflow_AuthorizationPage();

		try {
			//scroll and add remarks
			RAIS_applicationSpecificMethods.scrollToElement(wRnA, wfAuthRADataForm.authorizeRejectRemarks_Xpath);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wRnA);

			//input text
			GenericMethods.sendText_removeblank(wRnA, wfAuthRADataForm.authorizeRejectRemarks_Xpath, remarksTxt);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wRnA);

			//input reviewed by
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRnA, wfAuthRADataForm.reviewedBy_Xpath, approvedByName);

			//page wait
			GenericMethods .JSPageWait(wRnA);

			//scroll to bottom
			RAIS_applicationSpecificMethods.scrollToElement_Click(wRnA, buttonName.toString());

			//Waiting for popup to load
			GenericMethods.JSPageWait(wRnA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Payment attach invoice form
	public static void paymentAttachInvoiceReceiptDataForm(WebDriver wPay, Object dataFormName, Object fileUploadXpath) {

		//Initialize form
		Workflow_PaymentPage paymentDF = new Workflow_PaymentPage();

		try {

			//additional fields for confirm payment
			if (dataFormName.toString() == "Confirm Payment") {

				//Input amount paid
				GenericMethods.sendText(wPay, paymentDF.amountPaid_Xpath, RaisTestData.paymentInputData.get(2).toString());

				//Waiting for popup to load
				GenericMethods.JSPageWait(wPay);

				//Input amount paid mode
				GenericMethods.sendText(wPay, paymentDF.paymentMedium_Xpath, RaisTestData.paymentInputData.get(3).toString());

				//Waiting for popup to load
				GenericMethods.JSPageWait(wPay);	

				//scroll to element
				RAIS_applicationSpecificMethods.scrollToElement(wPay, fileUploadXpath.toString());

				//upload invoice
				RAIS_applicationSpecificMethods.clickAndUploadFile(wPay,fileUploadXpath.toString(), RaisTestData.paymentInputData.get(1).toString());

				//Waiting for popup to load
				GenericMethods.JSPageWait(wPay);

			} else {

				//scroll to element
				RAIS_applicationSpecificMethods.scrollToElement(wPay, fileUploadXpath.toString());

				//upload invoice
				RAIS_applicationSpecificMethods.clickAndUploadFile(wPay,fileUploadXpath.toString(), RaisTestData.paymentInputData.get(0).toString());

				//Waiting for popup to load
				GenericMethods.JSPageWait(wPay);

			}

			//scroll to bottom and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wPay, paymentDF.submitBtn);

			//Waiting for popup to load
			GenericMethods.JSPageWait(wPay);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Authorization terms and conditions
	public static void authorizationTermsAndConditions(WebDriver wTnC, String workflowName, String maxRadioActivityValue, String unitValue, String tncText, 
			Boolean certificateRejectPreviously ) {

		//initialise page
		Workflow_AuthorizationPage wfAuthTnC = new Workflow_AuthorizationPage();

		try {
			//Waiting for popup to load
			GenericMethods .JSPageWait(wTnC);

			//skip below for release and transport
			if(workflowName.equals("Transfer Authorization Workflow")) {

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wTnC, wfAuthTnC.authDateOfTransfer);

				//page wait
				GenericMethods .JSPageWait(wTnC);

				//select date
				GenericMethods.elementClick(wTnC, wfAuthTnC.selectSpecificDate_Xpath);

				//page wait
				GenericMethods .JSPageWait(wTnC);

				//send duration text
				GenericMethods.sendText(wTnC, wfAuthTnC.authTransferDuration_Xpath, RaisTestData.inputRequestedTermsDataForm.get(10).toString());


			} else if (!workflowName.equals("Release Authorization Workflow") && !workflowName.equals("Transport Authorization Workflow")) {

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wTnC, wfAuthTnC.authStartDate);

				//page wait
				GenericMethods .JSPageWait(wTnC);

				//select date
				GenericMethods.elementClick(wTnC, wfAuthTnC.selectSpecificDate_Xpath);

				//page wait
				GenericMethods .JSPageWait(wTnC);

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wTnC, wfAuthTnC.authEndDate);

				//page wait
				GenericMethods .JSPageWait(wTnC);

				//select date
				GenericMethods.elementClick(wTnC, wfAuthTnC.selectSpecificDate_Xpath);

				//page wait
				GenericMethods .JSPageWait(wTnC);

			}			

			if (workflowName == "Isotope Production Workflow") {

				//include max radio activity xpath and values here
			}

			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wTnC, wfAuthTnC.tncText_Xpath);

			if(certificateRejectPreviously == false) {
							
					//page wait
					GenericMethods .JSPageWait(wTnC);
		
					//input TNC
					GenericMethods.sendText_removeblank(wTnC, wfAuthTnC.tncText_Xpath, tncText);
		
					//page wait
					GenericMethods .JSPageWait(wTnC);
					
			}

			//scroll to bottom and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wTnC, wfAuthTnC.submitBtn);

			//page wait
			GenericMethods .JSPageWait(wTnC);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Acceptance form
	public static void acceptanceDataForm(WebDriver wAccept, String memoText, Boolean skipAccepDecline, Object buttonToClick) {

		//initialise page
		Workflow_AuthorizationPage acceptanceForm = new Workflow_AuthorizationPage();

		String clickButton = acceptanceForm.btnPrefix_Xpath +buttonToClick.toString() + acceptanceForm.btnSuffix_Xpath;

		try {
			//Waiting for popup to load
			GenericMethods .JSPageWait(wAccept);

			if (skipAccepDecline == false) {

				//click on accept check box
				GenericMethods.elementClick(wAccept, acceptanceForm.acceptTnC_ChkBox_Xpath);

				//Waiting for popup to load
				GenericMethods .JSPageWait(wAccept);
			

				//scroll to element
				RAIS_applicationSpecificMethods.scrollToElement(wAccept, acceptanceForm.remarks_Xpath);
	
				//input remarks
				GenericMethods.sendText_removeblank(wAccept, acceptanceForm.remarks_Xpath, memoText);
	
				//Waiting for popup to load
				GenericMethods .JSPageWait(wAccept);
				
			}

			//scroll and click
			RAIS_applicationSpecificMethods.scrollToElement_Click(wAccept, clickButton);

			//Waiting for popup to load
			GenericMethods .JSPageWait(wAccept);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Followupaction data form
	public static void followUpActionDataForm(WebDriver wdFollowUp, Object fwWorkflowName, String [] fwInputData, Object buttonClick) {

		//Initialise data form
		Workflow_FAPage fwDataForm = new Workflow_FAPage();

		try {
			//Waiting for popup to load
			GenericMethods .JSPageWait(wdFollowUp);

			//selection source location based on WF
			switch (fwWorkflowName.toString()) {

			case "FollowUpAction Import Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[0]);

				break;

			case "FollowUpAction Export Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[5]);

				break;

			case "FollowUpAction Transfer Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[8]);

				break;

			case "FollowUpAction Transport Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[9]);

				break;

			case "FollowUpAction Equipment Manufacturing Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[6]);

				break;

			case "FollowUpAction Isotope Production Workflow":

				//input source location
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceLocation_Xpath, fwInputData[7]);

				break;

			default:
				break;
			}

			//input source status
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wdFollowUp, fwDataForm.sourceStatus_Xpath, fwInputData[1]);

			//page wait
			GenericMethods .JSPageWait(wdFollowUp);

			//scroll to element
			//scrollToElement(wdFollowUp, fwDataForm.sourceStatusDate_Xpath);

			JavascriptExecutor jse = (JavascriptExecutor) wdFollowUp;
			jse.executeScript("window.scrollBy(0,100)");

			//date of inspection
			//click on date control
			GenericMethods.elementClick(wdFollowUp, fwDataForm.sourceStatusDate_Xpath);

			//page wait
			GenericMethods.JSPageWait(wdFollowUp);

			//select date
			GenericMethods.elementClick(wdFollowUp, fwDataForm.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wdFollowUp);

			//using switch case for different FA WF
			switch (fwWorkflowName.toString()) {

			case "FollowUpAction Import Workflow":

				//scroll to bottom
				jScrollToBottom(wdFollowUp);			

				//scroll to element
				//scrollToElement(wdFollowUp, fwDataForm.importDate_Xpath);

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wdFollowUp, fwDataForm.importDate_Xpath);

				//page wait
				GenericMethods .JSPageWait(wdFollowUp);

				//select date
				GenericMethods.elementClick(wdFollowUp, fwDataForm.selectSpecificDate_Xpath);

				//page wait
				//GenericMethods .JSPageWait(wdFollowUp);

				//scroll to element
				//scrollToElement(wdFollowUp, fwDataForm.securityPlan_Xpath);

				//upload security plan doc
				RAIS_applicationSpecificMethods.clickAndUploadFile(wdFollowUp, fwDataForm.securityPlan_Xpath,fwInputData[2]);

				//input customs number
				GenericMethods.sendText(wdFollowUp, fwDataForm.importCustomNum_Xpath, fwInputData[3]);

				//input Bills of number
				GenericMethods.sendText(wdFollowUp, fwDataForm.importBillLadingNum_XPath, fwInputData[4]);

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wdFollowUp, fwDataForm.importBillLadingdate_XPath);

				//page wait
				GenericMethods .JSPageWait(wdFollowUp);

				//select date
				GenericMethods.elementClick(wdFollowUp, fwDataForm.selectSpecificDate_Xpath);				

				break;

			case "FollowUpAction Export Workflow":

				//scroll to bottom
				jScrollToBottom(wdFollowUp);			

				//scroll to element
				//scrollToElement(wdFollowUp, fwDataForm.importDate_Xpath);

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wdFollowUp, fwDataForm.exportDate_Xpath);

				//page wait
				GenericMethods .JSPageWait(wdFollowUp);

				//select date
				GenericMethods.elementClick(wdFollowUp, fwDataForm.selectSpecificDate_Xpath);

				//page wait
				//GenericMethods .JSPageWait(wdFollowUp);

				//scroll to element
				//scrollToElement(wdFollowUp, fwDataForm.securityPlan_Xpath);

				//upload security plan doc
				RAIS_applicationSpecificMethods.clickAndUploadFile(wdFollowUp, fwDataForm.securityPlan_Xpath,fwInputData[2]);

				//click on source returned
				GenericMethods.elementClick(wdFollowUp, fwDataForm.receipientCountryApproval_ChkBox_Xpath);				

				//input customs number
				GenericMethods.sendText(wdFollowUp, fwDataForm.importCustomNum_Xpath, fwInputData[3]);

				break;

			case "FollowUpAction Transfer Workflow":

				//click on source returned
				GenericMethods.elementClick(wdFollowUp, fwDataForm.sourceReturnedToParent_ChkBox_Xpath);

				//scroll to bottom
				RAIS_applicationSpecificMethods.jScrollToBottom(wdFollowUp);

				//date of inspection
				//click on date control
				GenericMethods.elementClick(wdFollowUp, fwDataForm.returnDate_XPath);

				//page wait
				GenericMethods .JSPageWait(wdFollowUp);

				//select date
				GenericMethods.elementClick(wdFollowUp, fwDataForm.selectSpecificDate_Xpath);

				break;

			default:
				break;
			}			

			//page wait
			GenericMethods .JSPageWait(wdFollowUp);			

			//click on submit button
			GenericMethods.elementClick(wdFollowUp, buttonClick.toString());

			//Waiting for popup to load
			GenericMethods .JSPageWait(wdFollowUp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//FA - approval form
	public static void followUpActionApprovalDataForm(WebDriver wFADF, String remarksTxt, String reviewedBy, Object buttonToClick ) {

		//Initialise data form
		Workflow_FAPage fwApprovalDataForm = new Workflow_FAPage();

		try {

			//Waiting for popup to load
			GenericMethods.JSPageWait(wFADF);

			//date of inspection
			//scroll to element
			RAIS_applicationSpecificMethods.scrollToElement(wFADF, fwApprovalDataForm.approvalFrmStatusDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wFADF);

			//click on date control
			GenericMethods.elementClick(wFADF, fwApprovalDataForm.approvalFrmStatusDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wFADF);

			//select date
			GenericMethods.elementClick(wFADF, fwApprovalDataForm.selectSpecificDate_Xpath);

			//page wait
			GenericMethods .JSPageWait(wFADF);

			//input remarks on approval form
			GenericMethods.sendText(wFADF, fwApprovalDataForm.approvalFrmRemarks_Xpath, remarksTxt);

			//select approved by role by regulator
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(wFADF, fwApprovalDataForm.approvalFrmApprovedBy_Xpath, reviewedBy);

			//click on submit button
			GenericMethods.elementClick(wFADF, buttonToClick.toString());

			//page wait
			GenericMethods .JSPageWait(wFADF);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//identify user and followup WF name based on parent name
	public static ArrayList<Object> getUserFollowupWFName( String workfName) {

		ArrayList<Object> listValue = new ArrayList<>();

		try {
			switch (workfName) {
			case "Equipment Manufacturing Workflow":

				listValue.add("LICEQP");
				listValue.add(RaisTestData.faWFList[4]);

				break;

			case "Isotope Production Authorization":

				listValue.add("LICISP");
				listValue.add(RaisTestData.faWFList[5]);

				break;

			case "Transfer Authorization Workflow":

				listValue.add("LICTRANSF");
				listValue.add(RaisTestData.faWFList[2]);

				break;

			case "Transport Authorization Workflow":

				listValue.add("LICTRNSP");
				listValue.add(RaisTestData.faWFList[3]);

				break;

			case "Import Authorization Workflow":

				listValue.add("LICIMP");
				listValue.add(RaisTestData.faWFList[0]);

				break;

			case "Export Authorization Workflow":

				listValue.add("LICEXP");
				listValue.add(RaisTestData.faWFList[1]);

				break;


			default:
				break;
			}					

			return listValue;
			//return Arrays.asList(userId, faWF_Name);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	//identify user and facility and association form input data based on parent name
	public static ArrayList<Object> getDetailsAuthWorkflow( String workfName) {

		ArrayList <Object>inputDataForWorkflow = new ArrayList<Object>();
		//initialise counter i
		int i;

		try {
			switch (workfName) {
			case "Equipment Manufacturing Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_equipmentMWF.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_equipmentMWF.get(i));

				}

				break;

			case "Isotope Production Authorization":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_isotopeProdWF.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_isotopeProdWF.get(i));

				}

				break;

			case "Transfer Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_transfer.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_transfer.get(i));

				}

				break;

			case "Transport Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_transport.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_transport.get(i));

				}

				break;

			case "Use Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_use.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_use.get(i));

				}

				break;

			case "Import Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_import.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_import.get(i));

				}				

				break;

			case "Storage Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_storage.size();i++) {

					//add elements one by one
					inputDataForWorkflow.add(RaisTestData.associationsInputData_storage.get(i));

				}				

				break;

			case "Export Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_export.size();i++) {

					//add elements one by one
					//inputDataForWorkflow.add(RaisTestData.associationsInputData_export[i]);
					inputDataForWorkflow.add(RaisTestData.associationsInputData_export.get(i));

				}

				break;

			case "Release Authorization Workflow":

				//extract user name, facility and dependent data					
				for(i=0;i<RaisTestData.associationsInputData_release.size();i++) {

					//add elements one by one
					//inputDataForWorkflow.add(RaisTestData.associationsInputData_export[i]);
					inputDataForWorkflow.add(RaisTestData.associationsInputData_release.get(i));

				}

				break;


			default:
				break;
			}					

			return inputDataForWorkflow;
			//return Arrays.asList(userId, faWF_Name);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	//user creation screen multi select
	public static void multiSelect_UserCreationFR(WebDriver wmDF, String elementXpath,String textToSelect) {

		//		JavascriptExecutor jse = (JavascriptExecutor) wmDF;
		//		jse.executeScript("arguments[0].scrollIntoView(true);", 
		//				wmDF.findElement(By.xpath(elementXpath)));

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//click to expand multilookup		
		GenericMethods.elementClick(wmDF, elementXpath);

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//input text value that needs to be filtered
		GenericMethods.sendText(wmDF, elementXpath+"//input[@id='filter']", textToSelect);

		//wait for multilist to expand
		GenericMethods .JSPageWait(wmDF);

		//click on first element of the list
		GenericMethods.elementClick(wmDF, elementXpath +"//ul/li[1]/label");

		//wait for multilist to expand
		//GenericMethods .JSPageWait(wmDF);

		//click to collapse the list
		GenericMethods.elementClick(wmDF, elementXpath);

		//wait for multilist to expand
		//GenericMethods .JSPageWait(wmDF);

	}
}


