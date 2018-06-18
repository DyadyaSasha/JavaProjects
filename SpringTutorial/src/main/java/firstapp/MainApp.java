package firstapp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;

public class MainApp {

    public static final Log log = LogFactory.getLog(MainApp.class.getName());


    public static void main(String[] args) {
        /**
         * контекст приложения позволяет создавать и получать необходимые ресурсы, в том числе спринговские бины, которые
         * описаны в XML файле(в данном случае Beans.xml)
         * с помощью контекста регистрируются необходимые ресурсы в IoC контейнере
         * Context представляет собой контейнер с бинами и ресурсами
         * {@link ClassPathXmlApplicationContext} использует xml конфигурацию бинов из CLASSPATH
         * Наиболее используемые {@link ApplicationContext} реализации:
         *  {@link org.springframework.context.support.FileSystemXmlApplicationContext} - данный контейнер(контекст) загружает/регистрирует бины из XML файла(для данного контекста нужно указывать полный путь до xml конфигурации бинов)
         *  {@link ClassPathXmlApplicationContext} - использует xml конфигурацию бинов из CLASSPATH
         *  {@link WebApplicationContext} - ????
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        log.info("Going to create HelloWord Obj");
        /**
         * также как и {@link ApplicationContext} позволяет зарегистрировать бины, предтавленные в Xml файле
         * {@link XmlBeanFactory} рекомендуется использовать в легковесных приложениях, в остальных случаях предпочтителен {@link ApplicationContext}
         * {@link ClassPathResource} считывает файл из CLASSPATH
         */
        XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("Beans.xml"));

        /**
         * с помощью метода контекста getBean() получаем объект бина по его id(при этом возвращаемый объект имеет тип Object)
         * это происходит при помощи встроенного в фреймворк DI контейнера
         */
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        obj.setMessage("singleton");
        /**
         * так как бин имеет singleton scope, то при вызове метода getBean()
         * создастся не новый объект бина, а будет возвращена ссылка на этот бин из кеша
         */
        HelloWorld obj1 = (HelloWorld) context.getBean("helloWorld");
        obj1.getMessage();
        /**
         * вернёт true
         */
        System.out.println(obj == obj1);

        HelloWorld obj2 = (HelloWorld) factory.getBean("helloWorld");
        obj2.getMessage();

        PrototypeBean prototypeBean = context.getBean("prototypeBean", PrototypeBean.class);
        prototypeBean.setName("хфхфхф");
        prototypeBean.getName();

        PrototypeBean prototypeBean1 = (PrototypeBean) context.getBean("prototypeBean");
        prototypeBean1.getName();

        /**
         * registerShutdownHook() позволяет корректно высвободить ресурсы бинов и вызвать их destroy-методы
         */
        ((AbstractApplicationContext) context).registerShutdownHook();

        log.info("Exiting the program");
    }

}
