package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.crm.baseutility.BaseClass;
import com.vtiger.crm.objectrepositoryutility.ContactInformationPage;
import com.vtiger.crm.objectrepositoryutility.ContactsPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewContactPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.OrganaizationsPopUpPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createContactTest() throws Exception {
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactLnk().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastName().sendKeys(lastName);
		cncp.getSaveBtn().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String headerInfo = cip.getActualHeader().getText();

		Assert.assertTrue(headerInfo.contains(lastName));

		String actLastName = cip.getActualLastName().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actLastName, lastName);

	}

	@Test(groups = "regressionTest")
	public void createContActWithSupportDateTest() throws Exception {
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getContactLnk().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastName().sendKeys(lastName);

		cncp.getStartDate().clear();
		cncp.getStartDate().sendKeys(startDate);

		cncp.getEndDate().clear();
		cncp.getEndDate().sendKeys(endDate);

		cncp.getSaveBtn().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actualStartDate = cip.getActualStartDate().getText();
		String actualEndDate = cip.getActualEndDate().getText();

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualStartDate, startDate);
		sa.assertEquals(actualEndDate, endDate);

	}

	@Test(groups = "regressionTest")
	public void createContactWithOrganizationTest() throws Exception {

		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		cnop.getSaveBtn();

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		String headerInfo = oip.getHeaderInfo().getText();
		Assert.assertTrue(headerInfo.contains(orgName));

		oip.getContactLnk().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastName().sendKeys(lastName);
		cncp.getFindOrgImg().click();

		wLib.switchToTabOnURL(driver, "module=Accounts");
		OrganaizationsPopUpPage opp = new OrganaizationsPopUpPage(driver);
		opp.getSearchEdt().sendKeys(orgName);
		opp.getSearchBtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wLib.switchToTabOnURL(driver, "Contacts&action");
		cncp.getSaveBtn().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actualOrgName = cip.getActualOrgName().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualOrgName, orgName);

	}

}
