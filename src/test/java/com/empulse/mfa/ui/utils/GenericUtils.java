package com.empulse.mfa.ui.utils;

import org.openqa.selenium.WebDriver;

public class GenericUtils {
//put common codes to be used in step definition files

	WebDriver driver;

	public GenericUtils(WebDriver driver) {
		this.driver = driver;
	}
}
