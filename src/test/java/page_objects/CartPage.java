package page_objects;

import decorators.Driver;
import decorators.Element;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BaseEShopPage {
    private final String URL = "http://demos.bellatrix.solutions/";

    public CartPage(Driver driver) {
        super(driver);
    }

    protected String getUrl(){
        return  "http://demos.bellatrix.solutions/cart";
    }

    private Element couponCodeTextField(){
        return  driver.findElement(By.id("coupon_code"));
    }

    private  Element applyCouponButton(){
        return driver.findElement(By.name("apply_coupon"));
    }

    private Element quantityInput(){
        return driver.findElement(By.cssSelector("input[title='Qty']"));
    }

    private Element updateCartButton(){
        return driver.findElement(By.cssSelector("[value*='Update cart']"));
    }

    private Element cartTotal(){
        return driver.findElement(By.xpath("//td[@data-title='Total']//bdi"));
    }

    private Element proceedToCheckoutButton(){
        return driver.findElement(By.cssSelector(".checkout-button"));
    }

    public void applyCoupon(String coupon) {
        couponCodeTextField().typeText(coupon);
        applyCouponButton().click();
        driver.waitForAjax();
    }

    private void increaseProductQuantity (String productQuantity) {
        quantityInput().typeText(productQuantity);
        driver.waitForAjax();
        updateCartButton().click();
        driver.waitForAjax();
    }

    public String getCartTotal(){
        return cartTotal().getText();
    }

    public void clickProceedToCheckout(){
        proceedToCheckoutButton().click();
    }

    public BreadcrumbSection breadcrumbSection() {
        return new BreadcrumbSection(driver);
    }

}
