package testresultclass;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;
import org.junit.Assert;

/**
 * класс TestResult позволяет выполнить тест, а также
 * аккумулирует результаты теста
 * */
public class TestJunit extends TestResult {


    @Override
    public synchronized void addError(Test test, Throwable e) {
        super.addError(test, e);
    }

    @Override
    public synchronized void addFailure(Test test, AssertionFailedError e) {
        super.addFailure(test, e);
    }

    @org.junit.Test
    public void testAdd(){
        Assert.assertNotEquals(2, 4);
    }

    @Override
    public synchronized void stop() {
        System.out.println("test stop");
        super.stop();
    }
}
