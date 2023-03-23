package page_objects;

import decorators.Driver;
import decorators.interface_segregation.ElementFindService;

public class SearchSection {
    private final ElementFindService elementFindService;

    public SearchSection(ElementFindService elementFindService) {
        this.elementFindService = elementFindService;
    }
}
