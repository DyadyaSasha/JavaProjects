package eventHandling;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

/**
 * {@link ApplicationEventPublisherAware} требуется, чтобы создать событие и опубликовать(выполнить) его
 */
public class CustomEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }


    public void publish(){
        CustomEvent customEvent = new CustomEvent(this);
        /**
         * publishEvent() даёт знать всем слушателям (listeners), что есть такое событие - customEvent
         */
        publisher.publishEvent(customEvent);
    }
}
