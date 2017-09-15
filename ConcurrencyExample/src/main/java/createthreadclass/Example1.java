package createthreadclass;

import java.util.stream.IntStream;

 class ThreadExample1 implements Runnable {

    private Thread thread;
    private String threadName;

    static IntStream reverseOrder(int from, int to){
        return IntStream
                .rangeClosed(from, to)
                .map(i -> to - i + from);
    }

    public ThreadExample1(String threadName){
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    public void run(){
        System.out.println("Running " + threadName);
        reverseOrder(1, 4).forEach(i -> {
            System.out.println("Thread: " + threadName + "," + i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                System.out.println("Thread: " + threadName + " interrupted.");
            }
        });
        System.out.println("Thread: " + threadName + " exiting.");
    }

    public void start(){
        System.out.println("Starting " + threadName);
        if (thread == null){
            /**
             * this указывает класс, в котором есть метод run()
             * */
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}

public class Example1 {
     public static void main(String[] args){
         ThreadExample1 t = new ThreadExample1("ThreadExample1");
         t.start();
         ThreadExample1 t1 = new ThreadExample1("ThreadExample2");
         t1.start();
     }
}