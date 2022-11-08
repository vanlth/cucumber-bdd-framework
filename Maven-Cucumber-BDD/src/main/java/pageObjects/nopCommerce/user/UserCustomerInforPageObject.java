package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInforPageUIs;

public class UserCustomerInforPageObject extends BasePage{
	private WebDriver driver;
	
	public UserCustomerInforPageObject(WebDriver driver){
		this.driver = driver;
	}

	public boolean isCustomInforPageDisplyed() {
		waitForElementVisible(driver, CustomerInforPageUIs.CUSTOM_INFOR_HEADER);
		return isElementDisplayed(driver, CustomerInforPageUIs.CUSTOM_INFOR_HEADER);
	}

}
