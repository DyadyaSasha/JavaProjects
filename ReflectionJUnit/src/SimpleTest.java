import org.junit.*;
import org.junit.runners.model.TestTimedOutException;

/**
 * Created by user on 13.05.17.
 */
public class SimpleTest {

    @BeforeClass
    public static void start(){
        System.out.println("Before Class");
    }

    @Before
    public void beforeSingle(){
        System.out.println("Before");
    }

    @Test
    public void test1(){
        System.out.println("Test #1 Passed");
    }

    @Test(expected = NullPointerException.class)
    public void test2(){
        System.out.println("Test #2 Passed");
        throw new NullPointerException();
    }

    @Test(timeout = 5000)
    public void test4 () {
        while (true) ;
    }

    @After
    public void afterSingle(){
        System.out.println("End");
    }

    @AfterClass
    public static void complete(){
        System.out.println("Completed Simple Test");
    }
}
