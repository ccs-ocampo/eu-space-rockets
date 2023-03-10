package decorators;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LogElement extends ElementDecorator{
    protected LogElement(Element element) {
        super(element);
    }

    @Override
    public By getBy() {
        return element.getBy();
    }

    @Override
    public String getText() {
        System.out.printf("decorators.Element text = %s", element.getText());
        return element.getText();
    }

    @Override
    public Boolean isEnabled() {
        System.out.printf("decorators.Element Enabled = %b", element.isEnabled());
        return element.isEnabled();
    }

    @Override
    public Boolean isDisplayed() {
        System.out.printf("decorators.Element Displayed = %b", element.isDisplayed());
        return element.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        System.out.printf("Type Text = %s", text);
        element.typeText(text);
    }

    @Override
    public void click() {
        System.out.printf("decorators.Element Clicked");
        element.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        System.out.printf("decorators.Element Attribute = %s", attributeName);
        return element.getAttribute(attributeName);
    }

    @Override
    public void typeKey(Keys key) {
        System.out.println("Type Key");
        element.typeKey(key);
    }
}
