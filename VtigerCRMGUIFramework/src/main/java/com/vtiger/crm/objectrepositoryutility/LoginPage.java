package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author vaibh
 * 
 * contains Login page elements & business lib like login()
 */
public class LoginPage extends WebDriverUtility {
	WebDriver driver;
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Rule-1 Create A Separate java class
	//Rule-2 Object Creation
	
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	//Rule-3 Object Initialization
	//Rule-4 Object Encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule-5 Provide Action
	
	/**
	 * login to application based username, password, url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		maximizeWindow(driver);
		waitForPageToLoad(driver);
		driver.get(url);
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}

}
