package tika;

import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorImpl;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.language.LanguageIdentifier;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

public class ParserExtraction {

    public static void main(String[] args) throws IOException, TikaException, SAXException {
        File file = new File("sample.txt");
//      указываем парсер, который будет обрабатывать док-т(в данном случае парсер определяется автоматически)
        Parser parser = new AutoDetectParser();
//      объект, в котором будут хранится данные парсинга док-та
        BodyContentHandler handler = new BodyContentHandler();
//      объект, в котором хранятся метаданные док-та
        Metadata metadata = new Metadata();
        FileInputStream fis = new FileInputStream(file);
        ParseContext context = new ParseContext();
//      парсим док-т
        parser.parse(fis, handler, metadata, context);
//      выводим содержимое док-та
        System.out.println("File content: " + handler.toString());

//      выводим метаданные
        for (String name : metadata.names()){
            System.out.println(name + ": " + metadata.get(name));
        }
//      добавляем новые метаданные
        metadata.add("Author", "Alex");
        System.out.println();
        for (String name : metadata.names()){
            System.out.println(name + ": " + metadata.get(name));
        }

//      определяем язык используемый в переданной строке
        LanguageIdentifier identifier = new LanguageIdentifier("это русский язык");
        System.out.println("Language of the given content is : " + identifier.getLanguage());
    }
}
