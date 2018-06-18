package annotationQualifierConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Profile {

    @Autowired
    /**
     * {@link Qualifier} позволяет взять нужную реализацию бина из xml конфига(в нём две реализации бинов одного типа: student1, student2)
     */
    @Qualifier("student1")
    private Student student;

    public Profile(){
        System.out.println("Inside Profile constructor." );
    }
    public void printAge() {
        System.out.println("Age : " + student.getAge() );
    }
    public void printName() {
        System.out.println("Name : " + student.getName() );
    }

}
