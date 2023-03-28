package tests;

import decorators.Browser;
import decorators.interface_segregation.BrowseService;
import decorators.interface_segregation.ElementFindService;
import decorators.interface_segregation.NavigationService;
import observers.BrowserBehavior;
import observers.ExecutionBrowser;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import page_objects.CartPage;
import page_objects.MainPage;

import java.util.UUID;


@ExecutionBrowser(browser = Browser.CHROME, browserBehavior = BrowserBehavior.RESTART_EVERY_TIME)
public class OrderPlacement extends BaseTest{
    private MainPage mainPage;
    private CartPage cartPage;

    @Override
    protected void testInit() {
        mainPage = new MainPage((NavigationService) getDriver(), (ElementFindService) getDriver(), (BrowseService) getDriver());
        cartPage = new CartPage((NavigationService) getDriver(), (ElementFindService) getDriver(), (BrowseService) getDriver());

    }

    @Override
    protected void testCleanup() {


    }

    private String generateUniqueEmail(){
        return String.format("%s@yopmail.com", UUID.randomUUID().toString());
    }

    private void login(String username){
        var usernameInput = getDriver().findById("username");
        usernameInput.typeText(username);
        var passwordInput = getDriver().findById("password");
        passwordInput.typeText("Comadres3");
        var loginButton = getDriver().findByXPath("//button[@name ='login']");
        loginButton.click();
        getDriver().waitForAjax();
    }

    @Test
    public void validateOrderCreation() {
        mainPage.open();
        getDriver().waitForAjax();
        mainPage.addRocketToShoppingCart();
        cartPage.applyCoupon("happyBirthday")
            .increaseProductQuantity("2");
        var cartTotal = cartPage.getCartTotal();
        Assert.assertEquals(cartTotal, "114.00€");
        cartPage.clickProceedToCheckout();
        var firstnameField = getDriver().findById("billing_first_name");
        firstnameField.typeText("Tackine");
        var lastnameField = getDriver().findById("billing_last_name");
        lastnameField.typeText("Roiveira");
        var countryDropdown = getDriver().findById("select2-billing_country-container");
        countryDropdown.click();
        var countrySearch = getDriver().findByClass("select2-search__field");
        countrySearch.typeText("United States");
        countrySearch.typeKey(Keys.ENTER);
        var streetAddress = getDriver().findById("billing_address_1");
        streetAddress.typeText("Rodeo Rd.");
        var cityAddressField = getDriver().findById("billing_city");
        cityAddressField.typeText("Hidalgo");
        var stateDropdown = getDriver().findById("select2-billing_state-container");
        stateDropdown.click();
        var stateSearch = getDriver().findByClass("select2-search__field");
        stateSearch.typeText("Texas");
        stateSearch.typeKey(Keys.ENTER);
        var postalCodeField = getDriver().findById("billing_postcode");
        postalCodeField.typeText("77494");
        var phoneField = getDriver().findById("billing_phone");
        phoneField.typeText("1234543222");
        var emailField = getDriver().findById("billing_email");
        emailField.typeText(generateUniqueEmail());
        var createAccount = getDriver().findById("createaccount");
        createAccount.click();
        var placeOrderButton = getDriver().findById("place_order");
        getDriver().waitForAjax();
        placeOrderButton.click();
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        var orderPlacementTitle = getDriver().findByCss("h1.entry-title");
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");
    }

    @Test
    public void ValidateOrderAuthenticated() throws InterruptedException {
        mainPage.open();
        mainPage.addRocketToShoppingCart();
        cartPage.applyCoupon("happyBirthday")
            .increaseProductQuantity("2");
        var cartTotal = cartPage.getCartTotal();
        Assert.assertEquals(cartTotal, "114.00€");
        cartPage.clickProceedToCheckout();
        var loginLink = getDriver().findByClass("showlogin");
        loginLink.click();
        login("joako_r@yopmail.com");
        var placeOrderButton = getDriver().findById("place_order");
        placeOrderButton.click();
        getDriver().waitForAjax();
        getDriver().waitUntilPageLoadsCompletely();
        var orderPlacementTitle = getDriver().findByCss("h1.entry-title");
        Assert.assertEquals(orderPlacementTitle.getText(), "Order received");
    }


    @Test
    public void ValidateOrdersSaved() throws InterruptedException {
        mainPage.open();
        mainPage.mainMenuSection().openMyAccountPage();
        login("joako_r@yopmail.com");
        var ordersSection = getDriver().findByXPath("//a[contains(text(),'Orders')]");
        ordersSection.click();
        String orderNumber = "4788";
        var orderNumberRow = getDriver().findByXPath("//tr[.//td//a[contains(text(),'"+orderNumber+"')]]");
        var orderStatus = getDriver().findByXPath("//td[@data-title='Status']");
        var orderTotalAmount = getDriver().findByXPath("//td[@data-title='Total']/span");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(orderStatus.getText(), "On hold");
        softAssert.assertEquals(orderTotalAmount.getText(), "95.00");
    }
}
