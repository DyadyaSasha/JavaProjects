package testsuiteclass;

import junit.framework.TestResult;
import junit.framework.TestSuite;
import suite.TestJunit1;
import suite.TestJunit2;

public class TestSuiteJunit {

    public static void main(String[] args){
        TestSuite suite = new TestSuite(TestJunit1.class, TestJunit2.class);
        TestResult result = new TestResult();
        suite.run(result);
        System.out.println("number of test cases: " + result.runCount());

    }
}
