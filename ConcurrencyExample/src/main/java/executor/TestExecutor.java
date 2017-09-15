package executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExecutor {
    public static void main(String[] args){
        /**
         * {@link Executors} представляет ряд статических методов для получения
         * разных видов пулов потоков
         * */
        Executor executor = Executors.newCachedThreadPool();
        /**
         * {@link Executor} предоставляет всего один метод execute()
         * для запуска задач в новом потоке
         * */
        executor.execute(new Task());
        /**
         * {@link ThreadPoolExecutor}
         * */
        ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
        pool.shutdown();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                Long duration = (long) (Math.random()*5);
                System.out.println("Running Task!");
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task completed!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
