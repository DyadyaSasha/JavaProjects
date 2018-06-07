package aop;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AopTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

class TextEditor {
    private SpellChecker spellChecker;

    @Inject
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(SpellChecker.class).to(SpellCheckerImpl.class);

        /**
         * устанавливаем любому(any) методу, помеченному аннотацией {@link CallTracker} интерсептор {@link CallTrackerService}
         */
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(CallTracker.class), new CallTrackerService());
    }
}

//spell checker interface
interface SpellChecker {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

    @Override
    /**
     * подписываем своей аннотацией, чтобы вызов этого метода связать с интерсептором в методе configure()
     */
    @CallTracker
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CallTracker {}

/**
 * интерсептор, который будет вызываться до и после выполнения вызванного метода
 */
class CallTrackerService implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before " + invocation.getMethod().getName());
        Object result = invocation.proceed();
        System.out.println("After " + invocation.getMethod().getName());
        return result;
    }
}