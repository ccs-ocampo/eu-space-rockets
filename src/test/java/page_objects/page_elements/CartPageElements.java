package page_objects.page_elements;

import decorators.Element;
import decorators.interface_segregation.ElementFindService;
import org.openqa.selenium.By;

public class CartPageElements {
    private final ElementFindService elementFindService;

    public CartPageElements(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }

    public Element couponCodeTextField(){
        return  elementFindService.findElement(By.id("coupon_code"));
    }

    public Element applyCouponButton(){
        return elementFindService.findElement(By.name("apply_coupon"));
    }

    public Element quantityInput(){
        return elementFindService.findElement(By.cssSelector("input[title='Qty']"));
    }

    public Element updateCartButton(){
        return elementFindService.findElement(By.cssSelector("[value*='Update cart']"));
    }

    public Element cartTotal(){
        return elementFindService.findElement(By.xpath("//td[@data-title='Total']//bdi"));
    }

    public Element proceedToCheckoutButton(){
        return elementFindService.findElement(By.cssSelector(".checkout-button"));
    }
}
