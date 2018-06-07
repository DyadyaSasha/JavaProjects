package binding;

import com.google.inject.*;

public class ProviderInterface {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule3());
        TextEditor3 editor = injector.getInstance(TextEditor3.class);
        editor.makeSpellCheck();
    }
}

class TextEditor3 {
    private SpellChecker3 spellChecker;

    @Inject
    public TextEditor3(SpellChecker3 spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule3 extends AbstractModule {

    @Override
    protected void configure() {
        /**
         * указываем поставщика реализации интерфейса
         */
        bind(SpellChecker3.class).toProvider(SpellCheckerProvider.class);
    }
}

//spell checker interface
interface SpellChecker3 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl3 implements SpellChecker3 {

    private String dbUrl;
    private String user;
    private Integer timeout;

    //@Inject
    public SpellCheckerImpl3(String dbUrl,
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

/**
 * с помощью параметризованного интерфейса {@link Provider} можно создавать реализации кнужного интерфейса
 * (схоже с аннотацией {@link Provides} в классе-наследнике {@link AbstractModule})
 */
class SpellCheckerProvider implements Provider<SpellChecker3> {

    @Override
    public SpellChecker3 get() {
        String dbUrl = "jdbc:mysql://localhost:5326/emp";
        String user = "user";
        int timeout = 100;

        SpellChecker3 spellChecker = new SpellCheckerImpl3(dbUrl, user, timeout);
        return spellChecker;
    }
}