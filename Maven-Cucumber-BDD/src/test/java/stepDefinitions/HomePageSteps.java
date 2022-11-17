package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserHomePageObject;

public class HomePageSteps {
	WebDriver driver;
	private UserHomePageObject homePage;
	
	public HomePageSteps() {
		driver = Hooks.openAndQuitBrowser();
		homePage = PageGeneratorManager.openUserHomePage(driver);
	}
	
	@Given("^Open Register page$")
	public void openRegisterPage()  {
		System.out.println("open register");
		homePage.openUserRegisterPage();
	}
	
	@Given("^Open Login Page$")
	public void openLoginPage() {
		homePage.openUserLoginPage();
		}
	
	@Given("^Open Custommer Infor page$")
	public void openCustommerInforPage() {
		homePage.getUserCustomInforPage();
	}

}
