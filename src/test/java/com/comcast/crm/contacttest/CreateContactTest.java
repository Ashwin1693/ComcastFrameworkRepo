package com.comcast.crm.contacttest;

import java.io.IOException;


import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectRepositoryUtility.ContactInfoPage;
import com.comcast.crm.objectRepositoryUtility.ContactsPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewContactPage;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.OrgSearchWindowPopUp;
import com.comcast.crm.objectRepositoryUtility.OrganizationsPage;
/**
 * @author Ashwini  
 */
@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImpClass.class)
public class CreateContactTest extends BaseClass {

	@Test(groups= {"SmokeTest"})
	public void createContactTest() throws IOException, InterruptedException {

		/* read testscript data from Excel file */
		String LastName = eLib.getDataFromExcel("Contact", 4, 2).toString();

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.getLastNameEdt().sendKeys(LastName);
		cnp.getSaveBtn().click();
		Thread.sleep(2000);

		ContactInfoPage cip = new ContactInfoPage(driver);
		String DisplayedLastName = cip.getNameHeaderMsg().getText();
		System.out.println(DisplayedLastName);

		/* verify header msg expected result */

		if (DisplayedLastName.contains(LastName)) {
			System.out.println(LastName + " header verified-->PASS");
		} else {
			System.out.println(LastName + " header not verified-->FAIL");

		}

	}

	@Test(groups= {"Regression Test"})
	public void createContactWithSupportDateTest() throws IOException, InterruptedException {
		/* read testscript data from excel file */
		String lastname = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();

		/*stage 2 - Navigate to Contct module*/
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/*Stage 3 - Click on "Create Contact" button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		/* Stage 4 - Enter all details & create new contact */
		String startDate = jLib.getSystemDateMMDDYYYY();
		String endDate = jLib.getRequiredDateMMDDYYYY(10);
		CreatingNewContactPage cnp = new CreatingNewContactPage(driver);
		cnp.createContactWithSupportDate(lastname, startDate, endDate);

		String actstartdate = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if (actstartdate.contains(startDate)) {
			System.out.println(startDate + " date is verified");
		} else {
			System.out.println(startDate + " date is not verified");

		}
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();

		/* Note to self- I have identified the element wrong for actual end date..Needs to be corrected */
		if (actenddate.contains(endDate)) {
			System.out.println(endDate + " date is verified");
		} else {
			System.out.println(endDate + " date is not verified");

		}

	}

	@Test(groups= {"Regression Test"})
	
	public void createContactWithOrgTest() throws IOException, InterruptedException {

		/* Read testscript data from excel file for creating Organization */

		String shipAdd = eLib.getDataFromExcel("Org", 4, 5) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("Org", 4, 3);

		String LastName = eLib.getDataFromExcel("Contact", 4, 2).toString();
		String orgName = eLib.getDataFromExcel("Contact", 7, 2).toString() + jLib.getRandomNumber();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		HomePage hp = new HomePage(driver);

		hp.getorgLink().click();

		/* click on Create Organization button */

		OrganizationsPage CNP = new OrganizationsPage(driver);
		CNP.getCreateNewOrgBtn().click();

		/* enter all the details & create new organization */
		CreatingNewOrganizationPage CNOP = new CreatingNewOrganizationPage(driver);

		CNOP.createOrg(orgName, shipAdd, Industry);
		Thread.sleep(2000);

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

		/* verify header msg expected result */

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
		
		

	}
}