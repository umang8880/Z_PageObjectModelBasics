package com.w2a.pages.crm.products;

import com.w2a.base.Page;

public class ProductPage extends Page{
	
	public CreateProductPage goToCreateProduct() {
		click("create_product_btn_XPATH");
		return new CreateProductPage();
	}

}
