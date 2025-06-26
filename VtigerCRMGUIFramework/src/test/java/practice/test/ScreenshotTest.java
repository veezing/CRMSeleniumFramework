package practice.test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class ScreenshotTest {
	@Test
	public void flipkartTest() throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.flipkart.com/");

		// explicit type casting
		TakesScreenshot t = (TakesScreenshot) driver;
		// use screenshotAs()
		File src = t.getScreenshotAs(OutputType.FILE);
		// create a new file
		File dest = new File("./Screenshot/flipkart.png");
		// copy
		Files.copy(src, dest);
	}

}
