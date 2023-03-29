package decorators.find_strategy;

import org.openqa.selenium.By;

public class NameFindStrategy extends FindStrategy{
    public NameFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.name(getValue());
    }
}
