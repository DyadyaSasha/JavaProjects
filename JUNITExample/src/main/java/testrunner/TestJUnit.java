package testrunner;

import org.junit.Assert;
import org.junit.Test;

public class TestJUnit {

    @Test
    public void testAdd(){
        String str = "str";
        Assert.assertEquals("st", str);
        Assert.assertEquals("s", "s");
    }
}
