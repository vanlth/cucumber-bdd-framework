package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminLoginPageUIs;

public class AdminLoginPageObject extends BasePage{
	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver){
		this.driver = driver;
	}
	
	public void inputToUsernameTextbox(String email) {
		waitForElementVisible(driver, AdminLoginPageUIs.ADMIN_EMAIL_TEXTBOX);
		sendKeysToElement(driver, AdminLoginPageUIs.ADMIN_EMAIL_TEXTBOX, email);
	}
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUIs.ADMIN_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, AdminLoginPageUIs.ADMIN_PASSWORD_TEXTBOX, password);
	}
	public void clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUIs.LOGIN_BUTTON_AT_ADMIN_PAGE);
		clickToElement(driver, AdminLoginPageUIs.LOGIN_BUTTON_AT_ADMIN_PAGE);
	}
	
	public AdminDashboardPageObject loginAsAdmin(String email, String password) {
		inputToUsernameTextbox(email);
		inputToPasswordTextbox(password);
		clickToLoginButton();
		return PageGeneratorManager.openAdminDashboardPage(driver);
	}

}
