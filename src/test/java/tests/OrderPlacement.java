package tests;

import decorators.Browser;
import decorators.Driver;
import decorators.LogDriver;
import decorators.WebCoreDriver;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.UUID;

public class OrderPlacement {

    private static Driver driver;


    @BeforeTest
    public void testInit(){
        driver = new LogDriver(new WebCoreDriver());
        driver.start(Browser.CHROME);
    }

    @AfterTest
    public void testClean(){
        driver.quit();
    }

    private String generateUniqueEmail(){
        return String.format("%s@yopmail.com", UUID.randomUUID().toString());
    }



    private void login(String username){
        var usernameInput = driver.findElement(By.id("username"));
        usernameInput.typeText(username);
        var passwordInput = driver.findElement(By.id("password"));
        passwordInput.typeText("Comadres3");
        var loginButton = driver.findElement(By.xpath("//button[@name ='login']"));
        loginButton.click();
    }

    private void applyCoupon(String coupon) throws InterruptedException {
        var couponCodeInput = driver.findElement(By.id("coupon_code"));
        couponCodeInput.typeText(coupon);
        var applyCouponButton = driver.findElement(By.name("apply_coupon"));
        applyCouponButton.click();
        Thread.sleep(5000);
    }

    private void increaseProductQuantity (String productQuantity) throws InterruptedException {
        var quantityInput = driver.findElement(By.cssSelector("input[title='Qty']"));
        quantityInput.typeText(productQuantity);
        var updateCartButton = driver.findElement(By.cssSelector("[value*='Update cart']"));
        updateCartButton.click();
        Thread.sleep(5000);
    }

    private void addRocketToShoppingCart(){
        var addFalcon9ToCartButton = driver.findElement(By.cssSelector("[data-product_id*='28']"));
        addFalcon9ToCartButton.click();
        var viewCartFalcon9Button = driver.findElement(By.cssSelector(".post-28>a[title]"));
        viewCartFalcon9Button.click();
    }


    @Test
    public void validateOrderCreation() throws InterruptedException {
        driver.goToURL("http://demos.bellatrix.solutions/");
        Thread.sleep(5000);
        addRocketToShoppingCart();
        applyCoupon("happyBirthday");
        increaseProductQuantity("2");
        var cartTotal = driver.findElement(By.xpath("//td[@data-title='Total']//bdi"));
        Assert.assertEquals(cartTotal.getText(), "114.00€");
        var checkoutButton = driver.findElement(By.cssSelector(".checkout-button"));
        checkoutButton.click();
        var firstnameField = driver.findElement(By.id("billing_first_name"));
        firstnameField.typeText("Tackine");
        var lastnameField = driver.findElement(By.id("billing_last_name"));
        lastnameField.typeText("Roiveira");
        var countryDropdown = driver.findElement(By.id("select2-billing_country-container"));
        countryDropdown.click();
        var countrySearch = driver.findElement(By.className("select2-search__field"));
        countrySearch.typeText("United States");
        countrySearch.typeKey(Keys.ENTER);
        var streetAddress = driver.findElement(By.id("billing_address_1"));
        streetAddress.typeText("Rodeo Rd.");
        var cityAddressField = driver.findElement(By.id("billing_city"));
        cityAddressField.typeText("Hidalgo");
        var stateDropdown = driver.findElement(By.id("select2-billing_state-container"));
        stateDropdown.click();
        var stateSearch = driver.findElement(By.className("select2-search__field"));
        stateSearch.typeText("Texas");
        stateSearch.typeKey(Keys.ENTER);
        var postalCodeField = driver.findElement(By.id("billing_postcode"));
        postalCodeField.typeText("77494");
        var phoneField = driver.findElement(By.id("billing_phone"));
        phoneField.typeText("1234543222");
        var emailField = driver.findElement(By.id("billing_email"));
        emailField.typeText(generateUniqueEmail());
        var createAccount = driver.findElement((By.id("createaccount")));
        createAccount.click();
        var placeOrderButton = driver.findElement(By.id("place_order"));
        Thread.sleep(5000);
        placeOrderButton.click();
        Thread.sleep(15000);
        var orderPlacementTitle = driver.findElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");

    }


    @Test
    public void ValidateOrderAuthenticated() throws InterruptedException {
        driver.goToURL("http://demos.bellatrix.solutions/");
        Thread.sleep(5000);
        addRocketToShoppingCart();
        applyCoupon("happyBirthday");
        increaseProductQuantity("2");
        var cartTotal = driver.findElement(By.xpath("//td[@data-title='Total']//bdi"));
        Assert.assertEquals(cartTotal.getText(), "114.00€");
        var checkoutButton = driver.findElement(By.cssSelector(".checkout-button"));
        checkoutButton.click();
        var loginLink = driver.findElement(By.className("showlogin"));
        loginLink.click();
        login("joako_r@yopmail.com");
        Thread.sleep(5000);
        var placeOrderButton = driver.findElement(By.id("place_order"));
        placeOrderButton.click();
        Thread.sleep(15000);
        var orderPlacementTitle = driver.findElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");

    }

    @Test
    public void ValidateOrdersSaved() throws InterruptedException {
        driver.goToURL("http://demos.bellatrix.solutions/");
        var myAccountTab = driver.findElement(By.xpath("//ul[@class='nav-menu']//a[text()='My account']"));
        myAccountTab.click();
        login("joako_r@yopmail.com");
        var ordersSection = driver.findElement(By.xpath("//a[contains(text(),'Orders')]"));
        ordersSection.click();
        String orderNumber = "4181";
        var orderNumberRow = driver.findElement((By.xpath("//tr[.//td//a[contains(text(),'"+orderNumber+"')]]")));
        var orderStatus = driver.findElement(By.xpath("//td[@data-title='Status']"));
        var orderTotalAmount = driver.findElement(By.xpath("//td[@data-title='Total']/span"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderStatus.getText(), "On hold");
        softAssert.assertEquals(orderTotalAmount.getText(), "95.00");
    }

}
