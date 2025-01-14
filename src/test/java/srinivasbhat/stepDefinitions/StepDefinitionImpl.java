package srinivasbhat.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import srinivasBhat.pageobjects.CartPage;
import srinivasBhat.pageobjects.CheckOutPage;
import srinivasBhat.pageobjects.ConfirmationPage;
import srinivasBhat.pageobjects.LandingPage;
import srinivasBhat.pageobjects.ProductCatalogue;
import srinivasbhat.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue prodCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on eCommerce page")
	public void I_landed_on_eCommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		prodCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to cart$")
	public void i_add_the_product_to_cart(String productName) {
		List<WebElement> products = prodCatalogue.getProductsList();
		prodCatalogue.addProductToCart(productName);
	}

	@And("^checkout the (.+) by selecting (.+) and submit the order$")
	public void checkout_the_product_and_country_and_submit_the_order(String productName, String countryName) {
		CartPage cartPage = prodCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry(countryName);
		confirmationPage = checkOutPage.placeOrder();
	}

	@Then("{string} message should be displayed in confirmationPage")
	public void message_displayed_in_confirmationPage(String string) {
		String orderText = confirmationPage.getOrderConfirmationMessage();
		Assert.assertTrue(orderText.equalsIgnoreCase(string));
	}

	@Then("{string} message is displayed")
	public void something_message_is_displayed(String strArg1) throws Throwable {

		Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		driver.close();
	}
}
