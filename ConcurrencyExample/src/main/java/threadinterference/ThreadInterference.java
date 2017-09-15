package threadinterference;

/**
 * потоки обращаются к разделяемому ресурсу без
 * синхронизации, то есть нельзя предсказать, какой
 * поток в какое время изменил разделяемый ресурс,
 * обращение потоками к ресурсу происходит хаотично,
 * что приводит к непредсказуемому результату
 * */
public class ThreadInterference {

    public static void main(String[] args){
        Counter counter = new Counter();
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + counter.value());
            counter.increment();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " " + counter.value());
        });

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + counter.value());
            counter.decrement();
            System.out.println(Thread.currentThread().getName() + " " + counter.value());
        });

        thread.start();
        thread1.start();
    }

    static class Counter {
        private int c = 0;

        public void increment() {
            c++;
        }

        public void decrement() {
            c--;
        }

        public int value() {
            return c;
        }

    }
}
