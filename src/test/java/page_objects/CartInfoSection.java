package page_objects;

import decorators.interface_segregation.ElementFindService;

public class CartInfoSection {
    private final ElementFindService elementFindService;
    public CartInfoSection(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }
}
