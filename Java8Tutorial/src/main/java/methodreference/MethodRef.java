package methodreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Ссылка на метод помогает указать методы по их именам(использовать метод по имени)
 * Ссылка на метод обозначается ::
 * Ссылка на метод используется в следубщих случаях:
 * 1) чтобы вызвать static методы
 * 2) чтобы вызвать методы экземпляра
 * 3) чтобы вызвать конструктор (TreeSet::new)
 *
 */
public class MethodRef {
    public static void main(String[] args) {
        List names = new ArrayList();

        names.add("Mahesh");
        names.add("Suresh");
        names.add("Ramesh");
        names.add("Naresh");
        names.add("Kalpesh");

        names.forEach(System.out::println);
    }
}
