package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebDriverUtility.JavaUtility;

public class CreatingNewContactPage {

	WebDriver driver;
	JavaUtility jLib= new JavaUtility();

	public CreatingNewContactPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//img[@alt='Select'])[1]")
	private WebElement SelectOrganizationNameBtn;

	@FindBy(name = "lastname")
	private WebElement LastNameEdt;

	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement SaveBtn;

	@FindBy(xpath = "//input[@id='jscal_field_support_start_date']")
	private WebElement SupportStartDateEdtField;
	
	@FindBy(xpath = "//input[@id='jscal_field_support_end_date']")
	private WebElement SupportEndDateEdtField;

	public WebElement getSupportEndDateEdtField() {
		return SupportEndDateEdtField;
	}

	public WebElement getSupportStartDateEdtField() {
		return SupportStartDateEdtField;
	}

	public WebElement getSelectOrganizationNameBtn() {
		return SelectOrganizationNameBtn;
	}

	public WebElement getLastNameEdt() {
		return LastNameEdt;
	}

	public WebElement getSaveBtn() {
		return SaveBtn;
	}

	public void enterContactDetails(String lastName) {
		LastNameEdt.sendKeys(lastName);
		SelectOrganizationNameBtn.click();

	}
	public void createContactWithSupportDate(String lastname,String startDate,String endDate) throws InterruptedException {
		LastNameEdt.sendKeys(lastname);
		
		SupportStartDateEdtField.clear();
		SupportStartDateEdtField.sendKeys(startDate);
		Thread.sleep(1500);
		
		SupportEndDateEdtField.clear();
		SupportEndDateEdtField.sendKeys(endDate);
		Thread.sleep(1500);
		SaveBtn.click();
		
	}

}
