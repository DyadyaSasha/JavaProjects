package aliasing;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.xml.sax.InputSource;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ClassAlias {

    public static void main(String[] args) {

        ClassAlias tester = new ClassAlias();
        XStream xStream = new XStream(new StaxDriver());

        /**
         * aliasPackage() позволяет поменять имя пакета, которое будет отображаться
         * при описании класса в XML элементе(будет отображаться в XML
         * документе при условии, что классу не задан псевдоним(alias) с
         * помощью метода alias())
         * если имя реального пакета задано неверно, то будет отображаться
         * полное(реальное) имя класса
         * */
        xStream.aliasPackage("new.package.aliasing", "aliasing");

        /**
         * alias() позволяет поменять имена XML элементов(узлов дерева),
         * представляющих классы
         * */
//        xStream.alias("studentAlias", Student.class);
        xStream.alias("noteAlias", Note.class);

        /**
         * useAttributeFor() используется, чтобы при сериализации в XML
         * указанное поле оказалось в атрибуте корневого элемента,
         * описывающего класс
         * */
        xStream.useAttributeFor(Student.class, "studentName");

        /**
         * aliasField()  позволяет поменять имена XML элементов(узлов дерева),
         * представляющих поля класса
         * */
        xStream.aliasField("name", Student.class, "studentNameAlias");

        /**
         * addImplicitCollection() используется, когда коллекция должна быть
         * представлена ​​в XML без отображения корневого элемента
         * (в данном случае <notes></notes>)
         * */
        xStream.addImplicitCollection(Student.class, "notes");





        Student student = tester.getStudentDetails();

        String xml = xStream.toXML(student);
        System.out.println(tester.formatXml(xml));

    }


    private Student getStudentDetails(){

        Student student = new Student("Mahesh");

        student.addNote(new Note("first","My first assignment."));
        student.addNote(new Note("second","My Second assignment."));

        return student;
    }
    private String formatXml(String xml) {

        StreamResult res = null;
        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            res = new StreamResult(new ByteArrayOutputStream());

            serializer.transform(xmlSource, res);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
    }
}
