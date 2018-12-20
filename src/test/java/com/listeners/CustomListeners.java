package com.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.MonitoringMail;
import com.utilities.TestConfig;
import com.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener {
	
	public String MessageBody;

	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		//runmodes - Y
		
//		if(!TestUtil.isTestRunnable(result.getName(), excel)) {
//			throw new SkipException("Skipping the test" +result.getName().toUpperCase()+"As the runmode is NO");
//		}
		
	}

	public void onTestSuccess(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		
		
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.PASS, result.getName().toUpperCase()+ " Pass ");
		test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
//		Reporter.log("Capturing screenshot");
//		Reporter.log("Project Generation Pass");
//		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
//		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();
		
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " FAIL ");
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("Capturing screenshot");
		Reporter.log("Project Generation Pass");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		
	}

	public void onStart(ISuite suite) {
		
		
	}

	public void onFinish(ISuite suite) {
		
//		MonitoringMail mail = new MonitoringMail();
//		try {
//			MessageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/ProTrackplus1/HTML_20Report/";
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, MessageBody);
//		} catch (AddressException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
