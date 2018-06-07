package binding;

import com.google.inject.*;

public class ProvidesBinding {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule2());
        TextEditor2 editor = injector.getInstance(TextEditor2.class);
        editor.makeSpellCheck();
    }
}
class TextEditor2 {
    private SpellChecker2 spellChecker;

    @Inject
    public TextEditor2(SpellChecker2 spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule2 extends AbstractModule {

    @Override
    protected void configure() {}

    /**
     * {@link Provides} позволяет внедрять более сложные объекты-реализации
     * там где нужно внедрить реализацию {@link SpellChecker2} он внедряет реализацию из {@link Provides}
     */
    @Provides
    public SpellChecker2 provideSpellChecker() {
        String dbUrl = "jdbc:mysql://localhost:5326/emp";
        String user = "user";
        int timeout = 100;

        SpellChecker2 spellChecker = new SpellCheckerImpl2(dbUrl, user, timeout);
        return spellChecker;
    }
}

//spell checker interface
interface SpellChecker2 {
   void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl2 implements SpellChecker2 {

    private String dbUrl;
    private String user;
    private Integer timeout;

    //@Inject
    public SpellCheckerImpl2(String dbUrl,
                            String user,
                            Integer timeout) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.timeout = timeout;
    }

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
        System.out.println(dbUrl);
        System.out.println(user);
        System.out.println(timeout);
    }
}