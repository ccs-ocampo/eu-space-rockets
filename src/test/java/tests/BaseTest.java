package tests;

import decorators.Driver;
import decorators.WebCoreDriver;
import observers.BrowserLaunchTestBehaviorObserver;
import observers.ExecutionSubject;
import observers.TestExecutionSubject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static final TestExecutionSubject executionSubject;
    private static final Driver driver;
    private ITestResult result;

    static {
        executionSubject = new ExecutionSubject();
        driver = new LoggingDriver(new WebCoreDriver());
        new BrowserLaunchTestBehaviorObserver(executionSubject, driver);
    }
    
    public String getTestName(){
        return getTestResult().getTestName();
    }
    
    public void setTestResult(ITestResult result){
        this.result = result;
    }
    
    public ITestResult getTestResult(){
        return result;
    }
    
    public Driver getDriver(){
        return driver;
    }
    
    @AfterSuite
    public void afterSuite(){
        if(drive != null){
            driver.quit();
        }
    }
    
    @BeforeMethod
    public void beforeMethod(ITestResult result){
        setTestResult(result);
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.preTestInit(getTestResult(), methodInfo);
        testInit();
        executionSubject.postTestInit(getTestResult(), methodInfo);
    }
    
    @AfterMethod
    public void afterMethod(){
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.preTestCleanup(getTestResult(), methodInfo);
        testCleanup();
        executionSubject.postTestCleanup(getTestResult(), methodInfo);
    }

    private void testCleanup() {
    }

    private void testInit() {
    }

}

