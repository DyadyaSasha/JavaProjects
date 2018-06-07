package scopes;

import com.google.inject.*;

public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        SpellChecker spellChecker = new SpellCheckerImpl();
        injector.injectMembers(spellChecker);

        TextEditor editor = injector.getInstance(TextEditor.class);
        System.out.println(editor.getSpellCheckerId());

        TextEditor editor1 = injector.getInstance(TextEditor.class);
        System.out.println(editor1.getSpellCheckerId());
    }
}
class TextEditor {
    private SpellChecker spellChecker;

    @Inject
    public void setSpellChecker(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public TextEditor() { }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
    public double getSpellCheckerId() {
        return spellChecker.getId();
    }
}

//Binding Module
class TextEditorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SpellChecker.class).to(SpellCheckerImpl.class);
    }
}
interface SpellChecker {
    double getId();
    void checkSpelling();
}

/**
 * данная аннотация указывает, что зависимость будет создана один раз и будет использована во всех внедрениях при создании других объектов до завершения работы приложения
 * если данной аннотации не было то результаты System.out.println(editor.getSpellCheckerId()); и System.out.println(editor1.getSpellCheckerId());
 * были бы разными, смотри пример GuiceTester1
 * также есть аннотации:
 * {@link com.google.inject.servlet.SessionScoped} - действует во время сессии в веб-приложении
 * {@link com.google.inject.servlet.RequestScoped} - для любого запроса в веб-приложении
 */
@Singleton
class SpellCheckerImpl implements SpellChecker {
    double id;

    public SpellCheckerImpl() {
        id = Math.random();
    }
    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }
    @Override
    public double getId() {
        return id;
    }
}