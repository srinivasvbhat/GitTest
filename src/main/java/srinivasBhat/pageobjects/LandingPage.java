package srinivasBhat.pageobjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import srinivasBhat.AbsractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// 1st method
	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// 2nd method
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	@FindBy(xpath="(//input[@name='search'])[2]")
	WebElement search;
	
	@FindBy(xpath = "//*[@id='products']/div[1]/div[2]/div/div/div/h5") // Adjust the selector as per your page
    WebElement productList;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[1]")
	WebElement Home;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue prodCatalogue = new ProductCatalogue(driver);
		return prodCatalogue;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void searchProduct(String text) {
        waitForElementToBeClickable(search);
        search.click();
        search.clear(); // Clear the search field before entering new text
        search.sendKeys(text);
        search.sendKeys(Keys.ENTER);
        waitForSearchResults(); // Wait for search results to appear
    }
	
	public void searchProduct() {
        waitForElementToBeClickable(search);
        search.click();
        search.clear(); 
	}

    private void waitForSearchResults() {
        // Wait for the product list to be visible after performing the search
        wait.until(ExpectedConditions.visibilityOf(productList));
    }

    private void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    public void HomeLink()
    {
    	Home.click();
    }

}
