package com.comcast.crm.generic.WebDriverUtility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	public static ThreadLocal<ExtentTest> test= new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {
		return test.get();     
	}
	
	public static void setTest(ExtentTest actTest) {
		test.set(actTest);
	}

	public static WebDriver getDriver() {
		return driver.get();     
	}
	
	public static void setDriver(WebDriver actDriver) {
		driver.set(actDriver);
	}
	
}


//This method will give extent report object for multiple threads when you achieve parallel execution
// this method will share the object with the help of threadlocal