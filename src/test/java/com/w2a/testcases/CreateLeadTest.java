package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.crm.leads.LeadsPage;
import com.w2a.utilities.ExcelReader;
import com.w2a.pages.crm.leads.CreateLeadPage;

public class CreateLeadTest {
	
	@Test(dataProviderClass = ExcelReader.class,  dataProvider = "dp2")
	public void createLeadTest(Hashtable<String,String> data) {
		LeadsPage lp = Page.menu.goToLeads();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateLeadPage clp = lp.goToCreateLead();
		clp.createLead(data.get("firstname"));
	}
}
