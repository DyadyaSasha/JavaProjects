package classes;

import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;

public class MapIteratorTester {
    public static void main(String[] args) {
        /**
         * словарь, по которому можно итерироваться. не получая EntrySet
         */
        IterableMap<String, String> map = new HashedMap<>();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("4", "Four");
        map.put("5", "Five");

        /**
         * итератор, позволяющий итерироваться по словарю
         */
        MapIterator<String,String> iterator = map.mapIterator();
        while (iterator.hasNext()){
            /**
             * получаем ключ
             * также можно использовать iterator.getKey()
             */
            String key = iterator.next();
            /**
             * получаем значение
             */
            String value = iterator.getValue();

            System.out.println("key: " + key);
            System.out.println("value: " + value);

            /**
             * присваеваем новое значение
             */
            iterator.setValue(value + "_");

        }

        System.out.println(map);
    }
}
