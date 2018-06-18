package eventHandling;

import org.springframework.context.ApplicationEvent;

/**
 * {@link ApplicationEvent} абстрактный класс, представляющий возможность реализовывать свои пользовательские события
 */
public class CustomEvent extends ApplicationEvent {

    public CustomEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "CustomEvent Class";
    }
}
