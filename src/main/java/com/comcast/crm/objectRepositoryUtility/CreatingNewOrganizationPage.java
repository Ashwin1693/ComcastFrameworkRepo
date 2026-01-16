package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class CreatingNewOrganizationPage {
	
	WebDriver driver;

    public CreatingNewOrganizationPage(WebDriver driver){
    	this.driver=driver;
    	PageFactory.initElements(driver,this);
    	
    }
	
	@FindBy(name="accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name="ship_street")
	private WebElement ShipAddEdt;
	
	@FindBy (name= "phone")
	private WebElement PhoneEdt;
	
	@FindBy(name="industry")
	private WebElement IndustryDD;
	
	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[1]")
	private WebElement SaveBtn;
	
	public WebDriver getPhoneEdt() {
		return driver;
	}

	public WebElement getOrgNameEdt() {
		return PhoneEdt;
	}

	public WebElement getShipAddEdt() {
		return ShipAddEdt;
	}
	
	public WebElement IndustryDD() {
		return IndustryDD;	
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}

	public void createOrg(String orgName,String shipAdd) {   /*CreateOrg is an overloaded method wherein
															1st -> only essential data, 2nd -> Esse data with industry data */
		OrgNameEdt.sendKeys(orgName);		
		ShipAddEdt.sendKeys(shipAdd);
		SaveBtn.click();
	}
	
    public void createOrg(String orgName,String shipAdd,String Industry) throws InterruptedException {
		
		OrgNameEdt.sendKeys(orgName);
		ShipAddEdt.sendKeys(shipAdd);
		Select objSelc= new Select(IndustryDD);
		objSelc.selectByVisibleText(Industry);
		Thread.sleep(1500);
		SaveBtn.click();
	}
    
	
   public void createOrg1(String orgName,String shipAdd,String phoneNum) {
		
		OrgNameEdt.sendKeys(orgName);
		ShipAddEdt.sendKeys(shipAdd);
		PhoneEdt.sendKeys(phoneNum);
		SaveBtn.click();
}
   public void createOrg(String orgName,String shipAdd,String phoneNum,String Industry) {
		
		OrgNameEdt.sendKeys(orgName);
		ShipAddEdt.sendKeys(shipAdd);
		PhoneEdt.sendKeys(phoneNum);
		Select objSelc= new Select(IndustryDD);
		objSelc.selectByVisibleText(Industry);
		SaveBtn.click();
}
}
