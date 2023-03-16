package page_objects;

import decorators.Driver;
import page_objects.page_elements.BaseElements;
import page_objects.reflection.NewInstanceFactory;


public abstract class BaseEShopPage<ElementsT extends BaseElements> {
    public final Driver driver;

    public BaseEShopPage(Driver driver) {
        this.driver = driver;
    }

    protected ElementsT elements(){
        return NewInstanceFactory.<ElementsT>createByTypeParameter(getClass(), 0, driver);
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
        waitForPageLoad();
    }

    protected abstract void waitForPageLoad();
}
