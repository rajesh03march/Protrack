package com.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.TestUtil;

public class AddProject extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider = "dp")
	public void addProject(Hashtable<String, String> data) {
		
		try {
			Thread.sleep(2000);
			click("AddProject_ID");
			type("ProjectName_ID", data.get("Project"));
//			click("Category_NAME");
			//select("CompanyEDIT122_NAME", "123abc!@#$%^&*()");
			type("CommissionerName_ID", data.get("Commissioner"));
			type("District_ID", data.get("District"));
			
			click("btnProjectTabSave_ID");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	}
