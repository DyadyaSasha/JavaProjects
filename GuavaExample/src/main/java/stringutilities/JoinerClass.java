package stringutilities;

import com.google.common.base.Joiner;

import java.util.Arrays;

public class JoinerClass {
    public static void main(String[] args) {
        JoinerClass tester = new JoinerClass();
        tester.testJoiner();
    }

    private void testJoiner(){
        /**
         * {@link Joiner} содержит различные операции соединения
         * строк и объектов
         * on() возвращает объект Joiner с установленным сепаратором
         * skipNulls() возвращает объект Joiner, который будет
         * отбрасывать нулевые ссылки при использовании метода join()
         * join() возвращает строку согласно установенной конфигурации
         * */
        System.out.println(Joiner.on(".")
        .skipNulls()
        .join(Arrays.asList(1,2,3,4,5,null,6)));
    }
}
