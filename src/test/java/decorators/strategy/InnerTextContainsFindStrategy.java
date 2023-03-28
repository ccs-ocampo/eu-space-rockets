package decorators.strategy;

import org.openqa.selenium.By;

public class InnerTextContainsFindStrategy extends FindStrategy {
    protected InnerTextContainsFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.xpath(String.format("//*[contains(text(), '%s']", getValue()));
    }
}
