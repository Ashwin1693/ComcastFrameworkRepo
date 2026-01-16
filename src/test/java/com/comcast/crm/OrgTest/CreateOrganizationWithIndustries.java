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

public class CreateOrganizationWithIndustries {

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
				String ActualIndustry= eLib.getDataFromExcel("Org", 4, 3);
			

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
				 
				//Logging in
				 LoginPage lp= new LoginPage(driver);
				 lp.LoginToApp(USERNAME, PASSWORD);
				 
				 
				 HomePage hp= new HomePage(driver);
				 hp.getorgLink().click();
				 
				 OrganizationsPage CNP= new OrganizationsPage(driver);
				 CNP.getCreateNewOrgBtn().click();
				 
				 CreatingNewOrganizationPage cnp= new CreatingNewOrganizationPage(driver);
				 cnp.createOrg(orgName, shipAdd, ActualIndustry);
				 
				 OrganizationInfoPage cnop= new OrganizationInfoPage(driver);
			     String DisplayedINdustry =  cnop.getIndustryMsg().getText();
				 
				 
				 if(DisplayedINdustry.contains(ActualIndustry)) {
						System.out.println(ActualIndustry  + " information verified");		
						}else {
							System.out.println(ActualIndustry  + " information not verified");
							
						}

				}
}

		
		

