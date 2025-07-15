package tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    public HomePage hp;

    // UI Tests
    @Test(priority = 1, groups = {"ui", "sanity"})
    public void verifyHomePageTitle() {
        logger.info("Running test: verifyHomePageTitle");
        Assert.assertEquals(driver.getTitle(), "STORE");
        logger.info("Verified homepage title is 'STORE'");
    }

    @Test(priority = 2, groups = {"ui"})
    public void verifyLogoIsDisplayed() {
        logger.info("Running test: verifyLogoIsDisplayed");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.logoIsDisplayed());
        logger.info("Logo is displayed successfully");
    }

    @Test(priority = 3, groups = {"ui"})
    public void verifyNavbarItemsDisplayed() {
        logger.info("Running test: verifyNavbarItemsDisplayed");
        hp = new HomePage(driver);
        hp.listNavbarItemsDisplayed();
        Assert.assertEquals(hp.countNavbarItems(), 8);
        logger.info("Navbar items count is correct");
    }

    @Test(priority = 4, groups = {"ui"})
    public void verifyCarouselHasThreeSlides() {
        logger.info("Running test: verifyCarouselHasThreeSlides");
        hp = new HomePage(driver);
        Assert.assertEquals(hp.countCarouselSliders(), 3);
        logger.info("Carousel has 3 slides");
    }

    @Test(priority = 5, groups = {"ui"})
    public void verifyCarouselIsDisplayed() {
        logger.info("Running test: verifyCarouselIsDisplayed");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.carouselIsDisplayed());
        logger.info("Carousel is displayed");
    }

    @Test(priority = 6, groups = {"ui"})
    public void verifyCarouselAutoSlides() {
        logger.info("Running test: verifyCarouselAutoSlides");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.carouselAutoSlides());
        logger.info("Carousel auto-slide verified");
    }

    @Test(priority = 7, groups = {"ui"})
    public void verifyClickNextOnCarousel() {
        logger.info("Running test: verifyClickNextOnCarousel");
        hp = new HomePage(driver);
        Assert.assertFalse(hp.clickNextOnCarousel());
        logger.info("Verified clicking next on carousel returns false (as expected)");
    }

    @Test(priority = 8, groups = {"ui"})
    public void verifyClickPrevOnCarousel() {
        logger.info("Running test: verifyClickPrevOnCarousel");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.clickPrevOnCarousel());
        logger.info("Verified clicking previous on carousel");
    }

    @Test(priority = 9, groups = {"ui", "sanity"})
    public void verifyProductListIsVisible() {
        logger.info("Running test: verifyProductListIsVisible");
        hp = new HomePage(driver);
        logger.info("------List of Product------");
        hp.listOfProduct();
        Assert.assertEquals(hp.productListIsVisible(), 9);
        logger.info("Product list is visible with correct count");
    }

    @Test(priority = 10, groups = {"filter"})
    public void verifyFilterPhones() throws InterruptedException {
        logger.info("Running test: verifyFilterPhones");
        hp = new HomePage(driver);
        hp.filterProductsByPhones();
        Assert.assertEquals(hp.countPhoneItems(), 7);
        logger.info("Phone filter returns correct item count");
    }

    @Test(priority = 11, groups = {"filter"})
    public void verifyFilterLaptops() {
        logger.info("Running test: verifyFilterLaptops");
        hp = new HomePage(driver);
        hp.filterProductsByLaptops();
        Assert.assertEquals(hp.countLaptopItems(), 6);
        logger.info("Laptop filter returns correct item count");
    }

    @Test(priority = 12, groups = {"filter"})
    public void verifyFilterMonitors() {
        logger.info("Running test: verifyFilterMonitors");
        hp = new HomePage(driver);
        hp.filterProductsByMonitors();
        Assert.assertEquals(hp.countMonitorItems(), 2);
        logger.info("Monitor filter returns correct item count");
    }

    @Test(priority = 13, groups = {"navigation"})
    public void verifyNextPageNavigation() {
        logger.info("Running test: verifyNextPageNavigation");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.nextPageNavigation());
        logger.info("Next page navigation works correctly");
    }

    @Test(priority = 14, groups = {"navigation"})
    public void verifyPrevPageNavigation() {
        logger.info("Running test: verifyPrevPageNavigation");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.nextPageNavigation());
        logger.info("Previous page navigation works correctly");
    }

    @Test(priority = 15, groups = {"navigation"})
    public void verifyProductDetailNavigation() {
        logger.info("Running test: verifyProductDetailNavigation");
        hp = new HomePage(driver);
        hp.ProductDetailNavigation();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.demoblaze.com/prod.html?idp_=1"));
        logger.info("Navigated to correct product detail page");
    }

    @Test(priority = 16, groups = {"navigation"})
    public void verifyCartPageNavigation() {
        logger.info("Running test: verifyCartPageNavigation");
        hp = new HomePage(driver);
        hp.CartPageNavigation();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.demoblaze.com/cart.html"));
        logger.info("Navigated to cart page");
    }

    @Test(priority = 17, groups = {"popup"})
    public void verifyContactPopup() {
        logger.info("Running test: verifyContactPopup");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.contactPopup());
        logger.info("Contact popup is displayed");
    }

    @Test(priority = 18, groups = {"popup"})
    public void verifyLoginPopup() {
        logger.info("Running test: verifyLoginPopup");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.logInPopup());
        logger.info("Login popup is displayed");
    }

    @Test(priority = 19, groups = {"popup"})
    public void verifyAboutUsPopup() {
        logger.info("Running test: verifyAboutUsPopup");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.aboutUsPopup());
        logger.info("About Us popup is displayed");
    }

    @Test(priority = 20, groups = {"popup"})
    public void verifySignUpPopup() {
        logger.info("Running test: verifySignUpPopup");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.signUpPopup());
        logger.info("Sign up popup is displayed");
    }

    @Test(priority = 21, groups = {"cart", "smoke"})
    public void verifyAddtoCart() {
        logger.info("Running test: verifyAddtoCart");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.addToCart());
        logger.info("Add to cart successful");
    }

    @Test(priority = 22, groups = {"cart"})
    public void verifyDeleteProductFromCart() {
        logger.info("Running test: verifyDeleteProductFromCart");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.deleteProductFromCart());
        logger.info("Product deleted from cart successfully");
    }

    @Test(priority = 23, groups = {"cart", "smoke"})
    public void verifyCheckOutSuccessful() {
        logger.info("Running test: verifyCheckOutSuccessful");
        hp = new HomePage(driver);
        Assert.assertTrue(hp.checkOut());
        logger.info("Checkout process completed successfully");
    }
}