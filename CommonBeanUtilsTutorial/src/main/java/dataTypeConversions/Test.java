package dataTypeConversions;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

public class Test {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        @SuppressWarnings("rawtypes")
        HashMap map = new HashMap();
        map.put("username","admin");
        map.put("password","secret");
        map.put("age","52");
        User bean = new User();

        try {
            /**
             * заполняет поля класса, имена которых соответствуют ключам словаря, значениями, соответствующие ключам
             */
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Username: " + bean.getUsername());
        System.out.println("Password: " + bean.getPassword());
        System.out.println("Age: "+ bean.getAge());

        String values[] = {"5", "6", "3"};
        /**
         * Конвертирует строковые значения в значения конкретного типа
         */
        double doubleValues[] = (double[]) ConvertUtils.convert(values, Double.TYPE);
        System.out.println(Arrays.toString(doubleValues));

    }
}

