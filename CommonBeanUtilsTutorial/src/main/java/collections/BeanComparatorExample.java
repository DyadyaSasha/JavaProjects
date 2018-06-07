package collections;

import org.apache.commons.beanutils.BeanComparator;

public class BeanComparatorExample {

    public static void main(String[] args) {

        Car car1 = new Car();
        car1.setBrand("BMW");

        Car car2 = new Car();
        car2.setBrand("AUDI");
        /**
         * {@link BeanComparator} позволяет сравнивать бины по указанному в конструкторе имени поля
         */
        BeanComparator comparator = new BeanComparator("brand");
        /**
         * сравниваем бины
         */
        System.out.println("The value after comparing two beans is: " + comparator.compare(car1,car2));
    }
}
