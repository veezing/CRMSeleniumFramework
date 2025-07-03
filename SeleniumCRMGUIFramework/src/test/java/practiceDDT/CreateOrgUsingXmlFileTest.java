package practiceDDT;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class CreateOrgUsingXmlFileTest {
	
	@Test
	public void createOrg(XmlTest test) throws Exception {
		
		//read common data from xml file		
		String URL = test.getParameter("url");
		String BROWSER = test.getParameter("browser");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		

		//Genarate random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//read Test script data from Excel file
		FileInputStream f = new FileInputStream("./src/test/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(f);
		String orgName = wb.getSheet("org").getRow(1).getCell(2).toString()+randomInt;
		wb.close();
		
		WebDriver driver;
		if(BROWSER.equals("chrome")) 
			driver= new ChromeDriver();
		else if(BROWSER.equals("edge"))
			driver = new EdgeDriver();
		else if(BROWSER.equals("firefox"))
			driver=new FirefoxDriver();
		else
			driver=new ChromeDriver();
		
		
		//STEP 1: Login to App
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 2: Navigate to Organization Module
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 3: Click on "create organisation" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 4: Enter all details and create organisation
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 5: Logout
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
		
		
	}

}
