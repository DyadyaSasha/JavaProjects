package fixture;

import junit.framework.TestCase;
/**
 * в классе TestCase содержаться методы
 * для подготовки и очистки данных, используемых в тестах(case)
 * */
public class JavaTestFixture extends TestCase {
    protected  int a, b;

    /**
     * запускается до вызова каждого теста
     * */
    protected void setUp(){
        System.out.println("set values");
        a = 3;
        b = 3;
    }

    public void testAdd(){
        double result = a + b;
        assertTrue(result == 6);
    }

    public void testEqual(){
        assertTrue(a == b);
    }

    /**
     * запускается после вызова каждого теста
     * */
    protected void tearDown(){
        System.out.println("clear values");
        a = 0;
        b = 0;
    }
}
