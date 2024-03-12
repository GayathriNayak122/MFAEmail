package com.empulse.mfa.ui.pageobject;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	private WebDriver driver;
	private LoginPage loginPage;
	

	// PageObjectManager takes the driver from webdriverManager() and delegates to
	// all files
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public LoginPage getLoginPage() {
		loginPage = new LoginPage(driver);
		return loginPage;
	}

	
}