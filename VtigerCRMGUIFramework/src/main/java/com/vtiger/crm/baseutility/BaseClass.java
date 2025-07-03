package com.vtiger.crm.baseutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.crm.generic.databaseutility.DatabaseUtility;
import com.vtiger.crm.generic.fileutility.ExcelUtility;
import com.vtiger.crm.generic.fileutility.FileUtility;
import com.vtiger.crm.generic.webdriverutility.JavaUtility;
import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
import com.vtiger.crm.objectrepositoryutility.HomePage;
import com.vtiger.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	public ExcelUtility eLib = new ExcelUtility();
	public DatabaseUtility dbLib = new DatabaseUtility();
	public FileUtility fLib = new FileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();

	

	@BeforeSuite(groups = {"smokeTest", "regressionTest"})
	public void configBS() throws Exception {
		System.out.println("====Connect to DB==== , Report Config");
		dbLib.getDbConnnection();
		
	}
	
//@Parameters("BROWSER")
	@BeforeClass(groups = {"smokeTest", "regressionTest"})
	//public void configBC(String BROWSER) throws Exception {
	public void configBC() throws Exception {	
		System.out.println("====launch browser====");
		//String browser = BROWSER;
		//String browser = fLib.getDataFromPropertiesFile("browser");
		String browser = System.getProperty("browser", fLib.getDataFromPropertiesFile("browser"));

		if (browser.equals("chrome"))
			driver = new ChromeDriver();
		else if (browser.equals("edge"))
			driver = new EdgeDriver();
		else if (browser.equals("firefox"))
			driver = new FirefoxDriver();
		else
			driver = new ChromeDriver();
		sdriver = driver;
	}

	@BeforeMethod(groups = {"smokeTest", "regressionTest"})
	public void configBM() throws Exception {
		System.out.println("login");
		//String url = fLib.getDataFromPropertiesFile("url");
		//String username = fLib.getDataFromPropertiesFile("username");
		//String password = fLib.getDataFromPropertiesFile("password");
		
		String url = System.getProperty("url",fLib.getDataFromPropertiesFile("url"));
		String username = System.getProperty("username",fLib.getDataFromPropertiesFile("username"));
		String password = System.getProperty("password",fLib.getDataFromPropertiesFile("password"));
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(url, username, password);
	}

	@AfterMethod(groups = {"smokeTest", "regressionTest"})
	public void configAM() {
		System.out.println("logout");
		HomePage hp = new HomePage(driver);
		hp.logOut();

	}

	@AfterClass(groups = {"smokeTest", "regressionTest"})
	public void configAC() {
		System.out.println("====close browser====");
		driver.quit();
	}

	@AfterSuite(groups = {"smokeTest", "regressionTest"})
	public void configAS() {
		System.out.println("====Close DB==== , Report Backup");
		
	}

}
