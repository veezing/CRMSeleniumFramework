package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.crm.baseutility.BaseClass;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Exception {

		// read Test script data from Excel file
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// Step 2: Navigate to Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		// Step 3: Click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		// verify header message expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headerInfo = oip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(orgName));

		// verify org name expected result
		String actualOrgName = oip.getActualOrgName().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualOrgName, orgName);

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustryTest() throws Exception {

		// read Test script data from Excel file

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// Step 2: Navigate to Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		// Step 3: Click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// Step 4: Enter all details and create organization
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// verify industry and type info
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualIndustry = oip.getActualIndustry().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(actualIndustry.contains(industry));
		

		String actualType = oip.getActualType().getText();
		sa.assertTrue(actualType.contains(type));

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithPhoneNumberTest() throws Exception {
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type, phoneNumber);

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualPhoneNum = oip.getActualPhoneNum().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(actualPhoneNum.contains(phoneNumber));

	}

}
