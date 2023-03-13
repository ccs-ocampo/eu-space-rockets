package observers;

import org.testng.ITestResult;

import java.lang.reflect.Method;

public interface TestBehaviorObserver {
    void preTestInit(ITestResult result, Method memberInfo);
    void postTestInit(ITestResult result, Method memberInfo);
    void preTestCleanup(ITestResult result, Method memberInfo);
    void postTestCleanup(ITestResult result, Method memberInfo);
    void testInstantiated(Method memberInfo);
}
