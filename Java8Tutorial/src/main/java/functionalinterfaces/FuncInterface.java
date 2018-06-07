package functionalinterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * функциональные интерфейсы объявляют всего один метод который можно будет реализовать через лямбда выражения
 * функциональные интерфейсы можно использовать в качестве аргумента в методах
 * функциональные интерфейсы позволяют реализовывать по одному шаблону разную логику работы приложения
 * определённые в Java функциональные интерфейсы содержаться в пакете java.util.Function
 */
public class FuncInterface {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);

        System.out.println("Print all numbers:");

        eval(list, n -> true);

        System.out.println("Print even numbers:");
        eval(list, n -> n%2 == 0);

        System.out.println("Print numbers greater than 3:");
        eval(list, n-> n > 3);

    }

    private static void eval(List<Integer> list, Predicate<Integer> predicate){
        for(Integer n: list){
            /**
             * в функциональном интерфейсе {@link Predicate} сожержиться один единственный метод test()
             */
            if(predicate.test(n)){
                System.out.println(n + " ");
            }
        }
    }
}
