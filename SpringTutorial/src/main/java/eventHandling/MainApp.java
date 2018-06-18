package eventHandling;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        /**
         * {@link ConfigurableApplicationContext}
         */
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfig.class);
        HelloWorld helloWorld = context.getBean(HelloWorld.class);
        /**
         *
         */
        context.start();
        helloWorld.setMessage("Hello");
        helloWorld.getMessage();
        /**
         *
         */
        CustomEventPublisher publisher = (CustomEventPublisher) context.getBean(CustomEventPublisher.class);
        publisher.publish();

        /**
         *
         */
        context.stop();
    }
}
