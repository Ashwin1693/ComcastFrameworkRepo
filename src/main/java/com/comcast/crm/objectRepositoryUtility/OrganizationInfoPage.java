package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;
	 
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy (className = "dvHeaderText")
	private WebElement HeaderMsg;
	
	@FindBy (id="dtlview_Phone")
	private WebElement PhoneNoMsg;
	
	@FindBy (id="dtlview_Industry")
	private WebElement IndustryMsg;
	
	public WebElement getIndustryMsg() {
		return IndustryMsg;
	}
	
	public WebElement getPhoneNoMsg() {
		return PhoneNoMsg;
	}
	
	public WebElement getHeaderMsg() {
		return HeaderMsg;
	}

	
}
