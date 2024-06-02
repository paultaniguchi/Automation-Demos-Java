package com.example.magento_test_demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import java.time.Duration;

import com.example.magento_test_demo.pages.HomePage;
import com.example.magento_test_demo.pages.SearchResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.ArrayList;

/*
 * Tests for the https://magento.softwaretestingboard.com site
 * 
 * Author: Paul Taniguchi
 */
public class HomePageTests 
{
	// create driver out here so all methods can access it
	public WebDriver driver;
	
	@BeforeClass
	public void setUp()
	{
		// set driver to Firefox in here to avoid blank browser while test is running
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	/*
	 *  data provider for the testSearchReturnsProducts method
	 *  Object array of the form:
	 *  (search product to be entered into search bar),(list of products 
	 *  expected to be returned for the search term) 
	 */
	@DataProvider(name = "search")
	public Object[][] dataForSearchTest()
	{
		return new Object[][]
			{
				{"watch",List.of("Didi Sport Watch", "Dash Digital Watch",
					"Clamber Watch","Bolo Sport Watch","Luma Analog Watch",
					"Cruise Dual Analog Watch",	"Summit Watch",
					"Endurance Watch","Aim Analog Watch")},
				{"bottle",List.of("Affirm Water Bottle","Driven Backpack",
						"Savvy Shoulder Tote","Compete Track Tote",
						"Voyage Yoga Bag","Crown Summit Backpack")}
			};
	}
	
	/*
	 *  test for the products on 1st pg of search results for the searchTerm
	 *  Data driven test using data provider dataForSearchTest 
	 */
	@Test(dataProvider = "search")
	public void testSearchReturnsProducts(String searchTerm, List<String> expProdNamesList)
	{
		// actual product names
		List<String> prodNamesList = new ArrayList<String>();
		
		HomePage homePage = new HomePage(driver).get();
		SearchResultPage prodSearchResult;
		
		// search for searchTerm product
		prodSearchResult = homePage.submitTextInSearch(searchTerm);
		
		// get the products from the search results
		prodNamesList = prodSearchResult.getProductNameList();
				
		Assert.assertEquals(prodNamesList, expProdNamesList);
	}
	
	/*
	 *  Tear down 
	 */
	@AfterClass
	public void tearDown() 
	{
		driver.quit();
	}
}

