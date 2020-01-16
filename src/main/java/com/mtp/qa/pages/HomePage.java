/*
 *
 */
package com.mtp.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.mtp.qa.base.BasePage;
import com.mtp.qa.utils.TestUtils;


public class HomePage extends BasePage {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());

	/** The Three dot button */
	private By threeDotBttn = By.xpath("//i[@class='mdi mdi-menu']");

	/** The Tasks Tab */
	private By tasksTab = By.xpath("//span[text()='Tasks']");

	/** The Accounts Tab */
	private By accountsTab = By.xpath("//span[text()='Accounts']");

	/** The Projects Tab */
	private By projectsTab = By.xpath("//span[text()='Projects']");

	/**
	 * Instantiates a new login page.
	 *
	 * @param driver the driver
	 */
	// initialize elements
	public HomePage(ThreadLocal<WebDriver> driver) {
		super(driver);
		this.driver = driver;
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), threeDotBttn);
	}

	public void verifyThreeDotBttnAfterLogin() {
		waitForElementToBecomeVisible(threeDotBttn, longWait);
		Assert.assertTrue(isElementPresent(threeDotBttn), "Login unsuccessfull");
	}

	public void clickOnAccountsTab() {
		waitForElementToBecomeVisible(accountsTab, longWait);
		clickAndWait(accountsTab, longWait);
	}

	public void clickOnTasksTab() {
		waitForElementToBecomeVisible(tasksTab, longWait);
		clickAndWait(tasksTab, longWait);
	}

}