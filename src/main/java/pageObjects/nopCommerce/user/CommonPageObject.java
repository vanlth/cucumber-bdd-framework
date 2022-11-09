package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CommonPageUIs;

public class CommonPageObject extends BasePage{
	
	private WebDriver driver;
	
	public CommonPageObject(WebDriver driver){
		this.driver = driver;
	}

	/** Input to dynamic textbox by ID
	 * 
	 * @param driver
	 * @param textboxID
	 * @param value
	 */
	public void inputToTextboxByID(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, CommonPageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendKeysToElement(driver, CommonPageUIs.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}

	/** Click to dynamic button by Text
	 * 
	 * @param driver
	 * @param buttonText
	 */
	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, CommonPageUIs.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, CommonPageUIs.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}
	
	/**Check to dynamic radio button by label name
	 * 
	 * @param driver
	 * @param radioLabelName
	 */
	public void checkToRadioByLabel(WebDriver driver, String radioLabelName) {
		waitForElementClickable(driver, CommonPageUIs.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabelName);
		checkToDefaultCheckboxRadio(driver, CommonPageUIs.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabelName);
	}
	
	/**Select item in dynamic dropdown by attribute name
	 * 
	 * @param driver
	 * @param DropAttributeName
	 * @param itemValue
	 */
	public void selectToDropdownByAttributeName(WebDriver driver, String DropAttributeName, String itemValue) {
		waitForElementClickable(driver, CommonPageUIs.DYNAMIC_DROPDOWN_BY_NAME_ATTRIBUTE, DropAttributeName);
		selectItemInDefaultDropdown(driver, CommonPageUIs.DYNAMIC_DROPDOWN_BY_NAME_ATTRIBUTE, itemValue, DropAttributeName);
	}
	
	/**Check to dynamic checkbox by label name
	 * 
	 * @param driver
	 * @param checkboxLabelName
	 */
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickable(driver, CommonPageUIs.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToDefaultCheckboxRadio(driver, CommonPageUIs.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		waitForElementVisible(driver, CommonPageUIs.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		return getAttributeValue(driver, CommonPageUIs.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
}
