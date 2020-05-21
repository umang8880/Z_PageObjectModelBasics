package com.w2a.practice;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class LoginTest {

	public static void main(String[] args) throws InterruptedException {
		HomePage home = new HomePage();
		LoginPage lp = home.goToLogin();
		ZohoAppPage zp = lp.doLogin("parekhumangr@gmail.com","umang123456");
		zp.goToCRM();
		AccountsPage account = Page.menu.goToAccounts();
		CreateAccountPage cap = account.goToCreateAccount();
		cap.createAccount("Umang");
	}
}
