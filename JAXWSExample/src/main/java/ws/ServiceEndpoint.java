package ws;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * объявляем методы, которые будут в web сервисе(в данном случае SOAP)
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServiceEndpoint {

    @WebMethod
    String getHelloWorldMessage(String message);
}
