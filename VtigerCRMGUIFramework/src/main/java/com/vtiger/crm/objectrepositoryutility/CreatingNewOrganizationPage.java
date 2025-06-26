package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;

	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement orgNameEdt;

	@FindBy(id = "phone")
	private WebElement phoneNumberEdt;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name = "industry")
	private WebElement industryDropdown;

	@FindBy(name = "accounttype")
	private WebElement typeDropdown;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getPhoneNumberEdt() {
		return phoneNumberEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public void createOrg(String orgName) {
		orgNameEdt.sendKeys(orgName);
		saveBtn.click();

	}

	public void createOrg(String orgName, String industry) {
		orgNameEdt.sendKeys(orgName);
		Select s = new Select(industryDropdown);
		s.selectByVisibleText(industry);
		saveBtn.click();

	}

	public void createOrg(String orgName, String industry, String type) {
		orgNameEdt.sendKeys(orgName);
		Select s1 = new Select(industryDropdown);
		s1.selectByVisibleText(industry);

		Select s2 = new Select(typeDropdown);
		s2.selectByVisibleText(type);
		saveBtn.click();

	}

	public void createOrg(String orgName, String industry, String type, String phoneNumber) {
		orgNameEdt.sendKeys(orgName);
		phoneNumberEdt.sendKeys(phoneNumber);

		Select s1 = new Select(industryDropdown);
		s1.selectByVisibleText(industry);

		Select s2 = new Select(typeDropdown);
		s2.selectByVisibleText(type);
		saveBtn.click();

	}

}
