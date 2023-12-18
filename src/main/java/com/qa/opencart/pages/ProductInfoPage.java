package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.ElementUtil;

import open.qa.constants.AppConstants;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eUtil;
	

	private By productHeader = By.tagName("h1");
	//div#content h1   // css selector 
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceInfo= By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	//private Map<String, String> productMap = new HashMap<String, String>();
	//private Map<String, String> productMap = new LinkedHashMap<String, String>();// Maintain the order of insertion
	private Map<String, String> productMap = new TreeMap<String, String>();// Maintain alphabetical order
    
	
	// page constructor...
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eUtil= new ElementUtil(this.driver);// initialize eUtil driver
		
	}
	
	
	public String getProductHeaderName() {
		String productHeaderValue=eUtil.doElementGetText(productHeader);
		System.out.println("Product Header" + productHeaderValue);
		return productHeaderValue;
			
	}
	
	
	public int getProductImagesCount() {
	int productImgCount= eUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		
	System.out.println( "Product" + getProductHeaderName() + " imagesg count :" +  productImgCount);
	
	return productImgCount;
	}
	
	
	private void getProductMetaData() {
		//data in key value format
		// for key value use concept of hash map
		
		List<WebElement> metaDataList=eUtil.waitForVisibilityOfElements(productMetaData, AppConstants.LONG_DEFAULT_WAIT);
	     for (WebElement e: metaDataList) {
	    	 String metaData=e.getText().trim();// Bramd:Apple
	    	 System.out.println(metaData);
	    	 String metaKey= metaData.split(":")[0].trim();
	    	 //System.out.println(metaKey);
	    	 String metaValue= metaData.split(":")[1].trim();
	    	 System.out.println(metaValue);
	    	 productMap.put(metaKey, metaValue);
	    	
	     }
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eUtil.waitForVisibilityOfElements(productPriceInfo, AppConstants.LONG_DEFAULT_WAIT);
		String productPrice=metaPriceList.get(0).getText();
		System.out.println(productPrice);
		
		String productExTaxPrice=  metaPriceList.get(1).getText().split(":")[1].trim();
		
		 productMap.put("price", productPrice);
		 productMap.put("exTaxPrice", productExTaxPrice);
	
	}

	public Map<String, String> getProductDetails() {
		productMap.put("productName",getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		
		System.out.println(productMap);
		return productMap;
	}
	
}
