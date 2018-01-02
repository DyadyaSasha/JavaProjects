package pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CreateDoc {

    public static void main(String[] args) {
        try {
//      создаём представление док-та в памяти(в runtime)
            PDDocument document = new PDDocument();

//          объект, отвечающий за ограничение доступа у док-ту
            AccessPermission permission = new AccessPermission();
//          создаём ограничение(нужно будет ввести пароль), настраиваем политику ограничения
            StandardProtectionPolicy policy = new StandardProtectionPolicy("1234", "1234", permission);
//          задаём длину ключа шифрования
            policy.setEncryptionKeyLength(128);
//          устанавливаем настроенные ограничения
            policy.setPermissions(permission);
//          связываем ограничения с док-том
            document.protect(policy);

//          можно вставлять в документ Java Script код, который будет выполняться
            PDActionJavaScript action = new PDActionJavaScript("app.alert( {cMsg: 'this is an example', nIcon: 3,"
                    + " nType: 0,cTitle: 'PDFBox Javascript example' } );");
//          указываем действие(на Java Script), которое должно выполниться при открытии док-та
            document.getDocumentCatalog().setOpenAction(action);
//      создаём первую пустую страницу pdf док-та
            PDPage page0 = new PDPage();
//      добавляем страницу к док-ту
            document.addPage(page0);

//          загружаем картинку в документ(PDImageXObject представляет собой картинку в pdf док-те)
            PDImageXObject image = PDImageXObject.createFromFile("minion.jpg", document);
//          все манипуляции с данными на странице документа происходят через этот объект
            PDPageContentStream contentStream0 = new PDPageContentStream(document, page0);
//          рисуем картинку в определённыз координатах
            contentStream0.drawImage(image, 0, 0 );
            contentStream0.close();

            document.addPage(new PDPage());
//      берём нужную страницу(страницы начинаются с 0)
            PDPage page = document.getPage(1);
//      класс для манипуляции с данными на определённой странице
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
//          указываем, в какое место нужно вставить текст
            contentStream.newLineAtOffset(25,725);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//          расстояние сдвига курсора к новой строке - если не использовать этот метод, то новый текст, вставится не сновой строки, а поверх старого
            contentStream.setLeading(14.5);
//          вставляем текст(текст, который не умещается в ту же строку !!!отображаться не будет!!!)
            contentStream.showText("This is the sample document and we are adding content to it.");
            contentStream.newLine();
            contentStream.showText("This is an example of adding text to a page in the pdf document " +
                    "we can add as many lines.");
            contentStream.endText();
            contentStream.close();

            PDPage page2 = new PDPage();
            PDPageContentStream contentStream1 = new PDPageContentStream(document, page2);
//          указываем цвет будущего прямоугольника
            contentStream1.setNonStrokingColor(Color.BLUE);
//          указываем параметры прямоугольника, который нужно нарисовать
            contentStream1.addRect(200, 550, 50, 30);
//          рисуем прямоугольник
            contentStream1.fill();
//          обязательно закрываем поток, иначе вылезет ошибка
            contentStream1.close();
//          добавляем страницу к док-ту
            document.addPage(page2);


//          нужен для получения текста из док-та
            PDFTextStripper stripper = new PDFTextStripper();
//          выводим весь текст док-та
            System.out.println(stripper.getText(document));




//      информация, отображающаяся в свойствах док-та
            PDDocumentInformation information = document.getDocumentInformation();
            information.setAuthor("Alex");
            information.setTitle("Sample document");
            information.setCreator("PDF Examples");
            information.setSubject("Example document");

            Calendar date = new GregorianCalendar();
            date.set(2018, Calendar.JANUARY, 1);
            information.setCreationDate(date);
            date.set(2018, Calendar.JANUARY, 2);
            information.setModificationDate(date);

            information.setKeywords("sample, first example, my pdf");


//          сохраняем док-т в файловой системе
            document.save("doc.pdf");
//          обязательно закрываем док-т
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
