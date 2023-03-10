package decorators;

import org.openqa.selenium.By;

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
    public Element findElement(By locator) {
        System.out.print("Find element");
        return driver.findElement(locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        System.out.print("Find elements");
        return driver.findElements(locator);
    }
}
