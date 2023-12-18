package com.qa.opencart.utills;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
	}

	public String getTitleByJs() {

		return js.executeScript("return document.title").toString();

	}

	public String getURLByJs() {

		// JavascriptExecutor js= (JavascriptExecutor)driver;
		return js.executeScript("return document.URL").toString();
	}

	public void generateJsAlert(String mesg) {
		js.executeScript("alert('" + mesg + "')");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJsConfirm(String mesg) {
		js.executeScript("confirm('" + mesg + "')");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generatePrompt(String mesg, String value) {
		js.executeScript("prompt('" + mesg + "')");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Alert alert = driver.switchTo().alert();
		alert.sendKeys(value);
		//alert.accept();

	}
	
	public void goBackWithJS() {
		js.executeScript("history.go(-1)");
	}
	
	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}
	
	public void pageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}
	
	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void scrollPageDown() {
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollPageDown(String height) {
		 js.executeScript("window.scrollTo(0, '"+height+"')");
	}
	
	public void scrollPageUp() {
		 js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void scrollMiddlePageDown() {
		 js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	}
	
	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	
	/**
	 * example: "document.body.style.zoom='400.0%'"
	 * @param zoomPercentage
	 */
	
	public void zoomChromeEdgeSafari(String zoomPercentage) {
		String zoom = "document.body.style.zoom='"+zoomPercentage+"%'";
		js.executeScript(zoom);
	}
	
	/**
	 * example: "document.body.style.MozTransform='scale(0.5)';"
	 * @param zoomPercentage
	 */
	
	public void zoomFirefox(String zoomPercentage) {
		String zoom = "document.body.style.MozTransform='scale("+zoomPercentage+")';";
		//JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript(zoom);
	}
	

	
}
