package com.vtiger.crm.contacttest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.objectrepositoryutility.ContactInformationPage;
import com.vtiger.crm.objectrepositoryutility.ContactsPage;
import com.vtiger.crm.objectrepositoryutility.CreatingNewContactPage;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDatePOMTest {
	@Test
	public void createConatct() throws Exception {
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();

		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");

		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

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
		hp.getContactLnk().click();

		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateContactBtn().click();

		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastName().sendKeys(lastName);

		cncp.getStartDate().clear();
		cncp.getStartDate().sendKeys(startDate);

		cncp.getEndDate().clear();
		cncp.getEndDate().sendKeys(endDate);

		cncp.getSaveBtn().click();

		ContactInformationPage cip = new ContactInformationPage(driver);
		String actualStartDate = cip.getActualStartDate().getText();
		if (actualStartDate.equals(startDate))
			System.out.println(startDate + " information is verified==PASS");
		else
			System.out.println(startDate + " information is NOT verified==FAIL");

		String actualEndDate = cip.getActualEndDate().getText();
		if (actualEndDate.equals(endDate))
			System.out.println(endDate + " information is verified==PASS");
		else
			System.out.println(endDate + " information is NOT verified==FAIL");

		hp.logOut();
		driver.quit();

	}

}
