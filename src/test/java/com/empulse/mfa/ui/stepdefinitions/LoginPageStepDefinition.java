package com.empulse.mfa.ui.stepdefinitions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.empulse.mfa.ui.pageobject.LoginPage;
import com.empulse.mfa.ui.utils.TestContextSetup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {
	TestContextSetup testContextSetup;
	LoginPage loginPage;
	WebDriver driver;
	String otp;

	public LoginPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
		this.loginPage = testContextSetup.pageObjectManager.getLoginPage();
		// this.dealerPage= testContextSetup.pageObjectManager.getDealerPage();
	}

	@Given("^User is on Home Page$")
	public void user_is_on_home_page() {
		// driver = new FirefoxDriver();
		// driver.get("https://demo.emsalescrm.com/#/sign-in");
	}

	@When("^User navigates to Login Page$")
	public void user_navigates_to_login_page() throws Exception {
		// driver.findElement(By.id("login")).click();

		// Alert alert = driver.switchTo().alert();
		// alert.accept();
		// driver.findElement(By.xpath("//h6[@class='header-item11 mr-4']")).click();
	}

	@When("^User enters Credentials to LogIn$")
	public void user_enters_credentials_to_log_in() throws InterruptedException {
		boolean enterLoginCredentials = loginPage.LoginMethod();
		Assert.assertEquals(enterLoginCredentials, true);
	}

	@Then("^Message displayed Login Successfully$")
	public void message_displayed_login_successfully() {
		// Here you should check that the success message is displayed.
		// This will depend on how your application displays this message.
		System.out.println("Login Successfully");
	}

	@Then("^User receives OTP$")
	public void user_receives_OTP() {
		otp = loginPage.readOTPFromEmail("gnayak@1ffc.com");
		System.out.println("################## otp " + otp);
	}

	@When("^User enters OTP$")
	public void user_enters_OTP() {
		boolean enterOtp = loginPage.EntersOTP();
		Assert.assertEquals(enterOtp, true);
	}
}
