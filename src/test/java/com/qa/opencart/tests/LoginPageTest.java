package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import open.qa.constants.AppConstants;

public class LoginPageTest extends BaseTest{

	
	@Test
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
		
	}
	@Test
	public void loginPageURLTest() {
		String actURL=loginPage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_URLFRACTION));
		
	}
	@Test
	public void fogotPWDLinkExistTest() {
			Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		
	}
	@Test
	public void appLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	
}
	@Test
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
		
	
}	
	
}
