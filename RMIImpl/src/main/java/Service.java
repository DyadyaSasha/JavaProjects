import java.rmi.RemoteException;

public class Service implements RemoteInt {

    public void printMsg(int a, int b) throws RemoteException {
        System.out.println("Message from server: a + b = " + (a + b));
    }
}
