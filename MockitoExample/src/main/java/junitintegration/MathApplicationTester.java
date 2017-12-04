package junitintegration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Тесты запускают runner-ы.
 * Они - ответственные за то, как запускать наши тесты.
 * С помощью аннотации @RunWith можно указать Junit, какой раннер использовать.
 * Если Вы хотите каким-то образом по своему запускать тесты - Вы можете самостоятельно реализовать
 * свой runner и указать его в качестве параметра value для аннотации @RunWith.
 * В данном случае указывается runner? позволяющий JUnit работать вместе с Mockito
 */
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {

    /**
     * Mockito попытается внедрить заглушку в данный объект, путём попытки
     * вставить заглушку в конструктор данного класса, сеттер или напрямую по полю
     * Если соответсвие не найдено Mockito не сообщит об ошибке
     * подробнее: http://static.javadoc.io/org.mockito/mockito-core/2.12.0/org/mockito/InjectMocks.html
     */
    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    /**
     * Создание заглушки, вместо метода mock() можно использовать аннотацию
     */
    @Mock
    CalculatorService calcService;

    @Test(expected = RuntimeException.class)
    public void testAdd(){

        /**
         * описываем поведение методов заглушки, что при конкретных аргументах выводить
         */
        when(calcService.add(10.0, 20.0)).thenReturn(30.0);
        when(calcService.substract(20.0, 10.0)).thenReturn(10.0);

        /**
         * при вызове метода с такими параметрами вылетит исключение
         */
        doThrow(new RuntimeException("devide operation not support")).when(calcService).add(90.0, 50.0);

        /**
         * thenAnswer() можно использовать, когда методу нужно вернуть специфичный ответ,
         * либо пр возвращении ответа нужно выполнить дополнительное действие
         */
        when(calcService.add(5.0, 5.0)).thenAnswer(new Answer<Double>() {
            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                System.out.println("Arguments passed to mock: " + Arrays.toString(args));
                Object mock = invocation.getMock();
                System.out.println("MockName: " + mock);
                return 10.0;
            }
        });

        /**
         * объекты проаннотированные аннотацией @Spy или добавленные в
         * качестве параметра в метод spy() будут испольлзовать реальную
         * реализацию, а не реализацию заглушки
         */




        /**
         * используем методы заглушки
         */
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

        assertEquals(mathApplication.substract(20.0, 10.0), 10.0, 0);
        assertEquals(mathApplication.add(5.0,5.0), 10.0, 0);
//        assertEquals(mathApplication.substract(20.0, 10.0), 10.0, 0);



        /**
         * проверяет метод в заглушке, что он принимает нужные аргументы. В данном случае
         * аргументы различаются, поэтому при тестировании тест "провалиться" и выведется:
         * Argument(s) are different! Wanted:
         * calcService.add(10.0d, 30.0d);
         * -> at junitintegration.MathApplicationTester.testAdd(MathApplicationTester.java:46)
         * Actual invocation has different arguments:
         * calcService.add(10.0d, 20.0d);
         * -> at junitintegration.MathApplication.add(MathApplication.java:12)
         * */
//      verify(calcService).add(10.0, 30.0);

        /**
         * times() позволяет наложить ограничение на кол-во вызывов метода заглушки,
         * в данном случае 1 вызов - не больше, не меньше
         */
        verify(calcService, times(3)).add(10.0, 20.0);

        /**
         * здесь по умолчанию times() принимает 1
         */
        verify(calcService).substract(20.0,10.0);

        /**
         * never() показывает, что данный метод заглушки никогда не должен вызываться
         */
        verify(calcService, never()).multiply(10.0, 20.0);

        /**
         * atLeastOnce() проверяет, что метод вызван хотя бы один раз(можно вызывать несколько раз)
         */
        verify(calcService, atLeastOnce()).substract(20.0, 10.0);

        /**
         * timeout() указывает максимальное время, в течение которого может выполняться
         * данный метод заглушки(также нужно в times() указать, сколько раз вызывается данный метод)
         */
        verify(calcService, timeout(100).times(3)).add(10.0, 20.0);

        /**
         * atLeast() проверяет, что метод вызван не менее указанного числа раз
         */
        verify(calcService, atLeast(2)).add(10.0, 20.0);

        /**
         * atMost() проверяет, что метод вызван не более указанного числа раз
         */
        verify(calcService, atMost(4)).add(10.0, 20.0);

        /**
         * создаём объект, в котором будет указана очерёдность вызовов методов заглушки
         * Соблюдение этой очерёдности будет проверяться
         */

        InOrder inOrder = inOrder(calcService);
        inOrder.verify(calcService, times(3)).add(10.0, 20.0);
        inOrder.verify(calcService).substract(20.0, 10.0);


        /**
         * не будет работать, если метод-заглушки возвращает ошибку
         */
//         inOrder.verify(calcService).add(90.0, 50.0);


        /**
         * метод reset(<mock>) сбрасывает поведение данной заглушки(т.е. тестирование заглушки со старым поведением после reset() приведёт к неудаче
         * т.к. поведение методов для данной заглушки мы уже сбросили)
         */

        /**
         * Behavior Driven Development поддерживается Mockito с помощью метода
         * given()
         */
//      given
        given(calcService.add(40.0, 20.0)).willReturn(60.0);

//      when
        double result = calcService.add(40.0,20.0);

//      then
        assertEquals(result, 60.0, 0);

        /**
         * метод, который выбрасывает исключение лучше писать в конце метода, т.к. при выбрасывании методом
         * искючения остальные строчки текущего метода выполнятся не будут
         */
        assertEquals(mathApplication.add(90.0, 50.0), 140.0, 0);

    }


}
