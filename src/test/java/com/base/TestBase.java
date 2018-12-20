package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.ExcelReader;
import com.utilities.ExtentManager;

public class TestBase {
	
	public static WebDriver driver;
	
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis; 
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = new ExtentManager().getInstance();
	public static ExtentTest test;
	public static String browser;
	
	
	
	
	
	
	@BeforeSuite
	public void setUp()  {
		
		if(driver == null) {
			 try {
				fis  = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        try {
				Config.load(fis);
				log.debug("Config file loaded!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	        try {
				OR.load(fis);
				log.debug("OR file loaded!!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
			
			browser = System.getenv("browser");
			
		}else {
			browser = Config.getProperty("browser");
		}
		
		Config.setProperty("browser", browser);
		
		
		if(Config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			//log.debug("Firefox Launched!!!!");
		}else if (Config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome Launched!!!!");
		}else if (Config.getProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			//log.debug("IE Launched");
		}
		
		driver.get(Config.getProperty("testsiteurl"));
		log.debug("Navigated to :"+ Config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,5);
		
		
	}
	
	public void click(String locator) {
		if(locator.endsWith("_XPATH")) {
		driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}else if (locator.endsWith("_ID")) {
		driver.findElement(By.id(OR.getProperty(locator))).click();	
		}else if (locator.endsWith("_NAME")) {
		driver.findElement(By.name(OR.getProperty(locator))).click();
		test.log(LogStatus.INFO, "Clicking on :" + locator);
	}
	}
	
	public void type(String locator, String value) {
		if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
			
		}
		test.log(LogStatus.INFO, "Type in :" + locator + "entered value :" + value);
		
	}
	
	static WebElement dropdown;
	public void select(String locator, String value) {
		if (locator.endsWith("_ID")) {
			dropdown= driver.findElement(By.id(OR.getProperty(locator)));
		}else if (locator.endsWith("_XPATH")) {
			dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
		}else if (locator.endsWith("_NAME")) {
			dropdown=driver.findElement(By.name(OR.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByValue(value); 
		test.log(LogStatus.INFO, "Selecting from dropdowm :" + locator + " value as : " + value);
		
	}
	
	
	
	public boolean isElementPresent(By by) {
		
		try {
			
			driver.findElement(by);
			return true;
			
		} catch (NoSuchElementException e) {
			return false;
		}
		
	}
	
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		log.debug("test execution completed");
		
	}

}
