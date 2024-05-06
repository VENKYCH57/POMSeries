package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utilities.Constants;
import com.qa.opencart.utilities.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil el;

	private By mainproductnameby = By.cssSelector("div#content h1");
	private By mainproductimagesby = By.cssSelector("div#content li img");
	private By mainProductDataby = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By mainProductPriceDataby = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public String getMainProductName() {
		return el.doGetText(mainproductnameby);
	}

	public int getMainproductimagesCount() {
		return el.waitforElementsVisibile(mainproductimagesby, Constants.DEFAULT_TIME_OUT).size();
	}

	public HashMap<String, String> getProductMetaData() {
		HashMap<String, String> productMetaData = new HashMap<String, String>();
		String mainProductName = el.doGetText(mainproductnameby);
		productMetaData.put("name", mainProductName);
		getProdMetaData(productMetaData);
		getProdPriceMetaData(productMetaData);
		return productMetaData;
	}

	private void getProdMetaData(HashMap<String, String> productMetaData) {
		List<WebElement> metaDataList = el.getElements(mainProductDataby);
		for (WebElement e : metaDataList) {
			String data = e.getText();
			String metakey = data.split(":")[0].trim();
			String metavalue = data.split(":")[1].trim();
			productMetaData.put(metakey, metavalue);
		}
	}

	private void getProdPriceMetaData(HashMap<String, String> productMetaData) {
		List<WebElement> metaDataList = el.getElements(mainProductPriceDataby);
		String actprice = metaDataList.get(0).getText();
		String extprice = metaDataList.get(1).getText().split(":")[1];
		productMetaData.put("actprice", actprice);
		productMetaData.put("extprice", extprice);
	}

}
