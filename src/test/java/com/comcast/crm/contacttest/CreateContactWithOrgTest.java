package com.comcast.crm.contacttest;

import java.io.IOException;

import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.objectRepositoryUtility.ContactsPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.OrgSearchWindowPopUp;
import com.comcast.crm.objectRepositoryUtility.OrganizationsPage;

public class CreateContactWithOrgTest extends BaseClass {

	
        
	@Test
	public void createContactWithOrgTest() throws IOException, InterruptedException {

		// Read testscript data from excel file for creating Organization

		String shipAdd = eLib.getDataFromExcel("Org", 4, 5) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("Org", 4, 3);

		String LastName = eLib.getDataFromExcel("Contact", 4, 2).toString();
		String orgName = eLib.getDataFromExcel("Contact", 7, 2).toString() + jLib.getRandomNumber();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		HomePage hp = new HomePage(driver);

		hp.getorgLink().click();

		// click on Create Organization button

		OrganizationsPage CNP = new OrganizationsPage(driver);
		CNP.getCreateNewOrgBtn().click();

		// enter all the details & create new organization
		CreatingNewOrganizationPage CNOP = new CreatingNewOrganizationPage(driver);

		CNOP.createOrg(orgName, shipAdd, Industry);

//		Actions act = new Actions(driver);
//		act.moveToElement(driver.findElement(By.linkText("Contacts"))).click().build().perform();

		HomePage hp1= new HomePage(driver);
		hp1.getContactLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.enterContactDetails(LastName);
		cnp.getSelectOrganizationNameBtn().click();
		Thread.sleep(2000);

		wLib.switchToTabOnTitle(driver, "module=Accounts&action");

		OrgSearchWindowPopUp oswp = new OrgSearchWindowPopUp(driver);
		oswp.lookUpOrgName(orgName, "Organization Name");

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wLib.switchToTabOnTitle(driver, "module=Contacts&action");

		cnp.getSaveBtn().click();

		ContactInfoPage cip = new ContactInfoPage(driver);
		String DisplayedLastName = cip.getNameHeaderMsg().getText();
		System.out.println(DisplayedLastName);

		// verify header msg expected result

		if (DisplayedLastName.contains(LastName)) {
			System.out.println(LastName + " header verified-->PASS");
		} else {
			System.out.println(LastName + " header not verified-->FAIL");

		}
		String DisplayedOrgNAme = cip.getDisplayedOrgName().getText();

		if (DisplayedOrgNAme.contains(orgName)) {
			System.out.println(orgName + " header verified-->PASS");
		} else {
			System.out.println(orgName + " header not verified-->FAIL");

		}
		driver.close();
		driver.quit();

	}
}