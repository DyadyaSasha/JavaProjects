package suite;

import org.junit.Assert;
import org.junit.Test;

public class TestJunit2 {

    String message = "message";

    @Test
    public void test(){
        System.out.println("TestJunit2");
        Assert.assertEquals("message", message);
    }
}
