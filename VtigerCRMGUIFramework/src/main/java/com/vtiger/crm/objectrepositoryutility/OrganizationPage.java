package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	WebDriver driver;

	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgBtn;
	
	@FindBy(name = "search_text")
	private WebElement searchEdt;
	
	@FindAll({@FindBy(id = "bas_searchfield"), @FindBy(name = "search_field")})
	private WebElement searchDropDown;
	
	@FindBy(name = "submit")
	private WebElement searchNowBtn;

	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDropDown() {
		return searchDropDown;
	}
	
	public WebElement getSearchNowBtn() {
		return searchNowBtn;
	}
	

}