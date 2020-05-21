package com.w2a.pages.crm.products;

import com.w2a.base.Page;

public class CreateProductPage extends Page{

	public void createProduct(String product_code) {
		typeatext("product_code_ID", product_code);
		click("cancel_btn_from_create_product_ID");
	}
}
