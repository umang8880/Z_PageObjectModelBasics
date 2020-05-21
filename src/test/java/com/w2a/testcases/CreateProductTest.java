package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.crm.products.CreateProductPage;
import com.w2a.pages.crm.products.ProductPage;
import com.w2a.utilities.ExcelReader;

public class CreateProductTest {
	
	@Test(dataProviderClass = ExcelReader.class,  dataProvider = "dp2")
	public void createProductTest(Hashtable<String,String> data) {
		ProductPage pp = Page.menu.goToProducts();
		CreateProductPage cpp = pp.goToCreateProduct();
		cpp.createProduct(data.get("productCode"));
	}

}
