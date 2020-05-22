package commonfunction;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import constants.RaisTestData;
import pageLocators_Elements.RAIS.AddNewAttributePage;
import pageLocators_Elements.RAIS.AddNewEntityFormDetailsPage;
import pageLocators_Elements.RAIS.AddNewEntityPage;
import pageLocators_Elements.RAIS.AddNewPermRestrictionsPage;
import pageLocators_Elements.RAIS.AddNewRolePage;
import pageLocators_Elements.RAIS.AddNewUserDetailsPage;
import pageLocators_Elements.RAIS.DashboardPage;
import pageLocators_Elements.RAIS.DataRoles_FunctionalRolesPage;
import pageLocators_Elements.RAIS.EntityFormListingPage;
import pageLocators_Elements.RAIS.EntityListingPage;
import pageLocators_Elements.RAIS.UserListPage;


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
	public static void Generic_Menu_subMenu_Click(WebDriver wdMenu, String MainMenuName, String subMenu, int colposition, String childMenu) {	

		boolean flag = true;

		try {

			//Creating list of webelements returned from left pane				
			List<WebElement> menuList = wdMenu.findElements(By.xpath("//div//ul[@id='main-menu']/li"));
			//List<WebElement> rolesList = mainleftpaneRoleXpath.findElements(By.xpath("//*[@id=\"datarole\"]/div/div[1]/div/div/div[2]/ul/li"));

			//Iterating the role list webelement until match is found
			for (WebElement headerName : menuList) {				

				System.out.println(headerName);
				//locate specific role with text
				if ((flag==true) && headerName.findElement(By.tagName("a")).getText().equals(MainMenuName)) {

					System.out.println(headerName.findElement(By.tagName("a")).getText());
					headerName.click();

					GenericMethods.pageLoadWait(600);

					List<WebElement> subMenuList = headerName.findElements(By.xpath("//div[@class='sub-menu']//div[@class='column']["+colposition+"]"));

					for (WebElement subHeaderName : subMenuList) {

						if ((flag==true) && subHeaderName.findElement(By.tagName("a")).getText().equals(subMenu)) {

							//subHeaderName.click();
							System.out.println(subHeaderName.findElement(By.tagName("a")).getText());

							List<WebElement> childMenuList = subHeaderName.findElements(By.xpath("//ul[@class='sub-menu-column']//li"));

							for (WebElement childName : childMenuList) {

								if ((flag==true) && childName.findElement(By.tagName("a")).getText().equals(childMenu)) {

									System.out.println(childName.findElement(By.tagName("a")).getText());

									WebElement childElement = childName.findElement(By.tagName("a"));

									JavascriptExecutor jscrollClick = (JavascriptExecutor)wdMenu;

									jscrollClick.executeScript("arguments[0].scrollIntoView()", childElement);

									//Clicking on specific child
									//									childName.click();
									childElement.click();

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
			String publishNav1, String publishNav2) {

		EntityListingPage entityListingPage = new EntityListingPage();
		AddNewEntityPage addNewEntityPage = new AddNewEntityPage();


		try {
			//wait for page load
			GenericMethods.pageLoadWait(1000);

			//Clicking on Officers menu
			Generic_Menu_subMenu_Click(went, RaisTestData.AdministrationMainMenu, RaisTestData.CustomizeSubMenu, 3, RaisTestData.businessEntityList[66]);

			//********************************Add new Entity starts here
			//Waiting for button to load and click
			GenericMethods.waitforElement(went, entityListingPage.addNewEntityBtn_XPath);
			GenericMethods.elementClickable(went, entityListingPage.addNewEntityBtn_XPath);
			GenericMethods.elementClick(went, entityListingPage.addNewEntityBtn_XPath);

			//page wait
			GenericMethods.JSPageWait(went);

			//clicking on entity - save button
			GenericMethods.waitforElement(went, addNewEntityPage.cancelBtn_XPath);
			GenericMethods.elementClickable(went, addNewEntityPage.cancelBtn_XPath);

			//GenericMethods.elementClick(wd, addEntityPage.SaveBtn_XPath);
			//input entity internal name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_internalNameTxtBox_XPath, internalName);

			//input entity Description name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_DescTxtBox_XPath,
					RaisTestData.Entity_DescriptionData);

			//input entity Singular name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_SingTxtBox_XPath, singularName);

			//input entity Plural name
			GenericMethods.sendText(went, addNewEntityPage.addNewEntity_PluTxtBox_XPath, pluralName);

			//input entity group name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(went, addNewEntityPage.addNewEntity_grpDropDown_XPath, entityGroup);

			//input entity role name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(went, addNewEntityPage.addNewEntity_roleDropDown_XPath, entityRole);

			//input entity publish navigation name
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(went,addNewEntityPage.addNewEntity_pubNavi1DropDown_XPath, publishNav1);
			RAIS_applicationSpecificMethods.valueSelectfromDropDown(went,addNewEntityPage.addNewEntity_pubNavi2DropDown_XPath, publishNav2);

			//clicking on entity - save button
			GenericMethods.waitforElement(went, addNewEntityPage.SaveBtn_XPath);
			GenericMethods.elementClickable(went, addNewEntityPage.SaveBtn_XPath);
			GenericMethods.elementClick(went, addNewEntityPage.SaveBtn_XPath);

			//page wait
			GenericMethods.JSPageWait(went);

			//waiting for success message
			GenericMethods.waitforElement(went, addNewEntityPage.addnewEntity_SuccessMsg_XPath);
			GenericMethods.elementVisible(went, addNewEntityPage.addnewEntity_SuccessMsg_XPath);

			//verifying the success message
			Assert.assertEquals(GenericMethods.getActualTxt(went, addNewEntityPage.addnewEntity_SuccessMsg_XPath),
					addNewEntityPage.ADDNEWENT_SUCESSMSG_TXT);

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
					1, RaisTestData.functionalRole);

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
	public static boolean createEntityRecord(WebDriver wRecordEntity, String businessEntityName, String officerNameinput, String AuthTypeinput, String emailInput) {

		//Initialising form name
		AddNewEntityFormDetailsPage addEntityRecordPage = new AddNewEntityFormDetailsPage();
		EntityFormListingPage entityListing = new EntityFormListingPage();

		try {

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wRecordEntity, entityListing.addNewBtn_XPath);
			GenericMethods.elementClickable(wRecordEntity, entityListing.addNewBtn_XPath);
			GenericMethods.elementClick(wRecordEntity, entityListing.addNewBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);

			switch (businessEntityName) {

			case "Authorities and Organizations":

				//Input Name
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, AuthTypeinput);

				//Legal basis data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_legalBasisFld_Xpath, RaisTestData.LEGAL_BASIS_DATA);

				//Chair person data
				GenericMethods.sendText(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath, RaisTestData.CHAIRPERSON_DATA);
				GenericMethods.tabfromElement(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_ChairPrsonFld_Xpath);


				break;


			case "Officers":

				//input officer name
				GenericMethods.sendText_removeblank(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_inputNameFld_XPath, officerNameinput);

				//wait for page load
				GenericMethods.JSPageWait(wRecordEntity);

				//input Gender type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_genderFld_Xpath, RaisTestData.GENDER_FEMALE_DATA);

				//input Authority type
				RAIS_applicationSpecificMethods.valueSelectfromDropDown(wRecordEntity, addEntityRecordPage.entityFormDetailsPage_authorityFld_Xpath, AuthTypeinput);

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

			default:
				break;
			}
			//Waiting until element to load and click on Add new button
			GenericMethods.waitforElement(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);
			GenericMethods.elementClickable(wRecordEntity, addEntityRecordPage.cancelBtn_XPath);
			GenericMethods.elementClick(wRecordEntity, addEntityRecordPage.SaveBtn_XPath);

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);
			GenericMethods.pageLoadWait(1000);

			//waiting for link to load and then click
			GenericMethods.waitforElement(wRecordEntity, entityListing.addNewBtn_XPath);
			GenericMethods.elementClickable(wRecordEntity, entityListing.addNewBtn_XPath);			

			//wait for page load
			GenericMethods.JSPageWait(wRecordEntity);
			GenericMethods.pageLoadWait(1000);


			return true;
		}catch (NoSuchElementException  noElement) {

			noElement.printStackTrace();
			return false;

		}catch (Exception  e) {

			e.printStackTrace();
			return false;
		}

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
			if (userCategory == "Primary" && userType == "External") {
				
				//External user
				GenericMethods.elementClick(wUser, createUserPage.addnewUser_ExternalrdobtnAuth_XPath);
				
			}else if (userCategory == "Primary" && userType == "Internal") {
				
				//External user
				GenericMethods.elementClick(wUser, createUserPage.addnewUser_InternalrdobtnAuth_XPath);
			}

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
			// TODO: handle exception
		}
		
	}
}
