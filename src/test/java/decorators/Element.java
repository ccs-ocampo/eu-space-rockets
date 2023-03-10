package decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public abstract class Element {
    public abstract By getBy();
    public abstract String getText();
    public abstract Boolean isEnabled();
    public abstract Boolean isDisplayed();
    public abstract void typeText(String text);
    public abstract void typeKey(Keys key);
    public abstract void click();
    public abstract String getAttribute(String attributeName);

}
