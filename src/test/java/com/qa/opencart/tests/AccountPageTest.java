package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;

public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		ap = lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void accPageTitleTest() {
		String title = ap.accPageTitle();
		System.out.println("account page title is :" + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(ap.isLogoutLinkExist());
	}

	@Test
	public void isSearchFiledExistTest() {
		Assert.assertTrue(ap.isSearchFiledExist());
	}

	@Test
	public void accsecHeadersTest() {
		List<String> actsecList = ap.getAccsecHeaders();
		Assert.assertEquals(actsecList, Constants.EXP_ACCOUNTSPAGE_HEADER_LIST);
	}

	@DataProvider
	public Object[][] searchData() {
		return new Object[][] { { "macbook" }, { "imac" }, { "apple" }, { "samsung" } };
	}

	@Test(dataProvider = "searchData")
	public void doSearchCountTest(String productName) {
		rp = ap.doSearch(productName);
		Assert.assertTrue(rp.getProductCount() > 0);
	}

	@Test
	public void doSearchHeaderTest() {
		rp = ap.doSearch("macbook");
		Assert.assertEquals(rp.getproductHeader(), "Search - macbook");
	}

	@DataProvider
	public Object[][] selectProductData() {
		return new Object[][] { { "macbook", "MacBook Air" }, { "imac", "iMac" }, { "apple", "Apple Cinema 30\"" },
				{ "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "selectProductData")
	public void selectProductTest(String productName, String mainProductName) {
		rp = ap.doSearch(productName);
		rp.selectProduct(mainProductName);
	}

}
