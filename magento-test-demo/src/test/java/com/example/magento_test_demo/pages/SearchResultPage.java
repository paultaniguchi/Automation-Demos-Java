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

public class SearchResultPage extends LoadableComponent<SearchResultPage>{

	public WebDriver driver;
	// page factory for product tile in search results
	@FindBy(css = "img.product-image-photo")
	private List<WebElement> productTilesList;
	
	/**
	 * constructor
	 */
	public SearchResultPage(WebDriver driverFromTest) 
	{
		driver = driverFromTest;
		PageFactory.initElements(driver, this);
	}
	
	@Override
	protected void load() 
	{
	}
	
	@Override
	protected void isLoaded()
	/*
	 *  check page title to see if this is correct page
	 */
	{
		boolean isTitleCorrect;
		
		// Assert.assertTrue(getTitle().contains("Search results"));
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
		
		Assert.assertTrue(isTitleCorrect,"Incorrect page");		
	}
	
	public String getTitle() 
	{
		return driver.getTitle();
	}
	
	/*
	 *  Get the list of product names from the search results
	 *  returns: list of product names
	 */
	public List<String> getProductNameList()
	{
		// List of product names
		List<String> productNamesList = new ArrayList<String>();
		
		// Go thru each tile on search results page
		for (WebElement product : productTilesList)
		{
			productNamesList.add(product.getAttribute("alt"));
		}
	
		return productNamesList;
	}
}
