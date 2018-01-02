package mathapp;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

//указываем какой раннер использовать(раннер запускает и выполняет тесты, помеченные соответствующей аннотацией)
@RunWith(EasyMockRunner.class)
public class MathApplicationTester {

//  помечаем данной аннотацией класс, в котором будет использоваться заглушка
    @TestSubject
    MathApplication mathApplication = new MathApplication();
//  создаём объект заглушки
    @Mock
    CalculatorService calculatorService;

    @Test
    public void testAdd(){
        EasyMock.expect(calculatorService.add(10.0,20.0)).andReturn(30.00);
//      проверка на кол-во вызовов методов заглушки(должно быть до вызова метода replay()) - данная проверка дожна проводиться единожды!!!
        EasyMock.expectLastCall().times(1);
//      активируем заглушку(далее её можно использовать в тестировании), также можно использовать EasyMock.replayAll()
        EasyMock.replay(calculatorService);
        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
//      проверка будет ли вообще использован объект заглушки(какой-то из его методов), если нет, то ошибка. Также можно использовать EasyMock.verifyAll()
        EasyMock.verify(calculatorService);

    }
}
