package excel;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CreateWorkBook {

    public static void main(String[] args) {

//      создаёт пустой excel документ
        XSSFWorkbook workbook = new XSSFWorkbook();
//      создаёт лист в excel документе(без созданного листа файл откроется с ошибкой)
        XSSFSheet sheet = workbook.createSheet("Employee Info");

//      строка в таблице
        XSSFRow row;

        Map<String, Object[]> empInfo = new TreeMap<>();
        empInfo.put("1", new Object[]{"EMP ID", "EMP NAME", "DESIGNATION"});
        empInfo.put("2", new Object[]{"tp01", "Gopal", "Technical Manager"});
        empInfo.put("3", new Object[]{"tp02", "Manisha", "Proof Reader"});
        empInfo.put("4", new Object[]{"tp03", "Masthan", "Technical Writer"});
        empInfo.put("5", new Object[]{"tp04", "Satish", "Technical Writer"});
        empInfo.put("6", new Object[]{"tp05", "Krishna", "Technical Writer"});

        Set<String> keyId = empInfo.keySet();
        int rowId = 0;
        for(String key : keyId){
//          создаём строку
            row = sheet.createRow(rowId++);
            Object[] objectArr = empInfo.get(key);

            int cellID=0;

            for(Object obj : objectArr){
//              создаём в строке объект ячейки на заполнение
                Cell cell = row.createCell(cellID++);
                cell.setCellValue((String)obj);
            }
        }

        XSSFSheet sheet1 = workbook.createSheet("Types of cells");
        row = sheet1.createRow(2);
        row.createCell(0).setCellValue("Type of Cell");
        row.createCell(1).setCellValue("Cell Value");

        row = sheet1.createRow(3);
        row.createCell(0).setCellValue("set cell type BLANK");
        row.createCell(1);

        row = sheet1.createRow(4);
        row.createCell(0).setCellValue("set cell type BOOLEAN");
        row.createCell(1).setCellValue(true);

        row = sheet1.createRow(5);
        row.createCell(0).setCellValue("set cell type ERROR");
        row.createCell(1).setCellValue(CellType.ERROR.getCode());

        row = sheet1.createRow((short) 6);
        row.createCell(0).setCellValue("set cell type date");
        row.createCell(1).setCellValue(new Date());

        row = sheet1.createRow((short) 7);
        row.createCell(0).setCellValue("set cell type numeric");
        row.createCell(1).setCellValue(20);

        row = sheet1.createRow((short) 8);
        row.createCell(0).setCellValue("set cell type string");
        row.createCell(1).setCellValue("A String");

        XSSFSheet sheet2 = workbook.createSheet("Cell Styles");
//      строки начинаются с нуля
        row = sheet2.createRow(1);
        row.setHeight((short) 800);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("test of merging");

//      объединяем указанные столбцы и строки
        sheet2.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));

        row = sheet2.createRow(5);
        cell = row.createCell(0);
        row.setHeight((short) 800);

//      класс, отвечающий за формат отображения(выравнивание и проч.) ячейки
        XSSFCellStyle style = workbook.createCellStyle();
        sheet2.setColumnWidth(0, 8000);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        cell.setCellValue("Top Left");
        cell.setCellStyle(style);
        row = sheet2.createRow(6);
        cell = row.createCell(1);
        row.setHeight((short) 800);

        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellValue("Center Aligned");
        cell.setCellStyle(style);
        row = sheet2.createRow(7);
        cell = row.createCell(2);

        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setVerticalAlignment(VerticalAlignment.BOTTOM);
        cell.setCellValue("Bottom Right");
        cell.setCellStyle(style);
        row = sheet2.createRow(8);
        cell = row.createCell(3);

        style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.JUSTIFY);
        style.setVerticalAlignment(VerticalAlignment.JUSTIFY);
        cell.setCellValue("Contents are Justified in Alignment");
        cell.setCellStyle(style);

        row = sheet2.createRow(10);
        row.setHeight((short) 800);
        cell = row.createCell(1);
        cell.setCellValue("BORDER");

//      делаем границы ячейки
        style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THICK);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderLeft(BorderStyle.DOUBLE);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.HAIR);
        style.setRightBorderColor(IndexedColors.RED.getIndex());
        style.setBorderTop(BorderStyle.DASHED);
        style.setTopBorderColor(IndexedColors.CORAL.getIndex());
        cell.setCellStyle(style);

//      цвета фона ячейки
        row = sheet2.createRow(10);
        cell = row.createCell(1);

        style = workbook.createCellStyle();
        style.setFillBackgroundColor(
                HSSFColor.HSSFColorPredefined.LEMON_CHIFFON.getIndex());
        style.setFillPattern(FillPatternType.LESS_DOTS);
        style.setAlignment(HorizontalAlignment.FILL);
        sheet2.setColumnWidth(1, 8000);
        cell.setCellValue("FILL BACKGROUND/FILL PATTERN");
        cell.setCellStyle(style);

        row = sheet2.createRow(12);
        cell = row.createCell(1);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.LESS_DOTS);
        style.setAlignment(HorizontalAlignment.FILL);
        cell.setCellValue("FILL FOREGROUND/FILL PATTERN");
        cell.setCellStyle(style);

        XSSFSheet sheet3 = workbook.createSheet("Font Format");
        row = sheet3.createRow(2);

