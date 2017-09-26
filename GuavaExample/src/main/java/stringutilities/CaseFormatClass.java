package stringutilities;

import com.google.common.base.CaseFormat;

/**
 * CaseFormat - утилитарный класс, обеспечивающий преобразование между различнми форматами
 * символов ASCII
 * */
public class CaseFormatClass {
    public static void main(String[] args) {
        CaseFormatClass tester = new CaseFormatClass();
        tester.testCaseFormat();
    }
    private void testCaseFormat(){

        /**
         * LOWER_CAMEL - Java variable naming convention, e.g., "lowerCamel".
         * LOWER_HYPHEN - Hyphenated(написанный через дефис) variable naming convention, e.g., "lower-hyphen".
         * to() - возвращает строку(указанную в параметрах) в определенном формате(указанном в параметрах);
         * исходный формат строки указан в объекте, вызывающим данный метод
         * */
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));

        /**
         * LOWER_UNDERSCORE - C++ variable naming convention, e.g., "lower_underscore".
         * */
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));

        /**
         * UPPER_CAMEL - Java and C++ class naming convention, e.g., "UpperCamel".
         * */
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));
    }
}
