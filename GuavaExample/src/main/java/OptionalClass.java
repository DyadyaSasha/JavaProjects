import com.google.common.base.Optional;


/**
 * класс Optional позволяет представлять null в качестве absent-значения,
 * что позволяет эффективно работать с ссылками на null
 * */
public class OptionalClass {
    public static void main(String[] args) {

        Integer value1 = null;
        Integer value2 = new Integer(10);

        /**
         * Если ссылка значения ненуевая, то вернётся ссылка типа Option<T>,
         * содержащее это значение, если нулевая, то применится метод absent(),
         * который используется в качестве заглушки для объектов, содержащих ссылку
         * null
         * */
        Optional<Integer> a = Optional.fromNullable(value1);

        /**
         * of() присваивает значение ненулевой ссылки объекту типа Option,
         * если в метод of() передана ссылка на null, то будет исключение
         * NullPointerException
         * */
        Optional<Integer> b = Optional.of(value2);

        System.out.println(sum(a,b));
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){

        /**
         * isPresent() вернёт true, если переменная a не содержит в себе ссылки на null,
         * иначе - false
         * */
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        /**
         * если ссылка a не содержит ссылку на null, то возвращается её значение, иначе
         * возвращается значение, указанное в методе в качестве аргумента
         * */
        Integer value1 = a.or(new Integer(0));

        /**
         * Возвращает значение из объекта Option, если оно содержит ссылку null,
         * то -> NullPointerException
         * */
        Integer value2 = b.get();
        return value1 + value2;
    }
}
