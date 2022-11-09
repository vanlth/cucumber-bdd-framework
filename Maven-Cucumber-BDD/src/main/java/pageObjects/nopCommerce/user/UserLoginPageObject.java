package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.LoginPageUIs;

public class UserLoginPageObject extends BasePage{
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to 'Login' button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUIs.LOGIN_BUTTON_AT_USER_PAGE);
		clickToElement(driver, LoginPageUIs.LOGIN_BUTTON_AT_USER_PAGE);
		return PageGeneratorManager.openUserHomePage(driver);
		
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, LoginPageUIs.EMAIL_ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUIs.EMAIL_ERROR_MESSAGE);
	}

	@Step("Enter to 'Email' texbox with value is {0}")
	public void sendKeyToEmailTextbox(String Email) {
		waitForElementVisible(driver, LoginPageUIs.USER_EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUIs.USER_EMAIL_TEXTBOX, Email);
	}

	public String getUnsuccessfulErrorMessage() {
		waitForElementVisible(driver, LoginPageUIs.UNSUCCESSFUL_ERROR_MESSAGE);
		return getTextElement(driver, LoginPageUIs.UNSUCCESSFUL_ERROR_MESSAGE);
	}

	@Step("Enter to 'Password' textbox with value is {0}")
	public void sendKeyToPaswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUIs.USER_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUIs.USER_PASSWORD_TEXTBOX, password);
		
	}

	public UserHomePageObject loginAsUser(String email, String password) {
		sendKeyToEmailTextbox(email);
		sendKeyToPaswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.openUserHomePage(driver);
	}

}
