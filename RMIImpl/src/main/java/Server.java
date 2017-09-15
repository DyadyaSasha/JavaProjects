import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public void startServer(){

        Service service = new Service();
        try {
            RemoteInt stub = (RemoteInt) UnicastRemoteObject.exportObject(service, 0);
//          getRegistry() returns registry in default for RMI port
//            Registry registry = LocateRegistry.getRegistry();
//          create RMIRegistry
            Registry registry = LocateRegistry.createRegistry(8080);

//          register object in RMIregistry stub type is interface
            registry.bind("service", stub);

//            Also, if use method getRegistry(),
//            need in folder with server, client and other classes
//            execute terminal command "rmiregistry & [port]" to start RMIregistry
        } catch (RemoteException | AlreadyBoundException e){
            e.printStackTrace();
        }





    }
}
