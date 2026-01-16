package com.comcast.crm.OrgTest;


import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.FileUtility;
import com.comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationsPage;

public class CreateOrg_WithPhoneNumber_Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create Object
				FileUtility fLib= new FileUtility();
				ExcelUtility eLib= new ExcelUtility();
				JavaUtility jLib= new JavaUtility();
				
				//read data from JSON File
				
				String BROWSER= fLib.getDataFromPropertiesFile("browser");
				String URL= fLib.getDataFromPropertiesFile("url");
				String USERNAME= fLib.getDataFromPropertiesFile("username");
				String PASSWORD= fLib.getDataFromPropertiesFile("password");
				
				//Read testscript data from excel file
				String orgName= eLib.getDataFromExcel("Org", 4, 2)+ jLib.getRandomNumber();
				String shipAdd= eLib.getDataFromExcel("Org", 4, 5)+ jLib.getRandomNumber();
				String Industry= eLib.getDataFromExcel("Org", 4, 3);
				String phoneNum= eLib.getDataFromExcel("Org", 7, 3);

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
         
		//Login to app
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get(URL);
				Thread.sleep(2000);
				 LoginPage lp= new LoginPage(driver);
				 lp.LoginToApp(USERNAME, PASSWORD);
				 
				 HomePage hp= new HomePage(driver);
				 hp.getorgLink().click();
				 
				 OrganizationsPage CNP= new OrganizationsPage(driver);
				 CNP.getCreateNewOrgBtn().click();
				 
				 CreatingNewOrganizationPage cnp= new CreatingNewOrganizationPage(driver);
				 cnp.createOrg(orgName, shipAdd, Industry, phoneNum);
				 
				 OrganizationInfoPage cnop= new OrganizationInfoPage(driver);
			     String actphonenumber =  cnop.getPhoneNoMsg().getText();
				 
				 
				 if(actphonenumber.contains(phoneNum)) {
						System.out.println(phoneNum  + " information verified");		
						}else {
							System.out.println(phoneNum  + " information not verified");
							
						}

				}
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		driver.get(URL);
//		Thread.sleep(2000);
//		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
//		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("submitButton")).click();
//
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
//		driver.findElement(By.name("accountname")).sendKeys(orgName);
//		driver.findElement(By.id("phone")).sendKeys(phoneNum);
//		
//		driver.findElement(By.name("ship_street")).sendKeys(shipAdd);
		
		
		
	//	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//		Actions action = new Actions(driver);
//		action.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//		driver.findElement(By.linkText("Sign Out")).click();
		
		
		
		




