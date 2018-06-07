package classes;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;

public class BagTester {
    public static void main(String[] args) {
        /**
         * коллекция, которая может содержать несколько копий одного и того же элемента
         * данная коллекция хранит объект и его кол-во в этой коллекции
         */
        Bag<String> bag = new HashBag<>();
        /**
         * добавляем объект два раза
         */
        bag.add("a", 2);
        bag.add("c");
        bag.add("d", 3);
        /**
         * выводим кол-во конкретного объекта
         */
        System.out.println("d is present " + bag.getCount("d") + " times.");
        System.out.println("bag: " + bag);
        /**
         * представляем коллекцию в виде множества(уникальные значения)
         */
        System.out.println("unique set: " + bag.uniqueSet());
        /**
         * удаляем объект из коллекции указанное число раз
         */
        bag.remove("d", 2);
        System.out.println("2 occurences of d removed from bag: " + bag);
        System.out.println("d is present " + bag.getCount("d") + " times.");
        System.out.println("bag: " +bag);
        System.out.println("Unique Set: " +bag.uniqueSet());

    }
}
