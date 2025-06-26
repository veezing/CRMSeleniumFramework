package com.vtiger.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLnk;

	@FindBy(linkText = "Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaignLnk;
	
	@FindBy(linkText = "More")
	private WebElement moreLnk;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLnk;

	public WebElement getOrgLnk() {
		return orgLnk;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}
	
	public WebElement getCampaignLnk() {
		return campaignLnk;
	}

	public WebElement getMoreLnk() {
		return moreLnk;
	}
	
	public WebElement getadminImg() {
		return adminImg;
	}
	
	public WebElement getSignOutLnk() {
		return signOutLnk;
	}
	
	public void navigateToCampaignPage() {
		Actions a = new Actions(driver);
		a.moveToElement(moreLnk).perform();
		campaignLnk.click();
	}
	
	public void logOut() {
		Actions a = new Actions(driver);
		a.moveToElement(adminImg).perform();
		signOutLnk.click();
	}

}
