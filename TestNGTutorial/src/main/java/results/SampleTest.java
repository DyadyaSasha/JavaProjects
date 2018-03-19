package results;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SampleTest {

    @Test
    public void testMethodOne(){
        assertTrue(true);
    }

    @Test
    public void testMethodTwo(){
        assertTrue(false);
    }

    @Test(dependsOnMethods = {"testMethodTwo"})
    public void testMethodThree(){
        assertTrue(true);
    }
}
