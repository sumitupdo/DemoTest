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


public class LoginPage extends BasePage {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());

	/** The email field. */
	private By emailField = By.xpath("//input[@type='email']");

	/** The password field. */
	private By passwordField = By.xpath("//input[@type='password']");

	/** The Login button. */
	private By loginBttn = By.xpath("//button[@type='submit']");

	
	/**
	 * Instantiates a new login page.
	 *
	 * @param driver the driver
	 */
	// initialize elements
	public LoginPage(ThreadLocal<WebDriver> driver) {

		super(driver);
		this.driver = driver;
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), emailField);
	}

//add all actions method here
	/**
	 * Login into UI.
	 * @throws InterruptedException
	 */
	
	public void logInToarchibus(String username, String password) throws InterruptedException {
		gotoURL(TestUtils.getProperty("url"));
//		String username = TestUtils.decodeText(TestUtils.getProperty("engineer.ai.cloudops.username"));
//		String uipassword = TestUtils.decodeText(TestUtils.getProperty("engineer.ai.cloudops.password"));
		waitForElementToBecomeVisible(loginBttn, longWait);
		setText(emailField, username);
		setText(passwordField, password);
		clickAndWait(loginBttn, longWait);
		Log.info("Login Successfull");

	}

	

}