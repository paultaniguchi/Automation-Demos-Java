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
	public void testHomePageSearch()
	{
		// move this to constructor - TO DO
		// HomePage home_page = PageFactory.initElements(driver, HomePage.class);
		HomePage homePage = new HomePage(driver).get();
		SearchResultPage watchSearchResult;
		watchSearchResult = homePage.submitTextInSearch("watch");
		System.out.println(watchSearchResult.getTitle());
		// placeholder assert
		Assert.assertTrue(true);
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

