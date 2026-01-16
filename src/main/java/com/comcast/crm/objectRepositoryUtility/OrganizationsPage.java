package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OrganizationsPage {

	WebDriver driver;
	public OrganizationsPage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//img[@title='Create Organization...']")
	 private WebElement CreateNewOrgBtn;
	
	@FindBy (name="search_text")
	private WebElement SearchEdt;
	
	@FindBy (id="bas_searchfield")
    private WebElement SearchOrgDD;
	
	@FindBy (name="submit")
	private WebElement SearchBtn;
	
	public WebElement getSearchBtn() {
		return SearchBtn;
	}
		
	public WebElement getSearchEdt() {
		return SearchEdt;
	}

	public WebElement getSearchOrgDD() {
		return SearchOrgDD;
	}

	public WebElement getCreateNewOrgBtn() {
		return CreateNewOrgBtn;
	}
	
	public void SearchAndDeleteOrganization(String orgName) {
		SearchEdt.sendKeys(orgName);
		Select sel= new Select(SearchOrgDD);
		sel.selectByVisibleText(orgName);
				
	}
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
