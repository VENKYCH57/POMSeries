package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil el;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	private By search = By.name("search");
	private By searchIcon= By.cssSelector("div#search button");
	private By logout = By.linkText("Logout");
	private By accsec = By.xpath("//div[@id='content']/h2");

	public String accPageTitle() {
		return el.waitforTitleToBe(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean isLogoutLinkExist() {
		return el.checkElementDisplayed(logout);
	}

	public boolean isSearchFiledExist() {
		return el.checkElementDisplayed(search);
	}

	public List<String> getAccsecHeaders() {
		List<String> accheaderList = new ArrayList<String>();
		List<WebElement> acclist = el.getElements(accsec);
		for (WebElement e : acclist) {
			accheaderList.add(e.getText());
		}
		return accheaderList;

	}
	public ResultsPage doSearch(String productName) {
		el.doSendkeys(search, productName);
		el.doClick(searchIcon);
		return new ResultsPage(driver);
	}
}
