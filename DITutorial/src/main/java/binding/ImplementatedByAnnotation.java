package binding;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ImplementatedByAnnotation {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule6());
        TextEditor6 editor = injector.getInstance(TextEditor6.class);
        editor.makeSpellCheck();
    }
}

class TextEditor6 {

    private SpellChecker6 spellChecker;

    @Inject
    public TextEditor6(SpellChecker6 spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule6 extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("JDBC"))
                .toInstance("jdbc:mysql://localhost:5326/emp");
    }
}

/**
 * указываем класс, который будет реализовывать данный интерфейс
 * используя аннотацию {@link ImplementedBy}, можно не писать связывание интерфейса и его реализации в методе configure()
 */
@ImplementedBy(SpellCheckerImpl6.class)
interface SpellChecker6 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl6 implements SpellChecker6 {

    /**
     * помечаем поле, которое хотим внедрить
     */
    @Inject @Named("JDBC")
    private String dbUrl;

    public SpellCheckerImpl6(){}

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
        System.out.println(dbUrl);
    }
}