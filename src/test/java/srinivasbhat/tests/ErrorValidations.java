package srinivasbhat.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import srinivasbhat.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test
	public void submitOrder() throws IOException {

		String emailID = "svishal.bhat7@gmail.com";
		String password = "TestwrongPWD";
		String errMsg = "Incorrect email or password";

		landingPage.loginApplication(emailID, password);
		Assert.assertEquals(errMsg, landingPage.getErrorMessage());
	}
}
