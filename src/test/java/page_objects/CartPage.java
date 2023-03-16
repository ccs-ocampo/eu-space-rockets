package page_objects;

import decorators.Driver;
import page_objects.page_elements.CartPageElements;

public class CartPage extends BaseEShopPage {
    private final String URL = "http://demos.bellatrix.solutions/";

    public CartPage(Driver driver) {
        super(driver);
    }

    protected String getUrl(){
        return  "http://demos.bellatrix.solutions/cart";
    }

    private CartPageElements elements(){
        return new CartPageElements(driver);
    }

    public void applyCoupon(String coupon) {
        elements().couponCodeTextField().typeText(coupon);
        elements().applyCouponButton().click();
        driver.waitForAjax();
    }

    public void increaseProductQuantity(String productQuantity) {
        elements().quantityInput().typeText(productQuantity);
        driver.waitForAjax();
        elements().updateCartButton().click();
        driver.waitForAjax();
    }

    public String getCartTotal(){
        return elements().cartTotal().getText();
    }

    public void clickProceedToCheckout(){
        elements().proceedToCheckoutButton().click();
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

    @Override
    protected void waitForPageLoad() {
        elements().couponCodeTextField().waitToExists();
    }

}
