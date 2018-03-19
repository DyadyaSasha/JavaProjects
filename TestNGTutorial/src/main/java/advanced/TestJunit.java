package advanced;

import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TestJunit {

//  аннотация JUnit, которую принимает TestNG
    @Test
    public void test(){
        String str = "Junit testing using TestNG";
        assertEquals("Junit testing using TestNG",str);
    }

}
