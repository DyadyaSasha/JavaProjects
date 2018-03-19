package group;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class GroupTestExample {

    String message = ".com";
    MessageUtil messageUtil = new MessageUtil(message);

//  указываем принадлежность теста к группе/группам(параметр groups)
    @Test(groups = {"functest", "checkintest"})
    public void testPrintMessage(){
        System.out.println("Inside testPrintMessage()");
        message = ".com";
        assertEquals(messageUtil.printMessage(), message);
    }
//  параметр dependsOnMethods указывает на метод теста, который должен выполниться до данного метода теста
    @Test(groups = {"checkintest"}, dependsOnMethods = {"testPrintMessage"})
    public void testSalutationMessage(){
        System.out.println("Inside testSalutationMessage()");
        message = "tutorialspoint.com";
        assertEquals(messageUtil.salutationMessage(), message);
    }

//  параметр аннотации expectedExceptions - указываем исключение, которое ожидается в этом тесте
    @Test(groups = {"functest"}, dependsOnMethods = {"testSalutationMessage"})
    public void testExitMessage(){
        System.out.println("Inside testExitMessage()");
        message = "www.tutorialspoint.com";
        assertEquals(messageUtil.exitMessage(), message);
    }


}
