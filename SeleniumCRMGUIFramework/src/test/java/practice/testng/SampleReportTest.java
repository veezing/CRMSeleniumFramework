package practice.testng;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {
	ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// Spark Report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Result");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env Info & Create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("Browser", "Chrome");
	}

	@AfterSuite
	public void configAS() {
		report.flush();
	}

	@Test
	public void createContactTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		TakesScreenshot eDriver = (TakesScreenshot) driver;
		String filePath = eDriver.getScreenshotAs(OutputType.BASE64);

		ExtentTest test = report.createTest("createContactTest");
		test.log(Status.INFO, "Login to App");
		test.log(Status.INFO, "Navigate to App");
		test.log(Status.INFO, "Create Contact");
		if ("HDgFC".equals("HDFC"))
			test.log(Status.PASS, "Contact is Created");
		else
			test.addScreenCaptureFromBase64String(filePath, "ErrorFile");
		
		driver.close();

	}
	@Test
	public void createContactWithOrgTest() {

		ExtentTest test = report.createTest("createContactWithOrgTest");
		test.log(Status.INFO, "Login to App");
		test.log(Status.INFO, "Navigate to App");
		test.log(Status.INFO, "Create Contact");
		if ("HDFC".equals("HDFC"))
			test.log(Status.PASS, "Contact is Created");
		else
			test.log(Status.FAIL, "Contact is NOT Created");

	}
	@Test
	public void createContactWithPhoneNumberTest() {

		ExtentTest test = report.createTest("createContactWithPhoneNumberTest");
		test.log(Status.INFO, "Login to App");
		test.log(Status.INFO, "Navigate to App");
		test.log(Status.INFO, "Create Contact");
		if ("HDFC".equals("HDFC"))
			test.log(Status.PASS, "Contact is Created");
		else
			test.log(Status.FAIL, "Contact is NOT Created");

	}

}
