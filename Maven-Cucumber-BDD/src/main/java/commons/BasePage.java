package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewsPageObject;
import pageObjects.nopCommerce.user.UserRewardPointsPageObject;
import pageUIs.nopCommerce.user.BasePageUIs;

public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}
	
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	protected String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies ) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}
	
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait= new WebDriverWait(driver, longTimeOut);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	protected String getTextlert(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	protected void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	protected void switchWindowByID(WebDriver driver, String expectedID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}
	
	protected void switchWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allWindowIDs= driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if(actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}
	
	protected void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String runWindow : allWindowIDs) {
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=")|| locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by =  By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=")|| locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")){
			by = By.className(locatorType.substring(6));
		}else if (locatorType.startsWith("name=")|| locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")){
			by = By.name(locatorType.substring(5));
		}else if (locatorType.startsWith("css=")|| locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")){
			by = By.cssSelector(locatorType.substring(4));
		}else if (locatorType.startsWith("xpath=")|| locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") 
				|| locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator  type is not supported");
		}
		return by;
	}
	
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("xpath=")|| locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=") 
				|| locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}
	
	private WebElement getElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	protected List<WebElement> getListElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	
	protected void clickToElement(WebDriver driver, String locatorType) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType);
			sleepInSecond(3);
		} else {
			getElement(driver, locatorType).click();
		}
	}
	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locatorType, dynamicValues);
			sleepInSecond(3);
		} else {
			getElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
		}
	}
	
	protected void sendKeysToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	
	protected void sendKeysToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	protected void clearValueInElementByDeleteKey(WebDriver driver, String locatorType) {
		WebElement element = getElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	
	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	protected void isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getElement(driver, locatorType));
		select.isMultiple();
	}
	
	protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childLocator,
			String expectedTextItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));

		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	protected String getAttributeValue(WebDriver driver, String locatorType, String attributeName) {
		return getElement(driver, locatorType).getAttribute(attributeName);
	}
	protected String getAttributeValue(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	
	protected String getTextElement(WebDriver driver, String locatorType) {
		return getElement(driver, locatorType).getText();
	}
	
	protected String getTextElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
		
	protected String getCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getElement(driver, locatorType).getCssValue(propertyName);
	}
	
	protected String getHexaFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListElements(driver,locatorType).size();
	}
	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListElements(driver,getDynamicXpath(locatorType, dynamicValues)).size();
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	protected void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getElement(driver, locatorType).isDisplayed();
	}
	
	protected boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	
	protected boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeOut);
		List<WebElement> elements = getListElements(driver, locatorType);
		overrideImplicitTimeout(driver, shortTimeOut);
		if (elements.size()==0) {
			return true;
		} else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	protected boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeOut);
		List<WebElement> elements = getListElements(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, shortTimeOut);
		if (elements.size()==0) {
			return true;
		} else if (elements.size()>0 && !elements.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
	private void overrideImplicitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getElement(driver, locatorType).isSelected();
	}
	
	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getElement(driver, locatorType).isEnabled();
	}
	
	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getElement(driver, locatorType));
	}
	
	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(driver, locatorType)).perform();
	}
	
	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, locatorType), key).perform();
	}
	
	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	protected void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locatorType));
	}
	
	protected void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	protected void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locatorType));
	}
	
	protected String getElementValueByJSXpath(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		xpathLocator = xpathLocator.replace("xpath=", "");
		return (String) jsExecutor.executeScript("return $(document.evaluate(\"" + xpathLocator + "\", document, null, XpathResult.FIRST_ORDER_NODE_TYPE, null).singleNodeValue).val()");
	}

	protected void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locatorType));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locatorType));
	}

	protected boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	
	protected void waitForAllElemenInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(driver, locatorType)));
	}
	
	protected void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	/**
	 * wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 */
	protected void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideImplicitTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeOut);
	}
	
	protected void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeOut);
		overrideImplicitTimeout(driver, shortTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeOut);
	}
	
	protected void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	
	public UserCustomerInforPageObject getCustomInforPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.CUSTOM_INFOR_LINK);
		clickToElement(driver, BasePageUIs.CUSTOM_INFOR_LINK);
		return PageGeneratorManager.openUserCustomInforPage(driver);
	}
	
	public UserAddressPageObject getAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.ADDRESS_LINK);
		clickToElement(driver, BasePageUIs.ADDRESS_LINK);
		return PageGeneratorManager.openUserAddressPage(driver);
	}
	
	public UserRewardPointsPageObject getRewardPointsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.REWARD_POINTS_LINK);
		clickToElement(driver, BasePageUIs.REWARD_POINTS_LINK);
		return PageGeneratorManager.openUserRewardPointsPage(driver);
	}
	
	public UserMyProductReviewsPageObject getMyProductReviewsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, BasePageUIs.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.openUserMyProductReviewsPage(driver);
	}
	
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.LOGOUT_LINK_AT_USER_PAGE);
		clickToElement(driver, BasePageUIs.LOGOUT_LINK_AT_USER_PAGE);
		return PageGeneratorManager.openUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUIs.LOGOUT_LINK_AT_ADMIN_PAGE);
		clickToElementByJS(driver, BasePageUIs.LOGOUT_LINK_AT_ADMIN_PAGE);
		return PageGeneratorManager.openAdminLoginPage(driver);
	}
	
	public BasePage openDynamicPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageUIs.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, BasePageUIs.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch(pageName) {
		case "Customer info":
			return PageGeneratorManager.openUserCustomInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.openUserAddressPage(driver);
		case "Reward points":
			return PageGeneratorManager.openUserRewardPointsPage(driver);
		case "My product reviews":
			return PageGeneratorManager.openUserMyProductReviewsPage(driver);
		default:
			throw new RuntimeException("Invalid page at my account");
				
		}
	}
	
	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeOut = GlobalConstants.SHORT_TIMEOUT;
	
	}

