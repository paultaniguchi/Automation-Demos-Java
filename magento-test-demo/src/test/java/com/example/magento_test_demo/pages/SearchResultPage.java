package com.example.magento_test_demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import java.time.Duration;

public class SearchResultPage extends LoadableComponent<SearchResultPage>{

	public WebDriver driver;
	
	/**
	 * constructor
	 */
	public SearchResultPage(WebDriver driverFromTest) 
	{
		driver = driverFromTest;
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
	
	
}
