
public class Main {
    public static void main(String[] args){

        Thread thread = new Thread(() -> System.out.println(3+2));
        Thread server = new Thread(() -> new Server().startServer());
        Thread client = new Thread(() -> new Client().startClient());
        thread.start();
        server.start();
//        wait while server starts
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        client.start();

    }
}
