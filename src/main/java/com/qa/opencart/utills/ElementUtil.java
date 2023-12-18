package com.qa.opencart.utills;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

public class ElementUtil {
	private WebDriver driver;// it initialize null to avoid NPE initialize with constructor
	// make it private as no one can access out of the class and change the vlaue.
	// private variable access by public constructor---> Encapsulation

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String locatorValue) {
		By by = null;
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;

		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;

		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "cssselector":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			by = By.tagName(locatorValue);
			break;

		default:
			System.out.println("Wrong locator passed..." + locatorValue);
			throw new FrameworkException("Wrong locator type");
		     // break;
		}
		return by;
	}
	// overloading
	// locatory type= "id", locatoryvalue= "input_email",
	// locatorvalue="tom@123gmail.com"

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);

	}

	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	public String doElementgetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}

	public String dogetElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	// WAF : to capture the text of all the page link and return List<String>

	public List<String> getElementsText(By locator) {
		List<WebElement> elist = getElements(locator);
		List<String> eleTextList = new ArrayList<String>(); // physical capacity c=0

		for (WebElement e : elist) {
			String TextList = e.getText();

			if (TextList.length() != 0) {
				eleTextList.add(TextList);
			}

		}
		return eleTextList;
	}

	// WAF to capture specific Attribute from the list

	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleAttrValue = e.getAttribute(attrName);
			if (eleAttrValue.isBlank()) {
				eleAttrList.add(eleAttrValue);
			}
		}
		return eleAttrList;

	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void search(By searchField, By suggestions, String searchKey, String suggName) throws InterruptedException {
		// driver.findElement(searchField).sendKeys(searchKey);
		doSendKeys(searchField, searchKey);
		Thread.sleep(3000);

		// List<WebElement> suggList = driver.findElements(suggestions);
		List<WebElement> suggList = getElements(suggestions);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}

	// to check specific element available in page or not
	public boolean checkSingleElementPresent(By locator) {
		return driver.findElements(locator).size() == 1 ? true : false;
	}

	public boolean checkElementPresent(By locator) {
		return driver.findElements(locator).size() >= 1 ? true : false;
	}

	public boolean checkElementPresent(By locator, int totalElements) {
		return driver.findElements(locator).size() == totalElements ? true : false;
	}

	public void clickOnElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);

		System.out.println(eleList.size());

		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(eleText)) {
				e.click();
				break;
			}

		}

	}

	// *************Select DropDown Utils*************//

	private Select createSelect(By locator) { // Make it private as no one can create object and modify the value
		Select select = new Select(getElement(locator));
		return select;
	}

	public void doSelectDropDownByIndex(By locator, int index) {

		// Select select = new Select(getElement(locator));
		createSelect(locator).selectByIndex(index);
		// select.selectByIndex(index);
	}

	public void doSelectDropDownByVisibleText(By locator, String visibleText) {

//		Select select = new Select(getElement(locator));
//		select.selectByVisibleText(visibleText);
		createSelect(locator).selectByVisibleText(visibleText);
	}

	public void doSelectDropDownByVisibleValue(By locator, String value) {

//		Select select = new Select(getElement(locator));
//		select.selectByValue(value);
		createSelect(locator).deselectByValue(value);
	}

	public int getDropDownOptionsCount(By locator) {
//		Select select = new Select(getElement(locator));
//		System.out.println(select.getOptions().size());
		return createSelect(locator).getOptions().size();
	}

	public List<String> getDropDownOptions(By locator) {
		// Select select = new Select(getElement(locator));
		List<WebElement> optionList = createSelect(locator).getOptions();// return all the list of options belong to
																			// select tag
		List<String> optionTextList = new ArrayList<String>();
		for (WebElement e : optionList) {
			String countryName = e.getText();
			optionTextList.add(countryName);

		}
		return optionTextList;
	}

	public void selectDropDownOptions(By locator, String dropDownValue) {
		// Select select = new Select(getElement(locator));
		List<WebElement> countryList = createSelect(locator).getOptions();// return all the list of options belong to
																			// select tag
		for (WebElement e : countryList) {
			String countryName = e.getText();
			if (countryName.equals(dropDownValue)) {
				e.click();
				break;
			}
		}

	}

