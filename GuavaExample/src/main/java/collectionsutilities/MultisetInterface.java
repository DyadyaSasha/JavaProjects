package collectionsutilities;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Iterator;
import java.util.Set;

/**
 * расширяет класс Set с возможностью хранения дупликатов
 * Предсталяет собой тот же класс Set только содержит методы, которые учитывают,
 * что элементы во множестве могут дублироваться
 * */
public class MultisetInterface {
    public static void main(String[] args) {

        /**
         * ????
         * */
        Multiset<String> multiset = HashMultiset.create();

        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        multiset.add("b");
        multiset.add("b");


        /**
         * кол-во вхождений в множество с дупликатами
         * */
        System.out.println("Occurrence of 'b': " + multiset.count("b"));

        /**
         * кол-во элементов в множестве
         * */
        System.out.println("Total size: " + multiset.size());

        /**
         * Multiset в set
         * */
        Set<String> set = multiset.elementSet();

        System.out.println("Set [");
        for(String s: set){
            System.out.println(s);
        }
        System.out.println("]");

        System.out.println("Multiset [");
        for (Iterator<String> iterator = multiset.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }
        System.out.println("]");

        System.out.println("Multiset [");
        for(Multiset.Entry<String> entry: multiset.entrySet()){
            System.out.println("Element: " + entry.getElement() + ", Occurrence(s): " + entry.getCount());
        }
        System.out.println("]");

        /**
         * удаляем элемент заданное число раз
         * */
        multiset.remove("b", 2);

        System.out.println("Occurrence of 'b': " + multiset.count("b"));

    }
}
