package com.mtp.qa.uitests;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.mtp.qa.actions.ApiActions;
import com.mtp.qa.actions.BaseActions;
import com.mtp.qa.actions.UIActions;
import com.mtp.qa.pages.PageCollection;
import com.mtp.qa.utils.TestUtils;
/*import com.microsoft.tfs.core.TFSTeamProjectCollection;
import com.microsoft.tfs.core.clients.workitem.CoreFieldReferenceNames;
import com.microsoft.tfs.core.clients.workitem.WorkItem;
import com.microsoft.tfs.core.clients.workitem.WorkItemClient;
import com.microsoft.tfs.core.clients.workitem.project.Project;
import com.microsoft.tfs.core.clients.workitem.project.ProjectCollection;
import com.microsoft.tfs.core.clients.workitem.wittype.WorkItemType;
import com.microsoft.tfs.core.config.ConnectionAdvisor;
import com.microsoft.tfs.core.httpclient.Credentials;
import com.microsoft.tfs.core.httpclient.UsernamePasswordCredentials;*/
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	/** The Log. */
	private static Logger Log = LogManager.getLogger(BaseTest.class.getName());
	
	protected static String downloadFolder = System.getProperty("user.dir") + "/" + "resources" + "/" + "downloadedFiles";


	/** The driver. */
	// Use the application driver
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/** The pages. */
	PageCollection pages;

	/** The web actions. */
	UIActions uiActions;

	/** The actions. */
	BaseActions actions = new BaseActions();

	/** The api actions. */
	ApiActions apiActions = new ApiActions();

	/**
	 * Instantiates a new base test.
	 */

	public BaseTest() 
	{
		Log.info("Getting WebDriver Settings");
	}

	/**
	 * Creates the chrome driver.
	 */
	private void createChromeDriver() 
	{
		try {
			Log.info("Found Chrome as Browser");
			// set browser preferences to ignore browser notification pop up and to add
			// console errors
			
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("download.default_directory", downloadFolder);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);

			Log.info("Getting Chromedriver exe from resources");

			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "/" + "resources" + "/" + "chromedriver.exe");

			Log.info("Launching Chrome and Maximizing it");
			driver.set(new ChromeDriver(options));

			driver.get().manage().window().maximize();

		   } catch (Exception e) 
		      {
			   Log.info("unable to create chromedriver");
			   e.printStackTrace();
		      }
	}

	/**
	 * Driversetup.
	 */
	@BeforeMethod(alwaysRun = true)
	public void driversetup() {
		String browser = TestUtils.getProperty("selenium.browser");

		switch (browser) 
		{
		 case "chrome":
			createChromeDriver();
			break;

		 default:
			Log.info("browser not supported");
			break;
		}
	}

	/**
	 * Initialize pages.
	 */
	@BeforeMethod(alwaysRun = true)
	public void initializePages() {
		pages = new PageCollection(driver);
		uiActions = new UIActions(driver);
	}

	/**
	 * Teardown.
	 *
	 * @param result the result
	 */
	/*
	 * @AfterMethod(alwaysRun = true) public void teardown(ITestResult result) { //
	 * if test case failed then capture screenshot and console logs if
	 * (result.getStatus() == ITestResult.FAILURE) {
	 * uiActions.captureScreenShotInCaseOfFailure(result);
	 * uiActions.printconsoleerrors();
	 * 
	 * } Log.info("Closing Webdriver threads"); if
	 * (TestUtils.getProperty("selenium.closeBrowser").equalsIgnoreCase("true")) {
	 * driver.get().quit(); }
	 * 
	 * }
	 */
	
	
	//WebDriver driver;
		public static ExtentReports extent;
		private static final String BREAK_LINE = "</br>";
		public static ExtentTest test;
		
		/*
		 * protected String userName; protected String password; protected String
		 * browserType; //private WebDriver driver; protected String applicationUrl;
		 * 
		 * // pages object initialization protected LoginPage loginPage; protected
		 * DashboardPage dashBoardPage;
		 * 
		 * enum DriverType { Firefox, IE, Chrome }
		 */

		
		/*
		 * public BaseTest(String browser) { this.browserType = browser; }
		 */

		@BeforeSuite
		public void before() {
			extent = new ExtentReports("target/surefire-reports/ExtentReport.html", true);
		}


		@AfterMethod(alwaysRun = true)
		public void afterMainMethod(ITestResult result) throws Exception
		{
			if (result.getStatus() == ITestResult.FAILURE) 
			{
				captureScreenshot(result);
				String methodname= result.getMethod().getMethodName();
				String classname= getClass().getName();
				System.out.println("CLASSNME: "+classname);
				String summary=	"ERROR IN: "+classname+ ":: "+methodname+ ":: "+result.getThrowable().getMessage().split("\\r?\\n")[0];
				System.out.println(summary);
				//createWorkItemInTFS(summary);
				test.log(LogStatus.FAIL, summary);
				driver.get().quit();
			//	test.log(LogStatus.FAIL, result.getThrowable().getMessage());
				extent.endTest(test);
				extent.flush();	
			}
			
			else{
				//driver.quit();
				driver.get().quit();
				//test.log(LogStatus.FAIL, result.getThrowable().getMessage());
				extent.endTest(test);
				extent.flush();
				
			    }
		}	
			
		@AfterSuite
		public void tearDownSuite() {
			//reporter.endReport();
			//driver.quit();
			extent.flush();
			extent.close();
		}
		
		public void createTicketInTFS() throws URISyntaxException {
	    	
		//	String serverUrl= "http://24.37.17.59:8080/tfs";
		//	String username= "Adeeb";
	    	String domain= "http://24.37.17.59:8080";
	    	String password= "fz;1234";
			
		//	URIBuilder serverUrl = new URIBuilder("http://24.37.17.59:8080/tfs");
			
//			Credentials username;
//			ConnectionAdvisor domain;
//		//	String password;
			URI serverUrl = new URI("http://24.37.17.59:8080/tfs");
			
			 //Credentials credentials = new DefaultNTCredentials();
		/*
		 * Credentials credentials = new UsernamePasswordCredentials("Adeeb",
		 * "fz;1234"); //URI serverUrl = URI.parse ("http://example.com/query")
		 * //TFSTeamProjectCollection tpc
		 * =TFSTeamProjectCollection.GetTeamProjectCollection(new
		 * URI("http://server:8080/tfs/Collection"));
		 * 
		 * TFSTeamProjectCollection tpc = new TFSTeamProjectCollection(serverUrl,
		 * credentials); ProjectCollection coll = tpc.getWorkItemClient().getProjects();
		 * Project project = coll.get("Test");
		 * 
		 * WorkItemClient workItemClient = project.getWorkItemClient();
		 * 
		 * // Find the work item type matching the specified name. WorkItemType
		 * bugWorkItemType = project.getWorkItemTypes().get("Bug");
		 * 
		 * // Create a new work item of the specified type. WorkItem newWorkItem =
		 * project.getWorkItemClient().newWorkItem(bugWorkItemType);
		 * 
		 * // Set the title on the work item.
		 * newWorkItem.setTitle("Automation Bug Report");
		 * 
		 * // Add a comment as part of the change
		 * newWorkItem.getFields().getField(CoreFieldReferenceNames.HISTORY).setValue(
		 * "<p>Created automatically by a sample</p>");
		 * 
		 * // Save the new work item to the server. newWorkItem.save();
		 * 
		 * System.out.println("Work item " + newWorkItem.getID() +
		 * " successfully created");
		 */
	    	}
		public void captureScreenshot(ITestResult result) throws IOException, InterruptedException {
			try {
				String screenshotName = TestUtils.getFileName(result.getName());
				File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
				String path = TestUtils.getPath();
				String screen = path + "/screenshots/" + screenshotName + ".png";
				File screenshotLocation = new File(screen);
				FileUtils.copyFile(screenshot, screenshotLocation);
				Thread.sleep(2000);
				String image= test.addScreenCapture(screen);
				test.log(LogStatus.FAIL, screenshotName, image);

				Reporter.log(
						"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		
		 public void createWorkItemInTFS(String workitemTitle) {
				
//		    	System.setProperty("com.microsoft.tfs.jni.native.base-directory", TfSFolder);
//		        final TFSTeamProjectCollection tpc = SnippetSettings.connectToTFS();
//
//		        final Project project = tpc.getWorkItemClient().getProjects().get(SnippetSettings.PROJECT_NAME);
//
//		        // Find the work item type matching the specified name.
//		        final WorkItemType bugWorkItemType = project.getWorkItemTypes().get("Bug"); //$NON-NLS-1$
//
//		        // Create a new work item of the specified type.
//		        final WorkItem newWorkItem = project.getWorkItemClient().newWorkItem(bugWorkItemType);
//
//		        // Set the title on the work item.
//		        newWorkItem.setTitle(workitemTitle); //$NON-NLS-1$
//
//		        // Add a comment as part of the change
//		        newWorkItem.getFields().getField(CoreFieldReferenceNames.HISTORY).setValue(
//		            "<p>Created automatically by a Test Script</p>"); //$NON-NLS-1$
//
//		        // Save the new work item to the server.
//		        newWorkItem.save();
//
//		        System.out.println("Work item " + newWorkItem.getID() + " successfully created"); //$NON-NLS-1$ //$NON-NLS-2$
		    }
              		
}           