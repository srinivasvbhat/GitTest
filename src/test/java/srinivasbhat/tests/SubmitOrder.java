package srinivasbhat.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import srinivasBhat.pageobjects.CartPage;
import srinivasBhat.pageobjects.CheckOutPage;
import srinivasBhat.pageobjects.ConfirmationPage;
import srinivasBhat.pageobjects.ProductCatalogue;
import srinivasbhat.TestComponents.BaseTest;

public class SubmitOrder extends BaseTest {

	@Test
	public void submitOrder() throws IOException {

		String productName = "ZARA COAT 3";
		String countryName = "India";
		String emailID = "svishal.bhat7@gmail.com";
		String password = "Nimmibadri7";
		String orderConfirmationMessage = "THANKYOU FOR THE ORDER.";

		ProductCatalogue prodCatalogue = landingPage.loginApplication(emailID, password);
		List<WebElement> products = prodCatalogue.getProductsList();
		prodCatalogue.addProductToCart(productName);
		CartPage cartPage = prodCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry(countryName);
		ConfirmationPage confirmationPage = checkOutPage.placeOrder();
		String orderText = confirmationPage.getOrderConfirmationMessage();
		Assert.assertTrue(orderText.equalsIgnoreCase(orderConfirmationMessage));
	}
}
