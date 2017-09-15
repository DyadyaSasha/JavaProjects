import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public void startClient(){

        try {
            XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
            clientConfig.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc"));
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(clientConfig);
            List<Integer> params = new ArrayList<Integer>();
            params.add(new Integer(17));
            params.add(new Integer(13));

            Object result = client.execute("sample.sum", params);
            int sum = ((Integer) result).intValue();

            System.out.println("Result of remote method: " + sum);
        } catch (MalformedURLException | XmlRpcException e) {
            e.printStackTrace();
        }
    }
}
