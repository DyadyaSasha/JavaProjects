package more;

import com.google.inject.*;

public class OnDemandInjection {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule1());

        /**
         * с помощью метода injectMembers() можно внедрять зависимость, когда это необходимо
         */
        SpellChecker1 spellChecker = new SpellCheckerImpl1();
        injector.injectMembers(spellChecker);

        TextEditor1 editor = injector.getInstance(TextEditor1.class);
        editor.makeSpellCheck();
    }

}
class TextEditor1 {

    private SpellChecker1 spellChecker;

    @Inject
    public void setSpellChecker(SpellChecker1 spellChecker) {
        this.spellChecker = spellChecker;
    }

    public TextEditor1() { }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}

//Binding Module
class TextEditorModule1 extends AbstractModule {

    @Override
    protected void configure() {
    }
}

@ImplementedBy(SpellCheckerImpl1.class)
interface SpellChecker1 {
    void checkSpelling();
}

//spell checker implementation
class SpellCheckerImpl1 implements SpellChecker1 {

    public SpellCheckerImpl1(){}

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling." );
    }

}