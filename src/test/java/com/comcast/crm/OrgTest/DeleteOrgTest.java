package com.comcast.crm.OrgTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.FileUtility;
import com.comcast.crm.generic.WebDriverUtility.JavaUtility;
import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.LoginPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationsPage;

public class DeleteOrgTest {

public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create Object
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib= new ExcelUtility();
		JavaUtility jLib= new JavaUtility();
		WebDriverUtility wLib= new WebDriverUtility();
		//read data from JSON File
		
		String BROWSER= fLib.getDataFromPropertiesFile("browser");
		String URL= fLib.getDataFromPropertiesFile("url");
		String USERNAME= fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		
		//Read testscript data from excel file
		String orgName= eLib.getDataFromExcel("Org", 10, 2)+ jLib.getRandomNumber();
		String shipAdd= eLib.getDataFromExcel("Org", 4, 5)+ jLib.getRandomNumber();
		String Industry= eLib.getDataFromExcel("Org", 4, 3);
	
		
//		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
//		Properties pobj = new Properties();
//		pobj.load(fis);
//		String url = pobj.getProperty("url");
//		String browser = pobj.getProperty("browser");
//		String userName = pobj.getProperty("username");
//		String passWord = pobj.getProperty("password");

//		Random random = new Random();
//		int randomnum = random.nextInt(2000);
//
//		FileInputStream fis1 = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//		Sheet sh = wb.getSheet("Org");
//		String orgname = sh.getRow(4).getCell(2).toString() + randomnum;
//		wb.close();

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
		
		
		/*To simpify this line - "LoginPage lp= PageFactory.initElements(driver, LoginPage.class);"
		 --> this is known as Lazy Initialization, 
		 we will go to LoginPage class and create one constructor and inside this we will do initialization */
		
		LoginPage lp= new LoginPage(driver);
		
		lp.LoginToApp(USERNAME, PASSWORD);
	
		//Navigate to Organization module
		
		HomePage hp= new HomePage(driver);
		hp.getorgLink().click();
	
		
		// click on Create Organization button
		
		OrganizationsPage cnp= new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
				
		// enter all the details & create new organization
		CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
		
		cnop.createOrg(orgName,shipAdd,Industry);
		
		// Verify Header msg Expected Result
		
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String DisplayedOrgName= oip.getHeaderMsg().getText();
		if(DisplayedOrgName.contains(orgName)) {
			
			System.out.println(orgName+ " information is verified==> PASSED");
		}else {
			System.out.println(orgName+ " information is not verified==> FAILED");
		}
		
		//Go back to Organizations page 
		hp.getorgLink().click();
		
		
		//Search for organization
		cnp.getSearchEdt().sendKeys(orgName);
		wLib.select(cnp.getSearchOrgDD(),"Organization Name");
		cnp.getSearchBtn().click();
		
		//In dynamic Web table select and delete org
		driver.findElement
		(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		
			
		wLib.switchToAlertAndAccept(driver);
		
		// Logout
		hp.logOut();
		
	    driver.quit();
		
	}


}