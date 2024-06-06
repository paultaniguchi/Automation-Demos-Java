package com.example.magento_test_demo.pages;

import org.testng.Assert;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.example.magento_test_demo.pages.SearchResultPage;
import com.example.magento_test_demo.pages.LoginPage;


/*
 * Page Object Model for home page 
 * https://magento.softwaretestingboard.com
 *
 * Author: Paul Taniguchi
 */
public class HomePage extends LoadableComponent<HomePage>
{
	public WebDriver driver;
	// page factory for the search box
	@FindBy(css = "input#search")	
	private WebElement searchBox;
	// page factory for the Sign In link
	@FindBy(css = "li.authorization-link")
	private WebElement signInLink;
	// page factory for the currently signed in user
	@FindBy(css = "span.logged-in")
	private WebElement loggedInUser;
	
	/**
	 * constructor
	 */
	public HomePage(WebDriver driverFromTest) 
	{
		driver = driverFromTest;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load()
	/*
	 * Load the Magento home page
	 */
	{
		driver.get("https://magento.softwaretestingboard.com");
	}
	
	@Override
	protected void isLoaded() throws Error
	/*
	 * check page title to test if it's the correct page
	 */
	{
		Assert.assertEquals(getTitle(),"Home Page","Home Page not loaded");
	}
	
	public String getTitle() 
	{
		return driver.getTitle();
	}
	
	/*
	 *  Parameter String search_term
	 *  
	 *  enter search term into Search box
	*/
	public SearchResultPage submitTextInSearch(String search_term) 
	{
		// fill in the Search box
		searchBox.clear();
		searchBox.sendKeys(search_term);
		searchBox.submit();
		
		return new SearchResultPage(driver).get();
	}
	
	/*
	 * 
	 *  go to the Login page
	 */
	public LoginPage goToLoginPage()
	{
		signInLink.click();
		return new LoginPage(driver).get();
	}
	
	/*
	 *  get signed in user from the Welcome message
	 *  returns username in the Home Page welcome message
	 *  otherwise it returns empty string
	 */
	public String getCurrentUser(String expTextCurrentUser)
	{
		// wait for user to be logged in
		try 
		{
			Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.textToBePresentInElement(loggedInUser, expTextCurrentUser));
		}
		// return empty string if time out before welcome message appears
		catch (org.openqa.selenium.TimeoutException e)
		{
			return "";
		}
		
		return loggedInUser.getText();
	}
}
