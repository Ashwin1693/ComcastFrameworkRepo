package com.comcast.crm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.DatabaseUtility.DatabaseUtility;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.FileUtility;
import com.comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;

public class BaseClass {

	// Object Creation
	public DatabaseUtility dblib  = new DatabaseUtility();
	public ExcelUtility    eLib   = new ExcelUtility();
	public FileUtility     fLib   = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null; // "s" stands to indicate it is static driver reference

	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void ConfigBS() {

		System.out.println("<=======Connect to DB=========>" + "========Report Config======");
		dblib.getdbConnection();

	}

	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void ConfigBC() throws IOException {

		System.out.println("LAUNCH the BROWSER");

		String BROWSER = fLib.getDataFromPropertiesFile("browser").toString();

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver; // here we are trying to store real browser sessionID in sdriver
		UtilityClassObject.setDriver(driver);
	}

	@BeforeMethod
	public void ConfigBM() throws IOException {

		System.out.println("LOGIN to application");
		LoginPage lp = new LoginPage(driver);
		String URL = fLib.getDataFromPropertiesFile("url").toString();
		String USERNAME = fLib.getDataFromPropertiesFile("username").toString();
		String PASSWORD = fLib.getDataFromPropertiesFile("password").toString();
		lp.LoginToApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod
	public void ConfigAM() {

		System.out.println("LOGOUT from application");

		HomePage hp = new HomePage(driver);
		hp.logOut();
	}

	@AfterClass
	public void ConfigAC() {

		System.out.println("CLOSE the BROWSER");
		driver.quit();
	}

	@AfterSuite
	public void ConfigAS() {

		System.out.println("CLOSE the connection with DB" + "Report Back up");
		dblib.closeDBConnection();

	}

}
