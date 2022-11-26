package stepDefinitions;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class RegisterPageSteps {
	WebDriver driver;
	UserRegisterPageObject registerPage;
	
	String firstName="Auto";
	String lastName="Testing";
	String password="123456";

	public RegisterPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		registerPage = PageGeneratorManager.openUserRegisterPage(driver);
	}
	
	@Then("^Verify Error Empty FirtName message is displayed$")
	public void verifyErrorEmptyFirtNameMessageIsDisplayed()  {
		Assert.assertEquals(registerPage.getErrorEmptyFirtNameMessage(), "First name is required.");
	}

	@Given("^Reload Register page$")
	public void reloadRegisterPage() {
		registerPage.refreshCurrentPage(driver);
	}

	@Then("^Verify Success Register Message is displayed$")
	public void verifySuccessRegisterMessageIsDisplayed()  {
		Assert.assertEquals(registerPage.getSuccessRegisterMessage(), "Your registration completed");
	}
	
	@When("^Click to logout link$")
	public void clickToLogoutLink() {
		registerPage.clickToLogoutLink();
	}

}
