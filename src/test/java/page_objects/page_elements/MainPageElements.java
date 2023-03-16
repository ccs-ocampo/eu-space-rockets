package page_objects.page_elements;

import decorators.Driver;
import decorators.Element;
import org.openqa.selenium.By;

public class MainPageElements extends BaseElements {
    public MainPageElements(Driver driver) {
        super(driver);
    }
    public Element addFalcon9ToCartButton(){
        return driver.findElement(By.cssSelector("[data-product_id*='28']"));
    }

    public Element viewCartFalcon9Button(){
        return driver.findElement(By.cssSelector(".post-28>a[title]"));
    }

}
