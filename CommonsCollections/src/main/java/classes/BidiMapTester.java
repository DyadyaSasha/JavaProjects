package classes;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;

public class BidiMapTester {
    public static void main(String[] args) {
        /**
         * двунаправленный словарь, т.е. значение можно получить по ключу, а ключ - по значению
         */
        BidiMap<String, String> bidiMap = new TreeBidiMap<>();
        bidiMap.put("One", "1");
        bidiMap.put("Two", "2");
        bidiMap.put("Three", "3");
        /**
         * берём значение по ключу
         */
        System.out.println(bidiMap.get("One"));
        /**
         * берём ключ по значению
         */
        System.out.println(bidiMap.getKey("1"));
        System.out.println("Original Map: " + bidiMap);

        bidiMap.removeValue("1");
        System.out.println("Modified Map: " + bidiMap);
        /**
         * меняем ключ и значение местами
         */
        BidiMap<String,String> inversedMap = bidiMap.inverseBidiMap();
        System.out.println("Inversed Map: " + inversedMap);
    }
}
