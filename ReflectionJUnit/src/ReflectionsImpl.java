import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * Created by user on 13.05.17.
 */
public class ReflectionsImpl implements Reflections{

    @Override
    public Object getfieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        return null;
    }

    @Override
    public Set<String> getProtectedMehodNames(Class clazz) {
        return null;
    }

    @Override
    public Set<Method> getAllImpementedMethodsWithSupers(Class clazz) {
        return null;
    }

    @Override
    public List<Class> getExtendendsHierarchy(Class clazz) {
        return null;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        return null;
    }

    @Override
    public List<Class> getThrownExceptions(Method mrthod) {
        return null;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        Class<?> clazz;
        String result = null;
        try {
//            clazz = Class.forName("Reflections$SecretClass");//знаем точное имя класса
            clazz = Class.forName("Reflections");//ищем класс по имени
            clazz = clazz.getClasses()[0];//берем список классов внутри класса clazz

            Constructor<?> constructor = clazz.getDeclaredConstructor(new Class<?>[0]);//берём конструктор
            constructor.setAccessible(true);//разрешаем доступ к этому конструктору

            Object secretClassInstance = constructor.newInstance(new Object[0]);

            Method method1 = clazz.getDeclaredMethod("foo", new Class<?>[0]);//аргументов нет, поэтому new Class<?>[0]

            Method method2 = clazz.getDeclaredMethod("foo", new Class<?>[] {String.class, Integer[].class});//вызываем метод с параметрами

            method1.setAccessible(true);//открываем доступ к private методу
            method2.setAccessible(true);

//            result = (String)method1.invoke(secretClassInstance, new Class<?>[0]); //secretClassInstance - конкретный экземпляр класса, new Class<?>[0] - список параметров(в данном случае их нет)
            result  = (String)method2.invoke(secretClassInstance, new Object[] {"Sum", new Integer[]{1,2,3,4,5}});//вызов метода с параметрами
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Class was not found", e);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Method or constructor was not found", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Constructor error", e);
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("Constructor error", e);
        }
        return result;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        return null;
    }

    public static void main(String[] args){
        System.out.println(new ReflectionsImpl().getFooFunctionResultForDefaultConstructedClass());
    }
}
