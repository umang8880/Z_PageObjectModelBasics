package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.utilities.ExcelReader;

public class LoginTest extends BaseTest{
	
	@Test(dataProviderClass = ExcelReader.class,  dataProvider = "dp2")
	public void loginTest(Hashtable<String,String> data) throws InterruptedException {
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		lp.doLogin(data.get("username"), data.get("password"));
	}

}
