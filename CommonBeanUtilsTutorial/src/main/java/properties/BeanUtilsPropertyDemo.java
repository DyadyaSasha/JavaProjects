package properties;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BeanUtilsPropertyDemo {
    public static void main(String[] args) {
        try {

            MyBean myBean = new MyBean();
            SubBean sb = new SubBean();
            myBean.setSubBean(sb);

            /**
             * {@link PropertyUtils} класс, предостовляющий методы для работы с полями Java бинов
             * есть три вида полей бина:
             * простой(simple) - поле примитивного типа либо класса(не коллекции)
             * индексированное (indexed) - поле коллекции(но не слловарь(map))
             * mapped - поле словаря({@link java.util.Map})
             * для каждого типа полей есть свои геттеры и сеттеры, но также есть общие
             * геттеры и сеттеры для всех видов полей:
             * {@link PropertyUtils.getProperty()} и {@link PropertyUtils.setProperty()}
             */
            /**
             * назначаем значение simple-полю: указываем бин, имя поля, и значение поля, которое нужно присвоить
             */
            PropertyUtils.setSimpleProperty(myBean, "stringProp", "Hello!This is a string");
            PropertyUtils.setSimpleProperty(myBean, "floatProp", new Float(25.2));

            /**
             * получаем значение simple-поля: указываем бин и имя нужного поля
             */
            System.out.println("String Property: " + PropertyUtils.getSimpleProperty(myBean, "stringProp"));
            System.out.println("Float Property: " + PropertyUtils.getSimpleProperty(myBean, "floatProp"));

            List list = new ArrayList();
            list.add("String value 0");
            list.add("String value 1");

            myBean.setListProp(list);
            /**
             * устанавливаем значение index-поля: указываем бин, имя массива с индексом элемента, который нужно поменять, и новое значение
             */
            PropertyUtils.setIndexedProperty(myBean, "listProp[1]", "This is new string value 1");
            /**
             * получаем значение элемента по конкретному индексу
             */
            System.out.println("List Property[1]: " + PropertyUtils.getIndexedProperty(myBean, "listProp[1]") );

            /**
             * устанавливаем значение поля объекта класса, который объявлен в классе myBean
             */
            PropertyUtils.setNestedProperty(myBean, "subBean.stringProperty", "Hello World from SubBean, set via Nested Property Access");
            System.out.println(PropertyUtils.getNestedProperty(myBean, "subBean.stringProperty"));


        }  catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
