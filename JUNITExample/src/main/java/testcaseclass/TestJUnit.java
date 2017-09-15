package testcaseclass;

import junit.framework.TestCase;
import org.junit.Assert;

import org.junit.Test;

/**
 * если класс наследуется от {@link TestCase}, то в нём нельзя использовать
 * аннотации {@link org.junit.Before} и {@link org.junit.After}
 * для этого есть методы класса {@link TestCase} setUp() и tearDown()
 * */
public class TestJUnit extends TestCase{

    int a, b;


    public void setUp(){
        System.out.println("setup");
        a = 2;
        b = 3;
    }


    @Test
    public void testAdd(){

        System.out.println("Count of test cases: " + this.countTestCases()); // выведет 1

        System.out.println("Test case name: " + this.getName());

        this.setName("NewCaseName");

        System.out.println("Updated case name: " + this.getName());

        Assert.assertNotEquals(a, b);
    }

    /**
     * цепочка выполнения тестов завершается, если нашёлся неудачный тест
     * */
    @Test
    public void test(){
        System.out.println("Count of test cases: " + this.countTestCases()); // выведет тоже 1
        Assert.assertEquals(a, b);
    }




    public void tearDown(){
        System.out.println("teardown");
    }

}
