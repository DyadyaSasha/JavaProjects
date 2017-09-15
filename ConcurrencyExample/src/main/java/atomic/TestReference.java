package atomic;

import java.util.concurrent.atomic.AtomicReference;


/**
 * {@link AtomicReference} помогает работать с объектами так будто
 * объекты являются volatile
 * */
public class TestReference {

    private static String message = "hello";
    private static AtomicReference<String> atomicReference;

    public static void main(String[] args){
        atomicReference = new AtomicReference<>(message);
        new Thread("Thread 1"){
            @Override
            public void run() {
                atomicReference.compareAndSet(message, "Thread 1");
                message = message.concat("-Thread 1!");
//                System.out.println(message);
            }
        }.start();

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        /**
         * если не подождать завершения работы потока,
         * то в первой строчке выведется "hello", если подождать, то
         * "hello-Thread 1"
         * */
        System.out.println("Messsage is: " + message);
        System.out.println("Atomic Reference of Message is: " + atomicReference.get());
    }
}
