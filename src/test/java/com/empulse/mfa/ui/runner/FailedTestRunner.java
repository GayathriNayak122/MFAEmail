package com.empulse.mfa.ui.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "@target/failed_scenarios.txt", glue = "com/empulse/cycle/ui/stepdefinitions", monochrome = true, dryRun = false, tags = "@Login", plugin = {
		"pretty", "html:target/cucumber.html",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", })
public class FailedTestRunner extends AbstractTestNGCucumberTests {
	// below code is to run scenarios parallelly
	// 2 browsers will open parallelly
	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
