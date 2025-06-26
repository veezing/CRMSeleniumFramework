package com.vtiger.crm.organistationtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationWithIndustryTestUsingPOM {
	public static void main(String[] args) throws Exception {

		// read commondata from Properties file
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read Test script data from Excel file

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		WebDriver driver = null;
		if (BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if (BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else if (BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();

		// STEP 1: Login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);

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
		if (actualIndustry.equals(industry))
			System.out.println(industry + " information is verified==PASS");
		else
			System.out.println(industry + " information is NOT verified==FAIL");

		String actualType = oip.getActualType().getText();
		if (actualType.equals(type))
			System.out.println(type + " information is created==PASS");
		else
			System.out.println(type + " information is NOT created==FAIL");

		// Step 5: Logout
		hp.logOut();
		driver.quit();

	}

}
