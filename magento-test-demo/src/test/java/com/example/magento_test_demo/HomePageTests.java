package com.example.magento_test_demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import java.time.Duration;

import com.example.magento_test_demo.SetUpTearDown;
import com.example.magento_test_demo.pages.HomePage;
import com.example.magento_test_demo.pages.SearchResultPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

/*
 * Search Results Tests for the https://magento.softwaretestingboard.com site
 * Use Home page search bar to search for product & verify that the 1st page
 * of results has the correct items
 * 
 * Author: Paul Taniguchi
 */
public class HomePageTests 
{
	private static Logger logger = LoggerFactory.getLogger(HomePageTests.class);	
	
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
		logger.info("Executing Search test for {}", searchTerm);
		
		// actual product names
		List<String> prodNamesList = new ArrayList<String>();
		
		//HomePage homePage = new HomePage(driver).get();
		HomePage homePage = new HomePage(SetUpTearDown.driver).get();		
		SearchResultPage prodSearchResult;
		
		// search for searchTerm product
		prodSearchResult = homePage.submitTextInSearch(searchTerm);
		
		// get the products from the search results
		prodNamesList = prodSearchResult.getProductNameList();
				
		Assert.assertEquals(prodNamesList, expProdNamesList);
	}
	
}