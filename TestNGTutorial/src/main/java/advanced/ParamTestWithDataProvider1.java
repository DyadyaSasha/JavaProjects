package advanced;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ParamTestWithDataProvider1 {

    private PrimeNumberChecker primeNumberChecker;

    @BeforeMethod
    public void init(){
        primeNumberChecker = new PrimeNumberChecker();
    }

//  помечаем метод, который будет выдавать коллекцию параметров, которая будет использована в тесте - это поставщик данных для теста
    @DataProvider(name = "provider")
    public Object[][] primeNumbers(){
        return new Object[][] {{2, true}, {6, false}, {19, true}, {22, false}, {23, true}};
    }

//  указываем имя поставщика данных
    @Test(dataProvider = "provider")
    public void testPrimeNumberChecker(Integer inputNumber, Boolean expectedResult){
        System.out.println(inputNumber + " " + expectedResult);
        assertEquals(primeNumberChecker.validate(inputNumber), expectedResult);
    }
}
