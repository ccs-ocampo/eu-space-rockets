package page_objects;

import decorators.interface_segregation.BrowseService;
import decorators.interface_segregation.ElementFindService;
import decorators.interface_segregation.NavigationService;
import page_objects.page_elements.MainPageElements;

public class MainPage extends BaseEShopPage {
    private final BrowseService browseService;
    private final String url = "http://demos.bellatrix.solutions/";

    public MainPage(NavigationService navigationService, ElementFindService elementFindService, BrowseService browseService) {
        super(navigationService, elementFindService);
        this.browseService = browseService;
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
