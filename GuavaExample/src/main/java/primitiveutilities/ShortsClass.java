package primitiveutilities;

import com.google.common.primitives.Shorts;

import java.util.List;

/**
 * Shorts класс сдержит утилитарные методы для работы
 * с примитивами типа short
 * */
public class ShortsClass {
    public static void main(String[] args) {
        ShortsClass tester = new ShortsClass();
        tester.testShorts();
    }
    private void testShorts(){
        short[] shortArray = {1,2,3,4,5,5,7,9,9};

        /**
         * переводим массив примитивных short в объектный short лист
         * */
        List<Short> objectArray = Shorts.asList(shortArray);
        System.out.println(objectArray.toString());

        /**
         * пререводим объектный short лист в массив примитивных short
         * */
        shortArray = Shorts.toArray(objectArray);
        System.out.print("[ ");
        for (short b : shortArray) {
            System.out.print(b + " ");
        }
        System.out.println("]");
        short data = 5;


        /**
         * содержиться ли в массиве примитивных short указанное значение
         * */
        System.out.println("5 is in list? " + Shorts.contains(shortArray, data));

        /**
         * индекс первого вхождения указанного значения в массиве примитивных short
         * */
        System.out.println("Index of 5: " + Shorts.indexOf(shortArray, data));

        /**
         * индекс поледнего вхождения указанного значения в массиве примитивных short
         * */
        System.out.println("Last index of 5: " + Shorts.lastIndexOf(shortArray, data));

        /**
         * min значение в массиве примитивов short
         * */
        System.out.println("Min: " + Shorts.min(shortArray));

        /**
         * max значение в массиве примитивов short
         * */
        System.out.println("Max: " + Shorts.max(shortArray));

        data = 2400;

        /**
         * переводим значение примитива short в примитивный массив байтов
         * */
        byte[] byteArray = Shorts.toByteArray(data);
        for (int i = 0; i < byteArray.length; i++){
            System.out.print(byteArray[i] + " ");
        }
    }
}
