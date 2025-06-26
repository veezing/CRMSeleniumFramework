package practice.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConfigAnnotations {
	
	@BeforeSuite
	public void configBS() {
		System.out.println("Execute Before Suite");
	}
	
	@BeforeClass
	public void configBC() {
		System.out.println("Execute Before Class");
	}
	
	@BeforeMethod
	public void configBM() {
		System.out.println("Execute Before Method");
	}
	
	@Test
	public void createContact() {
		System.out.println("Create Contact");
	}
	
	@Test
	public void createContactWithDate() {
		System.out.println("Create Contact With Date");
	}
	
	@AfterSuite
	public void configAS() {
		System.out.println("Execute After Suite");
	}
	
	@AfterClass
	public void configAC() {
		System.out.println("Execute After Class");
	}
	
	@AfterMethod
	public void configAM() {
		System.out.println("Execute After Method");
	}

}
