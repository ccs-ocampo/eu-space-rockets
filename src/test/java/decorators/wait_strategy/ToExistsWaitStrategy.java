package decorators.wait_strategy;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class ToExistsWaitStrategy extends WaitStrategy{
    public ToExistsWaitStrategy(int timeoutIntervalSeconds, int sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    @Override
    public void waitUntil(SearchContext searchContext, WebDriver driver, By by) {
        waitUntil((x) -> elementExists(searchContext, by), driver);
    }

    private Boolean elementExists(SearchContext searchContext, By by) {
        try {
            var element = findElement(searchContext, by);
            return element != null;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
