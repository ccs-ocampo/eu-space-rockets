package page_objects;

import decorators.Driver;
import decorators.Element;
import org.openqa.selenium.By;

public class MainMenuSection {
    private final Driver driver;

    public MainMenuSection(Driver driver) {
        this.driver = driver;
    }

    private Element myAccountLink() {
        return driver.findElement(By.linkText("My Account"));
    }

    public void openMyAccountPage(){
        myAccountLink().click();
    }
}
