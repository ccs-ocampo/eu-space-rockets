import org.openqa.selenium.By;

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
        System.out.printf("Element text = %s", element.getText());
        return element.getText();
    }

    @Override
    public Boolean isEnabled() {
        System.out.printf("Element Enabled = %b", element.isEnabled());
        return element.isEnabled();
    }

    @Override
    public Boolean isDisplayed() {
        System.out.printf("Element Displayed = %b", element.isDisplayed());
        return super.isDisplayed();
    }

    @Override
    public void typeText(String text) {
        System.out.printf("Type Text = %s", text);
        super.typeText(text);
    }

    @Override
    public void click() {
        System.out.printf("Element Clicked");
        super.click();
    }

    @Override
    public String getAttribute(String attributeName) {
        System.out.printf("Element Attribute = %s", attributeName);
        return super.getAttribute(attributeName);
    }
}
