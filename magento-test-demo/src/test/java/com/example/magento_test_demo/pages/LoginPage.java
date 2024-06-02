package com.example.magento_test_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

/*
 * Page object model for Login page:
 * https://magento.softwaretestingboard.com/customer/account/login/referer/[some random string]
 * 
 * Author: Paul Taniguchi
 */


public class LoginPage extends LoadableComponent<LoginPage>
{
	public WebDriver driver;
	// page factory for the Login Username field
	@FindBy(css = "input#email.input-text")
	private WebElement userNameField;
	// page factory for the Login password field
	@FindBy(css = "input#pass.input-text")
	private WebElement userPassField;
	// page factory for the Login button
	@FindBy(css = "button#send2.action.login.primary")
	private WebElement loginButton;
	
	/**
	 * constructor
	 */
	public LoginPage(WebDriver driverFromTest) 
	{
		driver = driverFromTest;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load() 
	{
		// load is ok to be empty
	}
	
	@Override
	protected void isLoaded() throws Error
	/*
	 * check page title to test if it's the correct page
	 */
	{
		Assert.assertEquals(getTitle(),"Customer Login","Login Page not loaded");
	}
	
	public String getTitle() 
	{
		return driver.getTitle();
	}
	
	/*
	 * method for entering username / password into Login fields
	 * 
	 */
	public void enterCredentials(String username, String password)
	{
		// clear fields
		userNameField.clear();
		userPassField.clear();
		
		// enter creds into fields
		userNameField.sendKeys(username);
		userPassField.sendKeys(password);
		
		loginButton.click();
	}
}
