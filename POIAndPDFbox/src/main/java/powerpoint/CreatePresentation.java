package powerpoint;

import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePresentation {

    public static void main(String[] args) {
//      создаём пустую презентацию
        XMLSlideShow ppt = new XMLSlideShow();

//      создаём пустой слайд
        XSLFSlide slide = ppt.createSlide();

        try {
//      представляем картинку как массив байт
        byte[] image = IOUtils.toByteArray(new FileInputStream("minion.jpg"));
//      добавляем картинку к презентации
        XSLFPictureData pic = ppt.addPicture(image, XSLFPictureData.PictureType.JPEG);
//      вставляем картинку на конкретный слайд
        slide.createPicture(pic);


//      образец слайда(SlideMaster) содержит в себе несколько макетов слайдов(SlideLayouts)
        for(XSLFSlideMaster master : ppt.getSlideMasters()){
            for(XSLFSlideLayout layout : master.getSlideLayouts()){
                System.out.println("NAME: " + layout.getName() +
                "\n TYPE: " + layout.getType());
            }
        }
//      берём нужный образец слайда
        XSLFSlideMaster master = ppt.getSlideMasters().get(0);
//      берём нужный макет слайда
        XSLFSlideLayout layout = master.getLayout(SlideLayout.TITLE);

//      создаём слайд с нужным макетом
        XSLFSlide slide1 = ppt.createSlide(layout);

//      берём часть из слайда для заполнения
        XSLFTextShape title1 = slide1.getPlaceholder(0);
        title1.setText("Tutorials Point");
        XSLFTextShape hyperlink = slide1.getPlaceholder(1);
        hyperlink.clearText();
        XSLFTextRun textRun = hyperlink.addNewTextParagraph().addNewTextRun();
        textRun.setText("Tutorials");
//      создаём гиперссылку
        XSLFHyperlink link = textRun.createHyperlink();
//      привязываем гиперссылку к сайту
        link.setAddress("https://www.tutorialspoint.com/");

        XSLFSlide slide2 = ppt.createSlide(master.getLayout(SlideLayout.TITLE_AND_CONTENT));
        slide2.getPlaceholder(0).setText("Introduction");
        XSLFTextShape body = slide2.getPlaceholder(1);
//      очищаем текст, который добавлен в данную область по умолчанию
        body.clearText();
//      добавляем пункт с текстом
        body.addNewTextParagraph().addNewTextRun().setText("this is  my first slide body");

        XSLFSlide slide3 = ppt.createSlide(master.getLayout(SlideLayout.TITLE_AND_CONTENT));
        XSLFTextShape text = slide3.getPlaceholder(1);
        text.clearText();
        XSLFTextParagraph paragraph = text.addNewTextParagraph();
//      все манипуляции непосредственно с текстом происходят через XSLFTextRun
        XSLFTextRun run1 = paragraph.addNewTextRun();
        run1.setText("This is a colored line");
        run1.setFontColor(Color.RED);
        run1.setFontSize(24.0);
//      переходим на следующую строку
        paragraph.addLineBreak();

        XSLFTextRun run2 = paragraph.addNewTextRun();
        run2.setText("This is a bold  line");
        run2.setFontColor(Color.CYAN);
        run2.setBold(true);
        paragraph.addLineBreak();

        XSLFTextRun run3 = paragraph.addNewTextRun();
        run3.setText("This is a striked line");
        run3.setFontSize(12.0);
        run3.setItalic(true);
        run3.setStrikethrough(true);
        paragraph.addLineBreak();

        XSLFTextRun run4 = paragraph.addNewTextRun();
        run4.setText("This an underlined line");
        run4.setUnderlined(true);
        paragraph.addLineBreak();





            FileOutputStream os = new FileOutputStream(new File("example.pptx"));
//          привязываем к реальному файлу
            ppt.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
