package com.w2a.pages;

import com.w2a.base.Page;

public class LoginPage extends Page{

	public ZohoAppPage doLogin(String username, String password) throws InterruptedException {
		
//		if(runmode == "N") {
//			throw new SkipException("Skipping this test"+"AddCustomerTest".toUpperCase()+" as the runmode is 'NO'");
//		}
		
		
		typeatext("emailid_CSS", username);
		Thread.sleep(3000);
		click("nextbtn_ID");
		Thread.sleep(3000);
		typeatext("password_ID", password);
		click("signin_XPATH");
		return new ZohoAppPage();					
	}

	public void doOneAuth() {

	}

	public void goToGoogle() {

	}

	public void goToLinkedIn() {

	}

	public void goToFacebook() {

	}
}
