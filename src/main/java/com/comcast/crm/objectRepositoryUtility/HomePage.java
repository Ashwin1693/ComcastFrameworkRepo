package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(xpath="(//a[text()='Contacts'])[1]")
	private WebElement ContactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement CampaignLink;
	
	@FindBy (linkText="More")
	private WebElement MoreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement AdminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement SignOutLink;
	
	@FindBy(linkText="Products ")
	private WebElement ProductsLink;


	public WebElement getProductsLink() {
		return ProductsLink;
	}

	public WebElement getAdminImg() {
		return AdminImg;
	}

	public WebElement getSignOutBtn() {
		return SignOutLink;
	}

	public WebElement getCampaignLink() {
		return CampaignLink;
	}

	public WebElement getMoreLink() {
		return MoreLink;
	}

	public WebElement getorgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return ContactLink;
	}
	
	/* this is a business library */
	
	public void navigateToCampaignPage(){
		 Actions act= new Actions(driver);
		 act.moveToElement(MoreLink).perform();
		 
		 CampaignLink.click();
	}
		 
		 public void logOut() {
			 Actions act = new Actions(driver);
			 act.moveToElement(AdminImg).perform();
			 
			 SignOutLink.click();
		 }
		 
}




















	
