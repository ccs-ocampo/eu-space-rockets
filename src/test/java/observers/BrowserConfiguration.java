package observers;

import decorators.Browser;

public class BrowserConfiguration {
    private Browser browser;
    private BrowserBehavior browserBehavior;

    public BrowserConfiguration(Browser browser, BrowserBehavior browserBehavior) {
        this.browser = browser;
        this.browserBehavior = browserBehavior;
    }

    public BrowserBehavior getBrowserBehavior() {
        return browserBehavior;
    }

    public Browser getBrowser() {
        return browser;
    }

}
