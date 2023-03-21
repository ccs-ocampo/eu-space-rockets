package facade;

import tests.data.PurchaseInfo;

/* Facade and template method design pattern
   Supporting two versions of an app in a single workflow
* */
public abstract class PurchaseFacade {
    public void verifyItemPurchase (String rocketName, String couponName, String quantity, String expectedPrice, PurchaseInfo purchaseInfo) {
        addItemToShoppingCart(rocketName);
        applyCoupon(couponName);
        increaseProductQuantity(quantity);
        assertTotalPrice(expectedPrice);
        proceedToCheckout();
        fillBillingInfo(purchaseInfo);
        assertOrderReceived();
    }

    protected abstract void assertOrderReceived();

    protected abstract void fillBillingInfo(PurchaseInfo purchaseInfo);

    protected abstract void proceedToCheckout();

    protected abstract void assertTotalPrice(String expectedPrice);

    protected abstract void increaseProductQuantity(String quantity);

    protected abstract void applyCoupon(String couponName);

    protected abstract void addItemToShoppingCart(String rocketName);


}
