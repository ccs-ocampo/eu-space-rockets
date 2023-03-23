package decorators;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.function.Function;

public class DriverDecorator extends Driver {
    protected final Driver driver;

    public DriverDecorator(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void start(Browser browser) {
        driver.start(browser);
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public void goToURL(String url) {
        driver.goToURL(url);
    }

    @Override
    public Element findElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public List<Element> findElements(By locator) {
        return driver.findElements(locator);
    }

    @Override
    public void waitForAjax() {
        driver.waitForAjax();
    }

    @Override
    public void waitUntilPageLoadsCompletely() {
        driver.waitUntilPageLoadsCompletely();
    }

    @Override
    public void addCookie(Cookie cookie) {
        driver.addCookie(cookie);
    }

    @Override
    public void deleteAllCookies() {
        driver.deleteAllCookies();
    }

    @Override
    public void deleteCookie(String cookieName) {
        driver.deleteCookie(cookieName);
    }

    @Override
    public List<Cookie> getAllCookies() {
        return driver.getAllCookies();
    }

    @Override
    public String getCookie(String cookieName) {
        return driver.getCookie(cookieName);
    }
}
