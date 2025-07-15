package tests;



import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LogInPage;

public class LogInTest extends BaseTest {
	public LogInPage loginPage;
	@DataProvider(name = "LoginData")
    public Object[][] loginData() throws IOException {
        logger.info("Reading login data from Excel...");
        loginPage = new LogInPage(driver);
        Object[][] data = loginPage.readDataInExcel();
        logger.info("Total login test data rows: {}", data.length);
        return data;
    }
	
    @Test(groups = {"login", "smoke"},dataProvider = "LoginData")
    public void verifyLogin(String username, String password) {
        logger.info("Starting login test with username: {}, password: {}", username, password);
        loginPage = new LogInPage(driver);
        loginPage.login(username, password);
        boolean isSuccess = loginPage.loginSuccess();
        logger.info("Login success: {}", isSuccess);
        assertEquals(true, isSuccess, "Login failed with username: " + username);
        logger.info("Logging out");
        loginPage.Logout();
        logger.info("Finished test case for username: {}", username);
    }
}
