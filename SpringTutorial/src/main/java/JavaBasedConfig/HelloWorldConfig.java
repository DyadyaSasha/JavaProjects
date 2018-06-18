package JavaBasedConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * {@link Configuration} позволяет описать конфигурацию бинов с помощью Java кода, а не XML файла
 * {@link Import} позволяет импортировать конфигурацию бинов из другого класса конфигурации
 */
@Configuration
@Import(TextEditorConfig.class)
public class HelloWorldConfig {

    /**
     * {@link Bean} указывает, что метод, помеченный этой аннотацией,
     * описывает Spring-бин
     * имя метода является ID бина
     */
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Scope("prototype")
    public HelloWorld helloWorld(){
        return new HelloWorld();
    }
}
