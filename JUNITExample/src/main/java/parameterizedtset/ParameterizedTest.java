package parameterizedtset;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest {

    private Integer input;
    private Boolean expectedResult;
    private TestingClass testingClass;

    @Before
    public void initialize(){
        testingClass = new TestingClass();
    }

    public ParameterizedTest(Integer input, Boolean expectedResult){
        this.input = input;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers(){
        return Arrays.asList(new Object[][]{
            { 2, true },
            { 6, false },
            { 19, true },
            { 22, false },
            { 23, true }
        });
    }

    @Test
    public void testPrime(){
        System.out.println("Parameterized Number is : " + input);
        Assert.assertEquals(expectedResult,
                testingClass.validate(input));
    }
}
