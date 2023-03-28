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
        return  elementFindService.findById("coupon_code");
    }

    public Element applyCouponButton(){
        return elementFindService.findByName("apply_coupon");
    }

    public Element quantityInput(){
        return elementFindService.findByCss("input[title='Qty']");
    }

    public Element updateCartButton(){
        return elementFindService.findByCss("[value*='Update cart']");
    }

    public Element cartTotal(){
        return elementFindService.findByXPath("//td[@data-title='Total']//bdi");
    }

    public Element proceedToCheckoutButton(){
        return elementFindService.findByCss(".checkout-button");
    }
}
