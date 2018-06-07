package binding;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ConstructorBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule4());
        TextEditor4 editor = injector.getInstance(TextEditor4.class);
        editor.makeSpellCheck();
    }
}

class TextEditor4 {

    private SpellChecker4 spellChecker;

    @Inject
    public TextEditor4(SpellChecker4 spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule4 extends AbstractModule {

    @Override
    protected void configure() {
        try {
            /**
             * можно сопаставить интерфейс с конкретной реализацией при этом при создании объекта реализации будет использован
             * конкретный конструктор(его мы указываем в методе toConstructor())
             */
            bind(SpellChecker4.class).toConstructor(SpellCheckerImpl4.class.getConstructor(String.class));
        } catch (NoSuchMethodException | SecurityException e) {
            System.out.println("Required constructor missing");
        }

        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");
    }
}

//spell checker interface
interface SpellChecker4 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl4 implements SpellChecker4 {

    private String dbUrl;
    public SpellCheckerImpl4(){}

    /**
     * указываем, что в конструктор нужно будет внедрить знвчение с помощью {@link Named}
     */
    public SpellCheckerImpl4(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
        System.out.println(dbUrl);
    }
}
