package com.mtp.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.mtp.qa.base.BasePage;

public class ProjectsPage extends BasePage {

	/** The Log. */
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());

	/** Project in Projects */
	private By projectName = By
			.xpath("//div[@class='widget-list'][1]//div[@class='wdg-project-head']//a[@class='ember-view']");

	public ProjectsPage(ThreadLocal<WebDriver> driver) {
		super(driver);
		Log.info("Initializing login Page Objects");
		PageFactory.initElements(driver.get(), projectName);
		// TODO Auto-generated constructor stub
	}

	public void clickOnParticularProject() {
		waitForElementToBecomeVisible(projectName, longWait);
		clickAndWait(projectName, longWait);
	}

}
