package annotationRequiredConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRequired {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Required.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.getName() + " " + student.getAge());
    }
}
