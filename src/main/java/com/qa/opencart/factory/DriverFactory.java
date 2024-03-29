package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight = null;

	public WebDriver initDriver(Properties prop) {
		
		//String browserName = System.getProperty("browser");

		String browserName = prop.getProperty("browser");

		System.out.println("browser name is" + browserName);

		highlight = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {

		case "chrome":
			// driver = new ChromeDriver();

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid

				initRemoteWebDriver(browserName);
			}

			else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;

		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid

				initRemoteWebDriver(browserName);
			}

			// driver = new FirefoxDriver();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;

		case "edge":
			// driver = new EdgeDriver();
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// run it on grid

				initRemoteWebDriver(browserName);
			} else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;

		default:
			System.out.println("please pass the right browser" + browserName);
			throw new FrameworkException("No browser find...");

		}
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		driver.get(prop.getProperty("url"));
//		// "https://naveenautomationlabs.com/opencart/index.php?route=account/login"
//		return driver;
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * to run test cases on grid
	 * @param browserName
	 */

	private void initRemoteWebDriver(String browserName) {
		// TODO Auto-generated method stub
		System.out.println("Run on Grid" + browserName);
		try {
			switch (browserName.toLowerCase().trim()) {
			
			case "chrome":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));

				break;
				
			case "firefox":
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));

				break;
			
			case "edge":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));

				break;

			default:
				System.out.println("Wrong browser info ...not able to run test on grid" + browserName);
				break;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
	}
		
	
	}



	public Properties initProp() {

		// mvn clean install -Denv="qa"
		// mvn clean install

		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("envName is : " + envName);

		try {
			if (envName == null) {
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;

				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					System.out.println("please pass right env :" + envName);
					throw new FrameworkException("wrong env name: " + envName);

				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	// Method to take screen shot
	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
