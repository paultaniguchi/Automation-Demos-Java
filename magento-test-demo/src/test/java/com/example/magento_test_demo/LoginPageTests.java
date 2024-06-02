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

/*
 * Tests for the https://magento.softwaretestingboard.com site
 * 
 * Author: Paul Taniguchi
 */

public class LoginPageTests 
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
	
	@Test
	public void testLoginSuccessfully() 
	{
		String expCurrentUserText = "Welcome, Jacko Testbuyer!";
		
		HomePage homePage = new HomePage(driver).get();
		LoginPage testUserLoginPage;
		
		// Go to Login page
		testUserLoginPage = homePage.goToLoginPage();
		
		// enter credentials
		testUserLoginPage.enterCredentials("REDACTED","REDACTED");
		
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
