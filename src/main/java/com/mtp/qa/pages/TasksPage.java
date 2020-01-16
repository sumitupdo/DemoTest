package com.mtp.qa.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.mtp.qa.base.BasePage;
import com.mtp.qa.utils.TestUtils;

public class TasksPage extends BasePage {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());

	/** The Task Text Area Field. */
	private By textArea = By.id("exampleTextarea");
	
	/** Add Responsible icon */
	private By addResponsibleIcon = By.xpath("//i[@title='Responsible']");
	
	/** Assigned to me button*/
	private By assignedToMeBttn = By.xpath("//div[@class='popover-body']//input[@value='Assigned to me']");
	
	/** Add label tag*/
	private By addLabelTag = By.xpath("//i[@title='Tags']");
	
	/** select label tag*/
	private By selectLabelOption = By.xpath("//div[@class='popover-body']//option[1]");
	
	/** Add tick icon*/
	private By addTickIcon = By.xpath("//div[@class='popover-body']//i[@class='fa fa-check']");
	
	/** Add due date*/
	private By dueDateIcon = By.xpath("//i[@title='Due date']");
	
	/** Today date*/
	private By todayDate = By.xpath("//div[@class='datepicker-days']//th[@class='today'][contains(text(),'Today')]");
	
	/** Priority icon*/
	private By priorityIcon = By.xpath("//i[@title='Priority']");
	
	/** Priority text box*/
	private By priorityTxtBx = By.xpath("//div[@class='popover-body']//input[@id='priority_input_new']");
	
	/** Create task right tick icon*/
	private By createTaskTickIcon = By.xpath("//i[@class=' mdi mdi-check']");
	
	/** Created task */
	private By createdTask = By.xpath("//a[@class='pointer']");
	
	/** The Delete button */
	private By deleteBttn = By.xpath("//button[text()='Delete']");
	
	/** The OK button */
	private By OkBttn = By.xpath("//button[text()='OK']");
	

	public TasksPage(ThreadLocal<WebDriver> driver) {
		super(driver);
		this.driver = driver;
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), textArea);
	}
	
	public void verifySussessMessageAfterDeleteTheTask(String msg) {
		Assert.assertTrue(isTextPresentOnPage(msg), "Task not created successfully");
	}
	
	public void deleteTask() {
		waitForElementToBecomeVisible(deleteBttn, longWait);
		clickAndWait(deleteBttn, longWait);	
		waitForElementToBecomeVisible(OkBttn, longWait);
		clickAndWait(OkBttn, longWait);	
	}
	
	public void clickOnCreatedTask() {
		waitForElementToBecomeVisible(createdTask, longWait);
		clickAndWait(createdTask, longWait);			
	}
	
	public void verifyAfterCreateTheTask(String createdTask) {
		TestUtils.sleep(7);
		Assert.assertTrue(isTextPresentOnPage(createdTask), "Task not created successfully");
	}
	
	public void clickOnCreateTaskRightTickIcon() {
		waitForElementToBecomeVisible(createTaskTickIcon, longWait);
		clickAndWait(createTaskTickIcon, longWait);	
	}
	
	public void selectPriority(String priority) {
		waitForElementToBecomeVisible(priorityIcon, longWait);
		clickAndWait(priorityIcon, longWait);	
		waitForElementToBecomeVisible(priorityTxtBx, longWait);
		setText(priorityTxtBx, priority, longWait);
		clickAndWait(addTickIcon, longWait);
	}
	
	public void selectDueDate() {
		waitForElementToBecomeVisible(dueDateIcon, longWait);
		clickAndWait(dueDateIcon, longWait);	
		waitForElementToBecomeVisible(todayDate, longWait);
		clickAndWait(todayDate, longWait);
	}
	
	public void selectLableTag() {
		waitForElementToBecomeVisible(addLabelTag, longWait);
		clickAndWait(addLabelTag, longWait);	
		waitForElementToBecomeVisible(selectLabelOption, longWait);
		clickAndWait(selectLabelOption, longWait);
		clickAndWait(addTickIcon, longWait);
	}
	
	public void enterTaskDetail(String task) {
		waitForElementToBecomeVisible(textArea, longWait);
		setText(textArea, task, longWait);
	}
	
	public void selectResponsible() {
		waitForElementToBecomeVisible(addResponsibleIcon, longWait);
		clickAndWait(addResponsibleIcon, longWait);	
		waitForElementToBecomeVisible(assignedToMeBttn, longWait);
		clickAndWait(assignedToMeBttn, longWait);	
	}

}
