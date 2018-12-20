package com.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.base.TestBase;

public class LoginTest extends TestBase {
	@Test
	public void login() throws InterruptedException {
		log.info("Inside Login Test");
		type("TenantName_ID", "dafc");
		type("UserName_ID", "Admin");
		type("Password_ID", "P@ssword123");
		click("LoginButton_XPATH");
        
        Assert.assertTrue(isElementPresent(By.xpath("/html/body/div[1]/div/div[1]/header/div/div[3]/div/a[1]/span")),"Login not sucessfull");
        Thread.sleep(8000);
        log.debug("Login Sucessfully done !!!!!");
	}
	
	@Test(enabled=false)
	public void logout() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebElement element = driver.findElement(By.id("logout"));
  	  executor.executeScript("arguments[0].click();", element);
        //driver.findElement(By.id(OR.getProperty("LogOutButton"))).click();
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        log.debug("logput");
		
		
	}
	
	

}
