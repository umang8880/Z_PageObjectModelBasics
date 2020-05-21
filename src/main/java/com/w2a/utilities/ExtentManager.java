package com.w2a.utilities;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.w2a.base.Page;


public class ExtentManager extends Page{

	
	private static ExtentReports extent;
	static Date d = new Date();
	//static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String fileName = "ExtentReport.html";
	public static ExtentReports createInstance() {
    	
    	
    	if (extent == null) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extent_reports_path+fileName);
	       
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setDocumentTitle("Login Reports");
	        htmlReporter.config().setReportName("Login Test Result");
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Umang Parekhg");
	        extent.setSystemInfo("Organization", "Sculptsoft");
	        extent.setSystemInfo("Build no", "login-1234");	      
    	}
    	return extent;
    }
}
