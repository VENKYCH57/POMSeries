package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void produtInfoPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void MainProductNameTest() {
		rp = ap.doSearch("Samsung");
		pi = rp.selectProduct("Samsung Galaxy Tab 10.1");
		String name = pi.getMainProductName();
		Assert.assertEquals(name, "Samsung Galaxy Tab 10.1");
	}

	@DataProvider
	public Object[][] selectMainProductData() {
		return new Object[][] { { "macbook", "MacBook Air", 4 }, { "imac", "iMac", 3 },
				{ "apple", "Apple Cinema 30\"", 6 }, { "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}

	@Test(dataProvider = "selectMainProductData")
	public void MainProductImageCountTest(String productName, String mainProductName, int count) {
		rp = ap.doSearch(productName);
		pi = rp.selectProduct(mainProductName);
		int actCount = pi.getMainproductimagesCount();
		Assert.assertEquals(actCount, count);
	}

	@Test
	public void getMetaProductTest() {
		rp = ap.doSearch("macbook");
		pi = rp.selectProduct("MacBook Pro");
		HashMap<String, String> actmetadata = pi.getProductMetaData();
		actmetadata.forEach((k, v) -> System.out.println(k + ":" + v));
		softassert.assertEquals(actmetadata.get("name"), "MacBook Pro");
		softassert.assertEquals(actmetadata.get("Brand"), "Apple");
		softassert.assertEquals(actmetadata.get("actprice"), "$2,000.00");
		softassert.assertEquals(actmetadata.get("Product Code"), "Product 18");
		softassert.assertAll();
	}

}
