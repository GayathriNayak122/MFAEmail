package com.empulse.mfa.ui.stepdefinitions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.empulse.mfa.ui.utils.TestContextSetup;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

public class Hooks {
	TestContextSetup testContextSetup;

	public Hooks(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	// @After annotation means after execution of the scenario, this method will get
	// executed
	@After
	public void afterScenario() {
		// driver.quit();// to use driver here, we need to call webDriverManager() with
		// TestBase obj, but TestBase obj is already created in TestContextSetup
		// for that we need to inject TestContextSetup in Hooks (dependency injection),
		// just the way we did in step definitions.
		/*
		 * public SignUpPageStepDefinition(TestContextSetup testContextSetup){
		 * this.testContextSetup = testContextSetup; }
		 */
		// testContextSetup.testBase.webDriverManager().quit();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {
		try {
			WebDriver driver = testContextSetup.testBase.webDriverManager();
			if (scenario.isFailed()) {
				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				byte[] filecontent = FileUtils.readFileToByteArray(sourcePath);// for FileUtils put maven dependency
																				// commons.io
				scenario.attach(filecontent, "image/png", "image");// attach meth accepts file content in byte format.
				// That is why in the previous line we r converting File into byte array
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
