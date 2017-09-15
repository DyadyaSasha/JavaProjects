package atomic;


/**
 * классы типа Atomic предоставляют потокобезопасные
 * обертки над примитивами и объектами в том числе массивами
 * В основе их работы лежит работа с соответствующими
 * volatile значениями
 * */
import java.util.concurrent.atomic.AtomicInteger;

public class TestInteger {

    static class Counter {

        private AtomicInteger counter = new AtomicInteger(0);

        public void increment() {
            counter.getAndIncrement();
        }

        public int value() {
            return counter.get();
        }
    }

        public static void main(String[] args) throws InterruptedException{
            /**
             * делаем финальным, чтобы обезопасить во
             * впемя выполнения
             * */
            final Counter counter = new Counter();
            for(int i = 0; i < 1000; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        counter.increment();
                    }
                }).start();
            }

            /**
             * Почему если закомментировать sleep() число будет 999 или 997,
             * а не 1000? Почему главный поток не ждёт завершения потоков недемонов?
             * */
//                Thread.sleep(3000);
                System.out.println("Final number (should be 1000): " +
                counter.value());
        }
}