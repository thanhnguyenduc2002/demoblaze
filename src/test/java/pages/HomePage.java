package pages;

import java.awt.Button;
import java.io.ObjectInputFilter.Status;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Header
    @FindBy(xpath = "//a[@id='nava']")
    WebElement logoProductStore;

    @FindBy(xpath = "//li[@class='nav-item active']//a[@class='nav-link']")
    WebElement menuHome;

    @FindBy(xpath = "//a[normalize-space()='Contact']")
    WebElement menuContact;
    
    @FindBy(xpath = "  //a[normalize-space()='Cart']")
    WebElement menuCart;
    
    @FindBy(xpath = "//a[normalize-space()='About us']")
    WebElement menuAboutUs;

    @FindBy(xpath = "//a[@id='login2']")
    WebElement menuLogin;

    @FindBy(xpath = "//a[@id='signin2']")
    WebElement menuSignUp;

    // Carousel
    @FindBy(xpath = "//*[@id=\"carouselExampleIndicators\"]")
    WebElement carousel;

    @FindBy(xpath = "//img[@alt='First slide']")
    WebElement firstSlide;

    @FindBy(xpath = "//img[@alt='Second slide']")
    WebElement secondSlide;

    @FindBy(xpath = "//img[@alt='Thrird slide']")
    WebElement thirdSlide;

    @FindBy(xpath = "//span[@class='carousel-control-next-icon']")
    WebElement carouselNextIcon;

    @FindBy(xpath = "//span[@class='carousel-control-prev-icon']")
    WebElement carouselPrevIcon;

    // Categories
    @FindBy(xpath = "//a[text()='Phones']")
    WebElement categoriesPhones;

    @FindBy(xpath = "//a[text()='Laptops']")
    WebElement categoriesLaptops;

    @FindBy(xpath = "//a[text()='Monitors']")
    WebElement categoriesMonitors;

    @FindBy(xpath = "//button[@id='prev2']")
    WebElement prevButton;

    @FindBy(xpath = "//button[@id='next2']")
    WebElement nextButton;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[@class='col-lg-4 col-md-6 mb-4']/div[@class='card h-100']/div[@class='card-block']")
    WebElement products;
    
    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div")
    WebElement contactPopup;
    
    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div")
    WebElement logInPopup;
    
    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div")
    WebElement signUpPopup;
    
    @FindBy(xpath = "//*[@id=\"videoModal\"]/div/div")
    WebElement aboutUsPopup;
    
    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[1]/button")
    WebElement closeContactPopup;
    
    @FindBy(xpath = "//*[@id=\"videoModal\"]/div/div/div[1]/button")
    WebElement closeAboutUsPopup;
    
    @FindBy(xpath = "  //*[@id=\"logInModal\"]/div/div/div[1]/button")
    WebElement closeLogInPopup;
    
    @FindBy(xpath = "  //*[@id=\"signInModal\"]/div/div/div[1]/button")
    WebElement closeSignUpPopup;
    
    @FindBy(xpath = "//a[@class='btn btn-success btn-lg']")
    WebElement addToCart;
    
    @FindBy(xpath = "//button[normalize-space()='Place Order']")
    WebElement placeOrder;
    
    @FindBy(xpath = "//tbody/tr[1]/td[4]/a[1]")
    WebElement deletedFromCart;
    
    @FindBy(xpath = "//button[normalize-space()='Purchase']")
    WebElement purchase;
    
    @FindBy(xpath = "//*[@id=\"name\"]")
    WebElement name;
    
    @FindBy(xpath = "//*[@id=\"country\"]")
    WebElement country;
    
    @FindBy(xpath = "//*[@id=\"city\"]")
    WebElement city;
    
    @FindBy(xpath = "//*[@id=\"card\"]")
    WebElement creditCard;
    
    @FindBy(xpath = "//*[@id=\"month\"]")
    WebElement month;
    
    @FindBy(xpath = "//*[@id=\"year\"]")
    WebElement year;
    
    @FindBy(xpath = "//*[@id=\"orderModal\"]/div/div")
    WebElement placeOrderPopUp;
    
    public boolean logoIsDisplayed() {
        return logoProductStore.isDisplayed();
    }

    public List<WebElement> listNavbarItemsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> navBar = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("nav-item")));
        for (WebElement navItem : navBar) {
            System.out.println(navItem);
        }
        return navBar;
    }

    public int countNavbarItems() {
        return driver.findElements(By.className("nav-item")).size();
    }

    public boolean carouselIsDisplayed() {
        return carousel.isDisplayed();
    }

    public int countCarouselSliders() {
        List<WebElement> sliders = driver.findElements(By.className("carousel-item"));
        return sliders.size();
    }

    public boolean clickNextOnCarousel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickNextIcon = wait.until(ExpectedConditions.elementToBeClickable(carouselNextIcon));
        clickNextIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(firstSlide));
        return firstSlide.isDisplayed();
    }

    public boolean clickPrevOnCarousel() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement clickPrevIcon = wait.until(ExpectedConditions.elementToBeClickable(carouselPrevIcon));
        clickPrevIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(secondSlide));
        return firstSlide.isDisplayed();
    }

    public boolean carouselAutoSlides() {
        boolean status = firstSlide.isDisplayed();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(secondSlide));
        return status;
    }

    public List<WebElement> listOfProduct() {
        List<WebElement> products = driver.findElements(By.className("card-block"));
        for (WebElement product : products) {
            String productName = product.findElement(By.className("card-title")).getText();
            System.out.println("Name Product: " + productName);
        }
        return products;
    }

    public int productListIsVisible() {
        return driver.findElements(By.className("card-block")).size();
    }

    public List<WebElement> filterProductsByPhones() {
        int initialCount = driver.findElements(By.cssSelector(".card-block")).size();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        categoriesPhones.click();
        wait.until(driver -> driver.findElements(By.cssSelector(".card-block")).size() != initialCount);

        List<WebElement> phones = driver.findElements(By.className("card-block"));
        for (WebElement phone : phones) {
            String phoneName = phone.findElement(By.className("card-title")).getText();
            System.out.println("Name Product: " + phoneName);
        }
        System.out.println("There are 7 mobile phones in web");
        return phones;
    }

    public int countPhoneItems() {
        return driver.findElements(By.className("card-title")).size();
    }

    public List<WebElement> filterProductsByLaptops() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        categoriesLaptops.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[@class='card-title']/a[text()='MacBook air']")));

        List<WebElement> laptops = driver.findElements(By.className("card-block"));
        for (WebElement laptop : laptops) {
            String laptopName = laptop.findElement(By.className("card-title")).getText();
            System.out.println("Name Product: " + laptopName);
        }
        System.out.println("There are 6 laptops in web");
        return laptops;
    }

    public int countLaptopItems() {
        return driver.findElements(By.className("card-title")).size();
    }

    public List<WebElement> filterProductsByMonitors() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        categoriesMonitors.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[@class='card-title']/a[text()='ASUS Full HD']")));

        List<WebElement> monitors = driver.findElements(By.className("card-block"));
        for (WebElement monitor : monitors) {
            String monitorName = monitor.findElement(By.className("card-title")).getText();
            System.out.println("Name Product: " + monitorName);
        }
        System.out.println("There are 2 monitors in web");
        return monitors;
    }

    public int countMonitorItems() {
        return driver.findElements(By.className("card-title")).size();
    }

    public boolean nextPageNavigation() {
        logoProductStore.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//h4[@class='card-title']/a[text()='Samsung galaxy s6']")));
        nextButton.click();
        try {
            WebElement macbook = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h4[@class='card-title']/a[text()='MacBook air']")));
            return macbook.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Cannot find MacBook air.");
            return false;
        }
    }
    
    public boolean prevPageNavigation() {
        logoProductStore.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[@class='card-title']/a[text()='MacBook air']")));

        prevButton.click();

        try {
            WebElement macbook = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h4[@class='card-title']/a[text()='Samsung galaxy s7']")));
            return macbook.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Cannot find MacBook air.");
            return false;
        }
    }
    
    public void ProductDetailNavigation() {
    	logoProductStore.click();
    	System.out.println("URL of Home Page: " + driver.getCurrentUrl());
    	driver.findElement(By.xpath("//h4[@class='card-title']/a[text()='Samsung galaxy s6']")).click();
    }
    
    public void CartPageNavigation() {
    	logoProductStore.click();
    	System.out.println("URL of Home Page: " + driver.getCurrentUrl());
    	menuCart.click();
    }
    
    public boolean contactPopup() {
    	logoProductStore.click();
    	menuContact.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(contactPopup));
    	closeContactPopup.click();
    	return contactPopup.isDisplayed();
    }
    
    public boolean aboutUsPopup() {
    	logoProductStore.click();
    	menuAboutUs.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(aboutUsPopup));
    	closeAboutUsPopup.click();
    	return aboutUsPopup.isDisplayed();
    }
    
    public boolean logInPopup() {
    	logoProductStore.click();
    	menuLogin.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(logInPopup));
    	closeLogInPopup.click();
    	return logInPopup.isDisplayed();
    }
    
    public boolean signUpPopup() {
    	logoProductStore.click();
    	menuSignUp.click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(signUpPopup));
    	closeSignUpPopup.click();
    	return signUpPopup.isDisplayed();
    }
    
    public boolean addToCart() {
    	logoProductStore.click();
    	driver.findElement(By.xpath("//h4[@class='card-title']/a[text()='Samsung galaxy s6']")).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='price-container']"))).getText();
        int productPrice = Integer.parseInt(priceElement.replaceAll("[^0-9]","").trim());
    	addToCart.click();
    	menuCart.click();
    	wait.until(ExpectedConditions.visibilityOf(placeOrder));
    	String totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"totalp\"]"))).getText();
    	int totalPrice= Integer.parseInt(totalElement);
    	boolean status = false;
    	if(productPrice == totalPrice) {
    		status = true;
    	}	
    	return status;
    }

    public boolean deleteProductFromCart () {
       	logoProductStore.click();
    	driver.findElement(By.xpath("//h4[@class='card-title']/a[text()='Nokia lumia 1520']")).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='price-container']")));
    	addToCart.click();
    	menuCart.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"totalp\"]")));
    	wait.until(ExpectedConditions.visibilityOf(deletedFromCart));
    	deletedFromCart.click();
    	try {
    		WebElement nokia = driver.findElement( By.xpath("//td[normalize-space()='Nokia lumia 1520']"));
    		Boolean productStatus = wait.until(ExpectedConditions.invisibilityOf(nokia));
    		System.out.println("1");
        	return productStatus;
    		}
    	catch (TimeoutException e) {
    		System.out.println("Can not remove Product");
    		return false;
		}
    }
    
    public Boolean checkOut() {
       	logoProductStore.click();
    	driver.findElement(By.xpath("//h4[@class='card-title']/a[text()='Nexus 6']")).click();
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='price-container']")));
    	addToCart.click();
    	menuCart.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"totalp\"]")));
    	wait.until(ExpectedConditions.visibilityOf(placeOrder));
    	placeOrder.click();
    	wait.until(ExpectedConditions.visibilityOf(placeOrderPopUp));
    	name.sendKeys("Thanh");
    	country.sendKeys("Viet Nam");
    	city.sendKeys("Ha Noi");
    	creditCard.sendKeys("22110255177");
    	month.sendKeys("7");
    	year.sendKeys("2025");
    	purchase.click();
    	boolean purchaseSuccessful  = driver.findElement(By.className("sweet-alert")).isDisplayed();
    	WebElement okButton = driver.findElement(By.cssSelector("button.confirm"));
    	okButton.click();
    	return purchaseSuccessful;
    }
} 