package lambda;

/**
 * подробнее о лямбда смотри в соответствующих главах книг OCA и OCP
 */
public class Lambda {
    public static void main(String[] args) {

        Lambda lambda = new Lambda();

        /**
         * пишем разные реализации функционального интерфейса через лямбда
         */
        MathOperation addition = (int a, int b) -> a + b;

        MathOperation substraction = (a, b) -> a - b;

        MathOperation multiplication = (int a, int b) -> {return a*b;};

        MathOperation division = (int a, int b) -> a/b;

        /**
         * вызываем метод, который вызывает соответсвующую реализацию функционального интерфейса с конкретными аргументами
         */
        System.out.println("10 + 5 = " + lambda.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambda.operate(10, 5, substraction));
        System.out.println("10 x 5 = " + lambda.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambda.operate(10, 5, division));

        GreetingService greetingService1 = message -> System.out.println("Hello " + message);
        GreetingService greetingService2 = (message) -> System.out.println("Hello " + message);

        greetingService1.sayMessage("Alex");
        greetingService2.sayMessage("Vitsel");
    }

    /**
     * {@link FunctionalInterface} указывает, что данный интерфейс содержит
     * только один метод, чтобы реализовывать лямбда выражения(но применять это аннотацию необязательно,
     * но при этом в интерфейсе можно будет объявить больше методов и этот интерфейс уже нельзя будет реализовать через лямбда)
     */
    @FunctionalInterface
    interface MathOperation{
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService{
        void sayMessage(String message);
    }

    /**
     * функциональные интерфейсы можно использовать в качестве аргумента в методе
     */
    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
