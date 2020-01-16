package com.mtp.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.internal.BaseAction;

// TODO: Auto-generated Javadoc
/**
 * Created By : Adeeb.
 */

public class PageCollection 
{
	/** The Log. */
	private static Logger Log = LogManager.getLogger(PageCollection.class.getName());

	/** The driver. */
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/** The login page. */
	private LoginPage loginPage;
	
	private HomePage homePage;
	
	private TasksPage tasksPage;
	
	private AccountsPage accountsPage;
	
	private ProjectsPage projectsPage;
	
	

		
	/**
	 * Instantiates a new page collection.
	 *
	 * @param driver the driver
	 */
	public PageCollection(ThreadLocal<WebDriver> driver) 
	{
		this.driver = driver;
		Log.info("Initialized Page Collection Class");
	}

	/**
	 * Gets the login page.
	 *
	 * @return the login page
	 */
	

	public LoginPage getLoginPage() 
	{
		return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
	}
	
	public HomePage getHomePage() 
	{
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
	public TasksPage getTasksPage() 
	{
		return (tasksPage == null) ? tasksPage = new TasksPage(driver) : tasksPage;
	}
	
	public AccountsPage getAccountsPage() 
	{
		return (accountsPage == null) ? accountsPage = new AccountsPage(driver) : accountsPage;
	}
	
	public ProjectsPage getProjectsPage() 
	{
		return (projectsPage == null) ? projectsPage = new ProjectsPage(driver) : projectsPage;
	}
	
	
}