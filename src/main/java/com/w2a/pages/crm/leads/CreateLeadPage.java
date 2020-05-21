package com.w2a.pages.crm.leads;

import com.w2a.base.Page;

public class CreateLeadPage extends Page {
	
	public void createLead(String fname) {
//		click("name_prefix_XPATH");
//		click("firstname_prefix_mr_ID");
		typeatext("create_leadn_fname_ID", fname);
		click("cancel_lean_btn_ID");
	}

}
