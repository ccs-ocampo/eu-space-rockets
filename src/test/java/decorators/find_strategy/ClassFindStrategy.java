package decorators.find_strategy;

import org.openqa.selenium.By;

public class ClassFindStrategy extends FindStrategy{
    public ClassFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.className(getValue());
    }
}
