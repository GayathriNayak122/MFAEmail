package com.empulse.mfa.ui.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/empulse/mfa/ui/features", glue = "com/empulse/mfa/ui/stepdefinitions", monochrome = true, dryRun = true, tags =" @AseNewDistributorform", plugin = {
		"pretty", "html:target/cucumber.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"rerun:target/failed_scenarios.txt", ("json:target/cucumber-reports/CucumberTestReport.json") })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	// below code is to run scenarios parallelly
	// 2 browsers will open parallelly
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
