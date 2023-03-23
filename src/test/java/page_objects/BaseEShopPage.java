package page_objects;

import decorators.interface_segregation.ElementFindService;
import decorators.interface_segregation.NavigationService;

public abstract class BaseEShopPage {
    protected final NavigationService navigationService;
    protected final ElementFindService elementFindService;

    public BaseEShopPage(NavigationService navigationService, ElementFindService elementFindService) {
        this.navigationService = navigationService;
        this.elementFindService = elementFindService;
    }

    public CartInfoSection cartInfoSection(){
        return new CartInfoSection(elementFindService);
    }

    public MainMenuSection mainMenuSection(){
        return new MainMenuSection(elementFindService);
    }

    public SearchSection searchSection(){
        return new SearchSection(elementFindService);
    }

    protected abstract String getUrl();

    public void open(){
        navigationService.goToURL(getUrl());
        waitForPageLoad();
    }

    protected abstract void waitForPageLoad();
}
