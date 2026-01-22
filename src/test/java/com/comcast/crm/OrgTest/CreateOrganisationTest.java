package com.comcast.crm.OrgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.WebDriverUtility.UtilityClassObject;
import com.comcast.crm.objectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.objectRepositoryUtility.OrganizationsPage;

import junit.framework.Assert;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImpClass.class)
public class CreateOrganisationTest extends BaseClass {
	@Test(groups = "smoketest")

	public void createOrgTest() throws IOException, InterruptedException {
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");

		/* Read testscript data from excel file for creating Organization */

		String shipAdd = eLib.getDataFromExcel("Contact", 7, 4) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("Org", 4, 3);
		String orgName = eLib.getDataFromExcel("Contact", 7, 2).toString() + jLib.getRandomNumber();
		
		/* Step - Navigate to organization module */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Org page");
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		/* Step - click on Create Organization button */
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Org page");
		OrganizationsPage CNP = new OrganizationsPage(driver);
		CNP.getCreateNewOrgBtn().click();

		/* Step - enter all the details & create new organization */
		UtilityClassObject.getTest().log(Status.INFO, "create a new organization");
		CreatingNewOrganizationPage CNOP = new CreatingNewOrganizationPage(driver);

		CNOP.createOrg(orgName, shipAdd, Industry);
		Thread.sleep(2000);
		UtilityClassObject.getTest().log(Status.INFO, orgName + "======> Create a new Org");

		/* Step - Verify Header Message expected result */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNumberTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// read testscript data from the Excel file
		UtilityClassObject.getTest().log(Status.INFO, "read data from the excel");
		String orgName = eLib.getDataFromExcel("Contact", 7, 2).toString() + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("Org", 7, 3);
		String shipAdd = eLib.getDataFromExcel("Contact", 7, 4) + jLib.getRandomNumber();
		// Step 2 - Navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// step 3 - Click on "Create Organization" button

		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// Step 4 - Enter all the details & create new Organization

		UtilityClassObject.getTest().log(Status.INFO, "Create a new org with phone number");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg1(orgName, shipAdd, phoneNumber);

		// Verify Header phone number info expected result
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Phone Number");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));
		String actPhoneNumber = oip.getPhoneNoMsg().getText();
		Assert.assertEquals(actPhoneNumber, phoneNumber);
		UtilityClassObject.getTest().log(Status.PASS, actPhoneNumber + " ==>Verify the phone number");
	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustriesTest() throws EncryptedDocumentException, IOException, InterruptedException {
		// Read testscript data from ExcelFile
		UtilityClassObject.getTest().log(Status.INFO, "Read data from Excel");
		String orgName = eLib.getDataFromExcel("Contact", 7, 2) + jLib.getRandomNumber();
		String Industry = eLib.getDataFromExcel("Contact", 7, 6);
		String shipAdd = eLib.getDataFromExcel("Contact", 7, 4) + jLib.getRandomNumber();
		// String type= eLib.getDataFromExcel("Org", 7, 4);

		// Navigate to Organization module
		UtilityClassObject.getTest().log(Status.INFO, "Create a new Org");
		HomePage hp = new HomePage(driver);
		hp.getorgLink().click();

		// Step 3 - Click on "CreateOrganization" button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		UtilityClassObject.getTest().log(Status.INFO, "Create a new org with phone number");
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, shipAdd, Industry);

		// Step 4 - Verify Header Industry info expected result
		UtilityClassObject.getTest().log(Status.INFO, "Verify the Industry");
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		System.out.println(oip.getIndustryMsg().getText());
		String actIndustry = oip.getIndustryMsg().getText();
		Assert.assertEquals(true, actIndustry.contains(Industry));
		UtilityClassObject.getTest().log(Status.PASS, actIndustry + "=====>Verify the Industry");

	}
}
