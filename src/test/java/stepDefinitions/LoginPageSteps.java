package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class LoginPageSteps {
	WebDriver driver;
	private UserLoginPageObject loginPage;
	
	public LoginPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		loginPage = PageGeneratorManager.openUserLoginPage(driver);
	}
	
	@When("^Click to Login button$")
	public void clickToLoginButton(){
		loginPage.clickToLoginButton();
	}
}
