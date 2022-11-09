package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.HomePageUIs;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver){
		this.driver = driver;
	}
	
	public UserRegisterPageObject openUserRegisterPage() {
		waitForElementClickable(driver, HomePageUIs.REGISTER_LINK);
		clickToElement(driver, HomePageUIs.REGISTER_LINK);
		return PageGeneratorManager.openUserRegisterPage(driver);
	}
	
	@Step("Navigate to 'Login' page")
	public UserLoginPageObject openUserLoginPage() {
		waitForElementClickable(driver, HomePageUIs.LOGIN_LINK);

		clickToElementByJS(driver, HomePageUIs.LOGIN_LINK);
		return PageGeneratorManager.openUserLoginPage(driver);
	}
	
	@Step("Verify 'My account' link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUIs.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUIs.MY_ACCOUNT_LINK);
	}
	
	public UserCustomerInforPageObject getUserCustomInforPage() {
		waitForElementClickable(driver, HomePageUIs.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUIs.MY_ACCOUNT_LINK);
		return PageGeneratorManager.openUserCustomInforPage(driver);
	}

}
