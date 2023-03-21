package facade;

import page_objects.CartPage;
import page_objects.MainPage;
import tests.data.PurchaseInfo;

public class NewPurchaseFacade extends PurchaseFacade{

    private final MainPage mainPage;
    private final CartPage cartPage;

    public NewPurchaseFacade(MainPage mainPage, CartPage cartPage) {
        this.mainPage = mainPage;
        this.cartPage = cartPage;
    }

    @Override
    protected void assertOrderReceived() {

    }

    @Override
    protected void fillBillingInfo(PurchaseInfo purchaseInfo) {

    }

    @Override
    protected void proceedToCheckout() {

    }

    @Override
    protected void assertTotalPrice(String expectedPrice) {

    }

    @Override
    protected void increaseProductQuantity(String quantity) {
        cartPage.increaseProductQuantity(quantity);
    }

    @Override
    protected void applyCoupon(String couponName) {
        cartPage.applyCoupon(couponName);
    }

    @Override
    protected void addItemToShoppingCart(String rocketName) {
        mainPage.open();
        mainPage.addRocketToShoppingCart();
    }
}
