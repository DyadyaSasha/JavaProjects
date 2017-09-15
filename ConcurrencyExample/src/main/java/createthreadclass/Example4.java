package createthreadclass;

import java.util.stream.IntStream;

class ThreadExample4 extends Thread{

    public static IntStream reverseOrder(int from, int to){
        return IntStream
                .rangeClosed(from, to)
                .map(i -> from - i + to);
    }

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
}

public class Example4 {
    public static void main(String[] args){
        Thread thread = new ThreadExample4();
        thread.start();
        Thread thread1 = new ThreadExample4();
        thread1.start();

    }
}
