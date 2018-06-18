package annotationAutowiredConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutowired {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Autowired.xml");
        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();

    }
}
