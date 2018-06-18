package firstapp;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * интерфейсы {@link InitializingBean} и {@link DisposableBean} реализуют методы, которые вызываются при инициализации и удаления бина из контейнера,
 * методы в этих интерфейсах аналогичны методам, определяемым в файле конфигурации бина в атрибутах init-method и destroy-method
 */
public class PrototypeBean implements InitializingBean, DisposableBean{

    private String name;

    public void setName(String name){
        this.name = name;
    }

    public void getName(){
        System.out.println(name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Prototype Bean destroy method");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Prototype Bean init method");
    }
}
