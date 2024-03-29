package page_objects;

import decorators.Driver;
import decorators.interface_segregation.BrowseService;
import decorators.interface_segregation.ElementFindService;
import decorators.interface_segregation.NavigationService;
import page_objects.page_elements.CartPageElements;

public class CartPage extends BaseEShopPage {
    private final BrowseService browseService;

    public CartPage(NavigationService navigationService, ElementFindService elementFindService, BrowseService browseService) {
        super(navigationService, elementFindService);
        this.browseService = browseService;
    }

    protected String getUrl(){
        return  "http://demos.bellatrix.solutions/cart";
    }

    private CartPageElements elements(){
        return new CartPageElements(elementFindService);
    }

    public CartPage applyCoupon(String coupon) {
        elements().couponCodeTextField().typeText(coupon);
        elements().applyCouponButton().click();
        browseService.waitForAjax();
        return this;
    }

    public CartPage increaseProductQuantity(String productQuantity) {
        elements().quantityInput().typeText(productQuantity);
        browseService.waitForAjax();
        elements().updateCartButton().click();
        browseService.waitForAjax();
        return this;
    }

    public String getCartTotal(){
        return elements().cartTotal().getText();
    }

    public CartPage clickProceedToCheckout(){
        elements().proceedToCheckoutButton().click();
        return this;
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(elementFindService);
    }

    @Override
    protected void waitForPageLoad() {
        elements().couponCodeTextField().waitToExists();
    }

}
