package practice.contacttest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws Throwable {
		//read commondata from Properties file
				FileInputStream fis = new FileInputStream("./src/test/resources/commondata.properties");
				Properties p = new Properties();
				p.load(fis);
				
				String BROWSER = p.getProperty("browser");
				String URL = p.getProperty("url");
				String USERNAME = p.getProperty("username");
				String PASSWORD = p.getProperty("password");
				
				//Genarate random number
				Random random = new Random();
				int randomInt = random.nextInt(1000);
				
				//read Test script data from Excel file
				FileInputStream f = new FileInputStream("./src/test/resources/testScriptData.xlsx");
				Workbook wb = WorkbookFactory.create(f);
				String orgName = wb.getSheet("contact").getRow(7).getCell(2).toString()+randomInt;
				String lastName = wb.getSheet("contact").getRow(7).getCell(3).toString()+randomInt;
				wb.close();
				
				WebDriver driver= null;
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
				
				//Step 3: Click on "create organization" button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Step 4: Enter all details and create organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verify header message expected result
				String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgName))
					System.out.println(orgName+" header verified==PASS");
				else
					System.out.println(orgName+" header NOT verified==FAIL");
				
				//Step 5: Navigate to Contact Module
				driver.findElement(By.linkText("Contacts")).click();
				
				//Step 6: Click on "create Contact" button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//Step 7: Enter all details and create Contact
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
				
				//Switch to child window
				Set<String> windowHandles = driver.getWindowHandles();
				for(String id:windowHandles) {
					driver.switchTo().window(id);
					
					String currentUrl = driver.getCurrentUrl();
					if(currentUrl.contains("module=Accounts"))
						break;
				}
				
				driver.findElement(By.id("search_txt")).sendKeys(orgName);
				driver.findElement(By.name("search")).click();
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				
				//switch to parent window
				Set<String> windowHandles1 = driver.getWindowHandles();
				for(String id:windowHandles1) {
					driver.switchTo().window(id);
					
					String currentUrl = driver.getCurrentUrl();
					if(currentUrl.contains("Contacts&action"))
						break;
				}
				
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				//verify header message expected result
				headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(lastName))
					System.out.println(lastName+" header verified==PASS");
				else
					System.out.println(lastName+" header NOT verified==FAIL");
				
				//verify last name expected result
				String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				System.out.println("Organization name is "+orgName);
				System.out.println("Actual is: "+actualOrgName);
				if(actualOrgName.contains(orgName))
					System.out.println(orgName+" information is verified==PASS");
				else
					System.out.println(orgName+" information is NOT verified==FAIL");
				
				//Step 5: Logout
				Actions a = new Actions(driver);
				a.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				driver.quit();
	}

}
