package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void RegPageSetup() {
		regp = lp.navigateToRegistration();
	}

	public String getEmail() {
		Random random = new Random();
		String em = "testselnium" + random.nextInt(10000) + "@gmail.com";
		return em;
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object data[][] = ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}

	@Test(dataProvider = "getRegisterData")
	public void registrationFillTest(String fn, String ln, String ph, String pwd, String sub) {
		Assert.assertTrue(regp.fillRegistrationForm(fn, ln, getEmail(), ph, pwd, sub));
	}

}
