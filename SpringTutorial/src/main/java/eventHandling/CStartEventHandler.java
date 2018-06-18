package eventHandling;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * {@link ApplicationListener} интерфейс слушателя событий, принимающий конкретное событие и обеспечивающий логику
 * обработки события в методе onApplicationEvent()
 */
public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    /**
     *
     */
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println("ContextStartedEvent Received");
    }
}
