package genericclasses;

public class GenericMethods {

    /**
     * можно самостоятельно объявлять generic методы, которые могут принимать в
     * качестве аргумента различные типы(generic типы-параметры).
     * Правила при объявлении generic метода:
     * 1) При объявлении generic методов после модификаторов доступа указывается generic тип-параметр в <>, после него - тип возвращаемого значения
     * 2) generic типов-параметров может быть несколько в generic методе
     * 3) generic типы-параметры могут быть использованы, чтобы объявить тип возвращаемого значения, чтобы объявлять типы аргументов, передаваемых generic методу(которые называются фактическими аргументами типа)
     */
    public static <E> void printArray(E[] inputArray){
        for(E element : inputArray){
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Integer[] intArray = {1,2,3,4,5};
        Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
        Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);
    }
}
