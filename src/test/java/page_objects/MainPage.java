package page_objects;

import decorators.Driver;
import decorators.Element;
import org.openqa.selenium.By;

public class MainPage extends BaseEShopPage{
    private final String url = "http://demos.bellatrix.solutions/";

    public MainPage(Driver driver) {
        super(driver);
    }

    private Element addFalcon9ToCartButton(){
        return driver.findElement(By.cssSelector("[data-product_id*='28']"));
    }

    private Element viewCartFalcon9Button(){
        return driver.findElement(By.cssSelector(".post-28>a[title]"));
    }

    private void addRocketToShoppingCart(){
        driver.goToURL(url);
        driver.waitForAjax();
        addFalcon9ToCartButton().click();
        viewCartFalcon9Button().click();
    }
}
