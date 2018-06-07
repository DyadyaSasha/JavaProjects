package utilityclasses;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

public class LineIteratorTester {
    public static void main(String[] args) throws IOException{

        File file = FileUtils.getFile("input.txt");
        /**
         * {@link LineIterator} позволяет удобно построчно итерироваться по файлу
         */
        try (LineIterator iterator = FileUtils.lineIterator(file)){
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }
}
