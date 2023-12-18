package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductResultsPageTest extends BaseTest {

	
	
	@BeforeClass
	public void accSetup() {
		//accPage= new AccountsPage(driver);// accPage we can access by any child class as defined in Base class
		
		// to access login page
		accPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	
	@Test
	public void productImagesTest() {
		searchResultsPage=accPage.doSearch("mac book");
		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		
		int accImgCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(accImgCount, 4);
	}
	
	@Test
	public void productInfoTest() {
//		searchResultsPage=accPage.doSearch("mac book");
//		productInfoPage=searchResultsPage.selectProduct("MacBook Pro");
		
		Map<String, String> productDetailsMap= productInfoPage.getProductDetails();
		
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
	    
	    
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("exTaxPrice"), "$2,000.00");
		
		softAssert.assertAll();
	    
	
	}
	
}
