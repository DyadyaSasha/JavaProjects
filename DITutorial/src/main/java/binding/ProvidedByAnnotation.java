package binding;

import com.google.inject.*;

public class ProvidedByAnnotation {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule7());
        TextEditor7 editor = injector.getInstance(TextEditor7.class);
        editor.makeSpellCheck();
    }
}

class TextEditor7 {

    private SpellChecker7 spellChecker;

    @Inject
    public TextEditor7(SpellChecker7 spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule7 extends AbstractModule {

    @Override
    protected void configure() {
    }
}

/**
 * указываем класс-поставщик, который будет предоставлять реализацию данного интерфейса
 * аннотацией {@link ProvidedBy} указывается класс-поставщик реализации интерфейса, использование этой аннотации позволяет не указывать класс-провайдер
 * в методе configure()
 */
@ProvidedBy(SpellCheckerProvider7.class)
interface SpellChecker7 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl7 implements SpellChecker7 {
    private String dbUrl;
    private String user;
    private Integer timeout;

    @Inject
    public SpellCheckerImpl7(String dbUrl,
                             String user,
                             Integer timeout) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.timeout = timeout;
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
        System.out.println(dbUrl);
        System.out.println(user);
        System.out.println(timeout);
    }
}

class SpellCheckerProvider7 implements Provider<SpellChecker7> {

    @Override
    public SpellChecker7 get() {
        String dbUrl = "jdbc:mysql://localhost:5326/emp";
        String user = "user";
        int timeout = 100;

        SpellChecker7 spellChecker = new SpellCheckerImpl7(dbUrl, user, timeout);
        return spellChecker;
    }
}