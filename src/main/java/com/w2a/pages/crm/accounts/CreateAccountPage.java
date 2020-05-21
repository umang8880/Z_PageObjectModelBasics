package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class CreateAccountPage extends Page {
	
	public void createAccount(String accountName) {
		typeatext("accountname_CSS", accountName);
		click("cancel_btn_from_create_account_ID");
	}

}
