package practice.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.FileUtility.ExcelUtility;


public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.amazon.in/ref=nav_logo");
		
		//Search the product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
	
		//Capture product info
		String X = "//span[text()='"+productName+"']/../../../../div[3]/div/div/div/div/div/a/span/span/span[2]";
		
		String price= driver.findElement(By.xpath(X)).getText();
		System.out.println("The price of the product is "+price);
	
	}
	
	@DataProvider
	public Object[] [] getData() throws Throwable{
		ExcelUtility eLib= new ExcelUtility();
		int rowcount= eLib.getRowCount("Product");
		System.out.println(rowcount);
		Object [] [] objArr= new Object[rowcount][2];
		
		for(int i=0;i<rowcount;i++) {
			
		objArr[i][0]= eLib.getDataFromExcel("Product", i+1,0);
		objArr[i][1]= eLib.getDataFromExcel("Product", i+1,1);
		
	}
		return objArr;
	
	}
}
 // 

//span[text()='iPhone 15 (128 GB) - Green']/../../../../div[3]/div/div/div/div/div/a/span/span/span[2]

