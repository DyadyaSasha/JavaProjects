package wildcards;

import java.util.Arrays;
import java.util.List;

public class BoundingWildcards {

    /**
     * ? - обозначает wildcard(подстановочный знак) и озночает неизвестный тип generic
     * Может потребоваться, чтобы ограничить типы, которые разрешено использовать в качестве параметра типа.
     * Например, в данном случае, метод может принимать только списки, содержащие наследников класса {@link Number}(либо экземпляр самого этого класса)
     */
    public static double sum(List<? extends Number> numbers){
        double sum = 0.0;
        sum = numbers.stream().mapToDouble(i -> i.doubleValue()).sum();
        return sum;
    }

    /**
     * super используется только с ? и требуется для того, чтобы ограничить типы,которые разрешено использовать в качестве параметра типа "снизу".
     * Т.е. как в данном случае, разрешено использовать экземпляры класса {@link Cat} и экземпляры классов, стоящие выше класса {@link Cat} в древе наследования(т.е. класс {@link Cat}
     * прямо или косвенно должен наследоваться от этих классов)
     */
    public static void addCat(List<? super Cat1> catlist){
        catlist.add(new RedCat1());
        System.out.println("Cat Added");
    }

    /**
     * просто ? без ограничений приводит любой переданный объект в тип {@link Object}
     * Данный приём можно использовать, когда тип передаваемого объекта не важен
     */
    public static void printAll(List<?> list){
        for(Object item : list){
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,2,3);
        System.out.println("sum = " + sum(integerList));

        List<Double> doubleList = Arrays.asList(1.2, 2.3, 3.5);
        System.out.println("sum = " + sum(doubleList));
    }
}

class Animal1 {}

class Cat1 extends Animal1 {}

class RedCat1 extends Cat1 {}

class Dog1 extends Animal1 {}
