package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class AccountsPage extends Page {
	
	public CreateAccountPage goToCreateAccount() {
		click("createaccount_link_CSS");
		//click("createaccount_link_XPATH");
		return new CreateAccountPage();
	}
	
	public void goToImportAccounts() {
		
	}

}
