package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	WebDriver driver;
	 
	public ContactInfoPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement DisplayedOrgName;
		
	@FindBy(className ="dvHeaderText")
	private WebElement NameHeaderMsg;

	public WebElement getNameHeaderMsg() {
		return NameHeaderMsg;
	}
		
	public WebElement getDisplayedOrgName() {
		return DisplayedOrgName;
	}
	

}
