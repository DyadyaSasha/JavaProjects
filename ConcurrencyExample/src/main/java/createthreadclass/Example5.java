package createthreadclass;

import java.util.stream.IntStream;

public class Example5 {

    public static IntStream reverseOrder(int from, int to){
        return IntStream
                .rangeClosed(from, to)
                .map(i -> from - i + to);
    }

    public static void main(String[] args){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                System.out.println("Running " + threadName);
                reverseOrder(1, 4)
                        .forEach(i -> {
                            System.out.println("Thread: " + threadName + ", " + i);
                            try{
                                Thread.sleep(50);
                            } catch(InterruptedException e){
                                System.out.println("Thread: " + threadName + " interrupted.");
                            }
                        });
                System.out.println("Thread: " + threadName + " exiting.");
            }
        });
        thread.start();

        new Thread(() -> {String threadName = Thread.currentThread().getName();
            System.out.println("Running " + threadName);
            reverseOrder(1, 4)
                    .forEach(i -> {
                        System.out.println("Thread: " + threadName + ", " + i);
                        try{
                            Thread.sleep(50);
                        } catch(InterruptedException e){
                            System.out.println("Thread: " + threadName + " interrupted.");
                        }
                    });
            System.out.println("Thread: " + threadName + " exiting.");}).start();

    }
}
