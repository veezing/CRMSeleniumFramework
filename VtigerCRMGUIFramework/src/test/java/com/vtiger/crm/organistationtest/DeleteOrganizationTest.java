package com.vtiger.crm.organistationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationInformationPage;
import com.vtiger.crm.objectrepositoryutility.OrganizationPage;

public class DeleteOrganizationTest {
	public static void main(String[] args) throws Exception {

		// read commondata from Properties file
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read Test script data from Excel file
		String orgName = eLib.getDataFromExcel("org", 10, 2) + jLib.getRandomNumber();
		String dropDownData = eLib.getDataFromExcel("org", 10, 3);

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
		cnop.createOrg(orgName);

		// verify header message expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String headerInfo = oip.getHeaderInfo().getText();
		if (headerInfo.contains(orgName))
			System.out.println(orgName + " header verified==PASS");
		else
			System.out.println(orgName + " header NOT verified==FAIL");

		// verify org name expected result
		String actualOrgName = oip.getActualOrgName().getText();
		if (actualOrgName.equals(orgName))
			System.out.println(orgName + " information is created==PASS");
		else
			System.out.println(orgName + " information is NOT created==FAIL");

		// go back to organizations page
		hp.getOrgLnk().click();

		// search for organization
		op.getSearchEdt().sendKeys(orgName);
		wLib.selectFromDropdown(op.getSearchDropDown(), dropDownData);
		op.getSearchNowBtn().click();

		// in dynamic web table select & delete organization
		driver.findElement(By.xpath("//a[text()='" + orgName + "']/../../td[8]/a[text()='del']")).click();

		wLib.switchToAlertAndAccept(driver);

		// Step 5: Logout

		hp.logOut();

		driver.quit();

	}

}
