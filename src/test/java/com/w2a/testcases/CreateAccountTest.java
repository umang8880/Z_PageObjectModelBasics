package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.ExcelReader;

public class CreateAccountTest {
	

	@Test(dataProviderClass = ExcelReader.class,  dataProvider = "dp2")
	public void createAccountTest(Hashtable<String,String> data) {
		
		ZohoAppPage zp = new ZohoAppPage();
		zp.goToCRM();
		AccountsPage account = Page.menu.goToAccounts();
		CreateAccountPage cap = account.goToCreateAccount();
		cap.createAccount(data.get("accountname"));
		

	}

}
