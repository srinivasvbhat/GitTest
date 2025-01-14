package srinivasBhat.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import srinivasBhat.AbsractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement countrySelect;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	By selectCountry = By.cssSelector(".ta-results");

	public void selectCountry(String CountryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(selectCountry);
		countrySelect.click();
	}

	public ConfirmationPage placeOrder() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		waitUntilElementIsClickable(placeOrder);
		placeOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
