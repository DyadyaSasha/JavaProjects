package JavaBasedConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        /**
         * {@link AnnotationConfigApplicationContext} позволяет создавать контекст с помощью проаннотированного ({@link org.springframework.context.annotation.Configuration}) класса, описывающий бины
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
//        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        helloWorld.setMessage("Hello");
        helloWorld.getMessage();

        ApplicationContext context1 = new AnnotationConfigApplicationContext(TextEditorConfig.class);
//        TextEditor textEditor = context1.getBean(TextEditor.class);
        TextEditor textEditor = context.getBean(TextEditor.class);
        textEditor.spellCheck();

    }
}
