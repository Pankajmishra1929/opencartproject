package com.qa.opencart.tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import open.qa.constants.AppConstants;
@Epic("Epic 100: Design openCart loginpage")
@Story("US 101:Login page feature")
@Feature("F50: Feature login page")


public class LoginPageTest extends BaseTest{

	@Description("login page title test...")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	
	@Description("login page URL test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageURLTest() {
		String actURL=loginPage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URLFRACTION));
		
	}
	
	@Description("verifying forget pwd link...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void fogotPWDLinkExistTest() {
			Assert.assertTrue(loginPage.isForgotPwdLinkExist());
			
		
	}
	
	@Description("verifying application logo...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	
}
	
	@Description("verifying user able to login with correct user id and pwd...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=5)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
		
	
}	
	
}
