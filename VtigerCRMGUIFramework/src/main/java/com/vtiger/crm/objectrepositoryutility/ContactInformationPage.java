package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	WebDriver driver;

	public ContactInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement actualHeader;

	public WebElement getActualHeader() {
		return actualHeader;
	}

	@FindBy(id = "dtlview_Last Name")
	private WebElement actualLastName;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement actualStartDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement actualEndDate;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement actualOrgName;

	public WebElement getActualOrgName() {
		return actualOrgName;
	}

	public WebElement getActualLastName() {
		return actualLastName;
	}

	public WebElement getActualStartDate() {
		return actualStartDate;
	}

	public WebElement getActualEndDate() {
		return actualEndDate;
	}
	

}
