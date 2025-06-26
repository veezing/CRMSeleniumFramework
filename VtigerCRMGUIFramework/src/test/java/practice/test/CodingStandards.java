package practice.test;
/**
 * test class for contact module
 * @author vaibh
 */

import org.testng.annotations.Test;

import com.vtiger.crm.objectrepositoryutility.LoginPage;

public class CodingStandards extends BaseClass {
	/*
	 *Scenario: login() ==> navigateContact==> createContact() ==> verify 
	 */
	@Test
	public void searchContactTest() {
		/*step 1: login to app */
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
	
	

}
