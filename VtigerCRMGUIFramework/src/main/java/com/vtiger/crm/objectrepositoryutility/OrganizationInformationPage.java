package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;

	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInfo;

	@FindBy(id = "dtlview_Organization Name")
	private WebElement actualOrgName;

	@FindBy(id = "dtlview_Industry")
	private WebElement actualIndustry;

	@FindBy(id = "dtlview_Type")
	private WebElement actualType;

	@FindBy(id = "dtlview_Phone")
	private WebElement actualPhoneNum;

	@FindBy(xpath = "//a[text()='Contacts']")
	private WebElement contactLnk;

	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getActualPhoneNum() {
		return actualPhoneNum;
	}

	public WebElement getActualType() {
		return actualType;
	}

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getActualOrgName() {
		return actualOrgName;
	}

	public WebElement getActualIndustry() {
		return actualIndustry;
	}

}
