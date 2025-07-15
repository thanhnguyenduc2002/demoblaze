package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.AboutUsPage;

public class AboutUsPageTest extends BaseTest {
	@Test(groups = {"aboutus"})
    public void verifyOpenAndClose() {
        logger.info("Starting test: verifyOpenAndClose");
        AboutUsPage about = new AboutUsPage(driver);
        logger.info("Clicking on 'About Us'");
        about.clickAboutUs();
        boolean isClosed = about.closeAboutUsPopup();
        assertEquals(true, isClosed);
        logger.info("Test 'verifyOpenAndClose' completed successfully.");
    }
}
