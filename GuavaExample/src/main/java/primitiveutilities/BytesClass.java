package primitiveutilities;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 * Bytes класс сдержит утилитарные методы для работы
 * с примитивами типа byte
 * */
public class BytesClass {

    public static void main(String[] args) {
        BytesClass tester = new BytesClass();
        tester.testBytes();
    }

    private void testBytes(){
        byte[] byteArray = {1,2,3,4,5,5,7,9,9};

        /**
         * переводим массив примитивных байтов в объектный байт лист
         * */
        List<Byte> objectArray = Bytes.asList(byteArray);
        System.out.println(objectArray.toString());

        /**
         * пререводим объектный байт лист в массив примитивных байтов
         * */
        byteArray = Bytes.toArray(objectArray);
        System.out.print("[ ");
        for (byte b : byteArray) {
            System.out.print(b + " ");
        }
        System.out.println("]");
        byte data = 5;


        /**
         * содержиться ли в массиве примитивных байтов указанное значение
         * */
        System.out.println("5 is in list? " + Bytes.contains(byteArray, data));

        /**
         * индекс первого вхождения указанного значения в массиве примитивных байтов
         * */
        System.out.println("Index of 5: " + Bytes.indexOf(byteArray, data));

        /**
         * индекс поледнего вхождения указанного значения в массиве примитивных байтов
         * */
        System.out.println("Last index of 5: " + Bytes.lastIndexOf(byteArray, data));
    }

}
