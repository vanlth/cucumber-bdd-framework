package stepDefinitions;

import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageGeneratorManager;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.nopCommerce.user.CommonPageObject;

public class CommonPageSteps {
	
	WebDriver driver;
	private String email = "auto" + getRandomNumber()+ "@gmail.com";
	private CommonPageObject commonPage;
	
	public CommonPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.openCommonPage(driver);
	}
	
	@When("^Click to \"([^\"]*)\" button$")
	public void clickToButton(String buttonText){
		commonPage.clickToButtonByText(driver, buttonText);
	}

	@When("^Click to \"([^\"]*)\" radio button$")
	public void clickToRadioButton(String radioLabelName){
		commonPage.checkToRadioByLabel(driver, radioLabelName);
	}

	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String textboxID, String value){
		if (textboxID.equals("Email")) {
			value = email;
		}
		commonPage.inputToTextboxByID(driver, textboxID, value);
	}

	@When("^Select to \"([^\"]*)\" dropdown with value \"([^\"]*)\"$")
	public void selectToDropdownWithValue(String dropAttributeName, String itemValue){
		commonPage.selectToDropdownByAttributeName(driver, dropAttributeName, itemValue);
	}

	@When("^Check to \"([^\"]*)\" checkbox$")
	public void checkToCheckbox(String checkboxLabelName){
		commonPage.clickToCheckboxByLabel(driver, checkboxLabelName);
	}


	@Then("^The valid text is displayed at \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void theValidTextIsDisplayedAtWithValue(String textboxID, String validText){	
		if (textboxID.equals("Email")) {
			validText = email;
		}
		Assert.assertEquals(commonPage.getTextboxValueByID(driver, textboxID), validText);
	}
	
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
