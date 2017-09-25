import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

import java.io.Writer;

public class XstreamJSON {

    public static void main(String[] args) {
        XstreamJSON tester = new XstreamJSON();
        XStream xStream = new XStream(new JsonHierarchicalStreamDriver(){
            public HierarchicalStreamWriter createWriter(Writer writer) {
                /**
                 * DROP_ROOT_MODE позволяет не писать корневой
                 * узел при сериализации
                 * */
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        StudentJSON student = new StudentJSON("Mahesh","Parashar");

        /**
         * рассматривает структуру объекта как дерево
         * дублирующие ссылки - два разных объекта
         * круговые ссылки порождают исключение
         *
         * работает намного быстрее остальных
         * */
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("student", StudentJSON.class);

        System.out.println(xStream.toXML(student));
    }
}
@XStreamAlias("student")
class StudentJSON {

    private String firstName;
    private String lastName;

    public StudentJSON(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString(){
        return "Student [ firstName: "+firstName+", lastName: "+ lastName+ " ]";
    }
}