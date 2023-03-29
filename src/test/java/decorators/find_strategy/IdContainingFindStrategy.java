package decorators.find_strategy;

import org.openqa.selenium.By;

public class IdContainingFindStrategy extends FindStrategy{
    protected IdContainingFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[id*='%s']", getValue()));
    }
}
