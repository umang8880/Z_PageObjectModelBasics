package com.w2a.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.leads.LeadsPage;
import com.w2a.pages.crm.products.ProductPage;

public class TopMenu{

	/*
	 * TopMenu ISA PAGE?
	 * 
	 * HomePage HASA TopMenu
	 * AccountPage HASA TopMenu
	 * 
	 * Page HASA TopMenu
	 */
	
	WebDriver driver;
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToHome() {

	}

	public LeadsPage goToLeads() {
		Page.click("crm_menu_leades_link_CSS");
		return new LeadsPage();
	}

	public void goToContacts() {

	}

	public AccountsPage goToAccounts() {
		//Page.driver.findElement(By.cssSelector("div[data-value='Accounts'] > a")).click();
		Page.click("crm_menu_account_link_CSS");
		return new AccountsPage();
	}

	public void goToDeals() {

	}

	public void goToActivities() {

	}

	public void goToReports() {

	}

	public void goToAnalytics() {

	}

	public ProductPage goToProducts() {
		Page.click("crm_menu_products_link_CSS");
		return new ProductPage();
	}

	public void goToQuotes() {

	}

	public void goToMarketplace() {

	}

	public void goToMoreOptions() {

	}
	
	public void signOut() {
		Page.click("signout_image_ID");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Page.click("signout_text_XPATH");
	}
}
