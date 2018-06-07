package genericclasses;

public class GenericClass {
    public static void main(String[] args) {


        /**
         * diamond оператор (пустые скобки <>), который доступен с Java 7, позволяет не писать при создании объекта, его тип.
         * Компилятор сам определяет этот тип, согласно тому, какой ссылке присваивается этот объект
         */
        Box<Integer> integerBox = new Box<>();

        /**
         * компилятор может вывести предупреждение об неконтролируемом приведении типа, если тип указать явно в <> при создании объекта
         */
        Box<String> stringBox = new Box<String>();

        integerBox.add(new Integer(10));
        stringBox.add("Hello");

        System.out.printf("Integer Value: %d\n", integerBox.get());
        System.out.printf("String Value: %s\n", stringBox.get());
    }
}

/**
 * Объявление generic класса, выглядит как объявление обычного класса, за исключением того,
 * что после имени класса следует generic тип(параметр(тип класса), принимаемый в этот класс) в <> скобках
 * generic типов(параметров) может быть несколько(перечисляются через запятую)
 * <T> - generic type параметр, передаваемый в generic класс Box, generic type-параметр не может быть примитивом - могут быть только ссылочные типы
 * <p>
 * После создание объекта generic класса с конкретноым принимаемым generic типом, проверка generic типа
 * при вызове методов будет проходить во время компиляции
 * <p>
 * 1) примитивы не могут использоваться в качестве generic типа-параметра:
 * Box<int> stringBox = new Box<int>(); - ошибка при компиляции
 * <p>
 * 2) ссылки generic типа в классе не могут быть static!(так как при компиляции не могут конкретно быть определены)
 * <p>
 * 3) generic тип-параметр не может быть использован, чтобы создать объект того же типа:
 * T item = new T(); - ошибка компиляции
 * чтобы это реализовать нужно использовать рефлексию:
 * public static <T> void add(Box<T> box, Class<T> clazz)
 * throws InstantiationException, IllegalAccessException{
 * T item = clazz.newInstance();   // OK
 * }
 * <p>
 * 4) класс не может иметь два перегруженных метода, в которые имеют одинаковую сигнатуру после стирания generic типа-параметра:
 * public void print(List<String> stringList) { }
 * public void print(List<Integer> integerList) { } <- ошибка компиляции
 * <p>
 * 5) generic класс не может прямо или косвенно наследоваться от {@link Throwable} и от {@link Exception}:
 * class Box<T> extends Exception {} - ошибка компиляции
 * class Box1<T> extends Throwable {} - ошибка компиляции
 * <p>
 * в catch() не может быть указано экземпляра generic типа:
 * public static <T extends Exception, J> void execute(List<J> jobs) {
 * try {
 * for (J job : jobs){}
 * } catch (T e) {} - ошибка компиляции
 * }
 * <p>
 * generic типы-параметры допустимы в throws:
 * <p>
 * class Box<T extends Exception>  {
 * private int t;
 * <p>
 * public void add(int t) throws T {
 * this.t = t;
 * }
 * }
 * <p>
 * 6) приведение generic классов не допустимо:
 * Box<Integer> integerBox = new Box<Integer>();
 * Box<Number> numberBox = new Box<Number>();
 * integerBox = (Box<Integer>)numberBox; - ошибка компиляции
 * <p>
 * допустимо лишь в случае, когда generic тип-параметр обозначен в виде ?:
 * private static void add(Box<?> numberBox){
 * Box<Integer> integerBox = (Box<Integer>)numberBox;
 * }
 * <p>
 * 7) Так как компилятор использует стирание типов(смотри typeerasure.работа_компилятора.txt), то
 * среда выполнения не отслеживает параметры типов, поэтому во время выполнения программы невозможно
 * проверить разницу между Box<Integer> и Box<String>, поэтому использование оператора instanceof с generic классами недопустимо
 * Box<Integer> integerBox = new Box<Integer>();
 * if(integerBox instanceof Box<Integer>){} - ошибка компиляции
 * 8) Массивы параметризованного типа(generic типа) недопустимы:
 * Object[] stringBoxes = new Box<String>[]; - ошибка компиляции
 */
class Box<T> {

//    ошибка компиляции: ссылки generic типа в классе не могут быть static
//    private static T q;

    private T t;

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}
