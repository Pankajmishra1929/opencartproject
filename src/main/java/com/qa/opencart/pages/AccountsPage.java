package com.qa.opencart.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utills.ElementUtil;

import open.qa.constants.AppConstants;

public class AccountsPage {
	
	
	private WebDriver driver;
	private ElementUtil eUtil;
	
	//maintain by locator
	
	private By logoutLink= By.linkText("Logout");
	private By searchField= By.name("search");
	private By accHeaders= By.cssSelector("div#content h2");
	private By searchIcon=By.cssSelector("div#search button");
	
	
	// page constructor...
		public AccountsPage(WebDriver driver) {
			this.driver=driver;
			eUtil= new ElementUtil(this.driver);// initialize eUtil driver
		}

		
		
		//page actions/method...
		
		public String getAccPageTitle() {
			String title=eUtil.waitForTitle(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
			//String title= driver.getTitle();
			System.out.println("Account page title" +" " + title);
			return title;
		}
		
		public String getAccPageURL() {
			String url= eUtil.waitForURLContains(AppConstants.ACC_PAGE_URLFRACTION, AppConstants.SHORT_DEFAULT_WAIT);
			System.out.println("Acc page title" + url);
			return url;
		}
		
		public boolean isLogoutLinkExist() {
			return eUtil.waitForVisibilityOfElement(logoutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		}
		
		
		public void logout() {
			if (isLogoutLinkExist()) {
				eUtil.doClick(logoutLink);
			}
			
		}
		
		public boolean isSearchFieldExist() {
			return eUtil.waitForVisibilityOfElement(searchField, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		}
		
		
		public List<String> getAccountsHeaders() {
			List<WebElement> headersList=eUtil.waitForVisibilityOfElements(accHeaders, AppConstants.SHORT_DEFAULT_WAIT);
			List<String> headerValueList= new ArrayList<String>();
			for(WebElement e:headersList) {
				String text=e.getText();
				headerValueList.add(text);
			}
			return headerValueList;
		}
		
		public SearchResultsPage doSearch(String searchKey) {
			eUtil.waitForVisibilityOfElement(searchField, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
			   //button[@class='btn btn-default btn-lg']
			  //css selector  div#search button
			eUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);//TDD
			
		}
		
}
