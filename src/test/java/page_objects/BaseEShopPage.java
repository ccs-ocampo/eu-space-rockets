package page_objects;

import decorators.Driver;

public abstract class BaseEShopPage {
    protected final Driver driver;

    public BaseEShopPage(Driver driver) {
        this.driver = driver;
    }

    public CartInfoSection cartInfoSection(){
        return new CartInfoSection(driver);
    }

    public MainMenuSection mainMenuSection(){
        return new MainMenuSection(driver);
    }

    public SearchSection searchSection(){
        return new SearchSection(driver);
    }

    protected abstract String getUrl();

    public void open(){
        driver.goToURL(getUrl());
        driver.waitUntilPageLoadsCompletely();
    }
}
