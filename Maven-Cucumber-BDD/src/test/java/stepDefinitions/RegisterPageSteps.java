package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.DataTable;
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
	
//	@When("^Input infor invalid to Register Form$")
//	public void inputInforToRegisterForm(DataTable customerTable) {
//	    List<Map<String, String>> customer = customerTable.asMaps(String.class, String.class);
//	    registerPage.sendKeyToFirstNameTextbox(customer.get(0).get("FirstName"));
//	    registerPage.sendKeyToLastNameTextbox(customer.get(0).get("LastName"));
//	    registerPage.sendKeyToEmailTextbox("123456");
//	    registerPage.sendKeyToPasswordTextbox(customer.get(0).get("Password"));
//	    registerPage.sendKeyToConfirmPasswordTextbox(customer.get(0).get("Password"));
//	}

	@Then("^Verify Error Invalid Email message is displayed$")
	public void verifyErrorInvalidEmailMessageIsDisplayed() {
		Assert.assertEquals(registerPage.getErrorInvalidEmailMessage(),"Wrong email");
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
