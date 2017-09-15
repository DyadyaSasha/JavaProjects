package majoroperations;

class ThreadExample implements Runnable{

    public Thread thread;
    private String threadName;
    boolean suspended;


    public ThreadExample(String name){
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run(){
        System.out.println("Running " + threadName);
        try {
            for(int i = 10; i > 0; i--){
                System.out.println("Thread: " + threadName + ", " + i);
                /**
                 * приостанавливает выполнение потока на 3сек
                 * */
                Thread.sleep(300);
                /**
                 * wait() приостанавливает выполнение ТЕКУЩЕГО потока
                 * (то есть потока, в котором вызван метод wait())
                 * до тех пор, пока другой поток не вызовет
                 * метод notify()
                 * */
                //почему здесь synchronized
                synchronized (this){
                    while (suspended){
                        wait();
                    }
                }
            }
        } catch (InterruptedException e){
            System.out.println("Thread: " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start(){
        System.out.println("Starting " + threadName);
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    //почему здесь нет synchronized
    void suspend(){
        suspended = true;
    }

    /**
     * notify() возобновляет поток, который был приостановлен методом wait()
     * (и у которого есть монитор данного объекта)
     * */
    //почему здесь synchronized
    synchronized  void resume(){
        suspended = false;
        notify();
    }
}

public class Example{

    public static void main(String[] args){
        ThreadExample example = new ThreadExample("Thread1");
        example.start();
        ThreadExample example1 = new ThreadExample("Thread2");
        example1.start();

        try {

            Thread.sleep(1000);
            example.suspend();
            System.out.println("Suspending First Thread");
            Thread.sleep(1000);
            example.resume();
            System.out.println("Resuming First Thread");

            example1.suspend();
            System.out.println("Suspending thread Two");
            Thread.sleep(1000);
            example1.resume();
            System.out.println("Resuming thread Two");

        } catch (InterruptedException e){
            System.out.println("Main thread Interrupted");
        } try {
            System.out.println("Waiting for threads to finish.");
            /**
             * метод join() приостаналивает выполнение текущего (например main потока)
             * потока до тех пор, пока побочный поток, вызвавший этот метод не завершиться;
             * если потоки не демоны, то главный поток будет ждать их
             * завершения без метода join(), в противном случае требуется
             * join()
             * */
            example.thread.join();
            example1.thread.join();
        } catch (InterruptedException e){
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }
}
