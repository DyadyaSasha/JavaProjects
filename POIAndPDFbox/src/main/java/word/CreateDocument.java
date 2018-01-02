package word;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateDocument {
    public static void main(String[] args) {

        //создаём пустой word документ
        XWPFDocument document = new XWPFDocument();
        FileOutputStream os = null;

//      новый абзац в документе
//      каждый новый элемент док-та создаётся на новой строке
        XWPFParagraph paragraph = document.createParagraph();
//      выравнивание целого абзаца(по умолчанию, выравнивание по левому краю)
        paragraph.setAlignment(ParagraphAlignment.RIGHT);

//      настраиваем границы абзаца(в данном случае - тёмные линии по границам)
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

//      XWPFRun используется, чтобы писать текст в указанный элемент
        XWPFRun run = paragraph.createRun();
//      добавим текст в новом абзаце
        run.setText("At tutorialspoint.com, we strive hard to " +
                        "provide quality tutorials for self-learning " +
                        "purpose in the domains of Academics, Information " +
                        "Technology, Management and Computer Programming " +
                "Languages.");

        XWPFParagraph paragraph2 = document.createParagraph();
        paragraph2.setAlignment(ParagraphAlignment.CENTER);
        paragraph2.createRun().setText("The endeavour started by Mohtashim, an AMU " +
                "alumni, who is the founder and the managing director " +
                "of Tutorials Point (I) Pvt. Ltd. He came up with the " +
                "website tutorialspoint.com in year 2006 with the help" +
                "of handpicked freelancers, with an array of tutorials" +
                " for computer programming languages.");

//      создаётся таблица с одной строкой и одним столбцом(отсчёт строк, столбцов, ячеек начинается с нуля)
        XWPFTable table = document.createTable();
        XWPFTableRow rowOne = table.getRow(0);
//      в первую ячейку строки вставляем текст
        rowOne.getCell(0).setText("col one, row one");
//      добавляем ещё одну ячейку(столбец) в строку
        rowOne.addNewTableCell().setText("col two, row one");
        rowOne.addNewTableCell().setText("col three, row one");

//      добавим новую строку в таблицу(при этом нужные столюцы добавляются автомматически)
        XWPFTableRow rowTwo = table.createRow();
        rowTwo.getCell(0).setText("col one, row two");
        rowTwo.getCell(1).setText("col two, row two");
        rowTwo.getCell(2).setText("col three, row two");

        XWPFTableRow rowThree = table.createRow();
        rowThree.getCell(0).setText("col one, row three");
        rowThree.getCell(1).setText("col two, row three");
        rowThree.getCell(2).setText("col three, row three");

        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun run1 = paragraph1.createRun();
        run1.setBold(true);
        run1.setItalic(true);
        run1.setText("Font Style");
        run1.addBreak();

        XWPFRun run2 = paragraph1.createRun();
        run2.setText("Font Style two");
//      указывает позицию, с которой будет добавляться следующий текст
        run2.setTextPosition(100);

        XWPFRun run3 = paragraph1.createRun();
//      зачёркивает текст
        run3.setStrikeThrough(true);
        run3.setFontSize(20);
//      выравнивание текста
        run3.setSubscript(VerticalAlign.SUBSCRIPT);
        run3.setText("Different Font Styles");

        try {
            os = new FileOutputStream(new File("createdoc.docx"));
//          связываем word документ с созданным !реальным! файлом
            document.write(os);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
