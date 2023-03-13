package decorators;

import org.openqa.selenium.By;

import java.util.List;

public abstract class Driver {
    public abstract void start(Browser browser);
    public abstract void quit();
    public abstract void goToURL(String url);
    public abstract Element findElement(By locator);
    public abstract List<Element> findElements(By locator);
    public abstract void waitForAjax();
    public abstract void waitUntilPageLoadsCompletely();
    public abstract void deleteAllCookies();
}
