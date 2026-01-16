package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.WebDriverUtility.WebDriverUtility;

public class OrgSearchWindowPopUp {

	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	public OrgSearchWindowPopUp(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy (id="search_txt")
	private WebElement SearchBoxEdt;
	
	@FindBy (name="search_field")
	private WebElement SearchDP;
	
	@FindBy (xpath="//input[@name='search']")
	private WebElement SearchNowBtn;
	
	public void lookUpOrgName(String orgName, String OrganizationName) {
		
		SearchBoxEdt.sendKeys(orgName);
		
		Select objsel= new Select(SearchDP);
		objsel.selectByVisibleText(OrganizationName);
		SearchNowBtn.click();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
