package com.citiustech.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.citiustech.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends ExcelReader{


	/*
	 * ------Initializing----
	 * WebDriver - done
	 * Properties - done
	 * Logs - log4j jar, .log, log4j.properties, Logger
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 */
	
	public TestBase(String path) {
		super(path);
	}

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	
	
	@BeforeSuite
	public void setUp() throws IOException {
		
		if(driver==null) {
			
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("Config file loaded !!!");
			
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR file loaded !!!");
			
			if(config.getProperty("browser").equals("chrome")) {
				
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome Launched !!!");
			}
			else if(config.getProperty("browser").equals("firefox")) {
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(config.getProperty("browser").equals("ie")) {
				
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : "+config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		}
	}
	
	public boolean isElementPresent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	@AfterSuite
	public void tearDown() {
		
		if(driver!=null) {
		driver.quit();
		}
		
		log.debug("test execution completed !!");
	}
	
}
