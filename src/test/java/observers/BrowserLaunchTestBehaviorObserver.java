package observers;

import decorators.Driver;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.Optional;

public class BrowserLaunchTestBehaviorObserver extends BaseTestBehaviorObserver{
    private final Driver driver;
    private BrowserConfiguration currentBrowserConfiguration;
    private BrowserConfiguration previousBrowserConfiguration;

    public BrowserLaunchTestBehaviorObserver(TestExecutionSubject testExecutionSubject, Driver driver) {
        super(testExecutionSubject);
        this.driver = driver;
    }

    @Override
    public void preTestInit(ITestResult result, Method memberInfo) {
        currentBrowserConfiguration = getBrowserConfiguration(memberInfo);

        Boolean shouldRestartBrowser = ShouldRestartBrowser(currentBrowserConfiguration);

        if(shouldRestartBrowser){
            restartBrowser();
        }

        previousBrowserConfiguration = currentBrowserConfiguration;
    }

    @Override
    public void postTestCleanup(ITestResult testResult, Method memberInfo) {
        if(currentBrowserConfiguration.getBrowserBehavior() == BrowserBehavior.RESTART_ON_FAIL && testResult.getStatus() == ITestResult.FAILURE){
            restartBrowser();
        }

    }

    private void restartBrowser() {
        driver.quit();
        driver.start(currentBrowserConfiguration.getBrowser());
    }

    private Boolean ShouldRestartBrowser(BrowserConfiguration browserConfiguration) {
        if(previousBrowserConfiguration == null){
            return true;
        }

        Boolean shouldRestartBrowser = browserConfiguration.getBrowserBehavior() == BrowserBehavior.RESTART_EVERY_TIME || browserConfiguration.getBrowser() == BrowserBehavior.NOT_SET;

        return shouldRestartBrowser;
    }

    private BrowserConfiguration getBrowserConfiguration(Method memberInfo) {
        return Optional.ofNullable(getExecutionBrowserMethodLevel(memberInfo)).
                orElse(getExecutionBrowserClassLevel(memberInfo.getDeclaringClass()));
    }

    private BrowserConfiguration getExecutionBrowserMethodLevel(Method memberInfo) {
        var executionBrowserAnnotation = (ExecutionBrowser)memberInfo.getDeclaredAnnotations(ExecutionBrowser.class);
        if(executionBrowserAnnotation == null){
            return null;
        }

        return new BrowserConfiguration(executionBrowserAnnotation.browser(), executionBrowserAnnotation.browserBehavior());
    }

    private BrowserConfiguration getExecutionBrowserClassLevel(Class<?> type) {
        var executionBrowserAnnotation = (ExecutionBrowser)type.getDeclaredAnnotations(ExecutionBrowser.class);
        if(executionBrowserAnnotation == null) {
            return null;
        }

        return new BrowserConfiguration(executionBrowserAnnotation.browser(), executionBrowserAnnotation.browserBehavior());
    }
}
