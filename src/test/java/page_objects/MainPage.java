package page_objects;

import decorators.Driver;
import page_objects.page_elements.MainPageElements;

public class MainPage extends BaseEShopPage<MainPageElements> {
    private final String url = "http://demos.bellatrix.solutions/";

    public MainPage(Driver driver) {
        super(driver);
    }

    protected String getUrl(){
        return  url;
    }

    public void addRocketToShoppingCart(){
        driver.goToURL(url);
        driver.waitForAjax();
        elements().addFalcon9ToCartButton().click();
        elements().viewCartFalcon9Button().click();
    }

    @Override
    protected void waitForPageLoad() {
        elements().addFalcon9ToCartButton().waitToExists();
    }
}
