package collectionsutilities;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

/**
 * Тот же Map, что и в java.util, но с возможностью
 * хранения нескольких значений(в коллекции) для одного ключа
 * */
public class MultiMapInterface {
    public static void main(String[] args) {
        MultiMapInterface tester = new MultiMapInterface();
        Multimap multimap = tester.getMultimap();

        /**
         * получаем список значений, относящихся к заданному ключу
         * */
        List<String> lowerList = (List<String>) multimap.get("lower");

        System.out.println("Initial lower case list");

        System.out.println(lowerList.toString());

        /**
         * при изменении в отображении изменится и содержание оригинального
         * Multimap
         * */
        lowerList.add("f");
        System.out.println("Modified lower case list");
        System.out.println(lowerList.toString());

        List<String> upperList = (List<String>) multimap.get("upper");
        System.out.println("Initial upper case list");
        System.out.println(upperList.toString());

        /**
         * при изменении в отображении изменится и содержание оригинального
         * Multimap
         * */
        upperList.remove("D");
        System.out.println("Modified upper case list");
        System.out.println(upperList.toString());

        /**
         * Multimap в обычный Map
         * */
        Map<String, Collection<String>> map = multimap.asMap();
        System.out.println("Multimap as a map");

        for(Map.Entry<String,Collection<String>> entry : map.entrySet()){
            String key = entry.getKey();
            Collection<String> value = entry.getValue();
            System.out.println(key + ":" + value);
        }

        System.out.println("Keys of Multimap");
        Set<String> key = multimap.keySet();
        key.forEach(System.out::println);

        System.out.println("Values of Multimap");
        multimap.values().forEach(System.out::println);



    }

    private Multimap<String,String> getMultimap(){

        /**
         * ArrayListMultimap использует {@link ArrayList} для хранения значений,
         * соответствующих ключу
         */
        Multimap<String,String> multimap = ArrayListMultimap.create();

        multimap.put("lower", "a");
        multimap.put("lower", "b");
        multimap.put("lower", "c");
        multimap.put("lower", "d");
        multimap.put("lower", "e");

        multimap.put("upper", "A");
        multimap.put("upper", "B");
        multimap.put("upper", "C");
        multimap.put("upper", "D");

        return multimap;
    }
}
