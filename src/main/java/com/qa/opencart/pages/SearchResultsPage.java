package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utills.ElementUtil;

import open.qa.constants.AppConstants;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;

	
	
	// page constructor...
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(this.driver);// initialize eUtil driver
	}
	
	
	
	public ProductInfoPage selectProduct(String productName) {
		eUtil.waitForVisibilityOfElement(By.linkText(productName),AppConstants.LONG_DEFAULT_WAIT).click();
	    return new ProductInfoPage(driver);
	}
}
