package tests;

import decorators.Browser;
import observers.BrowserBehavior;
import observers.ExecutionBrowser;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page_objects.CartPageBase;
import page_objects.MainPageBase;

import java.util.UUID;


@ExecutionBrowser(browser = Browser.CHROME, browserBehavior = BrowserBehavior.RESTART_EVERY_TIME)
public class OrderPlacement extends BaseTest{
    private MainPageBase mainPage;
    private CartPageBase cartPage;

    @Override
    protected void testInit() {
        mainPage = new MainPageBase(getDriver());
        cartPage = new CartPageBase(getDriver());

    }

    @Override
    protected void testCleanup() {


    }

    private String generateUniqueEmail(){
        return String.format("%s@yopmail.com", UUID.randomUUID().toString());
    }

    private void login(String username){
        var usernameInput = getDriver().findElement(By.id("username"));
        usernameInput.typeText(username);
        var passwordInput = getDriver().findElement(By.id("password"));
        passwordInput.typeText("Comadres3");
        var loginButton = getDriver().findElement(By.xpath("//button[@name ='login']"));
        loginButton.click();
        getDriver().waitForAjax();
    }

    @Test
    public void validateOrderCreation() {
        mainPage.open();
        getDriver().waitForAjax();
        mainPage.addRocketToShoppingCart();
        cartPage.applyCoupon("happyBirthday");
        cartPage.increaseProductQuantity("2");
        var cartTotal = cartPage.getCartTotal();
        Assert.assertEquals(cartTotal, "114.00€");
        cartPage.clickProceedToCheckout();
        var firstnameField = getDriver().findElement(By.id("billing_first_name"));
        firstnameField.typeText("Tackine");
        var lastnameField = getDriver().findElement(By.id("billing_last_name"));
        lastnameField.typeText("Roiveira");
        var countryDropdown = getDriver().findElement(By.id("select2-billing_country-container"));
        countryDropdown.click();
        var countrySearch = getDriver().findElement(By.className("select2-search__field"));
        countrySearch.typeText("United States");
        countrySearch.typeKey(Keys.ENTER);
        var streetAddress = getDriver().findElement(By.id("billing_address_1"));
        streetAddress.typeText("Rodeo Rd.");
        var cityAddressField = getDriver().findElement(By.id("billing_city"));
        cityAddressField.typeText("Hidalgo");
        var stateDropdown = getDriver().findElement(By.id("select2-billing_state-container"));
        stateDropdown.click();
        var stateSearch = getDriver().findElement(By.className("select2-search__field"));
        stateSearch.typeText("Texas");
        stateSearch.typeKey(Keys.ENTER);
        var postalCodeField = getDriver().findElement(By.id("billing_postcode"));
        postalCodeField.typeText("77494");
        var phoneField = getDriver().findElement(By.id("billing_phone"));
        phoneField.typeText("1234543222");
        var emailField = getDriver().findElement(By.id("billing_email"));
        emailField.typeText(generateUniqueEmail());
        var createAccount = getDriver().findElement((By.id("createaccount")));
        createAccount.click();
        var placeOrderButton = getDriver().findElement(By.id("place_order"));
        getDriver().waitForAjax();
        placeOrderButton.click();
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        var orderPlacementTitle = getDriver().findElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");
    }

    @Test
    public void ValidateOrderAuthenticated() throws InterruptedException {
        mainPage.open();
        mainPage.addRocketToShoppingCart();
        cartPage.applyCoupon("happyBirthday");
        cartPage.increaseProductQuantity("2");
        var cartTotal = cartPage.getCartTotal();
        Assert.assertEquals(cartTotal, "114.00€");
        cartPage.clickProceedToCheckout();
        var loginLink = getDriver().findElement(By.className("showlogin"));
        loginLink.click();
        login("joako_r@yopmail.com");
        var placeOrderButton = getDriver().findElement(By.id("place_order"));
        placeOrderButton.click();
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        var orderPlacementTitle = getDriver().findElement(By.cssSelector("h1.entry-title"));
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");
    }


    @Test
    public void ValidateOrdersSaved() throws InterruptedException {
        mainPage.open();
        mainPage.mainMenuSection().openMyAccountPage();
        login("joako_r@yopmail.com");
        var ordersSection = getDriver().findElement(By.xpath("//a[contains(text(),'Orders')]"));
        ordersSection.click();
        String orderNumber = "4788";
        var orderNumberRow = getDriver().findElement((By.xpath("//tr[.//td//a[contains(text(),'"+orderNumber+"')]]")));
        var orderStatus = getDriver().findElement(By.xpath("//td[@data-title='Status']"));
        var orderTotalAmount = getDriver().findElement(By.xpath("//td[@data-title='Total']/span"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderStatus.getText(), "On hold");
        softAssert.assertEquals(orderTotalAmount.getText(), "95.00");
    }
}
