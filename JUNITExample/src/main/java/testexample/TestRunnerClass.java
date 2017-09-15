package testexample;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * если в тестируемом методе есть консольный вывод,
 * то он выведется в консоль
 * */
public class TestRunnerClass {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(TestCaseClass.class);

        for (Failure failure : result.getFailures()){
            System.out.println(failure.toString());
            System.out.println(failure.getMessage());
        }

        System.out.println(result.wasSuccessful());
    }
}