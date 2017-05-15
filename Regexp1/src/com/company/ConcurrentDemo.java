package com.company;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by user on 05.05.17.
 */
public class ConcurrentDemo {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static volatile  int volotileInt = 0;

    static long currentTime;

    public static void main(String... args) throws InterruptedException{
        System.out.println("Starting threads...");
        currentTime = System.currentTimeMillis();

        Thread t1 = new Thread(new ConcurrentRunnableDemo());
        t1.start();
        Thread t2 = new Thread(new ConcurrentRunnableDemo());
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Finished in "+(System.currentTimeMillis()-currentTime) + "ms");
        System.out.println("Atomic: " + atomicInteger.get() + "; Volatile: " + volotileInt);
    }

    static class ConcurrentRunnableDemo implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 200000000; i++){
                ConcurrentDemo.volotileInt++;
                ConcurrentDemo.atomicInteger.incrementAndGet();
            }
        }
    }
}
