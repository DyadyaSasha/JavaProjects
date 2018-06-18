package annotationQualifierConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQualifier {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Qualifier.xml");
        Profile profile = (Profile) context.getBean("profile");
        profile.printAge();
        profile.printName();
    }
}
