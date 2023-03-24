package page_objects;

import decorators.Driver;
import decorators.LogDriver;
import decorators.interface_segregation.BrowseService;
import decorators.interface_segregation.ElementFindService;
import decorators.interface_segregation.NavigationService;
import page_objects.page_elements.CartPageElements;
import page_objects.singleton_factory.SingletonFactory;

public class CartPage extends BaseEShopPage {
    private final BrowseService browseService = LogDriver.getInstance();

    private CartPage() {
    }

    public static CartPage getInstance(){
        return SingletonFactory.getInstance(CartPage.class);
    }

    protected String getUrl(){
        return  "http://demos.bellatrix.solutions/cart";
    }

    private CartPageElements elements(){
        return new CartPageElements(elementFindService);
    }

    public void applyCoupon(String coupon) {
        elements().couponCodeTextField().typeText(coupon);
        elements().applyCouponButton().click();
        browseService.waitForAjax();
    }

    public void increaseProductQuantity(String productQuantity) {
        elements().quantityInput().typeText(productQuantity);
        browseService.waitForAjax();
        elements().updateCartButton().click();
        browseService.waitForAjax();
    }

    public String getCartTotal(){
        return elements().cartTotal().getText();
    }

    public void clickProceedToCheckout(){
        elements().proceedToCheckoutButton().click();
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(elementFindService);
    }

    @Override
    protected void waitForPageLoad() {
        elements().couponCodeTextField().waitToExists();
    }

}
