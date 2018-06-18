package firstapp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

/**
 * {@link BeanPostProcessor} определяет методы, вызываемые при создании бина перед инициализацией полей бина и после инициализации полей бина
 * можно определять несколько классов, реализующих {@link BeanPostProcessor}, причём можно указывать порядок применения методов из данных классов с
 * помощью реализации метода getOrder() из интерфейса {@link Ordered}
 */
public class MyBeanPostProcessor implements BeanPostProcessor, Ordered{

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization   " + beanName);
        return null;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization   " + beanName);
        return null;
    }
}
