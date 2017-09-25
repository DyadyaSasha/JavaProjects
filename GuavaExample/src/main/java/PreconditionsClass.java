import com.google.common.base.Preconditions;

/**
 * Preconditions представляет методы для проверки значения параметров,
 * передаваемых в конструктор или метод, соответствия определённому условию,
 * если параметр не удовлетворяет условию, то -> IllegalArgumentException
 * */
public class PreconditionsClass {
    public static void main(String[] args) {
        try {
            System.out.println(sqrt(-3.0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(sum(null, 3));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(getValue(6));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static double sqrt(double input) throws IllegalArgumentException{
        /**
         * проверяет соответствие аргумента определённому условию, в случае
         * несоответствия -> {@link IllegalArgumentException}
         * */
        Preconditions.checkArgument(input > 0.0,
                "Illegal Argument passed: Negative value %s", input);
        return Math.sqrt(input);
    }

    public static int sum(Integer a, Integer b) throws NullPointerException{
        /**
         * проверка на null, если не равно null, то возвращается
         * ссылка на значение, иначе -> {@link NullPointerException}
         * */
        a = Preconditions.checkNotNull(a, "Illegal Argument passed: First parameter is Null.");
        b = Preconditions.checkNotNull(b, "Illegal Argument passed: Second parameter is Null.");
        return a + b;
    }

    public static int getValue(int input) throws IndexOutOfBoundsException{
        int[] data = {1,2,3,4,5};
        /**
         * Проверка на существование индекса в массиве, в случае неудачи -> {@link IndexOutOfBoundsException}
         * */
        Preconditions.checkElementIndex(input, data.length, "Illegal Argument passed: Invalid index.");
        return 0;
    }
}
