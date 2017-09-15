package lock;
;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * ReadWriteLock даёт возможность получить блокировку и читать разделяемый ресурс
 * сразу нескольким потокам либо изменять только одному потоку
 *
 * правила:
 * для Read Lock - если ни один поток не заблокировал(lock) ресурс на запись,
 * то сразу несколько нитей могут получить блокировку на разделяемый ресурс
 * и читать его
 * для Write Lock - если нет ни одного читающего или пишущего в разделяемый ресурс, то
 * только один поток может получить блокировку на запись
 * */
public class ReentrantLockExample {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static  String message = "a";

    public static void main(String[] args){

        Thread t1 = new Thread(new WriterA());
        t1.setName("WriterA");
        Thread t2 = new Thread(new WriterB(), "WriterB");
        Thread t3 = new Thread(new Reader(), "Reader");

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    static class Reader implements Runnable{

        @Override
        public void run() {
            if(lock.isWriteLocked()){
                System.out.println("write Lock Present");
            }
            lock.readLock().lock();
            try{
                Long duration = (long) (Math.random()*10000);
                System.out.println(Thread.currentThread().getName() +
                 " Time Taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + ": " + message);
                lock.readLock().unlock();
            }
        }
    }

    static class WriterA implements Runnable{

        @Override
        public void run(){
            lock.writeLock().lock();
            try{
                Long duration = (long) (Math.random()*10000);
                System.out.println(Thread.currentThread().getName() +
                        " Time Taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                message = message.concat("a");
                lock.writeLock().unlock();
            }
        }
    }

    static class WriterB implements Runnable{

        @Override
        public void run(){
            lock.writeLock().lock();
            try{
                Long duration = (long) (Math.random()*10000);
                System.out.println(Thread.currentThread().getName() +
                        " Time Taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                message = message.concat("b");
                lock.writeLock().unlock();
            }
        }
    }

}
