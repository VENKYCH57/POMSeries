package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.falctory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistartionPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {

	public WebDriver driver;
	public DriverFactory df;
	public Properties prop;
	public LoginPage lp;
	public AccountsPage ap;
	public ResultsPage rp;
	public ProductInfoPage pi;
	public RegistartionPage regp;

	public SoftAssert softassert;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		lp = new LoginPage(driver);
		softassert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
