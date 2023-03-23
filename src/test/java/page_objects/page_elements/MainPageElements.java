package page_objects.page_elements;

import decorators.Element;
import decorators.interface_segregation.ElementFindService;
import org.openqa.selenium.By;

public class MainPageElements {
    private final ElementFindService elementFindService;

    public MainPageElements(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }
    public Element addFalcon9ToCartButton(){
        return elementFindService.findElement(By.cssSelector("[data-product_id*='28']"));
    }

    public Element viewCartFalcon9Button(){
        return elementFindService.findElement(By.cssSelector(".post-28>a[title]"));
    }

}
