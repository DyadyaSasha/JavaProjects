package testexample;

import org.junit.Assert;
import org.junit.Test;

public class TestCaseClass {

    private String message = "message";
    private ClassToTest classToTest = new ClassToTest(message);

    @Test
    public void testPrintMessage(){
        Assert.assertEquals(message, classToTest.printMessage());
    }

    @Test
    public void testPrintMessageFail(){
        message = "FUCK";
        Assert.assertEquals(message, classToTest.printMessage());
    }

}
