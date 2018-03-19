package ws;

import javax.jws.WebService;

/**
 * реализуем методы web-сервиса
 */
@WebService(endpointInterface = "ws.ServiceEndpoint")
public class ServiceEndpointImpl implements ServiceEndpoint{

    @Override
    public String getHelloWorldMessage(String message) {
        return("Hello " + message + " to JAX WS world");
    }
}
