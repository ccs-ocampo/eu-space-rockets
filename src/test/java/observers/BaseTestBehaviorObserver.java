package observers;

import org.testng.ITestResult;

import java.lang.reflect.Method;

public class BaseTestBehaviorObserver implements TestBehaviorObserver{
    public BaseTestBehaviorObserver(TestExecutionSubject testExecutionSubject){
        testExecutionSubject.attach(this);
    }

    @Override
    public void preTestInit(ITestResult result, Method memberInfo) {

    }

    @Override
    public void postTestInit(ITestResult result, Method memberInfo) {

    }

    @Override
    public void preTestCleanup(ITestResult result, Method memberInfo) {

    }

    @Override
    public void postTestCleanup(ITestResult result, Method memberInfo) {

    }

    @Override
    public void testInstantiated(Method memberInfo) {

    }
}
