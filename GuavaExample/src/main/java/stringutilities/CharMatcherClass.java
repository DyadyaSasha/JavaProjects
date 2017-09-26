package stringutilities;

import com.google.common.base.CharMatcher;

/**
 * CharMatcher представляет методы для обработки символов
 * */
public class CharMatcherClass {
    public static void main(String[] args) {
        CharMatcherClass tester = new CharMatcherClass();
        tester.testCharMatcher();
    }

    private void testCharMatcher(){

        /**
         * digit() возвращает CharMatcher который будет искать вхождение чисел(соответствующие стандарту Unicode)
         * retainFrom() Returns a string containing all matching characters of a character sequence, in order.
         * */
        System.out.println(CharMatcher.digit().retainFrom("mahesh123"));

        /**
         * whitespace() возвращает CharMatcher который будет искать вхождение пробелов(соответствующие стандарту Unicode)
         * trimAndCollapseFrom() возвращает строку в которой нет лидирующих и последующих пробелов, при этом строка разбивается на группы,
         *  и между группами вставляется символ, указанный в втором парамеетре данного метода
         * */
        System.out.println(CharMatcher.whitespace().trimAndCollapseFrom("     Mahesh     Parashar     ", ' '));

        /**
         * javaDigit() возвращает CharMatcher который будет искать вхождение чисел(соответствующие определению в спецификации Java)
         * replaceFrom() вернёт строку, в которой все вхождения символов,
         * указанных в javaDigit()(в CharMatcher, изкоторого вызывается метод replaceFrom()),
         * будут заменены на символы, указанные в replaceFrom() в качестве второго параметра
         * */
        System.out.println(CharMatcher.javaDigit().replaceFrom("mahesh123", "*"));

        /**
         * or() возвращает CharMatcher который содержит в себе несколько CharMatcher
         * javaLowerCase() возвращает CharMatcher который будет искать вхождение символов в нижнем регистре(соответствующие определению в спецификации Java)
         * */
        System.out.println(CharMatcher.javaDigit().or(CharMatcher.javaLowerCase()).retainFrom("mahesh123"));
    }
}
