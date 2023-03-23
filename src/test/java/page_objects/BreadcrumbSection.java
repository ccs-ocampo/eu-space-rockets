package page_objects;

import decorators.Driver;
import decorators.interface_segregation.ElementFindService;

public class BreadcrumbSection {
    private final ElementFindService elementFindService;

    public BreadcrumbSection(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }
}
