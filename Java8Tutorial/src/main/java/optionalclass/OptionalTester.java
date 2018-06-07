package optionalclass;

import java.util.Optional;

/**
 * https://habrahabr.ru/post/225641/
 * Класс {@link Optional} - контейнер для корректной работы с возможными null значениями.
 * Помогает избегать {@link NullPointerException}
 * аналогичный класс есть в библиотеке Guava
 */
public class OptionalTester {
    public static void main(String[] args) {

        Integer value1 = null;
        Integer value2 = 10;

        /**
         * ofNullable() возвращает объект {@link Optional} с переданным в метод значением, если значение не null
         * и Optional.empty если переданное в метод значение null
         */
        Optional<Integer> a = Optional.ofNullable(value1);
        System.out.println(a.toString());

        /**
         * of() возвращает объект {@link Optional} с переданным в метод значением, если значение не null
         * и {@link NullPointerException} если переданное в метод значение null
         */
        Optional<Integer> b = Optional.of(value2);
        System.out.println(b.toString());

        System.out.println(sum(a, b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){

        /**
         * isPresent() возвращает true, если в объекте {@link Optional} не null(empty) значение,
         * иначе - false
         */
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        /**
         * orElse() возвращает значение из объекта {@link Optional}, если оно не empty(null),
         * иначе возвращает значение, переданное в метод в качестве аргумента
         */
        Integer value1 = a.orElse(new Integer(0));

        /**
         * get() возвращает значение из объекта {@link Optional}, если оно не empty(null),
         * иначе возвращает {@link java.util.NoSuchElementException}
         */
        Integer value2 = b.get();

        return value1 + value2;
    }
}
