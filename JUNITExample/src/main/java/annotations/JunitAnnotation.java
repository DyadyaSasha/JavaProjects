package annotations;

import org.junit.*;

public class JunitAnnotation {

    @BeforeClass
    public static void beforeClass(){
        System.out.println("before class");
    }

    @Before
    public void before(){
        System.out.println("before");
    }


    /**
     * тест(case), помечченный аннотоцией Ignore
     * не участвует в тестировании
     * если пометить данной аннотаций весь класс, то
     * данный класс со всеми тестовыми методами будет
     * проигнорирован при тестировании
     * */
    @Ignore
    @Test
    public void ignoreTest(){
        System.out.println("ignoreTest");
    }


    /**
     * параметр timeout: если тест выполняется больше указанного времени
     * в timeout(в миллисекундах), то данный тест будет
     * помечен как неудачный(failed)
     *
     * параметр expected: параметр expected позволяет протестировать,
     * порождает ли тестируемый код ошибку, указанную в expected или нет
     * если в методе возникнет ошибка, указзанная в expected, то тест
     * будет помечен как удачный, иначе - неудачный
     * */
    @Test(timeout = 1, expected = Exception.class)
    public void test(){
        System.out.println("test");
        Assert.assertSame(2, 2);
    }


    @After
    public void after(){
        System.out.println("after");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("after class");
    }
}
