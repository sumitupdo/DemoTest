package com.mtp.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mtp.qa.base.BasePage;

public class AccountsPage extends BasePage {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());

	/** Account in accounts */
	private By accountName = By.xpath("//div[@class='widget-list'][1]//p[1]//a[@class='ember-view']");

	public AccountsPage(ThreadLocal<WebDriver> driver) {
		super(driver);
		this.driver = driver;
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), accountName);
		// TODO Auto-generated constructor stub
	}

	public void clickOnParticularAccount() {
		waitForElementToBecomeVisible(accountName, longWait);
		clickAndWait(accountName, longWait);
	}

}
