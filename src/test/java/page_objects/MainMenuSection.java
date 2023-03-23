package page_objects;

import decorators.Driver;
import decorators.Element;
import decorators.interface_segregation.ElementFindService;
import org.openqa.selenium.By;

public class MainMenuSection {
    private final ElementFindService elementFindService;

    public MainMenuSection(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }

    private Element myAccountLink() {
        return elementFindService.findElement(By.xpath("//ul[@class='nav-menu']//a[text()='My account']"));
    }

    public void openMyAccountPage(){
        myAccountLink().click();
    }
}
