package suite;

import org.junit.Assert;
import org.junit.Test;


public class TestJunit1 {

    String message = "Robert";

    @Test
    public void test(){
        System.out.println("TestJunit1");
        Assert.assertEquals("Robert", message);

    }

}
