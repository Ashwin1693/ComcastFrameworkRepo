package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		

		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
		Properties pobj = new Properties();
		pobj.load(fis);

		
		
		String url = pobj.getProperty("url");
		String browser = pobj.getProperty("browser");
		String userName = pobj.getProperty("username");
		String passWord = pobj.getProperty("password");

		Random random = new Random();
		int randomnum = random.nextInt(1500);

		FileInputStream fis1 = new FileInputStream("./TestScriptData/TestScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Contact");
		Row row = sh.getRow(4);
		String lastname = row.getCell(2).toString()+randomnum;
		String orgname = sh.getRow(7).getCell(2).toString()+randomnum;
		wb.close();

		WebDriver driver = null;

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		Thread.sleep(2000);
		driver.findElement(By.name("user_name")).sendKeys(userName);
		driver.findElement(By.name("user_password")).sendKeys(passWord);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.className("detailedViewTextBox")).sendKeys(orgname);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Japan2");

		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startdate = sim.format(dateobj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String enddate = sim.format(cal.getTime());
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);	
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
		

		
	//	driver.findElement(By.name("ship_street")).sendKeys("wer");
		

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.className("dvHeaderText")).getText();
		if(headerInfo.contains(lastname)) {
			System.out.println(lastname  + " header verified");		
			}else {
				System.out.println(lastname  + " header not verified");
				
			}
		
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.contains(startdate)) {
			System.out.println(startdate  + " date is verified");		
			}else {
				System.out.println(startdate  + " date is not verified");
				
			}
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		
		//Note to self- I have identified the element wrong for actual end date..Needs to be corrected
		if(actenddate.contains(enddate)) {
			System.out.println(enddate  + " date is verified");		
			}else {
				System.out.println(enddate  + " date is not verified");
				
	
		}
	}

}
