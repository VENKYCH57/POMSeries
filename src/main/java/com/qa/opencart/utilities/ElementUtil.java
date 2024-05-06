package com.qa.opencart.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.falctory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptUtil js;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		js = new JavascriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			js.flshElementyJS(element);
		}
		return element;
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendkeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public String doGetTagName(By locator, String value) {
		return getElement(locator).getTagName();
	}

	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean doElementIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public boolean doElementIsSelected(By locator) {
		return getElement(locator).isSelected();
	}

	public String doGetAttribute(By locator, String attrValue) {
		return getElement(locator).getAttribute(attrValue);
	}

	public void doLinkClick(By locator, String value) {
		List<WebElement> linkList = getElements(locator);
		System.out.println(linkList.size());
		for (int i = 0; i < linkList.size(); i++) {
			String linkText = linkList.get(i).getText();
			System.out.println(linkText);
			if (linkText.trim().equalsIgnoreCase(value)) {
				linkList.get(i).click();
				break;
			}
		}
	}

	public boolean checkElementDisplayed(By locator) {
		if (getElements(locator).size() == 1)
			return true;
		return false;

	}

	public boolean checkElementDisplayed(By locator, int eleOcuurence) {
		if (getElements(locator).size() == eleOcuurence)
			return true;
		return false;
	}

	/********************* DropdownUtilities **************************/

	public boolean doSelectByIndex(By locator, int index, String expValue) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
		return isDropDownValueIsSelected(select, expValue);
	}

	public boolean doSelectByValue(By locator, String expValue) {
		Select select = new Select(getElement(locator));
		select.selectByValue(expValue);
		return isDropDownValueIsSelected(select, expValue);
	}

	public boolean doSelectByVisibleText(By locator, String expValue) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(expValue);
		return isDropDownValueIsSelected(select, expValue);
	}

	public boolean isDropDownValueIsSelected(Select select, String expValue) {
		System.out.println(expValue + ": is selected");
		if (select.getFirstSelectedOption().getText().contains(expValue)) {
			return true;
		}
		return false;
	}

	public void doSelectDropDownAllOptions(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		iterateDropDownAndSelect(optionsList, value);
	}

	public void selectDropdown(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		iterateDropDownAndSelect(optionsList, value);
	}

	public void iterateDropDownAndSelect(List<WebElement> optionsList, String value) {
		System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			String dropDownLists = e.getText();
			System.out.println(dropDownLists);
			if (dropDownLists.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}

	}

	public void googleSuggSelectListAndClick(By search, String text, By sugglistxp, String textlist)
			throws InterruptedException {
		doSendkeys(search, text);
		Thread.sleep(5000);
		List<WebElement> sugglist = getElements(sugglistxp);
		System.out.println(sugglist.size());
		for (WebElement e : sugglist) {
			String suggtext = e.getText();
			System.out.println(suggtext);
			if (suggtext.equalsIgnoreCase(textlist)) {
				e.click();
				break;
			}
		}
	}

	/************************ Action class utilities **************************/

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
		;
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
		;
	}

	public void parentChildMenueHandle(By parent, By child) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parent)).build().perform();
		doClick(child);
	}

	public void contextRightClickListClick(By rightClick, By rightItems, String value) {
		List<String> rightItemValues = new ArrayList<String>();
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick)).build().perform();
		List<WebElement> rightclickList = getElements(rightItems);
		System.out.println(rightclickList.size());
		for (WebElement e : rightclickList) {
			String rightText = e.getText();
			System.out.println(rightText);
			if (rightText.equalsIgnoreCase(value)) {
				e.click();
				break;
			}
		}

	}

	public List<String> contextRightClickList(By rightClick, By rightItems) {
		List<String> rightItemValues = new ArrayList<String>();
		Actions act = new Actions(driver);
		act.contextClick(getElement(rightClick)).build().perform();
		List<WebElement> rightclickList = getElements(rightItems);
		System.out.println(rightclickList.size());
		for (WebElement e : rightclickList) {
			String rightText = e.getText();
			rightItemValues.add(rightText);

		}
		return rightItemValues;

	}

	/******************************** wait utilities ***********************/

	public WebElement waitForElementPresent(By locator, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement fluent_waitForElementPresent(By locator, long time) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG)
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementPresent(By locator, long time, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time), Duration.ofNanos(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement fluent_waitForElementPresent(By locator, long time, long timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofMillis(timeOut)).withMessage(Error.TIME_OUT_WEB_ELEMENT_MSG)
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public WebElement waitForElementVisibilityUsingByLocator(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement fluent_waitForElementVisibilityUsingByLocator(By locator, long time) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withMessage(Error.TIME_OUT_WEB_ELEMENT_VISIBLE_MSG)
				.withTimeout(Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public WebElement waitforElementClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void clickWhenReady(By locator, int timeOut) {
		waitforElementClickable(locator, timeOut).click();
	}

	public Alert waitforJsAlert(long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJsAlert(long time) {
		waitforJsAlert(time).accept();
	}

	public void dismissJsAlert(long time) {
		waitforJsAlert(time).dismiss();
	}

	public void alertSendKeys(long time, String value) {
		waitforJsAlert(time).sendKeys(value);
	}

	public String alertGetText(long time) {
		Alert alert = waitforJsAlert(time);
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public boolean waitforUrlContains(String urlFraction, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}

	public boolean waitforUrlToBe(String urlFraction, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(urlFraction));

	}

	public String waitforTitleContains(String titleFraction, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitforTitleToBe(String title, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}

	public void waitforFrameUsingIdorName(String frameIdorName, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIdorName));
	}

	public void waitforFrameUsingIndex(int frameIndex, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitforFrameUsingBylocator(By frameLocator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitforFrameUsingWebElement(WebElement frameElement, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public List<WebElement> waitforElementsPresence(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public List<String> getElementsTextList(By locator, long timeOut) {
		List<WebElement> elelist = waitforElementsPresence(locator, timeOut);
		List<String> elevalueList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			elevalueList.add(text);
		}
		return elevalueList;

	}

	public void printElementsTextList(By locator, long timeOut) {
		List<WebElement> elelist = waitforElementsPresence(locator, timeOut);
		for (WebElement e : elelist) {
			String text = e.getText();
			System.out.println(text);
		}

	}

	public List<WebElement> waitforElementsVisibile(By locator, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	public List<String> getVisibleElementsTextList(By locator, long timeOut) {
		List<WebElement> elelist = waitforElementsVisibile(locator, timeOut);
		List<String> elevalueList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			elevalueList.add(text);
		}
		return elevalueList;

	}

	public void printVisibleElementsTextList(By locator, long timeOut) {
		List<WebElement> elelist = waitforElementsVisibile(locator, timeOut);
		for (WebElement e : elelist) {
			String text = e.getText();
			System.out.println(text);
		}

	}

	/*************************** custom wait util ***************************/
	public WebElement retryWebElement(By locator, int count) {
		WebElement element = null;
		for (int attempts = 0; attempts < count;) {
			try {
				element = getElement(locator);
				break;

			} catch (Exception e) {
				System.out.println("element not found ->trying for :" + attempts + " time " + "with the :" + locator);
			}

			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			attempts++;

		}
		if (element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println("Element is not found on the with this " + locator);
			}

		}
		return element;

	}

}
