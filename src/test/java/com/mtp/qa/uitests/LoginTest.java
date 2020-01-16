package com.mtp.qa.uitests;

import java.net.URISyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.mtp.qa.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginTest.class.getName());

	/**
	 * Test Login Flow.
	 * Verify home page after login
	 */
	@Test(description = "To verify login to MY Task Panel application")
	public void loginToMyTaskPanelApplicationTest() throws URISyntaxException, InterruptedException 
	{	
		test = extent.startTest("Login to My Task Panel Test");
		test.log(LogStatus.INFO, "Entered user name, password and click on Login button");
		pages.getLoginPage().logInToarchibus("mtpadmin1@yopmail.com", "Updoer.17");
		test.log(LogStatus.INFO, "Verify three dot button on home");
		pages.getHomePage().verifyThreeDotBttnAfterLogin();
		test.log(LogStatus.PASS, "Login to My Task Panel Test");
	}	
	
	/**
	 * Test task creation Flow.
	 * GO to particular account in accounts 
	 * Go to particular project in projects 
	 * Enter task detail
	 * Select Responsible for task
	 * Select label tag
	 * Select due date for the task
	 * Enter priority for the task
	 * Complete the creation of the task
	 * Verify newly created task after create the task.
	 */
	
	@Test(description = "To verify Task Creation")
	public void createTaskTest() throws URISyntaxException, InterruptedException 
	{	
		test = extent.startTest("Create Task Test");
		test.log(LogStatus.INFO, "Entered user name, password and click on Login button");
		pages.getLoginPage().logInToarchibus("mtpadmin1@yopmail.com", "Updoer.17");
		
		test.log(LogStatus.INFO, "Click on Accounts tab");
		pages.getHomePage().clickOnAccountsTab();
		
		test.log(LogStatus.INFO, "Click on particular Account");
		pages.getAccountsPage().clickOnParticularAccount();
		
		test.log(LogStatus.INFO, "Click on particular project");
		pages.getProjectsPage().clickOnParticularProject();
		
		test.log(LogStatus.INFO, "Enter any new task in text field");
		pages.getTasksPage().enterTaskDetail("New Task Entered");
		
		test.log(LogStatus.INFO, "Select Responsible for task");
		pages.getTasksPage().selectResponsible();
		
		test.log(LogStatus.INFO, "Select label tag");
		pages.getTasksPage().selectLableTag();
		
		test.log(LogStatus.INFO, "Select due date");
		pages.getTasksPage().selectDueDate();
		
		test.log(LogStatus.INFO, "Entered priority");
		pages.getTasksPage().selectPriority("1");
		
		test.log(LogStatus.INFO, "Click on right tick icon to complete the creation of the task");
		pages.getTasksPage().clickOnCreateTaskRightTickIcon();
		TestUtils.sleep(4);
		
		test.log(LogStatus.INFO, "Verify newly created task after create the task");
		pages.getTasksPage().verifyAfterCreateTheTask("New Task Entered");
		test.log(LogStatus.PASS, "Create New Task Test");
	}	
	
	/**
	 * Test Delete task Flow.
	 */
	
	@Test(description = "To verify Delete Task")
	public void deleteTaskTest() throws URISyntaxException, InterruptedException 
	{	
		test = extent.startTest("Delete Task Test");
		test.log(LogStatus.INFO, "Entered user name, password and click on Login button");
		pages.getLoginPage().logInToarchibus("mtpadmin1@yopmail.com", "Updoer.17");
		
		test.log(LogStatus.INFO, "Click on accounts tab");
		pages.getHomePage().clickOnAccountsTab();
		
		test.log(LogStatus.INFO, "Click on particular account");
		pages.getAccountsPage().clickOnParticularAccount();
		
		test.log(LogStatus.INFO, "Click on particular project");
		pages.getProjectsPage().clickOnParticularProject();
		
		test.log(LogStatus.INFO, "Click on particular task");
		pages.getTasksPage().clickOnCreatedTask();
		
		test.log(LogStatus.INFO, "Click on delete button and accept the popup to delete the task");
		pages.getTasksPage().deleteTask();
		test.log(LogStatus.PASS, "Delete Task Test");
		
//		test.log(LogStatus.INFO, "Verify success message after delete the task");
//		pages.getTasksPage().verifySussessMessageAfterDeleteTheTask("Task deleted successfully");
//		test.log(LogStatus.PASS, "Delete Task Test");
	}	
	
}