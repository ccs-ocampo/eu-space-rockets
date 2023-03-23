package decorators.interface_segregation;

import decorators.Browser;

public interface BrowseService {
    void start(Browser browser);
    void quit();
    void waitForAjax();
    void waitUntilPageLoadsCompletely();
}
