import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

public class Server {

    public void startServer(){

        try {
            WebServer webServer = new WebServer(8080);

            XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();

            PropertyHandlerMapping phm = new PropertyHandlerMapping();
//            phm.load(Thread.currentThread().getContextClassLoader(), "Logic");
            phm.addHandler("sample", new Logic().getClass());

            xmlRpcServer.setHandlerMapping(phm);

//            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
//            serverConfig.setEnabledForExtensions(true);
//            serverConfig.setContentLengthOptional(false);

//            System.out.println("Server starts");

            webServer.start();
            System.out.println("Server started");
        } catch (IOException | XmlRpcException e){
            e.printStackTrace();
        }
    }
}
