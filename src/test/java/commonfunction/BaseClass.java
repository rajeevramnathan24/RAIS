package commonfunction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseClass {

	//CONFIG FILE PATH
		//public static final String PROJ_FOLDER_PATH = "C:\\workspace1\\com.apache.RAIS\\src\\test\\java\\com\\apache\\RAIS\\resources\\";
		public static final String PROJ_FOLDER_PATH = "E:\\IAEA - RAIS\\Automation\\RAIS+Automation\\src\\test\\java\\com\\apache\\RAIS\\resources\\";

	public static final String CONFIG_FILE_PATH = "config.properties";

	public static String CONFIG_FILE_IO_ERROR_MSG="CONFIG_FILE_IO_ERROR_MSG";
	public static String CONFIG_FILE_EXCEPTION_ERROR_MSG ="CONFIG_FILE_EXCEPTION_ERROR_MSG"; 


	//Definning webdriver
	public WebDriver wd;

	//Chrome Browser constants
	public static String BrowserChrome="BrowserChrome";
	public static String chromeDriver ="chromeDriver";
	public static String exeChromeDriver="exeChromeDriver";
	public static String executeFromCH = "executeFromCH";

	public static String ChromeDisableInfo = "ChromeDisableInfo";
	public static String ChromePref_CreEnbServ = "ChromePref_CreEnbServ";
	public static String ChromePwdMgrEnabled = "ChromePwdMgrEnabled";
	public static String ChromePref = "ChromePref";
	public static String ChromeDisableAutoExtension ="ChromeDisableAutoExtension"; 
	public static String ChromeExcludeSwitches ="ChromeExcludeSwitches"; 
	public static String ChromeEnableAutomation ="ChromeEnableAutomation"; 

	//FF Browser constants
	public static String BrowserFF="BrowserFF";
	public static String FFDriver ="FFDriver";
	public static String exeFFDriver="exeFFDriver";
	public static String FFmarionette = "FFmarionette";
	public static String executeFromFF = "executeFromFF";

	//Edge Browser constants
	public static String BrowserEdge ="BrowserEdge";
	public static String EdgeDriver ="EdgeDriver";
	public static String exeEdgeDriver="exeEdgeDriver";

	//Error in closing browser
	public static String errClosingBrowser = "errClosingBrowser";

	//Extent report constants
	public static String resultHtmlReportPath = "resultHtmlReportPath";
	
	public static String rptSysInfo_OS = "rptSysInfo_OS";
	public static String rptSysInfo_OSValue = "rptSysInfo_OSValue";
	public static String rptSysInfo_Browser = "rptSysInfo_Browser";
	public static String rptSysInfo_BrowserValue = "rptSysInfo_BrowserValue";
	
	public static String rpt_Theme = "rpt_Theme";
	public static String rptTimeStamp = "rptTimeStamp";

	//Initialise extent report for adding test case name
	public static ExtentHtmlReporter htmlReporter;
	public static String testpackName = null;
	public static ExtentReports extentReportTC = new ExtentReports();
	public static ExtentTest tcNameDesc;
	public static String TestName = null;
	public static String TestDesc = null;
	public static int totalPasscount = 0;
	public static int totalFailcount = 0;

	//DateFormat
	public static String dateFormatType ="dateFormatType";

	//pass, fail, skip values
	public static String TC_PASSED = "TC_PASSED";
	public static String TC_FAILED = "TC_FAILED";
	public static String TC_SKIPPED = "TC_SKIPPED";

	//Used to count total test case pass-fail-skip
	public static String TC_STATUS_PASS = "TC_STATUS_PASS";
	public static String TC_STATUS_FAIL = "TC_STATUS_FAIL";
	public static String TC_STATUS_SKIP = "TC_STATUS_SKIP";


	public static String appUrl ="";
	public static String userName ="";
	public static String password ="";


	///RAIS CONSTANTS reading fromConfig file
	public static String CONFIG_FILE_RAIS_URL ="CONFIG_FILE_RAIS_URL";
	public static String CONFIG_FILE_RAIS_USERNAME ="CONFIG_FILE_RAIS_USERNAME";
	public static String CONFIG_FILE_RAIS_PASSWORD ="CONFIG_FILE_RAIS_PASSWORD";
	public static String CONFIG_FILE_RAIS_TEST_ENV2 ="";

	//Project specfic constants reading from config file
	public static String RAIS_rptNameHtml = "RAIS_rptNameHtml";
	public static String RAIS_rpt_Title_Name = "RAIS_rpt_Title_Name";
	
	
	//Loading config file
	public static void LoadConfigfile() {

		try {
			// Initialixzing property file and loading it
			Properties prop = new Properties();

			//input file path
			InputStream configFile = new FileInputStream(PROJ_FOLDER_PATH + CONFIG_FILE_PATH);
			
			//load config file into property file
			prop.load(configFile);

			// Read config file and print
			System.out.println(prop.getProperty(CONFIG_FILE_RAIS_URL));
			System.out.println(prop.getProperty(CONFIG_FILE_RAIS_USERNAME));
			System.out.println(prop.getProperty(CONFIG_FILE_RAIS_PASSWORD));

			//Assigning url, id, pwd to public strings
			appUrl = prop.getProperty(CONFIG_FILE_RAIS_URL);
			userName = prop.getProperty(CONFIG_FILE_RAIS_USERNAME);
			password = prop.getProperty(CONFIG_FILE_RAIS_PASSWORD);

			//Config file errors
			CONFIG_FILE_IO_ERROR_MSG = prop.getProperty(CONFIG_FILE_IO_ERROR_MSG);
			CONFIG_FILE_EXCEPTION_ERROR_MSG = prop.getProperty(CONFIG_FILE_EXCEPTION_ERROR_MSG);

			//Loading Chrome browser attributes
			BrowserChrome=prop.getProperty(BrowserChrome);
			chromeDriver =prop.getProperty(chromeDriver);
			exeChromeDriver =prop.getProperty(exeChromeDriver);
			executeFromCH =prop.getProperty(executeFromCH);
			ChromeDisableInfo =prop.getProperty(ChromeDisableInfo);
			ChromePref_CreEnbServ =prop.getProperty(ChromePref_CreEnbServ);
			ChromePwdMgrEnabled =prop.getProperty(ChromePwdMgrEnabled);
			ChromePref =prop.getProperty(ChromePref);
			ChromeDisableAutoExtension =prop.getProperty(ChromeDisableAutoExtension);
			ChromeExcludeSwitches =prop.getProperty(ChromeExcludeSwitches);
			ChromeEnableAutomation =prop.getProperty(ChromeEnableAutomation);

			//Loading FF browser attributes
			BrowserFF=prop.getProperty(BrowserFF);
			FFDriver=prop.getProperty(FFDriver);
			exeFFDriver=prop.getProperty(exeFFDriver);
			FFmarionette=prop.getProperty(FFmarionette);
			executeFromFF=prop.getProperty(executeFromFF);

			//Loading Edge browser attributes
			BrowserEdge=prop.getProperty(BrowserEdge);
			EdgeDriver=prop.getProperty(EdgeDriver);
			exeEdgeDriver=prop.getProperty(exeEdgeDriver);

			//Error in closing browser
			errClosingBrowser=prop.getProperty(errClosingBrowser);

			//Date format
			dateFormatType=prop.getProperty(dateFormatType);

			//Extent report constants
			//pass, fail, skip values
			TC_PASSED=prop.getProperty(TC_PASSED);
			TC_FAILED=prop.getProperty(TC_FAILED);
			TC_SKIPPED=prop.getProperty(TC_SKIPPED);

			//Used to count total test case pass-fail-skip
			TC_STATUS_PASS=prop.getProperty(TC_STATUS_PASS);
			TC_STATUS_FAIL=prop.getProperty(TC_STATUS_FAIL);
			TC_STATUS_SKIP=prop.getProperty(TC_STATUS_SKIP);

			//Extent report constants
			resultHtmlReportPath=prop.getProperty(resultHtmlReportPath);
			
			//Project specific values
			RAIS_rptNameHtml=prop.getProperty(RAIS_rptNameHtml);
			RAIS_rpt_Title_Name=prop.getProperty(RAIS_rpt_Title_Name);
			
			rptSysInfo_OS=prop.getProperty(rptSysInfo_OS);
			rptSysInfo_OSValue=prop.getProperty(rptSysInfo_OSValue);
			rptSysInfo_Browser=prop.getProperty(rptSysInfo_Browser);
			rptSysInfo_BrowserValue=prop.getProperty(rptSysInfo_BrowserValue);
			rpt_Theme=prop.getProperty(rpt_Theme);
			rptTimeStamp=prop.getProperty(rptTimeStamp);


		} catch (IOException e) {
			System.out.println(CONFIG_FILE_IO_ERROR_MSG);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(CONFIG_FILE_EXCEPTION_ERROR_MSG);
			e.printStackTrace();
		}

	}

	public static void SetUpExtentReport(String browserName, String testpack) {

		try {
			
			testpackName = testpack;
			//Setting report file
			htmlReporter = new ExtentHtmlReporter(resultHtmlReportPath+testpackName+browserName+RAIS_rptNameHtml);
			//creating instance of extent report to set details of OS, browser
			//extentReportTC = new ExtentReports();
			extentReportTC.attachReporter(htmlReporter);
			extentReportTC.setSystemInfo(rptSysInfo_OS, rptSysInfo_OSValue);
			extentReportTC.setSystemInfo(rptSysInfo_Browser, browserName);
			htmlReporter.config().setDocumentTitle(RAIS_rpt_Title_Name);
			htmlReporter.config().setReportName(RAIS_rpt_Title_Name + browserName);
			//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setTimeStampFormat(rptTimeStamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void SetUpExtentReport_TestPack(String browserName, String testType) {

		try {
			//Setting report file
			
			testpackName = testType;
			
			htmlReporter = new ExtentHtmlReporter(resultHtmlReportPath+browserName+testpackName+RAIS_rptNameHtml);
			
			//creating instance of extent report to set details of OS, browser
			extentReportTC = new ExtentReports();
			extentReportTC.attachReporter(htmlReporter);
			extentReportTC.setSystemInfo(rptSysInfo_OS, rptSysInfo_OSValue);
			extentReportTC.setSystemInfo(rptSysInfo_Browser, browserName);
			htmlReporter.config().setDocumentTitle(RAIS_rpt_Title_Name);
			htmlReporter.config().setReportName(RAIS_rpt_Title_Name + browserName);
			//htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTheme(Theme.DARK);
			htmlReporter.config().setTimeStampFormat(rptTimeStamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void SettingRptTestName_TestDesc(String tcName, String tcDesc) {
		//Assigning Test name and description
		TestName = tcName;
		TestDesc = tcDesc;

		//Adding Test case name to extent report
		tcNameDesc = extentReportTC.createTest(TestName,TestDesc );
	}

	public static void closeExtentReport( ) {

		//closing report and flush all details
		extentReportTC.flush();
	}

	//Method NOT USED ANYMORE
	public static int verifyPassFailOLD(ITestResult result, ExtentTest testCase) {

		//initialise test case count
		int testCaseCount = 0;

		try {

			//Verify result status
			if (result.getStatus() == ITestResult.FAILURE) {

				//Below condition is when test case fails
				testCase.log(Status.FAIL,
						MarkupHelper.createLabel(TC_FAILED + result.getThrowable(), ExtentColor.RED));

				//Old code ****************
				//testCase.log(Status.FAIL, MarkupHelper.createLabel(this.testCase+ GeneralConstants.TC_FAILED, ExtentColor.RED));

				//				testCase.log(Status.FAIL,
				//						MarkupHelper.createLabel(result.getTestName() + GeneralConstants.TC_FAILED, ExtentColor.RED));
				//				testCase.log(Status.INFO,
				//						MarkupHelper.createLabel(result.getTestName() + GeneralConstants.TC_FAILED, ExtentColor.RED));
				//				testCase.fail(result.getThrowable());
				// end here
				//

				//tcName.log(Status.FAIL, MarkupHelper.createLabel(GeneralConstants.TC_FAILED + result.getThrowable() , ExtentColor.RED));

				//Increment testcase count
				testCaseCount = testCaseCount + 1;

				//returning value
				return testCaseCount;

				//Below condition is for test case pass
			} else if (result.getStatus() == ITestResult.SUCCESS) {

				//Log status as pass in report and highlight in green
				testCase.log(Status.PASS,
						MarkupHelper.createLabel(result.getName() + TC_PASSED, ExtentColor.GREEN));

				//Increment testcase count
				testCaseCount = testCaseCount + 1;

				//returning value
				return testCaseCount;

			} else {
				testCase.log(Status.SKIP,
						MarkupHelper.createLabel(result.getName() + TC_SKIPPED, ExtentColor.ORANGE));
				testCase.skip(result.getThrowable());

				//Increment testcase count
				testCaseCount = testCaseCount + 1;

				//returning value
				return testCaseCount;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			return testCaseCount;
		}
	}

	//Method to verify pass-fail of test case and update status in extent report
	public static String verifyPassFail(ITestResult result, String testCaseName) {

		//initialise test case count
		String testCaseStatus = null;

		try {

			//Verify result status
			if (result.getStatus() == ITestResult.FAILURE) {

				//Below condition is when test case fails
				tcNameDesc.log(Status.FAIL,
						MarkupHelper.createLabel(testCaseName , ExtentColor.RED));
				tcNameDesc.log(Status.FAIL,
						MarkupHelper.createLabel(TC_FAILED + result.getThrowable(), ExtentColor.RED));


				//count pass test cases
				totalFailcount = totalFailcount +1;

				//Set return value to Fail
				testCaseStatus = TC_STATUS_FAIL;

				//returning value
				return testCaseStatus;

				//Below condition is for test case pass
			} else if (result.getStatus() == ITestResult.SUCCESS) {

				//Log status as pass in report and highlight in green
				tcNameDesc.log(Status.PASS,
						MarkupHelper.createLabel(testCaseName + TC_PASSED, ExtentColor.GREEN));

				//Increment testcase count
				testCaseStatus = TC_STATUS_PASS;

				//count pass test cases
				totalPasscount = totalPasscount +1;

				//returning value
				return testCaseStatus;			


			} else {
				tcNameDesc.log(Status.SKIP,
						MarkupHelper.createLabel(testCaseName + TC_SKIPPED, ExtentColor.ORANGE));
				tcNameDesc.skip(result.getThrowable());

				//Increment testcase count
				testCaseStatus = TC_STATUS_SKIP;

				//returning value
				return testCaseStatus;
			} 
		} catch (Exception e) {
			e.printStackTrace();
			return testCaseStatus;
		}
	}

	public void BrowserSetup_LaunchNew(String browserType)  {

		try {
			// Passing the browser name to the below and then initialising the driver
			if (browserType.equalsIgnoreCase("FireFox")) {

				//print in console execution from FF
				System.out.println(executeFromFF);

				//Setting system property of FF
				System.setProperty(FFDriver,PROJ_FOLDER_PATH + exeFFDriver);

				// ignore the code below

				//DesiredCapabilities capabilities = new DesiredCapabilities();
				//capabilities.setCapability("marionette", true); 
				//FirefoxOptions FFoptions = new FirefoxOptions(); 
				//FFoptions.merge(capabilities);

				//FirefoxOptions options = new FirefoxOptions();
				//options.setProfile(new FirefoxProfile());
				//wd = new FirefoxDriver(options);
				//*******************************

				//System.setProperty(GeneralConstants.FFDriver, GeneralConstants.ResourcesPath+GeneralConstants.exeFFDriver);

				//Set desired capabilities
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability(FFmarionette, true);
				FirefoxOptions FFoptions = new FirefoxOptions();
				FFoptions.merge(capabilities);

				wd = new FirefoxDriver(FFoptions);
				//*******************************
			} else if (browserType.equalsIgnoreCase("Chrome")) {

				//Setting chrome driver properties
				System.setProperty(chromeDriver,
						PROJ_FOLDER_PATH + exeChromeDriver);

				System.out.println(executeFromCH);
				//ChromeOptions options = new ChromeOptions();
				//options.ad.addArguments("--disable-infobars");
				//ch.setExperimentalOption("useAutomationExtension", false);
				
				//ChromeOptions ch = new ChromeOptions();
				
				//options.addArguments("disable-infobars");
//				options.setExperimentalOption(ChromeEnableAutomation, false);
//				options.setExperimentalOption(ChromeDisableAutoExtension, false);
//				options.setExperimentalOption(ChromeDisableInfo, false);
				
				//Disabling browser level messages
				//ch.setExperimentalOption(ChromeDisableAutoExtension, false);
//				ch.setExperimentalOption(ChromeExcludeSwitches,
//						Collections.singletonList(ChromeEnableAutomation));
//				ChromeOptions options = new ChromeOptions();
//			    options.addArguments("disable-infobars");
			    
//				Map<String, Object> prefs = new HashMap<String, Object>();
//				prefs.put(ChromePref_CreEnbServ, false);
//				prefs.put(ChromePwdMgrEnabled, false);
//				prefs.put("disable-infobars", false);
//				prefs.put("useAutomationExtension", false);
				
				//options.setExperimentalOption(ChromePref, prefs);
				//wd = new ChromeDriver(options);
				wd = new ChromeDriver();
				
				//System.out.println(wd);

			} else if (browserType.equalsIgnoreCase("IE")) {

				//System.setProperty(ProjConstants.IEWebDriver, ProjConstants.IEWebDriverPath);

				//wi = new InternetExplorerDriver();			
			}
			//Maximize browser window and launch
			wd.manage().window().maximize();
			wd.get(appUrl);
			wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		}catch (NoSuchElementException NoSuchElement) {
			NoSuchElement.printStackTrace();

		}catch (WebDriverException wex) {
			wex.printStackTrace();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void BrowserClose() throws Exception {

		try {
			// Close browser and quit
			//wd.close();
			wd.quit();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(errClosingBrowser);
		}

	}

	public  WebElement scrollUntilElement(WebElement tillElement ) throws Exception {

		try {
			WebElement element = wd.findElement(By.id("id_of_element"));
			((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500);
			return tillElement;
		} catch (Exception e) {
			e.printStackTrace();
			return tillElement;
		}


	}

	//Email feature
	public void sendMailReport(int chPass, int chFail, int ffPass, int ffFail)  {

		try {

			// new mail initialsing
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "outlook.office365.com");
			props.put("mail.smtp.ssl.trust", "outlook.office365.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("test@e-zest.com", "Nasa$2018");
				}
			});



			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("test@e-zest.com", "RAIS QA Team"));

			System.out.println("RAIS QA Team");

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajeev.ramnathan@e-zest.in"));
			//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ProjConstants.ToMail));

			message.setSubject("IAEA || RIAS Test Automation Report for "+  ExecutionDay());
			//String htmlText2 = "Test Subject";


			String browserNameCh = "Chrome";
			String browserNameFF = "FireFox";

			int totalCHcount = chPass + chFail;
			int totalFFcount = ffPass + ffFail;

			//			message.setText
			//
			//			("Hi," + "\n\n Automated test execution report for Beta2 environment is available in the below location :"
			//
			//			// Result link for Chrome
			//					+ "\n\n Chrome Browser Results\n http://14.141.104.8:9090/" + "ENV NAME1"
			//					+ "/" + "chrome" + "/Mobelisk_Test_Result.html" + "\n\n" +
			//
			//					// Result link for FF
			//					"FireFox Browser Results\n http://14.141.104.8:9090/" + "ENV NAME2"
			//					+ "/" + "FF" + "/Mobelisk_Test_Result.html" + "\n\n"
			//
			//					+ "Thanks," + "\n"+ "Mobelisk QA");

			String MailBody = "<p><span style=\"font-family: calibri, sans-serif;\">Hi All,</p>\r\n" + 

					//Below code is for Chrome
					"<p>Summary of Tests Executed in " +
					browserNameCh +
					":</p>\r\n" + 
					"<table style=\"height: 83px; width: 193px;\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"color: #000000; background-color: #00ff00;\"><strong>Total Pass Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong><span style=\"background-color: #00ff00;\">" +
					chPass +
					"</span></strong></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"background-color: #ff9900;\"><strong>Total Fail Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><span style=\"background-color: #ff9900;\"><strong>" +
					chFail +
					"</strong></span></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><strong>Total Test cases</strong></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong>" +
					totalCHcount +
					"</strong></td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" + 

					//
					//Below code is for FF
					"<p>Summary of Tests Executed in " +
					browserNameFF +
					":</p>\r\n" + 
					"<table style=\"height: 83px; width: 193px;\">\r\n" + 
					"<tbody>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"color: #000000; background-color: #00ff00;\"><strong>Total Pass Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong><span style=\"background-color: #00ff00;\">" +
					ffPass +
					"</span></strong></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><span style=\"background-color: #ff9900;\"><strong>Total Fail Test cases</strong></span></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><span style=\"background-color: #ff9900;\"><strong>" +
					ffFail +
					"</strong></span></td>\r\n" + 
					"</tr>\r\n" + 
					"<tr>\r\n" + 
					"<td style=\"width: 160px;\"><strong>Total Test cases</strong></td>\r\n" + 
					"<td style=\"width: 25px; text-align: center;\"><strong>" +
					totalFFcount +
					"</strong></td>\r\n" + 
					"</tr>\r\n" + 
					"</tbody>\r\n" + 
					"</table>\r\n" +

					//Text of detailed reports link
					"<p>Detailed Test Results are available in&nbsp;the location:&nbsp;</p>\r\n" + 
					"<p><a href=\"https://Test1.com\">https://Test1.com</a>&nbsp;</p>\r\n" + 
					"<p><a href=\"https://Test1.com\">https://Test1.com</a></p>\r\n" + 
					"<p>&nbsp;Thanks,<br />QA Team</p>\r\n" + 
					"<p>&nbsp;</p>\r\n" + 
					"<p>&nbsp;</span></p>";

			message.setContent(MailBody ,"text/html" );

			//			String htmlText2 = "<b><H1><font color=#93cff2>RAJEEV</font></H1></b>\n";
			//			
			//			message.setText(htmlText2);


			//addColor("\n Mobelisk QA", Color.BLUE);

			//+addColor(TestString, Color.BLUE);
			//message.setContent("<h1>This is actual message</h1>","text/html" );

			Transport.send(message);

			System.out.println("Mail sent");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Method returns current day in string
	//Method to add current Day, it returns current day.
	public static String ExecutionDay() {



		//Creating an object of calendar instance
		//Calendar cal = Calendar.getInstance();

		//get current time
		//SimpleDateFormat sdf = new SimpleDateFormat("HHmm");



		//Formatting current time
		//final String CurrentTime = sdf.format(cal.getTime()) ;

		//Initialising execution date
		String ExecutionDate = null;

		try {
			// Create object of SimpleDateFormat class and decide the format dd-MMM-YYYY
			DateFormat dateFormat = new SimpleDateFormat(dateFormatType);

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			ExecutionDate = dateFormat.format(date); //+CurrentTime;

			// Returning the current day
			return ExecutionDate;

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

	public static boolean teststatus( ) {
		return true;
	}

}
