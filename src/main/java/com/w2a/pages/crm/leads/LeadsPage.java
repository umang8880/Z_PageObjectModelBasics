package com.w2a.pages.crm.leads;

import com.w2a.base.Page;

public class LeadsPage extends Page {
	
	public CreateLeadPage goToCreateLead() {
		click("createlead_link_CSS");
		return new CreateLeadPage();
	}

}
