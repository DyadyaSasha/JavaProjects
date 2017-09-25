package converter;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("student")
public class Student {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private Name studentName;

    public Student(String firstName, String lastName){
        this.studentName = new Name(firstName, lastName);
    }

    public Name getName(){
        return studentName;
    }
}
