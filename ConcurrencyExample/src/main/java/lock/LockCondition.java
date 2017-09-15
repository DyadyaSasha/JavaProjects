package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockCondition {
    public static void main(String[] args){

        ItemQueue itemQueue = new ItemQueue(10);

        Thread producer = new Producer(itemQueue);
        Thread consumer = new Consumer(itemQueue);

        producer.start();
        consumer.start();


        try {
            /**
             * приостанавливаем главный поток, чтобы завершились
             * потоки producer и consumer
             * */
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ItemQueue{

        private Object[] items = null;
        private int current = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private final Lock lock;
        private final Condition isEmpty;
        private final Condition isFull;

        public ItemQueue(int capacity){

            this.items = new Object[capacity];
            lock = new ReentrantLock();

            /**
             * У объектов типа Lock можно вызвать объект типа {@link Condition},
             * который используется для приостановки(метод await()) выполнения потока,
             * в котором вызван метод await(), пока условие истинно(например в цикле while);
             * а также для возобновления этого потока, используя метод signal(),
             * который может быть ввызван из другого потока, но обязательно
             * его должен вызвать тот же объект типа {@link Condition}, который вызвал метод await()
             * */
            isEmpty = lock.newCondition();
            isFull = lock.newCondition();
        }


        /**
         * путём использования объектов типа {@link Condition} isFull и isEmpty
         * происходит взаимодействие между собой потоков(нитей) {@link Producer}
         * и {@link Consumer}
         * */
        public void add(Object item) throws InterruptedException {
            lock.lock();
            while (current >= items.length) {
                isFull.await();
                System.out.println(Thread.currentThread().getName() + " wait");
            }
                items[placeIndex] = item;
                placeIndex = (placeIndex + 1) % items.length;
                ++current;
                isEmpty.signal();
                lock.unlock();
        }

        public Object remove() throws InterruptedException{
            Object item = null;
            /**
             * если с помощью lock() нить не может сразу получить блокировку,
             * то она ждёт освобождения блокировки и ничего не делает(как в случае с synchronized),
             * чтобы избежать простаивания нити можно использовать tryLock(), который вернёт true, если
             * блокировку получилось мгновенно получить(при условии, что во время получения блокировки
             * нить была свободна) и false в противном случае
             * */
            lock.lock();
            while (current <= 0){
                    isEmpty.await();
                System.out.println(Thread.currentThread().getName() + " wait");
                }
            item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --current;
            isFull.signal();
            lock.unlock();
            return item;
        }

        public boolean isEmpty(){
            return (items.length == 0);
        }
    }

    static class Producer extends Thread{

        private final ItemQueue queue;

        public Producer(ItemQueue queue) {
            super("Thread-Producer");
            this.queue = queue;
        }

        @Override
        public void run(){

            String[] numbers = {
                    "1", "2", "3", "4", "5",
                    "6", "7", "8", "9", "10",
                    "11", "12"
            };

            try {
                for (String number : numbers) {
                    queue.add(number);
                    System.out.println("[Producer]: " + number);
                }
                queue.add(null);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread{

        private final ItemQueue queue;

        public Consumer(ItemQueue queue) {
            super("Thread-Consumer");
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                do{
                    Object number = queue.remove();
                    System.out.println("[Consumer]: " + number);
                    if(number == null){
                        return;
                    }
                } while (!queue.isEmpty());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
