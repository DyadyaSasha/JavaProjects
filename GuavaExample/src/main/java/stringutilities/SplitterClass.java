package stringutilities;

import com.google.common.base.Splitter;

public class SplitterClass {
    public static void main(String[] args) {
        SplitterClass tester = new SplitterClass();
        tester.testSplitter();
    }

    /**
     * {@link Splitter} содержит методы для разделения строк, объектов
     * on() возвращает Splitter с указанным в on() сепаратаром(по какому символу будет происходить разделение строки)
     * trimResults() возвращает Splitter, который удаляет лидирующие и замыкающие символы в каждой подстроке, получившейся после разделения
     * omitEmptyStrings() возвращает Splitter, который не будет учитывать пустые строки
     * split() возвращает объект типа Iterable<String> - коллекция строк после разделения по указанному в on() сепаратору
     * */
    private void testSplitter(){
        System.out.println(Splitter.on(",")
        .trimResults()
        .omitEmptyStrings()
        .split("the ,quick, ,brown, fox, jumps, over, the, lazy, little dog."));
    }
}
