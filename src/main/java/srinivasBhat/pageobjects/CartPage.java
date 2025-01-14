package srinivasBhat.pageobjects;

import java.util.List;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import srinivasBhat.AbsractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css = "button[class='btn btn-danger']")
	private List<WebElement> getDeletebuttons;
	
	@FindBy(css = ".ng-star-inserted >h1")
	WebElement emptyCartMessage;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
		

	}
	
	// Method to delete all items from the cart
    public void deleteAllItems() throws InterruptedException {
    	List<WebElement> deleteButtons = getDeletebuttons;
    	while (!deleteButtons.isEmpty()) {
          for (WebElement deleteButton : deleteButtons) {
            try {
                deleteButton.click();
                waitForStaleness(deleteButton);
            } catch (StaleElementReferenceException e) {
            	// Handle stale element reference exception
                // Re-locate the elements or handle the situation accordingly
                System.out.println("Stale element reference: " + e.getMessage());
            }
          }
          deleteButtons = getDeletebuttons;
    	}
    }

    // Method to check if the cart is empty
    public boolean isCartEmpty() {
        try {
          //  WebElement emptyCartMessage = driver.findElement(By.cssSelector(".mt-3"));
        	System.out.println("Cart Message is:" +emptyCartMessage.getText());
            return emptyCartMessage.getText().equals("No Products in Your Cart !");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // If the element is not found, assume cart is empty
            return true;
        }
    }

}
