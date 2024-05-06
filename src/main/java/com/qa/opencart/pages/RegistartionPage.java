package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;

public class RegistartionPage {

	private WebDriver driver;
	private ElementUtil el;

	public RegistartionPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	private By fnby = By.id("input-firstname");
	private By lnby = By.id("input-lastname");
	private By emby = By.id("input-email");
	private By telby = By.id("input-telephone");
	private By pwby = By.id("input-password");
	private By cnpwby = By.id("input-confirm");

	private By subyes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	private By subno = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	private By agree = By.xpath("//input[@type='checkbox']");
	private By cont = By.xpath("//input[@value='Continue']");

	private By suucmessage = By.cssSelector("div#content h1");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");

	public boolean fillRegistrationForm(String fn, String ln, String em, String ph, String pwd, String subscribe) {
		fillData(fn, ln, em, ph, pwd);
		subscription(subscribe);
		agreeAndContinue();
		return getSuccessStatus();
	}

	private void fillData(String fn, String ln, String em, String ph, String pwd) {
		el.doSendkeys(fnby, fn);
		el.doSendkeys(lnby, ln);
		el.doSendkeys(emby, em);
		el.doSendkeys(telby, ph);
		el.doSendkeys(pwby, pwd);
		el.doSendkeys(cnpwby, pwd);

	}

	private void subscription(String subscribe) {
		if (subscribe.equalsIgnoreCase("yes")) {
			el.doClick(subyes);
		} else {
			el.doClick(subno);
		}

	}

	private void agreeAndContinue() {
		el.doClick(agree);
		el.doClick(cont);
	}

	private boolean getSuccessStatus() {
		String sucmeaasge = el.doGetText(suucmessage);
		if (sucmeaasge.contains(Constants.REG_FILL_SUCCESS_MESSAGE)) {
			el.doClick(logout);
			el.doClick(register);
			return true;
		}
		return false;
	}

}
