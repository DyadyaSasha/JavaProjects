package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * реализация только через блок synchronzed
 * здесь {@link majoroperations.Synchronization}
 * */

class PrintObj{

    private final Lock queueLock = new ReentrantLock();

    public void  print(){
        queueLock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.println(Thread.currentThread().getName() +
            " Time Taken " + (duration/1000) + " seconds.");
            Thread.sleep(duration);
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            System.out.printf("%s printed the document successfully\n", Thread.currentThread());
            queueLock.unlock();
        }
    }
}


class ThreadLock extends Thread{

    PrintObj po;

    public ThreadLock(String name, PrintObj po){
        super(name);
        this.po = po;
    }

    @Override
    public void run(){
        System.out.printf("%s starts printing a document\n", Thread.currentThread());
        po.print();
    }
}
public class LockExample {

    public static void main(String[] args) {

        PrintObj po = new PrintObj();

        ThreadLock t1 = new ThreadLock("Thread1", po);
        ThreadLock t2 = new ThreadLock("Thread2", po);
        ThreadLock t3 = new ThreadLock("Thread3", po);
        ThreadLock t4 = new ThreadLock("Thread4", po);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
