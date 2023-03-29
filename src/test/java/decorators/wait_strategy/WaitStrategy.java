package decorators.wait_strategy;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

public abstract class WaitStrategy {
    private final int timeoutIntervalSeconds;
    private final int sleepIntervalSeconds;


    public WaitStrategy(int timeoutIntervalSeconds, int sleepIntervalSeconds) {
        this.timeoutIntervalSeconds = timeoutIntervalSeconds;
        this.sleepIntervalSeconds = sleepIntervalSeconds;
    }

    public int getTimeoutIntervalSeconds() {
        return timeoutIntervalSeconds;
    }

    public int getSleepIntervalSeconds() {
        return sleepIntervalSeconds;
    }

    public abstract void waitUntil(SearchContext searchContext, WebDriver driver, By by);

    protected void waitUntil(Function<SearchContext, Boolean> waitCondition, WebDriver driver){
        var webDriverWait = new WebDriverWait(driver, timeoutIntervalSeconds, sleepIntervalSeconds);
        webDriverWait.until(waitCondition);
    }
    protected WebElement findElement(SearchContext searchContext, By by){
        var element = searchContext.findElement(by);
        return element;
    }
}