//      меняем стиль и формат шрифта
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 30);
        font.setFontName("IMPACT");
        font.setItalic(true);
        font.setColor(HSSFColor.HSSFColorPredefined.BRIGHT_GREEN.getIndex());

        style = workbook.createCellStyle();
        style.setFont(font);

        cell = row.createCell(1);
        cell.setCellValue("Font Style");
        cell.setCellStyle(style);

//      меняем угол(направление) отображения текста
        XSSFSheet sheet4 = workbook.createSheet("Text direction");
        row = sheet4.createRow(2);
        style = workbook.createCellStyle();

        style.setRotation((short)0);
        cell = row.createCell(1);
        cell.setCellValue("0D angle");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setRotation((short) 30);
        cell = row.createCell(3);
        cell.setCellValue("30D angle");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setRotation((short) 90);
        cell = row.createCell(5);
        cell.setCellValue("90D angle");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setRotation((short) 120);
        cell = row.createCell(7);
        cell.setCellValue("120D angle");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setRotation((short) 270);
        cell = row.createCell(9);
        cell.setCellValue("270D angle");
        cell.setCellStyle(style);

        style = workbook.createCellStyle();
        style.setRotation((short) 360);
        cell = row.createCell(12);
        cell.setCellValue("360D angle");
        cell.setCellStyle(style);

        XSSFSheet sheet5 = workbook.createSheet("Formula");
        row = sheet5.createRow(1);
        cell = row.createCell(1);

        cell.setCellValue("A = ");
        cell = row.createCell(2);
        cell.setCellValue(2);
        row = sheet5.createRow(2);
        cell = row.createCell(1);
        cell.setCellValue("B = ");
        cell = row.createCell(2);
        cell.setCellValue(4);
        row = sheet5.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("Total = ");
        cell = row.createCell(2);

        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("SUM(C2:C3)");
        cell = row.createCell(3);
        cell.setCellValue("SUM(C2:C3)");
        row = sheet5.createRow(4);
        cell = row.createCell(1);
        cell.setCellValue("POWER = ");
        cell = row.createCell(2);

        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("POWER(C2,C3)");
        cell = row.createCell(3);
        cell.setCellValue("POWER(C2,C3)");
        row = sheet5.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("MAX = ");
        cell = row.createCell(2);

        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("MAX(C2,C3)");
        cell = row.createCell(3);
        cell.setCellValue("MAX(C2,C3)");
        row = sheet5.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("FACT = ");
        cell = row.createCell(2);

        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("FACT(C3)");
        cell = row.createCell(3);
        cell.setCellValue("FACT(C3)");
        row = sheet5.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("SQRT = ");
        cell = row.createCell(2);

        cell.setCellType(CellType.FORMULA);
        cell.setCellFormula("SQRT(C5)");
        cell = row.createCell(3);
        cell.setCellValue("SQRT(C5)");

//      выполняем указанные формулы
        workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();

        XSSFSheet sheet6 = workbook.createSheet("HyperLinks");
        CreationHelper creationHelper = workbook.getCreationHelper();
        XSSFCellStyle hlinkStyle = workbook.createCellStyle();
        XSSFFont hlinkFont = workbook.createFont();
        hlinkFont.setUnderline(XSSFFont.U_SINGLE);
        hlinkFont.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        hlinkStyle.setFont(hlinkFont);

        cell = sheet6.createRow(1).createCell(1);
        cell.setCellValue("URL Link");
        XSSFHyperlink link = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("http://www.tutorialspoint.com/");
        cell.setHyperlink(link);
        cell.setCellStyle(hlinkStyle);

        cell = sheet6.createRow(2).createCell(1);
        cell.setCellValue("File Link");
        link = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.FILE);
        link.setAddress("createworkbook.xlsx");
        cell.setHyperlink(link);
        cell.setCellStyle(hlinkStyle);

        cell = sheet6.createRow(3).createCell(1);
        cell.setCellValue("Email Link");
        link = (XSSFHyperlink) creationHelper.createHyperlink(HyperlinkType.EMAIL);
        link.setAddress("serebryakov_sash@list.ru");
        cell.setHyperlink(link);
        cell.setCellStyle(hlinkStyle);

        XSSFSheet sheet7 = workbook.createSheet("Print Area");
        workbook.setPrintArea(0,0,5,0,5);
        sheet7.getPrintSetup().setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
        sheet7.setDisplayGridlines(true);
        sheet7.setPrintGridlines(true);

        XSSFSheet sheet8 = workbook.createSheet("DB");
        row = sheet8.createRow(1);
        cell = row.createCell(1);
        cell.setCellValue("EMP ID");
        cell = row.createCell(2);
        cell.setCellValue("EMP NAME");
        cell = row.createCell(3);
        cell.setCellValue("DEG");
        cell = row.createCell(4);
        cell.setCellValue("SALARY");
        cell = row.createCell(5);
        cell.setCellValue("DEPT");
        int i = 2;

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "user"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empltable");

            while(resultSet.next()){
                row = sheet8.createRow(i);
                cell = row.createCell(1);
                cell.setCellValue(resultSet.getInt("EMP ID"));
                cell = row.createCell(2);
                cell.setCellValue(resultSet.getString("EMP NAME"));
                cell = row.createCell(3);
                cell.setCellValue("deg");
                cell = row.createCell(4);
                cell.setCellValue(resultSet.getString("salary"));
                cell = row.createCell(5);
                cell.setCellValue(resultSet.getString("dept"));
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream os = new FileOutputStream(new File("createworkbook.xlsx"));
            workbook.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
