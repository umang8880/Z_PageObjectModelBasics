package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page{
	
	public void goToBooks() {
		click("Books_link_XPATH");
	}
	
	public void goToCalendar() {
		click("Calendar_link_XPATH");
	}
		
	public void goToCampaigns() {
		click("Campaigns_link_XPATH");
	}
	
	public void goToCliq() {
		click("Cli_link_XPATH");
	}
	
	public void goToConnect() {
		click("Connect_link_XPATH");
	}
	
	public CRMHomePage goToCRM() {
		click("CRM_link_XPATH");
		return new CRMHomePage();
	}
	
	public void goToDesk() {
		click("Desk_link_XPATH");
	}
	
	public void goToInvoice() {
		click("Invoice_link_XPATH");
	}
	
	public void goToMail() {
		click("Mail_link_XPATH");
	}
	
	public void goToMeeting() {
		click("Meeting_link_XPATH");
	}
	
	public void goToSheet() {
		click("Sheet_link_XPATH");
	}
	
	public void goToShow() {
		click("Show_link_XPATH");
	}
	
	public void goToWorkDrive() {
		click("WorkDrive_link_XPATH");
	}
	
	public void goToWriter() {
		click("Writer_link_XPATH");
	}

}
