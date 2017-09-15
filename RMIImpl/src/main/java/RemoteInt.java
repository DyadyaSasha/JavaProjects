import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInt extends Remote {
    void printMsg(int a, int b) throws RemoteException;
}
