package atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

public class TestReferenceArray {

    private static String[] source = new String[10];
    private static AtomicReferenceArray<String> atomicReferenceArray
            = new AtomicReferenceArray<>(source);

//    private static final String msg = "item-2";

    public static void main(final String[] args) throws InterruptedException{

        for(int i = 0; i<atomicReferenceArray.length(); i++){
            atomicReferenceArray.set(i, "item-2");
    }

        Thread t1= new Thread(new Increment());
        Thread t2= new Thread(new Compare());

        t1.start();
        t2.start();

        t1.join();
        t2.join();



//        String str = "item-2";
////        System.out.println(atomicReferenceArray.compareAndSet(1, msg, "udate"));
//        System.out.println(atomicReferenceArray.get(1));
//        System.out.println(atomicReferenceArray.get(1).equals(str));
//        System.out.println(atomicReferenceArray.get(1).hashCode() == str.hashCode());
//        System.out.println(atomicReferenceArray.compareAndSet(1, "item-2", "update"));
//        System.out.println("bites: " + msg.getBytes() + " Hash " + msg.hashCode());
//        System.out.println("bites: " + str.getBytes() + " Hash " + str.hashCode());
//
//        byte[] arr = msg.getBytes();
//        for (byte i : arr){
//            System.out.print(i);
//        }
//
//        System.out.println();
//
//        byte[] st = str.getBytes();
//        for (byte i : st){
//            System.out.print(i);
//        }
    }

    static class Increment implements Runnable{
        @Override
        public void run() {
            for(int i = 0; i < atomicReferenceArray.length(); i++){
                String add = atomicReferenceArray.getAndSet(i, "item-" + (i+1));
                System.out.println("Thread " + Thread.currentThread().getId()
                 + ", index " + i + ", value: " + add);
            }
        }
    }

    static class Compare implements Runnable{
        @Override
        public void run() {
            for(int i = 0; i < atomicReferenceArray.length(); i++){
                System.out.println("Thread " + Thread.currentThread().getId() +
                        ", index " + i + ", value: " + atomicReferenceArray.get(i));
                boolean swapped = atomicReferenceArray.compareAndSet(i, "item-2", "updated-item-2");
                System.out.println("Item swapped: " + swapped);
                if(swapped){
                    System.out.println("Thread " + Thread.currentThread().getId() +
                            ", index " + i + ", updated-item-2");
                }
            }
        }
    }
}
