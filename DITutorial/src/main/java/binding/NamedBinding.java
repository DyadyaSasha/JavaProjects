package binding;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Names;

import javax.inject.Named;


public class NamedBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule1());
        TextEditor1 editor = injector.getInstance(TextEditor1.class);
        editor.makeSpellCheck();
    }
}

class TextEditor1 {

    private SpellChecker1 spellChecker;
    private String dbUrl;

    /**
     * с помощью аннотации {@link Named} можно внедрять зависимость(реализацию) по имени
     */
    @Inject
    public TextEditor1(@Named("OpenOffice") SpellChecker1 spellChecker, @Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
        System.out.println(dbUrl);
    }


}

interface SpellChecker1 {
    void checkSpelling();
}

class SpellCheckerImpl1 implements SpellChecker1 {
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }
}

class OpenOfficeWordSpellCheckerImpl extends SpellCheckerImpl1 {

    @Override
    public void checkSpelling() {
        System.out.println("Inside OpenOfficeWordSpellCheckerImpl.checkSpelling." );    }
}

class TextEditorModule1 extends AbstractModule {

    @Override
    protected void configure() {
        /**
         * связываем интерфейс с реализацией по имени OpenOffice
         */
        bind(SpellChecker1.class).annotatedWith(Names.named("OpenOffice")).to(OpenOfficeWordSpellCheckerImpl.class);
        /**
         * можно указать значение поля и привязать с помощью аннотации {@link Named}
         */
        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/emp");
    }
}
