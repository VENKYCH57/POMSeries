package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil el;

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	private By productHeaderBy = By.cssSelector("div#content h1");
	private By productResultsBy = By.cssSelector("div#content h4 a");

	public String getproductHeader() {
		return el.doGetText(productHeaderBy);
		
	}

	public int getProductCount() {
		return el.waitforElementsVisibile(productResultsBy, 5).size();
	}

	public ProductInfoPage selectProduct(String productName) {
		List<WebElement> productList = el.waitforElementsVisibile(productResultsBy, 5);
		for (WebElement e : productList) {
			String text = e.getText().trim();
			if (text.equals(productName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
