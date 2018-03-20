package boundedtype;

public class BoundedTypeParameters {

    /**
     * можно ограничивать типы generic параметров, которые можно будет использовать в generic классах и generic методах
     * extends позволяет ограничить тип generic параметра "сверху", т.е. допустимые generic типы
     * должны быть наследниками указанного класса(или экземпляром этого класса) либо реализовывать указанный интерфейс
     * Также можно сделать множественное ограничение generic типа-параметра:
     *  public static <T extends Number & Comparable<T>> T maximum(T x, T y, T z)
     *   - передаваемый generic тип-параметр должен быть субклассом(или объектом этого класса) для {@link Number} и реализовывать интерфейс {@link Comparable}
     *   следует отметить, что при множественном ограничении недопустимо использование логического ИЛИ, и сначала всегда следуют классы, а затем интерфейсы
     *
     *
     * тип возвращаемого значения в данном случае - generic тип-параметр
     */
    public static <T extends Comparable<T>> T maximum(T x, T y, T z){
        T max = x;
        if (y.compareTo(max) > 0){
            max = y;
        }

        if(z.compareTo(max) > 0){
            max = z;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.printf("Max of %d, %d and %d is %d\n\n",
                3, 4, 5, maximum( 3, 4, 5 ));

        System.out.printf("Max of %.1f,%.1f and %.1f is %.1f\n\n",
                6.6, 8.8, 7.7, maximum( 6.6, 8.8, 7.7 ));

        System.out.printf("Max of %s, %s and %s is %s\n","pear",
                "apple", "orange", maximum("pear", "apple", "orange"));
    }
}
