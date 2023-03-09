package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderPlacement {

    private static WebDriver driver;


    @BeforeTest
    public void testInit(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void testClean(){

        //driver.quit();
    }
    
    private WebElement waitAndFindElement(By by) {
        var webDriverWait = new WebDriverWait(driver, 30);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    
    private List<WebElement> waitAndFindElements(By by) {
        var webDriverWait = new WebDriverWait(driver, 30);
        return webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    private void waitToBeClickable(By by){
        var webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
    }

    private void login(String username){
        WebElement usernameInput = waitAndFindElement(By.id("username"));
        usernameInput.sendKeys(username);
        WebElement passwordInput = waitAndFindElement(By.id("password"));
        passwordInput.sendKeys("Comadres3");
        WebElement loginButton = waitAndFindElement(By.xpath("//button[@name ='login']"));
        loginButton.click();
    }

    private void applyCoupon(String coupon) throws InterruptedException {
        WebElement couponCodeInput = waitAndFindElement(By.id("coupon_code"));
        couponCodeInput.sendKeys(coupon);
        WebElement applyCouponButton = waitAndFindElement(By.name("apply_coupon"));
        waitToBeClickable(By.name("apply_coupon"));
        applyCouponButton.click();
        Thread.sleep(5000);
    }

    private void increaseProductQuantity (String productQuantity) throws InterruptedException {
        WebElement quantityInput = waitAndFindElement(By.cssSelector("input[title='Qty']"));
        quantityInput.clear();
        quantityInput.sendKeys(productQuantity);
        waitToBeClickable(By.cssSelector("[value*='Update cart']"));
        WebElement updateCartButton = waitAndFindElement(By.cssSelector("[value*='Update cart']"));
        updateCartButton.click();
        Thread.sleep(5000);
    }

    private void addRocketToShoppingCart(){
        WebElement addFalcon9ToCartButton = waitAndFindElement(By.cssSelector("[data-product_id*='28']"));
        addFalcon9ToCartButton.click();
        WebElement viewCartFalcon9Button = waitAndFindElement(By.cssSelector(".post-28>a[title]"));
        viewCartFalcon9Button.click();
    }


    @Test
    public void validateOrderCreation() throws InterruptedException {
        driver.navigate().to("http://demos.bellatrix.solutions/");
        Thread.sleep(5000);
        addRocketToShoppingCart();
        applyCoupon("happyBirthday");
        increaseProductQuantity("2");
        WebElement cartTotal = waitAndFindElement(By.xpath("//td[@data-title='Total']//bdi"));
        Assert.assertEquals(cartTotal.getText(), "114.00€");
        WebElement checkoutButton = waitAndFindElement(By.cssSelector(".checkout-button"));
        checkoutButton.click();
        WebElement firstnameField = waitAndFindElement(By.id("billing_first_name"));
        firstnameField.sendKeys("Tackine");
        WebElement lastnameField = waitAndFindElement(By.id("billing_last_name"));
        lastnameField.sendKeys("Roiveira");
        WebElement countryDropdown = waitAndFindElement(By.id("select2-billing_country-container"));
        countryDropdown.click();
        WebElement countrySearch = waitAndFindElement(By.className("select2-search__field"));
        countrySearch.sendKeys("United States");
        countrySearch.sendKeys(Keys.ENTER);
        WebElement streetAddress = waitAndFindElement(By.id("billing_address_1"));
        streetAddress.sendKeys("Rodeo Rd.");
        WebElement cityAddressField = waitAndFindElement(By.id("billing_city"));
        cityAddressField.sendKeys("Hidalgo");
        WebElement stateDropdown = waitAndFindElement(By.id("select2-billing_state-container"));
        stateDropdown.click();
        WebElement stateSearch = waitAndFindElement(By.className("select2-search__field"));
        stateSearch.sendKeys("Texas");
        stateSearch.sendKeys(Keys.ENTER);
        WebElement postalCodeField = waitAndFindElement(By.id("billing_postcode"));
        postalCodeField.sendKeys("77494");
        WebElement phoneField = waitAndFindElement(By.id("billing_phone"));
        phoneField.sendKeys("1234543222");
        WebElement emailField = waitAndFindElement(By.id("billing_email"));
        emailField.sendKeys("Toako_661@yopmail.com");
        WebElement createAccount = waitAndFindElement((By.id("createaccount")));
        createAccount.click();
        waitToBeClickable(By.id("place_order"));
        WebElement placeOrderButton = waitAndFindElement(By.id("place_order"));
        Thread.sleep(5000);
        placeOrderButton.click();
        Thread.sleep(15000);
        WebElement orderPlacementTitle = waitAndFindElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");

    }


    @Test
    public void ValidateOrderAuthenticated() throws InterruptedException {
        driver.navigate().to("http://demos.bellatrix.solutions/");
        Thread.sleep(5000);
        addRocketToShoppingCart();
        applyCoupon("happyBirthday");
        increaseProductQuantity("2");
        WebElement cartTotal = waitAndFindElement(By.xpath("//td[@data-title='Total']//bdi"));
        Assert.assertEquals(cartTotal.getText(), "114.00€");
        WebElement checkoutButton = waitAndFindElement(By.cssSelector(".checkout-button"));
        checkoutButton.click();
        WebElement loginLink = waitAndFindElement(By.className("showlogin"));
        loginLink.click();
        login("joako_r@yopmail.com");
        Thread.sleep(5000);
        waitToBeClickable(By.id("place_order"));
        WebElement placeOrderButton = waitAndFindElement(By.id("place_order"));
        placeOrderButton.click();
        Thread.sleep(15000);
        WebElement orderPlacementTitle = waitAndFindElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");

    }

    @Test
    public void ValidateOrdersSaved() throws InterruptedException {
        driver.navigate().to("http://demos.bellatrix.solutions/");
        WebElement myAccountTab = waitAndFindElement(By.xpath("//ul[@class='nav-menu']//a[text()='My account']"));
        myAccountTab.click();
        login("joako_r@yopmail.com");
        WebElement ordersSection = waitAndFindElement(By.xpath("//a[contains(text(),'Orders')]"));
        ordersSection.click();
        String orderNumber = "4181";
        WebElement orderNumberRow = waitAndFindElement((By.xpath("//tr[.//td//a[contains(text(),'"+orderNumber+"')]]")));
        WebElement orderDate = orderNumberRow.findElement(By.xpath("//td/time"));
        WebElement orderStatus = waitAndFindElement(By.xpath("//td[@data-title='Status']"));
        WebElement orderTotalAmount = waitAndFindElement(By.xpath("//td[@data-title='Total']/span"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderDate.getText(), "December 10, 2022");
        softAssert.assertEquals(orderStatus.getText(), "On hold");
        softAssert.assertEquals(orderTotalAmount.getText(), "95.00");
    }

}
