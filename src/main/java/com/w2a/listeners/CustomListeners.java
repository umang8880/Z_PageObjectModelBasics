package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import com.w2a.base.Page;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener, ISuiteListener {

	
	public String messageBody;
	public void onTestStart(ITestResult result) {
//		extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());
	//	test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName().toUpperCase());
		test = extent.createTest("TestCase: "+result.getMethod().getMethodName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
//		Reporter.log("Pass Test - " + result.getName());
//		String methodName = result.getMethod().getMethodName();
//		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
//		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
//		test.pass(m);
		
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);
	}

	public void onTestFailure(ITestResult result) {
		try {
			//TestUtil.captureFullScreenUsingAshot(TestBase.screenshotpath, result.getMethod().getMethodName());
			Utilities.captureScreenShots(screenshotpath, result.getMethod().getMethodName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String methodName = result.getMethod().getMethodName();
		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace()); 
		test.fail(	"<details>"
						+"<summary>"
							+"<b>"+"<font color=" + "red>" + "Exception Occured:Click to see"+ "</font>"+"</b>"
						+"</summary>"
					 + excepionMessage.replaceAll(",", "<br>") + "</details>"+" \n"+"<br>"
					 +"<a href=\""+Utilities.screenshotName+"\" target='_blank'>Click here to see full scerrenshot in new tab</a>"
					 +"<br>"+"<br>"
					 +"<a href=\""+Utilities.screenshotName+"\" target='_blank'><img src=\""+Utilities.screenshotName+"\" height=250 width=250></a>");
		
		
		String logtext = "<b>" + "Test Case: "+methodName.toUpperCase()+": "+" FAILED"+"</b>";
		
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		test.log(Status.FAIL, m);
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		Reporter.log("<a href=\""+Utilities.screenshotName+"\" target='_blank'>Click here to see Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a href=\""+Utilities.screenshotName+"\" target='_blank'><img src=\""+Utilities.screenshotName+"\" height=250 width=250></a>");
		//System.out.println("Capture screenshot and test fail for method: " + result.getName());

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logtext = "<b>" + "Test Case: "+methodName.toUpperCase()+": "+" SKIPPED"+"</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ISuite context) {
		if (extent != null) {

			extent.flush();
		}
		MonitoringMail mm = new MonitoringMail();
		try {
			messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/Z_PageObjectModelBasics/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mm.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
