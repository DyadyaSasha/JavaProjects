package eventHandling;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfig {

    @Bean
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }


    /**
     * регистрируем бины обработчиков событий контекста в контексте приложения
     */
    @Bean
    public CStartEventHandler cStartEventHandler(){
        return new CStartEventHandler();
    }

    @Bean
    public CStopEventHandler cStopEventHandler(){
        return new CStopEventHandler();
    }

    @Bean
    public CustomEventHandler customEventHandler(){
        return new CustomEventHandler();
    }

    /**
     *
     */
    @Bean
    public CustomEventPublisher customEventPublisher(){
        return new CustomEventPublisher();
    }
}
