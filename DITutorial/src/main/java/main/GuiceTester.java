package main;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceTester {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new TextEditorModule());
        TextEditor editor = injector.getInstance(TextEditor.class);
        editor.makeSpellCheck();
    }
}

class TextEditor{

    private SpellChecker spellChecker;

    /**
     * когда у объекта {@link Injector} вызывается метод getInstance() с нужным параметром, вызывается конструктор и внедряется та реализация,
     * которая указана в классе-наследнике класса {@link AbstractModule}, данный класс мы указываем, когда создаем {@link Injector} с помощью метода createInjector()
     * если конструктор не приватный и без параметров, то его можно не помечать аннотацией {@link Inject}
     */
    @Inject
    public TextEditor(SpellChecker spellChecker){
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck(){
        spellChecker.checkSpelling();
    }

}

/**
 * некий интерфейс
 */
interface SpellChecker{
    void checkSpelling();
}

/**
 * реализация интерфейса
 */
class SpellCheckerImpl implements SpellChecker{

    @Override
    public void checkSpelling() {
        System.out.println("Inside checkSpelling.");
    }
}

class WinWordSpellCheckerImpl extends SpellCheckerImpl{
    @Override
    public void checkSpelling() {
        System.out.println("Inside WinWordSpellCheckerImpl.checkSpelling.");
    }
}

/**
 * класс, содержащий привязки интерфейсов к их конкретным реализациям(помогает, когда имеется несколько модулей с разными реализациями одного интерфейса)
 */
class TextEditorModule extends AbstractModule{

    @Override
    protected void configure() {
        /**
         * сопоставляем интерфейс с его реализацией
         */
        bind(SpellChecker.class).to(SpellCheckerImpl.class);

        /**
         * также можно сопоставлять класс с классом наследником
         * можно делать цепочки зависимостей
         */
        bind(SpellCheckerImpl.class).to(WinWordSpellCheckerImpl.class);
    }
}


