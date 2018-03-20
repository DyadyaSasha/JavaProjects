package genericclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamingConventions {
    public static void main(String[] args) {
        Box1<Integer, String> box1 = new Box1<>();
        box1.add(10, "Hello");
        System.out.printf("Integer Value: %d\n", box1.getFirst());
        System.out.printf("String Value: %s\n", box1.getSecond());

        Pair<String, Integer> pair = new Pair<>();
        pair.addKeyValue("1", 10);
        System.out.printf("(Pair)Integer Value: %d\n", pair.getValue("1"));

        CustomList<Box1> list = new CustomList<>();
        list.addItem(box1);
        System.out.printf("(CustomList)Integer Value: %d\n", list.getItem(0).getFirst());
    }
}

/**
 * generic типы-параметры принято начинать с одной большой буквы
 * принятые обозначения:
 * E - используется для обозначения generic типа-параметра элемента в коллекциях
 * K - используется для обозначения generic типа-параметра ключа в Мапе
 * V - используется для обозначения generic типа-параметра значения в Мапе
 * N - используется для обозначения числового generic типа-параметра
 * T - используется для обозначения первого generic типа-параметра
 * S - используется для обозначения второго generic типа-параметра
 * U - используется для обозначения третьего generic типа-параметра
 * V - используется для обозначения четвёртого generic типа-параметра
 */
class Box1<T, S>{
    private T t;
    private S s;

    public void add(T t, S s){
        this.t = t;
        this.s = s;
    }

    public T getFirst(){
        return t;
    }

    public S getSecond(){
        return s;
    }
}

class Pair<K, V>{
    private Map<K, V> map = new HashMap<>();

    public void addKeyValue(K key, V value){
        map.put(key, value);
    }

    public V getValue(K key){
        return map.get(key);
    }
}

class CustomList<E>{
    private List<E> list = new ArrayList<>();

    public void addItem(E value){
        list.add(value);
    }

    public E getItem(int index){
        return list.get(index);
    }
}

