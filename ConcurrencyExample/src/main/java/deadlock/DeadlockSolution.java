package deadlock;

public class DeadlockSolution {
    private static Object Lock1 = new Object();
    private static Object Lock2 = new Object();

    public static void main(String[] argss){
        Thread1 t = new Thread1();
        Thread2 t1 = new Thread2();

        t.start();
        t1.start();
    }

    private static class Thread1 extends Thread{
        @Override
        public void run(){
            synchronized (Lock1){
                System.out.println("Thread 1: Holding lock 1");
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    System.out.println("Thread1 interrupted");
                }
                System.out.println("Thread 1: Waiting for lock 2");

                synchronized (Lock2){
                    System.out.println("Thread 1: Holding lock 1 and 2");
                }
            }
        }
    }


    private static class Thread2 extends Thread{
        @Override
        public void run(){
            synchronized (Lock1){
                System.out.println("Thread 2: Holding lock 1");
                try{
                    Thread.sleep(10);
                } catch (InterruptedException e){
                    System.out.println("Thread2 interrupted");
                }
                System.out.println("Thread 2: Waiting for lock 2");

                synchronized (Lock2){
                    System.out.println("Thread 2: Holding lock 1 and 2");
                }
            }
        }
    }
}
