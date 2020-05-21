package com.w2a.pages;

import com.w2a.base.Page;

public class HomePage extends Page{

//	public void goToCustomers() {
//		click("customers_link_CSS");
//	}
//
//	public void goToSupports() {
//		click("support_link_CSS");
//	}
//
//	public void goToContactSales() {
//		click("contact_link_CSS");
//	}

	public LoginPage goToLogin() {
		click("login_link_CSS");
		return new LoginPage();
	}
//	public void goToSignUp() {
//		click("signup_link_CSS");
//	}
//	public void validateZohoApplinks() {
//		click("zohoapps_mail_link_XPATH");
//	}
//	public void validateFooterLinks() {
//		click("footer_about_link_XPATH");
//	}
}
