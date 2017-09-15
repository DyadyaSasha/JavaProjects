
public class Main {
    public static void main(String[] args){

        Thread server = new Thread(() -> new Server().startServer());
        Thread client = new Thread(() -> new Client().startClient());

        server.start();
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        client.start();
    }
}
