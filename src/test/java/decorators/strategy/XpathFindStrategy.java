package decorators.strategy;

import org.openqa.selenium.By;

public class XpathFindStrategy extends FindStrategy{
    public XpathFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.xpath(getValue());
    }
}
