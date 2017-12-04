package junitintegration;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(MathApplicationTester.class);

        result.getFailures().forEach(System.out::println);

        System.out.println(result.wasSuccessful());
    }
}
