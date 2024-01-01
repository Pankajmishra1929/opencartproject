package com.qa.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utills.ElementUtil;

import io.qameta.allure.Step;
import open.qa.constants.AppConstants;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	//By locators :OR
	private By userName= By.id("input-email");
	private By password= By.id("input-password");
	private By loginBtn= By.xpath("//input[@value='Login']");
	private By forgotPwdLink= By.linkText("Forgotten Password11");
	private By logo= By.cssSelector("img[title='naveenopencart']");
	
	// page constructor...
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(this.driver);// initialize eUtil driver
	}
	
	//page actions/methods:
	@Step("getting login page title")
	public String getLoginPageTitle() {
		String title=eUtil.waitForTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		//String title= driver.getTitle();
		System.out.println("page title" + title);
		return title;
	}
	
	@Step("getting login page url..")
	public String getLoginPageUrl() {
		String url= eUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URLFRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("page url" + url);
		return url;
	}
	
	@Step("checking forgot pwd link")
	public boolean isForgotPwdLinkExist() {
		return eUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	
	@Step("checking logo exit or not")
	public boolean isLogoExist() {
		return eUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		//return driver.findElement(logo).isDisplayed();
	}
	
	@Step("User name is: {0} and Password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		eUtil.waitForVisibilityOfElement(userName, AppConstants.LONG_DEFAULT_WAIT).sendKeys(username);;
		eUtil.doSendKeys(password, pwd);
		eUtil.doClick(loginBtn);
		
		//driver.findElement(userName).sendKeys(username);
		//driver.findElement(password).sendKeys(pwd);
		//driver.findElement(loginBtn).click();
//		System.out.println("user is logged in");
//		return true;
		return new AccountsPage(driver);
	}
	
}
