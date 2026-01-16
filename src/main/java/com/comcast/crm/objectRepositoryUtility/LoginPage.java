package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;

/**
 * @author Ashwini 
 * Info - Contains Login page elements & business library like Login()
 */

public class LoginPage extends WebDriverUtility {
	// Rule 1 - Create a separate Java class
	// rule 2 - Object Creation
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Lazy Initialization
	}

	@FindBy(name = "user_name")
	WebElement usernameEdt;

	@FindBy(name = "user_password")
	WebElement passwordEdt;

	@FindBy(id = "submitButton")
	WebElement LoginBtn;

	// Rule 3 - Object initialization (this is to be done in the TestScript)

	// Rule 4 - Object Encapsulation (Provide getter and setter methods)

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return LoginBtn;
	}

	/**
	 * Info -  Login to application based on Username and Password arguments
	 * 
	 * @param username
	 * @param password
	 */

	public void LoginToApp(String username, String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		getUsernameEdt().sendKeys(username);
		getPasswordEdt().sendKeys(password);
		getLoginBtn().click();

	}

	/**
	 * Info - Login to application based on URL, Username and Password arguments
	 * 
	 * @param url
	 * @param username
	 * @param password
	 */

	public void LoginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		getUsernameEdt().sendKeys(username);
		getPasswordEdt().sendKeys(password);
		getLoginBtn().click();

	}

}
