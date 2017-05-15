import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by user on 13.05.17.
 */
@Retention(RetentionPolicy.RUNTIME)//время жизни аннотации(RUNTIME - во время компиляции)
public @interface SomeAnnotation {
    double val() default 0.0;
    String str() default "...";
}
