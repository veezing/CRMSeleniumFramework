package practiceDDT;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumTestReadDataFromRuntimeTest {
	@Test
	public void seleniumTest() {
		
		String URL = System.getProperty("url");
		String BROWSER = System.getProperty("browser");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		System.out.println("url is "+URL);
		System.out.println("browser is "+BROWSER);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		
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
		
		driver.findElement(By.name("staff_id")).sendKeys(USERNAME);
		driver.findElement(By.name("password")).sendKeys(PASSWORD);
		driver.findElement(By.name("staff_login-btn")).click();
		
	}

	
}
