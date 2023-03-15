package tests;

import decorators.Driver;
import decorators.LogDriver;
import decorators.WebCoreDriver;
import observers.BrowserLaunchTestBehaviorObserver;
import observers.ExecutionSubject;
import observers.TestExecutionSubject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private static final ThreadLocal<TestExecutionSubject> executionSubject;
    private static final ThreadLocal<Driver> driver;
    private ITestResult result;

    static {
        executionSubject = new ThreadLocal<>();
        executionSubject.set(new ExecutionSubject());
        driver = new ThreadLocal<>();
        driver.set(new LogDriver(new WebCoreDriver()));
        new BrowserLaunchTestBehaviorObserver(executionSubject.get(), driver.get());
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
        return driver.get();
    }
    
    @AfterSuite
    public void afterSuite(){
        if(driver.get() != null){
            driver.get().quit();
        }
    }
    
    @BeforeMethod
    public void beforeMethod(ITestResult result) throws NoSuchMethodException {
        setTestResult(result);
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.get().preTestInit(getTestResult(), methodInfo);
        testInit();
        executionSubject.get().postTestInit(getTestResult(), methodInfo);
    }
    
    @AfterMethod
    public void afterMethod() throws NoSuchMethodException {
        var testClass = this.getClass();
        var methodInfo = testClass.getMethod(getTestResult().getMethod().getMethodName());
        executionSubject.get().preTestCleanup(getTestResult(), methodInfo);
        testCleanup();
        executionSubject.get().postTestCleanup(getTestResult(), methodInfo);
    }

    private void testCleanup() {
    }

    private void testInit() {
    }

}

