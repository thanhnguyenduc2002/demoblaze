package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.ContactPage;

public class ContactPageTest extends BaseTest {
	@Test(groups = {"contact", "regression"})
    public void verifyInputContact() throws IOException {
        logger.info("Starting test: verifyInputContact");
        ContactPage contactPage = new ContactPage(driver);
        logger.info("Writing random contact data to Excel...");
        contactPage.writeDataInExcel();
        logger.info("Reading latest contact data from Excel...");
        Object[][] data = contactPage.readDataInExcel();
        String email = (String) data[data.length - 1][0];
        String name = (String) data[data.length - 1][1];
        String message = (String) data[data.length - 1][2];
        logger.info("Inputting contact form with: Email = {}, Name = {}, Message = {}", email, name, message);
        contactPage.contact(email, name, message);
        String popupText = contactPage.popUpSuccess();
        logger.info("Popup message received: {}", popupText);
        assertEquals(popupText, "Thanks for the message!!");
        logger.info("Contact form submission verified successfully.");
    }
}
