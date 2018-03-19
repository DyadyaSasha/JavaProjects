package ws;

import javax.xml.ws.Endpoint;


/**
 * размещаем web-сервис на заданном URL-е
 * данный класс можно использовать, если не хочется поднимать отдельно сервер
 */
public class PublishService {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new ServiceEndpointImpl());
    }

}
