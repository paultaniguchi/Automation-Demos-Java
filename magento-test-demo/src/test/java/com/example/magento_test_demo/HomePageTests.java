package com.example.magento_test_demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import java.time.Duration;

import com.example.magento_test_demo.pages.HomePage;
import com.example.magento_test_demo.pages.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.ArrayList;

/**
 * Tests for the https://magento.softwaretestingboard.com home page
 */
public class HomePageTests 
{
	/**
	 *  set up selenium
	 */
	
	public WebDriver driver = new FirefoxDriver();
	
	@BeforeClass
	public void SetUp()
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// driver.get("https://magento.softwaretestingboard.com");
	}

    /**
     * Test that the HomePage title is Home Page
     */
	@Test(enabled=false)
    public void testHomePageTitle()
    {
		HomePage home_page = new HomePage(driver).get();
		
		System.out.println("assert test");
        Assert.assertEquals(home_page.getTitle(),"Home Page" );
    }
	
	
	@Test
	public void testSearchReturnsProducts()
	{
		// actual product names
		List<String> watchProdNamesList = new ArrayList<String>();
		// expected product names
		// TO DO - this is ok for now, but List will prolly need to mutable
		// when parameters are added to this test
		List<String> expWatchProdNamesList = List.of("Didi Sport Watch", "Dash Digital Watch",
				"Clamber Watch","Bolo Sport Watch","Luma Analog Watch","Cruise Dual Analog Watch",
				"Summit Watch","Endurance Watch","Aim Analog Watch Fail");
		
		HomePage homePage = new HomePage(driver).get();
		SearchResultPage watchSearchResult;
		
		// search for watch
		watchSearchResult = homePage.submitTextInSearch("watch");
		System.out.println(watchSearchResult.getTitle());
		
		// get the watches from the search results
		watchProdNamesList = watchSearchResult.getProductNameList();
		
		// take a look at the list
		for (String watch : watchProdNamesList)
		{
			System.out.println(watch);
		}
		
		// placeholder assert
		Assert.assertEquals(watchProdNamesList, expWatchProdNamesList);
	}
	
	/**
	 *  Tear down
	 */
	@AfterClass
	public void TearDown() 
	{
		System.out.println("tear down");
		driver.quit();
	}
}

