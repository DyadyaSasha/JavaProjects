package annotations;

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

public class ClassAnnotation {

    public static void main(String[] args) {

        ClassAnnotation tester = new ClassAnnotation();
        XStream xStream = new XStream(new StaxDriver());
        Student student = tester.getStudentDetails();

        /**
         * processAnnotations() говорим, что в классе Student
         * проанотированы поля для сериализации, если не использовать
         * данный метод, то сериализатор не будет использовать информаию
         * в аннотациях при сериализации класса
         * */
        xStream.processAnnotations(Student.class);

        /**
         * или(будет использовано для всех классов, которые сериализуются):
         * */
        xStream.autodetectAnnotations(true);

        String xml = xStream.toXML(student);

        try {
            System.out.println(tester.formatXML(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Student getStudentDetails(){
        Student student = new Student("Mahesh");
        student.addNote(new Note("first","My first assignment."));
        student.addNote(new Note("second","My Second assignment."));
        student.setType(1);
        return student;
    }

    private String formatXML(String xml) throws Exception{
        Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
        StreamResult res = new StreamResult(new ByteArrayOutputStream());
        serializer.transform(xmlSource, res);
        return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
    }
}
