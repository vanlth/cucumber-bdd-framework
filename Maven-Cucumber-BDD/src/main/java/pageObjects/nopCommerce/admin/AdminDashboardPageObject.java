package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.admin.AdminDashboardPageUIs;
import pageUIs.nopCommerce.admin.AdminLoginPageUIs;

public class AdminDashboardPageObject extends BasePage{
	private WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver){
		this.driver = driver;
	}

	public boolean isDashboardPageDisplayed() {
		waitForElementVisible(driver, AdminDashboardPageUIs.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUIs.DASHBOARD_HEADER);
	}

}
