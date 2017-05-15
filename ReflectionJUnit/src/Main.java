import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main{
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException{
        Class clazz = Identifier.class;
        int clazzModifiers = clazz.getModifiers();
        System.out.println("Class is final: "+ Modifier.isFinal(clazzModifiers));
        Constructor constructor = clazz.getDeclaredConstructor(null);
        int constructorModifiers = constructor.getModifiers();
        System.out.println("Constructor is protected: "+ Modifier.isProtected(constructorModifiers));
        Field field = clazz.getField("str");
        int fieldModifiers = field.getModifiers();
        System.out.println("Field is transient: " + Modifier.isTransient(fieldModifiers));
        Method method = clazz.getMethod("getString", null);
        int methodModifiers = method.getModifiers();
        System.out.println("Method is synchronized: " + Modifier.isSynchronized(methodModifiers));
    }
}
