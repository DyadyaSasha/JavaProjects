package defaultmethods;

/**
 * default методы в интерфейсах позволяют написать реализацию метода, которая
 * будет использоваться, если класс, реализующий интерфейс, сам не реализует этот метод
 */
public class Tester implements Vehicle, FourWheeler{
    public static void main(String[] args) {
        Vehicle vehicle = new Tester();
        vehicle.print();
    }

    /**
     * если класс реализует два интерфейса с одинаковыми default методами, то данный метод нужно
     * реализовать в классе явно:
     *
     * public void print(){
     *   System.out.println("I am a four wheeler car vehicle!");
     * }
     */

    /**
     * Другое решение проблемы:
     * можно просто указать явно с помощью super, из какого интерфейса использовать default метод
     * default void print() {
            Vehicle.super.print();
       }
     */


    @Override
    public void print() {
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am a car!");
    }
}

interface Vehicle{

    default void print(){
        System.out.println("I am a vehicle!");
    }

    /**
     * Также в интерфейсе можно определить static метод
     */
    static void blowHorn(){
        System.out.println("Blowing horn!!!");
    }
}

interface FourWheeler{
    default void print(){
        System.out.println("I am a four wheeler!");
    }
}



