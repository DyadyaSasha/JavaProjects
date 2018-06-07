package singleton;

/**
 * синглтон используется, когда нужен только один объект конкретного класса
 */
public class SingleObject {

    private static SingleObject instance = new SingleObject();

    /**
     * делаем конструктор приватным, чтобы в других классах
     * нельзя было создоть новый объект синглтона
     */
    private SingleObject(){}

    public static SingleObject getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello!");
    }
}
