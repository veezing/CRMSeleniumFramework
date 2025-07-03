package practiceDDT;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {
	@Test
	public void runtimeParameterTest() {
		
		String URL = System.getProperty("url");
		String BROWSER = System.getProperty("browser");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		System.out.println("Env Data==> URL ==>"+URL);
		System.out.println("Browser Data==> URL ==>"+BROWSER);
		System.out.println("Username Data==> URL ==>"+USERNAME);
		System.out.println("Password Data==> URL ==>"+PASSWORD);
	}

}
