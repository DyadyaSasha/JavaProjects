import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by user on 13.05.17.
 */
public class AnnotationTest{

    @SomeAnnotation(str = "Test")
    public void method(){}

    public static void main(String args[]) throws NoSuchMethodException {

        Method m = AnnotationTest.class.getMethod("method", null);
        for (Annotation a : m.getAnnotations()){
            System.out.println(a);

        }

        SomeAnnotation b = m.getAnnotation(SomeAnnotation.class);
        if (m.isAnnotationPresent(SomeAnnotation.class)){
            System.out.println(b.val() + " " + b.str());
        }
    }
}
