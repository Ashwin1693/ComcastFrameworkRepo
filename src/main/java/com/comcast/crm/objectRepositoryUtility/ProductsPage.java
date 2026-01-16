package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
WebDriver driver;
	
	
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath="//img[@alt='Create Product...']")
	private WebElement CreateProductBtnImg;
	
	@FindBy (name= "searchBtn")
	private WebElement ele3;
}
