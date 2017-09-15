package threadlocal;

import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;
/**
 * класс {@link ThreadLocalRandom} используется когда нескольким потокам или классу {@link ForkJoinTask}
 * требуется генерироватьь рэндомные числа
 * */
public class TestThreadRandom {

    public static void main(String[] args){
        System.out.println("Random Integder: " + new Random().nextInt());
        System.out.println("Seeded Random Integer: " + new Random(15).nextInt());
        System.out.println("Thread Local Random Integer: " + ThreadLocalRandom.current().nextInt());
        ThreadLocalRandom random = ThreadLocalRandom.current();

        /**
         * данный метод можно использовать только в конструкторе
         * суперкласса, в остальных случаях выбрасывается ошибка:
         * UnsupportedOperationException
         * */
        random.setSeed(15);
        System.out.println("Seeded Thread Local Random Integer: " + random.nextInt());
    }
}
