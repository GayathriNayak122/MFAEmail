package com.empulse.mfa.ui.pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver driver;
	public Actions actions;
	public WebElement element;
    public WebDriverWait wait;
    

	public JavascriptExecutor javascriptExecutor;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		javascriptExecutor = (JavascriptExecutor) driver;
		actions = new Actions(driver);
		 wait = new WebDriverWait(driver, null);
	
		System.out.println("inside basepage constructor");

	}
}