// Without select method
	public void selectDropDownValue(By locator, String value) {

		List<WebElement> optionList = getElements(locator);

		for (WebElement e : optionList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public boolean isDropDownMultiple(By locator) {
		// Select select = new Select(getElement(locator));
		// Select select=createSelect(locator);
		return createSelect(locator).isMultiple() ? true : false;
	}

	/**
	 * This method is used to select value from dropdown 1. Single selection
	 * 2.Multiple selection 3.All for all need to pas "all" as a value of selector
	 * 
	 * @param locator
	 * @return
	 */

	public void selectDropDownMultipleValues(By locator, By optionLocator, String... values) {
		// problem statement how to pass multiple value in function
		// Select select = new Select(getElement(locator));
		// List<String> eleValueList = new ArrayList<String>();
		if (isDropDownMultiple(locator)) {

			if (values[0].equalsIgnoreCase("all")) {
				// List<WebElement> options = select.getOptions();

				List<WebElement> optionList = driver.findElements(optionLocator);
				for (WebElement e : optionList) {
					// String eleValuesValue = e.getText();
					// eleValueList.add(eleValueValue);
					// select.selectByVisibleText(eleValuesValue);

					e.click();
				}
			}

			else {
				for (String value : values) {
					createSelect(locator).selectByVisibleText(value);

				}
			}

		}

	}

	// ********************Action Utils***************//
	public void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();
		Thread.sleep(2000);
		doClick(childMenuLocator);
		// driver.findElement(childMenuLocator).click();
	}

	public void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocator, By secondChildMenuLocator,
			By thirdChildMenuLocator) throws InterruptedException {

		Actions act = new Actions(driver);
		// driver.findElement(parentMenuLocator).click();
		doClick(parentMenuLocator);
		Thread.sleep(500);

		act.moveToElement(getElement(firstChildMenuLocator)).perform();
		Thread.sleep(500);
		act.moveToElement(getElement(secondChildMenuLocator)).perform();
		Thread.sleep(500);
		// driver.findElement(thirdChildMenuLocator).click();
		doClick(thirdChildMenuLocator);

	}

	public void doActionSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();

	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();

	}	
	
	/********* Wait Util**********************************************************/
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	public WebElement waitForPresenceOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible
	 * 
	 * @param locator
	 * @param timeout
	 * @param intervalTime
	 * @return
	 */
	
	public WebElement waitForPresenceOfElement(By locator, int timeout, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */

	
	public WebElement waitForVisibilityOfElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}
	
	/**
	 * An expectation for checking that an elements is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * @param locator
	 * @param timeout
	 * @param intervalTime
	 * @return
	 */
	
	public WebElement waitForVisibilityOfElement(By locator, int timeout, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void doClickWithWait(By locator, int timeout) {
		waitForVisibilityOfElement(locator, timeout).click();
	}

	public void doSendKeyWithWait(By locator, String value, int timeout) {
		waitForVisibilityOfElement(locator, timeout).sendKeys(value);
	}
	
	/******** Wait for non web element Title, URL, Alert *******/
	public String waitForTitleContains(String titleFraction, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleContains("titleFraction"))) {
				return driver.getTitle();
			}
		} catch (Exception e) {

			System.out.println(titleFraction + "Title value not present.......");
			e.printStackTrace();

		}
		return null;

	}

	public String waitForTitle(String title, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (Exception e) {

			System.out.println(title + "Title value not present.......");
			e.printStackTrace();

		}
		return null;

	}

	public String waitForURLContains(String URLFraction, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(URLFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {

			System.out.println(URLFraction + "URL value not present.......");
			e.printStackTrace();

		}
		return null;

	}

	public String waitForURLToBe(String URL, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(URL))) {
				return driver.getTitle();
			}
		} catch (Exception e) {

			System.out.println(URL + "URL value not present.......");
			e.printStackTrace();

		}
		return null;

	}
	
	public Alert waitForJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeout) {
		waitForJSAlert(timeout).accept();
		
	}

	public void dismissJSAlert(int timeout) {
		waitForJSAlert(timeout).dismiss();
	}

	public String getJSAlertText(int timeout) {
		return waitForJSAlert(timeout).getText();
	}

	public void enterValueOnJSAlert(int timeout, String value) {
		waitForJSAlert(timeout).sendKeys(value);
		
	}
	
	
}
