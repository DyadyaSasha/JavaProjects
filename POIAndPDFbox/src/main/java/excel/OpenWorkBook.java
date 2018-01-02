package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class OpenWorkBook {

    public static void main(String[] args) {
        try {
            File file = new File("createworkbook.xlsx");
            FileInputStream is = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(is);
//
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            XSSFRow row;

            while (iterator.hasNext()){
                row = (XSSFRow) iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();

                    switch (cell.getCellTypeEnum()){
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t ");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
