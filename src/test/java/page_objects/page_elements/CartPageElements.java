package page_objects.page_elements;

import decorators.Driver;
import decorators.Element;
import org.openqa.selenium.By;

public class CartPageElements extends BaseElements{
    public CartPageElements(Driver driver) {
        super(driver);
    }

    public Element couponCodeTextField(){
        return  driver.findElement(By.id("coupon_code"));
    }

    public Element applyCouponButton(){
        return driver.findElement(By.name("apply_coupon"));
    }

    public Element quantityInput(){
        return driver.findElement(By.cssSelector("input[title='Qty']"));
    }

    public Element updateCartButton(){
        return driver.findElement(By.cssSelector("[value*='Update cart']"));
    }

    public Element cartTotal(){
        return driver.findElement(By.xpath("//td[@data-title='Total']//bdi"));
    }

    public Element proceedToCheckoutButton(){
        return driver.findElement(By.cssSelector(".checkout-button"));
    }
}
