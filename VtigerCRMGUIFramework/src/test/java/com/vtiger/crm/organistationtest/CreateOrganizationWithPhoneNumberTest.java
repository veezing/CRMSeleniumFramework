package com.vtiger.crm.organistationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationWithPhoneNumberTest {

	@Test
	public void createOrgTest() throws Exception {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String browser = fLib.getDataFromPropertiesFile("browser");
		String url = fLib.getDataFromPropertiesFile("url");
		String username = fLib.getDataFromPropertiesFile("username");
		String password = fLib.getDataFromPropertiesFile("password");

		String orgName = eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		WebDriver driver;

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
		cnop.createOrg(orgName, industry, type, phoneNumber);
		
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualPhoneNum = oip.getActualPhoneNum().getText();
		if(actualPhoneNum.equals(phoneNumber))
			System.out.println(phoneNumber+" info is Verified==> PASS");
		else
			System.out.println(phoneNumber+" info is NOT Verified==> FAIL");
		
		hp.logOut();
		driver.quit();
			

	}

}
