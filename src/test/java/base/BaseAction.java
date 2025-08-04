package base;

import java.time.Duration;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseAction extends BaseTest {

	public void pageNavigation(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement	elementToClick = wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void clickOnCarousel(WebElement element1, WebElement element2) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement	element = wait.until(ExpectedConditions.elementToBeClickable(element1));
			element.click();
			wait.until(ExpectedConditions.invisibilityOf(element2));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<WebElement> filterProducts(WebElement categories, String xpath){
        categories.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        List<WebElement> products = driver.findElements(By.className("card-block"));
        for (WebElement product : products) {
            String productName = product.findElement(By.className("card-title")).getText();
            System.out.println("Name Product: " + productName);
        }
        return products;
	}
	
	public boolean clickPageNavigation(WebElement element1, String xpath1, String xpath2) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	     wait.until(ExpectedConditions.presenceOfElementLocated(
	     By.xpath(xpath1)));
	     element1.click();
	     try {
	            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath(xpath2)));
	            return product.isDisplayed();
	        } catch (TimeoutException e) {
	            System.out.println("Cannot find MacBook air.");
	            return false;
	        }
	}
	
	public boolean checkPopup(WebElement element, WebElement popUp, WebElement closeContactPopup) {
		element.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(popUp));
		closeContactPopup.click();
    	return popUp.isDisplayed();
	}

}
	