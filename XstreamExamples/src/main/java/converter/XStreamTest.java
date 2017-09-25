package converter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
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

public class XStreamTest {
    public static void main(String[] args) {

        XStreamTest tester = new XStreamTest();
        XStream xStream = new XStream(new StaxDriver());
        Student student = tester.getStudentDetails();

        XStream xStream1 = new XStream(new StaxDriver());

        xStream.autodetectAnnotations(true);
        xStream1.autodetectAnnotations(true);

        /**
         * указываем, какой конвертер(в котором указано, как сериализовать объект)
         * использовать при сериализации
         * */
        xStream.registerConverter(new NameConveter());
        xStream1.registerConverter(new StudentConverter());

        String xml = xStream.toXML(student);
        System.out.println("NameConverter:\n" + formatXml(xml));

        xml = xStream1.toXML(student);
        System.out.println("StudentConverter:\n" + formatXml(xml));
    }
    private Student getStudentDetails(){
        Student student = new Student("Mahesh","Parashar");
        return student;
    }

    public static String formatXml(String xml){

        try{

            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();

            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
            StreamResult res =  new StreamResult(new ByteArrayOutputStream());

            serializer.transform(xmlSource, res);

            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());

        }catch(Exception e){
            return xml;
        }
    }
}
