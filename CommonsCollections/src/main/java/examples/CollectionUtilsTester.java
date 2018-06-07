package examples;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtilsTester {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();

        /**
         * {@link CollectionUtils} - утилитарный класс, предоставляющий методы для работы с коллекциями
         * addIgnoreNull() - добавляет элемент в коллекцию, если он не равен нулю, если элемент равен нулю, то метод игнорирует этот элемент
         */
        CollectionUtils.addIgnoreNull(list, null);
        CollectionUtils.addIgnoreNull(list, "a");

        System.out.println(list);

        if(list.contains(null)){
            System.out.println("Null value is present");
        } else {
            System.out.println("Null value is not present");
        }
        System.out.println();

        List<String> sortedList1 = Arrays.asList("A","C","E");
        List<String> sortedList2 = Arrays.asList("B","D","F");
        /**
         * Объединяет две !уже отсортированные! коллекции в одну отсортированнуюю коллекцию
         */
        List<String> mergedList = CollectionUtils.collate(sortedList1, sortedList2);
        System.out.println(mergedList);
        System.out.println();

        List<String> stringList = Arrays.asList("1","2","3");
        /**
         * преобразует коллекцию одного типа в коллекцию другого типа
         */
        List<Integer> integerList = (List<Integer>) CollectionUtils.collect(stringList, input -> Integer.parseInt(input));
        System.out.println(integerList);
        System.out.println();

        List<Integer> integerList1 = new ArrayList<>();
        integerList1.addAll(Arrays.asList(1,2,3,4,5,6,7,8));
        System.out.println("Original List: " + integerList1);
        /**
         * удаляем из коллекции значения не удовлетворяющие условию
         */
        CollectionUtils.filter(integerList1, object -> object % 2 == 0);
        System.out.println("Filtered List (Even numbers): " + integerList1);
        System.out.println();

        integerList1.addAll(Arrays.asList(1,2,3,4,5,6,7,8));
        System.out.println("Original List: " + integerList1);
        /**
         * удаляем из коллекции значения удовлетворяющие условию
         */
        CollectionUtils.filterInverse(integerList1, object -> object % 2 == 0);
        System.out.println("Filtered List (Odd numbers): " + integerList1);
        System.out.println();

        List<String> nullList = null;
        /**
         * isNotEmpty() возвращает true если коллекция не null и не пустая(содержит элементы)
         * т.е. данный метод можно вызывать не проверяя до этого равенство на null
         */
        System.out.println("Non-Empty List Check: " + CollectionUtils.isNotEmpty(nullList));


    }
}
