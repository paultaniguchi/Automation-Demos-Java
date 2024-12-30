package com.example.magento_test_demo;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import java.time.Duration;

import com.example.magento_test_demo.pages.HomePage;
import com.example.magento_test_demo.pages.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Tests for the https://magento.softwaretestingboard.com site
 * 
 * Author: Paul Taniguchi
 */

public class LoginPageTests 
{
	private static Logger logger = LoggerFactory.getLogger(LoginPageTests.class);
	
	// create driver out here so all methods can access it
	public WebDriver driver;
		
	/*
	 * Retrieves the Magento username stored in the env variable
	 * Note to self: env variables are set in the Eclipse Run Configurations for testng.xml 
	 * Returns: String
	 */
	private String getLoginUsername()
	{
		String username = System.getenv("MagentoUsername");

		// throw exception if username is not set in env variable
		if (username != null)
		{
			return username;
		}
		else
		{
			throw new RuntimeException("MagentoUsername env variable is missing");
		}
	}
	
	/*
	 * Retrieves the Magento password stored in the env variable
	 * Returns: char array
	 * keep password in char array instead of String
	 * https://stackoverflow.com/questions/43174598/password-security-in-java-converting-char-to-string
	 * 
	 */
	private char[] getLoginPassword()
	{
		// throw exception if password is not set in the env variable
		try 
		{
			return System.getenv("MagentoPassword").toCharArray();
		}
		catch(NullPointerException e)
		{
			throw new RuntimeException("MagentoPassword env variable is missing");
		}
	}
	
	@BeforeClass
	public void setUp()
	{
		// set driver to Firefox in here to avoid blank browser while test is running
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}
	
	@Test
	public void testLoginSuccessfully() 
	/*
	 * Verify that a valid user can log into magento
	 */
	{
		String expCurrentUserText = "Welcome, Jacko Testbuyer!";
		
		logger.info("Executing Valid user login test");
		
		HomePage homePage = new HomePage(driver).get();
		LoginPage testUserLoginPage;
		
		// Go to Login page
		testUserLoginPage = homePage.goToLoginPage();
		
		// enter credentials
		testUserLoginPage.enterCredentials(getLoginUsername(), getLoginPassword());
		
		// check correct user is in upper right corner
		Assert.assertEquals(homePage.getCurrentUser(expCurrentUserText), expCurrentUserText);
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
