package decorators;

import decorators.interface_segregation.*;
import org.openqa.selenium.By;

import java.util.List;

public abstract class Driver implements BrowseService, NavigationService, ElementFindService, CookiesService {

}
