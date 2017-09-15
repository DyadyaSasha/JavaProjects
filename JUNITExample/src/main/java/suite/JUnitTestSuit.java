package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * suite собирает разные тесты(case) и выполняет их сразу
 * аннотация @RunWith
 * */
@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestJunit1.class, TestJunit2.class
})

public class JUnitTestSuit {
}
