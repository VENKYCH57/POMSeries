package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil el;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@type='submit']");
	private By forgotPaslink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	public String getPageTitile() {
		return el.waitforTitleToBe(driver.getTitle(), 5);
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public boolean isForgotPaswordExist() {
		return el.doElementIsDisplayed(forgotPaslink);
	}

	public boolean isRegisterLinkExist() {
		return el.doElementIsDisplayed(registerLink);
	}

	public AccountsPage doLogin(String un, String pwd) {
		el.doSendkeys(email, un);
		el.doSendkeys(password, pwd);
		el.doClick(login);

		return new AccountsPage(driver);
	}
	public RegistartionPage navigateToRegistration() {
		el.doClick(registerLink);
		return new RegistartionPage(driver);
	}

}
