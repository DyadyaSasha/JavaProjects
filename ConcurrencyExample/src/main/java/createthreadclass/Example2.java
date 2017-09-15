package createthreadclass;

import java.util.stream.IntStream;

class ThreadExample2 extends Thread {

    private Thread thread;
    private String threadName;

    public static IntStream reverseOrder(int from, int to){
        return IntStream
                .rangeClosed(from, to)
                .map(i -> to - i + from);
    }

    ThreadExample2(String name){
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run(){
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

    @Override
    public void start(){
        System.out.println("Starting " + threadName);
        if(thread == null){
            /**
             * this указывает класс, в котором есть метод run()
             * */
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}

public class Example2{
    public static void main(String[] args){
        ThreadExample2 thread = new ThreadExample2("Thread1");
        thread.start();
        ThreadExample2 thread2 = new ThreadExample2("Thread2");
        thread2.start();
    }
}
