import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public void startClient(){

        try {

//           null - using localhost, server listens requests in localhost
           Registry registry = LocateRegistry.getRegistry(null, 8080);

//            Registry registry = LocateRegistry.getRegistry(null);
//          returns object which registered in RMIregistry by server
            RemoteInt stub = (RemoteInt) registry.lookup("service");

            stub.printMsg(5, 6);

        } catch (RemoteException | NotBoundException e ){
            e.printStackTrace();
        }

    }
}
