package advanced;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizedTest1 {

    @Test
//  указываем параметры(уже определённые в testing2.xml), которые будут использоваться в тесте
    @Parameters("myNam")
    public void parameterTest(String myName){
        System.out.println("Parameterized value is : " + myName);
    }
}
