package createthreadclass;

import java.util.stream.IntStream;

class ThreadExample3 implements Runnable{

    public static IntStream reverseOrder(int from, int to){
        return IntStream
                .rangeClosed(from, to)
                .map(i -> from - i + to);
    }

    @Override
    public void run(){
        String threadName = Thread.currentThread().getName();
        System.out.println("Running " + threadName);
        reverseOrder(1, 4)
                .forEach(i -> {
                    System.out.println("Thread: " + threadName + ", " + i);
                    try{
                        Thread.sleep(50);
                    } catch(InterruptedException e){
                        System.out.println("Thread: " + threadName + " interrupted.");
                    }
                });
        System.out.println("Thread: " + threadName + " exiting.");
    }
}

public class Example3  {

    public static void main(String[] args){
        /**
         * если у объекта типа Runnable вызвать run(), то параллелного выполнения
         * не будет, метод run() выполниться в том же потоке, который его вызвал,
         * для того, чтобы добиться параллельного выполнения, нужно засунуть
         * в конструктор класса Thread  этот объект типа Runnable и вызвать
         * у объекта типа Thread метод start(), который в свою очередь вызовет
         * метод run() в параллельном потоке
         * именно метод start() говорит потоку выполняться параллельно
         */
        Thread runnable = new Thread(new ThreadExample3());
        /**
         * если запускать через run(), то метод Thread.currentThread.getName()
         * будет выводить имя потока, из которого вызван run(),
         * а если через start(), то будет выведено для потока своё имя
         * */
        runnable.run();
//        runnable.run();
        /**
         * если два раза подряд у объекта нити вызвать метод start(), то
         * будет ошибка {@link IllegalThreadStateException} (даже если между методами
         * start() есть метод join() на том же объекте ???почему join() не помогает???),
         * а если через run(), то ошибки не будет и программа выполнит задачу
         * */
        runnable.start();
//        try {
//            runnable.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        runnable.start();
        Runnable runnable1 = new ThreadExample3();
        runnable1.run();

    }


}
