package collectionsutilities;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BimapInterface {
    public static void main(String[] args) {

        /**
         * BiMap предсавляет собой двунаправленный словарь, то есть и ключи и значения уникальны
         *
         * 1) если добавить в словарь к новому ключу значение. еоторое уже существует,
         * то -> java.lang.IllegalArgumentException: value already present:
         * 2) если к существующему ключу добавить новое значение, то значение обновиться
         * */
        BiMap<Integer,String> empIDNameMap = HashBiMap.create();
        empIDNameMap.put(new Integer(101), "Mahesh");
        empIDNameMap.put(new Integer(102), "Sohan");
        empIDNameMap.put(new Integer(103), "Ramesh");
        empIDNameMap.forEach((i, j) -> {
            System.out.println(i + " : " + j);
        });
        System.out.println(empIDNameMap.inverse().get("Mahesh"));
    }
}
