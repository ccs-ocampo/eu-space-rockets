package decorators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public Element findElement(By locator) {
        var nativeWebElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Element element = new WebCoreElement(webDriver,nativeWebElement,locator);

        return new LogElement(element);
    }

    @Override
    public List<Element> findElements(By locator) {
        List<WebElement> nativeWebElements =
                webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        var elements = new ArrayList<Element>();
        for(WebElement nativeWebElement: nativeWebElements){
            Element element = new WebCoreElement(webDriver, nativeWebElement, locator);
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
    public void deleteAllCookies(){
        webDriver.manage().deleteAllCookies();
    }
}
