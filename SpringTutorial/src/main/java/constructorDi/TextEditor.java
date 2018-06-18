package constructorDi;

public class TextEditor {

    /**
     * объект данного класса будет внедряться через конструктор {@link TextEditor}
     */
    private SpellChecker spellChecker;

    public TextEditor(SpellChecker spellChecker, int i){
        System.out.println("Inside TextEditor constructor.");
        this.spellChecker = spellChecker;
    }

    public void spellCheck(){
        spellChecker.checkSpelling();
    }
}
