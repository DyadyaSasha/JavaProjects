package saxonXQuery;

import com.saxonica.xqj.SaxonXQDataSource;

import javax.xml.xquery.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XQueryTester {
    public static void main(String[] args) {
        try {
            execute();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (XQException e) {
            e.printStackTrace();
        }
    }

    private static void execute() throws FileNotFoundException, XQException {
        /**
         * подключаем файл с XQuery запросом
         */
        InputStream is = new FileInputStream(System.getProperty("user.dir") + "\\books.xqy");
        XQDataSource ds = new SaxonXQDataSource();
        XQConnection conn = ds.getConnection();
        /**
         * подгатавливаем запрос(файл, к которому выполняется запрос указан в books.xqy)
         */
        XQPreparedExpression exp = conn.prepareExpression(is);
        /**
         * выполняем запрос
         */
        XQResultSequence result = exp.executeQuery();
        /**
         * выводим результаты запроса
         */
        while (result.next()) {
            System.out.println(result.getItemAsString(null));
        }

        System.out.println("\n");


        is = new FileInputStream(System.getProperty("user.dir") + "\\books1.xqy");
        exp = conn.prepareExpression(is);
        result = exp.executeQuery();
        while (result.next()){
            System.out.println(result.getItemAsString(null));
        }

        System.out.println("\n");


        is = new FileInputStream(System.getProperty("user.dir") + "\\seq.xqy");
        exp = conn.prepareExpression(is);
        result = exp.executeQuery();
        while (result.next()){
            System.out.println(result.getItemAsString(null));
        }

        System.out.println("\n");

        is = new FileInputStream(System.getProperty("user.dir") + "\\customfunc.xqy");
        exp = conn.prepareExpression(is);
        result = exp.executeQuery();
        while (result.next()){
            System.out.println(result.getItemAsString(null));
        }
        System.out.println("\n");
    }
}
