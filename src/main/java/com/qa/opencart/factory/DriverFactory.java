package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	
	static WebDriver driver;
	Properties prop;

	
	public WebDriver initDriver(Properties prop ) {//String browserName
		
	String browserName=	prop.getProperty("browser");
		
				
		System.out.println("browser name is" + browserName );
		
		switch (browserName.toLowerCase().trim()) {
		
		case  "chrome":
			driver = new ChromeDriver();
			
			break;
			
		case  "firefox":
			driver = new FirefoxDriver();
			
			break;
			
		case  "edge":
			driver = new EdgeDriver();
			
			break;

		default:
			System.out.println("please pass the right browser"+browserName);
			throw new FrameworkException("No browser find...");
		
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		//"https://naveenautomationlabs.com/opencart/index.php?route=account/login"
		return driver;
		
	}
	
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
		    prop.load(ip);
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}

}
