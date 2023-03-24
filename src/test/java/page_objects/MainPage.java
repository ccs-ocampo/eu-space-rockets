package page_objects;

import decorators.LogDriver;
import decorators.interface_segregation.BrowseService;
import page_objects.page_elements.MainPageElements;
import page_objects.singleton_factory.SingletonFactory;

public class MainPage extends BaseEShopPage {
    private final BrowseService browseService = LogDriver.getInstance();
    private final String url = "http://demos.bellatrix.solutions/";

    private MainPage() {
    }

    public static MainPage getInstance(){
        return SingletonFactory.getInstance(MainPage.class);
    }

    protected String getUrl(){
        return  url;
    }

    private MainPageElements elements(){
        return new MainPageElements(elementFindService);
    }

    public void addRocketToShoppingCart(){
        navigationService.goToURL(url);
        browseService.waitForAjax();
        elements().addFalcon9ToCartButton().click();
        elements().viewCartFalcon9Button().click();
    }

    @Override
    protected void waitForPageLoad() {
        elements().addFalcon9ToCartButton().waitToExists();
    }
}
