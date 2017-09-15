package majoroperations;

class PrintObj{
    public  void printCount(){
        try{
            for(int i = 5; i > 0; i--){
                System.out.println("Counter --- " + i);
            }
        } catch (Exception e){
            System.out.println("Thread interrupted");
        }
    }
}

class ThreadTest extends Thread{
    private Thread thread;
    private String threadName;
    PrintObj PO;

    public ThreadTest(String name, PrintObj po){
        threadName = name;
        PO = po;
    }

    @Override
    public void run(){
        /**
         * когда поток дойдёт до этого блока кода,
         * монитор объекта PO перейдёт из состояния unlock в
         * состояние lock до тех пор пока поток не закончит
         * работу, связанную с этим объектом. Пока монитор объекта находится в
         * состоянии lock ни один другой поток не может начять работу
         * с этим объектом, кроме потока, поставившим lock
         * */
        synchronized (PO){
            PO.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    @Override
    public void start(){
        System.out.println("Starting " + threadName);
        if(thread == null){
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}

public class Synchronization {
    public static void main(String[] args){

        PrintObj po = new PrintObj();

        ThreadTest t = new ThreadTest("Thread1", po);
        ThreadTest t1 = new ThreadTest("Thread2", po);

        t.start();
        t1.start();


        try{
            t.join();
            t1.join();
        } catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }
}
