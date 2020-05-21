package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import com.w2a.utilities.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Page {
 
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	//public static String screenshotpath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\screenshots\\";
	public static String screenshotpath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\";
	public static String extent_reports_path = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\";
	 
	public ExtentReports extent = ExtentManager.createInstance();
	public static ExtentTest test;
	public static String browser; 
	public static TopMenu menu;
	/*
	 * Logs, Properties: OR, Config
	 * Excel
	 * Implicit and Explicit Wait
	 * Extent Reports
	 */
	
	public Page() {
		if(driver==null) {
			//Setup Config and OR properties file// 
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Jenkins Browser filter configuration//
			if(System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			}else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			
			//Condition for launching browser//
			if(config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
						
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver =  new ChromeDriver(options);
				log.debug("Chrome Launch");
			}
			
			//WebDriver common code
			log.debug("Navigated to: "+config.getProperty("testsiteurl"));
			driver.get(config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			menu = new TopMenu(driver);
			
		}
	}
	public static void quit() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
	
	//Common Keywords//
	public static void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		log.debug("Clicking on element:"+locator);
		test.log(Status.INFO, "Clicking on:"+locator);
	}
	public static void typeatext(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if(locator.endsWith("_XPATH")) {
			System.out.println("2222");
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		log.debug("Tying in:"+locator+" and entered value is:"+value);
		test.log(Status.INFO, "Tying in:"+locator+" and entered value is:"+value);
	}
	
	static WebElement dropdown;
	public void select(String locator, String value, String bytype) {
		if(locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if(locator.endsWith("_XPATH")) {
			dropdown =  driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if(locator.endsWith("_ID")) {
			dropdown =  driver.findElement(By.id(OR.getProperty(locator)));
			
		}
		Select select = new Select(dropdown);
		if(bytype=="visbletext") {
			select.selectByVisibleText(value);
		}else if (bytype=="value") {
			select.selectByValue(value);
		}
//		else if (bytype=="index") {
//			int val = Integer.parseInt(value);
//			select.selectByIndex(val);
//			String value =String.valueOf(val); 
//		}
		log.debug("Tying in:"+locator+" and entered value is:"+value);
		test.log(Status.INFO, "Selecting from dropdown:"+locator+" and value is:"+value);
	}
	
	public static boolean isElementPresent(By by)
	{			
		int size = driver.findElements(by).size();
		if(size==0) {
			return false;
		}else {
			return true;
		}
	}

	public static void verifyEquals(String exp, String actual, String methodName) throws IOException {
		try{
			Assert.assertEquals(actual, exp);
		}catch(Throwable t) {
			
			Utilities.captureScreenShots(screenshotpath, methodName);
			
			//ReportNG Report
			System.setProperty("org.uncommons.reportng.escape-output", "false"+"<br>");
			Reporter.log("<br>"+"Verification failure: "+t.getMessage()+"<br>");
			Reporter.log("<a href=\""+Utilities.screenshotName+"\" target='_blank'><img src=\""+Utilities.screenshotName+"\" height=250 width=250></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//Extent Report
			test.fail("<a href=\""+Utilities.screenshotName+"\" target='_blank'>Click here to see full scerrenshot in new tab</a>"
					 +"<br>"+"<br>"
					 +"<a href=\""+Utilities.screenshotName+"\" target='_blank'><img src=\""+Utilities.screenshotName+"\" height=250 width=250></a>");
			test.log(Status.FAIL, "Verification failed with exception: "+t.getMessage());
		}
		
	}
	
}
