package decorators;

import decorators.find_strategy.FindStrategy;

import java.util.List;

public class LogDriver extends DriverDecorator {
    public LogDriver(Driver driver) {
        super(driver);
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
    public Element find(FindStrategy findStrategy) {
        System.out.print("Find element");
        return driver.find(findStrategy);
    }

    @Override
    public List<Element> findAll(FindStrategy findStrategy) {
        System.out.print("Find elements");
        return driver.findAll(findStrategy);
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
