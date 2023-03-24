package decorators;

import org.openqa.selenium.By;
import page_objects.singleton_factory.SingletonFactory;

import java.util.List;

public class LogDriver extends DriverDecorator {
    private static LogDriver instance;
    private LogDriver(Driver driver) {
        super(driver);
    }

    public static LogDriver getInstance(){
        if(instance == null){
            instance = new LogDriver(new WebCoreDriver());
        }
        return instance;
    }

    @Override
    public void start(Browser browser) {
        System.out.printf("Start browser = %s", browser);
        driver.start(browser);
    }

    @Override
    public void quit() {
        System.out.print("Close browser");
        driver.quit();
    }

    @Override
    public void goToURL(String url) {
        System.out.printf("Go to url = %s", url);
        driver.goToURL(url);
    }

    @Override
    public Element findElement(By locator) {
        System.out.print("Find element");
        return driver.findElement(locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        System.out.print("Find elements");
        return driver.findElements(locator);
    }

    @Override
    public void waitForAjax() {
        System.out.print("Wait for AJAX");
        driver.waitForAjax();
    }

    @Override
    public void waitUntilPageLoadsCompletely() {
        System.out.print("Wait for page to load completely");
        driver.waitUntilPageLoadsCompletely();
    }
}
