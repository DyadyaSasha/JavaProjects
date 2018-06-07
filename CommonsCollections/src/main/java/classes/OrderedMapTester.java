package classes;

import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.map.LinkedMap;

public class OrderedMapTester {
    public static void main(String[] args) {
        /**
         * словарь, учитывающий порядок добавления в коллекцию
         */
        OrderedMap<String,String> map = new LinkedMap<>();
        map.put("One", "1");
        map.put("Two", "2");
        map.put("Three", "3");

        /**
         * получаем первый ключ словаря
         */
        System.out.println(map.firstKey());
        /**
         * получаем следующий ключ словаря
         */
        System.out.println(map.nextKey("One"));
        System.out.println(map.nextKey("Two"));
    }
}
