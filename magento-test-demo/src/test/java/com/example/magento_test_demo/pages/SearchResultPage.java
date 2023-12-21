package com.example.magento_test_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/*
 * Page object model for search result page:
 * https://magento.softwaretestingboard.com/catalogsearch/result/?q=[product being searched]
 * 
 * Author: Paul Taniguchi
 */

public class SearchResultPage extends LoadableComponent<SearchResultPage>{

	public WebDriver driver;
	// page factory for product links in search results
	// use strong as parent to avoid picking up hidden a in the side Compare Product
	// on  Firefox
	@FindBy(css = "strong.product.name.product-item-name>a.product-item-link")
	private List<WebElement> productTilesList;
	
	/**
	 * constructor using page factory
	 */
	public SearchResultPage(WebDriver driverFromTest) 
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
	 *  check page title to see if this is correct page
	 */
	{
		boolean isTitleCorrect;
		
		// wait until Search Results page load 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		try 
		{
			isTitleCorrect = wait.until(ExpectedConditions.titleContains("Search results")); 
		}
		catch (org.openqa.selenium.TimeoutException e)
		{
			isTitleCorrect = false;
		}
		
		Assert.assertTrue(isTitleCorrect,"Incorrect Search Results page");		
	}
	
	/*
	 * getter for page title
	 */
	public String getTitle() 
	{
		return driver.getTitle();
	}
	
	/*
	 *  Returns the list of product names from the 1st page of search results
	 */
	public List<String> getProductNameList()
	{
		// List of product names
		List<String> productNamesList = new ArrayList<String>();
		
		// Go thru each tile on first search results page to get prod name
		for (WebElement product : productTilesList)
		{
			productNamesList.add(product.getText());
		}
	
		return productNamesList;
	}
}
