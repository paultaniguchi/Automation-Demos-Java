package com.example.magento_test_demo.pages;

import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.example.magento_test_demo.pages.SearchResultPage;


/**
 * Page Object Model for https://magento.softwaretestingboard.com
 *
 */
public class HomePage extends LoadableComponent<HomePage>
{
	public WebDriver driver;
	// page factory for the search box
	@FindBy(css = "input#search")	
	private WebElement searchBox;
	
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
		System.out.println("load");
		driver.get("https://magento.softwaretestingboard.com");
	}
	
	@Override
	protected void isLoaded() throws Error
	/*
	 * check if it's the correct page
	 */
	{
		Assert.assertEquals(getTitle(),"Home Page" );
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
}
