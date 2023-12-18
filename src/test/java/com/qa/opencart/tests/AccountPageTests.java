package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import open.qa.constants.AppConstants;

public class AccountPageTests extends BaseTest{
	
    // get url is the global precondition for all pages
	// login is the precondition for Account page
	
	@BeforeClass
	public void accSetup() {
		//accPage= new AccountsPage(driver);// accPage we can access by any child class as defined in Base class
		
		// to access login page
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	
	public void accPageTitleTest() {
		Assert.assertEquals(accPage.getAccPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
		
	}
	
	@Test
	public void accPageURlTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URLFRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	
	@Test
	public void accPageHeadersCountTest() {
		List<String> actAccPageHeadersList=accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		//How to compare the list with expected
		// create expected list in constants Array.asList
		//Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		
		Assert.assertEquals(actAccPageHeadersList.size(),AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
		
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList=accPage.getAccountsHeaders();
		System.out.println(actAccPageHeadersList);
		//How to compare the list with expected
		// create expected list in constants Array.asList
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
		// Assignment
		//sort the actual list
		// sort the expected list
		//compare
	}
	
	@Test
	public void searchTest() {
		searchResultsPage=accPage.doSearch("mac book");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		String accProductHeader=productInfoPage.getProductHeaderName();
		Assert.assertEquals(accProductHeader, "MacBook Pro");
		
	}
}
