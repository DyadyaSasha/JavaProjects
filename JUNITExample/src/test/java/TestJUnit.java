import org.junit.Assert;
import org.junit.Test;
public class TestJUnit {

    @Test
    public void testAdd(){
        String str = "str";
        Assert.assertEquals("str", str);
    }
}
