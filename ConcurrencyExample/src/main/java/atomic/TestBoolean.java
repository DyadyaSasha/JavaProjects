package atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestBoolean {

    public static void main(String[] args){
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        /**
         * анонимный класс
         * */
        new Thread("Thread 1"){
            @Override
            public void run(){
                while (true){
                    System.out.println(Thread.currentThread().getName() +
                    " Waiting for Thread 2 to set Atomic variable to true. Current value is " +
                    atomicBoolean.get());
                    if (atomicBoolean.compareAndSet(true, false)){
                        System.out.println("Done!");
                        /**
                         * выходим из цикла
                         * */
                        break;
                    }
                }
            }
        }.start();

        new Thread("Thread 2"){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " + atomicBoolean.get());
                System.out.println(Thread.currentThread().getName() + " is setting the variable to true ");
                atomicBoolean.set(true);
                System.out.println(Thread.currentThread().getName() + ", Atomic Variable: " + atomicBoolean.get());
            }
        }.start();
    }
}
