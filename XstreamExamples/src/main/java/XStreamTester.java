import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class XStreamTester {
    public static void main(String[] args) {

        XStreamTester tester = new XStreamTester();

        /**
         * {@link XStream} представляет инструменты для сеиализации
         * данных используя STAX API для чтения и записи
         * */
        XStream xStream = new XStream(new StaxDriver());

        Student student = tester.getStudentDetails();

        /**
         * сериализуем объект в строку в формате XML
         * */
        String xml = xStream.toXML(student);
        try {
            System.out.println(tester.formatXml(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * десириализует из строки в XML формате в Object
         * */
        Student student1 = (Student) xStream.fromXML(xml);

        System.out.println(student1);

    }

    private Student getStudentDetails(){
        Student student = new Student();
        student.setFirstName("Mahesh");
        student.setLastName("Parashar");
        student.setRollNo(1);
        student.setClassName("1st");

        Address address = new Address();

        address.setArea("H.No. 16/3, Preet Vihar.");
        address.setCity("Delhi");
        address.setState("Delhi");
        address.setCountry("India");
        address.setPincode(110012);

        student.setAddress(address);
        return student;
    }
    private String formatXml(String xml) throws Exception{
        /**
         * получаем объект для сериализации из фабрики
         * */
        Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

        /**
         * задаём шаблон вывода
         * */
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");

        /**
         * шаблон для отсупов
         * */
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        /**
         * Переводим строку в SAXSource,
         * через который можно преобразовать строку в удобочитаемый формат
         * */
        Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));

        /**
         * результат будет записываться в объект класса {@link StreamResult}
         * */
        StreamResult res = new StreamResult(new ByteArrayOutputStream());

        /**
         * трансформируем XML строку в удобочитаемый формат
         * */
        serializer.transform(xmlSource, res);

        return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
    }
}
