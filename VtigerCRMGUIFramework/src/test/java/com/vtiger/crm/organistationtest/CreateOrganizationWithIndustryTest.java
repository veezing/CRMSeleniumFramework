package com.vtiger.crm.organistationtest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustryTest {
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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

		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select s1 = new Select(industryDropdown);
		s1.selectByVisibleText(industry);

		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select s2 = new Select(typeDropdown);
		s2.selectByVisibleText(type);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify industry and type info
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.equals(industry))
			System.out.println(industry + " information is verified==PASS");
		else
			System.out.println(industry + " information is NOT verified==FAIL");

		String actualType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actualType.equals(type))
			System.out.println(type + " information is created==PASS");
		else
			System.out.println(type + " information is NOT created==FAIL");

		// Step 5: Logout
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}

}
