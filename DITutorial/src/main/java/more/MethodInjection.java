package more;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class MethodInjection {

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
        bind(String.class).annotatedWith(Names.named("JDBC")).toInstance("jdbc:mysql://localhost:5326/");
    }

}

@ImplementedBy(SpellCheckerImpl.class)
interface SpellChecker {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl implements SpellChecker {

    private String dbUrl = "default value";
    public SpellCheckerImpl(){}

    /**
     * можно внедрять зависимость в метод
     * когда внедряется зависимость этого класса данныый метод выполниться автоматически
     * параметр optional позволяет внедрять зависимость, если она существует(чтобы это проверить нужно закоментировать
     * строчку с методом bind())
     */
    @Inject(optional = true)
    public void setDbUrl(@Named("JDBC") String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
        System.out.println(dbUrl);
    }

}