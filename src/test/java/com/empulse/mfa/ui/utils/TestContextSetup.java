package com.empulse.mfa.ui.utils;

import org.openqa.selenium.WebDriver;

import com.empulse.mfa.ui.pageobject.PageObjectManager;

public class TestContextSetup {
	public WebDriver driver;
	public PageObjectManager pageObjectManager;
	public TestBase testBase;
	public GenericUtils genericUtils;

	public TestContextSetup() {
		// initializes pageObjectManager
		testBase = new TestBase();
		pageObjectManager = new PageObjectManager(testBase.webDriverManager());
		genericUtils = new GenericUtils(testBase.webDriverManager());
		
	}
	// TestContextSetup is the heart of the proj, which pumps the blood (driver) to
	// every part of this framework

}
