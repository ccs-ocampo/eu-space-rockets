package decorators;

import decorators.strategy.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WebCoreDriver extends Driver{
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @Override
    public void start(Browser browser) {
        webDriver = switch(browser){
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                yield new EdgeDriver();
            }
            case OPERA -> {
                WebDriverManager.operadriver().setup();
                yield new OperaDriver();
            }
            case SAFARI -> new SafariDriver();
            case INTERNET_EXPLORER -> {
                WebDriverManager.iedriver().setup();
                yield new InternetExplorerDriver();
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
        };

        webDriverWait = new WebDriverWait(webDriver, 30);
    }

    @Override
    public void quit() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Override
    public void goToURL(String url) {
        webDriver.navigate().to(url);
    }

    @Override
    public Element find(FindStrategy findStrategy) {
        var nativeWebElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(findStrategy.convert()));
        Element element = new WebCoreElement(webDriver,nativeWebElement,findStrategy.convert());

        return new LogElement(element);
    }

    @Override
    public Element findById(String id) {
        return find(new IdFindStrategy(id));
    }

    @Override
    public Element findByXPath(String xpath) {
        return find(new XpathFindStrategy(xpath));
    }

    @Override
    public Element findByTag(String tag) {
        return find(new TagFindStrategy(tag));
    }

    @Override
    public Element findByClass(String cssClass) {
        return find(new ClassFindStrategy(cssClass));
    }

    @Override
    public Element findByCss(String css) {
        return find(new CssFindStrategy(css));
    }

    @Override
    public Element findByLinkText(String linkText) {
        return find(new LinkTextFindStrategy(linkText));
    }

    @Override
    public List<Element> findAllById(String id) {
        return findAll(new IdFindStrategy(id));
    }

    @Override
    public List<Element> findAllByXPath(String xpath) {
        return findAll(new XpathFindStrategy(xpath));
    }

    @Override
    public List<Element> findAllByTag(String tag) {
        return findAll(new TagFindStrategy(tag));
    }

    @Override
    public List<Element> findAllByClass(String cssClass) {
        return findAll(new ClassFindStrategy(cssClass));
    }

    @Override
    public List<Element> findAllByCss(String css) {
        return findAll(new CssFindStrategy(css));
    }

    @Override
    public List<Element> findAllByLinkText(String linkText) {
        return findAll(new LinkTextFindStrategy(linkText));
    }

    @Override
    public Element findByName(String name) {
        return find(new NameFindStrategy(name));
    }

    @Override
    public List<Element> findAll(FindStrategy findStrategy) {
        List<WebElement> nativeWebElements =
                webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(findStrategy.convert()));
        var elements = new ArrayList<Element>();
        for(WebElement nativeWebElement: nativeWebElements){
            Element element = new WebCoreElement(webDriver, nativeWebElement, findStrategy.convert());
            elements.add(new LogElement(element));
        }
        return elements;
    }

    @Override
    public void waitForAjax() {
        var javascriptExecutor = (JavascriptExecutor) webDriver;
        webDriverWait.until(d -> (Boolean)javascriptExecutor.executeScript("return window.jQuery != undefined && jQuery.active == 0"));
    }

    @Override
    public void waitUntilPageLoadsCompletely() {
        var javascriptExecutor = (JavascriptExecutor) webDriver;
        webDriverWait.until(d -> javascriptExecutor.executeScript("return document.readyState")).toString().equals("complete");
    }

    @Override
    public void addCookie(Cookie cookie) {
        webDriver.manage().addCookie(cookie);
    }

    @Override
    public void deleteAllCookies(){
        webDriver.manage().deleteAllCookies();
    }

    @Override
    public void deleteCookie(String cookieName) {
        webDriver.manage().deleteCookieNamed(cookieName);
    }

    @Override
    public List<Cookie> getAllCookies() {
        return (List<Cookie>) webDriver.manage().getCookies();
    }

    @Override
    public String getCookie(String cookieName) {
        return webDriver.manage().getCookieNamed(cookieName).toString();
    }
}
