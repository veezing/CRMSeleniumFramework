package practice.pom.repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTestWithoutPOM {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/");
		
		WebElement element1 = driver.findElement(By.name("user_name"));
		WebElement element2 = driver.findElement(By.name("user_password"));
		WebElement element3 = driver.findElement(By.id("submitButton"));
		
		element1.sendKeys("admin");
		element2.sendKeys("admin");
		
		driver.navigate().refresh();
		
		element1.sendKeys("admin");
		element2.sendKeys("admin");
		
		element3.click();
	}

}
