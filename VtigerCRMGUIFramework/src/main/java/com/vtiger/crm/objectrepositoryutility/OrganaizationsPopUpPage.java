package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganaizationsPopUpPage {
	WebDriver driver;
	public OrganaizationsPopUpPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id = "search_txt")
	private WebElement searchEdt;
	
	@FindBy(name = "search_field")
	private WebElement searchDropdown;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDropdown() {
		return searchDropdown;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}
	
	

}
