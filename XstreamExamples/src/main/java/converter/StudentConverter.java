package converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class StudentConverter implements Converter{


    /**
     * сериализация в XML
     * */
    @Override
    public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
        Student student = (Student) object;
        writer.startNode("name");
        writer.setValue(student.getName().getFirstName() + "," + student.getName().getLastName());
        writer.endNode();
    }

    /**
     * десириализация в объект
     * */
    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        /**
         * выбирает текущего ребёнка в качестве корневого узла
         * должно использоваться вместе с moveUp()
         * */
        reader.moveDown();
        String[] nameParts = reader.getValue().split(",");
        Student student = new Student(nameParts[0], nameParts[1]);

        /**
         * выбирает родительский узел в качестве текущего
         * */
        reader.moveUp();
        return student;
    }

    /**
     * проверка класса на поддежку сериализации
     * */
    @Override
    public boolean canConvert(Class aClass) {
        return aClass.equals(Student.class);
    }
}
