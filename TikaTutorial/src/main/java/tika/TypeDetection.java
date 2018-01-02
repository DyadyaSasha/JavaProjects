package tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;

public class TypeDetection {

    public static void main(String[] args) {
        File file = new File("pom.xml");
//      класс с основными методами
        Tika tika = new Tika();
        String type = null;
        String text = null;
        try {
//          узнаём тип файла
            type = tika.detect(file);
//          вывод текстового содержимого(библиотека сама выбирает, какой парсер использовать)
            text = tika.parseToString(file);
        } catch (IOException | TikaException e) {
            e.printStackTrace();
        }
        System.out.println(type == null ? "File not found" : type);
        System.out.println("TEXT: \n" + text);
    }
}
