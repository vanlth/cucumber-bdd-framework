package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.RegisterPageUIs;

public class UserRegisterPageObject extends BasePage{
	private WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver){
		this.driver = driver;
	}
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUIs.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUIs.REGISTER_BUTTON);
		
	}

	public String getErrorEmptyFirtNameMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_EMPTY_FIRST_NAME_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_EMPTY_FIRST_NAME_MESSAGE);
	}

	public String getErrorEmptyLastNameMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_EMPTY_LAST_NAME_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_EMPTY_LAST_NAME_MESSAGE);
	}

	public String getErrorEmptyEmailMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_EMPTY_EMAIL_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_EMPTY_EMAIL_MESSAGE);
	}

	public String getErrorPasswordMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_PASSWORD_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_PASSWORD_MESSAGE);
	}

	public String getErrorConfirmPasswordMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_CONFIRM_PASSWORD_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_CONFIRM_PASSWORD_MESSAGE);
	}

	public void sendKeyToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, RegisterPageUIs.FIRST_NAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUIs.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void sendKeyToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, RegisterPageUIs.LAST_NAME_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUIs.LAST_NAME_TEXTBOX, lastName);
		
	}

	public void sendKeyToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUIs.EMAIL_ADDRESS_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUIs.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void sendKeyToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUIs.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUIs.PASSWORD_TEXTBOX, password);
		
	}

	public void sendKeyToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUIs.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, RegisterPageUIs.CONFIRM_PASSWORD_TEXTBOX, password);
		
	}

	public String getErrorInvalidEmailMessage() {
		waitForElementVisible(driver, RegisterPageUIs.ERROR_INVALID_EMAIL_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.ERROR_INVALID_EMAIL_MESSAGE);
	}

	public String getSuccessRegisterMessage() {
		waitForElementVisible(driver, RegisterPageUIs.SUCCESS_REGISTER_MESSAGE);
		return getTextElement(driver, RegisterPageUIs.SUCCESS_REGISTER_MESSAGE);
	}

	@Step("Click to Logout button")
	public UserHomePageObject clickToLogoutLink() {
		waitForElementVisible(driver, RegisterPageUIs.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUIs.LOGOUT_LINK);
		return PageGeneratorManager.openUserHomePage(driver);
		
	}

}
