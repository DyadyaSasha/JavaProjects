package annotationAutowiredConfig;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {

    /**
     * required=false позволяет не определять значение для поля в XML конфиге(аналог {@link org.springframework.beans.factory.annotation.Required})
     * по умолчанию значение поля должно быть определено
     */
    @Autowired(required = false)
    private String message;
    private SpellChecker spellChecker;

    /**
     * аннотация {@link Autowired} позволяет внедрять бины не указывая их в XML
     * конфиге в тегах property и constructor-arg, чтобы autowired работал нужно просто определить внедряемые бины в XML конфиге
     * данную аннотацию можно проставлять над полями, сеттерами, конструкторами
     * поле, помеченное
     */
    @Autowired
    public void setSpellChecker( SpellChecker spellChecker ){
        this.spellChecker = spellChecker;
    }

    public SpellChecker getSpellChecker( ) {
        return spellChecker;
    }
    public void spellCheck(){
        spellChecker.checkSpelling();
    }

}
