package com.vtiger.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws Throwable {

		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		// read commondata from Properties file
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");

		// read Test script data from Excel file
		String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

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
		wLib.maximizeWindow(driver);
		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 2: Navigate to Organization Module
		driver.findElement(By.linkText("Organizations")).click();

		// Step 3: Click on "create organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Step 4: Enter all details and create organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header message expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName))
			System.out.println(orgName + " header verified==PASS");
		else
			System.out.println(orgName + " header NOT verified==FAIL");

		// Step 5: Navigate to Contact Module
		driver.findElement(By.linkText("Contacts")).click();

		// Step 6: Click on "create Contact" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 7: Enter all details and create Contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to child window
		wLib.switchToTabOnURL(driver, "module=Accounts");

		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch to parent window
		wLib.switchToTabOnURL(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		// verify header message expected result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(lastName))
			System.out.println(lastName + " header verified==PASS");
		else
			System.out.println(lastName + " header NOT verified==FAIL");

		// verify last name expected result
		String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println("Organization name is " + orgName);
		System.out.println("Actual is: " + actualOrgName);
		if (actualOrgName.contains(orgName))
			System.out.println(orgName + " information is verified==PASS");
		else
			System.out.println(orgName + " information is NOT verified==FAIL");

		// Step 5: Logout
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.moveMouseOnElement(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
