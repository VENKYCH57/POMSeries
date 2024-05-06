package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;

public class LoginPageTest extends BaseTest {
    
	@Test
	public void pageTitleTest() {
		String title = lp.getPageTitile();
		System.out.println("Login page title is :" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void pageUrlTest() {
		String url = lp.getPageUrl();
		System.out.println("page url is:" + url);
		Assert.assertEquals(url, Constants.LOGIN_PAGE_URL);
	}

	@Test
	public void forgotPaswordLinkTest() {
		Assert.assertTrue(lp.isForgotPaswordExist());
	}

	@Test
	public void ResgterLinkTest() {
		Assert.assertTrue(lp.isRegisterLinkExist());
	}

	@Test
	public void doLoginTest() {
		lp.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
