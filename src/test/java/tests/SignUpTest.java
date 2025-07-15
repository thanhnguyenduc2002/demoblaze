package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.SignUpPage;

public class SignUpTest extends BaseTest {
protected HomePage hp;
@Test(groups = {"signup", "smoke"})
public void verifySignUp() throws IOException {
	logger.info("Test: Verify Sign Up started.");


    SignUpPage signup = new SignUpPage(driver);

    logger.info("Wrote test data to Excel.");
    signup.writeDataInExcel();

    logger.info("Reading latest signup data from Excel");
    Object[][] data = signup.readDataInExcel();
    String username = (String) data[data.length - 1][0];
    String password = (String) data[data.length - 1][1];
    logger.info("Read data from Excel: username='{}', password='{}'", username, password);
    signup.signUp(username, password);
    logger.info("Sign up attempted with username='{}'", username);
    String popupMsg = signup.popUpSuccess();
    logger.info("Popup message after signup: {}", popupMsg);
    assertEquals(popupMsg, "Sign up successful.", "Signup was not successful.");
    logger.info("Test: Verify Sign Up passed.");
	} 
}
