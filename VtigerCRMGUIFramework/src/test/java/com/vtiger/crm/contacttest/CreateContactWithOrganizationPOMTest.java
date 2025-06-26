package com.vtiger.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.ContactInformationPage;
import com.vtiger.crm.objectrepositoryutility.ContactsPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewContactPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganaizationsPopUpPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactWithOrganizationPOMTest {

	@Test
	public void createContactWithOrganizationTest() throws Exception {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");

		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("contact", 7, 3) + jlib.getRandomNumber();

		WebDriver driver = null;
		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(url, username, password);

		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		cnop.getSaveBtn();

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		String headerInfo = oip.getHeaderInfo().getText();
		if (headerInfo.contains(orgName))
			System.out.println(orgName + " header verified==PASS");
		else
			System.out.println(orgName + " header NOT verified==FAIL");

		oip.getContactLnk().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastName().sendKeys(lastName);
		cncp.getFindOrgImg().click();

		wlib.switchToTabOnURL(driver, "module=Accounts");
		OrganaizationsPopUpPage opp = new OrganaizationsPopUpPage(driver);
		opp.getSearchEdt().sendKeys(orgName);
		opp.getSearchBtn().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		wlib.switchToTabOnURL(driver, "Contacts&action");
		cncp.getSaveBtn().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actualOrgName = cip.getActualOrgName().getText();
		if (actualOrgName.contains(orgName))
			System.out.println(orgName + " information is verified==PASS");
		else
			System.out.println(orgName + " information is NOT verified==FAIL");

		hp.logOut();
		driver.quit();

	}

}
