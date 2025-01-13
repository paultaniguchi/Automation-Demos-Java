/**
 * 
 */
package com.example.magento_test_demo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

/**
 *  setup & teardown selenium for the tests
 *  Author: Paul Taniguchi
 */
public class SetUpTearDown {
	
	// create driver out here so all test classes can access it
	static public WebDriver driver;
	
	/*
	 * Set up Firefox driver
	 */
	@BeforeSuite
	public void setUp()
	{
		// set driver to Firefox in here to avoid blank browser while test is running
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@AfterSuite
	public void tearDown() 
	{
		driver.quit();
	}
}
