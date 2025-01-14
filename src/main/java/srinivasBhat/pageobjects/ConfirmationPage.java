package srinivasBhat.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import srinivasBhat.AbsractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {

	WebDriver driver;

	@FindBy(css = ".hero-primary")
	WebElement thankYouText;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getOrderConfirmationMessage() {
		String orderText = thankYouText.getText();
		return orderText;
	}
}
