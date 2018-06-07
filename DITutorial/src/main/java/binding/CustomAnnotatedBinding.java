package binding;

import com.google.inject.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * создаём аннотацию для того чтобы связывать через неё интерфейс и реализацию
 * аннотация {@link BindingAnnotation} показывает, что мы создаём связующую аннотацию
 */
@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
@interface WinWord {
}

public class CustomAnnotatedBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

class TextEditor {
    private SpellChecker spellChecker;

    /**
     * помечаем, что внедрение зависимости будет происходить через аннотацию {@link WinWord}
     */
    @Inject
    public TextEditor(@WinWord SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }


}

interface SpellChecker {
    void checkSpelling();
}

class SpellCheckerImpl implements SpellChecker {
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }
}

class WinWordSpellCheckerImpl extends SpellCheckerImpl {

    @Override
    public void checkSpelling() {
        System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling.");
    }
}

class TextEditorModule extends AbstractModule {
    @Override
    protected void configure() {
        /**
         * указываем, что интерфейс связан с реализацией с помощью аннотации
         */
        bind(SpellChecker.class).annotatedWith(WinWord.class).to(WinWordSpellCheckerImpl.class);
        bind(SpellChecker.class).to(SpellCheckerImpl.class);

    }
}
