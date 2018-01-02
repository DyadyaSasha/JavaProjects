package word;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

public class WordExtractor {

    public static void main(String[] args) {
        XWPFDocument document = null;
        try {
            document = new XWPFDocument(new FileInputStream("createdoc.docx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//      читает из doc файла текст, заголовки, таблицы и др.
        XWPFWordExtractor we = new XWPFWordExtractor(document);
        System.out.println(we.getText());
    }
}
