package annotationJSRConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJSR {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("JSR.xml");
        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        helloWorld.getMessage();
        helloWorld.getSpellChecker().checkSpelling();
        ((AbstractApplicationContext)context).registerShutdownHook();
    }
}
